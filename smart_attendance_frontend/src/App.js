import './App.css';
import { BrowserRouter as Router, Link, Route, Routes, Switch} from 'react-router-dom';
import ImageUploadComponent from './components/ImageUploadComponent';
import Header from './components/Header';
import Analysis from './components/Analysis';
import Home from './components/Home';
import StudentTable from './components/StudentTable';
import { useState } from 'react';
import BarGraph from './components/Bargraph';
function App() {
  const courseData = [
    { date: '2023-08-01', studentCount: 20 },
    { date: '2023-08-02', studentCount: 25 },
    { date: '2023-08-03', studentCount: 18 },
    { date: '2023-08-04', studentCount: 22 },
    { date: '2023-08-05', studentCount: 30 },
    { date: '2023-08-06', studentCount: 15 },
    { date: '2023-08-07', studentCount: 28 },
    { date: '2023-08-08', studentCount: 23 },
    { date: '2023-08-09', studentCount: 19 },
    { date: '2023-08-10', studentCount: 24 },
  ];
  return (
      <Router>
      <div className='app'>
      <Header/>
        <Routes>
          <Route path='/Attendance_analysis' element={(
            <div>
              <Analysis/>
            </div>
          )}/>
          <Route path='/bar' element={(
            <div>
              <BarGraph data={courseData}/>
            </div>
          )}/>
          <Route path='/image_upload'element={(
            <div>
              <ImageUploadComponent />
            </div>
          )}/>
          <Route path='/' element={(
            <div>
              <Home/>
            </div>
          )}/>
        </Routes> 
        <footer className="footer">
          <span className='footer-text'>&copy; 2023 My App. All rights reserved.</span>
        </footer>
      </div>
      </Router>
  );
}

export default App;


