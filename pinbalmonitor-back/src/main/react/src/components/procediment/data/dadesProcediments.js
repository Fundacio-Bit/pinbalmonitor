/* eslint-disable */
const urlBase= "http://127.0.0.1:8080/pinbalmonitorapi/interna/services/pinbal";
const funcionamentPinbal = {
  titol: 'Validar el funcionament de PINBAL',

  serveis: [
    {
      nom: "Petició a un servei intermediat de l'estat",
      ruta: `${urlBase}/verificacioIdentitat`,
   },
    { nom: 'Petició a un servei  oferit pel Govern',
    ruta: `${urlBase}/matricula`, },
    {
      nom: 'Petició a un servei  oferit per una altra administració de la CAIB',
      //Padró Eivissa
      ruta:`${urlBase}/padroHistoric/036` ,
    },
   /* {
      nom: "Petició asíncrona a un servei intermediat de l'estat",
    },
    { nom: 'Obtenir justificant de verificació de dades' },*/
  ],
};

const serveisPropis = {
  titol: 'Validar serveis propis del GOIB',
  serveis: [
    {
      nom: 'Consulta de servei de Discapacitat',
      ruta: `${urlBase}/discapacitat`,

    },

    {
      nom: "Consulta de servei de pagament amb l'ATIB per ajudes i subvencions",
      ruta: `${urlBase}/pagamentAjudes`, 

    },
    {
      nom: "Consulta de servei de pagament amb l'ATIB per contractacions",
      ruta: `${urlBase}/pagamentContractacions`, 
    },
    { nom: "Consulta de servei d'estar matriculat a un centre educatiu",
    ruta: `${urlBase}/matricula`, 
  },
    { nom: 'Obtenir un justificant de verificació de dades' },
  ],
};

const familiaNombrosa = {
  titol: 'Validar emissors de família nombrosa',
  serveis: [
    { nom: 'Consulta de família nombrosa - Mallorca' },
    { nom: 'Consulta de família nombrosa - Menorca' },
  ],
};

const padro = {
  // Pendent -> encara no ho actualitz perquè pertany a un altre issue
  titol: 'Serveis de padró municipal',
  seccions: [
    {
      nom: 'Consulta de padró històric',
      serveis: [
        { nom: "Ajuntament d'Algaida", ruta: `${urlBase}/padroHistoric/004`  },
        { nom: 'Ajuntament de Palma', ruta: `${urlBase}/padroHistoric/040` },
        { nom: 'Ajuntament de Calvià',  ruta: `${urlBase}/padroHistoric/011`  },
        { nom: 'Ajuntament de Maó', ruta: `${urlBase}/padroHistoric/032`  },
      ],
    },
    {
      nom: 'Consulta de padró de convivència',
      serveis: [
        { nom: "Ajuntament d'Algaida", ruta: `${urlBase}/padroHistoric/004`  },
        { nom: 'Ajuntament de Palma', ruta: `${urlBase}/padroHistoric/040` },
        { nom: 'Ajuntament de Calvià',  ruta: `${urlBase}/padroHistoric/011`  },
        { nom: 'Ajuntament de Maó', ruta: `${urlBase}/padroHistoric/032`  },
      ],
    },


    
  ],
};

export {
  funcionamentPinbal,
  serveisPropis,
  padro,
  familiaNombrosa,
};
