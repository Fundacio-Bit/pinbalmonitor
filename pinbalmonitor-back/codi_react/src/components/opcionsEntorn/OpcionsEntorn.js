import React from "react";
import {
  Radio,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
} from "@mui/material";

export default function OpcionsEntorn(props) {
  const { handleChange, entorn } = props;

  const radioStyles = {
    "&.Mui-checked": {
      color: "var(--color-primari)",
    },
  };

  const labelStyles = {
    "&.Mui-focused": {
      color: "var(--color-primari)",
    },
  };

  return (
    <div onChange={() => handleChange}>
      <FormControl>
        <FormLabel id="demo-controlled-radio-buttons-group" sx={labelStyles}>
          Seleccioni l'entorn
        </FormLabel>
        <RadioGroup
          row
          className="radio-group"
          aria-labelledby="demo-controlled-radio-buttons-group"
          name="controlled-radio-buttons-group"
          onChange={handleChange}
        >
          <FormControlLabel
            value="proves"
            control={
              <Radio
                sx={radioStyles}
                type="radio"
                checked={entorn === "proves"}
                value="proves"
                name="entorn"
                size="small"
                onChange={handleChange}
              />
            }
            label="Proves"
          />
          <FormControlLabel
            value="producio"
            control={
              <Radio
                sx={radioStyles}
                type="radio"
                checked={entorn === "producció"}
                value="producció"
                name="entorn"
                size="small"
                onChange={handleChange}
              />
            }
            label="Producció"
          />
        </RadioGroup>
      </FormControl>
    </div>
  );
}
