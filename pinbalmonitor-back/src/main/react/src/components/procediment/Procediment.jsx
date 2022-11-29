/* eslint-disable */ 
import  React, { useState } from "react";
import OpcionsEntorn from "../opcionsEntorn/OpcionsEntorn";
import Servei from "../servei/Servei";
import functions from "../helpers";
import "./Procediment.css";

export default function Procediment(props) {
  let procediment = props.procediment;
  let titol = procediment.titol;
  /* La variable d'entorn serveix per ser passada al component
   ** fill per a que la petició apunti a la url corresponent.
   ** L'entorn és modificat a través del component Opcions entorn
   ** que accedeix al handlechangei i a l'entorn a través de props.
   */
  const [entorn, setEntorn] = useState("proves");
  function handleChange(e) {
    setEntorn(e.target.value);
  }

  /* Renderitzar tots els serveis que pertanyen procediment
   **(la info s'agafa de procediment/data/dadesSeveis) i és pasada per props
   ** des de l'enrutador a través del component pare (layout):
   */
  function renderitzarLlistaDeServeis(
    llistaServeis,
    seccio /** paràmetre opcional */
  ) {
    return llistaServeis.map((s, index) => (
      <Servei
        entorn={entorn}
        id={`${s.nom}${seccio ? (" " + seccio.nom) : ""} ${entorn}`}
        key={`${s.nom} ${entorn}`}
        nombre={index + 1}
        {...s}
      />
    ));
  }
  function renderitzarProcedimentSimple() {
    let serveis = procediment.serveis;
    return (
      <ul className="serveis">{renderitzarLlistaDeServeis(serveis)}</ul>
    );
  }

  function renderitzarProcedimentAmbSeccions() {
    let seccions = procediment.seccions;
    return seccions.map((s, index) => (
      <div className="seccio" key={index}>
        <h2> {s.nom}</h2>
        <ul className="serveis">
          {renderitzarLlistaDeServeis(s.serveis, s)}
        </ul>
      </div>
    ));
  }
  const renderitzarProcediment = () => {
    return procediment.seccions
      ? renderitzarProcedimentAmbSeccions()
      : renderitzarProcedimentSimple();
  };

  return (
    <div className="procediment">
      <div className="superior">
        <h1>
          {titol} -{" "}
          <span
            className={`text-entorn ${
              entorn === "proves" ? "text-proves" : "text-producció"
            }`}
          >
            {functions.capitalitzar(entorn)}
          </span>
        </h1>
        <OpcionsEntorn handleChange={handleChange} entorn={entorn} />
      </div>
      {renderitzarProcediment()}
    </div>
  );
}
