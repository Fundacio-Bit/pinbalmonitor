import React from "react";
import "./Menu.css";
import {  NavLink } from "react-router-dom";

export default function Menu() {

  return (
    <nav className="menu">
      <NavLink to="/serveis-propis" className={isActive => ({
         color: isActive ? "green" : "blue"
      })}>
        Serveis Propis
      </NavLink>
      <NavLink to="/serveis-padro" activeClassName="selected">
        Serveis padró
      </NavLink>
      <NavLink to="/serveis-familia-nombrosa" activeClassName="selected">
        Serveis família nombrosa
      </NavLink>
      <NavLink to="/serveis-emiserv" activeClassName="selected">
        Serveis EMISERV
      </NavLink>
    </nav>
  );
}
