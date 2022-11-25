/* eslint-disable */ 
import React from 'react';
import Proptypes from 'prop-types';
import Servei from '../../components/serveis/Servei';
import './Layout.css';
export default function Layout(props) {
  const { servei } = props;
  return (
    <div className="layout">
      <br />
      <Servei servei={servei} />
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
