import { useTranslation } from 'react-i18next'
import BaseInput from '../common/BaseInput'
import NameIcon from '../../../../assets/icons/name.svg?react'
import { NAME_MAX_LENGTH } from '../../../../utils/constraints/userConstraints'

type UserNameInputProps = {
    name?: string
    value: string
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    hasError: boolean
}

const UserNameInput: React.FC<UserNameInputProps> = ({
    name = 'name',
    value,
    onChange,
    hasError
}) => {
    const { t } = useTranslation()

    return (
        <BaseInput
            name={name}
            placeholder={t('forms.fields.name.placeholder')}
            value={value}
            icon={NameIcon}
            onChange={onChange}
            label={t('forms.fields.name.label')}
            alertMessage={t('forms.fields.name.alert')}
            hasError={hasError}
            maxLength={NAME_MAX_LENGTH}
            isRequired
        />
    )
}

export default UserNameInput