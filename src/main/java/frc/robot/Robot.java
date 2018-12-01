package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import frc.robot.loops.ColorSensorLoop;
import frc.robot.loops.LoopController;


public class Robot extends IterativeRobot {
	
	LoopController loopController;
	
	public Robot()
	{
    }
    
    
	@Override
	public void robotInit() {
		try
    	{
    		loopController = new LoopController();
    		loopController.register(ColorSensorLoop.getInstance());
    		
    	}
    	catch(Throwable t)
    	{
    		throw t;
    	}
	}
	


	/****************************************************************
	 * DISABLED MODE
	 ****************************************************************/

	@Override
	public void disabledInit()
	{
		try
		{
			loopController.start();
		}
		catch (Throwable t)
		{
			throw t;
		}
	}

	@Override
	public void disabledPeriodic()
	{
		try
		{
			System.gc(); // runs garbage collector
		}
		catch (Throwable t)
		{
			throw t;
		}
	}



	/****************************************************************
	 * AUTONOMOUS MODE
	 ****************************************************************/

	@Override
	public void autonomousInit() {
 	}

	@Override
	public void autonomousPeriodic() 
	{
 	}
	
	
	/****************************************************************
	 * TELEOP MODE
	 ****************************************************************/

	@Override
	public void teleopInit()
	{
	}
	
	int prevButtonBoardDirection = -1;
	
	@Override
	public void teleopPeriodic() 
	{
	}



	/****************************************************************
	 * TEST MODE
	 ****************************************************************/

	@Override
	public void testInit() 
	{
	}

	@Override
	public void testPeriodic()
	{
	}
	
	
	// called after disabledPeriodic, autoPeriodic, and teleopPeriodic 
	@Override
	public void robotPeriodic()
	{
	}


}

