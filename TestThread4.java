/**
 * 多个线程 同时 操作 同一个对象
 * 买火车票的 例子
 */

// 发现问题： 多个线程操作同一个资源
// 线程不安全
public class TestThread4 implements Runnable{
    private int ticketNum = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) break;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticketNum-- + "票");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();

        new Thread(testThread4, "线程1").start();
        new Thread(testThread4,"线程2").start();
        new Thread(testThread4,"线程3").start();
        new Thread(testThread4,"线程3").start();

    }
}
