import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Router} from '@angular/router';
import {TokenStorage} from './token.storage';
import {Observable} from 'rxjs';

const TOKEN_HEADER_KEY = 'authorization';

@Injectable({
  providedIn: 'root'
})
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) {
  }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let contentType: any = null;
    if (req.headers.has('Content-Type')) {
       contentType = req.headers.get('Content-Type');
       console.log('content' + contentType);
    }
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${this.token.getToken()}`,
        // 'Content-Type': ''
          // (contentType !== 'application/json; charset=utf-8' ? 'multipart/form-data' :  undefined),
        // 'Content-Type': 'application/json; ',
        // Accept: 'application/json',
      },
    });
    return next.handle(cloned);
  }
}
