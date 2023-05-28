import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/models/user.model';
import { VideoModel } from 'src/app/models/video.model';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

	title: string = 'Streaming Service';

	page!: number;

	emptyUser: UserModel = {
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

	@Input()
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

	@Output()
	videosUpdated = new EventEmitter<VideoModel[]>();

	@Output()
	disconnect = new EventEmitter<UserModel>();

	@Output()
	changePage = new EventEmitter<number>();

	constructor(private videoService: VideoService, private router: Router) { }

	ngOnInit(): void { }

	async toggleHome() {
		await new Promise<void>((resolve) => {
			this.videoService.getAllVideos().subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						this.videoEntries = videos.map(video => video);
					}
					resolve();
				}
			});
		});
		this.page = 0;
		this.changePage.emit(this.page);
		this.videosUpdated.emit(this.videoEntries);
	}

	toggleLogIn() {
		console.log("LogIn");
	}

	toggleRegister() {
		console.log("Register");
	}

	logout() {
		this.disconnect.emit(this.emptyUser);
	}

	async handleSearch(keyword: string) {
		await new Promise<void>((resolve) => {
			this.videoService.getVideoByName(keyword).subscribe({
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
		await new Promise<void>((resolve) => {
			this.videoService.getVideoByChannel(keyword).subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						const aux = videos.map(video => video);
						for (const rez of aux) {
							this.videoEntries.push(rez);
						}
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
		await new Promise<void>((resolve) => {
			this.videoService.getVideoByGenre(keyword).subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						const aux = videos.map(video => video);
						for (const rez of aux) {
							this.videoEntries.push(rez);
						}
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
		this.page = 1;
		this.changePage.emit(this.page);
		this.videosUpdated.emit(this.videoEntries);
	}

	deleteAccount() {
		this.router.navigate(['/DeleteAccount']);
	}

}
