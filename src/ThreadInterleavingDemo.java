public class ThreadInterleavingDemo {
    public static void main(String[] args){
        //task1...
        Runnable task1 = ()-> {
            for(int i = 0; i<5; i++){
                System.out.println(Thread.currentThread().getName() + "index: " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        //task2...
        Runnable task2 = ()->{
          for(int j = 0; j<5; j++){
              System.out.println(Thread.currentThread().getName() + "index: " + j);
              try {
                  Thread.sleep(50);
              }catch (InterruptedException e){
                  throw new RuntimeException(e);
              }
          }
        };


        //initialize both
        Thread t1 = new Thread(task1, "worker-1");
        Thread t2 = new Thread(task2, "worker-2");

        t1.start();
        t2.start();


    }
}
