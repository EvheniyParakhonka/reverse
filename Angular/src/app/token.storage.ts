import {Injectable} from '@angular/core';


const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class TokenStorage {

  constructor() {
  }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    console.log('save token ' + token);
  }

  public hasToken(): boolean {
    return !!sessionStorage.getItem(TOKEN_KEY);

  }

  public getToken(): string {
    console.log('get token: ' + sessionStorage.getItem(TOKEN_KEY));
    return sessionStorage.getItem(TOKEN_KEY);
  }
}
