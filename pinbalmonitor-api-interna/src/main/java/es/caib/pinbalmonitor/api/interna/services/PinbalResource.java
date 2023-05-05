package es.caib.pinbalmonitor.api.interna.services;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbalmonitor.plugins.identitat.ServeiVerificacioIdentitatController;
import es.caib.pinbalmonitor.plugins.pagamentContractacions.ClientPagamentcontractacionsController;
import es.caib.pinbalmonitor.plugins.policia.PoliciaClientController;

@Path("pinbal")
public class PinbalResource {

    public PinbalResource(){}

    private ServeiVerificacioIdentitatController verificacioIdentitat= new ServeiVerificacioIdentitatController();
    private PoliciaClientController confirmacioIdentitat = new PoliciaClientController();
    private ClientPagamentcontractacionsController pagamentContractacions = new ClientPagamentcontractacionsController();
    @GET
    @Path("verificacioIdentitat")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getVerificacioIdentitat()  {
    return Response.status(Response.Status.ACCEPTED).entity(verificacioIdentitat.verificarIdentitat()).build();
    }
    
    
    @GET
    @Path("confirmacioIdentitat")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getConfirmacioIdentitat()  {
    return Response.status(Response.Status.ACCEPTED).entity(confirmacioIdentitat.confirmarIdentitat()).build();
    }
    

    @POST
    @Path("pagamentContractacions")
    @Consumes(MediaType.APPLICATION_XML)
    public Response getPagamentContractacions()  {
    return Response.status(Response.Status.ACCEPTED).entity(pagamentContractacions.correntPagament()).build();
    }
        
        

    }
    

