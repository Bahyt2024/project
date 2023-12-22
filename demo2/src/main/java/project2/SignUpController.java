package project2;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailField;

    @FXML
    private RadioButton FemaleButton;

    @FXML
    private Button LoginOn;

    @FXML
    private RadioButton MaleButton;

    @FXML
    private RadioButton OtherButton;

    @FXML
    private Label TechnologiesFiled;

    @FXML
    private Button authSignIn;

    @FXML
    private ImageView image2;

    @FXML
    private TextField loginfield2;

    @FXML
    private PasswordField passwordfield2;

    @FXML
    private Rectangle rectangle2;


    @FXML
    void initialize() throws IOException {
        File file = new File("file2.txt");


        LoginOn.setOnAction(event -> {
            LoginOn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo2/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        authSignIn.setOnAction(event -> {
            ArrayList<String> users = new ArrayList<>();
            if (file.exists()) {
                try(DataInputStream in = new DataInputStream(new FileInputStream("file2.txt"))){
                    while(in.available()>0){
                        String a = in.readUTF();
                        users.add(a);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            users.add(loginfield2.getText());
            users.add(loginfield2.getText()+" "+passwordfield2.getText());
            users.add(loginfield2.getText());
            users.add(loginfield2.getText()+" "+passwordfield2.getText());


            try(DataOutputStream out = new DataOutputStream(new FileOutputStream("file2.txt"))){
                for (String data:users){
                    out.writeUTF(data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            authSignIn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo2/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }
}