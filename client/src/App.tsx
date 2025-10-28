import { Route, Routes } from 'react-router-dom'
import LoginPage from './components/pages/login/LoginPage'
import { LOGIN_ROUTE, SIGN_UP_ROUTE } from './utils/consts/routes'
import SignUpPage from './components/pages/signUp/SignUpPage'

function App() {
  return (
    <Routes>
      <Route 
        path={LOGIN_ROUTE}
        element={<LoginPage />} 
      />
      
      <Route 
        path={SIGN_UP_ROUTE}
        element={<SignUpPage />} 
      />
    </Routes>
  )
}

export default App
