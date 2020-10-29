package sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller = new Controller();
    //----------- Base64 Converter --------
    @DisplayName("Test if base64Decode returns plain text")
    @Test
    void base64DecodeTest(){
        assertEquals("TestString", controller.base64Decode("VGVzdFN0cmluZw=="));
    }
    @DisplayName("Test if base64Encode returns base64 encoded string")
    @Test
    void base64EncodeTest(){
        assertEquals("VGVzdFN0cmluZw==", controller.base64Encode("TestString"));
    }
    //----------- Web Browser ------------
    @DisplayName("Test if setUrl returns the right url")
    @Test
    void setUrlTest(){
        assertEquals("https://google.se", controller.setUrl("google.se"));
        assertEquals("https://www.bing.com/search?q=korv", controller.setUrl("korv"));
        assertEquals("https://youtube.com", controller.setUrl("https://youtube.com"));
    }
    //----------- Text Editor ------------
    @DisplayName("Test if wordCounter counts correctly")
    @Test
    void wordCounterTest(){
        assertEquals(3,controller.wordCounter("Hej på dig"));
    }
    @DisplayName("Test if file loads correctly")
    @Test
    void loadFileTest() throws FileNotFoundException {
        assertEquals("Hej\n" + "Detta\n" + "Är\n" + "Ett\n" + "Test\n"
                , controller.loadFile("testData/TestFile1.txt"));
    }
    @DisplayName("Test if file saves correctly")
    @Test
    void saveToFileTest() throws IOException {
        controller.saveToFile("testData/TestFile2.txt","Test123\nTest");
        assertEquals("Test123\n" + "Test\n", controller.loadFile("testData/TestFile2.txt"));
    }
    @DisplayName("Test if sortAlphabeticalOrder sorts correctly")
    @Test
    void sortAlphabeticalOrderTest(){
        assertEquals("Adam Bertil Carl ", controller.sortAlphabeticalOrder("Bertil Carl Adam"));
    }
}