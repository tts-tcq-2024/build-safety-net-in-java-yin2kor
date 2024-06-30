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
        if (c == 'B' || c == 'F' || c == 'P' || c == 'V') {
            return '1';
        } else if (c == 'C' || c == 'G' || c == 'J' || c == 'K' || c == 'Q' || c == 'S' || c == 'X' || c == 'Z') {
            return '2';
        } else if (c == 'D' || c == 'T') {
            return '3';
        } else if (c == 'L') {
            return '4';
        } else if (c == 'M' || c == 'N') {
            return '5';
        } else if (c == 'R') {
            return '6';
        } else if (c == 'H' || c == 'W' || c == 'Y') {
            return '7';
        } else {
            return '0'; // For A, E, I, O, U and any other characters
        }
    }
}
