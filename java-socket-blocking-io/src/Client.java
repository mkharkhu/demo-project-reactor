import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 1111);

    OutputStream outputStream = socket.getOutputStream();
    InputStream inputStream = socket.getInputStream();

    outputStream.write("hello".getBytes());

    byte[] buffer = new byte[5];

    int bytesRead = inputStream.read(buffer);
    while (bytesRead != -1) {
      System.out.println("Read: " + bytesRead + ". [" + Arrays.toString(buffer) + "]");
      bytesRead = inputStream.read(buffer);
    }
    inputStream.close();
    outputStream.close();
  }
}
