import styles from './Link.module.css'

type LinkProps = {
    destiny: string
    text: string
}

const Link: React.FC<LinkProps> = ({
    destiny,
    text
}) => {
    return (
        <a 
            href={destiny} 
            target='_blank' 
            rel='noopener noreferrer' 
            className={styles.link}
        >
            {text}
        </a>
    )
}

export default Link