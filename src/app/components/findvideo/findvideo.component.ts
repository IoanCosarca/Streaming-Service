import { Component, OnInit } from '@angular/core';
import { VideoModel } from 'src/app/models/video.model';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-findvideo',
	templateUrl: './findvideo.component.html',
	styleUrls: ['./findvideo.component.css']
})
export class FindvideoComponent implements OnInit {
	videoData: VideoModel = {
		id: 0,
    	name: '',
    	channel: '',
    	genre: '',
    	ageRestriction: false,
    	link: '',
    	startHour: 0,
    	endHour: 0,
    	status: ''
	}

	apiLoaded = false;

	foundVideos: VideoModel[] = [];

	constructor (private videoService: VideoService) { }

	async ngOnInit(): Promise<void> {
		await new Promise<void>((resolve) => {
			this.videoService.getAllVideos().subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						this.foundVideos = videos.map(video => video);
					}
					resolve();
				}
			})
		});

		if (!this.apiLoaded) {
			const tag = document.createElement('script');
			tag.src = 'https://www.youtube.com/iframe_api';
			document.body.appendChild(tag);
			this.apiLoaded = true;
		}
	}

	async find() {
		if (!this.validateForm()) {
			await new Promise<void>((resolve) => {
				this.videoService.getAllVideos().subscribe({
					next: async (videos) => {
						if (videos && Array.isArray(videos)) {
							this.foundVideos = videos.map(video => video);
						}
						resolve();
					}
				})
			});
		}
		else {
			if (this.videoData.name != '') {
				await new Promise<void>((resolve) => {
					this.videoService.getVideoByName(this.videoData.name).subscribe({
						next: async (videos) => {
							if (videos && Array.isArray(videos)) {
								this.foundVideos = videos.map(video => video);
							}
							resolve();
						}
					})
				});
			}
			else if (this.videoData.channel != '') {
				await new Promise<void>((resolve) => {
					this.videoService.getVideoByChannel(this.videoData.channel).subscribe({
						next: async (videos) => {
							if (videos && Array.isArray(videos)) {
								this.foundVideos = videos.map(video => video);
							}
							resolve();
						}
					})
				});
			}
			else if (this.videoData.genre != '') {
				await new Promise<void>((resolve) => {
					this.videoService.getVideoByGenre(this.videoData.genre).subscribe({
						next: async (videos) => {
							if (videos && Array.isArray(videos)) {
								this.foundVideos = videos.map(video => video);
							}
							resolve();
						}
					})
				});
			}
			else if (this.videoData.startHour != 0 && Number(this.videoData.startHour)) {
				await new Promise<void>((resolve) => {
					this.videoService.getVideoByHour(this.videoData.startHour).subscribe({
						next: async (videos) => {
							if (videos && Array.isArray(videos)) {
								this.foundVideos = videos.map(video => video);
							}
							resolve();
						}
					})
				});
			}
		}
	}

	private validateForm(): boolean {
		if (this.videoData.name == '' && this.videoData.channel == '' && this.videoData.genre == '' && (this.videoData.startHour == 0 || !Number(this.videoData.startHour))) {
			return false;
		}
		return true;
	}

	async deleteVideo(id: number) {
		await new Promise<void>((resolve) => {
			this.videoService.delete(id).subscribe({
				next: () => {
					resolve();
					this.ngOnInit();
				},
				error: () => {
					resolve();
				}
			})
		});
	}

}
