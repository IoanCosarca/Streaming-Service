import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HistoryModel } from '../models/history.model';

@Injectable({
	providedIn: 'root'
})
export class HistoryService {

	constructor(private httpClient: HttpClient) { }

	public getGlobalHistory() {
		return this.httpClient.get<HistoryModel>(`/api/getHistory`);
	}

	public getUserHistory(userID: number) {
		return this.httpClient.get<HistoryModel>(`/api/allVideosWatchedByThisUser/${userID}`);
	}
	
	public addHistoryEntry(historyData: HistoryModel) {
		return this.httpClient.post<HistoryModel>(`/api/addHistory`, historyData);
	}

	public deleteUserHistory(userID: number) {
		return this.httpClient.delete<HistoryModel>(`/api/deleteUserHistory/${userID}`);
	}

}
