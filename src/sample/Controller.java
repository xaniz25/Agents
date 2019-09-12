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
    void initialize() throws SQLException {
        assert cbAgents != null : "fx:id=\"cbProducts\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtId != null : "fx:id=\"tfProductId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtFirstName != null : "fx:id=\"tfAgtFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtLastName != null : "fx:id=\"tfAgtLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtPhone != null : "fx:id=\"tfAgtPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgtEmail != null : "fx:id=\"tfAgtEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfAgencyId != null : "fx:id=\"tfAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";
        loadCombo();

        //Combo is loaded, detect selection and load selected product into textfields
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
    }

    private void loadCombo() throws SQLException {
        Connection conn = helper.createConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from agents");
        ArrayList<Agent> prodArrayList = new ArrayList<>();
        while (rs.next())
        {
            prodArrayList.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
        }
        agents = FXCollections.observableArrayList(prodArrayList);
        cbAgents.setItems(agents);
        conn.close();
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) throws SQLException {

    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException {
        Connection conn = helper.createConnection();
        String sql = "UPDATE `agents` SET `AgtFirstName`=? `AgtLastName`=? `AgtBusPhone`=? `AgtEmail`=? `AgtPosition`=? `AgencyId`=? WHERE `AgentId`= ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tfAgtFirstName.getText());
        stmt.setString(2, tfAgtLastName.getText());
        stmt.setString(3, tfAgtPhone.getText());
        stmt.setString(4, tfAgtEmail.getText());
        stmt.setString(5, tfAgtPosition.getText());
        stmt.setInt(8, Integer.parseInt(tfAgencyId.getText()));
        stmt.setInt(7, Integer.parseInt(tfAgtId.getText()));
        int rows = stmt.executeUpdate();
        conn.close();
        if (rows == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update sucessful", ButtonType.OK);
            alert.show();
            loadCombo();
        }

    }
}
