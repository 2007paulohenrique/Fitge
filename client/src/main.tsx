import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import './i18n'
import App from './App.tsx'
import { Provider } from 'react-redux'
import { store } from './app/store/store.ts'
import { SystemMessageProvider } from './app/contexts/systemMessageContext/SystemMessageContext.tsx'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider 
      store={store}
    >
      <SystemMessageProvider>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </SystemMessageProvider>
    </Provider>
  </StrictMode>
)
