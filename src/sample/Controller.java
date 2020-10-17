package sample;

import Commandline12.Dictionary;
import Commandline12.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    //private AutoCompletionBinding<String> autoCompletionBinding;
//    Text text = new Text();
//    text.setFont(Font.font ("Verdana", 20));
//    text.setFill(Color.RED);
    public void click(){
        String text = textField.getText();
        for (Word w : listWord){
            if (text.equals(w.getWord_target())) {
                textArea.setText(w.getWord_explain());
                textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                break;
            }
        }
    }
    Set<String> wordsSet = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int dem = 0;
        String[] wordsSet = new String[listWord.size()];
        for (Word w : listWord) {
            wordsSet[dem] = w.getWord_target();
            dem ++;
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(textField, wordsSet);
    }
}