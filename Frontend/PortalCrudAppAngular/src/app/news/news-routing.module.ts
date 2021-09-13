import { NewsDetailsComponent } from './news-details/news-details.component';
import { NewsBoxComponent } from './news-box/news-box.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewsComponent } from './news.component';

const routes: Routes = [
  { path: '', component: NewsBoxComponent },
  { path: ':newsId', component: NewsDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NewsRoutingModule { }
