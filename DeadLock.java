/**
 * 死锁：多个线程互相热着对方需要内资源，然后形成僵持。
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup("灰姑凉",0);
        Makeup g2 = new Makeup("白雪公主",1);
        g1.start();
        g2.start();
    }
}

class LipStick{
}
class Mirror{
}
class Makeup extends Thread {
    // static 保证资源只有一份
    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();
    int choice; // 选择
    String name; // 使用化妆品的人
    Makeup(String name, int choice) {
        this.name = name;
        this.choice = choice;
    }

    @Override
    public void run(){
        super.run();
        try {
            makeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipStick) {
                System.out.println(this.name + "获得口红的锁");
                Thread.sleep(1000);

                synchronized (mirror) {
                    System.out.println(this.name + "获得镜子的锁");
                }
            }
        }else {
                synchronized (mirror) {
                    System.out.println(this.name + "获得镜子的锁");

                    Thread.sleep(2000);

                    synchronized (lipStick) {
                        System.out.println(this.name + "获得口红的锁");
                    }
                }
        }
    }
}
