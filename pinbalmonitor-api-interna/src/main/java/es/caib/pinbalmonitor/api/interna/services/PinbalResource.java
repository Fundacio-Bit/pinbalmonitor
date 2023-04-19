package es.caib.pinbalmonitor.api.interna.services;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbalmonitor.plugins.identitat.ServeiVerificacioIdentitatController;

@Path("pinbal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class PinbalResource {

    public PinbalResource(){}

    private ServeiVerificacioIdentitatController verificacioIdentitat= new ServeiVerificacioIdentitatController();

    @GET
    @Path("verificacioIdentitat")
    @Produces(MediaType.TEXT_PLAIN)
    public String provaGet()  {
    return verificacioIdentitat.verificarIdentitat();
    }
    
    
        
        

    }
    

