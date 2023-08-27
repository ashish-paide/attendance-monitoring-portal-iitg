import React from 'react'
import axios from 'axios'


export async function courseId_by_date(courseId) {
  try {
        const response = await axios.get(`http://localhost:8080/api/getStudentsPresentPerDateByClassId/${courseId}`);
        console.log(response);
        return response;
      } catch (error) {
        console.error('Error uploading image:', error);
        throw error;
      }
}

export async function courseId_by_student(courseId) {
    try {
          const response = await axios.get(`http://localhost:8080/api/getTotalPresenListofStudents/${courseId}`);
          console.log(response);
          return response;
        } catch (error) {
          console.error('Error uploading image:', error);
          throw error;
        }
  }

  export async function student_by_course_by_date(courseId,rollNumber,date) {
    try {
          const response = await axios.get(`http://localhost:8080/api/getWhetherPresent/${courseId}/${date}/${rollNumber}`);
          console.log(response);
          return response;
        } catch (error) {
          console.error('Error uploading image:', error);
          throw error;
        }
  }

  export async function student_by_course(courseId,rollNumber) {
    try {
          const response = await axios.get(`http://localhost:8080/api/getStudentAttendanceListInCourse/${courseId}/${rollNumber}`);
          console.log(response);
          return response;
        } catch (error) {
          console.error('Error uploading image:', error);
          throw error;
       }
}

export async function student_by_course_average(courseId,rollNumber) {
    try {
          const response = await axios.get(`http://localhost:8080/api/getAttendancePercentageByRollAndClass/${courseId}/${rollNumber}`);
          console.log(response);
          return response;
        } catch (error) {
          console.error('Error uploading image:', error);
          throw error;
        }
}

export async function course_by_date(courseId,date) {
    try {
          const response = await axios.get(`http://localhost:8080/api/getAttendanceListByDateAndCourse/${courseId}/${date}`);
          console.log(response);
          return response;
        } catch (error) {
          console.error('Error uploading image:', error);
          throw error;
        }
}