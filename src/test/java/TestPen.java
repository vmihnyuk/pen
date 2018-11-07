import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

public class TestPen extends Assert{

    /**
     * Data provider for positive testing of constructor
     * @return [inkContainerValue, sizeLetter, color]
     */
    @DataProvider(name = "dataPenConstructor_ObjectIsCreated")
    public Object[][] dataPenConstructor_ObjectIsCreated(){
        return new Object[][]{
                {0, 0.0000001, "GREEN"},
                {50, 1, "green"},
                {2147483647, 3.24, "Green"},
        };
    }

    /**
     * Data provider for negative testing of constructor with ONE parameter
     * @return [inkContainerValue]
     */
    @DataProvider(name = "dataPenConstructorWithOneParameter_ObjectIsNotCreated")
    public Object[][]dataPenConstructorWithOneParameter_ObjectIsNotCreated(){
        return new Object[][]{
                {-1},
                {-50}
        };
    }

    /**
     * Data provider for negative testing of constructor with TWO parameter
     * @return [inkContainerValue, sizeLetter]
     */
    @DataProvider(name = "dataPenConstructorWithTwoParameter_ObjectIsNotCreated")
    public Object[][]dataPenConstructorWithTwoParameter_ObjectIsNotCreated(){
        return new Object[][]{
                {-1, 5},
                {-50, 5},
                {5, 0},
                {5, -0.0000001},
                {5, -50.23}
        };
    }

    /**
     * Data provider for negative testing of constructor with THREE parameter
     * @return [inkContainerValue, sizeLetter, color]
     */
    @DataProvider(name = "dataPenConstructorWithThreeParameter_ObjectIsNotCreated")
    public Object[][]dataPenConstructorWithThreeParameter_ObjectIsNotCreated(){
        return new Object[][]{
                {-1, 5, "GREEN"},
                {-50, 5, "GREEN"},
                {5, 0, "GREEN"},
                {5, -0.0000001, "GREEN"},
                {5, -50.23, "GREEN"},
                {5, 1, ""},
                {5,1, "    "},
                {5, 1, "123456789"},
                {5, 1, "\"[|]’~<!--@/*$%^&#*/()?>,.*/\\ "},
                {5, 1, "Зеленый"}
        };
    }

    /**
     * Data provider for positive testing the method isWork()
     * @return [inkContainerValue]
     */
    @DataProvider(name = "dataPenWorks")
    public Object[][]PenWorks(){
        return new Object[][]{
                {1},
                {50},
                {2147483647}};
    }

    /**
     * Data for negative testing the method isWork
     * @return [inkContainerValue]
     */
    @DataProvider(name = "dataPenDoesNotWork")
    public Object[][]PenDoesNotWark(){
        return new Object[][]{
                {0},
                {-1},
                {-50},
        };
    }

    /**
     * Data for testing the method write. The Pen writes the whole word
     * @return [inkContainerValue, sizeLetter, word]
     */
    @DataProvider(name = "dataPenWritesWholeWord")
    public Object[][]PenWritesWholeWord(){
        return new Object[][]{
                {10, 1, "book"},
                {10, 1.3, "mouse"},
                {12, 2, "pencil"}
        };
    }

    /**
     * Data for testing the method write. The Pen writes part of the word
     * @return [inkContainerValue, sizeLetter, word, expectedWord]
     */
    @DataProvider(name = "dataPenWritesPartOfWord")
    public Object[][]dataPenWritesPartOfWord(){
        return new Object[][]{
                {5, 1, "computer", "compu"},
                {5, 1, "компьютер", "компь"},
                {5, 1, "1234567890", "12345"},
                {5, 1, "", ""},
                {5, 1, "!@#$%^%&*()", "!@#$%"},
                {10, 2, "1234567890", "12345"},
                {7, 1.3, "computer", "compu"},
                {5, 1, "qa computer", "qa com"},
                {1, 1, "   ", "   "}
        };
    }

    /**
     * Data for testing the method write. Pen doesn't write word
     * @return [inkContainerValue, sizeLetter]
     */
    @DataProvider(name = "PenDoesNotWriteWord")
    public Object[][]PenDoesNotWriteWord(){
        return new Object[][]{
                {0, 1},
                {1, 1.3},
                {-1, 1}
        };
    }

    /**
     * Data for method getColor and doSomethingElse
     * @return [color]
     */
    @DataProvider(name = "dataColor")
    public Object[][]dataColor() {
        return new Object[][]{
                {"GREEN"},
                {"green"},
                {"Green"},
                {"зеленый"},
                {"1234567890"},
                {"!@#$%^%&*()"},
                {""},
                {"   "}
        };
    }

    /**
     * Positive test for constructor with ONE parameter.
     * Expected result: Object created
     */
    @Test(dataProvider = "dataPenConstructor_ObjectIsCreated")
    public void testPenConstructorWithOneParam_ObjectIsCreated(int inkContainerValue, double sizeLetter, String color){
        Pen objectPen = new Pen(inkContainerValue);
        assertNotNull(objectPen, "The constructor Pen(" + inkContainerValue + ") didn't create an object\n");
    }

