package edge.core;

public class Properties {
    public static boolean CLOSE_BROWSER = false;

    public static Browsers browser = Browsers.CHROME;

    public static String CONTA_ALTERADA = "Conta Alterada " + System.nanoTime();

    public  enum Browsers {
        FIREFOX,
        CHROME
    }
}