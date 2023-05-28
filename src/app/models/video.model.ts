export interface VideoModel {
    id: number,
    name: string,
    channel: string,
    genre: string,
    ageRestriction: boolean,
    link: string,
    startHour: number,
    endHour: number,
    status: string
}