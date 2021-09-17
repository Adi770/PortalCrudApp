import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsRoutingModule } from './news-routing.module';
import { NewsComponent } from './news.component';
import { NewsBoxComponent } from './news-box/news-box.component';
import { EditComponent } from './edit/edit.component';
import { AddComponent } from './add/add.component';
import { DeleteComponent } from './delete/delete.component';
import { NewsDetailsComponent } from './news-details/news-details.component';
import { CommentsComponent } from './comments/comments.component';


@NgModule({
  declarations: [NewsComponent, NewsBoxComponent, EditComponent, AddComponent, DeleteComponent, NewsDetailsComponent, CommentsComponent],
  imports: [
    CommonModule,
    NewsRoutingModule
  ]
})
export class NewsModule { }
