import { FormBuilder } from '@angular/forms';
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
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  deleteFormGroup = this.formBuilder.group({
    idNews: ''
  });


  ngOnInit(): void {
  }

  deleteNews() {
    console.log(this.data)
    if (this.data != null) {
      this.delete(this.data.id)
    } else {
      this.delete(this.deleteFormGroup.get(['idNews']).value)
    }
  }

  delete(idNews) {
    this.newsService.deleteNews(idNews).subscribe(
      res => console.log('delete news with id ' + idNews),
      err => console.error(err),
      () => {
        console.log('test ' + this.router.url)
        this.router.navigate([this.router.url])
      }
    );
  }
}
