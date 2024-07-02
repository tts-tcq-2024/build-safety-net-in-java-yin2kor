package CodeTestCoverJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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

    private static final List<Character> vowels = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private static final List<Character> ignoreAppendCharacters = Arrays.asList('W', 'H', 'Y');


    public static final char INVALID_CHAR = '8';

    public static String generateSoundex(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        // Retain the first letter of the name and convert it to uppercase
        StringBuilder soundex = CreateSoundexString(name.toUpperCase());
        AppendPadding(soundex);

        return soundex.substring(0, 4);
    }

    private static StringBuilder CreateSoundexString(String name) {
        char firstLetter = Character.toUpperCase(name.charAt(0));
        StringBuilder soundex = new StringBuilder().append(Character.toUpperCase(firstLetter));
        // Convert the rest of the name to uppercase and map to Soundex digits
        for (int i = 1; i < name.length(); i++) {
            System.out.println(name.charAt(i));
            if (isValidCharacterForSoundex(name, i)) {
                System.out.println("yes");
                soundex.append(soundexMap.get(name.charAt(i)));
            }
        }
        return soundex;

    }

    private static boolean isValidCharacterForSoundex(String name, int index) {
        List<Boolean> status = new ArrayList<>();
        status.add(isAlpha(name, index));
        status.add(!isVowel(name, index));
        status.add(!isIgnoreCharacter(name, index));
        status.add(!isConsecutiveCharacter(name, index));
        status.add(!isPrefixedByIgnore(name, index));
        return  !status.contains(false);


    }


    private static boolean isPrefixedByIgnore(String name, int index) {
        boolean status = false;
        if (isIgnoreCharacter(name, index-1) && index > 1) {
            status = (soundexMap.get(name.charAt(index)) == soundexMap.get(name.charAt(index-2)));
        }
        System.out.println(status);
        return status;
    }

    private static void AppendPadding(StringBuilder soundex) {
        while (soundex.length() < 4) {
            soundex.append('0');
        }
    }



    private static boolean isConsecutiveCharacter(String name, int index) {
        return soundexMap.get(name.charAt(index)) == soundexMap.get(name.charAt(index-1));
    }

    private static boolean isIgnoreCharacter(String name, int index) {
        return ignoreAppendCharacters.contains(name.charAt(index));
    }

    private static boolean isAlpha(String name, int index) {
        return Character.isAlphabetic(name.charAt(index));
    }

    private static boolean isVowel(String name, int index) {
        return vowels.contains(name.charAt(index));
    }

}
