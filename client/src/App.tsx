import { Route, Routes } from 'react-router-dom'
import LoginPage from './components/pages/login/LoginPage'
import { loginRote, signUpRote } from './utils/consts/rotes'
import SignUpPage from './components/pages/signUp/SignUpPage'

function App() {
  return (
    <Routes>
      <Route 
        path={loginRote}
        element={<LoginPage />} 
      />
      
      <Route 
        path={signUpRote}
        element={<SignUpPage />} 
      />
    </Routes>
  )
}

export default App
