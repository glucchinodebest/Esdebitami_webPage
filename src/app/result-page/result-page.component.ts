import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-result-page',
  templateUrl: './result-page.component.html',
  styleUrls: ['./result-page.component.css']
})
export class ResultPageComponent implements OnInit {
  


  headElements = ['Riferimento', 'Esito'];

  payload : any
  schedaCliente :any
  segmentazioneClienti:any
  elements: any

  constructor() {
    this.payload = JSON.parse(localStorage.getItem('valutazioneCliente'));
    this.schedaCliente = this.payload.schedaCliente;
    this.segmentazioneClienti = this.payload.segmentazioneClienti;


    this.elements = [
      {id: 'Impegni Detrimento Reddito',first : (this.segmentazioneClienti.impegniDetrimentoReddito == null ? "-" : this.segmentazioneClienti.impegniDetrimentoReddito ) },
      {id: 'Disponibilità Stimata', first :  (this.segmentazioneClienti.disponibilitaStimata == null ? "-" : this.segmentazioneClienti.disponibilitaStimata)},
      {id: 'Rapporto Montante Disponibilita Stimata', first: (this.segmentazioneClienti.rapportoMontanteDisponibilitaStimata == null ? "-" : this.segmentazioneClienti.rapportoMontanteDisponibilitaStimata)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},

      {id: 'Codice fiscale', first: (this.schedaCliente.cf == null ? "-" : this.schedaCliente.cf)},
      {id: 'Classe di Domicilio', first: (this.schedaCliente.classeDomicilio == null ? "-" : this.schedaCliente.classeDomicilio)},
      {id: 'Nucleo Familiare', first: (this.schedaCliente.nucleoFamiliare == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Numero familiari a carico', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Numero figli conviventi', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Di cui minori', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Reddito mensile rettificato cliente sub', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Reddito mensile rettificato cliente sub', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo delega cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo CSQ cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo Pignoramento cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data delega cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data CSQ cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data Pignoramento cliente', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Reddito Mensile rettificato Coniuge sub', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Reddito Mensile rettificato Coniuge aut', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo delega Coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo CSQ Coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Importo residuo Pignoramento Coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data delega coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data CSQ coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Data pignoramento coniuge', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Totale mensile affitti', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Totale mensile uscite obbligatorie', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Totale mensile uscite affitti', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Profilo di Rischio', first: (this.segmentazioneClienti.profiloRischio == null ? "-" : this.segmentazioneClienti.profiloRischio)},




    ];
   }


   
  ngOnInit(): void {
    console.log(this.segmentazioneClienti);
  }


  
  
}
