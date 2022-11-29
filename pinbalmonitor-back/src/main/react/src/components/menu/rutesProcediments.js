import * as peticions from '../procediment/data/dadesProcediments';

const rutes = [
  { procediment: peticions.dadesServeisPropis, nomAMenu: 'Serveis Propis' },
  { procediment: peticions.dadesPadro, nomAMenu: 'Dades Padró' },
  { procediment: peticions.dadesFamiliaNombrosa, nomAMenu: 'Família nombrosa' },
  { procediment: peticions.dadesFuncionamentPinbal, nomAMenu: 'Pinbal' },
];

export default rutes;
