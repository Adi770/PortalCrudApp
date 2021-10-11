import { Router } from '@angular/router';
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
    @Inject(MAT_DIALOG_DATA) public data: any,
    private newsService: NewsService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  deleteNews() {
    console.log(this.data.id)
    this.newsService.deleteNews(this.data.id).subscribe(
      res => console.log('delete news with id ' + this.data.id),
      err => console.error(err),
      () => {
        console.log('test '+this.router.url)
        this.router.navigate([this.router.url])}
    );
  }

}
