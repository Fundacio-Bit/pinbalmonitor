package es.caib.pinbalmonitor.pinbal;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.pinbal.client.recobriment.svddgpviws02.ClientSvddgpviws02;

public class ClientServeiVerificacioIdentitatTest {

	private static final Logger LOG = LoggerFactory.getLogger(ClientServeiVerificacioIdentitatTest.class);

	/**
	 *
	 */
	@Inject
    private Configuracio configuracio;

	private ClientSvddgpviws02 clientSvddgpviws02;

	public ClientServeiVerificacioIdentitatTest(){

	}

	@BeforeClass
	public void setUpClass(){
		LOG.info("Iniciant client");
        clientSvddgpviws02 = new ClientSvddgpviws02(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
	}

	@AfterClass
	public void tearDownClass(){

	}

	@Before
	public void setUp(){

	}	

	@After
	public void tearDown(){

	}

	@Test
	public void testInit() throws Exception {

	}

	@Test
	public void testPeticioSincrona() throws Exception {

	}

	@Test
	public void testGetJustificant() throws Exception {

	}

}
