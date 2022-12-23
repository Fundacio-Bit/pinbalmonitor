package es.caib.pinbalmonitor.pinbal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.svdccaacpasws01.ClientSvdccaacpasws01;
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

public class ProvaClientSvdccaacpasws01Test {

  private static final Logger LOG = LoggerFactory.getLogger(
    ProvaClientSvdccaacpasws01Test.class
  );

  @Inject
  private Configuracio configuracio;

  private ClientSvdccaacpasws01 clientSvdccaacpasws01;
  private ClientSvdccaacpasws01.SolicitudSvdccaacpasws01 solicitud;
  private List<ClientSvdccaacpasws01.SolicitudSvdccaacpasws01> llistaSolicituds;

  @Valid
  private TitularModel titular;

  public TitularModel getTitular() {
    return titular;
  }

  @Valid
  private FuncionariModel funcionari;

  public ProvaClientSvdccaacpasws01Test() {}



  @Before
  public void setUp() {
    this.solicitud = new ClientSvdccaacpasws01.SolicitudSvdccaacpasws01();
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


  @Test
  public void testPeticioSincrona() throws Exception {
    assertNotNull(
      this.clientSvdccaacpasws01.peticionSincrona(this.llistaSolicituds)
    );
  }

  @Test
  public void testGetJustificant() throws Exception {
    assertNotNull(
      this.clientSvdccaacpasws01.getJustificante("parametrostring")
    );
    assertTrue(
      this.clientSvdccaacpasws01.getJustificante(
          "parametrostring"
        ) instanceof ScspJustificante
    );
  }
}
