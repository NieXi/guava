package com.google.xi;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        SoftReference<String> sr = new SoftReference<>("str");
        System.out.println(sr.get());

        WeakReference<String> wr = new WeakReference<>("weakStr");
        System.out.println(wr.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();

        System.out.println(wr.get());

        ReferenceQueue<String> queue = new ReferenceQueue<>();

        String s = "s";
        PhantomReference<String> pr = new PhantomReference<>(s, queue);
        System.out.println(pr.get());
        System.out.println(queue.poll());
        System.out.println(s);
    }

}