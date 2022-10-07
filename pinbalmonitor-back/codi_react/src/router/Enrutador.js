import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";

import React from "react";
import Layout from "../components/Layout";
import { rutesServeis } from "./rutesServeis";

let rutaLandingPage = rutesServeis[0].path;
let elementRutesAServeis = rutesServeis.map((ruta) => (
  <Route path={ruta.path} exact element={<Layout servei={ruta.servei} />} />
));

export default function Enrutador() {
  return (
    <div>
      <Router>
        <Routes>
          <Route
            path="/"
            element={<Navigate to={rutaLandingPage} replace={true} />}
          />
          {elementRutesAServeis}
        </Routes>
      </Router>
    </div>
  );
}
