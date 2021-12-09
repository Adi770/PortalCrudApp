import { CommentDTO } from './../../service/news.interface';
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
  items: CommentDTO[];


  ngOnInit(): void {
    let id = Number(this.activatedRoute.snapshot.url[0].path);
    this.loadCommnet(id, 0, 10);
//    this.currentUser=sessionStorage.getItem('username');
  }

  loadCommnet(id: number, page: number, size: number) {
    return this.commentsService.getCommentByNewsId(id, size, page).subscribe(res=>this.items=res)
  }
  
  currentUser(){
    return sessionStorage.getItem('username')
  }

}
