import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import controller.Controller;

/**
 * A simple application that connects a MySQL database to a Java application.
 */
public class Main {

  public static void main(String[] args) {
    // theme for the app (FlatLaf - dependency in pom.xml)
    FlatAtomOneDarkIJTheme.setup();

    // main controller for the application
    Controller controller = new Controller();

    // render the main view
    controller.renderView();
  }
}
