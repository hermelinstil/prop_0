import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by molotov on 11/11/14.
 */
public class Tokenizer implements ITokenizer {

    HashMap<Character, Token> lexicon;

    private Scanner scanner;
    private Lexeme current = null;

    private ArrayList<Lexeme> list = new ArrayList<Lexeme>();

    public Tokenizer() {
        scanner = new Scanner();
        lexicon = new HashMap<Character, Token>();

        fillLexicon();
    }

    private void fillLexicon() {
        lexicon.put('+', Token.ADD_OP);
        lexicon.put('-', Token.SUB_OP);
        lexicon.put('*', Token.MULT_OP);
        lexicon.put('/', Token.DIV_OP);
        lexicon.put('=', Token.ASSIGN_OP);
        lexicon.put('(', Token.LEFT_PAREN);
        lexicon.put(')', Token.RIGHT_PAREN);
        lexicon.put(';', Token.SEMICOLON);
        lexicon.put('{', Token.LEFT_CURLY);
        lexicon.put('}', Token.RIGHT_CURLY);
        lexicon.put(Scanner.EOF, Token.EOF);
    }

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        scanner.open(fileName);
        scanner.moveNext();
    }

    @Override
    public Lexeme current() {
        return current;
    }

    private void appendTo(StringBuilder builder) throws IOException {
        builder.append(scanner.current());
        scanner.moveNext();
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        //new builder for new lexeme
        StringBuilder builder = new StringBuilder();
        Object value;
        Token token;

        //skip blank space
        while (Character.isSpaceChar(scanner.current())) {
            scanner.moveNext();
        }

        if(Character.isAlphabetic(scanner.current())) {
            while(Character.isAlphabetic(scanner.current())) {
                appendTo(builder);
            }
            token = Token.IDENT;
            value = builder.toString().trim();
        } else if(Character.isDigit(scanner.current())) {
            while(Character.isDigit(scanner.current())) {
                appendTo(builder);
            }
            token = Token.INT_LIT;
            value = Double.parseDouble(builder.toString().trim());
        } else {
            token = lexicon.get(scanner.current());
            appendTo(builder);
            value = builder.toString().charAt(0);
        }

        //String word = builder.toString().trim();

        current = new Lexeme(value, token);
        //köra på lista istället?
        //list.add(current);
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
