const funcionamentPinbal = {
  titol: 'Validar el funcionament de PINBAL',

  serveis: [
    { nom: "Petició a un servei intermediat de l'estat" },
    { nom: 'Petició a un servei  oferit pel Govern' },
    {
      nom: 'Petició a un servei  oferit per una altra administració de la CAIB',
    },
    { nom: "Petició asíncrona a un servei intermediat de l'estat" },
    { nom: 'Obtenir justificant de verificació de dades' },
  ],
};

const serveisPropis = {
  titol: 'Validar serveis propis del GOIB',
  serveis: [
    {
      nom: 'Consulta de servei de Discapacitat',
    },

    {
      nom: "Consulta de servei de pagament amb l'ATIB per ajudes i subvencions",
    },
    { nom: "Consulta de servei de pagament amb l'ATIB per contractacions" },
    { nom: "Consulta de servei d'estar matriculat a un centre educatiu" },
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
        { nom: "Ajuntament d'Algaida" },
        { nom: 'Ajuntament de Palma' },
        { nom: 'Ajuntament de Calvià' },
        { nom: 'Ajuntament de Maó' },
      ],
    },
    {
      nom: 'Consulta de padró de convivència',
      serveis: [
        { nom: "Ajuntament d'Algaida" },
        { nom: 'Ajuntament de Palma' },
        { nom: 'Ajuntament de Calvià' },
        { nom: 'Ajuntament de Maó' },
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
