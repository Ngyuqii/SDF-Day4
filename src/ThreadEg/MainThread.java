package ThreadEg;

public class MainThread {
public static void main(String[] args) {
            
    System.out.println("This is the main thread.");
    
    Thread t1 = new SimpleThread();
    t1.start();
    Thread t2 = new SimpleThread();
    t2.start();
    
    System.out.println("Finished execution");
    System.out.println("Exit");
    
    }
    
}
