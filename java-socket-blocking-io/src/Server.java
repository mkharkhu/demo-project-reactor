import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
  private static final Logger log = Logger.getLogger(Server.class.getName());

  public static void main(String[] args) throws IOException {
    ExecutorService pool = Executors.newFixedThreadPool(200);
    ServerSocket serverSocket = new ServerSocket(1111);
    log.info("Server started on port 1111. Listening  for client connections...");
    try {
      while (true) {
        final Socket socket = serverSocket.accept();
        handle(socket);
      }
    } finally {
      serverSocket.close();
    }
  }

  private static void handle(Socket socket) {
    log.info("Client connected: " + socket.getRemoteSocketAddress());
    try {
      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();

      // Received from the client
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String clientRequest = bufferedReader.readLine();
      log.info("Received from " + socket.getRemoteSocketAddress() + " > " + clientRequest);

      // Send response to the client
      String serverResponse = clientRequest + ", servertime=" + System.currentTimeMillis();
      PrintWriter writer = new PrintWriter(outputStream);
      writer.println(serverResponse);
      writer.flush();
      System.out.println("Send to " + socket.getRemoteSocketAddress() + " > " + serverResponse);
    } catch (IOException e) {
      log.log(Level.WARNING, "Get error: " + e);
    }
  }
}
