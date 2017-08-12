package online.blickle.energy.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataValue {

	
	private MeasureDescription measure;
	private double value;
	
	protected DataValue() {} //Serialization
	
	public DataValue(double value, MeasureDescription measure) {
		this.value = value;
		this.measure = measure;
	}
	
	public MeasureDescription getMeasure() {
		return measure;
	}

	public double getValue() {
		return value;
	}

}
