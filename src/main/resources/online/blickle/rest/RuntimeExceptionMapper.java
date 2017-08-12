package online.blickle.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	public Response toResponse(RuntimeException ex) {
		java.util.logging.Logger.getLogger (RuntimeExceptionMapper.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage());
		return Response.status(500)
				.entity(new ErrorMessage(ex))
				.type(MediaType.APPLICATION_JSON).
				build();
	}
}
