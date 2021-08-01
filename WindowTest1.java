package Day29;

/**
 * @ClassName: WindowTest1
 * @Description: 创建三个窗口卖票，总票数为100张，使用实现Runnable接口的方式
 * @Author: TianXing.Xue
 * @Date: 2021/7/25 12:18
 * @Version: 1.0
 * <p>
 *      比较创建线程的两种方式：
 *      开发中：优先选择：实现Runnable接口的方式
 *      1.实现的方式没有类的单继承性的局限性
 *      2.实现的方式更适合来处理多个线程有共享数据的情况
 *
 *      联系：public class Thread implements Runnable
 *      相同点：两种方式都需要重写run(),将线程要执行的逻辑声明在run()中
 **/
class Window1 implements Runnable {
    private int ticket = 100;

    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 window1 = new Window1();

        //创建的三个t 用的是同一个window1
        Thread t1 = new Thread(window1);
        Thread t2 = new Thread(window1);
        Thread t3 = new Thread(window1);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }
}
