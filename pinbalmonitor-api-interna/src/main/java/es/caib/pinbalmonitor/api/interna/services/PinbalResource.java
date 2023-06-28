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
import es.caib.pinbalmonitor.plugins.Entorns;
import es.caib.pinbalmonitor.plugins.discapacitat.ServeiDiscapacitatController;
import es.caib.pinbalmonitor.plugins.identitat.ServeiVerificacioIdentitatController;
import es.caib.pinbalmonitor.plugins.matricula.ServeiEscolaritzacioController;
import es.caib.pinbalmonitor.plugins.pagamentContractacions.ClientPagamentContractacionsController;
import es.caib.pinbalmonitor.plugins.pagamentAjudes.ClientPagamentAjudesController;
import es.caib.pinbalmonitor.plugins.policia.PoliciaClientController;
import es.caib.pinbalmonitor.plugins.padro.ClientPadroHistoricController;
import es.caib.pinbalmonitor.plugins.padro.ClientPadroConvivenciaController;
import es.caib.pinbalmonitor.plugins.familiaNombrosa.ClientFamiliaNombrosaController;
import com.google.gson.JsonObject;
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

// Posar s'entorn com a path param en lloc de duplicar peticions

    @POST
    @Path("verificacioIdentitat/proves")
    public Response getVerificacioIdentitatProves()   {
    return  Response.status(200).entity(verificacioIdentitat.verificarIdentitat("proves")).build();
    }
    

    
    @POST
    @Path("verificacioIdentitat/produccio")
    public Response getVerificacioIdentitatProd()   {
    return  Response.status(200).entity(verificacioIdentitat.verificarIdentitat("produccio")).build();
    }
    /*
    
    @POST
    @Path("justificantVerificacioIdentitat")
    public Response getJustificant() {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(verificacioIdentitat.provaJustificant()).build();

    
    }*/

  /*  @POST
    @Path("asincrona")
    public Response getVerificacioAsincrona()  {
    return  Response.status(200).entity(verificacioIdentitat.provaAsync()).build();
    
    }*/
    
    @POST
    @Path("confirmacioIdentitat/proves")
    public Response getConfirmacioIdentitatProves()  {
    return  Response.status(200).entity(confirmacioIdentitat.confirmarIdentitat("proves")).build();
    }

    @POST
    @Path("confirmacioIdentitat/produccio")
    public Response getConfirmacioIdentitatProd()  {
    return  Response.status(200).entity(confirmacioIdentitat.confirmarIdentitat("produccio")).build();
    }
    
    

    @POST
    @Path("pagamentContractacions/proves")
    public Response getPagamentContractacionsProves()  {
    return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  pagamentContractacions.correntPagament("proves")).build();
    }
    

    
    @POST
    @Path("pagamentContractacions/produccio")
    public Response getPagamentContractacionsProd()  {
    return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  pagamentContractacions.correntPagament("produccio")).build();
    }
    

    @POST
    @Path("pagamentAjudes/proves")
    public Response getPagamentAjudesProves()  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  pagamentAjudes.correntPagament("proves")).build();

    
    }

    
    @POST
    @Path("pagamentAjudes/produccio")
    public Response getPagamentAjudesProd()  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  pagamentAjudes.correntPagament("produccio")).build();

    
    }
    @POST
    @Path("discapacitat/proves")
    public Response getDiscapacitatProves()  {
        return  Response.status(200).entity(discapacitat.discapacitat("proves")).build();

    }

    @POST
    @Path("discapacitat/produccio")
    public Response getDiscapacitatProd()  {
        return  Response.status(200).entity(discapacitat.discapacitat("produccio")).build();

    }

    @POST
    @Path("matricula/proves")

    public Response getMatriculaProves()  {
    return  Response.status(200).entity(escolaritzacio.matricula("proves")).build();
    }

    
    @POST
    @Path("matricula/produccio")

    public Response getMatriculaProd()  {
    return  Response.status(200).entity(escolaritzacio.matricula("produccio")).build();
    }
       
    
    @POST
    @Path("padroHistoric/{codiMunicipi}/proves")
    public Response getPadroHistoricProves(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(padroHistoric.padro(codiMunicipi, "proves")).build();

    
    }

    @POST
    @Path("padroHistoric/{codiMunicipi}/produccio")
    public Response getPadroHistoricProd(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(padroHistoric.padro(codiMunicipi, "produccio")).build();

    
    }
    
    

    
    @POST
    @Path("padroConvivencia/{codiMunicipi}/produccio")
    public Response getPadroConvivenciaProd(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  padroConvivencia.padro(codiMunicipi, "produccio")).build();

    
    }
    
    
    @POST
    @Path("padroConvivencia/{codiMunicipi}/proves")
    public Response getPadroConvivenciaProves(@PathParam("codiMunicipi") String codiMunicipi)  {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(  padroConvivencia.padro(codiMunicipi, "proves")).build();

    
    }
    
    

    
    @POST
    @Path("familiaNombrosa/produccio")
    public Response getFamiliaNombrosaProves() {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(familiaNombrosa.familiaNombrosa("prod")).build();

    
    }
    @POST
    @Path("familiaNombrosa/proves")
    public Response getFamiliaNombrosaProd() {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers",
        		        "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods",
        		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(familiaNombrosa.familiaNombrosa("produccio")).build();

    
    }
        
        
        

    }
    

