package org.usfirst.frc.team5817.util;


public class TrapezoidalSpeedProfile {

	private double time1, time2, time3, time4;
	private double value1, value2, value3, value4;
	
	public TrapezoidalSpeedProfile(double startValue, double endValue, double endTime) {
		value1 = startValue;
		value4 = endValue;
		time1 = 0.0;
		time4 = endTime;
	}
	
	public void setPoint2(double value, double time) {
		value2 = value;
		time2 = time;
	}
	
	public void setPoint3(double value, double time) {
		value3 = value;
		time3 = time;
	}
	
	public double getValue(double time) {
		if(time <= 0.0) {
			return value1;
		} else if(time > 0.0 && time <= time2) {
			return ((value2 - value1) / time2) * time;
		} else if(time > time2 && time <= time3) {
			return value3;
		} else if(time > time3 && time <= time4) {
			return ((value3 - value4) / (time4 - time3)) * (time4 - time);
		} else if(time >= time4) {
			return value4;
		} else {
			return 0.0;
		}
	}
	
	public boolean isSlowingDown(double time) {
		if(time > time3) {
			return true;
		} else {
			return false;
		}
	}
	
}