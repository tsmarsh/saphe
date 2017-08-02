package com.tailoredshapes;

import com.tailoredshapes.stash.Stash;
import com.tailoredshapes.underbar.crypto.AES;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import static com.tailoredshapes.stash.Stash.stash;

public interface NewEntry {
    static Scene display(State state){


        Label nameLabel = new Label("Name:");
        TextField name = new TextField("");

        Label valueLabel = new Label("Value:");
        TextField value = new TextField("");

        Button save = new Button("Save");

        Pane layout = new FlowPane();
        layout.getChildren().addAll(nameLabel, name, valueLabel, value, save);

        save.setOnAction(e -> {
            state.event(State.Events.NEW_ENTRY, s -> stash().assoc("NEW ENTRY", stash( "name", name.getText(), "value", value.getText())));
        });

        return new Scene(layout);
    }
}
