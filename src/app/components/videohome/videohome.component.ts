import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { HistoryModel } from 'src/app/models/history.model';
import { VideoModel } from 'src/app/models/video.model';
import { HistoryService } from 'src/app/services/history.service';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-videohome',
	templateUrl: './videohome.component.html',
	styleUrls: ['./videohome.component.css']
})
export class VideohomeComponent implements OnInit {
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

	constructor(private historyService: HistoryService, private videoService: VideoService, private router: Router, private toast: NgToastService) { }

	async ngOnInit(): Promise<void> {
		if (!this.apiLoaded) {
			const tag = document.createElement('script');
			tag.src = 'https://www.youtube.com/iframe_api';
			document.body.appendChild(tag);
			this.apiLoaded = true;
		}
	}

	async handleClick(videoEntry: VideoModel) {
		if (videoEntry.status == "AVAILABLE" && this.userID != 0) {
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
			videoEntry.status = "BUSY";
			await new Promise<void>((resolve) => {
				this.videoService.update(videoEntry).subscribe({
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
		else if (videoEntry.status == "BUSY") {
			this.toast.warning({ detail: 'Busy Video', summary: 'This video is watched by another person!', duration: 5000, sticky: false })
		}
	}

}
