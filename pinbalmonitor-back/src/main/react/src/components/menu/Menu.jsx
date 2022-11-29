/* eslint-disable */
import React from 'react';
import Button from "@mui/material/Button";
import "./Menu.css";
import rutes from "./rutesProcediments";


const Menu = (props) => {
  const canviarProcediment = props.onClick

  return (
    <div className="menu-container">
      <div className="menu">

        { rutes.map((r) => (
          <Button
          className={props.procediment===r.procediment ? 'actiu' : ''} 
            variant="contained"
            size="medium"
            onClick={() => canviarProcediment(r.procediment)}
          >
          {r.nomAMenu}
        </Button>
        ))}

      </div>
    </div>
  );
}

export default Menu;
