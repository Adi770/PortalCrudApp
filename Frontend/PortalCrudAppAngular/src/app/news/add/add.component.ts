import { FormBuilder } from '@angular/forms';
import { NewsDTO } from './../../service/news.interface';
import { NewsService } from './../../service/news.service';
import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(private newsService: NewsService,
    private formBuilder: FormBuilder) { }

  imageList: FileList;
  previewsImageList: string[];
  previewsImageListName: string[];
  public Editor = ClassicEditor;
  nameImage: string;
  testImageList: string;
  edtiorData: string;
  articleTitle: string;

  articleFormGroup=this.formBuilder.group({
    title:'',
    article:''
  })

  public config = {
    language: 'pl'
  };

  ngOnInit(): void {
    this.edtiorData = '';
  }

  onFileSelected(event: any) {
    for (var i = 0; i < event.target.files.length; i++) {
      let reader = new FileReader();
      this.imageList = event.target.files[i];
      this.previewsImageList = [];
      reader.onload = (e: any) => {
        console.log("reader ")
        this.previewsImageList.push(e.target.result)
      }
      reader.readAsDataURL(event.target.files[i]);
      this.uploadImage(event.target.files[i])
    }
  }

  uploadImage(file: File) {
    if (file) {

    }

  }

  uploadFile(news: NewsDTO, image) {
    const data = new FormData();
    for (var i = 0; i < this.imageList.length; i++) {
      data.append('file', this.imageList.item[i])
    }

    data.append('news', JSON.stringify(news));

    this.newsService.createNews(data);
  }

  save(){
    console.log("test "+this.articleFormGroup.get(['title']).value)
    console.log(this.articleFormGroup.get(['article']).value)

  }

}
