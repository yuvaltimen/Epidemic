import processing.core.PApplet;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Constants available to our program.
 */
interface Constants {


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

/**
 * World in which the population evolves based on the states of its constituents' infection status.
 */
public class Epidemic extends PApplet {

  Collection<Person> population;


  /**
   * Has a population, which evolve under certain rules for each timestep:
   * 1 - Each person moves according to the velocity.
   * 2 - Velocity is randomly changed by some function of velocity and position.
   * 3 -
   */
  Epidemic() {
    this.population = new HashSet<>();
    for (int i = 0; i < Constants.N; i++) {
      float x = Constants.WIDTH * Constants.random.nextFloat();
      float y = Constants.HEIGHT * Constants.random.nextFloat();
      float vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
      float vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
      this.population.add(new Person(this, x, y, vx, vy));
    }
  }


  /**
   * Controls the settings - ie. size of the window.
   */
  @Override
  public void settings() {
    size(Constants.WIDTH, Constants.HEIGHT);
  }


  /**
   * Declares the setup of the image - sets the background.
   */
  @Override
  public void setup() {
    background(50);
  }

  /**
   * Draws the current scene to display.
   */
  @Override
  public void draw() {
    background(50);
    for (Person p : this.population) {
      p.render();
      p.update();
    }
  }


  public static void main(String[] args) {
    String[] processingArgs = {"EPiDemic"};
    Epidemic epidemic = new Epidemic();
    PApplet.runSketch(processingArgs, epidemic);
  }

}


/**
 * A person.
 */
class Person {

  Epidemic epidemic;
  float r = Constants.RADIUS;
  Posn position;
  Posn velocity;
  int timeInfected;
  Constants.Status status;



  Person(Epidemic sketch, float x, float y, float vx, float vy) {
    this.epidemic = sketch;
    this.position = new Posn(x, y);
    this.velocity = new Posn(vx, vy);
    boolean s = Constants.random.nextDouble() < Constants.INITIAL_INFECTED_PROPORTION;
    if (s) {
      this.status = Constants.Status.INFECTED;
    } else {
      this.status = Constants.Status.SUCCEPTIBLE;
    }
    this.timeInfected = 0;

  }


  /**
   * Draws the image of the given person.
   * @return WorldImage representing the person.
   */
  void render() {
    switch (this.status) {
      case SUCCEPTIBLE:
        this.epidemic.fill(0, 255, 0);
        break;
      case INFECTED:
        this.epidemic.fill(255, 0, 0);
        break;
      case REMOVED:
        this.epidemic.fill(128,128,128);
        break;
      default:
        throw new IllegalStateException(String.format("Invalid state: {}", this.status));
    }
    this.epidemic.ellipse(this.position.x, this.position.y, Constants.RADIUS, Constants.RADIUS);

    if (this.status == Constants.Status.INFECTED) {
      epidemic.noFill();
      this.epidemic.ellipse(this.position.x, this.position.y, Constants.INFECTION_RADIUS, Constants.INFECTION_RADIUS);
    }


  }


  /**
   * Updates the Person's fields.
   */
  void update() {
    this.move();
    this.updateStatus();
  }


  /**
   * Updates the person's position and velocity.
   * Standard Random Walk - pick a random x and y velocity from {-1, 0, 1} each time.
   */
  void move() {
    float vx;
    float vy;

    if (this.position.x >= Constants.WIDTH - this.r) {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.position.x <= this.r) {
      vx = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    if (this.position.y >= Constants.HEIGHT - this.r) {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (-Constants.MIN_VELOCITY));
    } else if (this.position.y <= this.r) {
      vy = (Constants.random.nextFloat() * Constants.MAX_VELOCITY);
    } else {
      vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
    }

    this.position.add(this.velocity);
    this.velocity.update(vx, vy);
  }

  /**
   * SUCCEPTIBLE - gets infected with probability q if in some radius R of a sick person.
   * INFECTED - gets removed after some time T.
   * REMOVED - stays that way.
   */
  void updateStatus() {

    switch (this.status) {

      // If you are succeptible, then each person near you has a Q chance of infecting you.
      case SUCCEPTIBLE:

        List<Person> infectedNearby = this.epidemic.population.stream()
                .filter(person -> new WithinRadius(Constants.INFECTION_RADIUS, this.position).test(person.position))
                .filter(person -> (person.status == Constants.Status.INFECTED))
                .collect(Collectors.toList());


        float probOfInfection;
        // The number of people near you increase your odds of infection:
        // 0  -> 0
        // 1  -> 0.2
        // 2  -> 0.4
        // 3  -> 0.7
        // 4  -> 0.9
        // 5+ -> 1
        switch(infectedNearby.size()) {
          case 0:
            probOfInfection = 0f;
            break;
          case 1:
            probOfInfection = 0.2f;
            break;
          case 2:
            probOfInfection = 0.4f;
            break;
          case 3:
            probOfInfection = 0.7f;
            break;
          case 4:
            probOfInfection = 0.9f;
            break;
          default:
            probOfInfection = 1f;
        }

        if (Constants.random.nextFloat() <= probOfInfection) {
          this.status = Constants.Status.INFECTED;
        }

        break;

      case INFECTED:
        this.timeInfected++;
        if (this.timeInfected > Constants.RECOVERY_TIME) {
          this.status = Constants.Status.REMOVED;
        }
        break;
      case REMOVED:
        break;
      default:
        throw new IllegalStateException(String.format("Invalid state: {}", this.status));

    }
  }


}



class Posn {

  float x;
  float y;

  Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  void add(Posn other) {
    this.x += other.x;
    this.y += other.y;
  }

  void update(float newX, float newY) {
    this.x = newX;
    this.y = newY;
  }
}


class WithinRadius implements Predicate<Posn> {


  float radius;
  Posn current;

  WithinRadius(float radius, Posn curr) {
    this.radius = radius;
    this.current = curr;
  }

  /**
   * Evaluates this predicate on the given argument.
   *
   * @param posn the input argument
   * @return {@code true} if the input argument matches the predicate,
   * otherwise {@code false}
   */
  @Override
  public boolean test(Posn posn) {
    return Math.pow(this.radius, 2) >= (Math.pow(this.current.x - posn.x, 2) + Math.pow(this.current.y - posn.y, 2));
  }
}