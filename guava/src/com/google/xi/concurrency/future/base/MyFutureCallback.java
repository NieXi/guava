package com.google.xi.concurrency.future.base;

import com.google.common.util.concurrent.FutureCallback;

public class MyFutureCallback implements FutureCallback<String> {

    @Override
    public void onSuccess(String result) {
        // do something
        System.out.println(result);
    }

    @Override
    public void onFailure(Throwable t) {
        // do something
        System.out.println(t);
    }
}
