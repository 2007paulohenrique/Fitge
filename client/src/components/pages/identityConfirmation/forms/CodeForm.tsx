import BaseForm from '../../../base/BaseForm'
import FlexContainer from '../../../containers/FlexContainer'
import { setCodeObjectInIdentityConfirmationForm } from '../../../../utils/reactState.ts/setData'
import type { Code } from '../types'
import CodeInput from '../../../ui/formFields/specific/CodeInput'
import { useTranslation } from 'react-i18next'
import PrimaryButton from '../../../ui/buttons/PrimaryButton'
import { useCallback, useMemo, useRef } from 'react'

type CodeFormProps = {
    onCodeSubmit: () => void
    codeError: boolean
    setCodeError: React.Dispatch<React.SetStateAction<boolean>>
    code: Code
    setCode: React.Dispatch<React.SetStateAction<Code>>
}

const CodeForm: React.FC<CodeFormProps> = ({
    onCodeSubmit,
    codeError,
    setCodeError,
    code,
    setCode
}) => {
    const { t } = useTranslation()

    const inputRefs = useRef<HTMLInputElement[]>([])

    const codeEntries = useMemo<[string, string][]>(() => {
        return Object.entries(code)
    }, [
        code
    ])

    const focusNext = useCallback((index: number) => {
        if (index < codeEntries.length - 1) {
            inputRefs.current[index + 1]?.focus()
        } else {
            inputRefs.current[index]?.blur()
        }
    }, [
        codeEntries,
        inputRefs.current
    ])

    const focusPrev = useCallback((index: number) => {
        if (index > 0) inputRefs.current[index - 1]?.focus()
    }, [
        inputRefs.current
    ])

    const onClick = useCallback(() => {
        const values = Object.values(code)
        const firstEmptyIndex = values.findIndex((value) => value === '')

        if (firstEmptyIndex === -1) {
            inputRefs.current[values.length - 1]?.focus()
        } else {
            inputRefs.current[firstEmptyIndex]?.focus()
        }
    }, [
        code,
        inputRefs.current
    ])

    const onKeyDown = useCallback((
        e: React.KeyboardEvent<HTMLInputElement>,
        key: keyof Code,
        index: number
    ) => {
        if (e.key === 'Backspace' ) {
            if (!code[key]) {
                if (index <= 0) return;
    
                const prevKey = codeEntries[index - 1][0];
    
                setCode(prevCode => ({ ...prevCode, [prevKey]: '' }))
    
                focusPrev(index);
            } else {
                setCode(prevCode => ({ ...prevCode, [key]: '' }))
            }
        }
    }, [
        code,
        codeEntries,
        setCode,
        focusPrev
    ])

    return (
        <BaseForm
            onSubmit={onCodeSubmit}
            hasError={codeError}
        >
            <FlexContainer
                flexDirection='row'
                gap='0.5em'
            >
                {codeEntries.map(([key, value], index) => (
                    <CodeInput
                        key={key}
                        ref={el => {
                            if (el) inputRefs.current[index] = el
                        }}
                        codeElementKey={key}
                        codeElementValue={value}
                        onChange={(e) => setCodeObjectInIdentityConfirmationForm(
                            e, 
                            code,
                            setCode,
                            setCodeError,
                            () => focusNext(index),
                        )}
                        onClick={onClick}
                        onKeyDown={(e) => onKeyDown(e, key as keyof Code, index)}
                    />
                ))}
            </FlexContainer>

            <PrimaryButton
                text={t('general.confirm')}
            />
        </BaseForm>
    )
}

export default CodeForm