import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news-box',
  templateUrl: './news-box.component.html',
  styleUrls: ['./news-box.component.css']
})
export class NewsBoxComponent implements OnInit {

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
  }

  news=[1,2,3,4,5,6,7,0];
  getSomeNews() {
    return
  }



}
