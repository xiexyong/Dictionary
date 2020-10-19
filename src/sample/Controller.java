package sample;

import Commandline12.Dictionary;
import Commandline12.Word;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static Commandline12.Dictionary.listWord;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private Button button;
    @FXML
    private TextArea textArea,textArea1;
    @FXML
    private ListView listView;

    public void click(){
        String text = textField.getText();
        textArea.setText("");
        textArea1.setText("");
        int dem = 0;
        for (Word w : listWord){
            if (text.equals(w.getWord_target())) {
                textArea.setText(w.getWord_explain());
                textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                dem++;
                break;
            }
        }
        if (dem==0) textArea.setText("Can not find this word.");
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            for(Word w : listWord){
                listView.getItems().add(w.getWord_target());
            }
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    listView.getItems().clear();
                    for(Word w : listWord){
                        if (w.getWord_target().startsWith(textField.getText())==true) listView.getItems().add(w.getWord_target());
                    }
                    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                    for (Word w : listWord){
                        if (selectedItem.equals(w.getWord_target())) {
                        textArea.setText(w.getWord_explain());
                        textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                        break;
                }}}
            });
            listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                        for (Word w : listWord){
                            if (selectedItem.equals(w.getWord_target())) {
                                textArea.setText(w.getWord_explain());
                                textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                                break;
                            }}
                    }
                }
            });
        }
}