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
    private ClientGeneric clientSvddgpciws02 = new ClientGeneric(
        dotenv.get("ENDPOINT"),
        dotenv.get("USUARI"),
        dotenv.get("SECRET")
        );

    public ScspRespuesta getResposta() {
        return resposta;
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
    public boolean confirmarIdentitat() {
        LOG.info("confirmarIdentitat");

        SolicitudConfirmacion solicitud = new SolicitudConfirmacion(null);
        solicitud.setIdentificadorSolicitante("S0711001H");
        solicitud.setCodigoProcedimiento("CODSVDR_GBA_20121107");
        solicitud.setUnidadTramitadora("Servei d'escolarització");
        solicitud.setFinalidad("Baremacions per el proces d'escolaritzacio");
        solicitud.setConsentimiento(ScspConsentimiento.Si);
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
        solicitud.setFuncionario(funcionario);
        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
        titular.setDocumentacion("12345678Z");
        solicitud.setTitular(titular);
        try {
            resposta =  clientSvddgpciws02.peticionSincrona("SVDDGPCIWS02", List.of(solicitud));
            LOG.info("resposta => " + getResposta());
            return true;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            // context.addMessage(null, message);
            Gson gson = new Gson();
            return (false /*+ gson.toJsonTree(solicitud)*/);
        }


    }

    public static class SolicitudConfirmacion extends Solicitud {
		protected  String numeroSoporte;

		public SolicitudConfirmacion(String numeroSoporte) {
			super();
			this.numeroSoporte = numeroSoporte;
		}

		@Override
		public String getDatosEspecificos() { // xml
			StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
			if (numeroSoporte != null && !numeroSoporte.isEmpty()) {
				xmlBuilder.append("<Consulta>");
				xmlBuilder.append(
						xmlOptionalStringParameter(this.numeroSoporte, "NumeroSoporte")
				);
				xmlBuilder.append("</Consulta>");
			}
			xmlBuilder.append("</DatosEspecificos>");
			return xmlBuilder.toString();
		}
	}
}
