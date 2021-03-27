package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.sql.Time;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread thread;

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) throws InterruptedException {
		this.step = joystickPosition;
		//origin: https://stackoverflow.com/questions/17758411/java-creating-a-new-thread/17758416
		thread = new Thread(){
			public void run(){
				thread.run();
				try{
					followSpeed();	//If the user changes the position of the joystick, the reference speed does change
					Thread.sleep(1000);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		};


	}

}
