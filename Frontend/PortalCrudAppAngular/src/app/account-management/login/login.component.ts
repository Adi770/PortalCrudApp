import { ForgottenPasswordComponent } from './../forgotten-password/forgotten-password.component';
import { MatDialog } from '@angular/material/dialog';
import { UsernameAndPassword } from './../../service/account.interface';
import { AccountService } from './../../service/account.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  loginForm = this.formBuilder.group({
    username: '',
    password: ''
  });

  isValid = false;


  login() {

    const user: UsernameAndPassword = {
      password: this.loginForm.get(['username']).value,
      username: this.loginForm.get(['password']).value
    }
    this.accountService.getToken(user)
  }

  role() {
    this.accountService.currentRole();
  }

  data() {
    console.log(sessionStorage.getItem('role'))
  }

  resetPassword() {

    this.dialog.closeAll;
    this.dialog.open(ForgottenPasswordComponent, {
      width: '250px',
    })
  }
}
