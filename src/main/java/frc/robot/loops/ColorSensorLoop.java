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
    boolean foundRed = false;
 
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
        // read values from sensors
        color = colorSensor.readColors();
        foundRed = true;
        if ((color.getH() >= 42) && (color.getH() <= 205))
        {
            // color is in the blue & green regions
            foundRed = false;
        }   

        // counter to write to screen about 5 times/second
        loopCnt++;
        if (loopCnt >= 10)
        {
            if (foundRed)
                System.out.println("Red");
            else
                System.out.println("Blue");

            // for now, just print out the colors
            //System.out.println(toString());

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
