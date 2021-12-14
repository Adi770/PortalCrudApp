import { Observable } from 'rxjs';
import { CommentResponseDTO, CommentDTO } from './news.interface';
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

  getCommentById(id: number): Observable<CommentResponseDTO> {
    return this.http.get<CommentResponseDTO>(this.baseUrlComments + '/' + id)
  }

  getCommentByNewsId(idNews: number, size: number, page: number): Observable<CommentResponseDTO[]> {
    // const paramsObj = {
    //   page: page.toString() || undefined,
    //   size: size.toString() || undefined
    // };
    // const params = new HttpParams({ fromObject: paramsObj });
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<CommentResponseDTO[]>(this.baseUrl + '/news/' + idNews + '/comments', {params: params})
  }

  createComment(idNews: number, comment: CommentDTO) {
    return this.http.post(this.baseUrl + '/news/' + idNews + '/comments', comment)
  }

  updateComment(id: number, comment: CommentDTO) {
    return this.http.put(this.baseUrlComments + '/' + id, comment)
  }
}
