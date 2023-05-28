import { Component, OnInit } from '@angular/core';
import { MessageModel } from 'src/app/models/message.model';
import { UserModel } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
	selector: 'app-notifications',
	templateUrl: './notifications.component.html',
	styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
	userData!: UserModel;

	messageEntries: MessageModel[] = [];

	constructor (private dataService: DataService, private messageService: MessagesService) { }

	async ngOnInit(): Promise<void> {
		this.dataService.currentUserData.subscribe(uData => this.userData = uData);

		await new Promise<void>((resolve) => {
			this.messageService.getUserMessages(this.userData.userID).subscribe({
				next: async (messages) => {
					if (messages && Array.isArray(messages)) {
						this.messageEntries = messages.map((message) => message);
					}
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
	}

}
