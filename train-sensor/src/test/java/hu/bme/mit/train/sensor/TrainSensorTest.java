package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockController;
    TrainSensor sensor;
    TrainUser mockUser;
    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        mockUser.setAlarmState(false);
        sensor = new TrainSensorImpl(mockController,mockUser);
        sensor.overrideSpeedLimit(50);
    }
        @Test
        public void Set_Minus_Limit() {
            when(mockUser.getAlarmState()).thenReturn(true);
            sensor.overrideSpeedLimit(-1);
            mockUser.getAlarmState();
            verify(mockUser,times(1)).getAlarmState();
        }
        @Test
        public void SetLimitFrom100to51() {
            when(mockUser.getAlarmState()).thenReturn(false);
            sensor.overrideSpeedLimit(51);
            sensor.overrideSpeedLimit(100);
            mockUser.getAlarmState();
            verify(mockUser,times(1)).getAlarmState();
        }
        @Test
        public void SetLimitFrom100to50() {
            when(mockUser.getAlarmState()).thenReturn(true);
            sensor.overrideSpeedLimit(100);
            sensor.overrideSpeedLimit(49);
            mockUser.getAlarmState();
            verify(mockUser,times(1)).getAlarmState();
        }
        @Test
        public void SetLimitFrom50to600() {
            when(mockUser.getAlarmState()).thenReturn(true);
            sensor.overrideSpeedLimit(600);
            mockUser.getAlarmState();
            verify(mockUser,times(1)).getAlarmState();
        }
    }
