import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Container container = new Container();
    Runnable foo = () -> {
      for (int i = 0; i < 100000; i++) {
//        System.out.println(Thread.currentThread().getName());
        container.add("foo");
      }
    };

    List<Thread> threads = new ArrayList<>();
    for (long count = 10; count > 0; count--) {
      Thread thread = new Thread(foo);
      thread.start();
      threads.add(thread);
    }
    for (Thread t : threads) {
      t.join();
    }
    System.out.println("Size is  " + container.size());
    while (container.size() < 1000000) {}
    System.out.println("Finished!");
  }
}
