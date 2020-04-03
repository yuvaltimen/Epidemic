import java.util.Random;

/**
 * Constants available to our program.
 */
public interface Constants {


  /**
   *
   */
  float SHOPPING_RATE = 3;
  /**
   * The radius at which to draw each Person as a circle.
   */
  float RADIUS = 5;
  /**
   * The radius at which each Person can transmit the disease.
   */
  float INFECTION_RADIUS = 20;
  /**
   * The percent of the population initially infected.
   */
  Double INITIAL_INFECTED_PROPORTION = 0.05;
  /**
   * Width to use for the animation.
   */
  int SCREEN_WIDTH = 900;
  /**
   * Height to use for the animation.
   */
  int SCREEN_HEIGHT = 700;
  /**
   * Number of time ticks until an infected person gets removed.
   */
  int RECOVERY_TIME = 300;
  /**
   * Time spent frozen at the market, if Epidemic is run with useMarket set to true.
   */
  int SHOPPING_TIME = 10;
  /**
   * The probability that a given person will get randomly transported to the market on each timestep.
   */
  float PROBABILITY_OF_SHOPPING = 0.005f;
  /**
   * The center of the screen along the vertical axis.
   */
  int HALF_HEIGHT = SCREEN_HEIGHT / 2;
  /**
   * The center of the screen along the horizontal axis.
   */
  int HALF_WIDTH = SCREEN_WIDTH / 2;
  /**
   * Total number of people in the simulation.
   */
  int NUMBER_OF_PEOPLE = 450;
  /**
   * Random object to be used for RNG.
   */
  Random random = new Random(100);
  /**
   * The maximum absolute value to use as the velocity for each direction.
   */
  float VELOCITY = 3;
  /**
   * The minimum bound for the velocity.
   */
  float MIN_VELOCITY = -VELOCITY;
  /**
   * The maximum bound for the velocity.
   */
  float MAX_VELOCITY = VELOCITY;
  /**
   * The possible infection states.
   */
  enum Status {
    SUSCEPTIBLE, INFECTED, REMOVED
  }
}