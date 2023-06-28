
package es.caib.pinbalmonitor.plugins.padro;
import es.caib.pinbal.client.recobriment.scdhpaju.ClientScdhpaju;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbalmonitor.plugins.DatosEspecificos;
import es.caib.pinbalmonitor.plugins.FuncionariModel;
import es.caib.pinbalmonitor.plugins.TitularModel;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Controlador d'exemple per emprar el servei de verificació d'identitat.
 *
 * @author areus
 */
@Named("pinbalPadroH")
@ViewScoped
public class ClientPadroHistoricController implements Serializable {


   // Dotenv dotenv = Dotenv.load();

 
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ClientPadroHistoricController.class);

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
    @Valid
    private DatosEspecificos datosEspecificos;
    Dotenv dotenv = Dotenv.load();


    private  final boolean ENABLE_LOGGING = true;

    private ClientScdhpaju getClient(String entorn) {
    	ClientScdhpaju client;
    	LOG.info(entorn);
          if (entorn == "proves") {
        	client= new ClientScdhpaju(dotenv.get("ENDPOINT_PROVES"), dotenv.get("USUARI_PROVES"), dotenv.get("SECRET_PROVES"));

    	}
         
          else {
   		 client= new ClientScdhpaju(dotenv.get("ENDPOINT_PROD"), dotenv.get("USUARI_PROD"), dotenv.get("SECRET_PROD"));

          }
		return client;
    }
    


            

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public boolean padro(String codiMunicipi, String entorn)  {
        ClientScdhpaju.SolicitudScdhpaju solicitud = new ClientScdhpaju.SolicitudScdhpaju();
        solicitud.setIdentificadorSolicitante("S0711001H");
        // Procediment
        solicitud.setCodigoProcedimiento("CODSVDR_GBA_20121107");
        // Unitat tramitadora
        solicitud.setUnidadTramitadora("Unitat de test");
        solicitud.setCodigoUnidadTramitadora("A04123456");
        solicitud.setUnidadTramitadora("Departament de test");
        solicitud.setFinalidad("Test peticionSincrona");
        solicitud.setConsentimiento(ScspConsentimiento.Si);
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
        solicitud.setFuncionario(funcionario);
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.DNI);
        titular.setDocumentacion("12345678Z");
        solicitud.setTitular(titular);
        solicitud.setConsultaPerDocumentIdentitat("NIF", "12345678Z", null);
        solicitud.setConsultaPerDadesPersonals(null, null, null, null, null, null,
        null, null, null);
        solicitud.setConsultaPerReferenciaNia(null);
        solicitud.setProvinciaSolicitud("07");
        solicitud.setMunicipioSolicitud(codiMunicipi);
        solicitud.setNumeroAnyos(null);
    
        
    
        try {
            getClient(entorn).peticionSincrona(Arrays.asList(solicitud));
        return true;
        } catch (Exception e) {
            //FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            // context.addMessage(null, message);


            System.out.println("Solicitud string" + solicitud.toString());

            e.printStackTrace(); 
            return (false );
        }

    
    }

   
}

