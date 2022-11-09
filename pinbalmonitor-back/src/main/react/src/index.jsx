import React from 'react';
import ReactDOM from 'react-dom';
// Importar componente APP
// eslint-disable-next-line import/extensions
import App from './components/App';

// Importamos las hojas de estilos CSS y SCSS
import './css/index.scss';
import './css/otro.css';

// Import de un export default
import saludo from './saludo';

// Import de la función para obtener un usuario aleatorio
import obtenerUsuario from './utils/services/random-user-service';

// Añadir el componente al archivo index.html en el elemento root
// ReactDOM.render(<App />, document.getElementById('root'));

// Añadir el componente al archivo index.html en el elemento content
ReactDOM.render(<App />, document.getElementById('root'));

console.log(`Prueba con Webpack: ${saludo}`); // Prueba con Webpack: Hola Martín

obtenerUsuario().then((respuesta) => {
    if (respuesta.status === 200) {
        const usuario = respuesta.data.results[0];
        const nombre = usuario.name.first;
        // Obtenemos el H1 del saludo
        const h1Saludo = document.getElementById('saludo');
        h1Saludo.innerHTML = `Hola, ${nombre}`; // Hola, usuario.name.first
    } else {
        console.log(`STATUS CODE ERROR: ${respuesta.status}`);
    }
}).catch(() => {
    console.log('ERROR OBTENIENDO UN USUARIO ALEATORIO');
});
