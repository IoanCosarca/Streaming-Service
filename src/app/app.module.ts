import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule, Routes } from "@angular/router";
import { YouTubePlayerModule } from "@angular/youtube-player";
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { NgToastModule } from "ng-angular-popup";
import { AppComponent } from "./app.component";
import { AddvideosComponent } from "./components/addvideos/addvideos.component";
import { AdminComponent } from "./components/admin/admin.component";
import { ButtonComponent } from "./components/button/button.component";
import { FindvideoComponent } from "./components/findvideo/findvideo.component";
import { HeaderComponent } from "./components/header/header.component";
import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/login/login.component";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { RegisterComponent } from "./components/register/register.component";
import { SearchComponent } from "./components/search/search.component";
import { SeeuserComponent } from "./components/seeuser/seeuser.component";
import { VideohomeComponent } from "./components/videohome/videohome.component";
import { VideosearchComponent } from "./components/videosearch/videosearch.component";
import { UserService } from "./services/user.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './components/profile/profile.component';
import { HistoryComponent } from './components/history/history.component';
import { HistoryuserComponent } from './components/historyuser/historyuser.component';
import { HistoryglobalComponent } from './components/historyglobal/historyglobal.component';
import { DeleteuserComponent } from './components/deleteuser/deleteuser.component';
import { PlayvideoComponent } from './components/playvideo/playvideo.component';
import { NotificationsComponent } from './components/notifications/notifications.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent },
	{ path: 'Login', component: LoginComponent },
	{ path: 'Register', component: RegisterComponent },
	{ path: 'Admin', component: AdminComponent },
	{ path: 'Profile', component: ProfileComponent },
	{ path: 'DeleteAccount', component: DeleteuserComponent },
	{ path: 'PlayVideo', component: PlayvideoComponent },
	{ path: 'Notifications', component: NotificationsComponent }
]

@NgModule({
	declarations: [
		AppComponent,
		HeaderComponent,
		ButtonComponent,
		SearchComponent,
		NavbarComponent,
		LoginComponent,
		HomeComponent,
		RegisterComponent,
		VideohomeComponent,
		VideosearchComponent,
		AdminComponent,
		SeeuserComponent,
		AddvideosComponent,
		FindvideoComponent,
		ProfileComponent,
		HistoryComponent,
		HistoryuserComponent,
		HistoryglobalComponent,
		DeleteuserComponent,
		PlayvideoComponent,
		NotificationsComponent
	],
	imports: [
		BrowserModule,
		RouterModule.forRoot(appRoutes, { enableTracing: true }),
		YouTubePlayerModule,
		NgToastModule,
		FormsModule,
		HttpClientModule,
		MatMenuModule,
		MatButtonModule,
		BrowserAnimationsModule
	],
	providers: [UserService],
	bootstrap: [AppComponent]
})
export class AppModule { }
