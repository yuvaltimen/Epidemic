import processing.core.PApplet;
import java.util.*;


/**
 * World in which the population evolves based on the states of its constituents' infection status.
 */
public class Epidemic extends PApplet {

  StringBuilder log;
  Collection<Person> population;



  /**
   * Has a population, which evolve under certain rules for each timestep:
   * 1 - Each person moves according to the velocity.
   * 2 - Velocity is randomly changed by some function of velocity and position.
   * 3 -
   */
  Epidemic() {
    this.log = new StringBuilder();
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

