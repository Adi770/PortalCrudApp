import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor() { }
  items = [1, 2, 3, 4, 5, 6, 7, 0];
  
  ngOnInit(): void {
  }

}
