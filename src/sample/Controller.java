package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {

    //------- Base64 Converter -----
    @FXML
    private Label labelText;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextArea textArea;
    //------ Web Browser --------
    @FXML
    private TextField adressBar;
    @FXML
    private WebView webView;
    //------ Text Editor -------
    @FXML
    private TextField textFieldFilePath;
    @FXML
    private TextArea textEditorArea;
    @FXML
    private Label wordCountLabel;
    //------------------------ Base64 Encoder ---------------------------
    public void convertButtonClicked() {
        if (labelText.getText().equals("Base64 to Text")) {
            textArea.setText(base64Decode(textArea.getText()));
        } else {
            textArea.setText(base64Encode(textArea.getText()));
        }
    }
    /**
     * Takes a string and encodes it in base64
     * @param text A string in plain text
     * @return A string encoded in base64
     */
    String base64Encode(String text){
        byte[] bytes = text.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
    /**
     * Takes a string encoded in base64 and decodes it
     * @param base64Text A string encoded in base64
     * @return A string in plain text
     */
    String base64Decode(String base64Text){
        byte[] bytes = Base64.getDecoder().decode(base64Text);
        return new String(bytes);
    }
    public void checkBoxClicked() {
        if (checkBox.isSelected()) {
            labelText.setText("Base64 to Text");
        } else {
            labelText.setText("Text to Base64");
        }
    }
    //------------------------ Web Browser ------------------------
    public void searchWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            searchButtonClick();
        }
    }
    public void searchButtonClick() {
        String url = adressBar.getText();
        webView.getEngine().load(setUrl(url));
    }
    /**
     * Takes a string and returns a usable url
     * @param url Becomes a usable url
     * @return  The usable url
     */
    String setUrl(String url){
        if (!url.contains(".") || url.endsWith(".")) {
            url = "https://www.bing.com/search?q=" + url;
        } else if (!url.startsWith("https://")) {
            url = "https://" + url;
        }
        return url;
    }
    //------------------------ Text Editor -------------------------
    public void loadFileButtonPressed() throws FileNotFoundException {
        String path = textFieldFilePath.getText();
        textEditorArea.setText("");
        textEditorArea.setText(loadFile(path));
        updateWordCount();
    }
    /**
     * Loads a string from a file
     * @param path The path of the file that will be loaded from
     * @return The loaded string
     */
    String loadFile(String path) throws FileNotFoundException {
        File file = new File(path);
        StringBuilder loadedText = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String textLine = scanner.nextLine();
            loadedText.append(textLine).append("\n");
        }
        scanner.close();
        System.out.println(loadedText);
        return loadedText.toString();
    }
    public void saveToFileButtonPressed() throws IOException {
        String path = textFieldFilePath.getText();
        String text = textEditorArea.getText();
        saveToFile(path,text);
    }
    /**
     * Saves a string to a file
     * @param path The path of the file that will be saved to
     * @param text The string that will be saved to the file
     */
    void saveToFile(String path, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
    public void updateWordCount(){
        String text = textEditorArea.getText();
        wordCountLabel.setText("Word count: " + wordCounter(text));
    }
    /**
     * Takes in a string and count the words
     * @param text The string that will be counted
     * @return The total word count
     */
    int wordCounter(String text){
        int wordCount = 0;
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()&& text.length() > 0) {
            scanner.next();
            wordCount++;
        }
        return wordCount;
    }
    public void sortButtonPressed(){
        String text = textEditorArea.getText();
        textEditorArea.setText(sortAlphabeticalOrder(text));
    }
    /**
     * Takes in a string and sort the words in alphabetical order
     * @param text The string that will be sorted
     * @return The sorted string
     */
    String sortAlphabeticalOrder(String text){
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        String[] sortedList = words
                .stream()
                .sorted(String::compareToIgnoreCase)
                .toArray(String[]::new);
        StringBuilder stringBuilder = new StringBuilder();
        for (String wordInList : sortedList) {
            stringBuilder.append(wordInList).append(" ");
        }
        return stringBuilder.toString();
    }
    //------------------------ Launcher ----------------------------
    public void webBrowserButtonClicked() throws IOException {
        newStage("webBrowser.fxml","Web Browser",1200,700);
    }
    public void textEditorButtonClicked() throws IOException {
        newStage("textEditor.fxml","Text Editor",900,600);
    }
    public void base64ConverterButtonClicked() throws IOException {
        newStage("base64Converter.fxml","Base64 Converter",900,600);
    }
    /**
     * Creates a new window
     * @param fxmlName The name of the fxml file
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     */
    private void newStage(String fxmlName, String title, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene secondScene = new Scene(root, width, height);
        Stage newWindow = new Stage();
        newWindow.setTitle(title);
        newWindow.setScene(secondScene);
        newWindow.show();
    }
}