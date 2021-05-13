public class MainUtils {

  public static void main(String[] args) {
    int FLAG_DEMO = 1;
    int FLAG_ADMIN = 2;
    int FLAG_RESTRICTED = 4;
    int flag = 0;
    // 00000..0
    System.out.println(Integer.toBinaryString(flag));
    flag = flag | FLAG_DEMO;
    // 00000..01
    System.out.println(Integer.toBinaryString(flag));
    //    flag = flag | FLAG_ADMIN;
    // 00000..01 + 00000..10 = 00000..011
    //    System.out.println(Integer.toBinaryString(flag));
    flag = flag | FLAG_RESTRICTED;
    // 00000..011 + 0000..100 = 0000..0111
    System.out.println(Integer.toBinaryString(flag));

    System.out.println(FLAG_DEMO == (flag & FLAG_DEMO));
    System.out.println(FLAG_ADMIN == (flag & FLAG_ADMIN));
    System.out.println(FLAG_RESTRICTED == (flag & FLAG_RESTRICTED));
  }
}
