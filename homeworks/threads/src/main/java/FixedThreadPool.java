import jdk.nashorn.internal.ir.WhileNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;

public class FixedThreadPool implements ThreadPool {
    private final int nThreads;
    private static PoolWorker[] threads;
    private static Queue queue;
    private TaskWorker taskWorker;

    public volatile Boolean isRunning = false;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];
        taskWorker = new TaskWorker();
    }

    @Override
    // запускает потоки. Потоки бездействуют, до тех пор пока не появится новое задание в очереди
    public void start() {
            System.out.println("Queue is not empty. Threads start...");
            isRunning = true;
            for (int i = 0; i < this.nThreads; i++) {
                 threads[i] = new PoolWorker();
                 System.out.println("Thread PoolWorker " + threads[i].getName() + " is started");
                 threads[i].start();
            }
    }

    @Override
    // складывает это задание в очередь. Освободившийся поток должен выполнить это задание.
    public void execute(Runnable runnable) {
        synchronized (queue) {
            System.out.println("Task was added in queue...");
            queue.offer(runnable);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Waiting queue...");
                            queue.wait();
                        } catch (InterruptedException interrExc) {

                        }
                    }

                    r = (Runnable) queue.poll();
                }

                try {
                    System.out.println("Task is running by thread " + Thread.currentThread().getName());
                    r.run();
                } catch (RuntimeException e) {

                }
            }
        }
    }

    private class TaskWorker extends Thread {
        public void run() {
            synchronized (queue) {
                System.out.println("TaskWorker is running...");
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Waiting starting threads... Queue is empty yet...");
                        queue.wait();

                    } catch (InterruptedException interrExc) {

                    }
                }
                if (!isRunning) {
                    isRunning = true;
                    FixedThreadPool.this.start();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool fixedThreadPool = new FixedThreadPool(5);
        ((FixedThreadPool) fixedThreadPool).taskWorker.start();

        for (int i = 1; i < 10; i++)  fixedThreadPool.execute(Thread.currentThread());
    }
}
