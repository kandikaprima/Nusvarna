from flask import Flask, request, session, jsonify
import pyrebase
import cv2
import numpy as np
import tensorflow as tf
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)
config = {
  'apiKey': "AIzaSyAa667qOWokjNtRSTP0kTg0ZvTDalQopAI",
  'authDomain': "moonlit-poetry-381319.firebaseapp.com",
  'projectId': "moonlit-poetry-381319",
  'storageBucket': "moonlit-poetry-381319.appspot.com",
  'messagingSenderId': "167385462735",
  'appId': "1:167385462735:web:a53855ed0b1a32a65d04ba",
  'databaseURL':'https://moonlit-poetry-381319-default-rtdb.asia-southeast1.firebasedatabase.app/'
}

firebase = pyrebase.initialize_app(config)
auth = firebase.auth()
db = firebase.database()
storage = firebase.storage()
model = tf.keras.models.load_model('current_model.h5')

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
                'profile_photo_url': 'https://firebasestorage.googleapis.com/v0/b/moonlit-poetry-381319.appspot.com/o/profile%2FTak%20berjudul115_20230615225802.png?alt=media&token=4ad22332-298d-4114-8737-793b3275dbd2',
                'poin': 0
            }
            db.child('data_user').child(user['localId']).set(data)         

            return jsonify({'status': 'Registrasi Berhasil'})
        except Exception as e:
            print(str(e))
            return jsonify({'error': 'Registrasi gagal'}), 400

@app.route('/login', methods=['POST', 'GET'])
def login():
    email = request.form.get('email')
    password = request.form.get('password')
    try:
        user = auth.sign_in_with_email_and_password(email, password)
        token = user['idToken']
        return jsonify({'access_token': token})
    except:
        return jsonify({'error': 'password email salah'}), 401

@app.route('/profile', methods=['GET', 'POST'])
def profile():
    # Retrieve access token from request headers
    access_token = request.headers.get('Authorization')

    # Check if access token is missing
    if not access_token:
        return jsonify({'error': 'Access token tidak ditemukan'}), 400

    try:
        user = auth.get_account_info(access_token)
        uid = user['users'][0]['localId']
        user_data = db.child('data_user').child(uid).get().val()
        if not user_data:
            return jsonify({'error': 'Data pengguna tidak ditemukan'}), 404

        return jsonify(user_data)

    except:
        return jsonify({'error': 'Gagal mengambil data pengguna'}), 400
  
@app.route('/profile/edit', methods=['POST'])
def edit_profile():
    access_token = request.headers.get('Authorization')
    if not access_token:
        return jsonify({'error': 'Access token not found'}), 401
    try:
        user = auth.get_account_info(access_token)
        uid = user['users'][0]['localId']
        user_data = db.child('data_user').child(uid).get().val()
        if not user_data:
            return jsonify({'error': 'User data not found'}), 404
        new_name = request.form.get('name')
        if new_name:
            user_data['name'] = new_name
            db.child('data_user').child(uid).update(user_data)
            return jsonify({'message': 'Profile updated successfully'})
        return jsonify({'error': 'No new name provided'}), 400
    except Exception as e:
        print(str(e))
        return jsonify({'error': 'Failed to update profile'}), 400

UPLOAD_FOLDER = '/'
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/profile/foto_profile', methods=['POST'])
def upload_profile_photo():
    access_token = request.headers.get('Authorization')
    if not access_token:
        return jsonify({'error': 'Access token tidak ditemukan', 'keyy' : access_token}), 401
    try:
        user = auth.get_account_info(access_token)
        uid = user['users'][0]['localId']
        user_data = db.child('data_user').child(uid).get().val()
        if not user_data:
            return jsonify({'error': 'Data pengguna tidak ditemukan'}), 404
        if 'file' not in request.files:
            return jsonify({'error': 'No file part in the request'}), 400
        file = request.files['file']
        if file.filename == '':
            return jsonify({'error': 'No file selected'}), 400
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename) # type: ignore
            file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
            file.save(file_path)
            storage.child('profile').child(filename).put(file_path)
            download_url = storage.child('profile').child(filename).get_url(None)
            user_data['profile_photo_url'] = download_url 
            db.child('data_user').child(uid).update(user_data)
            return jsonify({'message': 'Foto profil berhasil diupload', 'profile_photo_url': download_url})
        return jsonify({'error': 'File type not allowed'}), 400
    except Exception as e:
        print(str(e))
        return jsonify({'error': 'Gagal mengupload foto profil'}), 500

@app.route('/forgot_password', methods=['POST', 'GET'])
def forgot_password():
    email = request.form.get('email')
    try:
        auth.send_password_reset_email(email)
        return jsonify({'message': 'Password reset email sent'})
    except:
        return jsonify({'error': 'Failed to send password reset email'}), 400

@app.route('/predict', methods=['POST', 'GET'])
def predict():
        if 'image' not in request.files:
            return jsonify({'error': 'No image uploaded'}), 400
        image = request.files['image']
        img = cv2.imdecode(np.frombuffer(image.read(), np.uint8), cv2.IMREAD_COLOR)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        img = cv2.resize(img, (150, 150))
        img = img.astype(np.float32) / 255.0
        img = np.expand_dims(img, axis=0)
        pred = model.predict(img)  # type: ignore
        ml_class = ["asmat", "bali", "dayak", "madura"]
        if (pred.max() > 0.5):
            hasil = np.argmax(pred)
            predicted = db.child('baju_tradisional').child(ml_class[hasil]).get().val()
            return jsonify({'predicted_label': ml_class[hasil],'detail' : predicted})
        else:
            return jsonify({'error': 'tidak terdeteksi'})

@app.route('/data', methods=['POST'])
def get_data():
    data_id = request.form.get('select')
    if not data_id:
        return jsonify({'error': 'Missing data ID parameter'}), 400
    if data_id == '1':
        data = db.child('baju_tradisional').child('asmat').get().val()
    elif data_id == '2':
        data = db.child('baju_tradisional').child('bali').get().val()
    elif data_id == '3':
        data = db.child('baju_tradisional').child('bugis').get().val()
    elif data_id == '4':
        data = db.child('baju_tradisional').child('dayak').get().val()
    elif data_id == '5':
        data = db.child('baju_tradisional').child('madura').get().val()
    elif data_id == '6':
        data = db.child('baju_tradisional').child('minang').get().val()
    else:
        return jsonify({'error': 'Invalid data ID'}), 400
    return jsonify(data)

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug = True, port=8080)
