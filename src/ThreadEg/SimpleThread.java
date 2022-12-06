package ThreadEg;

public class SimpleThread extends Thread {
        
    public SimpleThread() {}

    @Override
    public void run() {
        System.out.println("Thread started execution + " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        System.out.println("Thread stopped execution + " + Thread.currentThread().getName());
    }
    
}