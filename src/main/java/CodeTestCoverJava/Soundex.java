package CodeTestCoverJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static javax.swing.UIManager.put;

public class Soundex {

    private static final HashMap<Character, Character> soundexMap = new HashMap<Character, Character>() {
        {
            put('B', '1');
            put('F', '1');
            put('P', '1');
            put('V', '1');
            put('C', '2');
            put('G', '2');
            put('J', '2');
            put('K', '2');
            put('Q', '2');
            put('S', '2');
            put('X', '2');
            put('Z', '2');
            put('D', '3');
            put('T', '3');
            put('L', '4');
            put('M', '5');
            put('N', '5');
            put('R', '6');
        }
    };

    private static final List<Character> vowels = Arrays.asList('a','e','i','o','u');
    private static final List<Character> ignoreAppendCharacters = Arrays.asList('w','h','y');



    public static final char INVALID_CHAR = '8';
    public static String generateSoundex(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        // Retain the first letter of the name and convert it to uppercase
        char firstLetter = Character.toUpperCase(name.charAt(0));
        StringBuilder soundex = new StringBuilder().append(Character.toUpperCase(firstLetter));

        // Convert the rest of the name to uppercase and map to Soundex digits
        for (int i = 1; i < name.length(); i++) {
            AppendToStringBuilder(soundex, GetSoundexCharacter(name,i));
        }

        while (soundex.length() < 4) {
            soundex.append('0');
        }

        return soundex.substring(0,4);
    }

    private static void AppendToStringBuilder(StringBuilder soundex, char c) {
        if (INVALID_CHAR != c) {
            soundex.append(c);
        }
    }

    private static char GetSoundexCharacter(String name, int i) {
        char current = getSoundexCode(name.charAt(i));
        char previous = getSoundexCode(name.charAt(i-1));
        if (vowels.contains(name.charAt(i))) {
            return INVALID_CHAR;
        }
        if (ignoreAppendCharacters.contains(name.charAt(i))) {
            return INVALID_CHAR;
        }
        if (current == previous) {
            return INVALID_CHAR;
        }
        if (vowels.contains(name.charAt(i-1))) {
            return current;
        }
        if (RepeatedCharactersBetweenIgnoreCharacters(name, i)) {
            return current;
        }
        return INVALID_CHAR;
    }


    private static boolean RepeatedCharactersBetweenIgnoreCharacters(String name, int currentIndex) {
        if (currentIndex <= 1) {
            return true;
        }
        if (ignoreAppendCharacters.contains(name.charAt(currentIndex-1))) {
            return !(getSoundexCode(name.charAt(currentIndex)) == getSoundexCode(name.charAt(currentIndex-2)));
        } else {
            return true;
        }
    }



    private static char getSoundexCode(char c) {
        c = Character.toUpperCase(c);
        if (soundexMap.containsKey(c)) {
            return soundexMap.get(c);
        }
        return INVALID_CHAR;
    }
}
