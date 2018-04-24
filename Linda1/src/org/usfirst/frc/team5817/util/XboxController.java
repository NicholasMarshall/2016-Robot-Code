package org.usfirst.frc.team5817.util;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick {

	/**
	 * Creates a new instance of XboxController, given a joystick port.
	 */
	public XboxController(int port) {
		super(port);
	}

	/**
	 * Returns the value of the x-axis of the left stick.
	 * 
	 * @return The value of the x-axis of the left stick.
	 */
	public double getLeftX() {
		return getRawAxis(XboxControllerMap.LEFT_X);
	}

	/**
	 * Returns the value of the y-axis of the left stick.
	 * 
	 * @return The value of the y-axis of the left stick.
	 */
	public double getLeftY() {
		return getRawAxis(XboxControllerMap.LEFT_Y);
	}

	/**
	 * Returns the value of the left trigger.
	 * 
	 * @return The value of the left trigger.
	 */
	public double getLeftTrigger() {
		return getRawAxis(XboxControllerMap.LEFT_TRIGGER);
	}

	/**
	 * Returns the value of the right trigger.
	 * 
	 * @return The value of the right trigger.
	 */
	public double getRightTrigger() {
		return getRawAxis(XboxControllerMap.RIGHT_TRIGGER);
	}

	/**
	 * Returns the value of the x-axis of the right stick.
	 * 
	 * @return The value of the x-axis of the right stick.
	 */
	public double getRightX() {
		return getRawAxis(XboxControllerMap.RIGHT_X);
	}

	/**
	 * Returns the value of the y-axis of the right stick.
	 * 
	 * @return The value of the y-axis of the right stick.
	 */
	public double getRightY() {
		return getRawAxis(XboxControllerMap.RIGHT_Y);
	}

	/**
	 * Returns the corrected (multiplied by -1) value of the y-axis of the left stick.
	 * 
	 * @return The corrected value of the y-axis of the left stick.
	 */
	public double getCorrectedLeftY() {
		return getLeftY() * -1.0;
	}

	/**
	 * Returns the corrected (multiplied by -1) value of the y-axis of the right stick.
	 * 
	 * @return The corrected value of the y-axis of the right stick.
	 */
	public double getCorrectedRightY() {
		return getRightY() * -1.0;
	}

	/**
	 * Returns whether or not the A button is pressed.
	 * 
	 * @return Whether or not the A button is pressed.
	 */
	public boolean getAButton() {
		return getRawButton(XboxControllerMap.A_BUTTON);
	}

	/**
	 * Returns whether or not the B button is pressed.
	 * 
	 * @return Whether or not the B button is pressed.
	 */
	public boolean getBButton() {
		return getRawButton(XboxControllerMap.B_BUTTON);
	}

	/**
	 * Returns whether or not the X button is pressed.
	 * 
	 * @return Whether or not the X button is pressed.
	 */
	public boolean getXButton() {
		return getRawButton(XboxControllerMap.X_BUTTON);
	}

	/**
	 * Returns whether or not the Y button is pressed.
	 * 
	 * @return Whether or not the Y button is pressed.
	 */
	public boolean getYButton() {
		return getRawButton(XboxControllerMap.Y_BUTTON);
	}

	/**
	 * Returns whether or not the left shoulder button is pressed.
	 * 
	 * @return Whether or not the left shoulder button is pressed.
	 */
	public boolean getLeftShoulder() {
		return getRawButton(XboxControllerMap.LEFT_SHOULDER);
	}

	/**
	 * Returns whether or not the right shoulder button is pressed.
	 * 
	 * @return Whether or not the right shoulder button is pressed.
	 */
	public boolean getRightShoulder() {
		return getRawButton(XboxControllerMap.RIGHT_SHOULDER);
	}

	/**
	 * Returns whether or not the view (small left center) button is pressed.
	 * 
	 * @return Whether or not the view button is pressed.
	 */
	public boolean getViewButton() {
		return getRawButton(XboxControllerMap.VIEW_BUTTON);
	}

	/**
	 * Returns whether or not the menu (small right center) button is pressed.
	 * 
	 * @return Whether or not the menu button is pressed.
	 */
	public boolean getMenuButton() {
		return getRawButton(XboxControllerMap.MENU_BUTTON);
	}

	/**
	 * Returns whether or not the left thumbstick is pressed.
	 * 
	 * @return Whether or not the left thumbstick is pressed.
	 */
	public boolean getLeftThumbButton() {
		return getRawButton(XboxControllerMap.LEFT_THUMB_BUTTON);
	}

	/**
	 * Returns whether or not the right thumbstick is pressed.
	 * 
	 * @return Whether or not the right thumbstick is pressed.
	 */
	public boolean getRightThumbButton() {
		return getRawButton(XboxControllerMap.RIGHT_THUMB_BUTTON);
	}

	/**
	 * Returns the angle of the left thumbstick in compass degrees from 0-360.0. Returns -1 if the stick is not pressed.
	 * 
	 * @return The direction of the left thumbstick.
	 */
	public double getLeftDirection() {
		double x = getLeftX();
		double y = getCorrectedLeftY();
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

	/**
	 * Returns the magnitude of the left thumbstick from 0-1.
	 * 
	 * @return The magnitude of the left thumbstick.
	 */
	public double getLeftMagnitude() {
		if(Math.abs(getLeftX()) > 0.3 || Math.abs(getLeftY()) > 0.3) {
			return Math.sqrt(Math.abs(getLeftY() * getLeftY()) + Math.abs(getLeftX() * getLeftX()));
		}
		return 0.0;
	}

	/**
	 * Returns the angle of the right thumbstick in compass degrees from 0-360.0. Returns -1 if the stick is not pressed.
	 * 
	 * @return The direction of the right thumbstick.
	 */
	public double getRightDirection() {
		double x = getRightX();
		double y = getCorrectedRightY();
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

	/**
	 * Returns the magnitude of the right thumbstick from 0-1.
	 * 
	 * @return The magnitude of the right thumbstick.
	 */
	public double getRightMagnitude() {
		if(Math.abs(getRightX()) > 0.3 || Math.abs(getRightY()) > 0.3) {
			return Math.sqrt(Math.abs(getRightY() * getRightY()) + Math.abs(getRightX() * getRightX()));
		}
		return 0.0;
	}
	
	public double getLimitedLeftTrigger() {
		return (getLeftTrigger() > 0.25) ? 0.050 : 1.0;
	}
	
	/**
	 * Generates a string containing the polar status of both thumbsticks.
	 * 
	 * @return A string containing the polar status of both thumbsticks.
	 */
	public String toString() {
		return "Left Direction: " + String.valueOf(getLeftDirection()).substring(0, 4) + "   " +
			   "Left Magnitude: " + String.valueOf(getLeftMagnitude()).substring(0, 4) + "   " +
			   "Right Direction: " + String.valueOf(getRightDirection()).substring(0, 4) + "   " +
			   "Right Magnitude: " + String.valueOf(getRightMagnitude()).substring(0, 4);
	}

}
