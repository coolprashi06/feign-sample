package com.prashast.service1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ServiceHelper {


    @Async
    public void retrieveObjects(){
        int recordsOffset = 0;

        int recordsTotal = 856;
        int pullSize = 100;

        int pageSize = recordsTotal/pullSize;
        List<Integer> offsetList = new ArrayList<>();


        for(int i = 2; i<= pageSize + 1; i++){
            offsetList.add( (i-1)* pullSize + 1);
        }

        int threadNum = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);

        for(int offset: offsetList){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    makeCallToExternalSystem(offset, pullSize);
                }
            };
            executor.submit(task);
        }
        executor.shutdown();
    }

    private void makeCallToExternalSystem(int recordOffset, int recordCount){
        System.out.println("making call to external system with record offset "+ recordOffset + " and record count as "+ recordCount);
    }

    public void retrieveObjectsOtherWay() throws Exception{
        int recordsOffset = 0;

        int recordsTotal = 856;
        int pullSize = 100;

        int pageSize = recordsTotal/pullSize;
        List<Integer> offsetList = new ArrayList<>();


        for(int i = 2; i<= pageSize + 1; i++){
            offsetList.add( (i-1)* pullSize + 1);
        }

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (Integer offset : offsetList) {
            Callable<Integer> c = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    makeCallToExternalSystem(offset, pullSize);
                    return offset;
                }
            };
            tasks.add(c);
        }

        ExecutorService exec = Executors.newFixedThreadPool(2);
        List<Future<Integer>> results = exec.invokeAll(tasks);
        int sum = 0;
        for (Future<Integer> fr : results) {
            sum += fr.get();
        }
        System.out.println("sum is "+ sum);

    }

}
