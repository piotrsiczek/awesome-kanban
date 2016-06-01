import {Injectable} from '@angular/core';

@Injectable()
export class OauthService {
  private auth:boolean;

  constructor() {
    this.auth = false;
  }


  isAuth() {
    return this.auth;
  }
}
