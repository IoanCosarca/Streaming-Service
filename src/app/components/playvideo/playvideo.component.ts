import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoModel } from 'src/app/models/video.model';
import { VideoService } from 'src/app/services/video.service';

@Component({
	selector: 'app-playvideo',
	templateUrl: './playvideo.component.html',
	styleUrls: ['./playvideo.component.css']
})
export class PlayvideoComponent implements OnInit {
	videoData: VideoModel = {
		id: 0,
		name: '',
		channel: '',
		genre: '',
		ageRestriction: false,
		link: '',
		startHour: 0,
		endHour: 0,
		status: ''
	}

	constructor(private route: ActivatedRoute, private videoService: VideoService) { }

	ngOnInit(): void {
		this.route.queryParamMap.subscribe(params => {
			const videoParam = params.get('videoEntry');
			if (videoParam !== null) {
				this.videoData = JSON.parse(videoParam);
			}
		})
	}

	async freeVideo() {
		this.videoData.status = "AVAILABLE";
		await new Promise<void>((resolve) => {
			this.videoService.update(this.videoData).subscribe({
				next: () => {
					resolve();
				},
				error: () => {
					resolve();
				}
			})
		});
	}

}
