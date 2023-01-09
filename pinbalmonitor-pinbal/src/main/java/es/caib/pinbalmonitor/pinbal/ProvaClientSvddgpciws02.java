//     VALIDAR FUNCIONAMENT PINBAL (petició a un servei oferit pel govern )
//      corrent pagament CAIB subvencions  i ajudes

package es.caib.pinbalmonitor.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svddgpciws02.ClientSvddgpciws02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
/**
 * Adapter entorn al client del servei de verificació d'identitat.
 */
@ApplicationScoped
public class ProvaClientSvddgpciws02 {

    private static final Logger LOG = LoggerFactory.getLogger(ProvaClientSvddgpciws02.class);

    @Inject
    private Configuracio configuracio;
    private ClientSvddgpciws02 ClientSvddgpciws02;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientSvddgpciws02 = new ClientSvddgpciws02(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvddgpciws02.SolicitudSvddgpciws02... solicituds) throws IOException {
        return ClientSvddgpciws02.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientSvddgpciws02.getJustificante(idPeticio);
    }

}
