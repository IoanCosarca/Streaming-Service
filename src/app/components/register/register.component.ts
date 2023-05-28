import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UserRegisterModel } from 'src/app/models/user-register.model';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {
	showOption: string = "password";

	userData: UserRegisterModel = {
		type: 'CLIENT',
		firstName: '',
		lastName: '',
		email: '',
		password: '',
		age: 0,
		country: ''
	}

	private valid!: boolean;

	constructor(private userService: UserService, private toast: NgToastService, private router: Router) { }

	async register() {
		if (this.validateForm()) {
			this.valid = true;
			await new Promise<void>((resolve) => {
				this.userService.getAllClients().subscribe({
					next: async (clients) => {
						if (clients && Array.isArray(clients)) {
							const emails = clients.map(client => client?.email);
							for (const em of emails) {
								if (em === this.userData.email) {
									this.valid = false;
									break;
								}
							}
						}
						else {
							console.error('Invalid clients data');
						}
						resolve();
					},
					error: () => {
						resolve(); // Resolve the Promise to continue execution
					}
				});
			});

			if (this.valid === true) {
				await new Promise<void>((resolve) => {
					this.userService.register(this.userData).subscribe({
						next: async () => {
							this.toast.success({ detail: 'Success', summary: 'Your account was registered!', duration: 5000, sticky: false });
							resolve();
							this.router.navigate(['/']);
						}
					});
				});
			}
			else {
				this.toast.error({ detail: 'Can\'t Register', summary: 'There is already a user with that email', duration: 5000, sticky: false });
			}

		}
	}

	private validateForm(): boolean {
		if (this.userData.firstName === '') {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for First Name', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.lastName === '') {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for Last Name', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.email === '') {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for email', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.password === '') {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for password', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.age === 0 || !Number(this.userData.age)) {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for age', duration: 5000, sticky: false });
			return false;
		}
		if (this.userData.country === '') {
			this.toast.error({ detail: 'Failed Register', summary: 'Invalid values for country', duration: 5000, sticky: false });
			return false;
		}
		return true;
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
