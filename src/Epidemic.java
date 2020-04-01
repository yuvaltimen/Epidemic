import processing.core.PApplet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;


/**
 * World in which the population evolves based on the states of its constituents' infection status.
 */
public class Epidemic extends PApplet {

  Writer fileWriter;
  Writer stringWriter;
  Collection<Person> population;
  int timeStep;

  /**
   * Has a population, which evolve under certain rules for each timestep:
   * 1 - Each person moves according to the velocity.
   * 2 - Velocity is randomly changed by some function of velocity and position.
   * 3 -
   */
  Epidemic(Writer fileWriter, Writer stringWriter) {

    Objects.requireNonNull(fileWriter);
    Objects.requireNonNull(stringWriter);

    this.fileWriter = fileWriter;
    this.stringWriter = stringWriter;
    this.population = new HashSet<>();
    for (int i = 0; i < Constants.N; i++) {
      float x = Constants.WIDTH * Constants.random.nextFloat();
      float y = Constants.HEIGHT * Constants.random.nextFloat();
      float vx = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
      float vy = Constants.MIN_VELOCITY + (Constants.random.nextFloat() * (Constants.MAX_VELOCITY - Constants.MIN_VELOCITY));
      this.population.add(new Person(this, x, y, vx, vy));
    }
    this.timeStep = 0;
  }


  /**
   * Overrides exit method - this is to ensure that log data is written to file before exit.
   */
  @Override
  public void exit() {
    try {
      this.fileWriter.write(stringWriter.toString());
      this.fileWriter.close();
      this.stringWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    super.exit();
  }


  /**
   * Formats the current state of the Epidemic as a String.
   * 1) Overall Statistics:
   * - Population size
   * - % of population Succeptible
   * - % of population Infected
   * - % of population Removed
   *
   * 2) TODO: Add more logging
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();


    int s = 0;
    int i = 0;
    int r = 0;

    for (Person p : this.population) {
      if (p.status == Constants.Status.SUCCEPTIBLE) {
        s++;
      } else if (p.status == Constants.Status.INFECTED) {
        i++;
      } else {
        r++;
      }
    }

    sb.append("Timestep: " + this.timeStep + "\n");
    sb.append("Population size: " + Constants.N + "\n");
    sb.append("S: " + s + ", I: " + i + ", R: " + r + "\n");

    return sb.toString();

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

    try {
      this.stringWriter.write(this.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

    background(50);
    for (Person p : this.population) {
      p.render();
      p.update();
    }
    this.timeStep++;
  }


  /**
   * Main entry point to program.
   */
  public static void main(String[] args) {

    FileWriter fw = null;
    try {
      fw = new FileWriter("file.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    StringWriter sw = new StringWriter();


    String[] processingArgs = {"Epidemic"};
    Epidemic epidemic = new Epidemic(fw, sw);
    PApplet.runSketch(processingArgs, epidemic);

  }

}

