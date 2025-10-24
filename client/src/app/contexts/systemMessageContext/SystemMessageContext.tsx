import { createContext, useRef, useCallback, type ReactNode } from 'react'
import toast, { Toaster, type ToastOptions } from 'react-hot-toast'
import styles from './SystemMessage.module.css'
import { cssVarColors } from '../../../utils/consts/cssVariables'
import ConfirmMessage from '../../../components/layout/messages/ConfirmMessage'

type NotifyType = 'success' | 'error' | 'loading'

export type SystemMessageContextType = {
    notify: (message: string, type: NotifyType, id?: string) => void
    confirm: (message: string, onConfirm: () => void) => void
}

export const SystemMessageContext = createContext<SystemMessageContextType | null>(null)

type SystemMessageProviderProps = {
    children: ReactNode
}

export function SystemMessageProvider({ 
    children 
}: SystemMessageProviderProps) {
    const confirmToastId = useRef<string | null>(null)
    const dismissTimeout = useRef<ReturnType<typeof setTimeout> | null>(null)

    const notify = useCallback((message: string, type: NotifyType, id?: string) => {
        if (dismissTimeout.current) clearTimeout(dismissTimeout.current)

        const toastId = id ?? String(Math.random())

        const commonProps: ToastOptions = {
            className: styles.message_toast,
            duration: 5000,
            position: 'top-right',
            id: toastId
        }

        switch (type) {
            case 'success':
                toast.success(message, {
                    ...commonProps,
                    iconTheme: {
                        primary: cssVarColors.successColor,
                        secondary: 'var(--bg-color)'
                    }
                })

                break

            case 'error':
                toast.error(message, {
                    ...commonProps,
                    iconTheme: {
                        primary: cssVarColors.alertColor,
                        secondary: 'var(--bg-color)'
                    }
                })

                break

            case 'loading':
                toast.loading(`${message}...`, commonProps)

                break
            }

        dismissTimeout.current = setTimeout(() => toast.dismiss(toastId), 5000)
    }, [])

    const confirm = useCallback((message: string, onConfirm: () => void) => {
        if (confirmToastId.current) return

        const finishConfirm = (callback?: () => void) => {
            if (confirmToastId.current) {
                toast.dismiss(confirmToastId.current)
                confirmToastId.current = null
            }
            
            callback?.()
        }

        confirmToastId.current = toast.custom(() => (
            <ConfirmMessage
                message={message}
                onConfirm={() => finishConfirm(onConfirm)}
                onCancel={() => finishConfirm()}
            />
        ),
        { 
            duration: Infinity 
        })
    }, [])

    return (
        <SystemMessageContext.Provider 
            value={{ notify, confirm }}
        >
            <Toaster />

            {children}
        </SystemMessageContext.Provider>
    )
}
