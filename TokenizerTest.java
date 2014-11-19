import static org.junit.Assert.assertEquals;

/**
 * Created by molotov on 11/17/14.
 */
public class TokenizerTest {

    Tokenizer tokenizer;

    @org.junit.Before
    public void setUp() throws Exception {
        tokenizer = new Tokenizer();
        tokenizer.open("program1.txt");
    }

    @org.junit.Test
    public void testOpen() throws Exception {

    }

    @org.junit.Test
    public void testCurrent() throws Exception {
        Lexeme lexeme = tokenizer.current();
        assertEquals(null, lexeme);

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("a", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("=", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("1", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("*", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("2", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("+", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("(", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("3", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("-", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("4", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals(")", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("/", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals("5", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals(";", lexeme.value());

        tokenizer.moveNext();
        lexeme = tokenizer.current();
        assertEquals(Token.EOF, lexeme.token());
    }

    @org.junit.Test
    public void testMoveNext() throws Exception {

    }

    @org.junit.Test
    public void testClose() throws Exception {

    }
}
