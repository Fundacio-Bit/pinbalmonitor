package es.caib.pinbalmonitor.api.interna.services;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbalmonitor.plugins.discapacitat.ServeiDiscapacitatController;
import es.caib.pinbalmonitor.plugins.identitat.ServeiVerificacioIdentitatController;
import es.caib.pinbalmonitor.plugins.matricula.ServeiEscolaritzacioController;
import es.caib.pinbalmonitor.plugins.pagamentContractacions.ClientPagamentContractacionsController;
import es.caib.pinbalmonitor.plugins.pagamentAjudes.ClientPagamentAjudesController;
import es.caib.pinbalmonitor.plugins.policia.PoliciaClientController;
import es.caib.pinbalmonitor.plugins.padro.ClientPadroHistoricController;
import es.caib.pinbalmonitor.plugins.padro.ClientPadroConvivenciaController;
import es.caib.pinbalmonitor.plugins.familiaNombrosa.ClientFamiliaNombrosaController;


import javax.ws.rs.PathParam;
@Path("pinbal")
public class PinbalResource {

    public PinbalResource(){}


    private ServeiVerificacioIdentitatController verificacioIdentitat= new ServeiVerificacioIdentitatController();
    private PoliciaClientController confirmacioIdentitat = new PoliciaClientController();
    private ClientPagamentContractacionsController pagamentContractacions = new ClientPagamentContractacionsController();
    private ClientPagamentAjudesController pagamentAjudes = new ClientPagamentAjudesController();
    private ClientPadroHistoricController padroHistoric = new ClientPadroHistoricController();
    private ClientPadroConvivenciaController padroConvivencia= new ClientPadroConvivenciaController();
    private ClientFamiliaNombrosaController familiaNombrosa = new ClientFamiliaNombrosaController();
    private ServeiEscolaritzacioController escolaritzacio = new ServeiEscolaritzacioController();
    private ServeiDiscapacitatController discapacitat = new ServeiDiscapacitatController();
    @POST
    @Path("verificacioIdentitat")
    public Response getVerificacioIdentitat()  {
    return  Response.status(200).entity(verificacioIdentitat.verificarIdentitat()).build();
    }
    
    
    @POST
    @Path("confirmacioIdentitat")
    public Response getConfirmacioIdentitat()  {
    return  Response.status(200).entity(confirmacioIdentitat.confirmarIdentitat()).build();
    }
    

    @POST
    @Path("pagamentContractacions")
    public Response getPagamentContractacions()  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(pagamentContractacions.correntPagament()).build();

    
    }
    

    @POST
    @Path("pagamentAjudes")
    public Response getPagamentAjudes()  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  pagamentAjudes.correntPagament()).build();

    
    }
    @POST
    @Path("discapacitat")
    public Response getDiscapacitat()  {
        return  Response.status(200).entity(discapacitat.discapacitat()).build();

    }

    @POST
    @Path("matricula")

    public Response getMatricula()  {
    return  Response.status(200).entity(escolaritzacio.matricula()).build();
    }
       
    
    @POST
    @Path("padroHistoric/{codiMunicipi}")
    public Response getPadroHistoric(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  padroHistoric.padro(codiMunicipi)).build();

    
    }
    

        
    @POST
    @Path("padroConvivencia/{codiMunicipi}")
    public Response getPadroConvivencia(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  padroConvivencia.padro(codiMunicipi)).build();

    
    }
    
    @POST
    @Path("familiaNombrosa")
    public Response getFamiliaNombrosa() {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(familiaNombrosa.familiaNombrosa()).build();

    
    }
        
        

    }
    

