import processing.core.PApplet;

public class Game extends PApplet {


  public void settings() {
    size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
  }


  public void setup() {
    background(0);
    Person you = new GamePerson();


  }

  public void draw() {

  }

  public static void main(String[] args) {

  }
}


class GamePerson extends Person {

  GamePerson() {

  }
}