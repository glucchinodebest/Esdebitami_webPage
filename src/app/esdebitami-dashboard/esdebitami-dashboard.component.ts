import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, FormsModule, Validators } from '@angular/forms';
import { ApiRestService } from '../../servicesRest/api-rest.service'
import * as XLSX from 'xlsx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-esdebitami-dashboard',
  templateUrl: './esdebitami-dashboard.component.html',
  styleUrls: ['./esdebitami-dashboard.component.css']
})
export class EsdebitamiDashboardComponent implements OnInit {

  datasetBase64: String = '';
  uploadForm: FormGroup;

  convertDate(date : any){

      // JavaScript dates can be constructed by passing milliseconds
      // since the Unix epoch (January 1, 1970) example: new Date(12312512312);
    
      // 1. Subtract number of days between Jan 1, 1900 and Jan 1, 1970, plus 1 (Google "excel leap year bug")             
      // 2. Convert to milliseconds.
    
      return new Date((date - (25567 + 1))*86400*1000);
  }

  dataForm = new FormGroup({
    //anagrafica
    anagrafica_cf: new FormControl(''),
    anagrafica_classeDomicilio: new FormControl(''),
    //cliente
    cliente_nFamiliariCarico: new FormControl(''),
    cliente_nFigliConviventi: new FormControl(''),
    cliente_nFigliConviventiMinori: new FormControl(''),
    cliente_nucleoFamiliare: new FormControl(''),
    cliente_regimePatrimoniale: new FormControl(''),
    cliente_statoCivile: new FormControl(''),

    //coniuge
    coniuge_cf: new FormControl(''),

    //altreEntrate
    altreEntrate_cifraMensileAltreEntrate: new FormControl(''),
    altreEntrate_descrizioneAltreEntrate: new FormControl(''),
    altreEntrate_progressivoAltreEntrate: new FormControl(''),

    //altreUscite
    altreUscite_cifraMensileAltreUscite: new FormControl(''),
    altreUscite_descrizioneAltreUscite: new FormControl(''),
    altreUscite_progressivoAltreUscite: new FormControl(''),

    //assegniMantenimentoEntrata
    assegniMantEntrata_cifraMensileAssMantEntrata: new FormControl(''),
    assegniMantEntrata_descrizioneAssMantEntrata: new FormControl(''),
    assegniMantEntrata_progressivoAssMantEntrata: new FormControl(''),

    //assegniMantenimentoUscita
    assegniMantUscita_cifraMensileAssMantUscita: new FormControl(''),
    assegniMantUscita_descrizioneAssMantUscita: new FormControl(''),
    assegniMantUscita_progressivoAssMantUscita: new FormControl(''),

    //immobile
    immobile_bancaMutuante: new FormControl(''),
    immobile_dataDalProprietaImmobile: new FormControl(''),
    immobile_descrizioneImmobile: new FormControl(''),
    immobile_flPrimaCasa: new FormControl(''),
    immobile_localita: new FormControl(''),
    immobile_mqImmobile: new FormControl(''),
    immobile_mutuoOriginario: new FormControl(''),
    immobile_rataMutuoMensile: new FormControl(''),
    immobile_renditaLocazioneMensile: new FormControl(''),
    immobile_residuoDebitoMutuo: new FormControl(''),
    immobile_scadenzaRata: new FormControl(''),
    immobile_tipoImmobile: new FormControl(''),
    immobile_valoreCommerciale: new FormControl(''),

    //totaleUscitePerAffitti
    totaleUscitePerAffitti_rataAffittoMensile: new FormControl(''),
    totaleUscitePerAffitti_rataCondominioMensile: new FormControl(''),

    //altriFamiliari
    listaAltriFamiliari_cfAltriF: new FormControl(''),
    listaAltriFamiliari_cittaNascitaAltriF: new FormControl(''),
    listaAltriFamiliari_cognomeAltriF: new FormControl(''),
    listaAltriFamiliari_dataNascitaAltriF: new FormControl(''),
    listaAltriFamiliari_gradoParentelaAltriF: new FormControl(''),
    listaAltriFamiliari_nomeAltriF: new FormControl(''),
    listaAltriFamiliari_redditoMensileAltriF: new FormControl(''),

    //attività cliente Autonomo
    listaAttivitaClienteAut_attivitaCliAut: new FormControl(''),
    listaAttivitaClienteAut_redditoMensileRetCliAut:  new FormControl(''),
    listaAttivitaClienteAut_descrizioneAttivitaCliAut: new FormControl(''),
    listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut: new FormControl(''),
    listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut: new FormControl(''),
    listaAttivitaClienteAut_redditoAnnuoCliAut: new FormControl(''),
    listaAttivitaClienteAut_tipoLavoratoreCliAut: new FormControl(''),

    //attività cliente Dis
    listaAttivitaClienteDis_tipoLavoratoreCliDis: new FormControl(''),

    //attività cliente sub
    listaAttivitaClienteSub_attivitaCliSub: new FormControl(''),
    listaAttivitaClienteSub_numMensilitaRetCliSub: new FormControl(''),
    listaAttivitaClienteSub_redditoMensileCliSub: new FormControl(''),
    listaAttivitaClienteSub_redditoMensileRettificato: new FormControl(''),
    listaAttivitaClienteSub_tipoContrattoCliSub: new FormControl(''),
    listaAttivitaClienteSub_tipoLavoratoreCliSub: new FormControl(''),

    //attività Coniuge aut
    listaAttivitaConiugeAut_attivitaConAut: new FormControl(''),
    listaAttivitaConiugeAut_descrizioneAttivitaConAut: new FormControl(''),
    listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut: new FormControl(''),
    listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut: new FormControl(''),
    listaAttivitaConiugeAut_redditoAnnuoConAut: new FormControl(''),
    listaAttivitaConiugeAut_tipoLavoratoreConAut: new FormControl(''),

    //attività coniuge Dis
    listaAttivitaConiugeDis_tipoLavoratoreConDis: new FormControl(''),

    //attività coniuge sub
    listaAttivitaConiugeSub_attivitaConSub: new FormControl(''),
    listaAttivitaConiugeSub_numMensilitaRetConSub: new FormControl(''),
    listaAttivitaConiugeSub_redditoAnnuoConSub: new FormControl(''),
    listaAttivitaConiugeSub_tipoContrattoConSub: new FormControl(''),
    listaAttivitaConiugeSub_tipoLavoratoreConSub: new FormControl(''),

    // redditi familiari mensili
    redditiFamiliariMensili: new FormControl(''),

    //trattenuta busta paga CSQ cliente
    trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli: new FormControl(''),
    trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli: new FormControl(''),
    trattenutaBustaPagaCliente_csqCliente_rataCSQCli: new FormControl(''),

    //trattenuta busta paga delega cliente
    trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli: new FormControl(''),
    trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli: new FormControl(''),
    trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli: new FormControl(''),

    //trattenuta busta paga pignoramento cliente
    trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli: new FormControl(''),
    trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli: new FormControl(''),
    trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli: new FormControl(''),

    //trattenuta busta paga CSQ coniuge
    trattenutaBustaPagaCon_csq_dataInizioCSQCon: new FormControl(''),
    trattenutaBustaPagaCon_csq_dataScadenzaCSQCon: new FormControl(''),
    trattenutaBustaPagaCon_csq_rataCSQCon: new FormControl(''),

    //trattenuta busta paga delega coniuge
    trattenutaBustaPagaCon_delega_dataInizioDelegaCon: new FormControl(''),
    trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon: new FormControl(''),
    trattenutaBustaPagaCon_delega_rataDelegaCon: new FormControl(''),

    //trattenuta busta paga pignoramento coniuge
    trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon: new FormControl(''),
    trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon: new FormControl(''),
    trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon: new FormControl(''),

    //lista creditori
    listaCreditori_coefficiente: new FormControl(''),
    listaCreditori_formaTecnica: new FormControl(''),
    listaCreditori_nomeCreditore: new FormControl(''),
    listaCreditori_obbligatorio: new FormControl(''),
    listaCreditori_posizioneTecnica: new FormControl(''),
    listaCreditori_rataMensile: new FormControl(''),
    listaCreditori_valoreDebito: new FormControl(''),
    importoTransattivo:new FormControl(''),
    rataTransattivaCreditore:new FormControl(''),

    //riepilogo creditori
    riepilogoCreditori_montanteDebitorioComplessivo: new FormControl(''),
    riepilogoCreditori_montanteDebitorioSecured: new FormControl(''),
    riepilogoCreditori_montanteDebitorioUnsecured: new FormControl(''),
    riepilogoCreditori_totaleRataCreditore: new FormControl('')
  });



