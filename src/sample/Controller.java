package sample;

import Commandline12.Dictionary;
import Commandline12.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static Commandline12.Dictionary.listWord;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class Controller implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private Button button;
    @FXML
    private TextArea textArea,textArea1;
    @FXML
    private ListView listView;
    //private AutoCompletionBinding<String> autoCompletionBinding;
//    Text text = new Text();
//    text.setFont(Font.font ("Verdana", 20));
//    text.setFill(Color.RED);
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
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            textField.setOnKeyReleased(event -> {
                if (textField.getText() != null) {
                    listView = null;
                    for ( Word w : listWord){
                        if (w.getWord_target().startsWith(textField.getText())==true) {
                            listView.getItems().add(w.getWord_target());
                        }
                    }
                    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
                event.consume();
            });
    }

}