import java.lang.instrument.Instrumentation;

/**
 * @author Lance.
 * @time: 2017-12-04 15:59
 * @desc:
 */
public class MyAgent {


    /**
     * agent
     *
     * @author lance
     * @time: 2017-12-04 16:01:50
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println("this is premain");
    }


}
