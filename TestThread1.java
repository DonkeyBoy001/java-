import javax.sound.midi.Soundbank;

public class TestThread1 extends Thread{
    @Override
    public void run() {

        // run 方法创建
        for (int i = 0; i < 20; i++) {
            System.out.println("test" + i);
        }
    }

    // main方法 是主线程
    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread1 test1 = new TestThread1();
        test1.start();// start方法 开启线程

        for (int i = 0; i < 20; i++) {
            System.out.println("i am testing " + i);
        }
    }
}
