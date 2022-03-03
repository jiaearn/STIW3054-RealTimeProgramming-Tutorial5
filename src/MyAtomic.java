import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomic {


    public static void main(String[] args) throws InterruptedException {
        int x;
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input x: ");
        x = sc.nextInt();
        System.out.println();

        CountSolution pt = new CountSolution(x);
        Thread t1 = new Thread(pt, "Thread-0:");
        Thread t2 = new Thread(pt, "Thread-1:");
        Thread t3 = new Thread(pt, "Thread-2:");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("Total = " + pt.getCount());
    }
}

class CountSolution implements Runnable {
    int x, total;

    public CountSolution(int a) {
        x = a;
    }

    @Override
    public void run() {
        AtomicInteger count = new AtomicInteger(x);
        for (int i = 1; i <= 10; i++) {
            processSomething(i);
            System.out.println(Thread.currentThread().getName() + " " + count);
            total += count.get();
            count.incrementAndGet();
        }
    }

    public int getCount() {
        return this.total;
    }

    private void processSomething(int i) {
        try {
            Thread.sleep(i * 100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}