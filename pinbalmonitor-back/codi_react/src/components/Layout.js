import React from "react";
import Menu from "./menu/Menu";
import Servei from "./serveis/Servei";
import "./Layout.css";
import Navbar from "./navbar/Navbar";


export default function Layout(props) {
  return (
    <div className="layout">
    <Navbar/>
      <br />
      <Servei servei={props.servei} />
    </div>
  );
}
