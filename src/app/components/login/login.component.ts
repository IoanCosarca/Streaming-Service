import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { UserModel, } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/services/data.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	showOption: string = "password";

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

	constructor(private userService: UserService, private toast: NgToastService, private dataService: DataService, private router: Router) { }

	ngOnInit(): void {
		this.dataService.currentUserData.subscribe(uData => this.userData = uData);
	}

	async loginClicked() {
		if (!this.validateForm()) {
			this.userData.email = '';
			this.userData.password = '';
			this.toast.error({ detail: 'Failed Log In', summary: 'Invalid values for email or password', duration: 5000, sticky: false });
			return;
		}
		else {
			await new Promise<void>((resolve) => {
				this.userService.getAdminByEmail(this.userData.email).subscribe({
					next: async (admin) => {
						if (this.userData.password === admin.password) {
							this.userData = admin;
							resolve(); // Resolve the Promise to continue execution
							this.dataService.changeUserData(this.userData);
							this.router.navigate(['/']);
						}
						else {
							this.userData = admin;
							this.userData.password = '';
							this.toast.error({ detail: 'Failed Log In', summary: 'Invalid password', duration: 5000, sticky: false });
							resolve(); // Resolve the Promise to continue execution
						}
					},
					error: () => {
						this.userData.type = ''; // Set the type to indicate no admin found
						resolve(); // Resolve the Promise to continue execution
					}
				});
			});
			if (this.userData.type == '') {
				await new Promise<void>((resolve) => {
					this.userService.getClientByEmail(this.userData.email).subscribe({
						next: async (client) => {
							if (this.userData.password === client.password) {
								this.userData = client;
								resolve(); // Resolve the Promise to continue execution
								this.dataService.changeUserData(this.userData);
								this.router.navigate(['/']);
							}
							else {
								this.userData = client;
								this.userData.password = '';
								this.toast.error({ detail: 'Failed Log In', summary: 'Invalid password', duration: 5000, sticky: false });
								resolve(); // Resolve the Promise to continue execution
							}
						},
						error: () => {
							this.userData.type = ''; // Set the type to indicate no admin found
							resolve(); // Resolve the Promise to continue execution
						}
					});
				});
				if (this.userData.type == '') {
					this.userData.email = '';
					this.userData.password = '';
					this.toast.error({ detail: 'Failed Log In', summary: 'This user doesn\'t exist', duration: 5000, sticky: false });
				}
			}
			console.log(this.userData.type);
		}
	}

	private validateForm(): boolean {
		if (this.userData.email == '' || this.userData.password == '') {
			return false;
		}
		return true;
	}

	changeVisibility() {
		if (this.showOption == "password") {
			this.showOption = "text";
		}
		else {
			this.showOption = "password";
		}
	}

}
