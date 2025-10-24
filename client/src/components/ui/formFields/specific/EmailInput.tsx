import { useTranslation } from 'react-i18next'
import BaseInput from '../common/BaseInput'
import EmailIcon from '../../../../assets/icons/email.svg?react'
import { emailMaxLength } from '../../../../utils/consts/dataLimits'

type EmailInputProps = {
    name?: string
    value: string
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    hasError?: boolean
}

const EmailInput: React.FC<EmailInputProps> = ({
    name = 'email',
    value,
    onChange,
    hasError
}) => {
    const { t } = useTranslation()

    return (
        <BaseInput
            name={name}
            placeholder={t('forms.fields.email.placeholder')}
            value={value}
            icon={EmailIcon}
            onChange={onChange}
            label={t('forms.fields.email.label')}
            alertMessage={t('forms.fields.email.alert')}
            hasError={hasError}
            maxLength={emailMaxLength}
            isRequired
        />
    )
}

export default EmailInput