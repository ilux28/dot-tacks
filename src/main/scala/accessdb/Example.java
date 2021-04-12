package accessdb;

public class Example {
    public static void main(String[] args) {
        String s = new String("abc");
        String b = new String(s).intern();
        System.out.println(s == b);

    }
}
