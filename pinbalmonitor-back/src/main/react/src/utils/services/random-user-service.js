import APIRandomUser from './config/axios.config';

export default function obtenerUsuarioAleatorio() {
    return APIRandomUser.get('/'); // https://randomuser.me/api/
}
