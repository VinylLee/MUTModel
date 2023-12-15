public class sin {
    public static double mysin(double x) {
        // Convert x to the equivalent angle between -π and π
        x = x % (2 * Math.PI);
        if (x > Math.PI)
            x -= 2 * Math.PI;
        else if (x < -Math.PI)
            x += 2 * Math.PI;
        // Calculate sine using the first few terms of the Taylor series
        double sin = x;
        double term = x;
        for (int i = 1; i < 10; ++i) {
            term = -term * x * x / ((2 * i) * (2 * i + 1));
            sin += term;
        }
        return sin;
    }
}
