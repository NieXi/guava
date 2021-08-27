package com.google.xi.event;

import com.google.common.eventbus.Subscribe;

public class MultiEventListener {

    @Subscribe
    public void listenA(EventA a) {
        System.out.println(a);
    }

    @Subscribe
    public void listenB(EventB b) {
        System.out.println(b);
    }
}
