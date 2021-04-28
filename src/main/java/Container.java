import java.util.ArrayList;
import java.util.List;

public class Container {
  public static final List<String> list = new ArrayList<>();
  synchronized void add(String s) {
    list.add(s);
  }

  int size() {
    return list.size();
  }
}
