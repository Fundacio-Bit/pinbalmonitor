const dadesFuncionamentPinbal = {
  titolServei: "Validar el funcionament de PINBAL",

  peticions: [
    { nom: "Petició a un servei intermediat de l'estat" },
    { nom: "Petició a un servei  oferit pel Govern" },
    {
      nom: "Petició a un servei  oferit per una altra administració de la CAIB",
    },
    { nom: "Petició asíncrona a un servei intermediat de l'estat" },
    { nom: "Obtenir justificant de verificació de dades" },
  ],
};

const dadesServeisPropis = {
  titolServei: "Validar serveis propis del GOIB",
  peticions: [
    {
      nom: "Consulta de servei de Discapacitat",
    },

    {
      nom: "Consulta de servei de pagament amb l'ATIB per ajudes i subvencions",
    },
    { nom: "Consulta de servei de pagament amb l'ATIB per contractacions" },
    { nom: "Consulta de servei d'estar matriculat a un centre educatiu" },
    { nom: "Obtenir un justificant de verificació de dades" },
  ],
};

const dadesFamiliaNombrosa = {
  titolServei: "Validar emissors de família nombrosa",
  peticions: [
    { nom: "Consulta de família nombrosa - Mallorca" },
    { nom: "Consulta de família nombrosa - Menorca" },
  ],
};

const dadesPadro = {
  // Pendent -> encara no ho actualitz perquè pertany a un altre issue
  titolServei: "Serveis de padró municipal",
  subserveis: [
    {
      nom: "Consulta de padró històric",
      peticions: [
        { nom: "Ajunatment d'Algaida" },
        { nom: "Ajunatment de Palma" },
        { nom: "Ajunatment de Calvià" },
        { nom: "Ajunatment de Maó" },
      ],
    },
    {
      nom: "Consulta de padró de convivència",
      peticions: [
        { nom: "Ajunatment d'Algaida" },
        { nom: "Ajunatment de Palma" },
        { nom: "Ajunatment de Calvià" },
        { nom: "Ajunatment de Maó" },
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
