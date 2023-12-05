package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {
    private MecanumDrive m_robotDrive;
    private double maxSpeedPercentage = 0.25;
    private Timer timer = new Timer();

    public MecanumDriveSubsystem() {
        PWMSparkMax frontLeft = new PWMSparkMax(3);
        PWMSparkMax rearLeft = new PWMSparkMax(0);
        PWMSparkMax frontRight = new PWMSparkMax(2);
        PWMSparkMax rearRight = new PWMSparkMax(1);

        frontRight.setInverted(true);
        rearRight.setInverted(true);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void driveCartesian(double forwardAndBack, double leftAndRight, double spinAround) {
        m_robotDrive.driveCartesian(-forwardAndBack * maxSpeedPercentage, leftAndRight * maxSpeedPercentage, spinAround * maxSpeedPercentage);
    }

    public CommandBase driveForwardForFiveSecondsCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              driveForwardForFiveSeconds();
            });
    }

    public void driveForwardForFiveSeconds() {
        timer.reset();

        timer.start();

        while (timer.get() < 5.0) {
            m_robotDrive.driveCartesian(0.25, 0, 0);
        }

        timer.stop();
        m_robotDrive.stopMotor();
    }
}
