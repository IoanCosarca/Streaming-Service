import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UserModel } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-deleteuser',
	templateUrl: './deleteuser.component.html',
	styleUrls: ['./deleteuser.component.css']
})
export class DeleteuserComponent implements OnInit {
	showOption: string = "password";

	emptyUser: UserModel = {
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

	userData: UserModel = {
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

	constructor (private dataService: DataService, private userService: UserService, private toast: NgToastService, private router: Router) { }

	ngOnInit(): void {
		this.dataService.currentUserData.subscribe(uData => this.userData = uData);
	}

	async deleteClicked() {
		if (!this.validate()) {
			this.userData.password = '';
			this.toast.error({ detail: 'Can\'t delete account', summary: 'Wrong password', duration: 5000, sticky: false });
			return;
		}
		else {
			if (this.userData.type == "CLIENT") {
				await new Promise<void>((resolve) => {
					this.userService.deleteClient(this.userData.userID).subscribe({
						next: () => {
							resolve();
							this.dataService.changeUserData(this.emptyUser);
							this.router.navigate(['/']);
						},
						error: () => {
							resolve();
						}
					})
				});
			}
			else if (this.userData.type == "ADMIN") {
				await new Promise<void>((resolve) => {
					this.userService.deleteAdmin(this.userData.userID).subscribe({
						next: () => {
							resolve();
							this.dataService.changeUserData(this.emptyUser);
							this.router.navigate(['/']);
						},
						error: () => {
							resolve();
						}
					})
				});
			}
		}
	}

	async validate(): Promise<boolean> {
		let userPassword: string = '';

		if (this.userData.type == "CLIENT")
		{
			await new Promise<void>((resolve) => {
				this.userService.getClientByID(this.userData.userID).subscribe({
					next: async (client) => {
						if (client != null) {
							userPassword = client.password;
						}
						resolve();
					},
					error: () => {
						resolve();
					}
				})
			});
			if (this.userData.password == userPassword) {
				return true;
			}
		}
		else if (this.userData.type == "ADMIN") {
			await new Promise<void>((resolve) => {
				this.userService.getAdminByID(this.userData.userID).subscribe({
					next: async (admin) => {
						if (admin != null) {
							userPassword = admin.password;
						}
						resolve();
					},
					error: () => {
						resolve();
					}
				})
			});
			if (this.userData.password == userPassword) {
				return true;
			}
		}

		return false;
	}

	changeVisibility() {
		if (this.showOption === "password") {
			this.showOption = "text";
		}
		else {
			this.showOption = "password";
		}
	}

}
