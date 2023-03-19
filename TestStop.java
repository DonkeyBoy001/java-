/**
 * 测试 stop
 * 1. 建议线程正常停止 --> 利用次数，不建议死循环
 * 2. 建议使用标志位
 * 3. 不建议使用 stop 和 destory method
 */

public class TestStop implements Runnable{

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;

        while (flag) {
            System.out.println("run thread + " + i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main" + i);
            if (i == 90) {
                // 调用stop切换标志位
                // 线程停止
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
