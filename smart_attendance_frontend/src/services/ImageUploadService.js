import React from 'react'
import axios from 'axios'


export async function uploadImage(image) {
  try {
        const response = await axios.post('http://localhost:8080/api/uploadImage' , image);
        console.log(response);
        const convertedData = response.data.roll_numbers.map(innerArray => innerArray[0])
        const finalresponse=await axios.post('http://localhost:8080/api/getStudentsById',convertedData);
        return finalresponse.data;
      } catch (error) {
        console.error('Error uploading image:', error);
        throw error;
      }
}


