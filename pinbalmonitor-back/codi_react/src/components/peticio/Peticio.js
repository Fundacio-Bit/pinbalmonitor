import React from "react";
import "./Peticio.css";

export default function Peticio(props) {
  let nom = props.nom;
  let entorn = props.entorn 
 // entorn -> variable que servirà per saber quina petició es cridarà 
  // en el controlador: la de producció o la de proves
  return (
    <div className="container-peticio">
      <span>{nom}</span>
      <button>Monitoritzar</button>
    </div>
  );
}
