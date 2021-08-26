import { UsernameAndPassword } from './account.interface';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }

  baseUrl = environment.baseAddress;
  basicApiUrl = environment.baseApiUrl;
  accountManagementUrl = this.basicApiUrl + '/AccountManagement';


  getToken(user: UsernameAndPassword) {
    console.log('get user token')
    return this.httpClient.post<any>(this.baseUrl+'/login', user, { observe: 'response' }).subscribe(
      res => {
        console.log(res.status)
        console.log(res.body)
        console.log(res.headers.get('Authorization'))
      })
  }

  logout(): void {
    // sessionStorage.removeItem()
  }

  currentRole() {
    return this.httpClient.get(this.accountManagementUrl+'/role').subscribe(res => {
      console.log('works')
    })
  }
}
