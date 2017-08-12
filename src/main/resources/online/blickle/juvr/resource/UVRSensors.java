package online.blickle.juvr.resource;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;





import online.blickle.energy.data.DataValue;
import online.blickle.energy.data.MeasureDescription;
import online.blickle.juvr.data.UVRDataSet;


@XmlRootElement
public class UVRSensors {

	private DataValue[] sensors = new DataValue[10];
	private UVRDataSet dataset;
	
	protected UVRSensors() {}
	
	public UVRSensors(UVRDataSet dataset){
		this.dataset = dataset;
		sensors[0] = createDataValue(dataset, 0,"WW_SPEICHER", "Warmwasser Speicher");
		sensors[1] = createDataValue(dataset, 2,"WW_REF", "Speicher Referenz");
		sensors[2] = createDataValue(dataset, 3,"KESSEL", "Kesseltemperatur");
		sensors[3] = createDataValue(dataset, 4,"SOLARVL", "Solar Vorlauf");
		sensors[4] = createDataValue(dataset, 5,"SOLARRL", "Solar Rücklauf");
		sensors[5] = createDataValue(dataset, 7,"KOLLEKTOR", "Kollektortemperatur");
		sensors[6] = createDataValue(dataset, 9,"AUSSEN", "Aussentemperatur");
		sensors[7] = createDataValue(dataset, 10,"ZIRKULATION", "Zirkulationsleitung");
		sensors[8] = createDataValue(dataset, 11,"HZVORLAUF", "Heizungsvorlauf");
		sensors[9] = createDataValue(dataset, 13,"RAUM", "Raumtemperatur");
	}
	
	public DataValue[] getSensors() {
		return sensors;
	}
	
	public String getDateAsString() {
		return dataset.getDateAsString();
	}
	
	public Date getDate() {
		return dataset.getDate();
	}
	
	public DataValue getSensor(String id) {
		for (DataValue dv: sensors) {
			if (dv.getMeasure().getId().equals(id)) {
				return dv;
			}
		}
		throw new IllegalArgumentException("Sensor with id '"+id+"' is unknown.");
	}
		
	private DataValue createDataValue(UVRDataSet dataset, int index, String ID, String description) {
		return new DataValue(dataset.getInput(index),new MeasureDescription(ID, description, dataset.getInputUnit(index)));
	}
	
	
}
