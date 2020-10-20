package sample;

import Commandline12.DictionaryManagement;
import Commandline12.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static Commandline12.Dictionary.listWord;


public class Controller implements Initializable {
    @FXML
    private TextField textSearch, texttienganh, textnghia, vietedit, anhedit, anhremote, vietremote;
    @FXML
    private TextArea textMean, thongbaoedit, thongbaoremote,textArea1;
    @FXML
    private Button closeButton, search, sound;
    @FXML
    private MenuItem buttonADD, buttonEdit, buttonRemote;
    @FXML
    private ListView listView;
    private Object NullPointerException;

    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void Successfully() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Successfully..fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void wordalready() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("This word is already in the dictionary.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void notfindword() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Can not find this word..fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void addword() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ADD.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void editword() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Edit.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void remoteword() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Remote.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void hamSearch(){
        String input = new String();
        input = textSearch.getText();
        int dem1 = 0;
        if (input.equals("")) textMean.setText("Can not find this word.\n");
        else{
            for (Word w : listWord) {
                if (input.equals(w.getWord_target()) == true) {
                    textMean.setText(w.getWord_explain());
                    textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                    dem1 = 1;
                }
            }
        }
        if (dem1 == 0) textMean.setText("Can not find this word.\n");
    }
    public void hamADD() throws IOException {
        String a;
        int dem2 = 0;
        a = texttienganh.getText();
        for ( Word w : listWord){
            if (a.equals(w.getWord_target())==true) {
                wordalready();
                dem2 ++;
                break;
            }
        }
        if (dem2 == 0){
            String b = textnghia.getText();
            listWord.add(new Word(a,b));
            Successfully();
            DictionaryManagement.dictionaryExportToFile();
        }
    }
    public void hamEdit() throws IOException {
        String a = anhedit.getText();
        int dem3 =0;
        for ( Word w : listWord) {
            if (a.equals(w.getWord_target()) == true) {
                String b = vietedit.getText();
                w.setWord_explain(b);
                Successfully();
               dem3++;
            }
        }
        if (dem3 ==0) notfindword();
        DictionaryManagement.dictionaryExportToFile();
    }
    public void hamRemote() throws IOException {
        int dem4 = 0,k = -1;
        String a = anhremote.getText();
        for (Word w : listWord) {
            if (a.equals(w.getWord_target()) == true) {
                k = listWord.indexOf(w);
                dem4 ++;
            }
        }
        if (k>=0){
            listWord.remove(listWord.get(k));
            Successfully();
        }
        if (dem4 ==0) notfindword();
        DictionaryManagement.dictionaryExportToFile();
    }
    public void speakWord(){
        Audio audio = Audio.getInstance();
        InputStream sound = null;
        if (textArea1.getText().trim().isEmpty()){
            try {
                sound = audio.getAudio("Can not find this word.", "en");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                sound = audio.getAudio(textArea1.getText(), "en");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
    public void startListWord() {

            for (Word w : listWord) {
                listView.getItems().add(w.getWord_target());
            }
            textSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    listView.getItems().clear();
                    for (Word w : listWord) {
                        if (w.getWord_target().startsWith(textSearch.getText()) == true)
                            listView.getItems().add(w.getWord_target());
                    }
                    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                    for (Word w : listWord) {
                        if (selectedItem.equals(w.getWord_target())) {
                            textMean.setText(w.getWord_explain());
                            textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                            textSearch.setText(selectedItem);
                            break;
                        }
                    }
                }
            });
            listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                        for (Word w : listWord) {
                            if (selectedItem.equals(w.getWord_target())) {
                                textMean.setText(w.getWord_explain());
                                textArea1.setText(Character.toUpperCase(w.getWord_target().charAt(0)) + w.getWord_target().substring(1));
                                textSearch.setText(selectedItem);
                                break;
                            }
                        }
                    }
                }
            });
    }
    public void clearlistView(){
        listView.getItems().clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
