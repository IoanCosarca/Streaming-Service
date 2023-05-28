import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VideoModel } from '../models/video.model';
import { VideoUploadModel } from '../models/video-upload.model';

@Injectable({
	providedIn: 'root'
})
export class VideoService {

	constructor(private httpClient: HttpClient) { }

	public getAllVideos() {
		return this.httpClient.get<VideoModel>(`/api/getVideos`);
	}

	public getVideoByID(id: number) {
		return this.httpClient.get<VideoModel>(`/api/getVideoByID/${id}`);
	}

	public getVideoByName(name: string) {
		return this.httpClient.get<VideoModel>(`/api/getVideoByName/${name}`);
	}

	public getVideoByChannel(channel: string) {
		return this.httpClient.get<VideoModel>(`/api/getVideosByChannel/${channel}`);
	}

	public getVideoByGenre(genre: string) {
		return this.httpClient.get<VideoModel>(`/api/getVideosByGenre/${genre}`);
	}

	public getVideoByHour(startHour: number) {
		return this.httpClient.get<VideoModel>(`/api/getVideosByHour/${startHour}`);
	}

	public upload(videoData: VideoUploadModel) {
		return this.httpClient.post<VideoUploadModel>(`/api/addVideo`, videoData);
	}

	public update(videoData: VideoModel) {
		return this.httpClient.put<VideoModel>(`/api/updateVideo`, videoData);
	}

	public delete(id: number) {
		return this.httpClient.delete<VideoModel>(`/api/deleteVideoByID/${id}`);
	}

}
