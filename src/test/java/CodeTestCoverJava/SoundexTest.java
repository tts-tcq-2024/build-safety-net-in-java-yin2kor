package CodeTestCoverJava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SoundexTest {

    @ParameterizedTest
    @MethodSource("provideStringsForGenerateSoundex")
    public void testWords(String word, String actualCode) {
        Assertions.assertEquals(Soundex.generateSoundex(word), actualCode);
    }


    private static Stream<Arguments> provideStringsForGenerateSoundex() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of(null, ""),
                Arguments.of("A", "A000"),
                Arguments.of("AN", "A500"),
                Arguments.of("AMP", "A510"),
                Arguments.of("HELP", "H410"),
                Arguments.of("Jackson", "J250"),
                Arguments.of("Pfister", "P236"),
                Arguments.of("Honeyman", "H555"),
                Arguments.of("R@#*(", "R000")
        );
    }


}
