import { ModeratorComponent } from './account-management/moderator/moderator.component';
import { UserComponent } from './account-management/user/user.component';
import { AdminComponent } from './account-management/admin/admin.component';
import { AccountManagementComponent } from './account-management/account-management.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'account-management', 
  loadChildren: () => import('./account-management/account-management.module').then(m => m.AccountManagementModule) }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const componentsRountig=[
  AccountManagementComponent
];