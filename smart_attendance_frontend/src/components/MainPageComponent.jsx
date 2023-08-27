// import React from 'react';
// import { BrowserRouter as Router, Route, useHistory } from 'react-router-dom';
// import ImageUploadComponent from './ImageUploadComponent';

// const MainPageComponent = ({ rollNumbers }) => {
//   const history = useHistory();
//   const handleButtonPress = () => {
//     history.push('/another');
//   }
//   return (
//     <Router>
//     <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
//       <nav style={{ backgroundColor: '#333', color: '#fff', padding: '10px' }}>
//         <ul style={{ listStyle: 'none', display: 'flex', justifyContent: 'center' }}>
//           <li style={{ margin: '0 10px' }}>Home</li>
//           <li style={{ margin: '0 10px' }}>Attendance</li>
//           <li style={{ margin: '0 10px' }}>About</li>
//         </ul>
//       </nav>
//       <main style={{ flex: 1 }}>
//         <h1>Hello</h1>
//         <p>bye</p>
//         <Route path="/another" component={ImageUploadComponent} />
//       </main>
//       <button onClick={handleButtonPress}>
//           Go to Another Component
//       </button>
//       <footer style={{ backgroundColor: '#333', color: '#fff', padding: '10px', textAlign: 'center'  }}>
//         <p>&copy; 2023 My App. All rights reserved.</p>
//       </footer>
//     </div>
//     <
//     </Router>
//   );
// };

// export default MainPageComponent;




import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes,Route as ReactRoute } from 'react-router-dom';
import ImageUploadComponent from './ImageUploadComponent';

const MainPageComponent = ({ rollNumbers }) => {
  return (
<Router>
      <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
        <nav style={{ backgroundColor: '#333', color: '#fff', padding: '10px' }}>
          <ul style={{ listStyle: 'none', display: 'flex', justifyContent: 'center' }}>
            <li style={{ margin: '0 10px' }}>Home</li>
            <li style={{ margin: '0 10px' }}> <Link to="/image-upload">Attendance</Link></li>
            <li style={{ margin: '0 10px' }}>About</li>
          </ul>
        </nav>
        <main style={{ flex: 1 }}>
          <h1>Hello</h1>
          <p>bye</p>
        </main>
        
        <Routes>
          <Route path="/" element={<MainPageComponent />} />
          <Route path="/image-upload" element={<ImageUploadComponent />} />
        </Routes>
      </div>
      </Router>
  );
};

export default MainPageComponent;
