import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import './i18n'
import App from './App.tsx'
import { Provider } from 'react-redux'
import { store } from './app/store/store.ts'
import { SystemMessageProvider } from './app/contexts/systemMessageContext/SystemMessageContext.tsx'
import { BrowserRouter } from 'react-router-dom'
import { ConfirmIdentityCallbackProvider } from './app/contexts/confirmIdentityCallbackContext/ConfirmIdentityCallbackContext.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider 
      store={store}
    >
      <SystemMessageProvider>
        <BrowserRouter>
          <ConfirmIdentityCallbackProvider>
            <App />
          </ConfirmIdentityCallbackProvider>
        </BrowserRouter>
      </SystemMessageProvider>
    </Provider>
  </StrictMode>
)
