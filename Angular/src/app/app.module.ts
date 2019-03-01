import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ReversesService} from './reverses.service';
import {LoginComponent} from './login/login.component';
import {Interceptor} from './app.interceptor';
import {AuthService} from './auth.service';
import {TokenStorage} from './token.storage';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [ReversesService, {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}, AuthService, TokenStorage],
  bootstrap: [AppComponent]
})
export class AppModule {
}


