import org.junit.Before;
import org.junit.Test;

/**
 * Created by molotov on 11/19/14.
 */
public class ParserTest {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
        parser.open("program1.txt");
    }

    @Test
    public void testOpen() throws Exception {

    }

    @Test
    public void testParse() throws Exception {
        INode node = parser.parse();
        StringBuilder builder = new StringBuilder();
        node.buildString(builder, 0);
        System.out.println(builder.toString());
    }

    @Test
    public void testClose() throws Exception {

    }
}
