package es.caib.pinbalmonitor.pinbal;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svdsccdws01.ClientSvdsccdws01;
public class ClientDiscapacitat {
    

    private static final Logger LOG = LoggerFactory.getLogger(ClientDiscapacitat.class);

    @Inject
    private Configuracio configuracio;
    private ClientSvdsccdws01 ClientSvdsccdws01;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientSvdsccdws01 = new ClientSvdsccdws01(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvdsccdws01.SolicitudSvdsccdws01... solicituds) throws IOException {
        return ClientSvdsccdws01.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientSvdsccdws01.getJustificante(idPeticio);
    }
}
