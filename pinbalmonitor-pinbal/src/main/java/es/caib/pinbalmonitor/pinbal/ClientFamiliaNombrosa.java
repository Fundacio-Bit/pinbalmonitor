package es.caib.pinbalmonitor.pinbal;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svdsctfnws01.ClientSvdsctfnws01;
public class ClientFamiliaNombrosa {
    

    private static final Logger LOG = LoggerFactory.getLogger(ClientFamiliaNombrosa.class);

    @Inject
    private Configuracio configuracio;
    private ClientSvdsctfnws01 ClientSvdsctfnws01;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientSvdsctfnws01 = new ClientSvdsctfnws01(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvdsctfnws01.SolicitudSvdsctfnws01... solicituds) throws IOException {
        return ClientSvdsctfnws01.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientSvdsctfnws01.getJustificante(idPeticio);
    }
}
