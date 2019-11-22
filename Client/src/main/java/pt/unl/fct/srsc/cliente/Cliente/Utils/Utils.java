package pt.unl.fct.srsc.cliente.Cliente.Utils;


import java.util.Base64;

public class Utils {

    private static final String RED = "\033[0;31m";
    private static final String RESET = "\033[0m";  // Text Reset
    private static final String WHITE = "\u001B[37m";
    private static final String BLUE_BACKGROUND = "\u001B[44m";

    public static String base64Encode(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    public static byte[] base64Decode(String input) {
        return Base64.getDecoder().decode(input);
    }

}

