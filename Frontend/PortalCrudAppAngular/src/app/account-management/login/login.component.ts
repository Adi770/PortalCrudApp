import { UsernameAndPassword } from './../../service/account.interface';
import { AccountService } from './../../service/account.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
  }

  login() {
    const user: UsernameAndPassword = {
      password: 'admin',
      username: 'admin'
    }
    console.log(user)
    this.accountService.getToken(user)

  }

  role(){
    this.accountService.currentRole();
  }

}
