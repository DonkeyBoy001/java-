

public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("/////////////");
        });

        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state); // NEw

        // 观察启动后
        thread.start(); // 启动线程
        state = thread.getState();
        System.out.println(state);


        while (state != Thread.State.TERMINATED) {
            // 只要线程不终止，就一直输出状态
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }

        thread.start(); // error: 线程一旦死亡，就不能再启动
    }
}
