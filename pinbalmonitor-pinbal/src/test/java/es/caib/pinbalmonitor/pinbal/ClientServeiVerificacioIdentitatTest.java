package es.caib.pinbalmonitor.pinbal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.svddgpviws02.ClientSvddgpviws02;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientServeiVerificacioIdentitatTest {

  private static final Logger LOG = LoggerFactory.getLogger(
    ClientServeiVerificacioIdentitatTest.class
  );

  /**
   *
   */
  @Inject
  private Configuracio configuracio;

  private ClientSvddgpviws02 clientSvddgpviws02;
  private ClientSvddgpviws02.SolicitudSvddgpviws02 solicitud;
  private List<ClientSvddgpviws02.SolicitudSvddgpviws02> llistaSolicituds;

  @Valid
  private TitularModel titular;

  public TitularModel getTitular() {
    return titular;
  }

  @Valid
  private FuncionariModel funcionari;

  public ClientServeiVerificacioIdentitatTest() {}

 /* @BeforeClass
  public void setUpClass() {
    LOG.info("Iniciant client");
    clientSvddgpviws02 =
      new ClientSvddgpviws02(
        configuracio.getEndpoint(),
        configuracio.getUsuari(),
        configuracio.getSecret()
      );
    LOG.info("Client creat");
  } */

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {
    this.solicitud = new ClientSvddgpviws02.SolicitudSvddgpviws02();
    solicitud.setIdentificadorSolicitante(
      configuracio.getOrganismeSolicitant()
    );
    solicitud.setUnidadTramitadora(configuracio.getUnitatTramitadora());
    solicitud.setCodigoProcedimiento(configuracio.getCodiProcediment());
    solicitud.setFinalidad(configuracio.getFinalitat());
    solicitud.setConsentimiento(ScspSolicitante.ScspConsentimiento.Si);

    solicitud.setFuncionario(funcionari.toScspFuncionario());
    solicitud.setTitular(titular.toScspTitular());

    this.llistaSolicituds = Arrays.asList(solicitud);
  }

  @After
  public void tearDown() {}

  @Test
  public void testInit() throws Exception {}

  @Test
  public void testPeticioSincrona() throws Exception {
    assertNotNull(
      this.clientSvddgpviws02.peticionSincrona(this.llistaSolicituds)
    );
  }

  @Test
  public void testGetJustificant() throws Exception {
    assertNotNull(this.clientSvddgpviws02.getJustificante("parametrostring"));
    assertTrue(
      this.clientSvddgpviws02.getJustificante(
          "parametrostring"
        ) instanceof ScspJustificante
    );
  }
}
