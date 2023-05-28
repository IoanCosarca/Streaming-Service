import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-seeuser',
	templateUrl: './seeuser.component.html',
	styleUrls: ['./seeuser.component.css']
})
export class SeeuserComponent implements OnInit {
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

	foundUsers: UserModel[] = [];

	constructor (private userService: UserService) { }

	async ngOnInit(): Promise<void> {
		await new Promise<void>((resolve) => {
			this.userService.getAllClients().subscribe({
				next: async (clients) => {
					if (clients && Array.isArray(clients)) {
						this.foundUsers = clients.map(client => client);
					}
					resolve();
				}
			})
		});
		await new Promise<void>((resolve) => {
			this.userService.getAllAdmins().subscribe({
				next: async (admins) => {
					if (admins && Array.isArray(admins)) {
						const aux = admins.map(admin => admin);
						for (const rez of aux) {
							this.foundUsers.push(rez);
						}
					}
					resolve();
				}
			})
		});
	}

	async find() {
		if (!this.validateForm()) {
			await new Promise<void>((resolve) => {
				this.userService.getAllClients().subscribe({
					next: async (clients) => {
						if (clients && Array.isArray(clients)) {
							this.foundUsers = clients.map(client => client);
						}
						resolve();
					}
				})
			});
			await new Promise<void>((resolve) => {
				this.userService.getAllAdmins().subscribe({
					next: async (admins) => {
						if (admins && Array.isArray(admins)) {
							const aux = admins.map(admin => admin);
							for (const rez of aux) {
								this.foundUsers.push(rez);
							}
						}
						resolve();
					}
				})
			});
		}
		else {
			this.foundUsers = [];
			if (this.userData.userID != 0 && Number(this.userData.userID)) {
				await new Promise<void>((resolve) => {
					this.userService.getClientByID(this.userData.userID).subscribe({
						next: async (client) => {
							if (client != null) {
								this.foundUsers.push(client);
							}
							resolve();
						},
						error: () => {
							resolve();
						}
					})
				});
				await new Promise<void>((resolve) => {
					this.userService.getAdminByID(this.userData.userID).subscribe({
						next: async (admin) => {
							if (admin != null) {
								this.foundUsers.push(admin);
							}
							resolve();
						},
						error: () => {
							resolve();
						}
					})
				});
			}
			else if (this.userData.email != '') {
				await new Promise<void>((resolve) => {
					this.userService.getClientByEmail(this.userData.email).subscribe({
						next: async (client) => {
							this.foundUsers.push(client);
							resolve();
						},
						error: () => {
							resolve();
						}
					})
				});
				await new Promise<void>((resolve) => {
					this.userService.getAdminByEmail(this.userData.email).subscribe({
						next: async (admin) => {
							this.foundUsers.push(admin);
							resolve();
						},
						error: () => {
							resolve();
						}
					})
				});
			}
			else if (this.userData.age != 0 || Number(this.userData.age)) {
				await new Promise<void>((resolve) => {
					this.userService.getClientsByAge(this.userData.age).subscribe({
						next: async (clients) => {
							if (clients && Array.isArray(clients)) {
								this.foundUsers = clients.map(client => client);
							}
							resolve();
						},
						error: () => {
							resolve();
						}
					})
				});
			}
			else if (this.userData.country != '') {
				await new Promise<void>((resolve) => {
					this.userService.getClientsByCountry(this.userData.country).subscribe({
						next: async (clients) => {
							if (clients && Array.isArray(clients)) {
								this.foundUsers = clients.map(client => client);
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
	}

	private validateForm(): boolean {
		if ((this.userData.userID == 0 || !Number(this.userData.userID)) && this.userData.email == '' && (this.userData.age == 0 || !Number(this.userData.age)) && this.userData.country == '') {
			return false;
		}
		return true;
	}

}
