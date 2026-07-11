class Task {

    volatile boolean running = true;

    public void work() {

        while (running) {

        }

        System.out.println("Stopped : " + Thread.currentThread().getName());
    }

    public void stop() {
        running = false;
    }
}

public class VisibilityProblem {

    public static void main(String[] args) throws InterruptedException {

        Task t = new Task();

        Thread t1 = new Thread(t::work, "Worker-1");


        t1.start();


        Thread.sleep(2000);

        System.out.println("Stopping...");

        t.stop();

        t1.join();

    }
}