package ru._7bits.formatter;

import junit.framework.TestCase;
import ru._7bits.lexer.Lexer;
import ru._7bits.reader.FReader;
import ru._7bits.reader.StringReader;
import ru._7bits.writer.FWriter;
import ru._7bits.writer.StringWriter;

import java.io.File;

/**
 * Created by User on 12.05.2016.
 */
public class FormatterTest extends TestCase {

    public void testOne() throws Exception {
        String input = "a=0;b=1;";
        String etalon = "a=0;\nb=1;\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testTwo() throws Exception {
        String input = "{{{int a = 0;a++;}}}";
        String etalon =   "{\n"
                        + "    {\n"
                        + "        {\n"
                        + "            int a = 0;\n"
                        + "            a++;\n"
                        + "        }\n"
                        + "    }\n"
                        + "}\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }
    public void testThree() throws Exception {
        String input = "a=0;b=1;{c=2;d=3;{e=4;}f=a+b;}g=f-c+d;";
        String etalon =   "a=0;\n"
                        + "b=1;\n"
                        + "{\n"
                        + "    c=2;\n"
                        + "    d=3;\n"
                        + "    {\n"
                        + "        e=4;\n"
                        + "    }\n"
                        + "    f=a+b;\n"
                        + "}\n"
                        + "g=f-c+d;\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFour() throws Exception {
        String input = "while(inputStream.hasNext()){char symbol=inputStream.read();}";
        String etalon =       "while(inputStream.hasNext()){\n"
                            + "    char symbol=inputStream.read();\n"
                            + "}\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFive() throws Exception {
        String input =  "while(true){\n" +
                        "if    (i>0) {obj.doSomething(q); \t i++;}\n" +
                        "      else j=j*2; z=0;}";

        String etalon =       "while(true){\n"
                            + "    if (i>0){\n"
                            + "        obj.doSomething(q);\n"
                            + "        i++;\n"
                            + "    }\n"
                            + "    else j=j*2;\n"
                            + "    z=0;\n"
                            + "}\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testSix() throws Exception {
        String input =  "int a   =   0   ;\n" +
                        "      { \n\n\n" +
                        "     a++;   \n\n" +
                        "   \t\t\n " +
                        "  if (a<0)\n" +
                        "{\n" +
                        "            a++; \t \n" +
                        "  }\n" +
                        "  } \n\t\n ";

        String etalon = "int a = 0;\n" +
                        "{\n" +
                        "    a++;\n" +
                        "    if (a<0){\n" +
                        "        a++;\n" +
                        "    }\n" +
                        "}\n";

        Formatter fmt = new Formatter();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFileOne() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test1_data.txt";

        String etalon = "a=0;\nb=1;\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFileTwo() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test2_data.txt";

        String etalon =   "{\n"
                + "    {\n"
                + "        {\n"
                + "            int a = 0;\n"
                + "            a++;\n"
                + "        }\n"
                + "    }\n"
                + "}\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }
    public void testFileThree() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test3_data.txt";

        String etalon =   "a=0;\n"
                + "b=1;\n"
                + "{\n"
                + "    c=2;\n"
                + "    d=3;\n"
                + "    {\n"
                + "        e=4;\n"
                + "    }\n"
                + "    f=a+b;\n"
                + "}\n"
                + "g=f-c+d;\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFileFour() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test4_data.txt";

        String etalon =       "while(inputStream.hasNext()){\n"
                + "    char symbol=inputStream.read();\n"
                + "}\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFileFive() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test5_data.txt";


        String etalon =       "while(true){\n"
                + "    if (i>0){\n"
                + "        obj.doSomething(q);\n"
                + "        i++;\n"
                + "    }\n"
                + "    else j=j*2;\n"
                + "    z=0;\n"
                + "}\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFileSix() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test6_data.txt";

        String etalon = "int a = 0;\n" +
                "{\n" +
                "    a++;\n" +
                "    if (a<0){\n" +
                "        a++;\n" +
                "    }\n" +
                "}\n";

        Formatter fmt = new Formatter();
        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);

        fmt.format(lexer, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

}