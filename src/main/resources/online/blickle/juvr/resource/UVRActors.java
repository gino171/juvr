package online.blickle.juvr.resource;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import online.blickle.energy.data.DataUnit;
import online.blickle.energy.data.DataValue;
import online.blickle.energy.data.MeasureDescription;
import online.blickle.juvr.data.UVRDataSet;

@XmlRootElement
public class UVRActors {

	private DataValue[] actors = new DataValue[5];
	private UVRDataSet dataset;

	protected UVRActors() {}
	
	public UVRActors(UVRDataSet dataset){
		this.dataset = dataset;
		actors[0] = createActorDataValue(dataset, 0,"PUMPE_SOLAR","Solarpumpe");
		actors[1] = createActorDataValue(dataset, 2,"PUMPE_HEIZUNG","Heizungspumpe");
		actors[2] = createActorDataValue(dataset, 4,"PUMPE_WW","WW Zirkulation");
		actors[3] = createActorDataValue(dataset, 6,"PUMPE_WWLADE","WW Speicher Ladepumpe");
		actors[4] = createActorDataValue(dataset, 11,"BRENNER_ANF","Anfoderung Brenner");
	}
	
	
	public String getDateAsString() {
		return dataset.getDateAsString();
	}
	
	public Date getDate() {
		return dataset.getDate();
	}
	
	public DataValue[] getActors() {
		return actors;
	}
	
	public DataValue getActor(String id) {
		for (DataValue dv: actors) {
			if (dv.getMeasure().getId().equals(id)) {
				return dv;
			}
		}
		throw new IllegalArgumentException("Actor with id '"+id+"' is unknown.");
	}
	
	private DataValue createActorDataValue(UVRDataSet dataset, int index, String ID, String description) {
		return new DataValue(dataset.getOutput(index)?1:0,new MeasureDescription(ID, description, DataUnit.ACTOR));
	}
	
	
	
	
}
