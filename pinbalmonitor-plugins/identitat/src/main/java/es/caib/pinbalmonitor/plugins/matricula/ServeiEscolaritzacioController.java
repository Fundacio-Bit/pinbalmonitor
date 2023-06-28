
package es.caib.pinbalmonitor.plugins.matricula;

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
import java.util.Arrays;
import java.util.List;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbalmonitor.plugins.FuncionariModel;
import es.caib.pinbalmonitor.plugins.TitularModel;
import io.github.cdimascio.dotenv.Dotenv;



/**
 * Controlador d'exemple per emprar el servei de verificació d'identitat.
 *
 * @author areus
 */
@Named("pinbalEscolaritzacio")
@ViewScoped
public class ServeiEscolaritzacioController implements Serializable {
    Dotenv dotenv = Dotenv.load();




    private  final String SERVEI_SCSP = "SCMCEDU";
    private  final boolean ENABLE_LOGGING = true;

 
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ServeiEscolaritzacioController.class);

    /**
     * Injecta l'API del client del servei de verificació d'identitat
     */

    
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


 
    


            

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public boolean matricula(String entorn) {
    	LOG.info("escolarització");
        //client
        LOG.info("Client creat");

        
        // Funcionari
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
    
        // Titular
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
        titular.setDocumentacion("12345678Z");
    
        // Dades especifiqeus
        String xmlDadesEspecifiques = "";
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<DatosEspecificos>");
        xmlBuilder.append("<Solicitud>");
        xmlBuilder.append("<FechaNacimientoTitular>28/09/2022</FechaNacimientoTitular>");
        xmlBuilder.append("<IdTutor>");
        xmlBuilder.append("<TipoDocumentacion>NIF</TipoDocumentacion><Documentacion>35146644E</Documentacion>");
        xmlBuilder.append("</IdTutor>");
        xmlBuilder.append("</Solicitud>");
        xmlBuilder.append("</DatosEspecificos>");
        xmlDadesEspecifiques = xmlBuilder.toString();
    
    
        Solicitud solicitud = new Solicitud();
        
        //solicitud.setIdentificadorSolicitante("B07167448");
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
    
        solicitud.setDatosEspecificos(xmlDadesEspecifiques);
    
    
        

        // 

        try {
            resposta =  getClient(entorn).peticionSincrona("SCMCEDU", Arrays.asList(solicitud));
            return true;
        } catch (Exception e) {
            //FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            // context.addMessage(null, message);


            System.out.println("Solicitud string" + solicitud.toString());

            e.printStackTrace(); 
            return (false );
        }
    }



    /**
     * Mètode d'utilitat per descarregar un fitxer
     *
     * @param filename Nom del fitxer
     * @param mimetype tipus mime pel content-type
     * @param content contingut del fitxer
     * @throws IOException Si es produeix un error I/O
     */
  
}

