import org.springframework.util.ConcurrentReferenceHashMap;

public class MainConcurrentReferenceHashMap {
  public static void main(String[] args) {
    ConcurrentReferenceHashMap<Integer, Integer> map =
        new ConcurrentReferenceHashMap(16, 0.75f, 2, ConcurrentReferenceHashMap.ReferenceType.WEAK);
    map.computeIfAbsent(
        1,
        i -> {
          System.out.println(i * 2);
          return i * 2;
        });
    System.out.println(map.get(1));
    System.gc();
    System.out.println(map.get(1));
    map.computeIfAbsent(
        1,
        i -> {
          System.out.println(i * 2);
          return i * 2;
        });
  }
}
