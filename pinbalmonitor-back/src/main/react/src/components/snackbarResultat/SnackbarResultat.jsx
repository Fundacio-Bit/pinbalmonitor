import * as React from 'react';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import { useEffect } from 'react';
import PropTypes from 'prop-types';

export default function SnackbarResultat(props) {
  const {
    resultatProva, entorn, loading, nom, obrir,
  } = props;
  const nomPeticio = nom;
  const [obert, setObert] = React.useState(obrir);

  useEffect(() => {
    // Obrir cada vegada que la petició acabi de carregar (és a dir quan s'hagi executat)
    if (loading === false) {
      setObert(obert);
    }
  }, [obert, loading]);

  const Alert = React.forwardRef((ref) => <MuiAlert elevation={6} ref={ref} variant="filled" entorn nom resultatProva />);

  const tancar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setObert(false);
  };

  function getTextResultat() {
    if (resultatProva === 'exit') {
      return `La prova de la petició ${nomPeticio} ha estat exitosa en l'entorn de ${entorn}. 
      El servei està funcionant
    `;
    }
    if (resultatProva === 'fall') {
      return `La prova de la petició ${nomPeticio} ha fallat l'entorn de ${entorn}.
        És possible que el servei no estigui funcionant. 
    `;
    }
      return `Error intern amb la prova. 
`;
  }
  return (
    <div>
      <Snackbar
        className="snackbar"
        open={obert}
        autoHideDuration={3000}
        onClose={tancar}
      >
        <Alert
          onClose={tancar}
          severity={`${resultatProva === 'exit' ? 'success' : 'error'}`}
          sx={{ width: '100%' }}
        >
          {' '}
          {getTextResultat()}
        </Alert>
      </Snackbar>
    </div>
  );
}

SnackbarResultat.propTypes = {
  entorn: PropTypes.oneOf(['proves', 'produccio']).isRequired,
  nom: PropTypes.string.isRequired,
  resultatProva: PropTypes.oneOf(['exit', 'fall']).isRequired,
  loading: PropTypes.bool.isRequired,
  obrir: PropTypes.bool.isRequired,

};
