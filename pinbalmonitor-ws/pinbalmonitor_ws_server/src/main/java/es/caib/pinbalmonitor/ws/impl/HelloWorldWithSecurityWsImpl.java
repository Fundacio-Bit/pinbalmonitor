package es.caib.pinbalmonitor.ws.impl;

import es.caib.pinbalmonitor.ws.utils.BaseWsImpl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import static es.caib.pinbalmonitor.commons.utils.Constants.PBM_ADMIN;
import static es.caib.pinbalmonitor.commons.utils.Constants.PBM_USER;

/**
 * @author anadal
 */
@Stateless(name = HelloWorldWithSecurityWsImpl.NAME + "Ejb")
@RolesAllowed({PBM_USER, PBM_ADMIN})
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = {
        "es.caib.pinbalmonitor.ws.utils.WsInInterceptor"})
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = {
        "es.caib.pinbalmonitor.ws.utils.WsOutInterceptor"})
@WebService(
        name = HelloWorldWithSecurityWsImpl.NAME_WS,
        portName = HelloWorldWithSecurityWsImpl.NAME_WS,
        serviceName = HelloWorldWithSecurityWsImpl.NAME_WS + "Service")
public class HelloWorldWithSecurityWsImpl extends BaseWsImpl {

    public static final String NAME = "HelloWorldWithSecurity";

    public static final String NAME_WS = NAME + "Ws";

    @Resource
    private WebServiceContext wsContext;

    @RolesAllowed({PBM_ADMIN, PBM_USER})
    @WebMethod
    public String echo(@WebParam(name = "echo") String echo) {
        log.info("HelloWorldWithSecurityWsImpl :: echo = " + echo);
        return "USER: " + wsContext.getUserPrincipal().getName() + " | ECHO: " + echo;
    }

}
