import { Injectable } from '@angular/core';
import { AdminModel, UserModel,  } from '../models/user.model';
import { HttpClient } from '@angular/common/http'
import { UserRegisterModel } from '../models/user-register.model';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	constructor(private httpClient: HttpClient) { }

	public getAllClients() {
		return this.httpClient.get<UserModel>(`/api/getClients`);
	}

	public getAllAdmins() {
		return this.httpClient.get<UserModel>(`/api/getAdmins`);
	}

	public getClientByID(userID: number) {
		return this.httpClient.get<UserModel>(`/api/getClientByID/${userID}`);
	}

	public getAdminByID(userID: number) {
		return this.httpClient.get<UserModel>(`/api/getAdminByID/${userID}`);
	}

	public getClientByEmail(email: string) {
		return this.httpClient.get<UserModel>(`/api/getClientByEmail/${email}`);
	}

	public getAdminByEmail(email: string) {
		return this.httpClient.get<UserModel>(`/api/getAdminByEmail/${email}`);
	}

	public getClientsByAge(age: number) {
		return this.httpClient.get<UserModel>(`/api/getClientsByAge/${age}`);
	}

	public getClientsByCountry(country: string) {
		return this.httpClient.get<UserModel>(`/api/getClientsByCountry/${country}`);
	}

	public register(userData: UserRegisterModel) {
		return this.httpClient.post<UserRegisterModel>(`/api/addClient`, userData);
	}

	public updateClient(userData: UserModel) {
		return this.httpClient.put<UserModel>(`/api/updateClient`, userData);
	}

	public updateAdmin(userData: AdminModel) {
		return this.httpClient.put<AdminModel>('/api/updateAdmin', userData);
	}

	public deleteClient(id: number) {
		return this.httpClient.delete<UserModel>(`/api/deleteClient/${id}`);
	}

	public deleteAdmin(id: number) {
		return this.httpClient.delete<AdminModel>(`/api/deleteAdmin/${id}`);
	}

}
