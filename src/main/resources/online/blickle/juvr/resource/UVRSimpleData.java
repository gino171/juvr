package online.blickle.juvr.resource;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import online.blickle.energy.data.DataValue;
import online.blickle.juvr.data.UVRDataSet;

@XmlRootElement
public class UVRSimpleData {

	private SimpleDataValue[] actors;
	private SimpleDataValue[] sensors;
	private Date date;
	private String dateAsString;
	
	protected UVRSimpleData() {} // Serialization
	
	public UVRSimpleData(UVRDataSet dataSet) {
		
		this.date = dataSet.getDate();
		this.dateAsString = dataSet.getDateAsString();
		
		DataValue[] actorValues =  (new UVRActors(dataSet)).getActors();
		actors = new SimpleDataValue[actorValues.length];
		for (int i=0; i< actorValues.length;i++) {
			actors [i] = new SimpleDataValue(
					actorValues[i].getMeasure().getId(),
					actorValues[i].getMeasure().getDescription(),
					actorValues[i].getValue()
					);
		}
		
		DataValue[] sensorValues =  (new UVRSensors(dataSet)).getSensors();
		sensors = new SimpleDataValue[sensorValues.length];
		for (int i=0; i< sensorValues.length;i++) {
			sensors [i] = new SimpleDataValue(
					sensorValues[i].getMeasure().getId(),
					sensorValues[i].getMeasure().getDescription(),
					sensorValues[i].getValue()
					);
		}
	}
	
	public SimpleDataValue[] getActors() {
		return actors;
	}
	
	public SimpleDataValue[] getSensors() {
		return sensors;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDateAsString() {
		return dateAsString;
	}
	
	public static class SimpleDataValue {
		public String id;
		public String description;
		public double value;
		
		protected SimpleDataValue() {} // Serialization
		public SimpleDataValue(String id, String description, double value) {
			super();
			this.id = id;
			this.description = description;
			this.value = value;
		}
		
		
	}
	
}
