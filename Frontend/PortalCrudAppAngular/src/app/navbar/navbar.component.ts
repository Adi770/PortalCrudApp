import { RegisterComponent } from './../account-management/register/register.component';
import { LoginComponent } from './../account-management/login/login.component';
import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public dialog:MatDialog) { }

  ngOnInit(): void {
  }

  openLoginDialog(){
    this.dialog.closeAll;
    this.dialog.open(LoginComponent,{
      width:'250px',
      height:'250px'
    })
  }

  openRegisterDialog(){
    this.dialog.closeAll;
    this.dialog.open(RegisterComponent,{
      width:'250px'
    })
  }

}
