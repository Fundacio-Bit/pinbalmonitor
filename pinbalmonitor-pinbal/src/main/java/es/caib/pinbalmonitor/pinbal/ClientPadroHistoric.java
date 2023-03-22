package es.caib.pinbalmonitor.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import es.caib.pinbal.client.recobriment.scdhpaju.ClientScdhpaju;

@ApplicationScoped
public class ClientPadroHistoric {

    private static final Logger LOG = LoggerFactory.getLogger(ClientPadroHistoric.class);

    @Inject
    private Configuracio configuracio;
    private ClientScdhpaju ClientScdhpaju;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientScdhpaju = new ClientScdhpaju(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientScdhpaju.SolicitudScdhpaju... solicituds) throws IOException {
        return ClientScdhpaju.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientScdhpaju.getJustificante(idPeticio);
    }

}
