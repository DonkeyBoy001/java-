import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock 锁
 */
public class TestLock {

    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }

}

class TestLock2 implements Runnable{
    private int ticketNum = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock(); // 加锁
                if (ticketNum > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(ticketNum--);
                } else {
                    break;
                }

            } finally {
                // 解锁
                lock.unlock();
            }
        }
    }
}