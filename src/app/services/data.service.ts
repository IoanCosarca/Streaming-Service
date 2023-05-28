import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { UserModel } from '../models/user.model';

@Injectable({
	providedIn: 'root'
})
export class DataService {
	userTemplate: UserModel = {
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

	private userData = new BehaviorSubject<UserModel>(this.userTemplate);

	currentUserData = this.userData.asObservable();

	constructor() { }

	changeUserData(uData: UserModel) {
		this.userData.next(uData);
	}

}
