import java.io.IOException;

/**
 * Created by molotov on 11/19/14.
 */
public class TermNode implements INode {

    private FactorNode factor;
    //optional ('*' or '/'), TermNode
    private Lexeme operator;
    private TermNode termNode;

    public TermNode(Tokenizer tokenizer) throws TokenizerException, ParserException, IOException {
       factor = new FactorNode(tokenizer);

        if(tokenizer.current().token() == Token.MULT_OP
           || tokenizer.current().token() == Token.DIV_OP) {
            operator = tokenizer.current();
            tokenizer.moveNext();
            termNode = new TermNode(tokenizer);
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        Double factorValue = (Double) factor.evaluate(args);
        if(operator != null) {
            switch((Character) operator.value()) {
                case '*': factorValue *= (Double) termNode.evaluate(args);
                    break;
                case '/': factorValue /= (Double) termNode.evaluate(args);
                    break;
            }
        }
        return factorValue;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        Parser.addTabs(builder, tabs);
        builder.append("TERM_NODE\n");
        factor.buildString(builder, tabs + 1);

        if(operator != null) {
            Parser.addTabs(builder, tabs + 1);
            builder.append(operator);
            builder.append("\n");
            termNode.buildString(builder, tabs + 1);
        }
    }

    @Override
    public String toString() {
        return factor.toString() +
                (operator != null ? operator : "") +
                (termNode != null ? termNode : "");
    }
}
