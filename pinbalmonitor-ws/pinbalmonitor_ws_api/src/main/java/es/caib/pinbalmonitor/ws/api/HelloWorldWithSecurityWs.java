package es.caib.pinbalmonitor.ws.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 3.2.11.redhat-00001
 * 2020-04-24T11:00:07.619+02:00
 * Generated source version: 3.2.11.redhat-00001
 *
 */
@WebService(targetNamespace = "http://impl.ws.pinbalmonitor.caib.es/", name = "HelloWorldWithSecurityWs")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorldWithSecurityWs {

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://impl.ws.pinbalmonitor.caib.es/", partName = "return")
    public java.lang.String echo(
        @WebParam(partName = "echo", name = "echo")
        java.lang.String echo
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://impl.ws.pinbalmonitor.caib.es/", partName = "return")
    public java.lang.String getVersion();
}
