/**
 * 测试 守护线程
 * 上帝守护你
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You1 you = new You1();


        Thread thread = new Thread(god);
        thread.setDaemon(true); // 默认是false，表示是用户线程

        thread.start();// 上帝守护线程启动
        new Thread(you).start();
    }

}

class God implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}

// 你
class You1 implements Runnable{
    @Override
    public void run() {
//        for(int i = 0; i < 36500000; i++) {
        for(int i = 0; i < 5; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("-====goodbye! world!");
    }
}
