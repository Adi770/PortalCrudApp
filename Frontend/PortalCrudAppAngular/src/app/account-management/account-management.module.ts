import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountManagementRoutingModule } from './account-management-routing.module';
import { AccountManagementComponent } from './account-management.component';
import { UserComponent } from "./user/user.component";
import { AdminComponent } from './admin/admin.component';
import { ModeratorComponent } from './moderator/moderator.component';


@NgModule({
  declarations: [AccountManagementComponent, UserComponent, AdminComponent, ModeratorComponent],
  imports: [
    CommonModule,
    AccountManagementRoutingModule
  ]
})
export class AccountManagementModule { }
