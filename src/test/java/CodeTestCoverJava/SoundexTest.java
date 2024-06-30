package CodeTestCoverJava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SoundexTest {

    @Test
    public void testEmptyString() {
        assertEquals(Soundex.generateSoundex(""),"");
    }

    @Test
    public void testNullString() {
        assertEquals(Soundex.generateSoundex(null), "");
    }

    @Test
    public void testSingleCharacter() {
         assertEquals(Soundex.generateSoundex("A"),"A000");
    }

    @Test
    public void testTwoCharacters() {
         assertEquals(Soundex.generateSoundex("AN"),"A500");
    }

    @Test
    public void testThreeCharacters() {
         assertEquals(Soundex.generateSoundex("AMP"),"A510");
    }

    @Test
    public void testFourCharacters() {
         assertEquals(Soundex.generateSoundex("HELP"),"H410");
    }

    @Test
    public void testConsecutiveSameCodeCharacter() {
         assertEquals(Soundex.generateSoundex("Jackson"),"J250");
    }

    @Test
    public void testRepeatedSameCodeDifferentCharacters() {
         assertEquals(Soundex.generateSoundex("Pfister"),"P236");
    }

    @Test
    public void testConsecutiveCharRepeatedSameCodeDifferentChar() {
         assertEquals(Soundex.generateSoundex("Gutierrez"),"G362");
    }

    @Test
    public void testConsecutiveCharRepeatedSameCodeDifferentChar2() {
         assertEquals(Soundex.generateSoundex("Honeyman"),"H555");
    }




}
