package com.wolkus.brain;

public class Brain implements Runnable {

	private Thread t;
    private String threadName = "Brain";

    @Override
    public void run() {

            while (true) {
                    try {
                            Thread.sleep(50);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }
    }

    public void start ()
       {
          if (t == null)
          {
             t = new Thread (this, threadName);
             t.start ();
          }
       }
    
}
