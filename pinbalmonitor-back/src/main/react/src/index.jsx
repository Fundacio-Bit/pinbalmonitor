import React from 'react';
import ReactDOM from 'react-dom';
// Importar componente APP
// eslint-disable-next-line import/extensions
import App from './components/App';

// Importamos las hojas de estilos CSS y SCSS
import './css/index.scss';
import './css/layout.scss';

// Añadir el componente al archivo index.xhtml en el elemento root
ReactDOM.render(<App />, document.getElementById('root'));
