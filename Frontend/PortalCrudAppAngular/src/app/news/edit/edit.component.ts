import { FormBuilder } from '@angular/forms';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  constructor(
    private formBuilder:FormBuilder,
    private router:Router
  ) { }

  idForm = this.formBuilder.group({
    idNews: ''
  });

  ngOnInit(): void {
  }

  edit(){
    let id ={
      idNews: this.idForm.get(['idNews']).value
    }
    this.router.navigate(['/news/edit/'+id.idNews])
    console.log(id.idNews)
  }

}
