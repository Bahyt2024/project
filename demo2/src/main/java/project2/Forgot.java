package project2;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Forgot {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;

    @FXML
    private Button login12;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void initialize() {
        ArrayList<String> users = new ArrayList<>();
        login.setOnAction(event -> {
            int a =0;
            File file = new File ("file2.txt");
            if(file.exists()) {
                try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("file2.txt"))){
                    while (dataInputStream.available()>0){
                        String line = dataInputStream.readUTF();
                        users.add(line);
                        if (username.getText().equals(line)) {
                            a = a + 1;
                        }
                    }

                    if (a > 0) {
                        users.add(username.getText() + " " + password.getText());
                        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("file2.txt"))) {
                            for (String data : users) {
                                out.writeUTF(data);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        login.getScene().getWindow().hide();
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
            }
        });
        login12.setOnAction(event -> {
            login12.getScene().getWindow().hide();
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

