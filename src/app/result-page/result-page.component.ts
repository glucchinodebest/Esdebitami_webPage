import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-result-page',
  templateUrl: './result-page.component.html',
  styleUrls: ['./result-page.component.css']
})
export class ResultPageComponent implements OnInit {
  


  payload : any
  schedaCliente :any
  segmentazioneClienti:any

  constructor() {
    this.payload = JSON.parse(localStorage.getItem('valutazioneCliente'));
    this.schedaCliente = this.payload.schedaCliente;
    this.segmentazioneClienti = this.payload.segmentazioneClienti;
   }

   
  ngOnInit(): void {
    console.log(this.segmentazioneClienti);
  }


  
  
}