  constructor(
    private formBuilder: FormBuilder,
    private api: ApiRestService,
    private router : Router
  ) {
    this.createForm();
  }

  ngOnInit(): void {
  }

  createForm() {
    this.uploadForm = this.formBuilder.group({
      dataset: [null]
    });
  }

  public onFileDatasetChange(event: any) {
    this.datasetBase64 = null;

    const target: DataTransfer = <DataTransfer>(event.target);
    if (target.files.length !== 1) throw new Error('Cannot user multiple files');

    const reader: FileReader = new FileReader();

    reader.onload = (e: any) => {
      const bstr: string = e.target.result;

      const sCliente = (XLSX.utils.sheet_to_json(XLSX.read(bstr, { type: 'binary', cellDates:true, cellNF: false, cellText:false }).Sheets['Dati per Calcolo Scheda Cliente'], { header: 1 , dateNF: "YYYY-MM-DD", raw: false }));
      console.log(sCliente);

      this.dataForm.setValue({
        anagrafica_cf: sCliente[5][1] == undefined ? "" : sCliente[5][1],
        anagrafica_classeDomicilio:sCliente[8][3] == undefined ? "" : sCliente[8][3],
        //cliente
        cliente_nFamiliariCarico: sCliente[16][1] == undefined ? "" : sCliente[16][1],
        cliente_nFigliConviventi: sCliente[17][1] == undefined ? "" : sCliente[17][1],
        cliente_nFigliConviventiMinori: sCliente[18][1] == undefined ? "" : sCliente[18][1],
        cliente_nucleoFamiliare: sCliente[19][1] == undefined ? "" : sCliente[19][1],
        cliente_regimePatrimoniale: sCliente[15][1] == undefined ? "" : sCliente[15][1],
        cliente_statoCivile: sCliente[14][1] == undefined ? "" : sCliente[14][1],

        //coniuge
        coniuge_cf: sCliente[42][1] == undefined ? "" : sCliente[42][1],

        //altreEntrate
        altreEntrate_cifraMensileAltreEntrate: sCliente[77][4] == undefined ? "" : sCliente[77][4],
        altreEntrate_descrizioneAltreEntrate: sCliente[77][5] == undefined ? "" : sCliente[77][5],
        altreEntrate_progressivoAltreEntrate: "",

        //altreUscite
        altreUscite_cifraMensileAltreUscite: sCliente[72][4] == undefined ? "" : sCliente[72][4],
        altreUscite_descrizioneAltreUscite: sCliente[72][5] == undefined ? "" : sCliente[72][5],
        altreUscite_progressivoAltreUscite: "",

        //assegniMantenimentoEntrata
        assegniMantEntrata_cifraMensileAssMantEntrata: sCliente[76][4] == undefined ? "" : sCliente[76][4],
        assegniMantEntrata_descrizioneAssMantEntrata: sCliente[76][5] == undefined ? "" : sCliente[76][5],
        assegniMantEntrata_progressivoAssMantEntrata: "",

        //assegniMantenimentoUscita
        assegniMantUscita_cifraMensileAssMantUscita: sCliente[71][4] == undefined ? "" : sCliente[71][4],
        assegniMantUscita_descrizioneAssMantUscita: sCliente[71][5] == undefined ? "" : sCliente[71][5],
        assegniMantUscita_progressivoAssMantUscita: "",

        //immobile
        immobile_bancaMutuante: sCliente[80][1] == undefined ? "" : sCliente[80][1],
        immobile_dataDalProprietaImmobile: sCliente[70][1] == undefined ? "" : sCliente[70][1],
        immobile_descrizioneImmobile: sCliente[74][1] == undefined ? "" : sCliente[74][1],
        immobile_flPrimaCasa: sCliente[71][1]== undefined ? "No" : sCliente[71][1],
        immobile_localita: sCliente[72][1] == undefined ? "" : sCliente[72][1],
        immobile_mqImmobile: sCliente[75][1] == undefined ? "" : sCliente[75][1],
        immobile_mutuoOriginario: sCliente[78][1] == undefined ? "" : sCliente[78][1],
        immobile_rataMutuoMensile: sCliente[81][1] == undefined ? "" : sCliente[81][1],
        immobile_renditaLocazioneMensile: sCliente[77][1] == undefined ? "" : sCliente[77][1],
        immobile_residuoDebitoMutuo: sCliente[79][1] == undefined ? "" : sCliente[79][1],
        immobile_scadenzaRata: sCliente[82][1] == undefined ? "" : sCliente[82][1],
        immobile_tipoImmobile: sCliente[73][1] == undefined ? "" : sCliente[73][1],
        immobile_valoreCommerciale: sCliente[76][1] == undefined ? "" : sCliente[76][1],

        //totaleUscitePerAffitti
        totaleUscitePerAffitti_rataAffittoMensile: sCliente[86][1] == undefined ? "" : sCliente[86][1],
        totaleUscitePerAffitti_rataCondominioMensile: sCliente[87][1] == undefined ? "" : sCliente[87][1],

        //altriFamiliari
        listaAltriFamiliari_cfAltriF: sCliente[63][1] == undefined ? "" : sCliente[63][1],
        listaAltriFamiliari_cittaNascitaAltriF: sCliente[62][1] == undefined ? "" : sCliente[62][1],
        listaAltriFamiliari_cognomeAltriF: sCliente[60][1] == undefined ? "" : sCliente[60][1],
        listaAltriFamiliari_dataNascitaAltriF: sCliente[61][1] == undefined ? "" : sCliente[61][1],
        listaAltriFamiliari_gradoParentelaAltriF: sCliente[64][1] == undefined ? "" : sCliente[64][1],
        listaAltriFamiliari_nomeAltriF: sCliente[59][1] == undefined ? "" : sCliente[59][1],
        listaAltriFamiliari_redditoMensileAltriF: sCliente[65][1] == undefined ? "" : sCliente[65][1],

        //attività cliente Autonomo
        listaAttivitaClienteAut_attivitaCliAut: sCliente[46][4] == undefined ? "" : sCliente[46][4],
        listaAttivitaClienteAut_descrizioneAttivitaCliAut: sCliente[47][4] == undefined ? "" : sCliente[47][4],
        listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut: sCliente[50][1] == undefined ? "" : sCliente[50][1],
        listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut: sCliente[49][1] == undefined ? "" : sCliente[49][1],
        listaAttivitaClienteAut_redditoAnnuoCliAut: sCliente[48][4] == undefined ? "" : sCliente[48][4],
        listaAttivitaClienteAut_redditoMensileRetCliAut:"",
        listaAttivitaClienteAut_tipoLavoratoreCliAut: "",

        //attività cliente Dis
        listaAttivitaClienteDis_tipoLavoratoreCliDis: "",

        //attività cliente sub
        listaAttivitaClienteSub_attivitaCliSub: sCliente[24][1] == undefined ? "" : sCliente[24][1],
        listaAttivitaClienteSub_numMensilitaRetCliSub: sCliente[26][1] == undefined ? "" : sCliente[26][1],
        listaAttivitaClienteSub_redditoMensileCliSub: (sCliente[27][1] == undefined ? 0 : sCliente[27][1])/(sCliente[26][1] == undefined ? 0 : sCliente[26][1]),//calcolato
        listaAttivitaClienteSub_redditoMensileRettificato: 0,
        listaAttivitaClienteSub_tipoContrattoCliSub: "",
        listaAttivitaClienteSub_tipoLavoratoreCliSub: "",

        //attività Coniuge aut
        listaAttivitaConiugeAut_attivitaConAut: sCliente[46][4] == undefined ? "" : sCliente[46][4],
        listaAttivitaConiugeAut_descrizioneAttivitaConAut: sCliente[47][4] == undefined ? "" : sCliente[47][4],
        listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut: sCliente[50][4] == undefined ? "" : sCliente[50][4],
        listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut: sCliente[49][4] == undefined ? "" : sCliente[49][4],
        listaAttivitaConiugeAut_redditoAnnuoConAut: "",
        listaAttivitaConiugeAut_tipoLavoratoreConAut: "",
        //attività coniuge Dis
        listaAttivitaConiugeDis_tipoLavoratoreConDis: "",

        //attività coniuge sub
        listaAttivitaConiugeSub_attivitaConSub: sCliente[46][1] == undefined ? "" : sCliente[46][1],
        listaAttivitaConiugeSub_numMensilitaRetConSub: "",
        listaAttivitaConiugeSub_redditoAnnuoConSub: sCliente[49][1] == undefined ? "" : sCliente[49][1],
        listaAttivitaConiugeSub_tipoContrattoConSub: sCliente[47][1] == undefined ? "" : sCliente[47][1],
        listaAttivitaConiugeSub_tipoLavoratoreConSub: "",

        // redditi familiari mensili
        redditiFamiliariMensili: sCliente[65][1] == undefined ? 0 : sCliente[65][1],

        //trattenuta busta paga CSQ cliente
        trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli: sCliente[33][1] == undefined ? "" : sCliente[33][1],
        trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli: sCliente[33][2] == undefined ? "" : sCliente[33][2],
        trattenutaBustaPagaCliente_csqCliente_rataCSQCli: sCliente[33][3] == undefined ? "" : sCliente[33][3],

        //trattenuta busta paga delega cliente
        trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli: sCliente[32][1] == undefined ? "" : sCliente[32][1],
        trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli: sCliente[32][2] == undefined ? "" : sCliente[32][2],
        trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli: sCliente[32][3] == undefined ? "" : sCliente[32][3],

        //trattenuta busta paga pignoramento cliente
        trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli: sCliente[34][1] == undefined ? "" : sCliente[34][1],
        trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli: sCliente[34][2] == undefined ? "" : sCliente[34][2],
        trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli: sCliente[34][3] == undefined ? "" : sCliente[34][3],

        //trattenuta busta paga CSQ coniuge
        trattenutaBustaPagaCon_csq_dataInizioCSQCon: sCliente[55][1] == undefined ? "" : sCliente[55][1],
        trattenutaBustaPagaCon_csq_dataScadenzaCSQCon: sCliente[55][2] == undefined ? "" : sCliente[55][2],
        trattenutaBustaPagaCon_csq_rataCSQCon: sCliente[55][3] == undefined ? "" : sCliente[55][3],

        //trattenuta busta paga delega coniuge
        trattenutaBustaPagaCon_delega_dataInizioDelegaCon: sCliente[54][1] == undefined ? "" : sCliente[54][1],
        trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon: sCliente[54][2] == undefined ? "" : sCliente[54][2],
        trattenutaBustaPagaCon_delega_rataDelegaCon: sCliente[54][3] == undefined ? "" : sCliente[54][3],

        //trattenuta busta paga pignoramento coniuge
        trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon: sCliente[56][1] == undefined ? "" : sCliente[56][1],
        trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon: sCliente[56][2] == undefined ? "" : sCliente[56][2],
        trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon: sCliente[56][3] == undefined ? "" : sCliente[56][3],

        //lista creditori
        listaCreditori_coefficiente: "",
        listaCreditori_formaTecnica: sCliente[91][1] == undefined ? "" : sCliente[91][1],
        listaCreditori_nomeCreditore: sCliente[91][0] == undefined ? "" : sCliente[91][0],
        listaCreditori_obbligatorio: sCliente[91][5] == undefined ? "" : sCliente[91][5],
        listaCreditori_posizioneTecnica: sCliente[91][2] == undefined ? "" : sCliente[91][2],
        listaCreditori_rataMensile: sCliente[91][4] == undefined ? "" : sCliente[91][4],
        listaCreditori_valoreDebito: sCliente[91][3] == undefined ? "" : sCliente[91][3],
        importoTransattivo:0,
        rataTransattivaCreditore:0,
        //riepilogo creditori
        riepilogoCreditori_montanteDebitorioComplessivo: "",
        riepilogoCreditori_montanteDebitorioSecured: "",
        riepilogoCreditori_montanteDebitorioUnsecured: "",
        riepilogoCreditori_totaleRataCreditore: "",
      })

    };

    reader.readAsBinaryString(target.files[0]);

    
  }



