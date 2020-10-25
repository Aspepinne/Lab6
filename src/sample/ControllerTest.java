package sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    //----------- Base64 Converter --------
    @DisplayName("Test if base64Decode returns plain text")
    @Test
    public void base64DecodeTest(){
        assertEquals("TestString", Controller.base64Decode("VGVzdFN0cmluZw=="));
    }
    @DisplayName("Test if base64Encode returns base64 encoded string")
    @Test
    public void base64EncodeTest(){
        assertEquals("VGVzdFN0cmluZw==", Controller.base64Encode("TestString"));
    }
    //----------- Web Browser ------------
    @DisplayName("Test if search returns the right url")
    @Test
    public void searchTest(){
        assertEquals("https://google.se", Controller.search("google.se"));
        assertEquals("https://www.bing.com/search?q=korv", Controller.search("korv"));
        assertEquals("https://youtube.com", Controller.search("https://youtube.com"));
    }
    //----------- Text Editor ------------
    @DisplayName("Test if wordCounter counts correctly")
    @Test
    public void wordCounterTest(){
        assertEquals(3,Controller.wordCounter("Hej på dig"));
    }
    @DisplayName("Test if file loads correctly")
    @Test
    public void loadFileTest() throws FileNotFoundException {
        assertEquals("Hej\n" + "Detta\n" + "Är\n" + "Ett\n" + "Test\n"
                , Controller.loadFile("testData/TestFile1.txt"));
    }
    @DisplayName("Test if file saves correctly")
    @Test
    public void saveToFileTest() throws IOException {
        Controller.saveToFile("testData/TestFile2.txt","Test123\nTest");
        assertEquals("Test123\n" + "Test\n", Controller.loadFile("testData/TestFile2.txt"));
    }
}