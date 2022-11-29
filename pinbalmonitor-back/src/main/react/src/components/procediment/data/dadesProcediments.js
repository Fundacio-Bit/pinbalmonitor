const dadesFuncionamentPinbal = {
  titol: 'Validar el funcionament de PINBAL',

  peticions: [
    { nom: "Petició a un servei intermediat de l'estat" },
    { nom: 'Petició a un servei  oferit pel Govern' },
    {
      nom: 'Petició a un servei  oferit per una altra administració de la CAIB',
    },
    { nom: "Petició asíncrona a un servei intermediat de l'estat" },
    { nom: 'Obtenir justificant de verificació de dades' },
  ],
};

const dadesServeisPropis = {
  titol: 'Validar serveis propis del GOIB',
  peticions: [
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

const dadesFamiliaNombrosa = {
  titol: 'Validar emissors de família nombrosa',
  peticions: [
    { nom: 'Consulta de família nombrosa - Mallorca' },
    { nom: 'Consulta de família nombrosa - Menorca' },
  ],
};

const dadesPadro = {
  // Pendent -> encara no ho actualitz perquè pertany a un altre issue
  titol: 'Serveis de padró municipal',
  subserveis: [
    {
      nom: 'Consulta de padró històric',
      peticions: [
        { nom: "Ajuntament d'Algaida" },
        { nom: 'Ajuntament de Palma' },
        { nom: 'Ajuntament de Calvià' },
        { nom: 'Ajuntament de Maó' },
      ],
    },
    {
      nom: 'Consulta de padró de convivència',
      peticions: [
        { nom: "Ajuntament d'Algaida" },
        { nom: 'Ajuntament de Palma' },
        { nom: 'Ajuntament de Calvià' },
        { nom: 'Ajuntament de Maó' },
      ],
    },
  ],
};

export {
  dadesFuncionamentPinbal,
  dadesServeisPropis,
  dadesPadro,
  dadesFamiliaNombrosa,
};
