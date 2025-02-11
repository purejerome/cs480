package math;

public abstract class Vector
{
  private Vector()
  {
  }
  
  public static double dot(final double[] v, final double[] w)
  {
    double total = 0;
    int pointSize = v.length;
    for(int i = 0; i < pointSize; i++)
    {
      total += v[i] * w[i];
    }
    return total;
  }
  
  public static double[] minus(final double[] v, final double[] w)
  {
    int pointSize = v.length;
    double[] retArray = new double[pointSize];
    for(int i = 0; i < pointSize; i++)
    {
      retArray[i] = v[i] - w[i];
    }
    return retArray;
  }
  
  public static double norm(final double[] v)
  {
    return Math.sqrt(Vector.dot(v, v));
  }
  
  public static double[] normalize(final double[] v)
  {
    return Vector.times(1.0/Vector.norm(v), v);
  }
  
  public static double[] perp(final double[] v)
  {
    return new double[]{-v[1], v[0]};
  }
  
  public static double[] plus(final double[] v, final double[] w)
  {
    int pointSize = v.length;
    double[] retArray = new double[pointSize];
    for(int i = 0; i < pointSize; i++)
    {
      retArray[i] = v[i] + w[i];
    }
    return retArray;
  }
  
  public static double[] times(final double s, final double[] w)
  {
    int pointSize = w.length;
    double[] retArray = new double[pointSize];
    for(int i = 0; i < pointSize; i++)
    {
      retArray[i] = s * w[i];
    }
    return retArray;
  }
  
  public static double[] times(final double[] v, final double s)
  {
    return Vector.times(s, v);
  }
}
