/* eslint-disable */ 
import  React, { useState } from "react";
import { useSessionStorage } from "react-use";
import "./Peticio.css";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import ReplayIcon from "@mui/icons-material/Replay";
import { CircularProgress, Tooltip } from "@mui/material";
import SnackbarResultat from "../snackbarResultat/SnackbarResultat";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ErrorIcon from "@mui/icons-material/Error";
import functions from "../helpers";
export default function Peticio(props) {
  const [loading, setLoading] = useState(false);
  const [resultatProva, setResultatProva] = useSessionStorage(
    `resultat ${props.id}`, ""
  );
  const [obrirSnackbar, setObrirSnackbar] = useState(false);
  const [ultimaProva, setUltimaProva] = useSessionStorage(
    `ultimaProva ${props.id}`,
    ""
  );

  /** Loading fals temporal per a poder fer els estils  de la petició quan carrega
   * TODO: Ha de ser esborrat quan tinguem el controlador.
   */
  function mockLoading() {
    //fetch temporal per simular request -> resultat aleatori
    setLoading(true);
    fetch("https://reqres.in/api/users?delay=3")
      .then((res) => {
        console.log(res);
        setLoading(false);
        Math.random() < 0.5
          ? setResultatProva("exit")
          : setResultatProva("fall");
        setUltimaProva(
          " Última execució: " +
            new Date().toLocaleString("ca-ES", {
              weekday: "long",
              year: "numeric",
              month: "long",
              day: "numeric",
              hour: "2-digit",
              minute: "2-digit",
            })
        );
      })
      .then(() => {
        setObrirSnackbar(true);
      })
      .catch((err) => {
        console.error(`Error: ${err.message}`);
        setResultatProva("");
        setLoading(false);
      });
  }

  function iconePlayOReplay() {
    return resultatProva === "" ? (
      <PlayArrowIcon className="executar-icone"></PlayArrowIcon>
    ) : (
      <ReplayIcon className="executar-icone"></ReplayIcon>
    );
  }

  function iconeResultat() {
    if (resultatProva && !loading)
      return resultatProva === "exit" ? (
        <CheckCircleIcon className="icone-output-exit icone-output">
          {" "}
        </CheckCircleIcon>
      ) : (
        <ErrorIcon className="icone-output-fall icone-output"></ErrorIcon>
      );
  }

  let nom = props.nom;
  let entorn = props.entorn;
  // entorn -> variable que servirà per saber quina petició es cridarà
  // en el controlador: la de producció o la de proves

  return (
    <li className="container-peticio" aria-label={props.id}>
      <Tooltip
        arrow
        placement="left-start"
        title={`${loading ? "Executant..." : "Executar"}`}
      >
        <button
          onClick={mockLoading}
          className={`peticio ${loading ? "loading" : ""} ${
            resultatProva === "exit" ? "exit" : ""
          } ${resultatProva === "fall" ? "fall" : ""}`}
        >
          <div className="nombre-peticio-container">
            <span>{props.nombre} </span>
          </div>
          {!loading ? (
            <div className="text-peticio">
              <span className="nom-peticio">{nom} ({functions.capitalitzar(entorn)})</span>
              {ultimaProva ? (
                <span className="timestamp"> ( {ultimaProva} ) </span>
              ) : (
                ""
              )}
            </div>
          ) : (
            ""
          )}
          <div className="executar-boto">
            {
              // Si està carregant tindrem loader, si no, botó de play
              loading ? (
                <CircularProgress style={{ color: "var(--blanc)" }} />
              ) : (
                iconePlayOReplay()
              )
            }
          </div>
          {iconeResultat()}
        </button>
      </Tooltip>
      <SnackbarResultat
        obert={obrirSnackbar}
        resultatProva={resultatProva}
        nom={nom}
        entorn={entorn}
        loading={loading}
      ></SnackbarResultat>
    </li>
  );
}
