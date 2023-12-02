package controller;

import database.DataService;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import views.Constants;
import views.MainView;

/**
 * A pseudo-service layer to handle database interactions for the inventory
 * Here we will define the methods for the queries we need to implement
 *
 * This isn't the best architecture, but it will work for this simple demo.
 * In a real application, we would want to abstract away the database logic
 *
 *
 * @see model.Warehouse
 * @see model.Product
 * @see model.ProductType
 * @see model.ProductPickup
 * @see model.ProductSupplied
 * @see model.Supplier
 *
 */
public class Controller {

  private MainView mainView;
  private DataService service;

  public Controller() {
    service = new DataService();
    mainView = new MainView("Grocery Store Database");

    attachButtonListener();
    attachDropdownListener();
  }

  public void renderView() {
    EventQueue.invokeLater(
      new Runnable() {
        public void run() {
          mainView.setVisible(true);
        }
      }
    );
  }

  /**
   * Attaches a listener to the dropdown to update the table columns
   * When the dropdown is changed, the table columns will be updated
   *
   * @see view.Form
   * @see view.Table
   * @see view.Constants
   */
  private void attachDropdownListener() {
    mainView
      .form()
      .dropdown()
      .addItemListener(
        new ItemListener() {
          public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
              int selectedIndex = mainView.form().dropdown().getSelectedIndex();
              String[] columns = Constants.getColumns(selectedIndex);
              mainView.table().setColumns(columns);
            }
          }
        }
      );
  }

  /**
   * Attaches a listener to the button to query the database
   * Gets the index of the dropdown and calls the query method
   */
  private void attachButtonListener() {
    mainView
      .form()
      .button()
      .addActionListener(e -> {
        int index = mainView.form().dropdown().getSelectedIndex(); // get dropdown index
        Object[][] data = query(index); // query the database
        mainView.table().setTable(data, Constants.Table.productCols); // update the table
      });
  }

  /**
   * Queries the database based on the dropdown index
   * @param index
   * @return Object[][] data
   */
  private Object[][] query(int index) {
    String params = mainView.form().textField().getText();
    switch (index) {
      case 0:
        return service.getProductById(Integer.parseInt(params));
      case 1:
        return service.getProductsByType(mainView.form().textField().getText());
      default:
        return new Object[][] {};
    }
  }
}