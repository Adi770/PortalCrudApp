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
    console.log('reload')
    this.baseUrl = environment.baseAddress;
    this.loadNews();
  }
  role = true;

  loadNews() {
    console.log('load news');
    this.newsService.getSomeNews(0, 5).subscribe(res => {
      this.news = res;
      console.log(res)
    },
      err => console.log(err),
      () => console.log('end request')
    );
  }

  openDeleteDialog(idNews: string) {
    this.dialog.closeAll;
    let dialogDelete = this.dialog.open(DeleteComponent, {
      width: '250px',
      data: { id: idNews }
    });
    dialogDelete.afterClosed().subscribe(
      res => {
        this.ngOnInit()
      }
    )

  }


  openDeleteDialo2(idNews: string) {
    console.log(idNews)
  }




}
