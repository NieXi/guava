package com.google.xi.event;

import com.google.common.eventbus.EventBus;

public class EventBusDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("bus");
        EventListener listener = new EventListener();
        eventBus.register(new DeadEventListener());
        eventBus.post(new EventA(1, "a"));
        eventBus.post(new EventB(2, "b"));
        eventBus.post(new EventC(3, "c"));

        eventBus.register(new EventListener());
        eventBus.post(new EventA(1, "a"));
        eventBus.post(new EventB(2, "b"));
        eventBus.post(new EventC(3, "c"));

        eventBus.register(new MultiEventListener());
        eventBus.post(new EventA(1, "a"));
        eventBus.post(new EventB(2, "b"));
        eventBus.post(new EventC(3, "c"));
    }
}
