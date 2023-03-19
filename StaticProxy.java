/**
 *
 * 静态代理模式总结:
 *  真实对象 和 代理对象 都要实现 同一个接口
 *  代理对象 要 代理 真是角色
 *
 *  好处：
 *      代理对象可以做很多真实对象做不了的事情
 *      真实对象专注做自己的事情
 *
 *  实际应用：
 *  Thread线程中， 创建 Runnable
 *
 *  线程开启
 *  new Thread(new Runnable) {
 *      @Override
 *      public void run() {
 *
 *      }.start();
 *  }
 *
 *  lambda 表达式 简化
 *
 *  new Thread( () -> System.out.println("我爱你") ).start();
 *   WeddingCompany weddingCompany = new WeddingCompany(new You());
 */



public class StaticProxy {

    public static void main(String[] args) {

        // new 一个真实对象
        You you = new You();

        WeddingCompany weddingCompany = new WeddingCompany(you);

        weddingCompany.HappyMarry();
    }
}


interface Marry{
    void HappyMarry();
}


// 真实角色，你去结婚
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("xxx要结婚");
    }
}

// 代理角色，帮助你结婚
class WeddingCompany implements Marry{

    // 代理谁 ---> 真实目标角色
    private Marry target;
    public WeddingCompany(Marry target) {
        this.target = target;
    }
    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after(){
        System.out.println("结婚前，布置现场");
    }

    private void before(){
        System.out.println("结婚后，收尾款");
    }
}