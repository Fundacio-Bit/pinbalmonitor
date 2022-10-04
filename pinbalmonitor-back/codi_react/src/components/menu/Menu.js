import React from "react";
import "./Menu.css";
import { NavLink } from "react-router-dom";

export default function Menu() { 
  return (
    <nav className="menu">
      <NavLink to="/serveis-propis">Serveis Propis de GOIB</NavLink>
      <NavLink to="/emissors-padro">Emissors padró</NavLink>
      <NavLink to="/emissors-familia-nombrosa">Emissors família nombrosa</NavLink>
      <NavLink to="/funcionament-pinbal">Validar funcionament de PINBAL</NavLink>
    </nav>
  );
}
