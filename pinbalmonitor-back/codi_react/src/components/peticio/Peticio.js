import { React, useState, useEffect } from "react";
import "./Peticio.css";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import ReplayIcon from "@mui/icons-material/Replay";
import { CircularProgress, Tooltip } from "@mui/material";

export default function Peticio(props) {
  const [loading, setLoading] = useState(false);
  const [resultatProva, setResultatProva] = useState("");

  /** Loading fals temporal per a poder fer els estils  de la petició quan carrega
   * TODO: Ha de ser esborrat quan tinguem el controlador.
   */
  function mockLoading() {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      /* Temporalment
      Per fer sa prova d'una petició exitosa es fa a proves,
       per fer sa prova d'un error es fa a producció"*/
      entorn === "proves" ? setResultatProva("exit") : setResultatProva("fall");
    }, 2000);
  }

  function iconePlayOReplay() {
    return resultatProva === "" ? (
      <PlayArrowIcon className="executar-icone"></PlayArrowIcon>
    ) : (
      <ReplayIcon className="executar-icone"></ReplayIcon>
    );
  }

  let nom = props.nom;
  let entorn = props.entorn;
  // entorn -> variable que servirà per saber quina petició es cridarà
  // en el controlador: la de producció o la de proves

  useEffect(() => {
    setResultatProva("");
  }, [entorn, props.servei]);

  return (
    <Tooltip
      arrow
      placement="left-start"
      title={`${loading ? "Executant..." : "Executar"}`}
    >
      <button
        onClick={mockLoading}
        className={`container-peticio ${loading ? "loading" : ""} ${
          resultatProva === "exit" ? "exit" : ""
        } ${resultatProva === "fall" ? "fall" : ""}`}
      >
        <div className="nombre-peticio-container">
          <span>{props.nombre}</span>
        </div>
        {!loading ? (
          <div className="text-peticio">
            <span className="nom-peticio">{nom}</span>
          </div>
        ) : (
          ""
        )}
        <div className="executar-boto">
          {
            // Si està carregant tindrem loader, si no, botó de play
            loading ? (
              <CircularProgress style={{color:"var(--blanc)"}} />
            ) : (
              iconePlayOReplay()
            )
          }
        </div>
      </button>
      
    </Tooltip>
    
  );
}
