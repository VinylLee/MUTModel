public class EarthDistance {
	public static final double PI = 3.14159265358979;
	public static final double R = 6378.0;

	// a,x should be in -90 to +90, b,y should be in -180 to +180
	public static double dis(double a, double b, double x, double y) {
		a = adjustLatitude(a);
		b = adjustLongitude(b);
		x = adjustLatitude(x);
		y = adjustLongitude(y);
		a = a * 2 * PI / 360.0;
		b = b * 2 * PI / 360.0;
		x = x * 2 * PI / 360.0;
		y = y * 2 * PI / 360.0;
		double ans = R * Math.acos(Math.cos(a) * Math.cos(x) * Math.cos(b - y) + Math.sin(a) * Math.sin(x));
		return ans;
	}

	private static double adjustLongitude(double longitude) {
		double adjustedLongitude = longitude % 360;
		if (adjustedLongitude > 180) {
			adjustedLongitude -= 360;
		} else if (adjustedLongitude < -180) {
			adjustedLongitude += 360;
		}
		return adjustedLongitude;
	}

	private static double adjustLatitude(double latitude) {
		double adjustedLatitude = latitude % 180;
		if (adjustedLatitude > 90) {
			adjustedLatitude = 180 - adjustedLatitude;
		} else if (adjustedLatitude < -90) {
			adjustedLatitude = -180 - adjustedLatitude;
		}
		return adjustedLatitude;
	}

	// public static void main(String[] args) {
	// double x1 = -14.0;
	// double y1 = 107.0;
	// double x2 = -62.0;
	// double y2 = -87.0;
	// double y = new Random().nextInt(100);
	// double origin = EarthDistance.dis(x1, y1, x2, y2), follow =
	// EarthDistance.dis(x1, y1, x2, 2 * y1 - y2);
	// System.out.println(origin);
	// System.out.println(follow);

	// }
}
