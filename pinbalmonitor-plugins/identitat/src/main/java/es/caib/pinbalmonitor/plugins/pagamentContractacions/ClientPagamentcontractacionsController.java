
package es.caib.pinbalmonitor.plugins.pagamentContractacions;

import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svdccaacpcws01.ClientSvdccaacpcws01;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import javax.faces.application.FacesMessage;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.Serializable;

import java.util.List;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
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
@Named("pinbalCCAAContractacions")
@ViewScoped
public class ClientPagamentcontractacionsController implements Serializable {
    Dotenv dotenv = Dotenv.configure()
    .directory("..")
    .ignoreIfMalformed() // 
    .ignoreIfMissing()
    .load();

    /* private ClientSvdccaacpcws01 clientSvdccaacpcws01 = new ClientSvdccaacpcws01(
        dotenv.get("ENDPOINT"),
        dotenv.get("USUARI"),
        dotenv.get("SECRET")
        );*/

        private ClientGeneric clientSvdccaacpcws01 = new ClientGeneric(
        // Posar a mà fins que funcioni:
        dotenv.get("ENDPOINT"),
        dotenv.get("USER"),
        dotenv.get("SECRET")
          );
 
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ClientPagamentcontractacionsController.class);

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


 
    


            

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public boolean correntPagament() {
        SolicitutPagamentContractacions solicitud = new SolicitutPagamentContractacions("07", "04"); 
        LOG.info("correntPagament");
        solicitud.setIdentificadorSolicitante("S0711001H");
        solicitud.setCodigoProcedimiento("SVDCCAACPCWS01");
        solicitud.setUnidadTramitadora("Servei d'escolarització");
        solicitud.setFinalidad("Baremacions per el proces d'escolaritzacio");
        solicitud.setConsentimiento(ScspConsentimiento.Si);
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("43117946F");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
        solicitud.setFuncionario(funcionario);
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
        titular.setDocumentacion("24255536N");
        titular.setApellido1("Fuster");
        solicitud.setTitular(titular);

        // Datos específicos
        Gson gson = new Gson();
        System.out.println( gson.toJsonTree(solicitud.getDatosEspecificos()));
        //System.out.println( gson.toJsonTree(solicitud));


        try {
            resposta =  clientSvdccaacpcws01.peticionSincrona("SVDCCAACPCWS01", List.of(solicitud));
            return true;
        } catch (Exception e) {
            //FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            // context.addMessage(null, message);

            System.out.println( gson.toJsonTree(solicitud.getDatosEspecificos()));


            e.printStackTrace(); 
            return (false );
        }
    }


    public static class SolicitutPagamentContractacions extends Solicitud {
		
        protected  String codiProvincia;
        protected  String codiComunitatAutonoma;
        
        public SolicitutPagamentContractacions(String codiProvincia, String codiComunitatAutonoma) {
            super();
            this.codiProvincia = codiProvincia;
            this.codiComunitatAutonoma = codiComunitatAutonoma;
        }
    
        @Override
        public String getDatosEspecificos() { // xml
            StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlBuilder.append("<DatosEspecificos>");
            xmlBuilder.append("<Consulta>");
            
                xmlBuilder.append(xmlOptionalStringParameter(this.codiProvincia, "CodigoComunidadAutonoma"));
            
            

                xmlBuilder.append(xmlOptionalStringParameter(this.codiProvincia, "CodigoProvincia"));
                
            
                
            xmlBuilder.append("</Consulta>");
            xmlBuilder.append("</DatosEspecificos>");
            
             System.out.println("DATOS ESPECIFICOS CONTRATACIONES: " + xmlBuilder.toString());
            
            return xmlBuilder.toString();
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

