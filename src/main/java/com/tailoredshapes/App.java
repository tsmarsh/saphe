package com.tailoredshapes;

import com.tailoredshapes.stash.Stash;
import com.tailoredshapes.underbar.crypto.AES;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.crypto.SecretKey;

import static com.tailoredshapes.stash.Stash.stash;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        SecretKey key = AES.aesKey();

        primaryStage.setTitle("Saphe");

        State state = new State();

        Scene login = Login.display(state);
        Scene newEntry = NewEntry.display(state);
        SaveEntry.init(state, key);
        Authenticator.init(state);

        state.register(State.Events.AUTHENTICATED, s -> {
            primaryStage.setScene(newEntry);
        });

        primaryStage.setScene(login);

        primaryStage.show();
    }
}
