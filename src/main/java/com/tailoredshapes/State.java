package com.tailoredshapes;

import com.tailoredshapes.stash.Stash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.tailoredshapes.underbar.UnderBar.list;

public class State {
    enum Events {
        PASSWORD_SUBMIT,
        NEW_ENTRY, AUTHENTICATED, PASSWORD_INCORRECT
    }

    ReentrantLock stateLock = new ReentrantLock();
    private Stash state = new Stash();

    private final Map<Events, List<Consumer<Stash>>> observers = new HashMap<>();

    public void register(Events event, Consumer<Stash> action){
        synchronized (observers){
            List<Consumer<Stash>> consumers = observers.getOrDefault(event, new ArrayList<>());
            consumers.add(action);
            observers.put(event, consumers);
        }
    }

    public void event(Events event, Function<Stash, Stash> update){
        stateLock.lock();
        try {
            state = update.apply(state);
        } finally {
            stateLock.unlock();
        }

        List<Consumer<Stash>> obs = observers.getOrDefault(event, list());
        obs.forEach((ob) -> ob.accept(state));
    }
}
