package hu.bme.mit.train.system;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;


public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();
		user.setAlarmState(false);
		sensor.overrideSpeedLimit(50);

	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() throws InterruptedException {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() throws InterruptedException {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

//saját tesztem:

	@Test
	public void Get_AlarmFlag_is_True() {
		boolean alarmflag;
		alarmflag = user.getAlarmFlag();
		Assert.assertEquals(false, alarmflag);
	}

	//Tachograph teszt

	@Test
	public void Tachograph_is_Working() throws InterruptedException {
		//tachograph table létrehozása
		Table<String, Integer, Integer> Tachograph
				= TreeBasedTable.create();

		Tachograph.put("12:10",user.getJoystickPosition(),controller.getReferenceSpeed());

		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Tachograph.put("12:23",user.getJoystickPosition(),controller.getReferenceSpeed());

		int refspeed
				= Tachograph.get("12:23", user.getJoystickPosition());
		Assert.assertEquals(refspeed,controller.getReferenceSpeed());
	}




}
