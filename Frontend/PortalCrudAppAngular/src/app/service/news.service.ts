import { NewsDTO } from './news.interface';
import { environment } from './../../environments/environment';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Data } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseApiUrl + '/news'

  getNewsById(id: number): Observable<NewsDTO> {
    return this.http.get<NewsDTO>(this.baseUrl + '/' + id);
  }

  getSomeNews(page: number, size: number): Observable<Array<NewsDTO>> {
    let params = new HttpParams();
    params.set('page', page.toString());
    params.set('size', size.toString());
    return this.http.get<Array<NewsDTO>>(this.baseUrl, { params })
  }

  updateNews(id: number, newsDTO: NewsDTO) {
    this.http.put(this.baseUrl + '/id', newsDTO)
  }

  deleteNews(id: number) {
    this.http.delete(this.baseUrl + '/id')
  }

  createNews(news: Data) {
    this.http.post(this.baseUrl, news)

  }
}
