from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
import numpy as np
import io
import os
import cv2
from PIL import Image
from model import get_predicted_roll_numbers
import base64

app = Flask(__name__)
CORS(app)

knn_model = joblib.load(r'E:\7th sem\monitoring\project\smart_attendance_python\knn_model.joblib')


@app.route('/predict_roll_numbers',methods=['POST'])
def predict_roll_numbers_api():

   
    if 'image' not in request.files:
        return jsonify({'error': 'No image provided'})

    # Get the image file from the form data
    image_file = request.files.get('image')
    

    image_data = Image.open(io.BytesIO(image_file.read()))
    image_data = Image.open(image_file)
    
    roll_numbers = get_predicted_roll_numbers(knn_model, image_data)



    # test_image_path = request.json['test_image_path']
    # roll_numbers = get_predicted_roll_numbers(knn_model,test_image_path)

    roll_numbers = [roll_number.tolist() for roll_number in roll_numbers]
    return jsonify({'roll_numbers':roll_numbers})


if __name__ == '__main__':
    app.run(debug = True)

