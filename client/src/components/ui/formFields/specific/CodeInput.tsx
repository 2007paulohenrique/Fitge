import React, { forwardRef } from "react"
import styles from './CodeInput.module.css'

type CodeInputProps = {
    codeElementKey: string 
    codeElementValue: string
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    onClick: () => void
    onKeyDown: (e: React.KeyboardEvent<HTMLInputElement>) => void
}

const CodeInput = forwardRef<HTMLInputElement, CodeInputProps>(({
    codeElementKey,
    codeElementValue,
    onChange,
    onClick,
    onKeyDown
}, ref) => {
    return (
        <input
            ref={ref}
            type="text"
            name={codeElementKey}
            id={codeElementKey}
            value={codeElementValue}
            onChange={(e) => {
                e.target.value = e.target.value.replace(/\s/g, '').toUpperCase()
                onChange(e)
            }}
            onClick={onClick}
            onKeyDown={onKeyDown}
            maxLength={1}
            className={styles.code_input}
        />
    )
})

export default CodeInput