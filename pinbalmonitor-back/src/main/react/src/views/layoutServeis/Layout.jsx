import React from 'react';
import Proptypes from 'prop-types';
import BotoScrollup from '../../components/botoScrollup/BotoScrollup';
import Navbar from '../../components/navbar/Navbar';
import Servei from '../../components/serveis/Servei';
import './Layout.css';

export default function Layout(props) {
  const { servei } = props;
  return (
    <div className="layout">
      <Navbar />
      <br />
      <Servei servei={servei} />
      <BotoScrollup />
    </div>
  );
}

Layout.propTypes = {
  servei: Proptypes.oneOfType([Proptypes.shape({
    titolServei: Proptypes.string.isRequired,
    peticions: Proptypes.arrayOf(Proptypes.obj).isRequired,
  }),
    Proptypes.shape({
      titolServei: Proptypes.string.isRequired,
      subserveis: Proptypes.arrayOf(Proptypes.shape({
        nom: Proptypes.string.isRequired,
        peticions: Proptypes.arrayOf(Proptypes.obj).isRequired,
      })),
     })]).isRequired,

};
