import { Component, OnInit } from '@angular/core';
import { HistoryModel } from 'src/app/models/history.model';
import { UserModel } from 'src/app/models/user.model';
import { VideoModel } from 'src/app/models/video.model';
import { HistoryService } from 'src/app/services/history.service';
import { UserService } from 'src/app/services/user.service';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-historyglobal',
	templateUrl: './historyglobal.component.html',
	styleUrls: ['./historyglobal.component.css']
})
export class HistoryglobalComponent implements OnInit {
	historyEntries: HistoryModel[] = [];

	userEntries: UserModel[] = [];
	
	videoEntries: VideoModel[] = [];

	constructor (private historyService: HistoryService, private userService: UserService, private videoService: VideoService) { }

	async ngOnInit(): Promise<void> {
		await new Promise<void>((resolve) => {
			this.historyService.getGlobalHistory().subscribe({
				next: async (history) => {
					if (history && Array.isArray(history)) {
						this.historyEntries = history.map((entry) => entry);
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});

		for (const entry of this.historyEntries) {
			await new Promise<void>((resolve) => {
				this.userService.getClientByID(entry.userID).subscribe({
					next: async (client) => {
						if (client != null) {
							this.userEntries.push(client);
						}
						resolve();
					},
					error: () => {
						resolve();
					}
				});
			});
			await new Promise<void>((resolve) => {
				this.userService.getAdminByID(entry.userID).subscribe({
					next: async (admin) => {
						if (admin != null) {
							this.userEntries.push(admin);
						}
						resolve();
					},
					error: () => {
						resolve();
					}
				});
			});
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

}
