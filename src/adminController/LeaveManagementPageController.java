package adminController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import model.LeaveTable;
import model.LeaveTable;
import model.LeaveTable;

public class LeaveManagementPageController implements Initializable{

    @FXML
    private TableView<LeaveTable> leaveTable;

    @FXML
    private TableColumn<LeaveTable,String> emailColumn;

    @FXML
    private TableColumn<LeaveTable,String> fromdateColumn;

    @FXML
    private TableColumn<LeaveTable,String> todateColumn;

    @FXML
    private TableColumn<LeaveTable,String> statusColumn;

    @FXML
    private JFXRadioButton allRadio;

    @FXML
    private ToggleGroup myradio;

    @FXML
    private JFXRadioButton pendingRadio;

    @FXML
    private JFXRadioButton approvedRadio;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXComboBox<String> statusDropDown;

    @FXML
    private Label lbl_email;

    @FXML
    void allRadioAction(ActionEvent event) {
    	leaveTable.setItems(null);
        leaveTable.setItems(oblist);
        checkSortBy();
    }

    @FXML
    void approvedRadioAction(ActionEvent event) {
    	FilterTable();
    }

    String user_email,user_status,start_date,end_date;
	long noOfDaysBetween;
    @FXML
    void onTableClick(MouseEvent event) {
    	user_email=leaveTable.getSelectionModel().getSelectedItem().getEmail();
    	user_status=leaveTable.getSelectionModel().getSelectedItem().getStatus();
    	start_date=leaveTable.getSelectionModel().getSelectedItem().getFromdate();
    	end_date=leaveTable.getSelectionModel().getSelectedItem().getTodate();
    	
    	statusDropDown.setValue(user_status);
    	lbl_email.setText(user_email);
    	
    	
    	LocalDate dateBefore = LocalDate.parse(start_date);
    	LocalDate dateAfter = LocalDate.parse(end_date);
    		
    	//calculating number of days in between
    	noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
    		
    	//displaying the number of days
    	System.out.println(noOfDaysBetween);
    	
    }
		void updateLeaves() {
			getLeaves(user_email);
			int requestedNew=(int) noOfDaysBetween;
			int newUsed=requestedNew+PreviousUsed;
			int newLeft=totalLeaves-newUsed;
			String sql="UPDATE LEAVE_RECORD SET AVAILABLE='"+newLeft+"', USED='"+newUsed+"' WHERE EMAIL='"+user_email+"'";
			Quires.InsertQuery(sql);
		}
		
		int  totalLeaves; 
		int PreviousUsed;
		
		void getLeaves(String email) {
			try {
				Connection conn=Main.conn;
				PreparedStatement pst=conn.prepareStatement("SELECT * FROM LEAVE_RECORD WHERE EMAIL='"+email+"'");
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					totalLeaves=rs.getInt(2);
					PreviousUsed=rs.getInt(4);
					
				}
			}catch(Exception e) {
				AlertClass.showAlert(e.toString());
			}
		}
    @FXML
    void pendingRadioAction(ActionEvent event) {
    	FilterTable();
    }

    @FXML
    void updateAction(ActionEvent event) {
    	if(user_email.isEmpty()) {
    		AlertClass.showAlert("Please Select Employee");
    	}else {
    	String sql="UPDATE LEAVE SET STATUS='"+statusDropDown.getValue()+"' where EMAIL='"+user_email+"' AND FROM_DATE='"+start_date+"' AND TO_DATE='"+end_date+"'";
    	Quires.InsertQuery(sql);
    	PopulateTable("SELECT * FROM LEAVE");
    	if(statusDropDown.getValue().equals("Abrbadas")) {
    		updateLeaves();
    	}
    	}
    }
    
    
    ObservableList<LeaveTable> oblist= FXCollections.observableArrayList();
    ObservableList<LeaveTable> oblist1= FXCollections.observableArrayList();
    
    ObservableList<String> access= FXCollections.observableArrayList("Pendiente","Abrbadas");
    
    public  void  PopulateTable( String sql){
        oblist.clear();
        leaveTable.getItems().clear();
          try {
        	  Connection connection=Main.conn;
              ResultSet rs = connection.createStatement().executeQuery(sql);
              while(rs.next()){
                
                  oblist.add( new LeaveTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            
              }
              
              emailColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("email"));
              fromdateColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("fromdate"));
              todateColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("todate"));
              statusColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("status"));
          
         
          leaveTable.setItems(null);
          leaveTable.setItems(oblist);
        
          
          }
          catch (Exception e) {
                AlertClass.showAlert(e.toString());
              }

      }

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statusDropDown.setItems(access);
		statusDropDown.setValue(access.get(0));
		typingAction();
		PopulateTable("Select * from LEAVE");
		
	}

	
	String sortBy="";
	
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
	    
	    void FilterTable() {
	    	checkSortBy();
	    	   oblist1.clear();
		       for (LeaveTable employee : oblist) {
		    	       if (employee.getStatus().equals(sortBy)) {
		                     oblist1.add(employee);         
		                 }
		            
		         }
		       
		       leaveTable.setItems(null);
		       leaveTable.setItems(oblist1);
	    	
	    }
	    
    void typingAction() {
 	   txt_search.setOnKeyTyped(e->{
 	     oblist1.clear();
 	       for (LeaveTable employee : oblist) {
 	    	   		
 	    	   		if(sortBy.equals("All")) {
 	    	   			if (employee.getEmail().toUpperCase().startsWith(txt_search.getText().toUpperCase())) {
    	                     oblist1.add(employee);
    	                     
 	    	   				}	
 	    	   		}	
 	    	   		else if(employee.getEmail().toUpperCase().startsWith(txt_search.getText().toUpperCase())&&employee.getStatus().equals(sortBy)) {
 	                     oblist1.add(employee);
 	                     
 	                 }
 	             
 	         }
 	       
 	      leaveTable.setItems(null);
 	     leaveTable.setItems(oblist1);
 	   });
 	 }
 

}
