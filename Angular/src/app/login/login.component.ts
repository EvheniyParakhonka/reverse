import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {ReversesService} from '../service/reverses.service';
import {AuthService} from '../service/auth.service';
import {TokenStorage} from '../token.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {userName: '', password: ''};

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage) {
  }

  login(): void {
    this.authService.attemptAuth(this.credentials.userName, this.credentials.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['home']);
        console.log('response log' + data.token);
      }
    );
  }
  ngOnInit(): void {
  }

}
