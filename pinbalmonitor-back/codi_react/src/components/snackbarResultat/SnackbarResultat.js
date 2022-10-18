import * as React from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import { useEffect } from "react";
export default function SnackbarResultat(props) {
  let nomPeticio = props.nom;
  let resultatProva = props.resultatProva;
  let entorn = props.entorn;
  let loading = props.loading;
  const [obert, setObert] = React.useState(props.obert);

  useEffect(() => {
    // Obrir cada vegada que la petició acabi de carregar (és a dir quan s'hagi executat)
    if (loading === false) {
      setObert(props.obert);
    }
  }, [props.obert, loading]);

  const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  });

  const tancar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setObert(false);
  };

  function getTextResultat() {
    if (resultatProva === "exit") {
      return `La prova de la petició ${nomPeticio} ha estat exitosa en l'entorn de ${entorn}. 
      El servei està funcionant
    `;
    }
    if (resultatProva === "fall") {
      return `La prova de la petició ${nomPeticio} ha fallat l'entorn de ${entorn}.
        És possible que el servei no estigui funcionant. 
    `;
    } else {
      return `Error intern amb la prova. 
`;
    }
  }
  return (
    <div>
      <Snackbar
        open={obert}
        autoHideDuration={3000}
        onClose={tancar}
      >
        <Alert
          onClose={tancar}
          severity={`${resultatProva === "exit" ? "success" : "error"}`}
          sx={{ width: "100%" }}
        >
          {" "}
          {getTextResultat()}
        </Alert>
      </Snackbar>
    </div>
  );
}
