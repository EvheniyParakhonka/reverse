import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ReversesService {
  baseUrl = '//localhost:8080/reverse_war/';

  constructor(private http: HttpClient, private router: Router) {
  }

  getPages(count) {
    return this.http.get(this.baseUrl + 'history/pages', {
        params: {
          count
        }
      }
    );
  }

  reformatJsonToXml(message, save) {
    return this.http.post(this.baseUrl + 'parse/to-xml', {
      reformatValue: message,
      checkedToSave: save
    });
  }

  reformatXmlToJson(message, save) {
    return this.http.post(this.baseUrl + 'parse/to-json', {
      reformatValue: message,
      checkedToSave: save
    });
  }

  downloadFile(id, fileName) {
    console.log(id + ' ' + fileName);
    return this.http.get(this.baseUrl + 'download-file', {
      params: {
        id,
        fileName
      },
      headers: {
        accept: 'application/octet-stream'
      },
      responseType: 'blob' as 'json'
    });
  }

  getLastAddedHistory() {
    return this.http.get(this.baseUrl + 'history/last');
  }

  uploadFile(formData) {
    return this.http.post(this.baseUrl + 'upload-file', formData);
  }

  getHistoryToOnePage(countPage, numberPage) {
    return this.http.get(this.baseUrl + 'history/stories', {
      params: {
        page: numberPage,
        count: countPage
      }
    });
  }


}
