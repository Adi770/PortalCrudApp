import { environment } from './../../../environments/environment';
import { Observable } from 'rxjs';
import { NewsResponseDTO } from './../../service/news.interface';
import { EditComponent } from './../edit/edit.component';
import { DeleteComponent } from './../delete/delete.component';
import { MatDialog } from '@angular/material/dialog';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news-box',
  templateUrl: './news-box.component.html',
  styleUrls: ['./news-box.component.css']
})
export class NewsBoxComponent implements OnInit {

  constructor(private newsService: NewsService, private dialog: MatDialog) { }

  news: any;
  baseUrl: string;

  ngOnInit(): void {
    this.baseUrl=environment.baseAddress;
    this.loadNews().subscribe(res => {
      this.news = res;
      console.log(res)
    });
  }
  role = true;

  loadNews(): Observable<Array<NewsResponseDTO>> {
    console.log('load news')
    return this.newsService.getSomeNews(0, 5);
  }

  openDeleteDialog(idNews: string) {
    this.dialog.closeAll;
    this.dialog.open(DeleteComponent, {
      width: '250px',
      data: { id: idNews }
    });

  }


  openDeleteDialo2(idNews: string) {
    console.log(idNews)
  }




}
