import * as peticions from '../serveis/data/dadesServeis';

const rutesServeis = [
  { servei: peticions.dadesServeisPropis, nomAMenu: 'Serveis Propis' },
  { servei: peticions.dadesPadro, nomAMenu: 'Dades Padró' },
  { servei: peticions.dadesFamiliaNombrosa, nomAMenu: 'Família nombrosa' },
  { servei: peticions.dadesFuncionamentPinbal, nomAMenu: 'Pinbal' },
];

export default rutesServeis;
