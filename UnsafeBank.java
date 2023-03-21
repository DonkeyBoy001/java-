/**
 * 不安全的取钱
 * 两个人去银行取钱，账户
 */

public class UnsafeBank {
    public static void main(String[] args) {
        // 账户
        Account account = new Account(100,"结婚基金");

        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "GirlFriend");

        you.start();
        girlFriend.start();
    }
}


// 账户
class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行： 模拟取款
class Drawing extends Thread {
    Account account; // 账户 account
    // 取了多少钱 How much did you withdraw
    int drawingMoney;

    // 现在手里钱 Now the money in hand
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }


    @Override
//    public void run(){
    public void run(){
        // 这里的account都是主函数传过来的结婚基金的account对象实例，是同一个
        /**
         * 其实这里同步只能按方法去锁对象，
         * 但是这里具体操作Drawing类的取钱方法的是new的两个账户，
         * 我们要锁的是两个不同的对象，所以单纯在方法里加syn锁不到两个对象，所以用同步块
         */

        synchronized (account) {
            // 判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 卡内余额 = 余额 - 你的钱
            account.money = account.money - drawingMoney;

            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "余额为:" + account.money);

            // Thread.currentThread().getName() 和 this.getName() 等价
            System.out.println(this.getName() + "手里的钱:" + nowMoney);
        }
    }
}