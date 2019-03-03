import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  attemptAuth(ussername: string, pasword: string): any {
    const credentials = {userName: ussername, password: pasword};
    console.log('attempAuth ::');
    return this.http.post('http://localhost:8080/reverse_war/login', {userName: ussername, password: pasword});
  }
}
