import { Component } from '@angular/core';
import { Router, Routes, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router';
import {LoginComponent} from './login';
import {OauthService} from "../../services/oauth-service";
import {OnInit} from "../../../../../web2/node_modules/angular2/src/core/linker/interfaces";
// import {AuxRoute} from '@angular/src/router/route_config_impl';

@Component( {
  directives: [ROUTER_DIRECTIVES],
  providers: [
    ROUTER_PROVIDERS
  ],
  selector : 'app',
  templateUrl : 'src/app/templates/app.html'
} )
@Routes([
  // new AuxRoute({aux: 'test', path: '/login', aux: 'login-outlet', component: LoginComponent})
])
export class AppComponent implements OnInit {

  public template = 'asdf';

  ngOnInit() {
    console.log('ngOnInit');
  }

  constructor(private router: Router, private securityService: OauthService) {
    console.log('construct');
    this.template = 'src/app/templates/app.html';


    // if (!securityService.isAuth()) {
    //   this.router.navigate(['/login']);
    // }

  }
}
