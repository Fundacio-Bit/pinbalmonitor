/* eslint-disable */ 
import  React, { useState } from "react";
import OpcionsEntorn from "../opcionsEntorn/OpcionsEntorn";
import Peticio from "../peticio/Peticio";
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

  /* Renderitzar totes les peticions que pertanyen procediment
   **(la info s'agafa de procediment/data/dadesSeveis) i és pasada per props
   ** des de l'enrutador a través del component pare (layout):
   */
  function renderitzarLlistaDePeticions(
    llistaPeticions,
    subservei /** paràmetre opcional */
  ) {
    return llistaPeticions.map((peticio, index) => (
      <Peticio
        entorn={entorn}
        id={`${peticio.nom}${subservei ? (" " + subservei.nom) : ""} ${entorn}`}
        key={`${peticio.nom} ${entorn}`}
        nombre={index + 1}
        {...peticio}
      />
    ));
  }
  function renderitzarProcedimentSimple() {
    let peticions = procediment.peticions;
    return (
      <ul className="peticions">{renderitzarLlistaDePeticions(peticions)}</ul>
    );
  }

  function renderitzarProcedimentAmbSubserveis() {
    let subserveis = procediment.subserveis;
    return subserveis.map((subservei, index) => (
      <div className="subservei" key={index}>
        <h2> {subservei.nom}</h2>
        <ul className="peticions">
          {renderitzarLlistaDePeticions(subservei.peticions, subservei)}
        </ul>
      </div>
    ));
  }
  const renderitzarProcediment = () => {
    return procediment.subserveis
      ? renderitzarProcedimentAmbSubserveis()
      : renderitzarProcedimentSimple();
  };

  return (
    <div className="servei">
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
