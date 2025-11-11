import { useTranslation } from 'react-i18next'
import BaseInput from '../common/BaseInput'
import NicknameIcon from '../../../../assets/icons/nickname.svg?react'
import { NICKNAME_MAX_LENGTH } from '../../../../utils/constraints/userConstraints'

type NicknameInputProps = {
    name?: string
    value: string
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    hasError: boolean
}

const NicknameInput: React.FC<NicknameInputProps> = ({
    name = 'nickname',
    value,
    onChange,
    hasError
}) => {
    const { t } = useTranslation()

    return (
        <BaseInput
            name={name}
            placeholder={t('forms.fields.nickname.placeholder')}
            value={value}
            icon={NicknameIcon}
            onChange={onChange}
            label={t('forms.fields.nickname.label')}
            alertMessage={t('forms.fields.nickname.alert')}
            hasError={hasError}
            maxLength={NICKNAME_MAX_LENGTH}
            isRequired
        />
    )
}

export default NicknameInput