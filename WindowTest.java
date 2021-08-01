package Day29;

/**
 * @ClassName: WindowTest
 * @Description: 创建三个窗口卖票，总票数为100张
 * @Author: TianXing.Xue
 * @Date: 2021/7/25 10:17
 * @Version:1.0
 *
 *      ！！存在安全问题，待解决
 *
 *
 **/
class Window extends Thread {
    private static int ticket = 100;  //每个线程共享同一个静态变量

    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(getName() + ": 卖票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口一");
        w1.setName("窗口二");
        w1.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}
