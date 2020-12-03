import { Component } from '@angular/core';
import { FormControl } from '@angular/forms'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'esdebitami';
  email = new FormControl('');  
  
  updateEmail() {  
    this.email.setValue('sonoojaiswal@javatpoint.com');  
  }  
}
