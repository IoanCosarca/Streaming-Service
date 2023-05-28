import { Component, OnInit } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { AdminModel, UserModel } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
	userData!: UserModel;

	adminData : AdminModel = {
		id: 0,
    	userID: 0,
    	type: '',
    	firstName: '',
    	lastName: '',
    	email: '',
    	password: ''
	}

	constructor (private userService: UserService, private dataService: DataService, private toast: NgToastService) { }

	ngOnInit(): void {
		this.dataService.currentUserData.subscribe(uData => this.userData = uData);
	}

	async update() {
		if (this.validateForm()) {
			if (this.userData.type === "CLIENT") {
				await new Promise<void>((resolve) => {
					this.userService.updateClient(this.userData).subscribe({
						next: async() => {
							this.toast.success({ detail: 'Success', summary: 'Your account was updated!', duration: 5000, sticky: false });
							resolve();
						}
					})
				});
			}
			if (this.userData.type === "ADMIN") {
				this.adminData = this.userData;
				await new Promise<void>((resolve) => {
					this.userService.updateAdmin(this.adminData).subscribe({
						next: async() => {
							this.toast.success({ detail: 'Success', summary: 'Your account was updated!', duration: 5000, sticky: false });
							resolve();
						}
					})
				});
			}
		}
	}

	private validateForm(): boolean {
		if (this.userData.firstName === '') {
			this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for First Name', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.lastName === '') {
			this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for Last Name', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.email === '') {
			this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for email', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.password === '') {
			this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for password', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.type === "CLIENT") {
			if (this.userData.age === 0 || !Number(this.userData.age)) {
				this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for age', duration: 5000, sticky: false });
				return false;
			}
			if (this.userData.country === '') {
				this.toast.error({ detail: 'Error Updating', summary: 'Invalid values for country', duration: 5000, sticky: false });
				return false;
			}
		}
		return true;
	}

}
