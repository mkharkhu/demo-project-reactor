import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NioMain {
  public static void main(String[] args) throws IOException {
//    reedFromChannel();
    printFile("/home/user4258/practice/project-reactor/io-nio-server/src/data/nio-data.txt");
  }

  private static void printFile(String path) throws IOException {
    Path p = Paths.get(path);
    Stream<String> lines = Files.lines(p);
    lines.forEach(System.out::println);
  }

  private static void reedFromChannel() throws IOException {
    RandomAccessFile aFile = new RandomAccessFile("/home/user4258/practice/project-reactor/io-nio-server/src/data/nio-data.txt", "rw");
    FileChannel inChannel = aFile.getChannel();

    //create buffer with capacity of 48 bytes
    ByteBuffer buf = ByteBuffer.allocate(48);

    int bytesRead = inChannel.read(buf); //read into buffer.
    while (bytesRead != -1) {

      System.out.println("Read " + bytesRead);
      buf.flip(); //make buffer ready for read

      while(buf.hasRemaining()){
        System.out.print((char) buf.get()); // read 1 byte at a time
      }

      buf.clear();
      bytesRead = inChannel.read(buf); //make buffer ready for writing
    }
    aFile.close();
  }
}
