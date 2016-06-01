// importables
import { bootstrap } from '@angular/platform-browser-dynamic';
// import { enableProdMode } from '@angular/core';
import { AppComponent } from './components/app/app';
import {OauthService} from "./services/oauth-service";

// bootstrap & to production
// enableProdMode();
bootstrap(AppComponent, [OauthService]);
