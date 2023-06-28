
package es.caib.pinbalmonitor.plugins.familiaNombrosa;

import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import javax.faces.application.FacesMessage;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbal.client.recobriment.svdsccdws01.ClientSvdsccdws01;
import es.caib.pinbalmonitor.plugins.Configuracio;
import es.caib.pinbalmonitor.plugins.FuncionariModel;
import es.caib.pinbalmonitor.plugins.TitularModel;
import io.github.cdimascio.dotenv.Dotenv;



import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
/**
 * Controlador d'exemple per emprar el servei de verificació d'identitat.
 *
 * @author areus
 */
@Named("pinbalFN")
@ViewScoped
public class ClientFamiliaNombrosaController implements Serializable {
    Dotenv dotenv = Dotenv.load();

 
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ClientFamiliaNombrosaController.class);

    /**
     * Injecta l'API del client del servei de verificació d'identitat
     */




    @Valid
    private TitularModel titular = new TitularModel("43117946F", ScspTipoDocumentacion.DNI, null, "Fuster", null);


    public TitularModel getTitular() {
        return titular;
    }

    @Valid
    private FuncionariModel funcionari;

    public FuncionariModel getFuncionari() {
        return funcionari;
    }

    private ScspRespuesta resposta;

    public ScspRespuesta getResposta() {
        return resposta;
    }

    /**
     * Neteja la resposta
     */
    public void neteja() {
        resposta = null;
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
    


            

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public boolean familiaNombrosa(String entorn) {
        LOG.info("solicitud discapacitat");


        
        // Funcionari
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
    
        // Titular
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
        titular.setDocumentacion("12345678Z");

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
    
        solicitud.setFuncionario(funcionario);
        solicitud.setTitular(titular);

      
        try {
            resposta = getClient(entorn).peticionSincrona("SVDSCTFNWS01", List.of(solicitud));
            LOG.info("resposta => " + getResposta());
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        return false;
        }
    }


   

}

