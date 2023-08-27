import React from 'react'
import HomeIcon from '@mui/icons-material/Home';
import { Link } from 'react-router-dom';
import "../App.css"

function Header() {
  return (
    <div className='header'>
      <img className="logo" src="https://event.iitg.ac.in/icann2019/Proceedings_LaTeX/2019/IITG_logo.png"></img>
      <Link style={{textDecoration: 'none'}} to='/'>
        <div className='Home_icon'>
          <HomeIcon className='HomeIcon'></HomeIcon>
          <span className='Home-text'>Home</span>
        </div>
      </Link>
      <div>
        <span className='slash'>|</span>
      </div>
      <div className='navigation'>
        <Link style={{textDecoration: 'none'}} to='/image_upload'>
          <div className='upload_image'>
            <span className='image_text'>Image Upload</span>
          </div>
        </Link>
      </div>
      <div>
        <span className='slash'>|</span>
      </div>
      <div className='navigation'>
        <Link style={{textDecoration: 'none'}} to='/Attendance_analysis'>
          <div className='Attendance_analysis'>
            <span className='analysis_text'>Attendance analysis</span>
          </div>
        </Link>
      </div>
    </div>
  )
}

export default Header
