# Nusvarna(Nusantara Berwarna)
**"Budaya yang Berwarna"**

Nusvarna is a mobile app that would give education about Indonesian local cultures, particularly by introducing the various kinds of traditional dresses in Indonesia through image recognition

# Machine Learning
1. dataset
We are collecting traditional dress images from google images with web scraping. We got 1800 images with 6 classes with 300 images in each class.
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Dataset.png "Nusvarna Dataset")
after we got our dataset, next step is clean the image and we got 750 images with 6 classes with 125 iamges in each class

2. augmentation
After getting and cleaning the image, we add augmentation to the dataset
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Augmentasi.png "AUGMENTATION")

4. Machine Learning Model
We build the model with TensorFlow. We use the Convolutional Neural Network method in our models. With images size 150, batch size 32, and 3 convolution layers
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Model%20Summary.png "MODEL SUMMARY")

5. accuracy & loss graphic
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Accuracy.png "ACCURACY")
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Loss.png "LOSS")

7. testing model
![alt text](https://github.com/kandikaprima/Nusvarna/blob/machine-learning/assets/Testing%20Model.png "TESTING MODEL")

# Developers
Machine Learning
* Kandika Prima Putra [Github](https://github.com/kandikaprima) [LinkedIn](https://www.linkedin.com/in/kandikaprima/)

* Mery Yulinda Rahmi [Github](https://github.com/meryyulinda) [LinkedIn](https://www.linkedin.com/)

* Lisa Asaliontin [Github](https://github.com/lisaasaliontin) [LinkedIn](https://www.linkedin.com/in/lisa-asaliontin-4210b8216/)

Cloud Computing
* Muhammad Al Fahri [Github](https://github.com/alfahri18) [LinkedIn](https://www.linkedin.com/in/al-fahri-8361bb1a4/)

* Okta Tiara Novitasari [Github](https://github.com/Ashleyookta) [LinkedIn](https://www.linkedin.com/in/okta-tiara-novitasari-017034247/)

Mobile Development
* Gusti Aqhsal Mujahid [Github](https://github.com/shall31) [LinkedIn](https://www.linkedin.com/in/gustiaqhsal/)

C23-PS065 - Project NUSVARNA@2023
