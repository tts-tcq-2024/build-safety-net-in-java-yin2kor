package CodeTestCoverJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class Soundex {

    private static final HashMap<String, Character> soundexCodeMap = new HashMap<String, Character>() {
        {
            put("BFPV", '1');
            put("CGJKQSXZ", '2');
            put("DT", '3');
            put("L", '4');
            put("MN", '5');
            put("R", '6');
        }
    };

    private static final Character INVALID_CHARACTER = '0';

    private static final List<Character> vowels = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private static final List<Character> ignoreAppendCharacters = Arrays.asList('W', 'H', 'Y');


    public static String generateSoundex(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        // Retain the first letter of the name and convert it to uppercase
        StringBuilder soundex = createSoundexString(name.toUpperCase());
        appendPadding(soundex);

        return soundex.substring(0, 4);
    }

    private static StringBuilder createSoundexString(String name) {
        char firstLetter = Character.toUpperCase(name.charAt(0));
        StringBuilder soundex = new StringBuilder().append(Character.toUpperCase(firstLetter));
        // Convert the rest of the name to uppercase and map to Soundex digits
        for (int charracterIndex = 1; charracterIndex < name.length(); charracterIndex++) {
            if (isValidCharacterForSoundex(name, charracterIndex)) {
                soundex.append(getCodeForCharacterFromMap(name.charAt(charracterIndex)));
            }
        }
        return soundex;

    }

    private static Character getCodeForCharacterFromMap(Character alphabet) {
        Character code = INVALID_CHARACTER;
        Optional<String> selectedKey = soundexCodeMap.keySet().stream().filter(key -> key.indexOf(alphabet) != -1).findFirst();
        if (selectedKey.isPresent()) {
            code = soundexCodeMap.get(selectedKey.get());
        }
        return code;
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
            status = (getCodeForCharacterFromMap(name.charAt(index)) == getCodeForCharacterFromMap(name.charAt(index-2)));
        }
        return status;
    }

    private static void appendPadding(StringBuilder soundex) {
        while (soundex.length() < 4) {
            soundex.append('0');
        }
    }



    private static boolean isConsecutiveCharacter(String name, int index) {
        return getCodeForCharacterFromMap(name.charAt(index)) == getCodeForCharacterFromMap(name.charAt(index-1));
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
