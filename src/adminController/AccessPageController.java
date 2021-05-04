package adminController;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import controller.AlertClass;
import db.Quires;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.EmployeeTable;
import model.EmployeeTable;

public class AccessPageController implements Initializable{

    @FXML
    private TableView<EmployeeTable> employeeTable;

    @FXML
    private TableColumn<EmployeeTable,String> firstNameColumn;

    @FXML
    private TableColumn<EmployeeTable,String> lastNameColumn;

    @FXML
    private TableColumn<EmployeeTable,String> emailColumn;

    @FXML
    private TableColumn<EmployeeTable,String> passwordColumn;

    @FXML
    private TableColumn<EmployeeTable,String> statusColumn;

    @FXML
    private JFXRadioButton allRadio;

    @FXML
    private ToggleGroup myradio;

    @FXML
    private JFXRadioButton pendingRadio;

    @FXML
    private JFXRadioButton approvedRadio;

    @FXML
    private JFXComboBox<String> statusDropDown;
    @FXML
    private Label lbl_email;

    @FXML
    private JFXTextField txt_search;
    
    ObservableList<String>LoginStatuses=FXCollections.observableArrayList("Pendiente","Abrbadas");

    @FXML
    void updateAction(ActionEvent event) {
    	if(user_email.isEmpty()) {
    		AlertClass.showAlert("Por favor seleccione Empleada");
    	}else {
    	String sql="UPDATE EMPLOYEE SET STATUS='"+statusDropDown.getValue()+"' where EMAIL='"+user_email+"'";
    	Quires.InsertQuery(sql);
    	PopulateTable("SELECT * FROM EMPLOYEE");
    	
    	}
    }

    String user_email="",user_status="";
    @FXML
    void onTableClick(MouseEvent event) {
    	user_email=employeeTable.getSelectionModel().getSelectedItem().getEmail();
    	user_status=employeeTable.getSelectionModel().getSelectedItem().getStatus();
    	
    	statusDropDown.setValue(user_status);
    	lbl_email.setText(user_email);
    }

    @FXML
    void allRadioAction(ActionEvent event) {
    	  employeeTable.setItems(null);
          employeeTable.setItems(oblist);
          checkSortBy();
    }

    @FXML
    void approvedRadioAction(ActionEvent event) {
    	FilterTable();
    }
    @FXML
    void pendingRadioAction(ActionEvent event) {
    	FilterTable();
    }

    String sortBy;
    
    void FilterTable() {
    	checkSortBy();
    	   oblist1.clear();
	       for (EmployeeTable employee : oblist) {
	    	       if (employee.getStatus().equals(sortBy)) {
	                     oblist1.add(employee);         
	                 }
	            
	         }
	       
	       employeeTable.setItems(null);
	       employeeTable.setItems(oblist1);
    	
    }
    
    void checkSortBy() {
    	if(allRadio.isSelected()) {
    		sortBy="All";
    	}
    	if(pendingRadio.isSelected()) {
    		sortBy="Pendiente";
    	}
    	if(approvedRadio.isSelected()) {
    		sortBy="Abrbadas";
    	}
    }
    
    void typingAction() {
    	   txt_search.setOnKeyTyped(e->{
    	     oblist1.clear();
    	       for (EmployeeTable employee : oblist) {
    	    	   		
    	    	   		if(sortBy.equals("All")) {
    	    	   			if (employee.getFname().toUpperCase().startsWith(txt_search.getText().toUpperCase())) {
       	                     oblist1.add(employee);
       	                     
    	    	   				}	
    	    	   		}	
    	    	   		else if(employee.getFname().toUpperCase().startsWith(txt_search.getText().toUpperCase())&&employee.getStatus().equals(sortBy)) {
    	                     oblist1.add(employee);
    	                     
    	                 }
    	             
    	         }
    	       
    	       employeeTable.setItems(null);
    	       employeeTable.setItems(oblist1);
    	   });
    	 }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typingAction();
		statusDropDown.setItems(LoginStatuses);
		statusDropDown.setValue(LoginStatuses.get(0));
		PopulateTable("SELECT * FROM EMPLOYEE");
		
	}
	
	
	
	
	
	
	
	
    ObservableList<EmployeeTable> oblist= FXCollections.observableArrayList();
    ObservableList<EmployeeTable> oblist1= FXCollections.observableArrayList();
    
    public  void  PopulateTable( String sql){
        oblist.clear();
        employeeTable.getItems().clear();
          try {
        	  Connection connection=Main.conn;
              ResultSet rs = connection.createStatement().executeQuery(sql);
              while(rs.next()){
                
                  oblist.add( new EmployeeTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            
              }
          
          firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("fname"));
          lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("lname"));
          emailColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("email"));
          passwordColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("password"));
          statusColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("status"));
          
          
         
          employeeTable.setItems(null);
          employeeTable.setItems(oblist);
        
          
          }
          catch (Exception e) {
                AlertClass.showAlert(e.toString());
              }

      }
	
	
	
	

}
