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
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
  }

  loginForm = this.formBuilder.group({
    username: '',
    password: ''
  });



  login() {
    
    const user: UsernameAndPassword = {
      password: this.loginForm.get(['username']).value,
      username: this.loginForm.get(['password']).value
    }
    console.log(this.loginForm.value)
    console.log(this.loginForm.get(['username']).value)
    this.accountService.getToken(user)

  }

  role() {
    this.accountService.currentRole();
  }

  data() {
    console.log(sessionStorage.getItem('token'))
    console.log(sessionStorage.getItem('role'))
  }
}
