package be.onurbugdayci.junit.exercise11;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Disabled
public class ThermostatTest {
   private final static int INTERVAL = 10;
   private final static int SLEEP_TIME = INTERVAL * 3;
   @Mock
   private Heating heatingMock;
   @Mock
   private Sensor sensorMock;
   @InjectMocks
   private Thermostat thermostat;

   @BeforeEach
   public void init() {
      thermostat.setInterval(INTERVAL);
      Mockito.when(sensorMock.getTemperature())
            .thenReturn(new Temperature(
                  0F));
      thermostat.start();
   }

   @AfterEach
   public void destroy() {
      thermostat.stop();
   }

   @Test
   public void testChangeCurrent() throws InterruptedException {
      Temperature target = new Temperature(20F);
      Temperature current = new Temperature(15F);
      thermostat.setTargetTemperature(target);

      for (float t = 15; t <= 20; t += 0.1F) {
         current.setValue(t);
         Mockito.when(sensorMock.getTemperature())
               .thenReturn(current);
         sleep();
         Assertions.assertTrue(thermostat.isHeating());
      }

      for (float t = 20; t <= 25; t += 0.1F) {
         current.setValue(t);
         Mockito.when(sensorMock.getTemperature())
               .thenReturn(current);
         sleep();
         Assertions.assertFalse(thermostat.isHeating());
      }

      for (float t = 25; t >= 20; t -= 0.1F) {
         current.setValue(t);
         Mockito.when(sensorMock.getTemperature())
               .thenReturn(current);
         sleep();
         Assertions.assertFalse(thermostat.isHeating());
      }

      for (float t = 19.99F; t >= 15; t -= 0.1F) {
         current.setValue(t);
         Mockito.when(sensorMock.getTemperature())
               .thenReturn(current);
         sleep();
         Assertions.assertTrue(thermostat.isHeating());
      }
   }

   @Test
   public void testChangeTarget() throws InterruptedException {
      Temperature target = new Temperature(20F);
      Temperature current = new Temperature(20F);
      thermostat.setTargetTemperature(target);
      Mockito.when(sensorMock.getTemperature())
            .thenReturn(current);

      for (float t = 15; t < 20; t += 0.1F) {
         target.setValue(t);
         thermostat.setTargetTemperature(target);
         sleep();
         Assertions.assertFalse(thermostat.isHeating());
      }

      for (float t = 20.01F; t <= 25; t += 0.1F) {
         target.setValue(t);
         thermostat.setTargetTemperature(target);
         sleep();
         Assertions.assertTrue(thermostat.isHeating());
      }

      for (float t = 25; t >= 20; t -= 0.1F) {
         target.setValue(t);
         thermostat.setTargetTemperature(target);
         sleep();
         Assertions.assertTrue(thermostat.isHeating());
      }

      for (float t = 20F; t >= 15; t -= 0.1F) {
         target.setValue(t);
         thermostat.setTargetTemperature(target);
         sleep();
         Assertions.assertFalse(thermostat.isHeating());
      }
   }

   private void sleep() {
      try {
         Thread.sleep(SLEEP_TIME);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }
}
