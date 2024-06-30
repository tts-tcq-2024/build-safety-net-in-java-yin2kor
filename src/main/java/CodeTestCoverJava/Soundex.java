package CodeTestCoverJava;

public class Soundex {

    public static String generateSoundex(String name) {
        String result = "";
        if (null != name && !name.isEmpty()) {
            StringBuilder soundex = new StringBuilder("0000");
            int index = 0;
            soundex.setCharAt(index++,Character.toUpperCase(name.charAt(0)));
            char prevCode = getSoundexCode(name.charAt(0));
            boolean canAppend = false;
            for (int i = 1; i < name.length() && index < 4; i++) {
                char code = getSoundexCode(name.charAt(i));
                if (code == '0') {
                    canAppend = true;
                    continue;
                }
                if (code == '7') {
                    prevCode = code;
                    continue;
                }
                if (canAppend || code != prevCode) {
                    soundex.setCharAt(index++,code);
                }
                prevCode = code;
                canAppend = false;
            }
            result = soundex.toString();
        }

        return result;
    }


    private static char getSoundexCode(char c) {
        c = Character.toUpperCase(c);
        switch (c) {
            case 'B': case 'F': case 'P': case 'V':
                return '1';
            case 'C': case 'G': case 'J': case 'K': case 'Q': case 'S': case 'X': case 'Z':
                return '2';
            case 'D': case 'T':
                return '3';
            case 'L':
                return '4';
            case 'M': case 'N':
                return '5';
            case 'R':
                return '6';
            case 'H' : case 'W' : case 'Y':
                return '7';
            default:
                return '0'; // For A, E, I, O, U, H, W, Y
        }
    }
}
