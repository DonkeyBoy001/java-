
//实现runnable接口，
// 重写run方法，
// 执行线程需要去入runnable接口实现类。调用start方法。

public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println("多线程测试" + i);
        }
        // 创建runnable接口实现类
        TestThread3 t3 = new TestThread3();
        // 创建线程对象，然后开启线程，代理
        // Thread thread = new Thread(t3);
        //  thread.start();

        new Thread(t3).start();

    }
}
