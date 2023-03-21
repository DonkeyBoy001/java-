import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 回归总结线程的创建
 */
public class ThreadNew {
    public static void main(String[] args) {
        new MyThread1().start();

        new Thread(new MyThread2()).start();

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}

// 1. 继承Thread类
class MyThread1 extends Thread{

    @Override
    public void run(){
        System.out.println("My Thread1");
    }
}

//2. 实现Runnable接口
class MyThread2 implements Runnable{

    @Override
    public void run(){
        System.out.println("My Thread2");
    }
}

//3. 实现Callable接口
class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 100;
    }
}