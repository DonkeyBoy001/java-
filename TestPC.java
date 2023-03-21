/**
 * 测试生产者消费者模型
 * 利用 缓冲区解决： 管程法
 *
 * 生产者，消费者，产品，缓冲区
 *
 * 生产者:
 * 消费者:
 * 产品:
 *
 */

public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Cumsumer(container).start();

    }
}

class Productor extends Thread{
    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            synContainer.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}
class Cumsumer extends Thread{
    SynContainer synContainer;

    public Cumsumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            long id = synContainer.pop().getId();
            System.out.println("消费了第" + id + "只鸡");

        }
    }
}
//产品
class Chicken extends Thread{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
// 缓冲区
class SynContainer{

    // 需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    // 容器已有产品计数
    int count = 0;

    // 生产者生产
    public synchronized void push(Chicken chicken){
        // 如果容器满了，等待消费者消费，没满，就加入
        if (count == chickens.length) {
            // 通知消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }

    // 消费者消耗
    public synchronized Chicken pop(){
        // 为空，就通知生产者生产，等待，否则消费
        if (count == 0) {
            // 消费者等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        count--;
        Chicken chicken1 = chickens[count];
        this.notifyAll();
        return chicken1;
    }
}