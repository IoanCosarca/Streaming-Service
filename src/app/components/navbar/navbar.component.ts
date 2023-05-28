import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { VideoModel } from 'src/app/models/video.model';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	videoEntries: VideoModel[] = [];

	page!: number;

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

	@Output()
	videosUpdated = new EventEmitter<VideoModel[]>();

	@Output()
	changePage = new EventEmitter<number>();

	constructor(private videoService: VideoService) {}

	ngOnInit(): void {}

	toggleHistory() {
		this.page = 2;
		this.changePage.emit(this.page);
	}

	toggleAdmin() {
		console.log("Admin");
	}

	async toggleCategory(category: string) {
		this.page = 0;
		await new Promise<void>((resolve) => {
			this.videoService.getVideoByGenre(category).subscribe({
				next: async (videos) => {
					if (videos && Array.isArray(videos)) {
						this.videoEntries = videos.map(video => video);
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
		this.changePage.emit(this.page);
		this.videosUpdated.emit(this.videoEntries);
	}

}
