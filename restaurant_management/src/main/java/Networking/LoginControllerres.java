package Networking;

import Restaurant.IO;
import Restaurant.client;
import Restaurant.restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class LoginControllerres {


        private ResMain main;

        @FXML
        private TextField userText;

        @FXML
        private PasswordField passwordText;

        @FXML
        private Button resetButton;

        @FXML
        private Button loginButton;

        @FXML
        void loginAction(ActionEvent event) throws Exception {
            List<restaurant> a= IO.addres();;
            String userName = userText.getText();
            int z=0;
            if (!passwordText.getText().isEmpty()) {
                try {
                    Integer password = Integer.parseInt(passwordText.getText());
                    for(int i=0;i<a.size();i++){
                        restaurant b=a.get(i);
                        if((b.getName().equals(userName))&&(b.getId()==(password))){
                            main.showHomePage(userName);
                            z=1;
                        }
                    }
                    if(z==0){
                        main.showAlert();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
        }
        }

        @FXML
        void resetAction(ActionEvent event) {
            userText.setText(null);
            passwordText.setText(null);
        }

        void setMain(ResMain main) {
            this.main = main;
        }

    }


