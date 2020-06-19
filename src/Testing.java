//import processing.core.PApplet;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//public class Testing extends PApplet {
//
//  Set<EpidemicPerson> population;
//
//  Testing() {
//    this.population = new HashSet<>();
//    this.population.add(new EpidemicPerson()
//  }
//
//
//  public static void main(String[] args) {
//
//    String[] processingArgs = {"Test Run"};
//    Testing test = new Testing();
//    PApplet.runSketch(processingArgs, test);
//  }
//
//
//
//  /**
//   * Controls the settings - ie. size of the window.
//   */
//  @Override
//  public void settings() {
//    size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
//  }
//
//
//  /**
//   * Declares the setup of the image - sets the background.
//   */
//  @Override
//  public void setup() {
//    background(50);
//  }
//
//
//  /**
//   * First, commits a message to the logbook.
//   * Then, updates the Epidemic based on its updateStrategy object.
//   * Finally, increments the time step.
//   */
//  @Override
//  public void draw() {
//    for (EpidemicPerson p : this.population) {
//      p.renderStrategy.render();
//    }
//  }
//
//}
