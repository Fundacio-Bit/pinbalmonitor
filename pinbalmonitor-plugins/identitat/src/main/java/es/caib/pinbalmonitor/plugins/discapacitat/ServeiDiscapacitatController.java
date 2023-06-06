
package es.caib.pinbalmonitor.plugins.discapacitat;

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
@Named("pinbalDiscapacitat")
@ViewScoped
public class ServeiDiscapacitatController implements Serializable {
    Dotenv dotenv = Dotenv.load();

    private  final String URL_BASE = dotenv.get("ENDPOINT");
    private  final String USUARI = dotenv.get("USUARI");    
    private  final String CONTRASENYA = dotenv.get("SECRET");
 
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ServeiDiscapacitatController.class);

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
    public boolean discapacitat() {
        LOG.info("solicitud discapacitat");

        ClientGeneric client = new ClientGeneric(URL_BASE, USUARI, CONTRASENYA);
        LOG.info("Client creat");
        
        // Funcionari
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
    
        // Titular
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
        titular.setDocumentacion("12345678Z");

        Solicitud solicitud = new Solicitud();

          // Datos específicos
          StringBuilder xmlDadesEspecifiques = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
          xmlDadesEspecifiques.append("<DatosEspecificos>");
          xmlDadesEspecifiques.append("<Consulta>");

          xmlDadesEspecifiques.append("<CodigoComunidadAutonoma>");
          xmlDadesEspecifiques.append("04");
          xmlDadesEspecifiques.append("</CodigoComunidadAutonoma>");

          xmlDadesEspecifiques.append("<CodigoProvincia>");
          xmlDadesEspecifiques.append("07");
          xmlDadesEspecifiques.append("</CodigoProvincia>");

          xmlDadesEspecifiques.append("<FechaConsulta>");
          xmlDadesEspecifiques.append("30/08/2022");
          xmlDadesEspecifiques.append("</FechaConsulta>");

          xmlDadesEspecifiques.append("<DatosAdicionalesTitular>");
          xmlDadesEspecifiques.append("<FechaNacimiento>31/08/2022</FechaNacimiento>");
          xmlDadesEspecifiques.append("</DatosAdicionalesTitular>");
  
          xmlDadesEspecifiques.append("<ConsentimientoTiposDiscapacidad>");
          xmlDadesEspecifiques.append("S");

          xmlDadesEspecifiques.append("</ConsentimientoTiposDiscapacidad>");

          xmlDadesEspecifiques.append("</Consulta>");
          xmlDadesEspecifiques.append("</DatosEspecificos>");


    
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
        solicitud.setDatosEspecificos(xmlDadesEspecifiques.toString());

      
        try {
            resposta = client.peticionSincrona("SVDSCDDWS01", List.of(solicitud));
            LOG.info("resposta => " + getResposta());
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        return false;
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

