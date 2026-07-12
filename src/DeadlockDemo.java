public class DeadlockDemo {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            synchronized (lock1){
                System.out.println("Thread 1 acquired Lock1");
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("Thread 1 waiting to for lock2");
                synchronized (lock2) {

                    System.out.println("Thread 1 acquired Lock2");

                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (lock2) {

                System.out.println("Thread 2 acquired Lock2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread 2 waiting for Lock1");

                synchronized (lock1) {

                    System.out.println("Thread 2 acquired Lock1");

                }
            }
        });

        t1.start();
        t2.start();
    }
}
