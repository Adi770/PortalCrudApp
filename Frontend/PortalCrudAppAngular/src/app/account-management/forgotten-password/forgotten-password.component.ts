import { Router } from '@angular/router';
import { environment } from './../../../environments/environment';
import { RecoveryMessage } from './../../service/account.interface';
import { AccountService } from './../../service/account.service';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forgotten-password',
  templateUrl: './forgotten-password.component.html',
  styleUrls: ['./forgotten-password.component.css']
})
export class ForgottenPasswordComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private accountService: AccountService,
    private router: Router) { }

  ngOnInit(): void {
  }


  emailForm = this.formBuilder.group({
    email: ''
  })


  recorvery() {
    const recoveryMessage: RecoveryMessage = {
      title: 'Recovery Message',
      baseUrl: environment.baseUrl,
      message: 'reset password link:',
      userEmail: this.emailForm.get(['email']).value
    }
    console.log(recoveryMessage)
    this.accountService.recorveryAccount(recoveryMessage).subscribe(
      res=> {
        console.log('send reset link')
      },
      err=>{
        console.log('problem with recovery message')
        console.log(err)
      },
      ()=>{
        console.log('request done')
      }
    );

  }

}
