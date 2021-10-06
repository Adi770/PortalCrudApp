import { NewsService } from './../../service/news.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data:any,
    private newsService:NewsService
  ) { }

  ngOnInit(): void {
  }

  deleteNews(){
    this.newsService.deleteNews(this.data.id);
  }

}
