import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EsdebitamiDashboardComponent } from './esdebitami-dashboard/esdebitami-dashboard.component';
import { LoginPageComponent } from './login-page/login-page.component';

const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'dashBoard', component: EsdebitamiDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
