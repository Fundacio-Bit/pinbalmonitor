package es.caib.pinbalmonitor.plugins.policia;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbal.client.recobriment.svddgpciws02.ClientSvddgpciws02;
import es.caib.pinbalmonitor.plugins.Configuracio;
import es.caib.pinbalmonitor.plugins.FuncionariModel;
import es.caib.pinbalmonitor.plugins.TitularModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.cdimascio.dotenv.Dotenv;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

/**
 * Controlador d'exemple per emprar el servei de verificació d'identitat.
 *
 * @author areus
 */
@Named("policiaController")
@ViewScoped
public class PoliciaClientController implements Serializable {


    Dotenv dotenv =  Dotenv.load();

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(PoliciaClientController.class);

    /**
     * Injecta l'API del client del servei de verificació d'identitat
     */
    private ScspTipoDocumentacion DNI = ScspTipoDocumentacion.NIF;

    
    @Inject
    private Configuracio configuracio;
    @Inject
    private FacesContext context;

    @Valid
    private TitularModel titular = new TitularModel("41509405V", DNI, "Berta", "Vergés", "Prova");

    public TitularModel getTitular() {
        return titular;
    }

    @Valid
    private FuncionariModel funcionari = new FuncionariModel("41509405V", "Prova");

    public FuncionariModel getFuncionari() {
        return this.funcionari;
    }

    private ScspRespuesta resposta;


    public ScspRespuesta getResposta() {
        return resposta;
    }

        
    private ClientGeneric getClient(String entorn) {
    	ClientGeneric client;
    	LOG.info(entorn);
          if (entorn == "proves") {
        	client= new ClientGeneric(dotenv.get("ENDPOINT_PROVES"), dotenv.get("USUARI_PROVES"), dotenv.get("SECRET_PROVES"));

    	}
         
          else {
            LOG.info("pasa x el if");
   		 client= new ClientGeneric(dotenv.get("ENDPOINT_PROD"), dotenv.get("USUARI_PROD"), dotenv.get("SECRET_PROD"));

          }
		return client;
    }

    @PostConstruct
    protected void init() {
        LOG.info("init");
        // titular = new TitularModel("41509405V", ScspTipoDocumentacion.DNI, "Berta",
        // "Vergés", "Prova");
        // funcionari = new FuncionariModel("41509405V", "Prova");
    }

    /**
     * Neteja la resposta
     */
    public void neteja() {
        resposta = null;
    }

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public boolean confirmarIdentitat(String entorn) {
        LOG.info("confirmarIdentitat");

        LOG.info("Client creat");
        
        // Funcionari
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
    
        // Titular
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.DNI);
        titular.setDocumentacion("10000949C");

        Solicitud solicitud = new Solicitud();

    
        // Solicitant
        solicitud.setIdentificadorSolicitante("S0711001H");

        solicitud.setNombreSolicitante("Solicitant");
        // Procediment
        solicitud.setCodigoProcedimiento("CODSVDR_GBA_20121107");
        // Unitat tramitadora
        solicitud.setUnidadTramitadora("Unitat de test");
        solicitud.setCodigoUnidadTramitadora("A04123456");
        solicitud.setFinalidad("Finalitat solicitut");
        solicitud.setConsentimiento(ScspConsentimiento.Si);
        solicitud.setIdExpediente("ID-Expedient");
    
        solicitud.setFuncionario(funcionario);
        solicitud.setTitular(titular);
   	 	
        try {
          resposta = getClient(entorn).peticionSincrona("SVDDGPCIWS02", List.of(solicitud));
            LOG.info("resposta => " + getResposta());
            return true;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            // context.addMessage(null, message);
            Gson gson = new Gson();
            return (false /*+ gson.toJsonTree(solicitud)*/);
        }


    }

}
