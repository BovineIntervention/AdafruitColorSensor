package frc.robot.loops;

import frc.robot.lib.sensors.TCS34725ColorSensor;
import frc.robot.lib.sensors.TCS34725ColorSensor.TCSColor;

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
    TCSColor color;

 
    ColorSensorLoop() 
    {
        colorSensor = new TCS34725ColorSensor();
        int ret_val = colorSensor.init();
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

    int loopCnt = 0;

    @Override
    public void onLoop() 
    {
        loopCnt++;
        
        if (loopCnt >= 10)
        {
            // read values from sensors
            color = colorSensor.readColors();

            // for now, just print out the colors
            System.out.println(toString());

            loopCnt = 0;
        }
    }

    @Override
    public void onStop() 
    {
        // no-op
    }

    public String toString() {
        return "ColorSensor: " + color.toString();
    }

}
