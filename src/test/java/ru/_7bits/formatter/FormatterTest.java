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

    public void test1() throws Exception {
        String input = "a=0;b=1;";
        String etalon = "a=0;\nb=1;\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void test2() throws Exception {
        String input = "{{{int a = 0;a++;}}}";
        String etalon =   "{\n"
                        + "    {\n"
                        + "        {\n"
                        + "            int a = 0;\n"
                        + "            a++;\n"
                        + "        }\n"
                        + "    }\n"
                        + "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }
    public void test3() throws Exception {
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

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void test4() throws Exception {
        String input = "while(inputStream.hasNext()){char symbol=inputStream.read();}";
        String etalon =       "while(inputStream.hasNext()){\n"
                            + "    char symbol=inputStream.read();\n"
                            + "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void test5() throws Exception {
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

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test6() throws Exception {
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

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test7() throws Exception {
        String input =  "aaa// bbb;{ ccc; }";
        String etalon = "aaa // bbb;{ ccc; }\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test8() throws Exception {
        String input =  "aaa;// bbb;{ ccc; }";
        String etalon = "aaa;\n// bbb;{ ccc; }\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test9() throws Exception {
        String input =  "{ aaa; // bbb; ccc; } \n ddd; }";
        String etalon = "{\n" +
                        "    aaa;\n" +
                        "    // bbb; ccc; } \n" +
                        "    ddd;\n" +
                        "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test10() throws Exception {
        String input =  "aaa /* /bbb;* **/ ccc";
        String etalon = "aaa /* /bbb;* **/ ccc";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test11() throws Exception {
        String input =  "{ aaa; /* /bbb;* **/ ccc;  }";
        String etalon = "{\n" +
                        "    aaa;\n" +
                        "    /* /bbb;* **/ ccc;\n" +
                        "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test12() throws Exception {
        String input =  "{ aaa; /*** / *  \n" +
                        "\n" +
                        "{ bbb; }\n" +
                        "*/ ccc;  }";
        String etalon = "{\n" +
                        "    aaa;\n" +
                        "    /*** / *  \n" +
                        "    \n" +
                        "    { bbb; }\n" +
                        "    */ ccc;\n" +
                        "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test13() throws Exception {
        String input =  "{ aaa; /* aaa \n" +
                        "\n" +
                        "{ bbb; // ccc }\n" +
                        "*/ ddd;  }";
        String etalon = "{\n" +
                "    aaa;\n" +
                "    /* aaa \n" +
                "    \n" +
                "    { bbb; // ccc }\n" +
                "    */ ddd;\n" +
                "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test14() throws Exception {
        String input = "c = ';'; d=\" aa { b;c// } \\\" \"";
        String etalon = "c =';';\nd=\" aa { b;c// } \\\" \"";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void test15() throws Exception {
        String input = "{c = /* { aaa = \" { bbb; } \"; }*/   '{';}";
        String etalon = "{\n" +
                        "    c = /* { aaa = \" { bbb; } \"; }*/'{';\n" +
                        "}\n";

        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFile1() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test1_data.txt";

        String etalon = "a=0;\nb=1;\n";

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFile2() throws Exception {
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

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }
    public void testFile3() throws Exception {
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

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFile4() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test4_data.txt";

        String etalon =       "while(inputStream.hasNext()){\n"
                + "    char symbol=inputStream.read();\n"
                + "}\n";

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));

    }

    public void testFile5() throws Exception {
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

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }

    public void testFile6() throws Exception {
        File f = new File(".");
        String fileName = f.getAbsolutePath() + "\\src\\test6_data.txt";

        String etalon = "int a = 0;\n" +
                "{\n" +
                "    a++;\n" +
                "    if (a<0){\n" +
                "        a++;\n" +
                "    }\n" +
                "}\n";

        FReader reader = new FReader(fileName);
        StringWriter writer = new StringWriter();
        Lexer lexer = new Lexer(reader);
        Formatter fmt = new Formatter(lexer);

        fmt.format(reader, writer);
        String result = writer.toString();

        assertTrue(etalon.equals(result));
    }


}