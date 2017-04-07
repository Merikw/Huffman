/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman_gui;

import Models.HuffmanHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Merik
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtText;

    @FXML
    private TextField txtOutput;

    @FXML
    private Button btnCreateTree;

    @FXML
    private Button btnSaveHuffman;

    @FXML
    private Button btnLoadHuffman;

    @FXML
    private Button btn100K;

    @FXML
    private Button btn1Milion;

    private HuffmanHelper huffmanHelper;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.huffmanHelper = new HuffmanHelper();
    }

    @FXML
    private void createTree() {
        this.txtOutput.setText(this.huffmanHelper.getCompleteTree(this.txtText.getText()));
    }

    @FXML
    private void saveHuffman() {
        this.huffmanHelper.generateFile();
    }

    @FXML
    private void loadHuffman() {
        this.txtOutput.setText(this.huffmanHelper.readFile());
    }

    @FXML
    private void textTo100K() {
        this.txtOutput.setText(this.huffmanHelper.getCompleteTree10k(this.txtText.getText()));
    }

    @FXML
    private void textTo1Milion() {
        this.huffmanHelper.getCompleteTree1m(this.txtText.getText());
    }

}
