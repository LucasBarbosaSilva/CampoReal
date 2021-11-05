package edge.core;

public class Properties {
    public static boolean CLOSE_BROWSER = true;

    public static Browsers browser = Browsers.CHROME;
    public static TypeExecution type = TypeExecution.NUVEM;

    public  enum Browsers {
        FIREFOX,
        CHROME
    }

    public  enum TypeExecution {
        LOCAL,
        GRID,
        NUVEM
    }
}