import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class sinTestCase0 {

    double x, same;
    double pi;

    @Before
    public void setUp() {
        // location_input
        x = 11965679.062678386;
        same = 1e-4;
        pi = Math.PI;
    }

    // // This MR is specificly designed to detect Equivalent mutants
    // @Test(timeout = 10)
    // public void MR0() {
    // for (double radians = -10; radians < 10; radians += 0.6)
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);

    // for (double radians = 1200; radians < 1300; radians += 0.6)
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // double radians = Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = 2 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = -Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = -2 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = 3 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = -3 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = 0 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // radians = 2 / 3 * Math.PI;
    // assertTrue(radians + " " + sin.mysin(radians) + " " + Math.sin(radians),
    // Math.abs(sin.mysin(radians) - Math.sin(radians)) < 1e-6);
    // }

    @Test // sin(x) = -sin(-x)
    public void MR1() {
        double origin = sin.mysin(x);
        double follow = sin.mysin(-x);
        assertTrue(this.getClass().toString(), Math.abs(origin - follow) < same);
    }

    @Test // sin(x) = sin(x+2pi)
    public void MR2() {
        double origin = sin.mysin(x);
        double follow = sin.mysin(x + 2 * pi);
        assertTrue(this.getClass().toString(), Math.abs(origin - follow) < same);
    }

    @Test // sin(x) = sin(pi-x)
    public void MR3() {
        double origin = sin.mysin(x);
        double follow = sin.mysin(pi - x);
        assertTrue(this.getClass().toString(), Math.abs(origin - follow) < same);
    }

    @Test // sin(x) = sin(-pi-x)
    public void MR4() {
        double origin = sin.mysin(x);
        double follow = sin.mysin(-pi - x);
        assertTrue(this.getClass().toString(), Math.abs(origin - follow) < same);
    }

    @Test // sin(x) = sin(x-2pi)
    public void MR5() {
        double origin = sin.mysin(x);
        double follow = sin.mysin(x - 2 * pi);
        assertTrue(this.getClass().toString(), Math.abs(origin - follow) < same);
    }

}