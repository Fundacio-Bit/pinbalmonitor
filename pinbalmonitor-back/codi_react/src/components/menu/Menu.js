import React from "react";
import "./Menu.css";
import { NavLink } from "react-router-dom";

export default function Menu() {
  return (
    <nav className="menu">
      <NavLink to="/serveis-propis">Serveis Propis</NavLink>
      <NavLink to="/serveis-padro">Serveis padró</NavLink>
      <NavLink to="/serveis-familia-nombrosa">Serveis família nombrosa</NavLink>
      <NavLink to="/serveis-emiserv">Serveis EMISERV</NavLink>
    </nav>
  );
}
