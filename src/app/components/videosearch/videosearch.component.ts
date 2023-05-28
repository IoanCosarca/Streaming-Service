import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { HistoryModel } from 'src/app/models/history.model';
import { VideoModel } from 'src/app/models/video.model';
import { HistoryService } from 'src/app/services/history.service';

@Component({
	selector: 'app-videosearch',
	templateUrl: './videosearch.component.html',
	styleUrls: ['./videosearch.component.css']
})
export class VideosearchComponent implements OnInit {
	@Input()
	videoEntries: VideoModel[] = [];

	@Input()
	userID!: number;

	historyData: HistoryModel = {
		userID: 0,
		videoID: 0,
		date: '',
		time: ''
	}

	apiLoaded = false;

	constructor (private historyService: HistoryService, private router: Router, private toast: NgToastService) { }

	ngOnInit(): void {
		if (!this.apiLoaded) {
			const tag = document.createElement('script');
			tag.src = 'https://www.youtube.com/iframe_api';
			document.body.appendChild(tag);
			this.apiLoaded = true;
		}
	}

	async handleClick(videoEntry: VideoModel) {
		this.historyData.userID = this.userID;
		this.historyData.videoID = videoEntry.id;

		const currentDate = new Date();
		const year = currentDate.getFullYear();
		const month = String(currentDate.getMonth() + 1).padStart(2, '0');
		const day = String(currentDate.getDate()).padStart(2, '0');
		this.historyData.date = `${year}-${month}-${day}`;

		const currentTime = new Date();
		const hour = String(currentTime.getHours()).padStart(2, '0');
		const minute = String(currentTime.getMinutes()).padStart(2, '0');
		const second = String(currentTime.getSeconds()).padStart(2, '0');
		this.historyData.time = `${hour}:${minute}:${second}`;

		if (this.userID != 0) {
			await new Promise<void>((resolve) => {
				this.historyService.addHistoryEntry(this.historyData).subscribe({
					next: () => {
						resolve();
					},
					error: () => {
						resolve();
					}
				})
			});
			this.router.navigate(['/PlayVideo'], { queryParams: { videoEntry: JSON.stringify(videoEntry) } });
		}
	}

}
