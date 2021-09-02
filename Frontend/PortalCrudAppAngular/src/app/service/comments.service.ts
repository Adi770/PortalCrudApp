import { CommentDTO } from './news.interface';
import { environment } from './../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseApiUrl;
  baseUrlComments = environment.baseApiUrl + '/comments'

  getCommentById(id: number) {
    this.http.get(this.baseUrlComments + '/' + id)
  }

  getCommentByNews(idNews: number, size: number, page: number) {
    let params = new HttpParams();
    params.set('page', page.toString());
    params.set('size', size.toString());
    return this.http.get(this.baseUrl + 'news/' + idNews + '/comments', { params })
  }

  createComment(idNews: number, comment: CommentDTO) {
    this.http.post(this.baseUrl + 'news/' + idNews + '/comments', comment)
  }

  updateComment(id: number, comment: CommentDTO) {
    this.http.put(this.baseUrlComments + '/' + id, comment)
  }
}
