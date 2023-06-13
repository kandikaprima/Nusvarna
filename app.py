from flask import Flask, render_template, request, redirect, session, jsonify
import pyrebase
import cv2
import numpy as np
import tensorflow as tf



app = Flask(__name__, template_folder='template')
config = {
  'apiKey': "",
  'authDomain': "",
  'databaseURL': "",
  'projectId': "",
  'storageBucket': "",
  'messagingSenderId': "",
  'appId': "",
  'databaseURL':''
}

firebase = pyrebase.initialize_app(config)
auth = firebase.auth()
db = firebase.database()
storage = firebase.storage()
model = tf.keras.models.load_model('current_model.h5')
class_labels = ['bingka ambon', 'pisang ijo ']

@app.route('/register', methods=['POST', 'GET'])
def register():
        email = request.form.get('email')
        password = request.form.get('password')
        username = request.form.get('username')
        nama = request.form.get('nama')
        try:
            user = auth.create_user_with_email_and_password(email, password)
            data = {
                'name': nama,
                'username': username,
                'email': email,
                'poin': 0
            }
            db.child('data_user').child(user['localId']).set(data)         

            return jsonify({'status': 'Registrasi Berhasil'})
        except Exception as e:
            print(str(e))
            return jsonify({'error': 'Registrasi gagal'}), 400


@app.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == 'POST':
        email = request.form.get('email')
        password = request.form.get('password')
        try:
            user = auth.sign_in_with_email_and_password(email, password)
            token = user['idToken']

            return jsonify({'access_token': token})
        except:
            return jsonify({'error': 'password email salah'}), 401
    return render_template('login.html')

@app.route('/profile', methods=['GET'])
def profile():
    access_token = request.headers.get('Authorization')
    if not access_token:
        return jsonify({'error': 'Access token tidak ditemukan'}), 401

    try:
        # Verify access token and get user UID
        user = auth.get_account_info(access_token)
        uid = user['users'][0]['localId']
        # uid = user['uid']

        # Get user data from Realtime Database based on UID
        user_data = db.child('users').child(uid).get().val()

        if not user_data:
            return jsonify({'error': 'Data pengguna tidak ditemukan'}), 404

        # Send response with user data
        return jsonify(user_data)

    except Exception as e:
        print(str(e))
        return jsonify({'error': 'Gagal mengambil data pengguna'}), 400


@app.route('/change_password', methods=['POST', 'GET'])
def change_password():
        access_token = session.get('access_token')
        if access_token:
            try:
                user = auth.get_account_info(access_token)
                email = user['users'][0]['email']

                current_password = request.form.get('current_password')
                new_password = request.form.get('new_password')

                auth.sign_in_with_email_and_password(email, current_password)
                auth.change_user_password(access_token, new_password) # type: ignore

                return jsonify({'message': 'Password changed successfully'})
            except:
                return jsonify({'error': 'Failed to change password'}), 400
        else:
            return jsonify({'error': 'User not logged in'}), 401


@app.route('/forgot_password', methods=['POST', 'GET'])
def forgot_password():
    if request.method == 'POST':
        email = request.form.get('email')
        try:
            auth.send_password_reset_email(email)
            return jsonify({'message': 'Password reset email sent'})
        except:
            return jsonify({'error': 'Failed to send password reset email'}), 400

    return render_template('forgot_password.html')

@app.route('/predict', methods=['POST', 'GET'])
def predict():
        if 'image' not in request.files:
            return jsonify({'error': 'No image uploaded'}), 400

        # Read the uploaded image file
        image = request.files['image']
        img = cv2.imdecode(np.frombuffer(image.read(), np.uint8), cv2.IMREAD_COLOR)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

        # Resize the image to 150x150
        img = cv2.resize(img, (150, 150))

        # Preprocess the image
        img = img.astype(np.float32) / 255.0
        img = np.expand_dims(img, axis=0)

        # Make predictions using the model
        predictions = model.predict(img) # type: ignore
        predicted_class = np.argmax(predictions)
        predicted_label = class_labels[predicted_class]
        if predicted_class == 0:
            predicted = db.child('baju_tradisional').child('bugis').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        elif predicted_class == 1:
            predicted = db.child('baju_tradisional').child('minang').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        elif predicted_class == 2:
            predicted = db.child('baju_tradisional').child('jawa').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        elif predicted_class == 3:
            predicted = db.child('baju_tradisional').child('papua').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        elif predicted_class == 4:
            predicted = db.child('baju_tradisional').child('kalimantan').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        elif predicted_class == 5:
            predicted = db.child('baju_tradisional').child('bali').get().val()
            return jsonify({'predicted_label': predicted_label}, predicted)
        else:
                return jsonify({'error': 'tidak terdeteksi', })


@app.route('/logout', methods=['POST'])
def logout():
    access_token = request.headers.get('Authorization')

    if not access_token:
        return jsonify({'error': 'Access token not found'}), 401

    try:
        # Mendekode access token dan mendapatkan UID pengguna
        decoded_token = firebase.auth.verify_id_token(access_token)
        uid = decoded_token['uid']

        # Menghapus refresh token pengguna dari Firebase Authentication
        firebase.auth.revoke_refresh_tokens(uid)

        # Hapus access token dari sesi Flask
        session.clear()

        return jsonify({'message': 'Logout successful'})

    except Exception as e:
        print(str(e))
        return jsonify({'error': 'Failed to logout'}), 400
    
if __name__ == '__main__':
    app.run(debug = True, port=8080)
