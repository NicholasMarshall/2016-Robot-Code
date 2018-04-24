package org.usfirst.frc.team5817.util;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechExtreme3DPro extends Joystick {

	public LogitechExtreme3DPro(int port) {
		super(port);
	}

	public double getMainX() {
		return getRawAxis(LogitechExtreme3DProMap.X_AXIS);
	}
	
	public double getMainY() {
		return getRawAxis(LogitechExtreme3DProMap.Y_AXIS);
	}
	
	public double getMainTwist() {
		return getRawAxis(LogitechExtreme3DProMap.TWIST_AXIS);
	}
	
	public double getSliderAxis() {
		return 1.0 - getRawAxis(LogitechExtreme3DProMap.SLIDER_AXIS);
	}
	
	public boolean getTriggerButton() {
		return getRawButton(LogitechExtreme3DProMap.TRIGGER_BUTTON);
	}
	
	public boolean getButton2() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_2);
	}
	
	public boolean getButton3() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_3);
	}
	
	public boolean getButton4() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_4);
	}
	
	public boolean getButton5() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_5);
	}
	
	public boolean getButton6() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_6);
	}
	
	public boolean getButton7() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_7);
	}
	
	public boolean getButton8() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_8);
	}
	
	public boolean getButton9() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_9);
	}
	
	public boolean getButton10() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_10);
	}
	
	public boolean getButton11() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_11);
	}
	
	public boolean getButton12() {
		return getRawButton(LogitechExtreme3DProMap.BUTTON_12);
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