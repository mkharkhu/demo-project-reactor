package client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClient {
  public static void main(String[] args) {
    List<Socket> sockets = new ArrayList<>();
    System.out.println("Opening socket connection");
    for (int i = 0; i < 10000; i++) {
      try {
        sockets.add(new Socket("localhost", 1111));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.out.println("Print any text to exit");
    new Scanner(System.in).next();

    System.out.println("Closing connection");
    for (Socket socket : sockets) {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
