import { Trans } from 'react-i18next'
import styles from './AcceptCheckBox.module.css'
import type React from 'react'
import FlexContainer from '../../../containers/FlexContainer'
import Description from '../../text/Description'
import Link from '../../text/Link'
import { cssVarTextSizes } from '../../../../utils/consts/cssVariables'

type Policy = {
    name: string
    link: string
}

type AcceptCheckBoxProps = {
    isAccepted: boolean
    setIsAccepted: React.Dispatch<React.SetStateAction<boolean>>
    description: React.ReactNode
    policies?: Policy[]
}

const AcceptCheckBox: React.FC<AcceptCheckBoxProps> = ({ 
    isAccepted, 
    setIsAccepted, 
    description,
    policies
}) => {
    return (
        <FlexContainer
            flexDirection='row'
            className={styles.accept_check_box}
        >
            <span
                className={isAccepted ? styles.accepted : undefined}
                onClick={() => setIsAccepted(prev => !prev)}
            ></span>

            <FlexContainer
                gap='0.1em'
                alignItems='start'
            >
                <Description
                    varSize={cssVarTextSizes.smallTextSize}
                    textAlign='left'
                    text={description} 
                />

                {policies && (
                    <FlexContainer>
                        {policies.map((policy, index) => (
                            <Description
                                varSize={cssVarTextSizes.smallTextSize}
                                textAlign='left'
                                text={
                                    <Trans
                                        i18nKey='general.checkPolicyLink'
                                        components={{ 
                                            link: <Link 
                                                destiny={policy.link} 
                                                text={policy.name} 
                                            /> 
                                        }}
                                    />
                                }
                                key={index}
                            />
                        ))}
                    </FlexContainer>
                )}
            </FlexContainer>
        </FlexContainer>
    )
}

export default AcceptCheckBox