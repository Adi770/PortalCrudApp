import { CommentsService } from './../../service/comments.service';
import { Component, OnInit } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor(private commentsService: CommentsService) { }
  items = [1, 2, 3, 4, 5, 6, 7, 0];

  ngOnInit(): void {
  }

  loadCommnet(id: number, page: number, size: number) {

    this.commentsService.getCommentByNewsId(id, size, page).pipe(catchError(err => {
      console.log('error with comments')
      return throwError(err);
    }));
  }

}
