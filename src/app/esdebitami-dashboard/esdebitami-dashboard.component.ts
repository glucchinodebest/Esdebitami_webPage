import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, FormsModule, Validators } from '@angular/forms';
import { ApiRestService } from '../../servicesRest/api-rest.service'
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-esdebitami-dashboard',
  templateUrl: './esdebitami-dashboard.component.html',
  styleUrls: ['./esdebitami-dashboard.component.css']
})
export class EsdebitamiDashboardComponent implements OnInit {

  datasetBase64: String = '';
  uploadForm: FormGroup;

  dataForm = new FormGroup({
    //anagrafica
    anagrafica_cf: new FormControl(''),

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

    //riepilogo creditori
    riepilogoCreditori_montanteDebitorioComplessivo: new FormControl(''),
    riepilogoCreditori_montanteDebitorioSecured: new FormControl(''),
    riepilogoCreditori_montanteDebitorioUnsecured: new FormControl(''),
    riepilogoCreditori_totaleRataCreditore: new FormControl('')
  });



  constructor(
    private formBuilder: FormBuilder,
    private api: ApiRestService
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

      const sCliente = (XLSX.utils.sheet_to_json(XLSX.read(bstr, { type: 'binary' }).Sheets['SCHEDA CLIENTE - INPUT'], { header: 1 }));
      const sClienteOut = (XLSX.utils.sheet_to_json(XLSX.read(bstr, { type: 'binary' }).Sheets['SCHEDA CLIENTE - OUTPUT'], { header: 1 }));
      console.log(sCliente);

      this.dataForm.setValue({
        anagrafica_cf: sCliente[4][3] == undefined ? "" : sCliente[4][3],

        //cliente
        cliente_nFamiliariCarico: sCliente[14][3] == undefined ? "" : sCliente[14][3],
        cliente_nFigliConviventi: sCliente[15][3] == undefined ? "" : sCliente[15][3],
        cliente_nFigliConviventiMinori: sCliente[16][3] == undefined ? "" : sCliente[16][3],
        cliente_nucleoFamiliare: sCliente[17][3] == undefined ? "" : sCliente[17][3],
        cliente_regimePatrimoniale: sCliente[13][3] == undefined ? "" : sCliente[13][3],
        cliente_statoCivile: sCliente[12][3] == undefined ? "" : sCliente[12][3],

        //coniuge
        coniuge_cf: sCliente[43][3] == undefined ? "" : sCliente[43][3],

        //altreEntrate
        altreEntrate_cifraMensileAltreEntrate: sCliente[98][4] == undefined ? "" : sCliente[98][4],
        altreEntrate_descrizioneAltreEntrate: sCliente[97][4] == undefined ? "" : sCliente[97][4],
        altreEntrate_progressivoAltreEntrate: sCliente[96][4] == undefined ? "" : sCliente[96][4],

        //altreUscite
        altreUscite_cifraMensileAltreUscite: sCliente[92][4] == undefined ? "" : sCliente[92][4],
        altreUscite_descrizioneAltreUscite: sCliente[91][4] == undefined ? "" : sCliente[91][4],
        altreUscite_progressivoAltreUscite: sCliente[90][4] == undefined ? "" : sCliente[90][4],

        //assegniMantenimentoEntrata
        assegniMantEntrata_cifraMensileAssMantEntrata: sCliente[95][4] == undefined ? "" : sCliente[95][4],
        assegniMantEntrata_descrizioneAssMantEntrata: sCliente[94][4] == undefined ? "" : sCliente[94][4],
        assegniMantEntrata_progressivoAssMantEntrata: sCliente[93][4] == undefined ? "" : sCliente[93][4],

        //assegniMantenimentoUscita
        assegniMantUscita_cifraMensileAssMantUscita: sCliente[89][4] == undefined ? "" : sCliente[89][4],
        assegniMantUscita_descrizioneAssMantUscita: sCliente[88][4] == undefined ? "" : sCliente[88][4],
        assegniMantUscita_progressivoAssMantUscita: sCliente[87][4] == undefined ? "" : sCliente[87][4],

        //immobile
        immobile_bancaMutuante: sCliente[82][4] == undefined ? "" : sCliente[82][4],
        immobile_dataDalProprietaImmobile: sCliente[72][4] == undefined ? "" : sCliente[72][4],
        immobile_descrizioneImmobile: sCliente[76][4] == undefined ? "" : sCliente[76][4],
        immobile_flPrimaCasa: false,
        immobile_localita: sCliente[74][4] == undefined ? "" : sCliente[74][4],
        immobile_mqImmobile: sCliente[77][4] == undefined ? "" : sCliente[77][4],
        immobile_mutuoOriginario: sCliente[80][4] == undefined ? "" : sCliente[80][4],
        immobile_rataMutuoMensile: sCliente[83][4] == undefined ? "" : sCliente[83][4],
        immobile_renditaLocazioneMensile: sCliente[79][4] == undefined ? "" : sCliente[79][4],
        immobile_residuoDebitoMutuo: sCliente[81][4] == undefined ? "" : sCliente[81][4],
        immobile_scadenzaRata: sCliente[84][4] == undefined ? "" : sCliente[84][4],
        immobile_tipoImmobile: sCliente[75][4] == undefined ? "" : sCliente[75][4],
        immobile_valoreCommerciale: sCliente[78][4] == undefined ? "" : sCliente[78][4],

        //totaleUscitePerAffitti
        totaleUscitePerAffitti_rataAffittoMensile: sCliente[85][4] == undefined ? "" : sCliente[85][4],
        totaleUscitePerAffitti_rataCondominioMensile: sCliente[86][4] == undefined ? "" : sCliente[86][4],

        //altriFamiliari
        listaAltriFamiliari_cfAltriF: sCliente[69][3] == undefined ? "" : sCliente[69][3],
        listaAltriFamiliari_cittaNascitaAltriF: sCliente[68][3] == undefined ? "" : sCliente[68][3],
        listaAltriFamiliari_cognomeAltriF: sCliente[66][3] == undefined ? "" : sCliente[66][3],
        listaAltriFamiliari_dataNascitaAltriF: sCliente[67][3] == undefined ? "" : sCliente[67][3],
        listaAltriFamiliari_gradoParentelaAltriF: sCliente[70][3] == undefined ? "" : sCliente[70][3],
        listaAltriFamiliari_nomeAltriF: sCliente[65][3] == undefined ? "" : sCliente[65][3],
        listaAltriFamiliari_redditoMensileAltriF: sCliente[71][3] == undefined ? "" : sCliente[71][3],

        //attività cliente Autonomo
        listaAttivitaClienteAut_attivitaCliAut: sCliente[24][3] == undefined ? "" : sCliente[24][3],
        listaAttivitaClienteAut_descrizioneAttivitaCliAut: sCliente[25][3] == undefined ? "" : sCliente[25][3],
        listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut: sCliente[28][3] == undefined ? "" : sCliente[28][3],
        listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut: sCliente[27][3] == undefined ? "" : sCliente[27][3],
        listaAttivitaClienteAut_redditoAnnuoCliAut: sCliente[26][3] == undefined ? "" : sCliente[26][3],
        listaAttivitaClienteAut_tipoLavoratoreCliAut: sCliente[23][3] == undefined ? "" : sCliente[23][3],

        //attività cliente Dis
        listaAttivitaClienteDis_tipoLavoratoreCliDis: sCliente[29][3] == undefined ? "" : sCliente[29][3],

        //attività cliente sub
        listaAttivitaClienteSub_attivitaCliSub: sCliente[19][3] == undefined ? "" : sCliente[19][3],
        listaAttivitaClienteSub_numMensilitaRetCliSub: sCliente[21][3] == undefined ? "" : sCliente[21][3],
        listaAttivitaClienteSub_redditoMensileCliSub: "",
        listaAttivitaClienteSub_redditoMensileRettificato: "",
        listaAttivitaClienteSub_tipoContrattoCliSub: sCliente[20][3] == undefined ? "" : sCliente[20][3],
        listaAttivitaClienteSub_tipoLavoratoreCliSub: sCliente[18][3] == undefined ? "" : sCliente[18][3],

        //attività Coniuge aut
        listaAttivitaConiugeAut_attivitaConAut: sCliente[50][3] == undefined ? "" : sCliente[50][3],
        listaAttivitaConiugeAut_descrizioneAttivitaConAut: sCliente[50][3] == undefined ? "" : sCliente[51][3],
        listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut: sCliente[53][3] == undefined ? "" : sCliente[53][3],
        listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut: sCliente[54][3] == undefined ? "" : sCliente[54][3],
        listaAttivitaConiugeAut_redditoAnnuoConAut: sCliente[52][3] == undefined ? "" : sCliente[52][3],
        listaAttivitaConiugeAut_tipoLavoratoreConAut: sCliente[49][3] == undefined ? "" : sCliente[49][3],

        //attività coniuge Dis
        listaAttivitaConiugeDis_tipoLavoratoreConDis: sCliente[55][3] == undefined ? "" : sCliente[55][3],

        //attività coniuge sub
        listaAttivitaConiugeSub_attivitaConSub: sCliente[45][3] == undefined ? "" : sCliente[45][3],
        listaAttivitaConiugeSub_numMensilitaRetConSub: sCliente[47][3] == undefined ? "" : sCliente[47][3],
        listaAttivitaConiugeSub_redditoAnnuoConSub: sCliente[48][3] == undefined ? "" : sCliente[48][3],
        listaAttivitaConiugeSub_tipoContrattoConSub: sCliente[46][3] == undefined ? "" : sCliente[46][3],
        listaAttivitaConiugeSub_tipoLavoratoreConSub: sCliente[44][3] == undefined ? "" : sCliente[44][3],

        // redditi familiari mensili
        redditiFamiliariMensili: sCliente[71][3] == undefined ? "" : sCliente[71][3],

        //trattenuta busta paga CSQ cliente
        trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli: sCliente[33][4] == undefined ? "" : sCliente[33][4],
        trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli: sCliente[34][4] == undefined ? "" : sCliente[34][4],
        trattenutaBustaPagaCliente_csqCliente_rataCSQCli: sCliente[35][4] == undefined ? "" : sCliente[35][4],

        //trattenuta busta paga delega cliente
        trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli: sCliente[30][4] == undefined ? "" : sCliente[30][4],
        trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli: sCliente[31][4] == undefined ? "" : sCliente[31][4],
        trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli: sCliente[32][4] == undefined ? "" : sCliente[32][4],

        //trattenuta busta paga pignoramento cliente
        trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli: sCliente[36][4] == undefined ? "" : sCliente[36][4],
        trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli: sCliente[37][4] == undefined ? "" : sCliente[37][4],
        trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli: sCliente[38][4] == undefined ? "" : sCliente[38][4],

        //trattenuta busta paga CSQ coniuge
        trattenutaBustaPagaCon_csq_dataInizioCSQCon: sCliente[59][4] == undefined ? "" : sCliente[59][4],
        trattenutaBustaPagaCon_csq_dataScadenzaCSQCon: sCliente[60][4] == undefined ? "" : sCliente[60][4],
        trattenutaBustaPagaCon_csq_rataCSQCon: sCliente[61][4] == undefined ? "" : sCliente[61][4],

        //trattenuta busta paga delega coniuge
        trattenutaBustaPagaCon_delega_dataInizioDelegaCon: sCliente[56][4] == undefined ? "" : sCliente[56][4],
        trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon: sCliente[57][4] == undefined ? "" : sCliente[57][4],
        trattenutaBustaPagaCon_delega_rataDelegaCon: sCliente[58][4] == undefined ? "" : sCliente[58][4],

        //trattenuta busta paga pignoramento coniuge
        trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon: sCliente[62][4] == undefined ? "" : sCliente[62][4],
        trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon: sCliente[63][4] == undefined ? "" : sCliente[63][4],
        trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon: sCliente[64][4] == undefined ? "" : sCliente[64][4],

        //lista creditori
        listaCreditori_coefficiente: sClienteOut[120][3] == undefined ? "" : sClienteOut[120][3],
        listaCreditori_formaTecnica: sClienteOut[117][3] == undefined ? "" : sClienteOut[117][3],
        listaCreditori_nomeCreditore: sClienteOut[115][3] == undefined ? "" : sClienteOut[115][3],
        listaCreditori_obbligatorio: sClienteOut[121][3] == undefined ? "" : sClienteOut[121][3],
        listaCreditori_posizioneTecnica: sClienteOut[116][3] == undefined ? "" : sClienteOut[116][3],
        listaCreditori_rataMensile: sClienteOut[119][3] == undefined ? "" : sClienteOut[119][3],
        listaCreditori_valoreDebito: sClienteOut[118][3] == undefined ? "" : sClienteOut[118][3],

        //riepilogo creditori
        riepilogoCreditori_montanteDebitorioComplessivo: sClienteOut[124][3] == undefined ? "" : sClienteOut[124][3],
        riepilogoCreditori_montanteDebitorioSecured: sClienteOut[123][3] == undefined ? "" : sClienteOut[123][3],
        riepilogoCreditori_montanteDebitorioUnsecured: sClienteOut[122][3] == undefined ? "" : sClienteOut[122][3],
        riepilogoCreditori_totaleRataCreditore: sClienteOut[125][3] == undefined ? "" : sClienteOut[125][3],
      })

    };

