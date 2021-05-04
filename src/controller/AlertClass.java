package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertClass {

  static Alert alert=new Alert(AlertType.INFORMATION);
  
  public static void showAlert(String message) {
    alert.setContentText(message);
    alert.show();
  }
  
}
