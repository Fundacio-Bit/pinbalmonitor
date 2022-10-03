import React from "react";
import "./Peticio.css";

export default function Peticio(props) {
  let nom = props.nom;

 
  return (
    <div className="container-peticio">
      <span>{nom}</span>
      <button>Monitoritzar</button>
    </div>
  );
}
