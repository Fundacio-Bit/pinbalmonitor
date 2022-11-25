import React from 'react';
import Layout from '../views/layoutServeis/Layout';
import * as peticions from './serveis/data/dadesServeis';

export default function App() {
    return (
      <div className="App">
        <Layout servei={peticions.dadesPadro} />
      </div>
    );
}
