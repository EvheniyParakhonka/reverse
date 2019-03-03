import {Component, OnInit} from '@angular/core';
import {ReversesService} from '../service/reverses.service';
import {TokenStorage} from '../token.storage';

declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  disable = false;
  messageJson: string;
  messageXml: string;
  files: File;
  fileUpload = false;
  saveHistory = true;
  fileNmaeToLoad: string;
  items: { id: number, user: string, date: string, jsonFull: string, xmlFull: string, isFile: boolean }[] = [];
  pages: number[];
  one: number;
  disableFirstPage = true;
  disabledLastPage = false;
  currentPage = 1;
  hiddenPlusOne = false;
  hiddenPlusTwo = false;


  constructor(private service: ReversesService, private token: TokenStorage) {
  }

  handleFileChange(event) {
    this.files = event.target.files[0];
  }

  uploadFile() {
    console.log('upload');
    const formData = new FormData();
    formData.append('file', this.files);
    // @ts-ignore
    formData.append('save', this.saveHistory);
    console.log('form data');
    console.log(formData);
    console.log(this.files.name);
    this.service.uploadFile(formData).subscribe(() => {
        console.log('file upload response');
        this.getLastAddedHistory();
        this.fileUpload = true;
      }, error => console.log(error),
      // tell us if it's finished
      () => {
        console.log('completed');
      }
    );
  }

  jsonToXml() {
    this.disable = true;
    console.log('jsonToXml');
    console.log(this.messageJson);
    this.service.reformatJsonToXml(this.messageJson, this.saveHistory).subscribe(response => {
        console.log(response);
        if (this.saveHistory) {
          if (this.currentPage === 1) {
            this.getLastAddedHistory();
          }
          this.getPages(10);
        }
        this.messageXml = String(response);
        this.disable = false;
      }
    )
    ;
  }

  xmlToJson() {
    this.disable = true;
    this.service.reformatXmlToJson(this.messageXml, this.saveHistory).subscribe(response => {
        console.log(response);
        this.messageJson = String(response);
        if (this.saveHistory) {
          if (this.currentPage === 1) {
            this.getLastAddedHistory();
          }
          this.getPages(10);
        }
        this.disable = false;
      }
    )
    ;
  }

  getLastAddedHistory() {
    this.service.getLastAddedHistory().subscribe((response: any) => {
      this.items.pop();
      this.items.unshift({
          id: response.id,
          user: response.name,
          date: new Date(response.date).getDate() + '-' + (new Date(response.date).getMonth() + 1)
            + '-' + new Date(response.date).getFullYear() + ' ' +
            new Date(response.date).getHours() + ':' + new Date(response.date).getMinutes(),
          jsonFull: response.json,
          xmlFull: response.xml,
          isFile: response.file
        }
      );
      this.downloadJsonFile(this.items[0].id, this.files.name);
    });
  }

  getFullJsonFromHistory(index) {
    console.log(index);
    $('#myModal .modal-title').text('Json');
    if (index.isFile) {
      $('#myModal .modal-body').html('<div id=\'downloadJson\'>File to large. Download him:' +
        ' <button class=\'btn btn-outline-primary btn-sm \'>load</button></div> ');
      $('#downloadJson').on('click', () => {
        this.downloadJsonFile(index.id, index.jsonFull);
        this.fileNmaeToLoad = index.jsonFull;
        console.log('click');
      });
    } else {
      $('#myModal .modal-body').text(index.jsonFull);
    }
    $('#myModal').modal('show');
  }

  downloadJsonFile(id, value) {
    console.log('download');
    this.service.downloadFile(id, value).subscribe((response: any) => {
      console.log('answer');
      const blob = new Blob([response], {type: 'application/octet-stream'});
      const link = document.createElement('a');
      link.download = value;
      link.href = window.URL.createObjectURL(blob);
      link.click();
    })
    ;
  }

  getFullXmlFromHistory(index) {
    console.log(index);
    $('#myModal .modal-title').text('Xml');
    if (index.isFile) {
      console.log('is xml file');
      $('#myModal .modal-body').html('<div id=\'downloadJson\'>File to large. Download him:' +
        ' <button class=\'btn btn-outline-primary btn-sm \'>load</button></div> ');
      $('#downloadJson').on('click', () => {
        this.downloadJsonFile(index.id, index.xmlFull);
        this.fileNmaeToLoad = index.xmlFull;
        console.log('click');
      });
    } else {
      $('#myModal .modal-body').text(index.xmlFull);
    }
    $('#myModal').modal('show');
  }

  getPageFunction(page: number) {
    console.log(page);
    this.currentPage = page;
    this.disableFirstPage = page <= 1;
    this.service.getHistoryToOnePage(10, page).subscribe(response => {
        this.items = [];
        this.currentPage = page;
        if (typeof this.pages === 'undefined') {
          console.log('undefined');
        } else {
          console.log('pages normal');
          this.hiddenPlusTwo = page > this.pages.length - 2;
          this.hiddenPlusOne = page === this.pages.length;
          this.disabledLastPage = page === this.pages.length;
          console.log('lenght pages = ' + this.pages.length + ';');
          console.log('current page ' + page);
          console.log('pages normal' + this.hiddenPlusTwo);
        }
        console.log(response);
        console.log(response[0]);
        for (let i = 0; i < 10; i++) {
          const obj = response[i];
          this.items.push({
            id: obj.id,
            user: obj.name,
            date: new Date(obj.date).getDate() + '-' + (new Date(obj.date).getMonth() + 1)
              + '-' + new Date(obj.date).getFullYear() + ' ' +
              new Date(obj.date).getHours() + ':' + new Date(obj.date).getMinutes(),
            jsonFull: obj.json,
            xmlFull: obj.xml,
            isFile: obj.file
          });
        }

        console.log(this.items[0]);
      }
    );
  }

  getPages(count) {
    this.service.getPages(count).subscribe(data => {
      console.log(data);
      this.one = +data;
      console.log(this.one);
      this.pages = [];
      for (let i = 0; i < this.one; i++) {
        console.log(i);
        this.pages[i] = i + 1;
      }
    });
  }

  ngOnInit(): void {
    this.getPages(10);
    this.getPageFunction(this.currentPage);
  }

  logout() {
    this.token.signOut();
  }

}
