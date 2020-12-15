import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, FormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(public router: Router) { }


  ngOnInit(): void {
  }


  public name:any;

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });



  onLogin() {
    if (this.loginForm.get('username').value === 'esdebitami' && this.loginForm.get('password').value === 'esdebitami') {
      this.router.navigate(['/dashBoard'])
    } else {
      this.name = "Credenziali errate";
    }
  }

}
