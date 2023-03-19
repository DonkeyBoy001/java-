
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.net.URL;


//实现多线程同步下载图片
public class TestThread2 extends Thread{

    private String url; // 网络图片
    private String name; // 保存的文件名

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run(){
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.download(url, name);
        System.out.println("下载了文件" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","1.jpg");
        TestThread2 t2 = new TestThread2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","2.jpg");
        TestThread2 t3 = new TestThread2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/08/03/kuangstudyaef93204-e2a6-41da-bf60-6a88e59da2b3.jpg","3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }
}

// 下载器
class WebDownLoader{
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