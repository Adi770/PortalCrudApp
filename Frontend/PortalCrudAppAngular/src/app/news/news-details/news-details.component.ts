import { NewsDTO } from './../../service/news.interface';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.css']
})
export class NewsDetailsComponent implements OnInit {

  constructor(private newsService: NewsService) { }

  news: NewsDTO;
  items = [1, 2, 3, 4, 5, 6, 7, 0];
  
  ngOnInit(): void {
  }

  loadNews(id: number): void {
    this.newsService.getNewsById(id).subscribe(res=>{
      this.news=res;
    });
  }

}
