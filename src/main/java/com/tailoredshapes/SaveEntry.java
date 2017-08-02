package com.tailoredshapes;

import com.tailoredshapes.stash.Stash;
import com.tailoredshapes.underbar.crypto.AES;
import javafx.scene.control.Alert;

import javax.crypto.SecretKey;

public class SaveEntry {
    static void init(State state, SecretKey key){
        state.register(State.Events.NEW_ENTRY, s -> {
            Stash stash = s.get("NEW ENTRY");
            AES.Payload payload = AES.encrypt(key, stash.toJSONString());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(payload.asString());
            alert.showAndWait();
        });
    }
}
