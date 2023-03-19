import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * callable的好处
 * 1. 可以定义返回值 Define return value
 * 2. 可以抛出异常 throw an exception.
 */

public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownLoader2 webDownLoader2 = new WebDownLoader2();
        webDownLoader2.download(url, name);
        System.out.println("下载了文件名为: " + name);

        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","1.jpg");
        TestCallable t2 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","2.jpg");
        TestCallable t3 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","3.jpg");

        // 创建执行服务
        //  Create Execution Service
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 提交执行
        // Submit for execution
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 获取结果
        // Get the results
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 关闭服务
        // Shut down service
        ser.shutdownNow();
    }
}

class WebDownLoader2{
    // 下载方法
    public void download(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常");
            throw new RuntimeException(e);
        }
    }
}
