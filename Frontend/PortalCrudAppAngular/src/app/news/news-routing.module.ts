import { NewsBoxComponent } from './news-box/news-box.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewsComponent } from './news.component';

const routes: Routes = [{ path: '', component: NewsComponent },
{ path: ':newsId', component: NewsBoxComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NewsRoutingModule { }
