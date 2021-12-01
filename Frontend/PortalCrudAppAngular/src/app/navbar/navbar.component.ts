import { Router } from '@angular/router';
import { DeleteComponent } from './../news/delete/delete.component';
import { EditComponent } from './../news/edit/edit.component';
import { AccountService } from './../service/account.service';
import { RegisterComponent } from './../account-management/register/register.component';
import { LoginComponent } from './../account-management/login/login.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
    private accountService: AccountService,
    private router: Router) {

  }

  currentUserName: String;

  ngOnInit(): void {
  }

  userIsLogin(): boolean {
    let token = sessionStorage.getItem('token');
    this.currentUserName= sessionStorage.getItem('username');
    if (token != 'null' && token != null) {
      return true;
    } else {
      return false;
    }
  }

  openEditDialog() {
    this.dialog.closeAll;
    this.dialog.open(EditComponent, {
      width: '250px'
    })
  }


  openLoginDialog() {
    this.dialog.closeAll;
    this.dialog.open(LoginComponent, {
      width: '250px',
    })
  }

  openRegisterDialog() {
    this.dialog.closeAll;
    this.dialog.open(RegisterComponent, {
      width: '250px',
    })
  }

  logout() {
    this.accountService.logout()

  }

  openDeleteDialog() {
    this.dialog.closeAll;
    let dialogDelete = this.dialog.open(DeleteComponent, {
      width: '250px'
    })
    dialogDelete.afterClosed().subscribe(
      res => {
        console.log(this.router.url)
        this.router.navigateByUrl('/news?d=0')
      }
    )
  }

}
