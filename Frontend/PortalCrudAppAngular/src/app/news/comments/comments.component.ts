import { FormBuilder } from '@angular/forms';
import { CommentResponseDTO, CommentDTO } from './../../service/news.interface';
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

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private commentsService: CommentsService) { }
  
  
  items: CommentResponseDTO[];

  newCommentFormGroup = this.formBuilder.group({
    comment: ''
  });
  id: number;

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.url[0].path);
    this.loadCommnet(this.id, 0, 10);
  }

  loadCommnet(id: number, page: number, size: number) {
    return this.commentsService.getCommentByNewsId(id, size, page).subscribe(res => this.items = res)
  }

  currentUser() {
    return sessionStorage.getItem('username')
  }

  createComment() {
    let commentDTO: CommentDTO = {
      commentText: this.newCommentFormGroup.get(['comment']).value
    }
    this.commentsService.createComment(this.id, commentDTO).subscribe(res => console.log('added comment'))
    return this.loadCommnet(this.id, 0, 10);
  }

}
