import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class EarthDistanceTestCase0 {

	double x1, y1, x2, y2, y;
	public static double PI = 3.14159265358979;
	public static double R = 6378.0;

	@Before
	public void setUp() {
		// location_input
		y = new Random().nextInt(100);
	}

	// // This MR is specificly designed to detect Equivalent mutants
	// @Test
	// public void MR0() {
	// double ta = x1 * 2 * PI / 360.0;
	// double tb = y1 * 2 * PI / 360.0;
	// double tx = x2 * 2 * PI / 360.0;
	// double ty = y2 * 2 * PI / 360.0;
	// double ans = R * Math.acos(Math.cos(ta) * Math.cos(tx) * Math.cos(tb - ty) +
	// Math.sin(ta) * Math.sin(tx));
	// assertTrue(this.getClass().toString(), ans == EarthDistance.dis(x1, y1, x2,
	// y2));
	// }

	@Test
	public void MR1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x1, y2, x2, y1);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x2, y1, x1, y2);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(-x1, y1, -x2, y2);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x1, -y1, x2, -y2);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x1, y1 + y, x2, y2 + y);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x1, y1, x2, 2 * y1 - y2);
		assertTrue(this.getClass().toString(),
				Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void MR7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2),
				follow = EarthDistance.dis(x1, 2 * y2 - y1, x2, y2);
		assertTrue(this.getClass().toString(), Math.abs(follow - origin) < 1e-4);
	}

	@Test
	public void CMR1_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (y2), (x1), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR1_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (y2), (-(x2)), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR1_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-(y2)), (x2), (-(y1)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR1_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((y2) + (y)), (x2), ((y1) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR1_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (y2), (x2), (2 * (y2) - (y1)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR1_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * (y1) - (y2)), (x2), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x2)), (y1), (-(x1)), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (-(y1)), (x1), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), ((y1) + (y)), (x1), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (y1), (x1), (2 * (y1) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (2 * (y2) - (y1)), (x1), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (-(y1)), (-(x2)), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), ((y1) + (y)), (-(x2)), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (y1), (-(x2)), (2 * (y1) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (2 * (y2) - (y1)), (-(x2)), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((-(y1)) + (y)), (x2), ((-(y2)) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-(y1)), (x2), (2 * (-(y1)) - (-(y2))));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * (-(y2)) - (-(y1))), (x2), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((y1) + (y)), (x2), (2 * ((y1) + (y)) - ((y2) + (y))));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * ((y2) + (y)) - ((y1) + (y))), (x2), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_7() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * (2 * (y1) - (y2)) - (y1)), (x2), (2 * (y1) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_6() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * (y2) - (y1)), (x2), (2 * (2 * (y2) - (y1)) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((2 * (y2) - (y1)) + (y)), (x2), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-(2 * (y2) - (y1))), (x2), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (2 * (y2) - (y1)), (-(x2)), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (2 * (y2) - (y1)), (x1), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR7_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (y2), (x2), (2 * (y2) - (y1)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_5() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((y1) + (y)), (x2), ((2 * (y1) - (y2)) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-(y1)), (x2), (-(2 * (y1) - (y2))));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (y1), (-(x2)), (2 * (y1) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (y1), (x1), (2 * (y1) - (y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR6_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (2 * (y1) - (y2)), (x2), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_4() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-((y1) + (y))), (x2), (-((y2) + (y))));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), ((y1) + (y)), (-(x2)), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), ((y1) + (y)), (x1), ((y2) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR5_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), ((y2) + (y)), (x2), ((y1) + (y)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_3() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (-(y1)), (-(x2)), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (-(y1)), (x1), (-(y2)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR4_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x1), (-(y2)), (x2), (-(y1)));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_2() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x2)), (y1), (-(x1)), (y2));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR3_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((-(x1)), (y2), (-(x2)), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}

	@Test
	public void CMR2_1() {
		double origin = EarthDistance.dis(x1, y1, x2, y2);
		double follow = EarthDistance.dis((x2), (y2), (x1), (y1));
		assertTrue(this.getClass().toString(), Math.abs(origin - follow) < 1e-4);
	}
}