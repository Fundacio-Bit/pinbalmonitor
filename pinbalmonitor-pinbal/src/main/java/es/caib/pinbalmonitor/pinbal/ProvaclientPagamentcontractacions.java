//     VALIDAR FUNCIONAMENT PINBAL (petició a un servei oferit pel govern )
//      corrent pagament CAIB subvencions  i ajudes

package es.caib.pinbalmonitor.pinbal;
import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svdccaacpcws01.ClientSvdccaacpcws01;
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
public class ProvaclientPagamentcontractacions {

    private static final Logger LOG = LoggerFactory.getLogger(ProvaclientPagamentcontractacions.class);

    @Inject
    private Configuracio configuracio;
    private ClientSvdccaacpcws01 ClientSvdccaacpcws01;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        ClientSvdccaacpcws01 = new ClientSvdccaacpcws01(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvdccaacpcws01.SolicitudSvdccaacpcws01... solicituds) throws IOException {
        return ClientSvdccaacpcws01.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return ClientSvdccaacpcws01.getJustificante(idPeticio);
    }

}
