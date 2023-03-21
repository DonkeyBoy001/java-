import java.nio.ByteBuffer;
import java.util.TreeMap;

/**
 * 线程不安全： ----> 有负数
 *
 *
 */

public class unsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "我").start();
        new Thread(station,"你们").start();
        new Thread(station, "黄牛").start();
    }
}



class BuyTicket implements Runnable{
    private int ticketNums = 10;
    boolean flag = true; // 外部停止方式

    @Override
    public void run() {
        // buy ticket
        while (flag) {
            try {
                Thread.sleep(1000);
                buy();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    private void buy() throws InterruptedException { // 不安全线程

    // 同步方法
//    锁的是this
    private synchronized void buy() throws InterruptedException { // 修改为安全线程
        // 判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

//         模拟延时
//        Thread.sleep(100000);

        // 买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }
}