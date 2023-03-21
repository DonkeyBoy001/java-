import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的集合
 * A collection of unsafe threads
 * 但是为什么会出现没到10000的情况呢 就是main线程睡眠到醒过来的时间list添加还是没有结束
 */

public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {

            new Thread(()-> {
                synchronized (list) { // 上锁，修改为安全线程
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.size());
    }
}
// 结果 size 少于 100000， 因为有些线程之间 同时重复覆盖了，导致size不够
// As a result, the size is less than 100000,
// because some threads are repeatedly covered at the same time,
// resulting in insufficient size.