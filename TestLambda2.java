


public class TestLambda2 {
//    static class Love implements ILove {
//        @Override
//        public void love(int a) {
//            System.out.println("I Love You ---> " + a);
//        }
//    }

    public static void main(String[] args) {

//        class Love implements ILove{
//            @Override
//            public void love(int a) {
//                System.out.println("I Love You ---> " + a);
//            }
//        }

//        ILove love = new ILove() {
//            @Override
//            public void love(int a) {
//                System.out.println("I Love You ---> " + a);
//            }
//        };

//        ILove love = (int a) -> {
//            System.out.println("I Love You ---> " + a);
//        };

        // 简化 参数类型
//        ILove love = (a) -> {
//            System.out.println("I Love You ---> " + a);
//        };

        // 简化 括号
        ILove love = a -> {
            System.out.println("I Love You ---> " + a);
        };

        // 去掉花括号
        ILove iLove = a -> System.out.println("I Love You ---> " + a);


        /**
         * 总结
         * 能去掉 花括号的愿意是因为 这个代码只有一行
         * 多个参数 也得加 括号
         */

        love.love(520);
    }
}

interface ILove{
    void love(int a);
}

