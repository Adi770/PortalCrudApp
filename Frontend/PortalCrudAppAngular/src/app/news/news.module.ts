import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsRoutingModule } from './news-routing.module';
import { NewsComponent } from './news.component';
import { NewsBoxComponent } from './news-box/news-box.component';


@NgModule({
  declarations: [NewsComponent, NewsBoxComponent],
  imports: [
    CommonModule,
    NewsRoutingModule
  ]
})
export class NewsModule { }
