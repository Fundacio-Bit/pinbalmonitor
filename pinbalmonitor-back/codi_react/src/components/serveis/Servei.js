import React from "react";
import Peticio from "../peticio/Peticio";
import './Servei.css';

export default function Servei(props) {
let peticions = props.servei.peticions;

let titolServei = props.servei.titolServei 

const peticionsElement = peticions.map((peticio, index) => (
  // Esto debería ser sustituido por un componente Persona.js
  <Peticio key={index} nom={peticio.nom}
  
  />
));
  return (
    <div>
    {titolServei} 
    <div className="peticions">
    {peticionsElement}
    </div>
    </div>
  );
}
