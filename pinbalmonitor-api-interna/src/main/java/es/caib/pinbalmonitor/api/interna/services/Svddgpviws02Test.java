public class Svddgpviws02Test {

	private static final String URL_BASE = "https://proves.caib.es/pinbalapi";
	private static final String USUARI = "USER";
	private static final String CONTRASENYA = "PASSWD";

	private ClientSvddgpviws02 client = new ClientSvddgpviws02(
			URL_BASE, USUARI, CONTRASENYA);

	@Test
	public void peticionSincrona() {
		SolicitudSvddgpviws02 solicitud = new SolicitudSvddgpviws02();
		solicitud.setIdentificadorSolicitante("B07167448");
		solicitud.setCodigoProcedimiento("ProvaConcepte");
		solicitud.setUnidadTramitadora("Departament de test");
		solicitud.setFinalidad("Test peticionSincrona");
		solicitud.setConsentimiento(ScspConsentimiento.Si);
		ScspFuncionario funcionario = new ScspFuncionario();
		funcionario.setNifFuncionario("00000000T");
		funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
		solicitud.setFuncionario(funcionario);
		ScspTitular titular = new ScspTitular();
		titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
		titular.setDocumentacion("12345678Z");
		solicitud.setTitular(titular);
		ScspRespuesta respuesta =
			client.peticionSincrona(Arrays.asList(solicitud));
		assertNotNull(respuesta);
	}

}