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

  ngOnInit(): void {
  }

  news = [1, 2, 3, 4, 5, 6, 7, 0];
  getSomeNews() {
    return
  }


  openDeleteDialog() {
    this.dialog.closeAll;
    this.dialog.open(DeleteComponent, {
      width: '250px'
    })
  }
  openEditDialog() {
    this.dialog.closeAll;
    this.dialog.open(EditComponent, {
      width: '250px'
    })
  }


}
