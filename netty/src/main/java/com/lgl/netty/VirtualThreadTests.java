package com.lgl.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lgl
 * @Date 2023/9/23 20:48
 */
public class VirtualThreadTests {
   public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
       service.submit(new Runnable() {
           /**
            * Runs this operation.
            */
           @Override
           public void run() {
               for (int i = 0; i < 100; i++) {
                   System.out.println(Thread.currentThread().getName() + " " + i);
               }
           }
       });
       service.shutdown();
   }
}
