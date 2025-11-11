export type MediaRequestType = {
    file: File
    blobUrl: string
}

export type UserRequestType = Partial<{
    name: string
    nickname: string
    email: string
    password: string
    isDarkTheme: boolean
    isComplainterAnonymous: boolean
    isRaterAnonymous: boolean
    emailMessagesNotificationPermission: boolean
    emailNewsNotificationPermission: boolean
    emailOthersNotificationPermission: boolean
    appMessagesNotificationPermission: boolean
    appNewsNotificationPermission: boolean
    appOthersNotificationPermission: boolean
    isMale: boolean
    birthDate: Date
    media: MediaRequestType
    countryId: number
    languageId: number
}>

export type ClientRequestType = Partial<{
    heightCm: number
    weightKg: number
    weekAvailableDays: number
    dayAvailableHours: number
    dailyCaloricIntakeKcal: number
    dailyWaterIntakeLiters: number
    dailySleepHours: number
    cardiacConditions: string
    mentalConditions: string
    physicalLimitations: string
    trainingExperienceId: number
    trainingTargetId: number
    user: UserRequestType
}>

export type TrainerLocationRequestType = {
    name: string
    address: string
}

export type TrainerSocialNetworkRequestType = {
    profile: string
    socialNetworkId: number
}

export type TrainerSkillRequestType = {
    isMain: boolean
    trainingTargetId: number
}

export type TrainerRequestType = Partial<{
    description: string
    daysForClientContractAfterPermission: number
    maxActiveContracts: number
    isRequestsBlockedInUnavailability: boolean
    emailClientChangesNotificationPermission: boolean
    appClientChangesNotificationPermission: boolean
    website: string
    user: UserRequestType
    locations: Array<TrainerLocationRequestType>
    socialNetworks: Array<TrainerSocialNetworkRequestType>
    trainingTargets: Array<TrainerSkillRequestType>
}>

export type CreatingUserType = ClientRequestType | TrainerRequestType | null
