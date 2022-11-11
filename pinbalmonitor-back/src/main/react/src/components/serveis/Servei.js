import { React, useState } from "react";
import OpcionsEntorn from "../opcionsEntorn/OpcionsEntorn";
import Peticio from "../peticio/Peticio";
import functions from "../helpers";
import "./Servei.css";

export default function Servei(props) {
  let servei = props.servei;
  let titolServei = servei.titolServei;
  /* La variable d'entorn serveix per ser passada al component
   ** fill per a que la petició apunti a la url corresponent.
   ** L'entorn és modificat a través del component Opcions entorn
   ** que accedeix al handlechangei i a l'entorn a través de props.
   */
  const [entorn, setEntorn] = useState("proves");
  function handleChange(e) {
    setEntorn(e.target.value);
  }

  /* Renderitzar totes les peticions que pertanyen servei
   **(la info s'agafa de serveis/data/dadesSeveis) i és pasada per props
   ** des de l'enrutador a través del component pare (layout):
   */
  function renderitzarLlistaDePeticions(
    llistaPeticions,
    subservei /** paràmetre opcional */
  ) {
    return llistaPeticions.map((peticio, index) => (
      <Peticio
        entorn={entorn}
        id={`${peticio.nom}${subservei ? (" " + subservei.nom) : ""} ${ entorn}`}
        key={`${peticio.nom} ${entorn}`}
        nombre={index + 1}
        {...peticio}
      />
    ));
  }
  function renderitzarServeiSimple() {
    let peticions = servei.peticions;
    return (
      <ul className="peticions">{renderitzarLlistaDePeticions(peticions)}</ul>
    );
  }

  function renderitzarServeiAmbSubserveis() {
    let subserveis = servei.subserveis;
    return subserveis.map((subservei, index) => (
      <div className="subservei" key={index}>
        <h2> {subservei.nom}</h2>
        <ul className="peticions">
          {renderitzarLlistaDePeticions(subservei.peticions, subservei)}
        </ul>
      </div>
    ));
  }
  const renderitzarServei = () => {
    return servei.subserveis
      ? renderitzarServeiAmbSubserveis()
      : renderitzarServeiSimple();
  };

  return (
    <div className="servei">
      <div className="superior">
        <h1>
          {titolServei} -{" "}
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
      {renderitzarServei()}
    </div>
  );
}
