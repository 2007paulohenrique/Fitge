import { createContext, useRef, useCallback, type ReactNode } from 'react'
import toast, { Toaster, type ToastOptions } from 'react-hot-toast'
import { cssVarColors } from '../../../utils/consts/cssVariables'
import ConfirmMessage from '../../../components/ui/messages/ConfirmMessage'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'

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
            style: {
                backgroundColor: formatVariableName(cssVarColors.bgColor),
                color: formatVariableName(cssVarColors.textColor),
                borderRadius: '10px',
                border: `2px solid ${formatVariableName(cssVarColors.textColor)}`,
                padding: '0.5em 0.5em 0.5em 1em',
                letterSpacing: '0.5px',
                maxWidth: '30em'
            },
            duration: 5000,
            position: 'top-right',
            id: toastId
        }

        switch (type) {
            case 'success':
                toast.success(message, {
                    ...commonProps,
                    iconTheme: {
                        primary: formatVariableName(cssVarColors.successColor),
                        secondary: formatVariableName(cssVarColors.bgColor)
                    }
                })

                break

            case 'error':
                toast.error(message, {
                    ...commonProps,
                    iconTheme: {
                        primary: formatVariableName(cssVarColors.alertColor),
                        secondary: formatVariableName(cssVarColors.bgColor)
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
