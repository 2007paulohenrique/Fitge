export interface UserConfigState {
    isDarkTheme: boolean
    isComplainterAnonymous: boolean
    isRaterAnonymous: boolean
    language: 'pt-BR' | 'en-US' | 'es-ES'
}

export interface UserState {
    id: number | undefined
    name: string
    nickname: string
    sex: 'male' | 'female' | 'none'
    userType: 'client' | 'trainer' | undefined
    photoUrl: string | undefined
    config: UserConfigState
}

const initialUserState: UserState = {
    id: undefined,
    name: '',
    nickname: '',
    sex: 'none',
    userType: undefined,
    photoUrl: undefined,
    config: {
        isDarkTheme: false,
        isComplainterAnonymous: true,
        isRaterAnonymous: false,
        language: 'pt-BR'
    }
}

export default initialUserState