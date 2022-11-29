/* eslint-disable */ 
import React, {useState} from 'react';
import Procediment from '../../components/Procediment/Procediment';
import './Layout.css';
import Menu from '../../components/menu/Menu'
import * as peticions from '../../components/procediment/data/dadesProcediments';


export default function Layout() {

  const [procediment, setProcediment] = useState(peticions.dadesServeisPropis);

  function renderitzarNouProcediment (procediment) {
    setProcediment(procediment)
  }
  return (
    <div className="layout">
    <Menu procediment={procediment} onClick={renderitzarNouProcediment}/>
      <br />
      <Procediment procediment={procediment} />
    </div>
  );
}
