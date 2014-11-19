import java.io.IOException;

/**
 * Created by molotov on 11/19/14.
 */
public class AssignmentNode implements INode {

    private Lexeme identifier,
                   assignmentSign;
    public ExpressionNode expression;
    private Lexeme semicolon;

    public AssignmentNode(Tokenizer tokenizer) throws ParserException, IOException, TokenizerException {
        if(tokenizer.current().token() == Token.IDENT) {
            identifier = tokenizer.current();
        } else {
            throw new ParserException("Lexeme was not of type IDENT");
        }

        tokenizer.moveNext();

        if(tokenizer.current().token() == Token.ASSIGN_OP) {
            assignmentSign = tokenizer.current();
            tokenizer.moveNext();
        } else {
            throw new ParserException("Lexeme was not of type ASSIGN_OP");
        }

        expression = new ExpressionNode(tokenizer);

        if(tokenizer.current().token() == Token.SEMICOLON) {
            semicolon = tokenizer.current();
        } else {
            throw new ParserException("Lexeme was not of type SEMICOLON");
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return identifier.value() + " "
                + assignmentSign.value() + " " +
                expression.evaluate(args)
                + " " + semicolon.value();
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("ASSIGNMENT_NODE\n");
        builder.append("\t");
        builder.append(identifier);
        builder.append("\n\t");
        builder.append(assignmentSign);
        builder.append("\n");
        expression.buildString(builder, tabs + 1);
        builder.append("\n\t");
        builder.append(semicolon);
    }

    @Override
    public String toString() {
        return identifier + " " + assignmentSign + " " + expression + " " + semicolon;
    }
}
