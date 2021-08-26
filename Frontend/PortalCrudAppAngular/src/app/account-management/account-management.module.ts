import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountManagementRoutingModule } from './account-management-routing.module';
import { AccountManagementComponent } from './account-management.component';
import { UserComponent } from "./user/user.component";
import { AdminComponent } from './admin/admin.component';
import { ModeratorComponent } from './moderator/moderator.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [AccountManagementComponent, UserComponent, AdminComponent, ModeratorComponent, LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    AccountManagementRoutingModule
  ],
  exports:[
    AccountManagementComponent
  ]
})
export class AccountManagementModule { }
