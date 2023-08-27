import React from 'react'
import { useState } from 'react';
import './analysis.css';
import {courseId_by_date ,courseId_by_student,student_by_course_by_date,student_by_course ,student_by_course_average,course_by_date} from '../services/analysisService.js'
import BarGraph from './Bargraph';
import PieChart from './PieChart';
import DatesPresent from './DatesPresent';
function Analysis() {
  const [classId, setClassId] = useState("");
  const [date, setDate] = useState("");
  const [rollNumber, setRollNumber] = useState("");
  const [courseAttendance,setCourseAttendance]=useState([]);
  const [studentPresent,setStudentPresent]=useState("");
  const [studentAverage,setStudentAverage]=useState([]);
  const [datevsStatus,setDatevsStatus]=useState([]);
  const [coursevsDate,setCoursevsDate]=useState([]);
  const [courseAverage,setCourseAverage]=useState([]);
  const COLUMNS1 = [
    {
      Header: 'Date',
      accessor: 'date',
    },
    {
      Header: 'Status',
      accessor: 'status',
    },
  ];

  const COLUMNS2=[
    {
      Header:'Student Id',
      accessor:'studentId',
    },
    {
      Header:'Student Name',
      accessor:'studentName',
    },
    {
      Header:'Status',
      accessor:'status',
    }
  ]

  const handleClassIdChange = (e) => {
    console.log("value", e);
    setClassId(e.target.value);
  };

  const handleDateChange = (e) => {
    setDate(e.target.value);
  };

  const handleStudentIdChange=(e)=>{
    setRollNumber(e.target.value);
  }

  const handleSubmit=async(e)=>{
      e.preventDefault();
      if(classId!=="" && date==="" && rollNumber===""){
        try {
          const response1 = await courseId_by_date(classId);
          const response2= await courseId_by_student(classId);
          const Date_and_student=[response1.data,response2.data];
          console.log(Date_and_student)
          setCourseAttendance(Date_and_student);
        } catch (error) {
          console.error("Error uploading image:", error);
        }
        setCoursevsDate([]);
        setStudentPresent("");
        setStudentAverage([]);
      }else if(classId!=="" && date!=="" && rollNumber!==""){
        try {
          const response = await student_by_course_by_date(classId,rollNumber,date);
          console.log(response);
          const value=(response.data[0])?"present":"absent";
          setStudentPresent(value);
        } catch (error) {
          console.error("Error uploading image:", error);
        }
        setCourseAttendance([]);
        setCoursevsDate([]);
        setStudentAverage([]);
      }else if(classId!=="" && date==="" && rollNumber!==""){
        try {
          const response1 = await student_by_course(classId,rollNumber);
          const response2=await student_by_course_average(classId,rollNumber);
          const present=response2.data;
          const absent=100-present;
          const date_attend=response1.data.map((element)=>{
            return {
              "date" : element[0],
              "status" :element[1]?"Present":"Absent"
            }
          });
          console.log(date_attend);
          setStudentAverage([present,absent]);
          setDatevsStatus(date_attend);
          
        } catch (error) {
          console.error("Error uploading image:", error);
        }
        setCourseAttendance([]);
        setCoursevsDate([]);
        setStudentPresent("");
      }else if(classId!=="" && date!=="" && rollNumber===""){
        try {
          const response1 = await course_by_date(classId,date);
          let present=0;
          let absent=0;
          const date_attend=response1.data.map((element)=>{
            if(element[2])present++;
            else absent++;
            return {
              "studentId" : element[0],
              "studentName" :element[1],
              "status":element[2]?"Present":"Absent"
            }
          });
          const presentAverage=(present*100)/(present+absent);
          const absentAverage=(absent*100)/(present+absent);
          setCourseAverage([presentAverage,absentAverage]);
          setCoursevsDate(date_attend);
          
        } catch (error) {
          console.error("Error uploading image:", error);
        }
        setStudentAverage([]);
        setCourseAttendance([]);
        setStudentPresent("");
      }
      
  }

  return (
      <div className="container" >
        <div className="analysis-box">
              <h5>Class Id :</h5>
              <input
                type="text"
                placeholder="Class ID"
                value={classId}
                onChange={handleClassIdChange}
              />
              <h5>Date :</h5>
              <input type="date" value={date} onChange={handleDateChange} />
              <h5>Student ID :</h5>
              <input type="text" placeholder="Student ID" value={rollNumber} onChange={handleStudentIdChange} />
              <button type="submit" class="submit_button" onClick={handleSubmit}>Submit</button>
        </div>
          {
            courseAttendance.length>0?(
              <BarGraph data={courseAttendance}/>
            ):<></>
          }
          {
            studentPresent!=""?(
              <h1 className='studentPresent'>{`Student is ${studentPresent}`}</h1>
            ):<></>
          }
          {
            studentAverage.length>0?(
              <div className='course_student'>
                <PieChart attendancePercentage={studentAverage[0]} absencePercentage={studentAverage[1]}/>
                <DatesPresent student_Data={datevsStatus} columns={COLUMNS1}/>
              </div>
            ):<></>
          }
          {
            coursevsDate.length>0?(
              <div className='course_student'>
                <PieChart attendancePercentage={courseAverage[0]} absencePercentage={courseAverage[1]}/>
                <DatesPresent student_Data={coursevsDate} columns={COLUMNS2}/>
              </div>
            ):<></>
          }
      </div>
  )
}

export default Analysis