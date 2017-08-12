package online.blickle.juvr.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import online.blickle.energy.data.DataUnit;
import online.blickle.energy.data.DataValue;

/**
 * Object containing one set of data observed by UVR 1611
 * Input and output item have no meta information and are completely generic
 * @author tbl
 *
 */
@XmlRootElement
public class UVRDataSet {
	
	public static final double UNUSED = -999999.999;
	protected Date date;

	protected double[] input = new double[16];
	protected DataUnit[] inputUnit = new DataUnit[16];
	protected boolean[] output = new boolean[16];
	
	protected boolean hasPower1;
	protected boolean hasPower2;
	protected double power1;
	protected double power2;
	protected double mwz1;
	protected double mwz2;
	protected short turn_A1;
	protected short turn_A2;
	protected short turn_A6;
	protected short turn_A7;

	public double getInput(int idx) {
		checkIndexRange(idx);
		return input[idx];
	}

	public void setInput(int idx, double value ){
		checkIndexRange(idx);
		input[idx]=value;
	}

	public DataUnit getInputUnit(int idx) {
		checkIndexRange(idx);
		return inputUnit[idx];
	}
	
	public void setInput(int idx,DataValue value) {
		if (idx <0 || idx >=16) {
			throw new IllegalArgumentException("Input index must be in the range from 0-15 (but is "+idx+" ).");
		}
		this.input[idx] = value.getValue();
		this.inputUnit[idx] = value.getMeasure().getDataUnit();
		
	}

	public void setInputUnit(int idx, DataUnit value ){
		if (idx <0 || idx >=16) {
			throw new IllegalArgumentException("Input index must be in the range from 0-15 (but is "+idx+" ).");
		}
		this.inputUnit[idx]=value;
	}

	public boolean getOutput(int idx) {
		if (idx <0 || idx >=16) {
			throw new IllegalArgumentException("Output index must be in the range from 0-15 (but is "+idx+" ).");
		}
		return output[idx];
	}

	public void setOutput(int idx, boolean value) {
		if (idx <0 || idx >=16) {
			throw new IllegalArgumentException("Output index must be in the range from 0-15 (but is "+idx+" ).");
		}
		output[idx] = value;
	}
	
	/*
	 * returns the status of the actor with id <outputID> (0-15)
	 * as double value (0.0 = off, 1.0 = on)
	 */
	public double getOutputAsDouble(int outputID) {
		return getOutput(outputID) ? 1 : 0;
	}

	public DataUnit[] getInputUnit() {
		return inputUnit;
	}
	
	public double[] getInput() {
		return input;
	}
	
	public boolean[] getOutput() {
		return output;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isHasPower1() {
		return hasPower1;
	}
	public void setHasPower1(boolean hasPower1) {
		this.hasPower1 = hasPower1;
	}
	public boolean isHasPower2() {
		return hasPower2;
	}
	public void setHasPower2(boolean hasPower2) {
		this.hasPower2 = hasPower2;
	}
	public double getMwz1() {
		return mwz1;
	}
	public void setMwz1(double mwz1) {
		this.mwz1 = mwz1;
	}
	public double getMwz2() {
		return mwz2;
	}
	public void setMwz2(double mwz2) {
		this.mwz2 = mwz2;
	}
	public double getPower1() {
		return power1;
	}
	public void setPower1(double power1) {
		this.power1 = power1;
	}
	public double getPower2() {
		return power2;
	}
	public void setPower2(double power2) {
		this.power2 = power2;
	}
	public short getTurn_A1() {
		return turn_A1;
	}
	public void setTurn_A1(short turn_A1) {
		this.turn_A1 = turn_A1;
	}
	public short getTurn_A2() {
		return turn_A2;
	}
	public void setTurn_A2(short turn_A2) {
		this.turn_A2 = turn_A2;
	}
	public short getTurn_A6() {
		return turn_A6;
	}
	public void setTurn_A6(short turn_A6) {
		this.turn_A6 = turn_A6;
	}
	public short getTurn_A7() {
		return turn_A7;
	}
	public void setTurn_A7(short turn_A7) {
		this.turn_A7 = turn_A7;
	} 


	public String toString() {

		String ret = "Dataset:\n";
		ret+="Date = "+getDateAsString()+"\n";

		for (int i=0; i<16; i++) {
			ret+="Input "+i+" = "+getInput(i)+" ["+getInputUnit(i).getUnit()+"]\n";
		}
		for (int i=0; i<16; i++) {
			ret+="Output "+i+"= "+getOutput(i)+"\n";

		}
		return ret;
	}
	
	public String getDateAsString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return df.format(getDate()); 
	}

	
		private void checkIndexRange(int idx) {
		if (idx <0 || idx >=16) {
			throw new IllegalArgumentException("Input index must be in the range from 0-15 (but is "+idx+" ).");
		}
	}

}
