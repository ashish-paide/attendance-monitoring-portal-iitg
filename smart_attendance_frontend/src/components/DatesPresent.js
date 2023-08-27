import React from 'react';
import './DatesPresent.css'
import StudentTable from './StudentTable';

const DatesPresent = ({ student_Data,columns }) => {
  return (
    <div className='studentvsDate'>
      <h1>Student attendance vs Date</h1>
      <StudentTable row={student_Data} col={columns}/>
    </div>
  );
};

export default DatesPresent
;