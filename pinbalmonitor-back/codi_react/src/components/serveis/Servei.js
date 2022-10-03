import { React, useState } from "react";
import OpcionsEntorn from "../opcionsEntorn/OpcionsEntorn";
import Peticio from "../peticio/Peticio";
import "./Servei.css";

export default function Servei(props) {
  let titolServei = props.servei.titolServei;

  /* Renderitzar totes les peticions que pertanyen servei 
  (la info s'agafa de serveis/data/dadesSeveis) i és pasada per props
  des de l'enrutador a través del component pare (layout):
  */
  let peticions = props.servei.peticions;
  const peticionsElement = peticions.map((peticio, index) => (
    <Peticio key={index} {...peticio} />
  ));

  const [entorn, setEntorn] = useState("proves");
  function handleChange(e) {
    setEntorn(e.target.value);

  }

  return (
    <div className="servei">
      <div className="superior">
        <span>{titolServei}</span>
        <OpcionsEntorn handleChange={handleChange} entorn={entorn} />
      </div>
      <div className="peticions">{peticionsElement}</div>
    </div>
  );
}
