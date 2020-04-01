public class Utils {

  /**
   * Calculates the sigmoid of the given input.
   * @param inp float from 0 -> infinity.
   * @return sigmoid of input.
   */
  static float sigmoid(float inp) {
    if (inp < 0) {
      throw new IllegalArgumentException(String.format("Negative input: {}", inp));
    }

    return 1/ (1 + (float)Math.exp(-inp));
  }

  // TODO: Implement shifted sigmoid
//  static float mapTo(float input, float lower, float upper) {
//    float ans =
//    return ans;
//  }

}