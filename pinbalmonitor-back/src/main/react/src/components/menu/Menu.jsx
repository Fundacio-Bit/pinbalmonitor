/* eslint-disable */
import React from 'react';
import Button from "@mui/material/Button";
import "./Menu.css";
import rutesServeis from "./rutesServeis";


const Menu = (props) => {
  const canviarServei = props.onClick

  return (
    <div className="menu-container">
      <div className="menu">

        { rutesServeis.map((p) => (
          <Button
          className={props.servei===p.servei ? 'actiu' : ''} 
            variant="contained"
            size="medium"
            onClick={() => canviarServei(p.servei)}
          >
          {p.nomAMenu}
        </Button>
        ))}

      </div>
    </div>
  );
}

export default Menu;
