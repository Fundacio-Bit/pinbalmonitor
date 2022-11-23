import { React, useState } from 'react';
import { useSessionStorage } from 'react-use';
import './Peticio.css';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import ReplayIcon from '@mui/icons-material/Replay';
import { CircularProgress, Tooltip } from '@mui/material';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from '@mui/icons-material/Error';
import PropTypes from 'prop-types';
import SnackbarResultat from '../snackbarResultat/SnackbarResultat';
import functions from '../helpers';

export default function Peticio(props) {
  const {
 id, nom, entorn, nombre,
} = props;
  const [loading, setLoading] = useState(false);
  const [resultatProva, setResultatProva] = useSessionStorage(
    `resultat ${id}`,
    '',
  );
  const [obrirSnackbar, setObrirSnackbar] = useState(false);
  const [ultimaProva, setUltimaProva] = useSessionStorage(
    `ultimaProva ${id}`,
    '',
  );

  /** Loading fals temporal per a poder fer els estils  de la petició quan carrega
   * TODO: Ha de ser esborrat quan tinguem el controlador.
   */

   function calcularResultatProva() {
    return Math.random() < 0.5
      ? setResultatProva('exit')
      : setResultatProva('fall');
  }

  function mockLoading() {
    // fetch temporal per simular request -> resultat aleatori
    setLoading(true);
    fetch('https://reqres.in/api/users?delay=3')
      .then((res) => {
        console.log(res);
        setLoading(false);
        calcularResultatProva();
        setUltimaProva(
          ` Última execució: ${new Date().toLocaleString('ca-ES', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
          })}`,
        );
      })
      .then(() => {
        setObrirSnackbar(true);
      })
      .catch((err) => {
        console.error(`Error: ${err.message}`);
        setResultatProva('');
        setLoading(false);
      });
  }

  function iconePlayOReplay() {
    return resultatProva === '' ? (
      <PlayArrowIcon className="executar-icone" />
    ) : (
      <ReplayIcon className="executar-icone" />
    );
  }

  function iconeResultat() {
    if (resultatProva && !loading) {
      return resultatProva === 'exit' ? (
        <CheckCircleIcon className="icone-output-exit icone-output">
          {' '}
        </CheckCircleIcon>
      ) : (
        <ErrorIcon className="icone-output-fall icone-output" />
      );
    }
    return {};
  }

  return (
    <li className="container-peticio" aria-label={id}>
      <Tooltip
        arrow
        placement="left-start"
        title={`${loading ? 'Executant...' : 'Executar'}`}
      >
        <button
          type="button"
          onClick={mockLoading}
          className={`peticio ${loading ? 'loading' : ''} ${
            resultatProva === 'exit' ? 'exit' : ''
          } ${resultatProva === 'fall' ? 'fall' : ''}`}
        >
          <div className="nombre-peticio-container">
            <span>
              {nombre}
              {' '}
            </span>
          </div>
          {!loading ? (
            <div className="text-peticio">
              <span className="nom-peticio">
                {nom}
                {' '}
                (
                {functions.capitalitzar(entorn)}
                )
              </span>
              {ultimaProva ? (
                <span className="timestamp">
                  {' '}
                  (
                  {' '}
                  {ultimaProva}
                  {' '}
                  )
                  {' '}
                </span>
              ) : (
                ''
              )}
            </div>
          ) : (
            ''
          )}
          <div className="executar-boto">
            {
              // Si està carregant tindrem loader, si no, botó de play
              loading ? (
                <CircularProgress style={{ color: 'var(--blanc)' }} />
              ) : (
                iconePlayOReplay()
              )
            }
          </div>
          {iconeResultat()}
        </button>
      </Tooltip>
      <SnackbarResultat
        obrir={obrirSnackbar}
        resultatProva={resultatProva}
        nom={nom}
        entorn={entorn}
        loading={loading}
      />
    </li>
  );
}

Peticio.propTypes = {
  id: PropTypes.string.isRequired,
  entorn: PropTypes.oneOf(['proves', 'produccio']).isRequired,
  nom: PropTypes.string.isRequired,
  nombre: PropTypes.number.isRequired,
};
