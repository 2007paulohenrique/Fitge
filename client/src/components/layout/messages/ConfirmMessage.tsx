import React from 'react'
import FlexContainer from '../../../components/containers/FlexContainer'
import TextButton from '../buttons/TextButton'
import PrimaryButton from '../buttons/PrimaryButton'
import { useTranslation } from 'react-i18next'
import BaseCard from '../cards/BaseCard'
import Description from '../text/Description'
import IconIllustration from '../illustrations/IconIllustration'
import QuestionIcon from '../../../assets/icons/question.svg?react'
import styles from './ConfirmMessage.module.css'

type ConfirmMessageProps = {
    message: string
    onConfirm: () => void
    onCancel: () => void
}

const ConfirmMessage: React.FC<ConfirmMessageProps> = ({
    message,
    onConfirm,
    onCancel
}) => {
    const { t } = useTranslation()

    return (
        <BaseCard
            width='50dvw'
            className={styles.confirm_message}
        >
            <IconIllustration
                icon={QuestionIcon}
                size='small'
            />

            <FlexContainer
                gap='2em'
            >
                <Description
                    text={message}
                />

                <FlexContainer
                    flexDirection='row'
                    gap='5em'
                >
                    <PrimaryButton
                        text={t('general.confirm')}
                        isSubmit={false}
                        onClick={onConfirm}
                    
                    />

                    <TextButton
                        text={t('general.cancel')}
                        onClick={onCancel}
                    />
                </FlexContainer>

            </FlexContainer>
        </BaseCard>
    )
}

export default ConfirmMessage
