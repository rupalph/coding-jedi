package concurrency;

/**
 * Created by rupalph on 10/21/19.
 */
public class LazyInitialization {

    private static boolean initialized = false;
    private static Thread t = new Thread(new Runnable() {
        public void run() {
            initialized = true;
            System.out.println("in running "+initialized);

        }
    });
    static {

        t.start();
        System.out.println("after thread");

    }

    public static void main(String[] args){
        System.out.println("in main");
        try {
            t.join();
        }catch (InterruptedException e){
            throw new AssertionError(e);
        }
        System.out.print(initialized);
    }
}
