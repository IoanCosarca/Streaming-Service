import { Component, Input, OnInit } from '@angular/core';
import { HistoryModel } from 'src/app/models/history.model';
import { VideoModel } from 'src/app/models/video.model';
import { HistoryService } from 'src/app/services/history.service';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-historyuser',
	templateUrl: './historyuser.component.html',
	styleUrls: ['./historyuser.component.css']
})
export class HistoryuserComponent implements OnInit {
	@Input()
	userID!: number;

	historyEntries: HistoryModel[] = [];
	
	videoEntries: VideoModel[] = [];

	constructor(private historyService: HistoryService, private videoService: VideoService) { }

	async ngOnInit(): Promise<void> {
		await new Promise<void>((resolve) => {
			this.historyService.getUserHistory(this.userID).subscribe({
				next: async (history) => {
					if (history && Array.isArray(history)) {
						this.historyEntries = history.map((entry) => entry);
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			});
		});

		for (const entry of this.historyEntries) {
			await new Promise<void>((resolve) => {
				this.videoService.getVideoByID(entry.videoID).subscribe({
					next: async (video) => {
						this.videoEntries.push(video);
						resolve();
					},
					error: () => {
						resolve();
					}
				});
			});
		}
	}

	async deleteHistory() {
		await new Promise<void>((resolve) => {
			this.historyService.deleteUserHistory(this.userID).subscribe({
				next: () => {
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
		this.ngOnInit();
	}
}
