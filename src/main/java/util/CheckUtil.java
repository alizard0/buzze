package util;

public class CheckUtil {

    public static void isNotNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) throw new NullPointerException();
        }
    }
}
