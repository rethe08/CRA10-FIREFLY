import com.firefly.EmpManager.EmpManager;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        if (isValidFile(args[0]) && isValidFile(args[1]))
        {
            new EmpManager().doProcess(args[0], args[1]);
        }

        System.out.println("Sorry. Please check your input file");

    }

    static boolean isValidFile(String arg) {
        if (arg == null) {
            System.out.println("Input file is null.");
            return false;
        } else if (arg.length() <= 3) {
            System.out.println("Input file is abnormally short.");
            return false;
        } else if (!Arrays.asList("txt", "TXT").contains(arg.substring(arg.length() - 3))) {
            System.out.println("Input file is not in white list.");
            return false;
        } else {
            return true;
        }
    }
}