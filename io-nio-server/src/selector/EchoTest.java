package selector;

import static java.nio.channels.SelectionKey.OP_READ;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.*;

public class EchoTest {

  Process server;
  EchoClient client;

  @Before
  public void setup() throws IOException, InterruptedException {
    server = EchoServer.start();
    client = EchoClient.start();
  }

  @Test
  public void givenServerClient_whenServerEchosMessage_thenCorrect() {
    String resp1 = client.sendMessage("hello");
    String resp2 = client.sendMessage("world");
    assertEquals("hello", resp1);
    assertEquals("world", resp2);
  }

  @Test
  public void whenWakeUpCalledOnSelector_thenBlockedThreadReturns() throws IOException, InterruptedException {
    Pipe pipe = Pipe.open();
    Selector selector = Selector.open();
    SelectableChannel channel = pipe.source();
    channel.configureBlocking(false);
    channel.register(selector, OP_READ);

    List<String> invocationStepsTracker = Collections.synchronizedList(new ArrayList<>());

    CountDownLatch latch = new CountDownLatch(1);

    new Thread(() -> {
      invocationStepsTracker.add(">> Count down");
      latch.countDown();
      try {
        invocationStepsTracker.add(">> Start select");
        selector.select();
        invocationStepsTracker.add(">> End select");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

    invocationStepsTracker.add(">> Start await");
    latch.await();
    invocationStepsTracker.add(">> End await");

    invocationStepsTracker.add(">> Wakeup thread");
    selector.wakeup();
    //clean up
    channel.close();

    Assert.assertEquals(invocationStepsTracker, List.of(
        ">> Start await",
        ">> Count down",
        ">> Start select",
        ">> End await",
        ">> Wakeup thread"
    ));
  }

  @After
  public void teardown() throws IOException {
    server.destroy();
    EchoClient.stop();
  }
}