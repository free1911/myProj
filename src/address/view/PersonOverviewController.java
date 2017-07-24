package address.view;

import address.MainApp;
import javafx.fxml.FXML;
import address.model.Person;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person,String> firstNameColumn;
    @FXML
    private TableColumn<Person,String> lastNameColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    public PersonOverviewController(){}

    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(
                cellData->cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData->cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPersonDetails(newValue))
        );
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;

        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person){
        if(person!=null){
            firstnameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
        }
        else{
            firstnameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }
}
