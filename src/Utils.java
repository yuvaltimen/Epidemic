/**
 * Utils class for miscellaneous calculations.
 */
public class Utils {

  /**
   * Inverse transform sampling for an exponential distribution with rate parameter lambda.
   */
  static float sampleExponential(float lambda) {
    return (float)(Math.log(1 - Constants.random.nextFloat()) / (-lambda));
  }

}