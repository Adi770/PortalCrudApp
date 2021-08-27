import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }
  registerForm = this.formBuilder.group({
    username: '',
    email: '',
    password: ''
  })

  register() {
    console.log(this.registerForm.value)
  }
}
