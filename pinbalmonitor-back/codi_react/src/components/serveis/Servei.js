import { React, useState } from "react";
import OpcionsEntorn from "../opcionsEntorn/OpcionsEntorn";
import Peticio from "../peticio/Peticio";
import "./Servei.css";

export default function Servei(props) {
  let titolServei = props.servei.titolServei;
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
  let peticions = props.servei.peticions;
  const peticionsElement = peticions.map((peticio, index) => (
    <Peticio entorn={entorn} key={index} {...peticio} />
  ));

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
