import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from 'react-router-dom';

import React from 'react';
import Layout from '../views/layoutServeis/Layout';
import rutesServeis from './rutesServeis';

const rutaLandingPage = rutesServeis[0].path;
const elementRutesAServeis = rutesServeis.map((ruta) => (
  <Route
    key={ruta.servei.titolServei}
    path={ruta.path}
    exact
    element={<Layout servei={ruta.servei} />}
  />
));

export default function Enrutador() {
  return (
    <div>
      <Router>
        <Routes>
          <Route
            path="/"
            element={<Navigate to={rutaLandingPage} replace />}
          />
          {elementRutesAServeis}
        </Routes>
      </Router>
    </div>
  );
}
