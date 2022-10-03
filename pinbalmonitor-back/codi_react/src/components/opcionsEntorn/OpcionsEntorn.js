import React from "react";

export default function OpcionsEntorn(props) {
  const {handleChange,entorn} =props



  return (
    <div onChange={() =>handleChange}>
      <input type="radio" checked={entorn === 'proves'} value="proves" name="entorn" onChange={handleChange} /> Proves
      <input type="radio"  checked={entorn === 'produccio'} value="produccio" name="entorn"  onChange={handleChange} /> Producció
    </div>
  );
}
