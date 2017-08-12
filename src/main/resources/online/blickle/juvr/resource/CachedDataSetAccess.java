package online.blickle.juvr.resource;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;

import online.blickle.juvr.data.UVRDataSet;
import online.blickle.juvr.read.UVR1611Connection;
import online.blickle.juvr.read.UVRCurrentDataReader;


public class CachedDataSetAccess {
	
	private static final String MOST_RECENT_DATASET = "online.blickle.juvr.resource.MostRecentDataset";
	private static final int CACHE_TIME_IN_SEC = 15; 
	private ServletContext servletContext;
	
	public CachedDataSetAccess(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public UVRDataSet getUVRData() throws IOException {
		
		UVRDataSet res = readDataSetFromCache(servletContext);
		if (res == null){
			res = readDataSetFromHeating(servletContext);
			putDataSetToCache(servletContext, res);
		}
		return res;
	}
	
	public int getCacheTimeInSec() {
		return CACHE_TIME_IN_SEC;
	}
	
	public void clearCache() {
		servletContext.removeAttribute(MOST_RECENT_DATASET);
	}

	private void putDataSetToCache(ServletContext servletContext, UVRDataSet res) {
		servletContext.setAttribute(MOST_RECENT_DATASET, res);
	}

	private UVRDataSet readDataSetFromHeating(ServletContext servletContext)
			throws IOException {
		UVRDataSet res;
		UVR1611Connection conn = new UVR1611Connection(
				servletContext.getInitParameter("online.blickle.juvr.resource.UVRConnectionURL"),
				Integer.parseInt(servletContext.getInitParameter("online.blickle.juvr.resource.UVRConnectionPort"))
				);
		UVRCurrentDataReader reader = new UVRCurrentDataReader(conn);
		res = reader.getCurrentData();
		return res;
	}

	private UVRDataSet readDataSetFromCache(ServletContext servletContext) {
		UVRDataSet mrd = (UVRDataSet)servletContext.getAttribute(MOST_RECENT_DATASET);
		if (mrd != null) {
			Calendar cal = Calendar.getInstance(); 
		    cal.setTime(mrd.getDate()); 
		    cal.add(Calendar.SECOND,CACHE_TIME_IN_SEC);
		    if (cal.getTime().after(new Date())) {
		    	// cache valid
		    	return mrd;
		    }
		}
		return null;
	}
}
