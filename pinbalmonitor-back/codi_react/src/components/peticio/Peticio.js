import React from 'react'
import './Peticio.css'

function monitoritzar(){

}

function ferCridadaPINBAL(){

}
export default function Peticio(props) {
let nom= props.nom
  return (
    <div className="container-peticio">
    <span>{nom}</span>
    <button onClick={monitoritzar}>Monitoritzar</button>
    </div>
  )
}
