import java.io.IOException;

/**
 * Created by molotov on 11/19/14.
 */
public class ExpressionNode implements INode {

    private TermNode term;
    //optional ('+' or '-'), expressionNode
    private Lexeme operator;
    private ExpressionNode expressionNode;


    public ExpressionNode(Tokenizer tokenizer) throws IOException, TokenizerException, ParserException {
        term = new TermNode(tokenizer);

        if(tokenizer.current().token() == Token.ADD_OP
            || tokenizer.current().token() == Token.SUB_OP) {
            operator = tokenizer.current();
            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        Parser.addTabs(builder, tabs);
        builder.append("ExpressionNode\n");
        Parser.addTabs(builder, tabs);
        term.buildString(builder, tabs);

        if(operator != null) {
            Parser.addTabs(builder, tabs);
            builder.append(operator);
            builder.append("\n");
            Parser.addTabs(builder, tabs);
            expressionNode.buildString(builder, tabs);
        }
    }

    @Override
    public String toString() {
        return term.toString() +
                (operator != null ? " " + operator : "") +
                (expressionNode != null ? " " + expressionNode : "");
    }
}
