import java.io.IOException;

/**
 * Created by molotov on 11/19/14.
 */
public class FactorNode implements INode {

    private Lexeme integer;
    //or '(', expressionNode, ')'
    private Lexeme leftParen;
    private ExpressionNode expressionNode;
    private Lexeme rightParen;

    public FactorNode(Tokenizer tokenizer) throws ParserException, IOException, TokenizerException {
        if(tokenizer.current().token() == Token.INT_LIT) {
            integer = tokenizer.current();
            tokenizer.moveNext();
        } else if(tokenizer.current().token() == Token.LEFT_PAREN) {
            leftParen = tokenizer.current();
            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
            if(tokenizer.current().token() == Token.RIGHT_PAREN) {
                rightParen = tokenizer.current();
                tokenizer.moveNext();
            } else {
                throw new ParserException("Factornode: not right paren: " + tokenizer.current().toString());
            }
        } else {
            throw new ParserException("Factornode: wrong token: " + tokenizer.current().toString());
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        if(integer != null) {
            return integer.value();
        } else {
            return expressionNode.evaluate(args);
        }
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        Parser.addTabs(builder, tabs);
        builder.append("FACTOR_NODE\n");
        Parser.addTabs(builder, tabs + 1);

        if(integer != null) {
            builder.append(integer);
        } else {
            builder.append(leftParen);
            builder.append("\n");
            expressionNode.buildString(builder, tabs + 1);
            Parser.addTabs(builder, tabs + 1);
            builder.append(rightParen);
        }
        builder.append("\n");
    }

    @Override
    public String toString() {
        return integer != null ? integer.toString() : leftParen + " " + expressionNode + " " + rightParen;
    }
}
