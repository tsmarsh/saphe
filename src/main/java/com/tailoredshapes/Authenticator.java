package com.tailoredshapes;

public class Authenticator {
    static void init(State state){
        state.register(State.Events.PASSWORD_SUBMIT, s -> {
            state.event(State.Events.AUTHENTICATED, v -> v);
        });
    }
}
