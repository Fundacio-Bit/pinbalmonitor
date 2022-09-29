import React from 'react';
import './Menu.css';
import { useNavigate,  } from "react-router-dom";


export default function Menu() {
 let navigate = useNavigate();


  return (
    <div class="menu">
    Esto es el menú
      <span onClick={() =>navigate('/serveis-propis')}>Serveis Propis</span>
      <span onClick={() =>navigate('/serveis-padro')}>Serveis padró</span>
      <span onClick={() =>navigate('/serveis-familia-nombrosa')}>Serveis família nombrosa</span>
      <span onClick={() =>navigate('/serveis-emiserv')}>Serveis EMISERV</span>

    </div>
      
  )
}
