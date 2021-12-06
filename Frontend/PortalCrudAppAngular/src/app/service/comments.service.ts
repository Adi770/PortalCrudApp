import { Observable } from 'rxjs';
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

  getCommentById(id: number): Observable<CommentDTO> {
    return this.http.get<CommentDTO>(this.baseUrlComments + '/' + id)
  }

  getCommentByNewsId(idNews: number, size: number, page: number): Observable<Array<CommentDTO>> {
    // let params = new HttpParams();
    // params.append('page', page.toString());
    // params.append('size', size.toString());
    const paramsObj = {
      page: page.toString() || undefined,
      size: size.toString() || undefined
    };
    const params = new HttpParams({ fromObject: paramsObj });

    
    return this.http.get<Array<CommentDTO>>(this.baseUrl + 'news/' + idNews + '/comments', { params })
  }

  createComment(idNews: number, comment: CommentDTO) {
    return this.http.post(this.baseUrl + 'news/' + idNews + '/comments', comment)
  }

  updateComment(id: number, comment: CommentDTO) {
    return this.http.put(this.baseUrlComments + '/' + id, comment)
  }
}
