import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 网络延时 会导致 多个线程 操作同一个对象
 *
 * 线程休眠 可以 模拟 网络延时
 *
 *
 */

public class TestSleep {

    public static void tenDown() throws InterruptedException {
        int num = 10;

        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        // 打印当前系统时间
        Date startTime = new Date(System.currentTimeMillis()); // 获取系统当前时间

        while (true) {
            try {
                Thread.sleep(1000);
                // 获取系统当前时间
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis()); // 跟新系统当前时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        tenDown();
    }
}
