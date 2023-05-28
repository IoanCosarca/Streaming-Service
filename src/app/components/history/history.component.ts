import { Component, Input, OnInit } from '@angular/core';
import { HistoryService } from 'src/app/services/history.service';

@Component({
	selector: 'app-history',
	templateUrl: './history.component.html',
	styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
	@Input()
	userID!: number;

	@Input()
	userType!: string;

	historyPage!: number;

	constructor (private historyService: HistoryService) { }

	async ngOnInit(): Promise<void> {
		this.historyPage = 0;
	}

	userHistory() {
		this.historyPage = 0;
	}

	globalHistory() {
		this.historyPage = 1;
	}

}
