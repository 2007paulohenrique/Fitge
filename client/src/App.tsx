import { Route, Routes } from 'react-router-dom'
import LoginPage from './components/pages/login/LoginPage'
import { loginRoute, signUpRoute } from './utils/consts/routes'
import SignUpPage from './components/pages/signUp/SignUpPage'

function App() {
  return (
    <Routes>
      <Route 
        path={loginRoute}
        element={<LoginPage />} 
      />
      
      <Route 
        path={signUpRoute}
        element={<SignUpPage />} 
      />
    </Routes>
  )
}

export default App
