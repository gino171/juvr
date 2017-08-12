package online.blickle.juvr.resource;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import online.blickle.energy.data.DataValue;
import online.blickle.juvr.data.UVRDataSet;


@Path("/sensors")
public class SensorResource {

	@Context ServletContext servletContext;
	
	@Path("/")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public UVRSensors getUVRData() throws IOException{
		CachedDataSetAccess cache = new CachedDataSetAccess(servletContext);
		UVRDataSet res = cache.getUVRData();
		return new UVRSensors(res);
	}
	
	@Path("/{id}/")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public DataValue getSensor(@PathParam("id") String id) throws IOException {
		CachedDataSetAccess cache = new CachedDataSetAccess(servletContext);
		UVRDataSet res = cache.getUVRData();
		UVRSensors sensors = new UVRSensors(res);
		return sensors.getSensor(id);
	}
}