    /**
     * Positive test for constructor with TWO parameters.
     * Expected result: Object created
     */
    @Test(dataProvider = "dataPenConstructor_ObjectIsCreated")
    public void testPenConstructorWithTwoParam_ObjectIsCreated(int inkContainerValue, double sizeLetter, String color){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter);
        assertNotNull(objectPen, "The constructor Pen(" + inkContainerValue + ", " + sizeLetter + ") didn't create an object\n");
    }

    /**
     * Positive test for constructor with THREE parameters.
     * Expected result: Object created
     */
    @Test(dataProvider = "dataPenConstructor_ObjectIsCreated")
    public void testPenConstructorWithThreeParam_ObjectIsCreated(int inkContainerValue, double sizeLetter, String color){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter, color);
        assertNotNull(objectPen, "The constructor Pen(" + inkContainerValue + ", " + sizeLetter + ", " + color + ") didn't create an object!\n");
    }

    /**
     * Negative test for constructor with ONE parameter.
     * Expected result: Object was not created, equals null
     */
    @Test(dataProvider = "dataPenConstructorWithOneParameter_ObjectIsNotCreated", expectedExceptions = IllegalArgumentException.class)
    public void testPenConstructorWithOneParameter_ObjectIsNotCreated(int inkContainerValue){
        Pen objectPen = new Pen(inkContainerValue);
        fail(String.format("An object with incorrect parameters was created! Pen(%s) \n", inkContainerValue));
    }

    /**
     * Negative test for constructor with TWO parameters.
     * Expected result: Object was not created, equals null
     */
    @Test(dataProvider = "dataPenConstructorWithTwoParameter_ObjectIsNotCreated", expectedExceptions = IllegalArgumentException.class)
    public void testPenConstructorWithTwoParam_ObjectIsNotCreated(int inkContainerValue, double sizeLetter){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter);
        fail( String.format("An object with incorrect parameters was created! Pen(%s, %s) \n", inkContainerValue, sizeLetter ));
    }

    /**
     * Negative test for constructor with THREE parameters.
     * Expected result: Object was not created, equals null
     */
    @Test(dataProvider = "dataPenConstructorWithThreeParameter_ObjectIsNotCreated", expectedExceptions = IllegalArgumentException.class)
    public void testPenConstructorWithThreeParam_ObjectIsNotCreated(int inkContainerValue, double sizeLetter, String color){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter, color);
        fail(String.format("An object with incorrect parameters was created! Pen(%s, %s, %s)", inkContainerValue, sizeLetter, color));
    }

    /**
     * Positive test for method isWork
     * Expected result: return true - pen is work
     */
    @Test(dataProvider = "dataPenWorks")
    public void testPenIsWork_shouldWork(int inkContainerValue){
        Pen objectPen = new Pen(inkContainerValue);
        assertTrue(objectPen.isWork(), "Method isWork return false with inkContainerValue=" + inkContainerValue + "\n");
    }

    /**
     * Negative test for method isWork
     * Expected result: return false - pen does not work
     */
    @Test(dataProvider = "dataPenDoesNotWork")
    public void testPenIsWork_shouldNotWork(int inkContainerValue){
        Pen objectPen = new Pen(inkContainerValue);
        assertFalse(objectPen.isWork(), "Method isWork return true with inkContainerValue=" + inkContainerValue + "\n");
    }

    /**
     * Test for method write()
     * Expected result: Pen writes whole word
     */
    @Test(dataProvider = "dataPenWritesWholeWord")
    public void testPenWrite_WritesWholeWord(int inkContainerValue, double sizeLetter, String word){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter);
        String printedWord = objectPen.write(word);
        assertEquals(printedWord, word, "Pen did not write the whole word with enough ink\n");
    }

    /**
     * Test for method write
     * Expected result: Pen writes part of word (5 characters)
     */
    @Test(dataProvider = "dataPenWritesPartOfWord")
    public void testPenWrite_WritesPartOfWord(int inkContainerValue, double sizeLetter, String word, String expectedWord){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter);
        String printedWord = objectPen.write(word);
        assertEquals(printedWord, expectedWord, "Pen writes the wrong part of the word!\n");
    }

    /**
     * Test for method write
     * Expected result: The Pen doesn't write word
     */
    @Test(dataProvider = "PenDoesNotWriteWord")
    public void testPenWrite_PenDoesNotWriteWord(int inkContainerValue, double sizeLetter){
        Pen objectPen = new Pen(inkContainerValue, sizeLetter);
        String printedWord = objectPen.write("word");
        assertEquals(printedWord, "", "Pen writes with not enough ink\n");
    }

    /**
     * Test for method getColor
     * Expected result: The Pen returns color
     */
    @Test(dataProvider = "dataColor")
    public void testPenGetColor_returnColor(String color){
        Pen objectPen = new Pen (100, 1.0, color);
        String actualColor = objectPen.getColor();
        assertEquals(actualColor, color, "Method getColor returned " + actualColor + " instead \"" + color + "\"\n");
    }

    /**
     * Testing method doSomethingElse
     * Redirects the output stream to a file and reads color from file
     * Expected result: printed color
     */
    @Test(dataProvider = "dataColor")
    public void testPenDoSomethingElse_printedRightColor(String color) {
        Pen p = new Pen(1000, 1, color);
        File tempFile = new File("tempFile");
        try{
            PrintStream oldStream = System.out;
            FileOutputStream outFileStream = new FileOutputStream(tempFile);
            PrintStream printStream = new PrintStream(outFileStream);
            System.setOut(printStream);
            p.doSomethingElse();
            System.setOut(oldStream);
            printStream.close();
            outFileStream.close();
            FileReader fileRead = new FileReader(tempFile);
            BufferedReader buffRead = new BufferedReader(fileRead);
            String returnWord = buffRead.readLine();
            buffRead.close();
            fileRead.close();
            tempFile.delete();
            assertEquals(returnWord, color, "Method doSomethingElse printed wrong color");
        } catch (FileNotFoundException e){
            e.printStackTrace();
            fail("Error creating temporary file!");
        } catch (IOException e){
            e.printStackTrace();
            fail("Error in working with streams");
        }
    }
}