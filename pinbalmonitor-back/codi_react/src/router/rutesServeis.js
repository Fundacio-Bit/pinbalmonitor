
import * as peticions from "../components/serveis/data/dadesServeis";

export const rutesServeis = [
  { servei: peticions.dadesServeisPropis, path: "/propis", nomAMenu :"Serveis Propis" },
  { servei: peticions.dadesPadro, path: "/padro", nomAMenu :"Dades Padró"  },
  { servei: peticions.dadesFamiliaNombrosa, path: "/familia-nombrosa", nomAMenu :"Família nombrosa" },
  { servei: peticions.dadesFuncionamentPinbal, path: "/pinbal", nomAMenu : "Pinbal" },
];