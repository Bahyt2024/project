package project2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox RememberMee;

    @FXML
    private Button SignUpButton;

    @FXML
    private Label TextField;

    @FXML
    private Button authSignIn;

    @FXML
    private ImageView editImage;

    @FXML
    private Button forgottt;

    @FXML
    private TextField loginfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Rectangle rectangle;
    @FXML

    void initialize() throws IOException {
        SignUpButton.setOnAction(event->{
            SignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo2/SignUp2.fxml"));
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


        RememberMee.setOnAction(actionEvent -> {
            authSignIn.setOnAction((ActionEvent event) -> {
                int a = 1;
                String username = loginfield.getText()+" "+passwordfield.getText();
                File file = new File("file2.txt");
                try(DataInputStream in = new DataInputStream(new FileInputStream("file2.txt"))){
                    while(in.available()>0){
                        String line = in.readUTF();
                        if(username.equals(line)){
                            a=a+1;}}
                        if(a>1){
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/demo2/app.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        }
                        else{

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/demo2/error.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }



            });

        });
        authSignIn.setOnAction(event -> {
            int a = 1;
            String username = loginfield.getText() +" " + passwordfield.getText();
            File file = new File("file2.txt");
            try (DataInputStream in = new DataInputStream(new FileInputStream("file2.txt"))) {
                while (in.available() > 0) {
                    String line = in.readUTF();
                    if (username.equals(line)) {
                        a=a+1;}}
                    if(a>1){
                        SeatSubject.attach(new users(loginfield.getText()));
                        authSignIn.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/demo2/app.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/demo2/error.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        forgottt.setOnAction(event->{
            forgottt.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo2/ForgorPassword.fxml"));
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

