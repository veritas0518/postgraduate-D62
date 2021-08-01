package Day29;

/**
 * @ClassName: WindowsTest2
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/7/25 14:26
 * @Version: 2.0
 *
 *          承接WindowsTest1中为解决的问题
 *        1.问题：卖票过程中，出现了重票、错票  --> 出现了线程安全问题
 *        2.问题出现的原因：当某个线程操作车票的过程中，尚未完成操作时，其他线程参与进来，也操作车票
 *        3.如何解决：当一个线程在操作ticket的时候，其他线程不能参与进来，直到线程 a 操作完ticket时，
 *                  其他线程才可以开始操作ticket。这种情况即使线程 a 出现了阻塞，也不能被改变。
 *        4.在java中，我们通过同步机制，来解决线程的安全问题
 *
 *        方式一：同步代码块
 *
 *        synchronized(同步监视器){
 *            //需要被同步的代码
 *
 *        }
 *        说明：1.操作共享数据的代码，即为需要被同步的代码  -->不能包含代码多了，也不能包含代码少了
 *             2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据
 *             3.同步监视器，俗称：锁。任何一个类的对象都可以充当锁
 *                  要求：多个线程必须要共用同一把锁。
 *             补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器
 *
 *        方式二：同步方法
 *               如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的
 *
 *        5.同步的方式，解决了线程的安全问题。  --好处
 *          操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。  --局限性
 **/

class Window2 implements Runnable {
    private int ticket = 100;
    Object obj =new Object();

    public void run() {
        while (true) {
            synchronized (obj) {  //obj就是锁
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票：票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowsTest2 {
    public static void main(String[] args) {
        Window2 window2 = new Window2();

        Thread t1 = new Thread(window2);
        Thread t2 = new Thread(window2);
        Thread t3 = new Thread(window2);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
