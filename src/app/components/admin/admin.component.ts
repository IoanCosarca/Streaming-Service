import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-admin',
	templateUrl: './admin.component.html',
	styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

	page!: number;

	ngOnInit(): void {
		this.page = 0;
	}

	toggleUsers() {
		this.page = 0;
	}

	toggleAddVideo() {
		this.page = 1;
	}

	toggleFDVideo() {
		this.page = 2;
	}

}
