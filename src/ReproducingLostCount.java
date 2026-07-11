import java.util.concurrent.locks.ReentrantLock;

class Counter{
    private int count = 0;
    //using extrinsic lock.. to get control over lock...
    ReentrantLock lock = new ReentrantLock();

    public  void increment(){
        lock.lock();
        try{
            count++;
        }finally {
            //release only when we successfully resolved the value..
            lock.unlock();
        }

    }

    public  int get(){
        lock.lock();
        try{
            return count;
        }finally {
            lock.unlock();
        }
    }
}

//main purpose is to understand that why we need Synchronization?..
public class ReproducingLostCount {
    public static void main(String[] args) throws InterruptedException{

        Counter counter = new Counter();

        Thread[] threads = new Thread[10000];     //pool of predefined threads...

        //define each thread and cal increment....
        for(int i = 0; i<10000; i++){
            threads[i] = new Thread(counter::increment);
        }

        //start all threads....
        for(Thread t: threads){
            t.start();
        };


        //wait till start and then join...   what if we dont use below join loop than some of threads
        // might print before all completion...
        for(Thread t: threads){
            t.join();
        }


        System.out.println("Final count = " + counter.get());
    }
}