  public onSubmitFrom() {
    console.log(this.dataForm.get('anagrafica_classeDomicilio').value);
    
    var json =
      {
    schedaCliente: {
      anagrafica: {
        cf: this.dataForm.get('anagrafica_cf').value,
        classeDomicilio: this.dataForm.get('anagrafica_classeDomicilio').value
      },
      datiEconomici: {
        cliente: {
          familiariCarico: this.dataForm.get('cliente_nFamiliariCarico').value,
          figliConviventi: this.dataForm.get('cliente_nFigliConviventi').value,
          figliConviventiMinori: this.dataForm.get('cliente_nFigliConviventiMinori').value,
          nucleoFamiliare: this.dataForm.get('cliente_nucleoFamiliare').value,
          regimePatrimoniale: this.dataForm.get('cliente_regimePatrimoniale').value,
          statoCivile: this.dataForm.get('cliente_statoCivile').value
        },
        coniuge: {
          cf: this.dataForm.get('coniuge_cf').value
        },
        entrateUscite: {
          altreEntrate: [
            {
              cifraMensileAltreEntrate: this.dataForm.get('altreEntrate_cifraMensileAltreEntrate').value,
              descrizioneAltreEntrate: this.dataForm.get('altreEntrate_descrizioneAltreEntrate').value,
              progressivoAltreEntrate: this.dataForm.get('altreEntrate_progressivoAltreEntrate').value
            }
          ],
          altreUscite: [
            {
              cifraMensileAltreUscite: this.dataForm.get('altreUscite_cifraMensileAltreUscite').value,
              descrizioneAltreUscite: this.dataForm.get('altreUscite_descrizioneAltreUscite').value,
              progressivoAltreUscite: this.dataForm.get('altreUscite_progressivoAltreUscite').value
            }
          ],
          assegniMantEntrata: [
            {
              cifraMensileAssMantEntrata: this.dataForm.get('assegniMantEntrata_cifraMensileAssMantEntrata').value,
              descrizioneAssMantEntrata: this.dataForm.get('assegniMantEntrata_descrizioneAssMantEntrata').value,
              progressivoAssMantEntrata: this.dataForm.get('assegniMantEntrata_progressivoAssMantEntrata').value
            }
          ],
          assegniMantUscita: [
            {
              cifraMensileAssMantUscita: this.dataForm.get('assegniMantUscita_cifraMensileAssMantUscita').value,
              descrizioneAssMantUscita: this.dataForm.get('assegniMantUscita_descrizioneAssMantUscita').value,
              progressivoAssMantUscita: this.dataForm.get('assegniMantUscita_progressivoAssMantUscita').value
            }
          ],
          immobile: [
            {
              bancaMutuante: this.dataForm.get('immobile_bancaMutuante').value,
              dataDalProprietaImmobile: this.dataForm.get('immobile_dataDalProprietaImmobile').value,
              descrizioneImmobile: this.dataForm.get('immobile_descrizioneImmobile').value,
              flPrimaCasa: this.dataForm.get('immobile_flPrimaCasa').value,
              localita: this.dataForm.get('immobile_localita').value,
              mqImmobile: this.dataForm.get('immobile_mqImmobile').value,
              mutuoOriginario: this.dataForm.get('immobile_mutuoOriginario').value,
              rataMensileMutuo: this.dataForm.get('immobile_rataMutuoMensile').value,
              renditaLocazioneMensile: this.dataForm.get('immobile_renditaLocazioneMensile').value,
              residuoDebitoMutuo: this.dataForm.get('immobile_residuoDebitoMutuo').value,
              scadenzaRata: this.dataForm.get('immobile_scadenzaRata').value,
              tipoImmobile: this.dataForm.get('immobile_tipoImmobile').value,
              valoreCommerciale: this.dataForm.get('immobile_valoreCommerciale').value
            }
          ],
          totaleUscitePerAffitti: [{
            rataAffittoMensile: this.dataForm.get('totaleUscitePerAffitti_rataAffittoMensile').value,
            rataCondominioMensile: this.dataForm.get('totaleUscitePerAffitti_rataCondominioMensile').value
          }]
        },
        listaAltriFamiliari: [
          {
            cfAltriF: this.dataForm.get('listaAltriFamiliari_cfAltriF').value,
            cittaNascitaAltriF: this.dataForm.get('listaAltriFamiliari_cittaNascitaAltriF').value,
            cognomeAltriF: this.dataForm.get('listaAltriFamiliari_cognomeAltriF').value,
            dataNascitaAltriF: this.dataForm.get('listaAltriFamiliari_dataNascitaAltriF').value,
            gradoParentelaAltriF: this.dataForm.get('listaAltriFamiliari_gradoParentelaAltriF').value,
            nomeAltriF: this.dataForm.get('listaAltriFamiliari_nomeAltriF').value,
            redditoMensileAltriF:this.dataForm.get('listaAltriFamiliari_redditoMensileAltriF').value
          }
        ],
        listaAttivitaClienteAut: [
          {
            attivitaCliAut: this.dataForm.get('listaAttivitaClienteAut_attivitaCliAut').value,
            descrizioneAttivitaCliAut: this.dataForm.get('listaAttivitaClienteAut_descrizioneAttivitaCliAut').value,
            numAnniAttivitaEsercitataCliAut: this.dataForm.get('listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut').value,
            numAnniEsercizioAttivitaCliAut: this.dataForm.get('listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut').value,
            redditoAnnuoCliAut: this.dataForm.get('listaAttivitaClienteAut_redditoAnnuoCliAut').value,
            redditoMensileRettificato:this.dataForm.get('listaAttivitaClienteAut_redditoMensileRetCliAut').value,
            tipoLavoratoreCliAut: this.dataForm.get('listaAttivitaClienteAut_tipoLavoratoreCliAut').value
          }
        ],
        listaAttivitaClienteDis: [
          {
            tipoLavoratoreCliDis: this.dataForm.get('listaAttivitaClienteDis_tipoLavoratoreCliDis').value
          }
        ],
        listaAttivitaClienteSub: [
          {
            attivitaCliSub: this.dataForm.get('listaAttivitaClienteSub_attivitaCliSub').value,
            numMensilitaRetCliSub: this.dataForm.get('listaAttivitaClienteSub_numMensilitaRetCliSub').value,
            redditoMensileCliSub:this.dataForm.get('listaAttivitaClienteSub_redditoMensileCliSub').value,
            redditoMensileRettificato: this.dataForm.get('listaAttivitaClienteSub_redditoMensileRettificato').value,
            tipoContrattoCliSub: this.dataForm.get('listaAttivitaClienteSub_tipoContrattoCliSub').value,
            tipoLavoratoreCliSub: this.dataForm.get('listaAttivitaClienteSub_tipoLavoratoreCliSub').value
          }
        ],
        listaAttivitaConiugeAut: [
          {
            attivitaConAut: this.dataForm.get('listaAttivitaConiugeAut_attivitaConAut').value,
            descrizioneAttivitaConAut: this.dataForm.get('listaAttivitaConiugeAut_descrizioneAttivitaConAut').value,
            numAnniAttivitaEsercitataConAut: this.dataForm.get('listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut').value,
            numAnniEsercizioAttivitaConAut: this.dataForm.get('listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut').value,
            redditoAnnuoConAut: this.dataForm.get('listaAttivitaConiugeAut_redditoAnnuoConAut').value,
            tipoLavoratoreConAut: this.dataForm.get('listaAttivitaConiugeAut_tipoLavoratoreConAut').value
          }
        ],
        listaAttivitaConiugeDis: [
          {
            tipoLavoratoreConDis: this.dataForm.get('listaAttivitaConiugeDis_tipoLavoratoreConDis').value
          }
        ],
        listaAttivitaConiugeSub: [
          {
            attivitaConSub: this.dataForm.get('listaAttivitaConiugeSub_attivitaConSub').value,
            numMensilitaRetConSub: this.dataForm.get('listaAttivitaConiugeSub_numMensilitaRetConSub').value,
            redditoMensileConSub: this.dataForm.get('listaAttivitaConiugeSub_redditoAnnuoConSub').value,
            tipoContrattoConSub: this.dataForm.get('listaAttivitaConiugeSub_tipoContrattoConSub').value,
            tipoLavoratoreConSub: this.dataForm.get('listaAttivitaConiugeSub_tipoLavoratoreConSub').value
          }
        ],
        redditiFamiliariMensili: this.dataForm.get('redditiFamiliariMensili').value,
        trattenutaBustaPagaCliente: {
          csqCliente: {
            dataInizioCSQCli: this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli').value,
            dataScadenzaCSQCli: this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli').value,
            rataCSQCli: this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_rataCSQCli').value
          },
          delegaCliente: {
            dataInizioDelegaCli: this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli').value,
            dataScadenzaDelegaCli: this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli').value,
            rataDelegaCli: this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli').value
          },
          pignoramentoCliente: {
            dataInizioPignoramentoCli: this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli').value,
            dataScadenzaPignoramentoCli: this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli').value,
            rataPignoramentoCli: this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli').value
          }
        },
        trattenutaBustaPagaConiuge: {
          csqConiuge: {
            dataInizioCSQCon: this.dataForm.get('trattenutaBustaPagaCon_csq_dataInizioCSQCon').value,
            dataScadenzaCSQCon: this.dataForm.get('trattenutaBustaPagaCon_csq_dataScadenzaCSQCon').value,
            rataCSQCon: this.dataForm.get('trattenutaBustaPagaCon_csq_rataCSQCon').value
          },
          delegaConiuge: {
            dataInizioDelegaCon: this.dataForm.get('trattenutaBustaPagaCon_delega_dataInizioDelegaCon').value,
            dataScadenzaDelegaCon: this.dataForm.get('trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon').value,
            rataDelegaCon: this.dataForm.get('trattenutaBustaPagaCon_delega_rataDelegaCon').value
          },
          pignoramentoConiuge: {
            dataInizioPignoramentoCon: this.dataForm.get('trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon').value,
            dataScadenzaPignoramentoCon:this.dataForm.get('trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon').value,
            rataPignoramentoCon: this.dataForm.get('trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon').value
          }
        }
      },
      listaCreditori: [
        {
          cObbligato: true,
          coefficienteCreditore: this.dataForm.get('listaCreditori_coefficiente').value,
          formaTecnica: this.dataForm.get('listaCreditori_formaTecnica').value,
          nomeCreditore: this.dataForm.get('listaCreditori_nomeCreditore').value,
          obbligatorio: this.dataForm.get('listaCreditori_obbligatorio').value,
          posizioneTecnica: this.dataForm.get('listaCreditori_posizioneTecnica').value,
          rataCreditore: this.dataForm.get('listaCreditori_rataMensile').value,
          valoreDebito: this.dataForm.get('listaCreditori_valoreDebito').value,
          importoTransattivo:0,
          rataTransattivaCreditore:0
        }
      ],
      riepilogoCreditori: {
        montanteDebitorioComplessivo: this.dataForm.get('riepilogoCreditori_montanteDebitorioComplessivo').value,
        montanteDebitorioSecured: this.dataForm.get('riepilogoCreditori_montanteDebitorioSecured').value,
        montanteDebitorioUnsecured: this.dataForm.get('riepilogoCreditori_montanteDebitorioUnsecured').value,
        totaleRataCreditore: this.dataForm.get('riepilogoCreditori_totaleRataCreditore').value
      }
    },
    requestType:"SYNC",
    requestKey:"requestKeySYNC101091",
    applicationKey:"appKeyExt"
  };

    //var json = "{   'schedaCliente': {     'anagrafica': {       'cf': 'codice fiscale'     },     'datiEconomici': {       'cliente': {         'nFamiliariCarico': 0,         'nFigliConviventi': 0,         'nFigliConviventiMinori': 0,         'nucleoFamiliare': 0,         'regimePatrimoniale': true,         'statoCivile': true       },       'coniuge': {         'cf': 'string'       },       'entrateUscite': {         'altreEntrate': [           {             'cifraMensileAltreEntrate': 0,             'descrizioneAltreEntrate': 'string',             'progressivoAltreEntrate': 200           } 					, 					 {             'cifraMensileAltreEntrate': 0,             'descrizioneAltreEntrate': 'string',             'progressivoAltreEntrate': 300           }         ],         'altreUscite': [           {             'cifraMensileAltreUscite': 0,             'descrizioneAltreUscite': 'string',             'progressivoAltreUscite': 400           }         ],         'assegniMantEntrata': [           {             'cifraMensileAssMantEntrata': 0,             'descrizioneAssMantEntrata': 'string',             'progressivoAssMantEntrata': 0           }         ],         'assegniMantUscita': [           {             'cifraMensileAssMantUscita': 0,             'descrizioneAssMantUscita': 'string',             'progressivoAssMantUscita': 0           }         ],         'immobile': [           {             'bancaMutuante': 'string',             'dataDalProprietaImmobile': '2020-11-26T17:44:21.976Z',             'descrizioneImmobile': 'string',             'flPrimaCasa': true,             'localita': 'string',             'mqImmobile': 0,             'mutuoOriginario': 0,             'rataMutuoMensile': 0,             'renditaLocazioneMensile': 0,             'residuoDebitoMutuo': 0,             'scadenzaRata': '2020-11-26T17:44:21.976Z',             'tipoImmobile': 'string',             'valoreCommerciale': 0           }         ],         'totaleUscitePerAffitti': [           {             'rataAffittoMensile': 500,             'rataCondominioMensile': 10           }, 					 {             'rataAffittoMensile': 600,             'rataCondominioMensile': 50           }         ]       },       'listaAltriFamiliari': [         {           'cfAltriF': 'string',           'cittaNascitaAltriF': 'string',           'cognomeAltriF': 'string',           'dataNascitaAltriF': '2020-11-26T17:44:21.976Z',           'gradoParentelaAltriF': 'string',           'nomeAltriF': 'string',           'redditoMensileAltriF': 0         }       ],       'listaAttivitaClienteAut': [         {           'attivitaCliAut': 'string',           'descrizioneAttivitaCliAut': 'string',           'numAnniAttivitaEsercitataCliAut': 0,           'numAnniEsercizioAttivitaCliAut': 0,           'redditoMensileCliAut': 0,           'redditoMensileRettificato': 0,           'tipoLavoratoreCliAut': 0         }       ],       'listaAttivitaClienteDis': [         {           'tipoLavoratoreCliDis': 0         }       ],       'listaAttivitaClienteSub': [         {           'attivitaCliSub': 'string',           'numMensilitaRetCliSub': 16,           'redditoMensileCliSub': 1400,           'redditoMensileRettificato': 0,           'tipoContrattoCliSub': 'string',           'tipoLavoratoreCliSub': 0         }       ],       'listaAttivitaConiugeAut': [         {           'attivitaConAut': 'string',           'descrizioneAttivitaConAut': 'string',           'numAnniAttivitaEsercitataConAut': 0,           'numAnniEsercizioAttivitaConAut': 0,           'redditoMensileConAut': 0,           'tipoLavoratoreConAut': 0         }       ],       'listaAttivitaConiugeDis': [         {           'tipoLavoratoreConDis': 0         }       ],       'listaAttivitaConiugeSub': [         {           'attivitaConSub': 'string',           'numMensilitaRetConSub': 0,           'redditoMensileConSub': 0,           'tipoContrattoConSub': 'string',           'tipoLavoratoreConSub': 0         }       ],       'redditiFamiliariMensili': 900,       'trattenutaBustaPagaCliente': {         'csqCliente': {           'dataInizioCSQCli': '2020-11-26T17:44:21.976Z',           'dataScadenzaCSQCli': '2020-11-26T17:44:21.976Z',           'rataCSQCli': 0         },         'delegaCliente': {           'dataInizioDelegaCli': '2020-11-26T17:44:21.976Z',           'dataScadenzaDelegaCli': '2020-11-26T17:44:21.976Z',           'rataDelegaCli': 0         },         'pignoramentoCliente': {           'dataInizioPignoramentoCli': '2020-11-26T17:44:21.976Z',           'dataScadenzaPignoramentoCli': '2020-11-26T17:44:21.976Z',           'rataPignoramentoCli': 0         }       },       'trattenutaBustaPagaConiuge': {         'csqConiuge': {           'dataInizioCSQCon': '2020-11-26T17:44:21.976Z',           'dataScadenzaCSQCon': '2020-11-26T17:44:21.976Z',           'rataCSQCon': 0         },         'delegaConiuge': {           'dataInizioDelegaCon': '2020-11-26T17:44:21.976Z',           'dataScadenzaDelegaCon': '2020-11-26T17:44:21.976Z',           'rataDelegaCon': 0         },         'pignoramentoConiuge': {           'dataInizioPignoramentoCon': '2020-11-26T17:44:21.976Z',           'dataScadenzaPignoramentoCon': '2020-11-26T17:44:21.976Z',           'rataPignoramentoCon': 0         }       }     },     'listaCreditori': [       {         'coefficiente': 0,         'formaTecnica': 'string',         'nomeCreditore': 'string',         'obbligatorio': true,         'posizioneTecnica': 's',         'rataMensile': 70,         'valoreDebito': 80       }, 			 {         'coefficiente': 0,         'formaTecnica': 'string',         'nomeCreditore': 'string',         'obbligatorio': true,         'posizioneTecnica': 's',         'rataMensile': 90,         'valoreDebito': 990       }     ],     'riepilogoCreditori': {       'montanteDebitorioComplessivo': 0,       'montanteDebitorioSecured': 0,       'montanteDebitorioUnsecured': 0,       'totaleRataCreditore': 0     }   },   'requestType':'SYNC',   'requestKey':'requestKey11',   'applicationKey':'appKeyExt' }";
    console.log(json);

    this.api.calcolo(json).subscribe((data: any) => {
      console.log(data);
      localStorage.setItem('valutazioneCliente', JSON.stringify(data["payload"]));
      this.router.navigate(['/result'])
    });
  }


}
