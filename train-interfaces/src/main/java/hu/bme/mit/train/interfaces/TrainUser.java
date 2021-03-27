package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	boolean getAlarmFlag();

	void overrideJoystickPosition(int joystickPosition) throws InterruptedException;

	boolean getAlarmState();

	void setAlarmState(boolean alarmState);

}
