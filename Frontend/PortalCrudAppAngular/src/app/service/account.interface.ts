export interface UsernameAndPassword {
    username: string,
    password: string
}

export interface register {
    username: string,
    password: string,
    email: string
}

export interface RecoveryMessage {

    title: string,
    baseUrl: string,
    message: string,
    userEmail: string
}
