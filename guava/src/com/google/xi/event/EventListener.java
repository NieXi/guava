package com.google.xi.event;

import com.google.common.eventbus.Subscribe;

public class EventListener {

    @Subscribe
    public void listen(EventA event) {
        System.out.println(Thread.currentThread().getName() + " " + event);
    }

}
