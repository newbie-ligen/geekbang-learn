package com.sqz.theads;

import java.util.concurrent.*;

public class ThreadResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        m1();
        m2();
        m3();
        m4();
        m5();
        m6();
    }

    private static void m6() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        WorkThread3 workThread = new WorkThread3(cyclicBarrier);
        workThread.start();
        cyclicBarrier.await();
        System.out.println(workThread.getResult());

    }

    private static void m5() throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        new Thread(()->{
            sleepSeconds(2);
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(queue.take());
    }

    private static void m4() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WorkThread1 workThread1 = new WorkThread1(countDownLatch);
        workThread1.start();
        countDownLatch.await();
        System.out.println(workThread1.getResult());
    }

    private static void m3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Thread thread = new Thread(() -> {
            completableFuture.complete(1);
        });
        thread.start();
        System.out.println(completableFuture.get());
    }

    private static void m2() throws InterruptedException {
        WorkThread workThread = new WorkThread();
        workThread.start();
        workThread.join();
        System.out.println(workThread.getResult());

    }

    public static void m1() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            sleepSeconds(2);
            return 1;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }

    public static void sleepSeconds(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class WorkThread extends Thread {
        private Integer result;

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            result = new Integer(1);
        }

        public Integer getResult() {
            return result;
        }
    }

    static class WorkThread1 extends Thread {
        private CountDownLatch countDownLatch;
        private Integer result;
        public WorkThread1(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            result = new Integer(1);
            countDownLatch.countDown();
        }

        public Integer getResult() {
            return result;
        }
    }

    static class WorkThread3 extends Thread {
        private CyclicBarrier CyclicBarrier;
        private Integer result;
        public WorkThread3(CyclicBarrier CyclicBarrier) {
            this.CyclicBarrier = CyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            result = new Integer(1);
            try {
                CyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public Integer getResult() {
            return result;
        }
    }
}
