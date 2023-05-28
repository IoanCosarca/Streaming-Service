import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageModel } from '../models/message.model';

@Injectable({
	providedIn: 'root'
})
export class MessagesService {

	constructor(private httpClient: HttpClient) { }

	public getUserMessages(userID: number) {
		return this.httpClient.get<MessageModel>(`/api/getUserMessages/${userID}`);
	}

	public saveMessage(messageData: MessageModel) {
		return this.httpClient.post<MessageModel>(`/api/addMessage`, messageData);
	}
	
}
