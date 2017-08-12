package online.blickle.juvr.resource;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import online.blickle.juvr.data.UVRDataSet;



@Path("/")
public class HeatingRessource {
	

	@Context ServletContext servletContext;
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public UVRSimpleData getUVRData() throws IOException{
		CachedDataSetAccess cache = new CachedDataSetAccess(servletContext);
		UVRDataSet res = cache.getUVRData();
		return new UVRSimpleData(res);
	}
	
}
