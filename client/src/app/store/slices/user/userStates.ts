export interface UserMediaState {
    id: number
    url: string
}

export interface UserCountryState {
    id: number
    code: string
}

export interface UserLanguageState {
    id: number
    code: string
}

export interface UserState {
    id: number
    name: string
    nickname: string
    email: string
    isDarkTheme: boolean
    isComplainterAnonymous: boolean
    isRaterAnonymous: boolean
    isMale: boolean
    birthDate: Date
    fitgePlanLevel: number
    media?: UserMediaState
    country?: UserCountryState
    language: UserLanguageState
}

export interface ClientTrainingExperienceState {
    id: number
    code: string
    minExperienceYears: number
    maxExperienceYears: number
}

export interface ClientTrainingTargetState {
    id: number
    code: string
    isSport: boolean
}

export interface ClientState {
    roleId: number
    heightCm?: number
    weightKg?: number
    weekAvailableDays?: number
    dayAvailableHours?: number
    dailyCaloricIntakeKcal?: number
    dailyWaterIntakeLiters?: number
    dailySleepHours?: number
    cardiacConditions?: string
    mentalConditions?: string
    physicalLimitations?: string
    trainingExperience?: ClientTrainingExperienceState
    trainingTarget?: ClientTrainingTargetState
    user: UserState
}

export interface TrainerLocationState {
    id: number
    name: string
    address: string
}

export interface SocialNetworkState {
    id: number
    name: string
    webDomain: string
}

export interface TrainerSocialNetworkState {
    id: number
    profile: string
    socialNetwork: SocialNetworkState
}

export interface TrainerState {
    roleId: number
    description?: string
    rating: number
    ratingsNumber: number
    complaintsNumber: number
    contractsNumber: number
    paymentPlansDurationPriceRatio: number
    paymentPlansAveragePrice: number
    daysForClientContractAfterPermission: number
    maxActiveContracts: number
    isAvailable: boolean
    isRequestsBlockedInUnavailability: boolean
    website?: string
    user: UserState
    locations?: Array<TrainerLocationState>
    socialNetworks?: Array<TrainerSocialNetworkState>
}

export type UserSliceState = ClientState | TrainerState | null
