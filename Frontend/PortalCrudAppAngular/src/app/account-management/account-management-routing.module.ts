import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountManagementComponent } from './account-management.component';

const routes: Routes = [
  { path: '', component: AccountManagementComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'moderator', component: AdminComponent },
  { path: 'user', component: AdminComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountManagementRoutingModule { }
