import java.util.Random;

/**
 * Constants available to our program.
 */
public interface Constants {


  float RADIUS = 5;
  float INFECTION_RADIUS = 20;
  Double INITIAL_INFECTED_PROPORTION = 0.05;
  Double PROBABILITY_OF_INFECTION =  0.2;
  int WIDTH = 800;
  int HEIGHT = 600;
  int RECOVERY_TIME = 500;


  int HALF_HEIGHT = HEIGHT / 2;
  int HALF_WIDTH = WIDTH / 2;
  int N = 150;
  Random random = new Random(100);

  float VELOCITY = 3;
  float MIN_VELOCITY = -VELOCITY;
  float MAX_VELOCITY = VELOCITY;


  // Possible states of infection.
  enum Status {
    SUCCEPTIBLE, INFECTED, REMOVED
  }
}