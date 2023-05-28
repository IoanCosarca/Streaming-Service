import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { VideoModel } from 'src/app/models/video.model';
import { DataService } from 'src/app/services/data.service';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	page!: number;

	userData: UserModel = {
		id: 0,
		userID: 0,
		type: '',
		firstName: '',
		lastName: '',
		email: '',
		password: '',
		age: 0,
		country: ''
	}

	videoEntries: VideoModel[] = [];

	constructor (private videoService: VideoService, private dataService: DataService) { }
	
	async ngOnInit(): Promise<void> {
		this.page = 0;
		this.dataService.currentUserData.subscribe(uData => this.userData = uData);

		await new Promise<void>((resolve) => {
			this.videoService.getAllVideos().subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						this.videoEntries = videos.map(video => video);
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			});
		});

		const currentTime = new Date();
		const hour = currentTime.getHours();
		for (const video of this.videoEntries) {
			if (video.status != "BUSY") {
				if (video.startHour <= hour && hour < video.endHour) {
					video.status = "AVAILABLE";
				}
				else {
					video.status = "UNAVAILABLE";
				}
			}
			await new Promise<void>((resolve) => {
				this.videoService.update(video).subscribe({
					next: () => {
						resolve();
					},
					error: () => {
						resolve();
					}
				})
			});
		}
	}

	handleVideosUpdated(updatedVideos: VideoModel[]) {
		this.videoEntries = updatedVideos;
	}

	handleLogOff(noUser: UserModel) {
		this.userData = noUser;
	}

	handlePage(newPage: number) {
		this.page = newPage;
	}

}
