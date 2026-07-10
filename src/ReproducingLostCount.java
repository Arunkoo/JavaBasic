class Counter{
    private int count = 0;
    public synchronized void increment(){    //acquire an intrinsic lock making it process one thread at a time..
        count++;
    }

    public synchronized int get(){
        return count;
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
