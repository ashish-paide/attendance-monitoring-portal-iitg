import React from 'react'
import '../App.css'
import { useNavigate } from 'react-router-dom/dist'

function Home() {
    const navigate=useNavigate();
    const handleClick=(e)=>{
        navigate('/image_upload');
    }
  return (
    <div className='Home'>
        <div className='outer-container'>
        <div className='college'>
        <img
            className='Home-image'
            src="https://event.iitg.ac.in/icann2019/Proceedings_LaTeX/2019/IITG_logo.png"
        />
        <h1>
            INDIAN INSTITUTE OF TECHNOLOGY GUWAHATI
        </h1>
        </div>
        <div className='button'>
            <button type="submit" class="Home_upload_button" onClick={handleClick} >Upload Image</button>
        </div>
        </div>
    </div>
  )
}

export default Home
