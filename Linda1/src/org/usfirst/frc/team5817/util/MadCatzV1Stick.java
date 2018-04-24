package org.usfirst.frc.team5817.util;

import edu.wpi.first.wpilibj.Joystick;

public class MadCatzV1Stick extends Joystick {

	public MadCatzV1Stick(int port) {
		super(port);
	}

	public double getMainX() {
		return -getRawAxis(MadCatzV1StickMap.X_AXIS);
	}
	
	public double getMainY() {
		return -getRawAxis(MadCatzV1StickMap.Y_AXIS);
	}
	
	public double getMainTwist() {
		return -getRawAxis(MadCatzV1StickMap.TWIST_AXIS);
	}
	
	public double getMainSliderAxis() {
		return getRawAxis(MadCatzV1StickMap.SLIDER_AXIS);
	}
	
	public boolean getTriggerButton() {
		return getRawButton(MadCatzV1StickMap.TRIGGER_BUTTON);
	}
	
	public boolean getButton2() {
		return getRawButton(MadCatzV1StickMap.BUTTON_2);
	}
	
	public boolean getButton3() {
		return getRawButton(MadCatzV1StickMap.BUTTON_3);
	}
	
	public boolean getButton4() {
		return getRawButton(MadCatzV1StickMap.BUTTON_4);
	}
	
	public boolean getButton5() {
		return getRawButton(MadCatzV1StickMap.BUTTON_5);
	}
	
	public boolean getButton6() {
		return getRawButton(MadCatzV1StickMap.BUTTON_6);
	}
	
	public boolean getShift() {
		return getRawButton(MadCatzV1StickMap.SHIFT_BUTTON);
	}
	
	public int getPOV() {
		return getPOV();
	}

	public double getMainDirection() {
		double x = getMainX();
		double y = getMainY() * -1.0;
		double trigDegrees = 0.0;
		double compassDegrees = -1.0;
		if(Math.abs(x) > 0.1 || Math.abs(y) > 0.1) {
			if(x > 0.0) {
				trigDegrees = Math.toDegrees(Math.atan(y / x));
			} else if(x < 0.0) {
				trigDegrees = Math.toDegrees(Math.atan(y / x)) + 180;
			} else {
				if(y < 0.0) {
					trigDegrees = 270.0;
				} else if(y > 0.0) {
					trigDegrees = 90.0;
				}
			}
			if(trigDegrees < 0.0) {
				trigDegrees += 360.0;
			}
			compassDegrees = 450.0 - trigDegrees;
			if(compassDegrees > 360.0) {
				compassDegrees -= 360.0;
			}
		}
		return compassDegrees;
	}

	public double getMainMagnitude() {
		if(Math.abs(getMainX()) > 0.1 || Math.abs(getMainY()) > 0.1) {
			return Math.sqrt(Math.abs(getMainY() * getMainY()) + Math.abs(getMainX() * getMainX()));
		}
		return 0.0;
	}

}