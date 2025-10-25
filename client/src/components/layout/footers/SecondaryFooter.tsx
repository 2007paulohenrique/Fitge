import { useNavigate } from "react-router-dom"
import { aboutRoute, contentPolicyRoute, cookiesPolicyRoute, helpRoute, privacyAndSecurityPolicyRoute, termsAndConditionsRoute } from "../../../utils/consts/routes"
import FlexContainer from "../../containers/FlexContainer"
import FlexWrapContainer from "../../containers/FlexWrapContainer"
import TextButton from "../../ui/buttons/TextButton"
import { useTranslation } from "react-i18next"
import type React from "react"
import { formatVariableName } from "../../../utils/formatters/style/cssVariables"
import { cssVarColors } from "../../../utils/consts/cssVariables"
import useWindowSize from "../../../hooks/useWindowSize"

const SecondaryFooter = () => {
    const { t } = useTranslation()

    const navigate = useNavigate()

    const { isMobile } = useWindowSize()

    type NavigateListElementProps = {
        route: string
        text: string
    }

    const NavigateListElement: React.FC<NavigateListElementProps> = ({
        route,
        text
    }) => {
        return (
            <li>
                <TextButton
                    onClick={() => navigate(route)}
                    text={text}
                    varColor={cssVarColors.grayColor}
                />
            </li>
        )
    }

    return (
        <FlexContainer
            tag='footer'
        >
            <FlexWrapContainer
                tag="ul"
                padding={isMobile ? '0 1em' : '0 5em'}
                style={{
                    color: formatVariableName(cssVarColors.grayColor),
                    textAlign: 'center'
                }}
            >
                <NavigateListElement
                    route={aboutRoute}
                    text={t('pages.about')}
                />
                
                <NavigateListElement
                    route={helpRoute}
                    text={t('pages.help')}
                />
                
                <NavigateListElement
                    route={termsAndConditionsRoute}
                    text={t('pages.termsAndConditions')}
                />
                
                <NavigateListElement
                    route={privacyAndSecurityPolicyRoute}
                    text={t('pages.privacyAndSecurityPolicy')}
                />
                
                <NavigateListElement
                    route={contentPolicyRoute}
                    text={t('pages.contentPolicy')}
                />
                
                <NavigateListElement
                    route={cookiesPolicyRoute}
                    text={t('pages.cookiesPolicy')}
                />

                <li>
                    <TextButton
                        onClick={() => undefined}
                        text={t('general.download')}
                        varColor={cssVarColors.grayColor}
                    />
                </li>
                
                <li>
                    {t('identity.copyright')}
                </li>
            </FlexWrapContainer>
        </FlexContainer>
    )
}

export default SecondaryFooter