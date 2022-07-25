import "./App.css";
import { Routes, Route } from 'react-router-dom';

// import Main from './pages/Main/Main';
import { 
  Main,
  ManagerMain,
  ConsultantMain,
  ManagerMyPage,
  ManagerRegister,
  ManageRegister,
} from './pages/index'

function App() {
  return(
  <>
    <Routes>
      <Route path="/" element={<Main /> } />
      <Route path="/manager/main" element={<ManagerMain /> } />
      <Route path="/consultant/main" element={<ConsultantMain />} />
      <Route path="/manager/mypage" element={<ManagerMyPage />} />
      <Route path="/manager/register" element={<ManageRegister />} />
    </Routes>
  </>
  )
}

export default App;
