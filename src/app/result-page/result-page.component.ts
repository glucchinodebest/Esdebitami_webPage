import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-result-page',
  templateUrl: './result-page.component.html',
  styleUrls: ['./result-page.component.css']
})
export class ResultPageComponent implements OnInit {
  


  headElementsSC = ['Riferimento - Scheda Cliente', 'Esito - Scheda Cliente'];
  headElementsSeg = ['Riferimento - Segmentazione Cliente', 'Esito - Segmentazione Cliente'];

  payload : any
  schedaCliente :any
  segmentazioneClienti:any
  elementsSegmentazione: any
  elementsSchedaCliente: any

  constructor() {
    this.payload = JSON.parse(localStorage.getItem('valutazioneCliente'));
    
    
    if(this.payload!=null){
      this.schedaCliente = this.payload.schedaCliente;
      this.segmentazioneClienti = this.payload.segmentazioneClienti;
    
    console.log(this.payload);
    
    
      this.elementsSegmentazione = [

        {idSeg: 'Reddito familiare complessivo', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.redditoFamiliareComplessivo == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.redditoFamiliareComplessivo )},
        {idSeg: 'Totale mensile affitti', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAffitti  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAffitti )},
        {idSeg: 'Totale mensile uscite affitti', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAffittiUscite  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAffittiUscite )},
        {idSeg: 'Totale mensile altre entrate', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAltreEntrate  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAltreEntrate )},
        {idSeg: 'Totale mensile altre entrate da locazione immobili', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAltreEntrateDaLocazioneImmobili  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileAltreEntrateDaLocazioneImmobili )},
        {idSeg: 'Totale mensile uscite obbligatorie', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileUsciteObbligatorie  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleMensileUsciteObbligatorie )},
        {idSeg: 'Totale rate mensili mutuo', firstSeg :  (this.segmentazioneClienti.datiEconomici.entrateUscite.totaleRateMensiliMutuo  == null ? "-" : this.segmentazioneClienti.datiEconomici.entrateUscite.totaleRateMensiliMutuo )},

        {idSeg: 'Totale redditi Altri familiari', firstSeg :  (this.segmentazioneClienti.datiEconomici.totaleRedditiAltriFamiliari == null ? "-" : this.segmentazioneClienti.datiEconomici.totaleRedditiAltriFamiliari)},
        {idSeg: 'Totale redditi mensili familiari', firstSeg :  (this.segmentazioneClienti.datiEconomici.totaleRedditiMensiliFamiliari == null ? "-" : this.segmentazioneClienti.datiEconomici.totaleRedditiMensiliFamiliari)},




        /*{idSeg: 'Impegni Detrimento Reddito',firstSeg : (this.segmentazioneClienti.datiEconomici.impegniDetrimentoReddito == null ? "-" : this.segmentazioneClienti.datiEconomici.impegniDetrimentoReddito ) },
        {idSeg: 'Disponibilità Stimata', firstSeg :  (this.segmentazioneClienti.datiEconomici.disponibilitaStimata == null ? "-" : this.segmentazioneClienti.datiEconomici.disponibilitaStimata)},
        {idSeg: 'Rapporto Montante Disponibilita Stimata', firstSeg: (this.segmentazioneClienti.datiEconomici.rapportoMontanteDisponibilitaStimata == null ? "-" : this.segmentazioneClienti.datiEconomici.rapportoMontanteDisponibilitaStimata)},
        {idSeg: 'Profilo di Rischio', firstSeg: (this.segmentazioneClienti.datiEconomici.profiloRischio == null ? "-" : this.segmentazioneClienti.datiEconomici.profiloRischio)},
      
        {idSeg: 'Mese Inizio Piano Competenze Upfront', firstSeg: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.meseInizioPianoCompetenzeUpfront)},
        {idSeg: 'Durata Piano Competenze Upfront', firstSeg: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.durataPianoCompetenzeUpfront)},
        {idSeg: 'Importo Rata Piano Competenze Upfront', firstSeg: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.importoRataPianoCompetenzeUpfront)},
        {idSeg: 'Numero Rate Piano Competenze Upfront', firstSeg: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.numeroRatePianoCompetenzeUpfront)},
        {idSeg: 'Totale Piano Competenze Upfront', firstSeg: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.totalePianoCompetenzeUpfront)},
        
        {idSeg: 'Success Fee Globale', firstSeg: (this.segmentazioneClienti.upfrontSegmentazione == null ? "-" : this.segmentazioneClienti.upfrontSegmentazione.successFeeGlobale)},*/
      ];

      this.elementsSchedaCliente = [
        {id: 'Codice fiscale', first: (this.schedaCliente.anagraficaCliente.cf == null ? "-" : this.schedaCliente.anagraficaCliente.cf)},
        {id: 'Classe di Domicilio', first: (this.schedaCliente.anagraficaCliente.classeDomicilio == null ? "-" : this.schedaCliente.anagraficaCliente.classeDomicilio)},

        {id: 'Nucleo Familiare', first: (this.schedaCliente.datiEconomici.clienteDatiEconomici.nucleoFamiliare == null ? "-" :this.schedaCliente.datiEconomici.clienteDatiEconomici.nucleoFamiliare)},
        {id: 'Numero familiari a carico', first: (this.schedaCliente.datiEconomici.clienteDatiEconomici.nFamiliariCarico == null ? "-" : this.schedaCliente.datiEconomici.clienteDatiEconomici.nFamiliariCarico)},
        {id: 'Numero figli conviventi', first: (this.schedaCliente.datiEconomici.clienteDatiEconomici.nFigliConviventi == null ? "-" : this.schedaCliente.datiEconomici.clienteDatiEconomici.nFigliConviventi)},
        {id: 'Di cui minori', first: (this.schedaCliente.datiEconomici.clienteDatiEconomici.nFigliConviventiMinori == null ? "-" : this.schedaCliente.datiEconomici.clienteDatiEconomici.nFigliConviventiMinori)},
        {id: 'Regime Patrimoniale', first: (this.schedaCliente.datiEconomici.clienteDatiEconomici.regimePatrimoniale == null ? "-" : this.schedaCliente.datiEconomici.clienteDatiEconomici.regimePatrimoniale)},

        {id: 'Reddito Familiare Complessivo', first: (this.schedaCliente.datiEconomici.entrateUscite.redditoFamiliareComplessivo == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.redditoFamiliareComplessivo)},
        {id: 'Totale mensile affitti', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAffitti == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAffitti)},
        {id: 'Totale mensile uscite affitti', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAffittiUscite == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAffittiUscite)},
        {id: 'Totale mensile altre entrate', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAltreEntrate == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAltreEntrate)},
        {id: 'Totale mensile altre entrate da locazione immobili', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAltreEntrateDaLocazioneImmobili == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleMensileAltreEntrateDaLocazioneImmobili)},
        {id: 'Totale mensile uscite obbligatorie', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleMensileUsciteObbligatorie == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleMensileUsciteObbligatorie)},
        {id: 'Totale rata mensile mutuo', first: (this.schedaCliente.datiEconomici.entrateUscite.totaleRateMensiliMutuo == null ? "-" : this.schedaCliente.datiEconomici.entrateUscite.totaleRateMensiliMutuo)},

        {id: 'Totale reddito altri familiari', first: (this.schedaCliente.datiEconomici.totaleRedditiAltriFamiliari == null ? "-" : this.schedaCliente.datiEconomici.totaleRedditiAltriFamiliari)},
        {id: 'Totale totale reddito mensile altri familiari', first: (this.schedaCliente.datiEconomici.totaleRedditiMensiliFamiliari == null ? "-" : this.schedaCliente.datiEconomici.totaleRedditiMensiliFamiliari)},



        
        /*{id: 'Reddito mensile rettificato cliente sub', first: (this.schedaCliente.mensileRettificatoCliSub == null ? "-" : this.schedaCliente.mensileRettificatoCliSub)},
        {id: 'Reddito mensile rettificato cliente aut', first: (this.schedaCliente.mensileRettificatoCliAut == null ? "-" : this.schedaCliente.mensileRettificatoCliAut)},
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
        {id: 'Riepilogo Creditori', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori)},

        {id: 'Montante debitorio Unsecured', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioUnsecured)},
        {id: 'Montante debitorio secured', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioSecured)},
        {id: 'Montante debitorio complessivo', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.montanteDebitorioComplessivo)},
        {id: 'Totale rate creditore', first: (this.schedaCliente.riepilogoCreditori == null ? "-" : this.schedaCliente.riepilogoCreditori.totaleRateCreditore)},

        {id: 'Mese Inizio Piano Competenze Upfront', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.meseInizioPianoCompetenzeUpfront)},
        {id: 'Durata Piano Competenze Upfront', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.durataPianoCompetenzeUpfront)},
        {id: 'Importo Rata Piano Competenze Upfront', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.importoRataPianoCompetenzeUpfront)},
        {id: 'Numero Rate Piano Competenze Upfront', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.numeroRatePianoCompetenzeUpfront)},
        {id: 'Totale Piano Competenze Upfront', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.totalePianoCompetenzeUpfront)},
        {id: 'Success Fee Globale', first: (this.schedaCliente.pianoCompetenzeUpfront == null ? "-" : this.schedaCliente.pianoCompetenzeUpfront.successFeeGlobale)}*/

      ];
    }else{
      this.elementsSegmentazione = [
        {idSeg: 'Impegni Detrimento Reddito',firstSeg : "-" },
        {idSeg: 'Disponibilità Stimata', firstSeg :  "-"},
        {idSeg: 'Rapporto Montante Disponibilita Stimata', firstSeg: "-"},
        {idSeg: 'Profilo di Rischio', firstSeg: "-"},
      
        {idSeg: 'Mese Inizio Piano Competenze Upfront', firstSeg: "-"},
        {idSeg: 'Durata Piano Competenze Upfront', firstSeg: "-"},
        {idSeg: 'Importo Rata Piano Competenze Upfront', firstSeg: "-"},
        {idSeg: 'Numero Rate Piano Competenze Upfront', firstSeg: "-"},
        {idSeg: 'Totale Piano Competenze Upfront', firstSeg: "-"},
        
        {idSeg: 'Success Fee Globale', firstSeg: "-"},
      ];

      this.elementsSchedaCliente = [
        {id: 'Codice fiscale', first: "-"},
        {id: 'Classe di Domicilio', first: "-"},
        {id: 'Nucleo Familiare', first: "-"},
        {id: 'Numero familiari a carico', first: "-"},
        {id: 'Numero figli conviventi', first: "-"},
        {id: 'Di cui minori', first: "-"},
        {id: 'Reddito mensile rettificato cliente sub', first:"-"},
        {id: 'Reddito mensile rettificato cliente aut', first: "-"},
        {id: 'Importo residuo delega cliente', first: "-"},
        {id: 'Importo residuo CSQ cliente', first: "-"},
        {id: 'Importo residuo Pignoramento cliente', first: "-"},
        {id: 'Data delega cliente', first: "-"  },
        {id: 'Data CSQ cliente', first: "-"},
        {id: 'Data Pignoramento cliente', first: "-"},
        {id: 'Reddito Mensile rettificato Coniuge sub', first: "-"},
        {id: 'Reddito Mensile rettificato Coniuge aut', first: "-"},
        {id: 'Importo residuo delega Coniuge', first: "-"},
        {id: 'Importo residuo CSQ Coniuge', first: "-"},
        {id: 'Importo residuo Pignoramento Coniuge', first: "-"},
        {id: 'Data delega coniuge',first: "-"},
        {id: 'Data CSQ coniuge', first : "-"},
        {id: 'Data pignoramento coniuge', first : "-"},
        {id: 'Totale mensile affitti', first: "-"},
        {id: 'Totale mensile uscite obbligatorie', first: "-"},
        {id: 'Totale mensile uscite affitti', first: "-"},
        {id: 'Totale mensile altre entrate', first: "-"},
        {id: 'Reddito familiare complessivo', first: "-"},
        {id: 'Reddito mensile altri familiari', first: "-"},
        {id: 'Rata Mensile mutuo', first: "-"},
        {id: 'Rendita da locazione', first: "-"},

        {id: 'Montante debitorio Unsecured', first: "-"},
        {id: 'Montante debitorio secured', first: "-"},
        {id: 'Montante debitorio complessivo', first: "-"},
        {id: 'Totale rate creditore', first: "-"},


      ];
    }
   }


   
  ngOnInit(): void {
  }


  
  
}
