import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";

import React from 'react'
import Layout from "./components/Layout";
import * as peticions from './components/serveis/data/dadesServeis'


export default function Enrutador() {
  return (
    <div>
    <Router>
      <Routes>
      <Route path="/" element= {<Navigate  to='/serveis-propis' replace={true} />} />
      <Route path="/serveis-propis" exact element={<Layout servei={peticions.dadesServeisPropis} />} />
      <Route path="/emissors-padro" exact element={<Layout servei={peticions.dadesFamiliaNombrosa}/>} />
      <Route path="/emissors-familia-nombrosa" exact element={<Layout servei={peticions.dadesPadro}/>} />
      <Route path="/funcionament-pinbal" exact element={<Layout servei={peticions.dadesFuncionamentPinbal}/>} />
      </Routes>
      </Router>

    </div>
  )
}
