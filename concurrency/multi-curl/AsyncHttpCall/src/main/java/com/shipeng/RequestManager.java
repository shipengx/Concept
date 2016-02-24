package com.shipeng;

import java.util.*;
import java.util.concurrent.*;


public class RequestManager {

    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            Callable<String> callable = new HttpRequest("http://www.google.com",i);
            Future<String> future = pool.submit(callable);
            list.add(future);
        }

        for (Future<String> future : list) {
            System.out.println(future.get());
        }
 
        pool.shutdown();
    }//end main

}//end class RequestManager




