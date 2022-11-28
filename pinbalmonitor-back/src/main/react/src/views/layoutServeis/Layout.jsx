/* eslint-disable */ 
import React, {useState} from 'react';
import Proptypes from 'prop-types';
import Servei from '../../components/serveis/Servei';
import './Layout.css';
import Menu from '../../components/menu/Menu'
import * as peticions from '../../components/serveis/data/dadesServeis';


export default function Layout() {

  const [servei, setServei] = useState(peticions.dadesServeisPropis);
  function renderitzarNouServei (servei) {
    setServei(servei)
  }
  return (
    <div className="layout">
    <Menu servei={servei} onClick={renderitzarNouServei}/>
      <br />
      <Servei servei={servei} />
    </div>
  );
}