    reader.readAsBinaryString(target.files[0]);

    /*if (event.target.files.length > 0) {
      const me = this;
      const file = event.target.files[0];
      const a = file['name'].split('.');
      const reader: FileReader = new FileReader();
      if (['xls', 'xlsx', 'xlxs'].indexOf(a[a.length - 1]) !== -1) {
        reader.onload = (e: any) => {
          const binarystr: string = e.target.result;
          this.api.readxls(btoa(binarystr)).subscribe(
            (data: any) => {

              var anagrafica = data["payload"]["schedaCliente"]["anagrafica"];
              var cliente = data["payload"]["schedaCliente"]["cliente"];
              var coniuge = data["payload"]["schedaCliente"]["coniuge"];
              var entrateUscite = data["payload"]["schedaCliente"]["entrateUscite"];
              var immobile = data["payload"]["schedaCliente"]["entrateUscite"]["immobile"];
              var totaleUscitePerAffitti = data["payload"]["schedaCliente"]["entrateUscite"]["totaleUscitePerAffitti"];
              var listaAltriFamiliari = data["payload"]["schedaCliente"]["listaAltriFamiliari"];
              var listaAttivitaClienteAut = data["payload"]["schedaCliente"]["listaAttivitaClienteAut"];
              var listaAttivitaClienteDis = data["payload"]["schedaCliente"]["listaAttivitaClienteDis"];
              var listaAttivitaClienteSub = data["payload"]["schedaCliente"]["listaAttivitaClienteSub"];
              var listaAttivitaConiugeAut = data["payload"]["schedaCliente"]["listaAttivitaConiugeAut"];
              var listaAttivitaConiugeDis = data["payload"]["schedaCliente"]["listaAttivitaConiugeDis"];
              var listaAttivitaConiugeSub = data["payload"]["schedaCliente"]["listaAttivitaConiugeSub"];
              var redditiFamiliariMensili = data["payload"]["schedaCliente"]["redditiFamiliariMensili"];
              var trattenutaBustaPagaCliente = data["payload"]["schedaCliente"]["trattenutaBustaPagaCliente"];
              var trattenutaBustaPagaConiuge = data["payload"]["schedaCliente"]["trattenutaBustaPagaConiuge"];
              var listaCreditori = data["payload"]["schedaCliente"]["listaCreditori"];
              var riepilogoCreditori = data["payload"]["schedaCliente"]["riepilogoCreditori"];



              this.dataForm.setValue({
                anagrafica_cf: anagrafica["cf"],

                //cliente
                cliente_nFamiliariCarico: cliente["nFamiliariCarico"],
                cliente_nFigliConviventi: cliente["nFigliConviventi"],
                cliente_nFigliConviventiMinori: cliente["nFigliConviventiMinori"],
                cliente_nucleoFamiliare: cliente["nucleoFamiliare"],
                cliente_regimePatrimoniale: cliente["regimePatrimoniale"],
                cliente_statoCivile: cliente["statoCivile"],

                //coniuge
                coniuge_cf: coniuge["cf"],

                //altreEntrate
                altreEntrate_cifraMensileAltreEntrate: entrateUscite["altreEntrate"][0]["cifraMensileAltreEntrate"],
                altreEntrate_descrizioneAltreEntrate: entrateUscite["altreEntrate"][0]["descrizioneAltreEntrate"],
                altreEntrate_progressivoAltreEntrate: entrateUscite["altreEntrate"][0]["progressivoAltreEntrate"],

                //altreUscite
                altreUscite_cifraMensileAltreUscite: entrateUscite["altreUscite"][0]["cifraMensileAltreUscite"],
                altreUscite_descrizioneAltreUscite: entrateUscite["altreUscite"][0]["descrizioneAltreUscite"],
                altreUscite_progressivoAltreUscite: entrateUscite["altreUscite"][0]["progressivoAltreUscite"],

                //assegniMantenimentoEntrata
                assegniMantEntrata_cifraMensileAssMantEntrata: entrateUscite["assegniMantEntrata"][0]["cifraMensileAssMantEntrata"],
                assegniMantEntrata_descrizioneAssMantEntrata: entrateUscite["assegniMantEntrata"][0]["descrizioneAssMantEntrata"],
                assegniMantEntrata_progressivoAssMantEntrata: entrateUscite["assegniMantEntrata"][0]["progressivoAssMantEntrata"],

                //assegniMantenimentoUscita
                assegniMantUscita_cifraMensileAssMantUscita: entrateUscite["assegniMantUscita"][0]["cifraMensileAssMantUscita"],
                assegniMantUscita_descrizioneAssMantUscita: entrateUscite["assegniMantUscita"][0]["descrizioneAssMantUscita"],
                assegniMantUscita_progressivoAssMantUscita: entrateUscite["assegniMantUscita"][0]["progressivoAssMantUscita"],

                //immobile
                immobile_bancaMutuante: immobile[0]["bancaMutuante"],
                immobile_dataDalProprietaImmobile: immobile[0]["dataDalProprietaImmobile"],
                immobile_descrizioneImmobile: immobile[0]["descrizioneImmobile"],
                immobile_flPrimaCasa: immobile[0]["flPrimaCasa"],
                immobile_localita: immobile[0]["localita"],
                immobile_mqImmobile: immobile[0]["mqImmobile"],
                immobile_mutuoOriginario: immobile[0]["mutuoOriginario"],
                immobile_rataMutuoMensile: immobile[0]["rataMutuoMensile"],
                immobile_renditaLocazioneMensile: immobile[0]["renditaLocazioneMensile"],
                immobile_residuoDebitoMutuo: immobile[0]["residuoDebitoMutuo"],
                immobile_scadenzaRata: immobile[0]["scadenzaRata"],
                immobile_tipoImmobile: immobile[0]["tipoImmobile"],
                immobile_valoreCommerciale: immobile[0]["valoreCommerciale"],

                //totaleUscitePerAffitti
                totaleUscitePerAffitti_rataAffittoMensile: totaleUscitePerAffitti["rataAffittoMensile"],
                totaleUscitePerAffitti_rataCondominioMensile: totaleUscitePerAffitti["rataCondominioMensile"],

                //altriFamiliari
                listaAltriFamiliari_cfAltriF: listaAltriFamiliari[0]["cfAltriF"],
                listaAltriFamiliari_cittaNascitaAltriF: listaAltriFamiliari[0]["cittaNascitaAltriF"],
                listaAltriFamiliari_cognomeAltriF: listaAltriFamiliari[0]["cognomeAltriF"],
                listaAltriFamiliari_dataNascitaAltriF: listaAltriFamiliari[0]["dataNascitaAltriF"],
                listaAltriFamiliari_gradoParentelaAltriF: listaAltriFamiliari[0]["gradoParentelaAltriF"],
                listaAltriFamiliari_nomeAltriF: listaAltriFamiliari[0]["nomeAltriF"],
                listaAltriFamiliari_redditoMensileAltriF: listaAltriFamiliari[0]["redditoMensileAltriF"],

                //attività cliente Autonomo
                listaAttivitaClienteAut_attivitaCliAut: listaAttivitaClienteAut[0]["attivitaCliAut"],
                listaAttivitaClienteAut_descrizioneAttivitaCliAut: listaAttivitaClienteAut[0]["descrizioneAttivitaCliAut"],
                listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut: listaAttivitaClienteAut[0]["numAnniAttivitaEsercitataCliAut"],
                listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut: listaAttivitaClienteAut[0]["numAnniEsercizioAttivitaCliAut"],
                listaAttivitaClienteAut_redditoAnnuoCliAut: listaAttivitaClienteAut[0]["redditoAnnuoCliAut"],
                listaAttivitaClienteAut_tipoLavoratoreCliAut: listaAttivitaClienteAut[0]["tipoLavoratoreCliAut"],

                //attività cliente Dis
                listaAttivitaClienteDis_tipoLavoratoreCliDis: listaAttivitaClienteDis[0]["tipoLavoratoreCliDis"],

                //attività cliente sub
                listaAttivitaClienteSub_attivitaCliSub: listaAttivitaClienteSub[0]["attivitaCliSub"],
                listaAttivitaClienteSub_numMensilitaRetCliSub: listaAttivitaClienteSub[0]["numMensilitaRetCliSub"],
                listaAttivitaClienteSub_redditoMensileCliSub: listaAttivitaClienteSub[0]["redditoMensileCliSub"],
                listaAttivitaClienteSub_redditoMensileRettificato: listaAttivitaClienteSub[0]["redditoMensileRettificato"],
                listaAttivitaClienteSub_tipoContrattoCliSub: listaAttivitaClienteSub[0]["tipoContrattoCliSub"],
                listaAttivitaClienteSub_tipoLavoratoreCliSub: listaAttivitaClienteSub[0]["tipoLavoratoreCliSub"],

                //attività Coniuge aut
                listaAttivitaConiugeAut_attivitaConAut: listaAttivitaConiugeAut[0]["attivitaConAut"],
                listaAttivitaConiugeAut_descrizioneAttivitaConAut: listaAttivitaConiugeAut[0]["descrizioneAttivitaConAut"],
                listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut: listaAttivitaConiugeAut[0]["numAnniAttivitaEsercitataConAut"],
                listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut: listaAttivitaConiugeAut[0]["numAnniEsercizioAttivitaConAut"],
                listaAttivitaConiugeAut_redditoAnnuoConAut: listaAttivitaConiugeAut[0]["redditoAnnuoConAut"],
                listaAttivitaConiugeAut_tipoLavoratoreConAut: listaAttivitaConiugeAut[0]["tipoLavoratoreConAut"],

                //attività coniuge Dis
                listaAttivitaConiugeDis_tipoLavoratoreConDis: listaAttivitaConiugeDis[0]["tipoLavoratoreConDis"],

                //attività coniuge sub
                listaAttivitaConiugeSub_attivitaConSub: listaAttivitaConiugeSub[0]["attivitaConSub"],
                listaAttivitaConiugeSub_numMensilitaRetConSub: listaAttivitaConiugeSub[0]["numMensilitaRetConSub"],
                listaAttivitaConiugeSub_redditoAnnuoConSub: listaAttivitaConiugeSub[0]["redditoAnnuoConSub"],
                listaAttivitaConiugeSub_tipoContrattoConSub: listaAttivitaConiugeSub[0]["tipoContrattoConSub"],
                listaAttivitaConiugeSub_tipoLavoratoreConSub: listaAttivitaConiugeSub[0]["tipoLavoratoreConSub"],

                // redditi familiari mensili
                redditiFamiliariMensili: redditiFamiliariMensili,

                //trattenuta busta paga CSQ cliente
                trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli: trattenutaBustaPagaCliente["csqCliente"]["dataInizioCSQCli"],
                trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli: trattenutaBustaPagaCliente["csqCliente"]["dataScadenzaCSQCli"],
                trattenutaBustaPagaCliente_csqCliente_rataCSQCli: trattenutaBustaPagaCliente["csqCliente"]["rataCSQCli"],

                //trattenuta busta paga delega cliente
                trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli: trattenutaBustaPagaCliente["delegaCliente"]["dataInizioDelegaCli"],
                trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli: trattenutaBustaPagaCliente["delegaCliente"]["dataScadenzaDelegaCli"],
                trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli: trattenutaBustaPagaCliente["delegaCliente"]["rataDelegaCli"],

                //trattenuta busta paga pignoramento cliente
                trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli: trattenutaBustaPagaCliente["pignoramentoCliente"]["dataInizioPignoramentoCli"],
                trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli: trattenutaBustaPagaCliente["pignoramentoCliente"]["dataScadenzaPignoramentoCli"],
                trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli: trattenutaBustaPagaCliente["pignoramentoCliente"]["rataPignoramentoCli"],

                //trattenuta busta paga CSQ coniuge
                trattenutaBustaPagaCon_csq_dataInizioCSQCon: trattenutaBustaPagaConiuge["csqConiuge"]["dataInizioCSQCon"],
                trattenutaBustaPagaCon_csq_dataScadenzaCSQCon: trattenutaBustaPagaConiuge["csqConiuge"]["dataScadenzaCSQCon"],
                trattenutaBustaPagaCon_csq_rataCSQCon: trattenutaBustaPagaConiuge["csqConiuge"]["rataCSQCon"],

                //trattenuta busta paga delega coniuge
                trattenutaBustaPagaCon_delega_dataInizioDelegaCon: trattenutaBustaPagaConiuge["delegaConiuge"]["dataInizioDelegaCon"],
                trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon: trattenutaBustaPagaConiuge["delegaConiuge"]["dataScadenzaDelegaCon"],
                trattenutaBustaPagaCon_delega_rataDelegaCon: trattenutaBustaPagaConiuge["delegaConiuge"]["rataDelegaCon"],

                //trattenuta busta paga pignoramento coniuge
                trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon: trattenutaBustaPagaConiuge["pignoramentoConiuge"]["dataInizioPignoramentoCon"],
                trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon: trattenutaBustaPagaConiuge["pignoramentoConiuge"]["dataScadenzaPignoramentoCon"],
                trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon: trattenutaBustaPagaConiuge["pignoramentoConiuge"]["rataPignoramentoCon"],

                //lista creditori
                listaCreditori_coefficiente: listaCreditori[0]["coefficiente"],
                listaCreditori_formaTecnica: listaCreditori[0]["formaTecnica"],
                listaCreditori_nomeCreditore: listaCreditori[0]["nomeCreditore"],
                listaCreditori_obbligatorio: listaCreditori[0]["obbligatorio"],
                listaCreditori_posizioneTecnica: listaCreditori[0]["posizioneTecnica"],
                listaCreditori_rataMensile: listaCreditori[0]["rataMensile"],
                listaCreditori_valoreDebito: listaCreditori[0]["valoreDebito"],

                //riepilogo creditori
                riepilogoCreditori_montanteDebitorioComplessivo: riepilogoCreditori["montanteDebitorioComplessivo"],
                riepilogoCreditori_montanteDebitorioSecured: riepilogoCreditori["montanteDebitorioSecured"],
                riepilogoCreditori_montanteDebitorioUnsecured: riepilogoCreditori["montanteDebitorioUnsecured"],
                riepilogoCreditori_totaleRataCreditore: riepilogoCreditori["totaleRataCreditore"],
              })
              console.log(this.dataForm.get('trattenutaBustaPagaCon_delega_dataInizioDelegaCon').value);
            }
          );
        }
        reader.readAsBinaryString(file);
        event.target.value = null;
      }
    }*/
  }



  public onSubmitFrom() {
    var json: String =
      `{
    schedaCliente: {
      anagrafica: {
        cf: ${this.dataForm.get('anagrafica_cf').value}
      },
      datiEconomici: {
        cliente: {
          nFamiliariCarico: ${this.dataForm.get('cliente_nFamiliariCarico').value},
          nFigliConviventi: ${this.dataForm.get('cliente_nFigliConviventi').value},
          nFigliConviventiMinori: ${this.dataForm.get('cliente_nFigliConviventiMinori').value},
          nucleoFamiliare: ${this.dataForm.get('cliente_nucleoFamiliare').value},
          regimePatrimoniale: ${this.dataForm.get('cliente_regimePatrimoniale').value},
          statoCivile: ${this.dataForm.get('cliente_statoCivile').value}
        },
        coniuge: {
          cf: ${this.dataForm.get('coniuge_cf').value}
        },
        entrateUscite: {
          altreEntrate: [
            {
              cifraMensileAltreEntrate: ${this.dataForm.get('altreEntrate_cifraMensileAltreEntrate').value},
              descrizioneAltreEntrate: ${this.dataForm.get('altreEntrate_descrizioneAltreEntrate').value},
              progressivoAltreEntrate: ${this.dataForm.get('altreEntrate_progressivoAltreEntrate').value}
            }
          ],
          altreUscite: [
            {
              cifraMensileAltreUscite: ${this.dataForm.get('altreUscite_cifraMensileAltreUscite').value},
              descrizioneAltreUscite: ${this.dataForm.get('altreUscite_descrizioneAltreUscite').value},
              progressivoAltreUscite: ${this.dataForm.get('altreUscite_progressivoAltreUscite').value}
            }
          ],
          assegniMantEntrata: [
            {
              cifraMensileAssMantEntrata: ${this.dataForm.get('assegniMantEntrata_cifraMensileAssMantEntrata').value},
              descrizioneAssMantEntrata: ${this.dataForm.get('assegniMantEntrata_descrizioneAssMantEntrata').value},
              progressivoAssMantEntrata: ${this.dataForm.get('assegniMantEntrata_progressivoAssMantEntrata').value}
            }
          ],
          assegniMantUscita: [
            {
              cifraMensileAssMantUscita: ${this.dataForm.get('assegniMantUscita_cifraMensileAssMantUscita').value},
              descrizioneAssMantUscita: ${this.dataForm.get('assegniMantUscita_descrizioneAssMantUscita').value},
              progressivoAssMantUscita: ${this.dataForm.get('assegniMantUscita_progressivoAssMantUscita').value}
            }
          ],
          immobile: [
            {
              bancaMutuante: ${this.dataForm.get('immobile_bancaMutuante').value},
              dataDalProprietaImmobile: ${this.dataForm.get('immobile_dataDalProprietaImmobile').value},
              descrizioneImmobile: ${this.dataForm.get('immobile_descrizioneImmobile').value},
              flPrimaCasa: ${this.dataForm.get('immobile_flPrimaCasa').value},
              localita: ${this.dataForm.get('immobile_localita').value},
              mqImmobile: ${this.dataForm.get('immobile_mqImmobile').value},
              mutuoOriginario: ${this.dataForm.get('immobile_mutuoOriginario').value},
              rataMutuoMensile: ${this.dataForm.get('immobile_rataMutuoMensile').value},
              renditaLocazioneMensile: ${this.dataForm.get('immobile_renditaLocazioneMensile').value},
              residuoDebitoMutuo: ${this.dataForm.get('immobile_residuoDebitoMutuo').value},
              scadenzaRata: ${this.dataForm.get('immobile_scadenzaRata').value},
              tipoImmobile: ${this.dataForm.get('immobile_tipoImmobile').value},
              valoreCommerciale: ${this.dataForm.get('immobile_valoreCommerciale').value}
            }
          ],
          totaleUscitePerAffitti: {
            rataAffittoMensile: ${this.dataForm.get('totaleUscitePerAffitti_rataAffittoMensile').value},
            rataCondominioMensile: ${this.dataForm.get('totaleUscitePerAffitti_rataCondominioMensile').value}
          }
        },
        listaAltriFamiliari: [
          {
            cfAltriF: ${this.dataForm.get('listaAltriFamiliari_cfAltriF').value},
            cittaNascitaAltriF: ${this.dataForm.get('listaAltriFamiliari_cittaNascitaAltriF').value},
            cognomeAltriF: ${this.dataForm.get('listaAltriFamiliari_cognomeAltriF').value},
            dataNascitaAltriF: ${this.dataForm.get('listaAltriFamiliari_dataNascitaAltriF').value},
            gradoParentelaAltriF: ${this.dataForm.get('listaAltriFamiliari_gradoParentelaAltriF').value},
            nomeAltriF: ${this.dataForm.get('listaAltriFamiliari_nomeAltriF').value},
            redditoMensileAltriF: ${this.dataForm.get('listaAltriFamiliari_redditoMensileAltriF').value}
          }
        ],
        listaAttivitaClienteAut: [
          {
            attivitaCliAut: ${this.dataForm.get('listaAttivitaClienteAut_attivitaCliAut').value},
            descrizioneAttivitaCliAut: ${this.dataForm.get('listaAttivitaClienteAut_descrizioneAttivitaCliAut').value},
            numAnniAttivitaEsercitataCliAut: ${this.dataForm.get('listaAttivitaClienteAut_numAnniAttivitaEsercitataCliAut').value},
            numAnniEsercizioAttivitaCliAut: ${this.dataForm.get('listaAttivitaClienteAut_numAnniEsercizioAttivitaCliAut').value},
            redditoAnnuoCliAut: ${this.dataForm.get('listaAttivitaClienteAut_redditoAnnuoCliAut').value},
            tipoLavoratoreCliAut: ${this.dataForm.get('listaAttivitaClienteAut_tipoLavoratoreCliAut').value}
          }
        ],
        listaAttivitaClienteDis: [
          {
            tipoLavoratoreCliDis: ${this.dataForm.get('listaAttivitaClienteDis_tipoLavoratoreCliDis').value}
          }
        ],
        listaAttivitaClienteSub: [
          {
            attivitaCliSub: ${this.dataForm.get('listaAttivitaClienteSub_attivitaCliSub').value},
            numMensilitaRetCliSub: ${this.dataForm.get('listaAttivitaClienteSub_numMensilitaRetCliSub').value},
            redditoMensileCliSub: ${this.dataForm.get('listaAttivitaClienteSub_redditoMensileCliSub').value},
            redditoMensileRettificato: ${this.dataForm.get('listaAttivitaClienteSub_redditoMensileRettificato').value},
            tipoContrattoCliSub: ${this.dataForm.get('listaAttivitaClienteSub_tipoContrattoCliSub').value},
            tipoLavoratoreCliSub: ${this.dataForm.get('listaAttivitaClienteSub_tipoLavoratoreCliSub').value}
          }
        ],
        listaAttivitaConiugeAut: [
          {
            attivitaConAut: ${this.dataForm.get('listaAttivitaConiugeAut_attivitaConAut').value},
            descrizioneAttivitaConAut: ${this.dataForm.get('listaAttivitaConiugeAut_descrizioneAttivitaConAut').value},
            numAnniAttivitaEsercitataConAut: ${this.dataForm.get('listaAttivitaConiugeAut_numAnniAttivitaEsercitataConAut').value},
            numAnniEsercizioAttivitaConAut: ${this.dataForm.get('listaAttivitaConiugeAut_numAnniEsercizioAttivitaConAut').value},
            redditoAnnuoConAut: ${this.dataForm.get('listaAttivitaConiugeAut_redditoAnnuoConAut').value},
            tipoLavoratoreConAut: ${this.dataForm.get('listaAttivitaConiugeAut_tipoLavoratoreConAut').value}
          }
        ],
        listaAttivitaConiugeDis: [
          {
            tipoLavoratoreConDis: ${this.dataForm.get('listaAttivitaConiugeDis_tipoLavoratoreConDis').value}
          }
        ],
        listaAttivitaConiugeSub: [
          {
            attivitaConSub: ${this.dataForm.get('listaAttivitaConiugeSub_attivitaConSub').value},
            numMensilitaRetConSub: ${this.dataForm.get('listaAttivitaConiugeSub_numMensilitaRetConSub').value},
            redditoAnnuoConSub: ${this.dataForm.get('listaAttivitaConiugeSub_redditoAnnuoConSub').value},
            tipoContrattoConSub: ${this.dataForm.get('listaAttivitaConiugeSub_tipoContrattoConSub').value},
            tipoLavoratoreConSub: ${this.dataForm.get('listaAttivitaConiugeSub_tipoLavoratoreConSub').value}
          }
        ],
        redditiFamiliariMensili: ${this.dataForm.get('redditiFamiliariMensili').value},
        trattenutaBustaPagaCliente: {
          csqCliente: {
            dataInizioCSQCli: ${this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_dataInizioCSQCli').value},
            dataScadenzaCSQCli: ${this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_dataScadenzaCSQCli').value},
            rataCSQCli: ${this.dataForm.get('trattenutaBustaPagaCliente_csqCliente_rataCSQCli').value}
          },
          delegaCliente: {
            dataInizioDelegaCli: ${this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_dataInizioDelegaCli').value},
            dataScadenzaDelegaCli: ${this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_dataScadenzaDelegaCli').value},
            rataDelegaCli: ${this.dataForm.get('trattenutaBustaPagaCliente_delegaCliente_rataDelegaCli').value}
          },
          pignoramentoCliente: {
            dataInizioPignoramentoCli: ${this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_dataInizioPignoramentoCli').value},
            dataScadenzaPignoramentoCli: ${this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_dataScadenzaPignoramentoCli').value},
            rataPignoramentoCli: ${this.dataForm.get('trattenutaBustaPagaCliente_pignoramentoCliente_rataPignoramentoCli').value}
          }
        },
        trattenutaBustaPagaConiuge: {
          csqConiuge: {
            dataInizioCSQCon: ${this.dataForm.get('trattenutaBustaPagaCon_csq_dataInizioCSQCon').value},
            dataScadenzaCSQCon: ${this.dataForm.get('trattenutaBustaPagaCon_csq_dataScadenzaCSQCon').value},
            rataCSQCon: ${this.dataForm.get('trattenutaBustaPagaCon_csq_rataCSQCon').value}
          },
          delegaConiuge: {
            dataInizioDelegaCon: ${this.dataForm.get('trattenutaBustaPagaCon_delega_dataInizioDelegaCon').value},
            dataScadenzaDelegaCon: ${this.dataForm.get('trattenutaBustaPagaCon_delega_dataScadenzaDelegaCon').value},
            rataDelegaCon: ${this.dataForm.get('trattenutaBustaPagaCon_delega_rataDelegaCon').value}
          },
          pignoramentoConiuge: {
            dataInizioPignoramentoCon: ${this.dataForm.get('trattenutaBustaPagaCon_pignoramento_dataInizioPignoramentoCon').value},
            dataScadenzaPignoramentoCon:${this.dataForm.get('trattenutaBustaPagaCon_pignoramento_dataScadenzaPignoramentoCon').value},
            rataPignoramentoCon: ${this.dataForm.get('trattenutaBustaPagaCon_pignoramento_rataPignoramentoCon').value}
          }
        }
      },
      listaCreditori: [
        {
          coefficiente: ${this.dataForm.get('listaCreditori_coefficiente').value},
          formaTecnica: ${this.dataForm.get('listaCreditori_formaTecnica').value},
          nomeCreditore: ${this.dataForm.get('listaCreditori_nomeCreditore').value},
          obbligatorio: ${this.dataForm.get('listaCreditori_obbligatorio').value},
          posizioneTecnica: ${this.dataForm.get('listaCreditori_posizioneTecnica').value},
          rataMensile: ${this.dataForm.get('listaCreditori_rataMensile').value},
          valoreDebito: ${this.dataForm.get('listaCreditori_valoreDebito').value}
        }
      ],
      riepilogoCreditori: {
        montanteDebitorioComplessivo: ${this.dataForm.get('riepilogoCreditori_montanteDebitorioComplessivo').value},
        montanteDebitorioSecured: ${this.dataForm.get('riepilogoCreditori_montanteDebitorioSecured').value},
        montanteDebitorioUnsecured: ${this.dataForm.get('riepilogoCreditori_montanteDebitorioUnsecured').value},
        totaleRataCreditore: ${this.dataForm.get('riepilogoCreditori_totaleRataCreditore').value}
      }
    },
    requestType:richiesta5,
    requestKey:requestKey5,
    applicationKey:appKeyOdoo
  }`;

    console.log(json);

    this.api.calcolo(json).subscribe((data: any) => {
      console.log(data);
      
    });
  }


}
