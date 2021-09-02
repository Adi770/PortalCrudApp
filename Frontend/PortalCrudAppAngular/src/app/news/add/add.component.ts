import { NewsDTO } from './../../service/news.interface';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(private newsService: NewsService) { }

  imageList: FileList;

  ngOnInit(): void {
  }

  onFileSelected(event) {
    for (var i = 0; i < event.target.files.length; i++) {
      this.imageList = event.target.files[i];
    }
  }

  uploadFile(news: NewsDTO, image) {
    const data = new FormData();
    for (var i = 0; i < this.imageList.length; i++) {
      data.append('file',this.imageList.item[i])
    }

    data.append('news', JSON.stringify(news));

    this.newsService.createNews(data);
  }


}
