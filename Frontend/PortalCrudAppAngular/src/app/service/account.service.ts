import { UsernameAndPassword, RecoveryMessage } from './account.interface';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }

  baseUrl = environment.baseAddress;
  basicApiUrl = environment.baseApiUrl;
  accountManagementUrl = this.basicApiUrl + '/AccountManagement';
  defualtValue = null;

  getToken(user: UsernameAndPassword) {
    sessionStorage.setItem('token', this.defualtValue)
    return this.httpClient.post<any>(this.baseUrl + '/login', user, { observe: 'response' }).subscribe(
      res => {
        let token = res.headers.get('Authorization');
        sessionStorage.setItem('token', token);
        this.currentRole();
      });
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
  }

  currentRole() {
    sessionStorage.setItem('role', this.defualtValue)
    return this.httpClient.get<string>(this.accountManagementUrl + '/role').subscribe(res => {
      console.log('current user role:' + res);
      sessionStorage.setItem('role', res);
    });
  }

  recorveryAccount(recoveryMessage: RecoveryMessage) {
    return this.httpClient.post(this.accountManagementUrl + '/password', recoveryMessage)

  }
}
