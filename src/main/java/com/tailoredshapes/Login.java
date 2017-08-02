package com.tailoredshapes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;

import static com.tailoredshapes.State.Events.*;

public class Login {

    static Scene display(State state) {
        PasswordField password = new PasswordField();

        Label errorMessage = new Label();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(password, errorMessage);

        password.setPromptText("Password");
        password.setOnKeyReleased(e -> {
            if(e.getCode().equals(KeyCode.ENTER)){
                state.event(PASSWORD_SUBMIT, s -> s.assoc("PASSWORD", password.getText()));
            }
        });

        state.register(PASSWORD_INCORRECT, s -> {
            errorMessage.setText("Password not recognized");
            password.clear();
        });

        return new Scene(vBox);
    }
}
