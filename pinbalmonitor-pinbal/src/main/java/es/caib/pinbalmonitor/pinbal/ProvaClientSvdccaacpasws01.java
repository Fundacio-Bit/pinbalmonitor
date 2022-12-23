//     VALIDAR FUNCIONAMENT PINBAL (petició a un servei oferit pel govern )
//      corrent pagament CAIB subvencions  i ajudes

package es.caib.pinbalmonitor.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svdccaacpasws01.ClientSvdccaacpasws01;
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
public class ProvaClientSvdccaacpasws01 {

    private static final Logger LOG = LoggerFactory.getLogger(ProvaClientSvdccaacpasws01.class);

    @Inject
    private Configuracio configuracio;
    private ClientSvdccaacpasws01 ClientSvdccaacpasws01;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientSvdccaacpasws01 = new ClientSvdccaacpasws01(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvdccaacpasws01.SolicitudSvdccaacpasws01... solicituds) throws IOException {
        return ClientSvdccaacpasws01.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientSvdccaacpasws01.getJustificante(idPeticio);
    }

}
