import { FormBuilder } from '@angular/forms';
import { NewsDTO } from './../../service/news.interface';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Title } from '@angular/platform-browser';
import { catchError, finalize } from 'rxjs/operators';
import { of, throwError } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(
    private newsService: NewsService,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

  }

  imageList: FileList;
  previewsImageList: string[];
  previewsImageListName: string[];
  public Editor = ClassicEditor;
  nameImage: string;
  testImageList: string;
  edtiorData: string;
  articleTitle: string;
  idNews: number;
  currentPath: string;

  articleFormGroup = this.formBuilder.group({
    title: '',
    article: ''
  })

  public config = {
    language: 'pl'
  };

  ngOnInit(): void {
    this.edtiorData = '';
    const snap = this.activatedRoute.snapshot
    this.idNews = snap.params.newsId;
    this.currentPath = snap.routeConfig.path

    this.loadEditData()

  }

  loadEditData() {
    if (this.idNews) {
      //load news by id
      this.newsService.getNewsById(this.idNews)
        .subscribe(
          res => {
            this.articleFormGroup.setValue({
              title: res.title,
              article: res.article
            })
          },
          err => {
            console.log(err)
            console.log('news doesn\'t exist')
            this.router.navigate(['news/create'])
          },
          () => ('load news ' + this.idNews)
        )
    } else if (this.currentPath === 'create') {
      this.router.navigate(['/news/create'])
    } else {
      this.router.navigate(['/news'])
    }

  }

  onFileSelected(event: any) {
    //for (var i = 0; i < event.target.files.length; i++)
    let list = new DataTransfer();
    let maxAmountImage: number;
    if (event.target.files.length < 5) {
      maxAmountImage = event.target.files.length
    }
    else {
      maxAmountImage = 5;
    }
    for (var i = 0; i < maxAmountImage; i++) {
      let reader = new FileReader();
      list.items.add(event.target.files[i])
      this.previewsImageList = [];
      reader.onload = (e: any) => {
        console.log("reader ")
        this.previewsImageList.push(e.target.result)
      }
      reader.readAsDataURL(event.target.files[i]);
    }
    this.imageList = list.files;
  }


  save() {
    console.log(this.articleFormGroup.get(['title']).value)
    console.log(this.articleFormGroup.get(['article']).value)
    const news: NewsDTO = {
      title: this.articleFormGroup.get(['title']).value,
      article: this.articleFormGroup.get(['article']).value
    }

    this.createRequest(news)
  }

  createRequest(news: NewsDTO) {
    if (this.idNews) {
      this.editNewsRequest(news);

    } else {
      this.addNewsRequest(news);
    }
  }

  private addNewsRequest(news: NewsDTO) {
    const data = new FormData();
    for (var i = 0; i < this.imageList.length; i++) {
      data.append('file', this.imageList[i]);
    }
    data.append('news', JSON.stringify(news));
    this.newsService.createNews(data)
      .subscribe(
        res => console.log('succces ', res),
        err => console.log(err),
        () => this.router.navigate(['/news'])
      );
  }

  private editNewsRequest(news: NewsDTO) {
    this.newsService.updateNews(this.idNews, news)
      .subscribe(
        res => console.log('succces ', res),
        err => console.log(err),
        () => this.router.navigate(['/news'])
      );
  }
}
