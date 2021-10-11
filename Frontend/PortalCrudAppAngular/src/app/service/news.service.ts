import { NewsDTO, NewsResponseDTO } from './news.interface';
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

  getNewsById(id: number): Observable<NewsResponseDTO> {
    return this.http.get<NewsResponseDTO>(this.baseUrl + '/' + id);
  }

  getSomeNews(page: number, size: number): Observable<Array<NewsResponseDTO>> {
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());
    return this.http.get<Array<NewsResponseDTO>>(this.baseUrl, {params: params})
  }

  updateNews(id: number, newsDTO: NewsDTO) {
    return this.http.put(this.baseUrl + '/' + id, newsDTO)
  }

  deleteNews(id: number) {
    return this.http.delete(this.baseUrl + '/' + id)
  }

  createNews(news: Data) {
   return this.http.post(this.baseUrl, news)
  }
}
