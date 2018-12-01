package frc.robot.loops;

import frc.robot.lib.sensors.TCS34725ColorSensor;

/**
 * Periodically estimates the state of the robot using the robot's distance
 * traveled (compares two waypoints), gyroscope orientation, and velocity, among
 * various other factors. Similar to a car's odometer.
 */
public class ColorSensorLoop implements Loop 
{
    static ColorSensorLoop instance = new ColorSensorLoop();
    public static ColorSensorLoop getInstance() { return instance; }

    TCS34725ColorSensor colorSensor;
    int redVal;
    int grnVal;
    int bluVal;
    int clrVal;
    public Solenoid valveSolenoid; 
    public final int port = 1;
    public static double valveRedD = -1;
    public static double valveBlueD = 1;
    public static double valveStop = 0;

    ColorSensorLoop() 
    {
        valveSolenoid = new Solenoid(port);
        colorSensor = new TCS34725ColorSensor();
        int ret_val = colorSensor.init();;
        if (ret_val != 0)
        {
            System.out.println("ColorSensor failed to init");
        }
        System.out.println("Finished colorSensor.init()");
    }
    


    @Override
    public void onStart() 
    {
    	// no-op
    }

    @Override
    public void onLoop() 
    {
        // // read values from sensors
         int ret_val = colorSensor.readColors();

         if (ret_val != 0)
         {
            System.out.printf("Failed to read from ColorSensor: error code %d", ret_val);
            return;
         }

        // after a successful read, you can query the individual colors
        // clrVal =  colorSensor.getClearVal();
        // grnVal =  colorSensor.getGreenVal();
         redVal =  colorSensor.getRedVal();
         bluVal =  colorSensor.getBlueVal();
        if (redVal == 2)
        {
            valveSolenoid.set(valveRedD);
        }
        else if (blue == 1)
        {
            valveSolenoid.set(valveBlueD);
        }
        else
        {
            valveSolenoid.set(valveStop);
        }

        // // for now, just print out the colors
        // System.out.println(toString());
    }

    @Override
    public void onStop() 
    {
        // no-op
    }


	public String toString() 
    {
		return String.format("ColorSensor R: %3d, G: %d, B: %d, C: %d\n", 
				redVal, grnVal, bluVal, clrVal);
    }

}
