import React from "react";
import Menu from "./menu/Menu";
import Servei from "./serveis/Servei";
import "./Layout.css";


export default function Layout(props) {
  return (
    <div className="layout">
      <Menu />
      <br />
      <Servei servei={props.servei} />
    </div>
  );
}
