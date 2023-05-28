import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { VideoModel } from 'src/app/models/video.model';

@Component({
	selector: 'app-search',
	templateUrl: './search.component.html',
	styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
	enteredSearchValue: string = "";

	videoEntries: VideoModel[] = [];

	@Output()
	searchCommand = new EventEmitter<string>

	constructor() { }

	ngOnInit(): void { }

	checkEnter(event: KeyboardEvent) {
		if (event.key === "Enter") {
			this.searchCommand.emit(this.enteredSearchValue);
		}
	}

	initiateSearch() {
		this.searchCommand.emit(this.enteredSearchValue);
	}

}
