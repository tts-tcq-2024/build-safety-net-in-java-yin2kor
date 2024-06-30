package CodeTestCoverJava;

public class Soundex {

    public static final char INVALID_CHAR = '8';
    public static String generateSoundex(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        // Retain the first letter of the name and convert it to uppercase
        char firstLetter = Character.toUpperCase(name.charAt(0));
        StringBuilder soundex = new StringBuilder().append(firstLetter);

        // Convert the rest of the name to uppercase and map to Soundex digits
        for (int i = 1, index = 1; i < name.length() && index < 4; i++) {
            char code = getSoundexCode(name.charAt(i));
            if (code != '0' && code != '7') {
                char currentCode = GetSoundexCharacter(name, i);
                if (INVALID_CHAR != currentCode)
                {
                    soundex.append(currentCode);
                    index++;
                }
            }
        }

        // Pad with '0' to ensure the code is 4 characters long
        while (soundex.length() < 4) {
            soundex.append('0');
        }

        return soundex.toString();
    }

    private static char GetSoundexCharacter(String name, int i) {
        char result = getSoundexCode(name.charAt(i));
        if (getSoundexCode(name.charAt(i-1)) == result) {
            return INVALID_CHAR;
        }
        if (i > 1 && getSoundexCode(name.charAt(i-1)) == '7') {
            if (getSoundexCode(name.charAt(i-2)) == result) {
                return INVALID_CHAR;
            }
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
