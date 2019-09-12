//Created by Shanice Talan on September 11, 2019
//CMPP 264 Java - Day 6 Assignment: JavaFX/SceneBuilder Application that modifies Agent table
package sample;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    //instantiating db connector and agent list
    DBHelper helper = new DBHelper();
    ObservableList<Agent> agents;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Agent> cbAgents;

    @FXML
    private TextField tfAgtId;

    @FXML
    private TextField tfAgtFirstName;

    @FXML
    private TextField tfAgtLastName;

    @FXML
    private TextField tfAgtPhone;

    @FXML
    private TextField tfAgtEmail;

    @FXML
    private TextField tfAgtPosition;

    @FXML
    private TextField tfAgencyId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    //gets agents info to instantiate list of agent objects and loads it into combobox
    private void loadCombo() throws SQLException {
        Connection conn = helper.createConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from agents");
        ArrayList<Agent> agtArrayList = new ArrayList<>();
        while (rs.next()){
        //read each row and keep making agents. integers are agent table's column indexes
            agtArrayList.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
        }
        agents = FXCollections.observableArrayList(agtArrayList);
        cbAgents.setItems(agents);
        conn.close();
    }

    @FXML
    void initialize() throws SQLException {
        //checks if each FXML elements are injected
        assert cbAgents != null : "fx:id=\"cbAgents\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtId != null : "fx:id=\"tfAgentId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtFirstName != null : "fx:id=\"tfAgtFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtLastName != null : "fx:id=\"tfAgtLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtPhone != null : "fx:id=\"tfAgtPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtEmail != null : "fx:id=\"tfAgtEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgencyId != null : "fx:id=\"tfAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'sample.fxml'.";

        loadCombo();

        //detect selection in combobox and load the selected agent's info in the text fields
        cbAgents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observable, Agent oldValue, Agent newValue) {
                if (newValue != null) {
                    tfAgtId.setText(newValue.getAgtId()+"");
                    tfAgtFirstName.setText(newValue.getAgtFirstName());
                    tfAgtLastName.setText(newValue.getAgtLastName());
                    tfAgtPhone.setText(newValue.getAgtPhone());
                    tfAgtEmail.setText(newValue.getAgtEmail());
                    tfAgtPosition.setText(newValue.getAgtPosition());
                    tfAgencyId.setText(newValue.getAgencyId()+"");
                }
            }
        });

        //all text fields are uneditable on default/on load (set in fxml) until Edit button is clicked
        //changing opacity so it's more obvious to users that the fields are not editable
        btnSave.setStyle("-fx-opacity: 0.5");
        tfAgtId.setStyle("-fx-opacity: 0.5");
        tfAgtFirstName.setStyle("-fx-opacity: 0.5");
        tfAgtLastName.setStyle("-fx-opacity: 0.5");
        tfAgtPhone.setStyle("-fx-opacity: 0.5");
        tfAgtEmail.setStyle("-fx-opacity: 0.5");
        tfAgtPosition.setStyle("-fx-opacity: 0.5");
        tfAgencyId.setStyle("-fx-opacity: 0.5");
    }

    @FXML
    private void onActionBtnEdit(ActionEvent event) throws SQLException {
    //when Edit button is clicked, enable editing on textfield and Save button, and disable Edit button
    //AgentID is a PK, it's auto-incremented in the table and we want to keep the same ID for an agent
    //so AgentID is kept as uneditable

        btnEdit.setDisable(true);
        btnSave.setDisable(false);
        tfAgtFirstName.setEditable(true);
        tfAgtLastName.setEditable(true);
        tfAgtPhone.setEditable(true);
        tfAgtEmail.setEditable(true);
        tfAgtPosition.setEditable(true);
        tfAgencyId.setEditable(true);

        btnEdit.setStyle("-fx-opacity: 0.5");
        btnSave.setStyle("-fx-opacity: 1");
        tfAgtFirstName.setStyle("-fx-opacity: 1");
        tfAgtLastName.setStyle("-fx-opacity: 1");
        tfAgtPhone.setStyle("-fx-opacity: 1");
        tfAgtEmail.setStyle("-fx-opacity: 1");
        tfAgtPosition.setStyle("-fx-opacity: 1");
        tfAgencyId.setStyle("-fx-opacity: 1");
    }

    @FXML
    //update selected agent with new input from text fields, show alert if successful or not,
    //and disable text fields and save button, and enable edit button
    private void onActionBtnSave(ActionEvent event) throws SQLException {
        Connection conn = helper.createConnection();
        String sql = "UPDATE `agents` SET `AgtFirstName`=?, `AgtLastName`=?, `AgtBusPhone`=?, `AgtEmail`=?, `AgtPosition`=?, `AgencyId`=? WHERE `AgentId`= ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //assigning ? parameters to corresponding text fields
        stmt.setString(1, tfAgtFirstName.getText());
        stmt.setString(2, tfAgtLastName.getText());
        stmt.setString(3, tfAgtPhone.getText());
        stmt.setString(4, tfAgtEmail.getText());
        stmt.setString(5, tfAgtPosition.getText());
        //validate if input for Agency Id is int
        if(!tfAgencyId.getText().matches("[0-9]")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Agency Id must be an integer!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();
        }
        else {
            stmt.setInt(6, Integer.parseInt(tfAgencyId.getText()));
        }
        stmt.setInt(7, Integer.parseInt(tfAgtId.getText()));
        int rows = stmt.executeUpdate();
        conn.close();
        if (rows == 0){ //if no rows were updated, show error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();
        }
        else { //successful update
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();
            loadCombo();
        }

        //disable Save button and textfield editing, and enable Edit button
        btnEdit.setDisable(false);
        btnSave.setDisable(true);
        tfAgtFirstName.setEditable(false);
        tfAgtLastName.setEditable(false);
        tfAgtPhone.setEditable(false);
        tfAgtEmail.setEditable(false);
        tfAgtPosition.setEditable(false);
        tfAgencyId.setEditable(false);

        btnEdit.setStyle("-fx-opacity: 1");
        btnSave.setStyle("-fx-opacity: 0.5");
        tfAgtFirstName.setStyle("-fx-opacity: 0.5");
        tfAgtLastName.setStyle("-fx-opacity: 0.5");
        tfAgtPhone.setStyle("-fx-opacity: 0.5");
        tfAgtEmail.setStyle("-fx-opacity: 0.5");
        tfAgtPosition.setStyle("-fx-opacity: 0.5");
        tfAgencyId.setStyle("-fx-opacity: 0.5");
    }
}
