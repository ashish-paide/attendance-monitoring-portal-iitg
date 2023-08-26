import cv2
import os
from mtcnn import MTCNN
from IPython.display import display
from PIL import Image
from deepface import DeepFace
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import joblib
detector = MTCNN()


# give the path of folder of each person and get the list of embeddings and labels of same person
def get_embeddings(roll_path):
    embeddings = []
    labels = []
    for file in os.listdir(roll_path):
        file_path = os.path.join(roll_path,file)
        embedding = DeepFace.represent(file_path, model_name='Facenet',enforce_detection=False)
        embeddings.append(embedding[0]['embedding'])
    roll_number = os.path.basename(roll_path)
    labels = [roll_number]*len(os.listdir(roll_path))  
    return embeddings,labels


# data_path = r'C:\Users\Venkatesh Yeturi\OneDrive\Desktop\auto_attendance_python\Data\cropped_faces_dataset'

# # if knn_model is not already existing , get all the embeddings and train a model and save. 
# if not os.path.exists('knn_model.joblib'):

#     embeddings = []
#     labels = []
#     for each_roll in os.listdir(data_path):
#         roll_path = os.path.join(data_path,each_roll)
#         person_embeddings, person_labels = get_embeddings(roll_path)
#         embeddings.append(person_embeddings)
#         labels.append(person_labels)
#     embeddings = np.concatenate(embeddings, axis=0)
#     labels = np.concatenate(labels, axis=0)

#     knn = KNeighborsClassifier(n_neighbors=3)
#     knn.fit(embeddings, labels)
#     joblib.dump(knn,'knn_model.joblib')

# give path of test_image and saved model, get the predictions ( roll numbers )
def get_predicted_roll_numbers(model, image_data):

    if isinstance(image_data,np.ndarray):
        image_data = Image.fromarray(image_data)
    # raw_test_image_path = os.path.normpath(test_image_path)
    # test_image = cv2.imread(raw_test_image_path)

    faces = detector.detect_faces(np.array(image_data))
    current_script_path = os.path.abspath(__file__)
    current_dir_path = os.path.dirname(current_script_path)
    data_path = os.path.join(current_dir_path,"Data")
    incoming_data_path = os.path.join(data_path,"incoming_data")

    if not os.path.exists(incoming_data_path):
        os.makedirs(incoming_data_path)

    count = len(os.listdir(incoming_data_path))
    incoming_file_saving_path = os.path.join(incoming_data_path,f"_{count+1}.jpg")
    cv2.imwrite(incoming_file_saving_path,np.array(image_data))
    test_image = cv2.imread(incoming_file_saving_path)
    raw_test_image_path = incoming_file_saving_path

    parent_folder_path = os.path.dirname(raw_test_image_path)
    name = os.path.basename(raw_test_image_path)[:-4] + "_faces"
    new_faces_folder = os.path.join(parent_folder_path,name)
    if not os.path.exists(new_faces_folder):
        os.makedirs(new_faces_folder)


    roll_numbers = []
    for i,face in enumerate(faces):
        x,y,w,h = face['box']
        face_image = image_data.crop((x,y,x+w,y+h))
        face_image_path = os.path.join(new_faces_folder,f'face_{i}.jpg')
        face_image.save(face_image_path)
        face_embedding = DeepFace.represent(face_image_path,model_name='Facenet',enforce_detection=False)[0]['embedding']
        roll_number = model.predict([face_embedding])
        roll_numbers.append(roll_number)
    return roll_numbers


