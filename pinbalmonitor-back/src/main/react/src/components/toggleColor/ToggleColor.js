import React from "react";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import Brightness4Icon from "@mui/icons-material/Brightness4";
import Brightness7Icon from "@mui/icons-material/Brightness7";
import { useLocalStorage } from "react-use";
import { useEffect } from "react";

const ToggleColor = () => {
  const [tema, setTema] = useLocalStorage("tema", "clar");

  function toggleTema() {
    if (tema === "clar") {
      setTema("fosc");
    }
    if (tema === "fosc") {
      setTema("clar");
    }
  }
  const root = document.querySelector(":root");
  useEffect(() => {
    // Obrir cada vegada que la petició acabi de carregar (és a dir quan s'hagi executat)
    if (tema === "fosc") {
      root.classList.add("fosc");
    }
    if (tema === "clar") {
      root.classList.remove("fosc");
    }
  }, [tema]);
  return (
    <Tooltip
      arrow
      placement="left"
      title={`${
        tema === "clar" ? "Cambiar a tema fosc" : "Cambiar a tema clar "
      }`}
    >
      <IconButton
        sx={{ fontSize: "1rem" }}
        onClick={toggleTema}
        color="inherit"
      >
        {tema === "clar" ? (
          <Brightness4Icon sx={{ fontSize: "1.3rem" }} />
        ) : (
          <Brightness7Icon sx={{ fontSize: "1.3rem" }} />
        )}
      </IconButton>
    </Tooltip>
  );
};

export default ToggleColor;
