import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";
import ServeisEMISERV from './components/serveis/ServeisEMISERV';
import ServeisPadro from './components/serveis/ServeisPadro';
import ServeisFamiliaNombrosa from './components/serveis/ServeisFamiliaNombrosa';
import ServeisPropis from './components/serveis/ServeisPropis';

import React from 'react'
import Home from "./components/Home";

export default function Enrutador() {
  return (
    <div>
    <Router>
      <Routes>
      <Route path="/" exact element={<Home />} />
      <Route path="/serveis-propis" exact element={<ServeisPropis />} />
      <Route path="/serveis-familia-nombrosa" exact element={<ServeisFamiliaNombrosa />} />
      <Route path="/serveis-padro" exact element={<ServeisPadro />} />
      <Route path="/serveis-emiserv" exact element={<ServeisEMISERV />} />
      </Routes>
      </Router>

    </div>
  )
}
