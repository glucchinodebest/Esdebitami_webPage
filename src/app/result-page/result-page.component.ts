import { DatePipe } from '@angular/common';
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
      {id: 'Nucleo Familiare', first: (this.schedaCliente.nucleoFamiliare == null ? "-" :this.schedaCliente.nucleoFamiliare)},
      {id: 'Numero familiari a carico', first: (this.schedaCliente.numFamiliariCarico == null ? "-" : this.schedaCliente.numFamiliariCarico)},
      {id: 'Numero figli conviventi', first: (this.schedaCliente.numFigliConviventi == null ? "-" : this.schedaCliente.numFigliConviventi)},
      {id: 'Di cui minori', first: (this.schedaCliente.numFigliConviventiMinori == null ? "-" : this.schedaCliente.numFigliConviventiMinori)},
      {id: 'Reddito mensile rettificato cliente sub', first: (this.schedaCliente.mensileRettificatoCliSub == null ? "-" : this.schedaCliente.mensileRettificatoCliSub)},
      {id: 'Reddito mensile rettificato cliente sub', first: (this.schedaCliente.mensileRettificatoCliAut == null ? "-" : this.schedaCliente.mensileRettificatoCliAut)},
      {id: 'Importo residuo delega cliente', first: (this.schedaCliente.importoResiduoDelegaCliente == null ? "-" : this.schedaCliente.importoResiduoDelegaCliente)},
      {id: 'Importo residuo CSQ cliente', first: (this.schedaCliente.importoResiduoCsqCliente == null ? "-" : this.schedaCliente.importoResiduoCsqCliente)},
      {id: 'Importo residuo Pignoramento cliente', first: (this.schedaCliente.importoResiduoPignoramentoCliente == null ? "-" : this.schedaCliente.importoResiduoPignoramentoCliente)},
      {id: 'Data delega cliente', first: (this.schedaCliente.dataDelegaCliente == null ? "-" :  new Date( this.schedaCliente.dataDelegaCliente).toLocaleDateString())  },
      {id: 'Data CSQ cliente', first: (this.schedaCliente.dataCsqCliente == null ? "-" : new Date(this.schedaCliente.dataCsqCliente).toLocaleDateString())},
      {id: 'Data Pignoramento cliente', first: (this.schedaCliente.dataPignoramentoCliente == null ? "-" : new Date(this.schedaCliente.dataPignoramentoCliente).toLocaleDateString())},
      {id: 'Reddito Mensile rettificato Coniuge sub', first: (this.schedaCliente.mensileRettificatoConSub == null ? "-" : this.schedaCliente.mensileRettificatoConSub)},
      {id: 'Reddito Mensile rettificato Coniuge aut', first: (this.schedaCliente.mensileRettificatoConAut == null ? "-" : this.schedaCliente.mensileRettificatoConAut)},
      {id: 'Importo residuo delega Coniuge', first: (this.schedaCliente.importoResiduoDelegaConiuge == null ? "-" : this.schedaCliente.importoResiduoDelegaConiuge)},
      {id: 'Importo residuo CSQ Coniuge', first: (this.schedaCliente.importoResiduoCsqConiuge == null ? "-" : this.schedaCliente.importoResiduoCsqConiuge)},
      {id: 'Importo residuo Pignoramento Coniuge', first: (this.schedaCliente.importoResiduoPignoramentoConiuge == null ? "-" : this.schedaCliente.importoResiduoPignoramentoConiuge)},
      {id: 'Data delega coniuge', first: (this.schedaCliente.dataDelegaConiuge == null ? "-" : new Date(this.schedaCliente.dataDelegaConiuge).toLocaleDateString())},
      {id: 'Data CSQ coniuge', first: (this.schedaCliente.dataCsqConiuge == null ? "-" : new Date(this.schedaCliente.dataCsqConiuge).toLocaleDateString())},
      {id: 'Data pignoramento coniuge', first: (this.schedaCliente.dataPignoramentoConiuge == null ? "-" : new Date(this.schedaCliente.dataPignoramentoConiuge).toLocaleDateString())},
      {id: 'Totale mensile affitti', first: (this.schedaCliente.totaleMensileAffitti == null ? "-" : this.schedaCliente.totaleMensileAffitti)},
      {id: 'Totale mensile uscite obbligatorie', first: (this.schedaCliente.totaleMensileUsciteObbligatorie == null ? "-" : this.schedaCliente.totaleMensileUsciteObbligatorie)},
      {id: 'Totale mensile uscite affitti', first: (this.schedaCliente.totaleMensileAffittiUscite == null ? "-" : this.schedaCliente.totaleMensileAffittiUscite)},
      {id: 'Totale mensile altre entrate', first: (this.schedaCliente.totaleMensileAltreEntrate == null ? "-" : this.schedaCliente.totaleMensileAltreEntrate)},
      {id: 'Reddito familiare complessivo', first: (this.schedaCliente.redditoFamiliareComplessivo == null ? "-" : this.segmentazioneClienti.profiloRischio)},
      {id: 'Reddito mensile altri familiari', first: (this.schedaCliente.redditiMensiliAltriFamiliari == null ? "-" : this.schedaCliente.redditiMensiliAltriFamiliari)},
      {id: 'Rata Mensile mutuo', first: (this.schedaCliente.rataMensileMutuo == null ? "-" : this.schedaCliente.rataMensileMutuo)},
      {id: 'Rendita da locazione', first: (this.schedaCliente.renditaDaLocazione == null ? "-" : this.schedaCliente.renditaDaLocazione)},
      //{id: 'Riepilogo Creditori', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori)},

      {id: 'Montante debitorio Unsecured', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioUnsecured)},
      {id: 'Montante debitorio secured', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioSecured)},
      {id: 'Montante debitorio complessivo', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioComplessivo)},
      {id: 'Totale rate creditore', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.totaleRateCreditore)},



    ];
   }


   
  ngOnInit(): void {
    console.log(this.segmentazioneClienti);
  }


  
  
}
