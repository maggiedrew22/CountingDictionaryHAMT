
public class CS211HAMTHelper {

    private static String[] specials = {"amherst","college", "mammoth","computer"};

    public static String getHash(String key) {

        for (String s : specials)
            if (s.equals(key)) key = "amherst";;

        String hashed = "";

        int has = key.hashCode();
        if (has < 0) has = -1*has;
        for (int i=0; i<7;i++) {
            hashed = hashed + ((char) ('a'+ (has % 26)));
            has = has /26;
        }
        return hashed;
    }


    public static void main(String[] args) {
        System.out.println(getHash("hello"));
        System.out.println(getHash("amherst"));
        System.out.println(getHash("mammoth"));
        System.out.println(getHash("hell"+"o"));
        System.out.println(getHash("mammothamherstthisisreallylong"));

    }

}