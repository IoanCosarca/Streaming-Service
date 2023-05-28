import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { MessageModel } from 'src/app/models/message.model';
import { AdminModel } from 'src/app/models/user.model';
import { VideoUploadModel } from 'src/app/models/video-upload.model';
import { MessagesService } from 'src/app/services/messages.service';
import { UserService } from 'src/app/services/user.service';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-addvideos',
	templateUrl: './addvideos.component.html',
	styleUrls: ['./addvideos.component.css']
})
export class AddvideosComponent {
	videoData: VideoUploadModel = {
		name: '',
    	channel: '',
    	genre: '',
    	ageRestriction: false,
    	link: '',
    	startHour: 0,
    	endHour: 0,
	}

	private valid!: boolean;

	userIDs: number[] = [];

	messageData: MessageModel = {
		userID: 0,
		message: ''
	}

	constructor (private videoService: VideoService, private toast: NgToastService, private userService: UserService, private messageService: MessagesService) { }

	async add() {
		if (this.validateForm()) {
			this.valid = true;
			await new Promise<void>((resolve) => {
				this.videoService.getAllVideos().subscribe({
					next: async (videos) => {
						if (videos && Array.isArray(videos)) {
							const links = videos.map(video => video?.link);
							for (const link of links) {
								if (link === this.videoData.link) {
									this.valid = false;
									break;
								}
							}
						}
						resolve();
					},
					error: () => {
						resolve();
					}
				})
			});
			if (this.valid === true) {
				await new Promise<void>((resolve) => {
					this.videoService.upload(this.videoData).subscribe({
						next: () => {
							this.toast.success({ detail: 'Success', summary: 'Video uploaded successfully!', duration: 5000, sticky: false });
							resolve();
						}
					})
				});
				await this.getUserIDs();
				for (const userID of this.userIDs) {
					this.messageData.userID = userID;
					var str1 = "Video ";
					var str2 = " was uploaded!";
					this.messageData.message = str1.concat(this.videoData.name).concat(str2);
					console.log(this.messageData.message);
					await new Promise<void>((resolve) => {
						this.messageService.saveMessage(this.messageData).subscribe({
							next: () => {
								resolve();
							}
						})
					});
				}
			}
			else {
				this.toast.error({ detail: 'Can\'t Upload Video', summary: 'There is already a video with that link', duration: 5000, sticky: false });
			}
		}
	}

	private validateForm(): boolean {
		if (this.videoData.name === '') {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for Video Name', duration: 5000, sticky: false });
			return false;
		}
		if (this.videoData.channel === '') {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for Channel', duration: 5000, sticky: false });
			return false;
		}
		if (this.videoData.genre === '') {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for Genre', duration: 5000, sticky: false });
			return false;
		}
		if (this.videoData.link === '') {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for Video Link', duration: 5000, sticky: false });
			return false;
		}
		if (this.videoData.startHour === 0 || !Number(this.videoData.startHour)) {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for Start Hour', duration: 5000, sticky: false });
			return false;
		}
		if (this.videoData.endHour === 0 || !Number(this.videoData.endHour)) {
			this.toast.error({ detail: 'Failed Upload', summary: 'Invalid values for End Hour', duration: 5000, sticky: false });
			return false;
		}
		return true;
	}

	async getUserIDs() {
		await new Promise<void>((resolve) => {
			this.userService.getAllClients().subscribe({
				next: async (clients) => {
					if (clients && Array.isArray(clients)) {
						this.userIDs = clients.map(client => client.userID);
					}
					resolve();
				}
			})
		});
		let aux: number[] = [];
		await new Promise<void>((resolve) => {
			this.userService.getAllAdmins().subscribe({
				next: async (admins) => {
					if (admins && Array.isArray(admins)) {
						aux = admins.map(admin => admin.userID);
						for (const id of aux) {
							this.userIDs.push(id);
						}
					}
					resolve();
				}
			})
		});
	}

}
