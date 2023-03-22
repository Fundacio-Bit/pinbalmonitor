
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

import es.caib.pinbal.client.recobriment.q2827003atgss001.ClientQ2827003atgss001;

@ApplicationScoped
public class ClientServeiCorrentPagamentAmbSS {

    private static final Logger LOG = LoggerFactory.getLogger(ClientServeiCorrentPagamentAmbSS.class);

    @Inject
    private Configuracio configuracio;
    private  ClientQ2827003atgss001 ClientQ2827003atgss001 ;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientQ2827003atgss001 = new ClientQ2827003atgss001(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientQ2827003atgss001.SolicitudQ2827003atgss001... solicituds) throws IOException {
        return ClientQ2827003atgss001.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientQ2827003atgss001.getJustificante(idPeticio);
    }
    
}
