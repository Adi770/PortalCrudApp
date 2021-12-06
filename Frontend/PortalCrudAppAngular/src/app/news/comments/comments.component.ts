import { CommentsService } from './../../service/comments.service';
import { Component, OnInit } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private commentsService: CommentsService) { }
  items = null;

  ngOnInit(): void {
    let id = Number(this.activatedRoute.snapshot.url[0].path);
    this.items = this.loadCommnet(id, 0, 10);
  }

  loadCommnet(id: number, page: number, size: number) {
    console.log("load comments")
    
    this.commentsService.getCommentByNewsId(id, size, page).subscribe(res => console.log(res))
  
    return this.commentsService.getCommentByNewsId(id, size, page).pipe(catchError(err => {
      console.log('error with comments')
      return throwError(err);
    }));

  }

}
