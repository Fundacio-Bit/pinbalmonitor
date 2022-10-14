import React from "react";
import Servei from "../../components/serveis/Servei";
import "./Layout.css";
import Navbar from "../../components/navbar/Navbar";
import   BotoScrollup  from "../../components/botoScrollup/BotoScrollup"

export default function Layout(props) {
  return (
    <div className="layout">
    <Navbar/>
      <br />
      <Servei servei={props.servei} />
      <BotoScrollup/>
    </div>
  );
}
