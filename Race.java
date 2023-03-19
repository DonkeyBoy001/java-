public class Race implements Runnable{

    private static String winner;

    @Override
    public void run(){

        for (int i = 0; i <= 100; i++) {

            if (Thread.currentThread().getName().equals("rabbit")) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = gameOver(i);
            if (flag) break;

            System.out.println(Thread.currentThread().getName() + "--->跑了" + i + "步");
        }
    }
// 判断比赛是否完成
    private boolean gameOver(int steps) {
//        判断是否有胜利者
        if (winner != null) { // 已经存在胜利者
            return true;
        }

        if (steps == 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race, "rabbit").start();
        new Thread(race, "turtle").start();

    }
}
