import { VideoModel } from "./video.model"

export interface UserModel {
    id: number,
    userID: number,
    type: string,
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    age: number,
    country: string,
}

export interface AdminModel {
    id: number,
    userID: number,
    type: string,
    firstName: string,
    lastName: string,
    email: string,
    password: string
}
