package edge.core;

public class Properties {
    public static boolean CLOSE_BROWSER = true;

    public static Browsers browser = Browsers.CHROME;
    public static TypeExecution type = TypeExecution.GRID;

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