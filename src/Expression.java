import java.util.LinkedList;

/*Expression class is essentially a parser and data structure
 *
 * Black box description:
 *
 * Input = String, lowercased, no whitespace
 * Output = Linked List of Strings which represents the order of operations if put into a Queue
 * sent into Theorem set to be accessed by the Proof class.
 *
 *
 */
public class Expression {

	//Root of Tree (First Operator Usually)
	public LinkedList<String> Queue;
	// Class Constructor: Takes expression and passes to exprTreeHelper
	public Expression (String s) {
		Queue = new LinkedList<String>();
		exprTreeHelper(s);
	}
	public String toString ()
	{
		String myString =
				"";

		for(int i = 0; i < Queue.size(); i++)
		{
			myString +=
					Queue.get(i);
		}

		return myString;
	}
	//Parser of Expression into Tree
	private void exprTreeHelper (String expr) {
		if(ProofChecker.iAmDebugging){
		System.out.print(expr);
		}
		if (expr.charAt (0) != '(' && expr.charAt (0) != '~') {
			Queue.add(Character.toString(expr.charAt(0)));
		}
		else if (expr.charAt (0) == '~')
		{
			if(expr.charAt (1) == '(' || expr.charAt (1) == '~')
			{
				Queue.add(Character.toString(expr.charAt(0)));
				exprTreeHelper(expr.substring(1));
			}
			else
			{
				Queue.add(Character.toString(expr.charAt(0)));
				Queue.add(Character.toString(expr.charAt(1)));
			}
		}
		else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operators nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			int opPosLen = 0;
			//Iterates over expression
			for (int k=0; k<expr.length(); k++) {
				if(Character.toString(expr.charAt(k)).equals("("))
				{
					nesting++;
				}
				if(Character.toString(expr.charAt(k)).equals(")"))
				{
					nesting--;
				}
				if(nesting==1)
				{
					if(Character.toString(expr.charAt(k)).equals("=") && Character.toString(expr.charAt(k+1)).equals(">"))
					{
						opPos = k;
						opPosLen++;
						k++;
					}
					if(Character.toString(expr.charAt(k)).equals("|"))
					{
						opPos = k;
					}
					if(Character.toString(expr.charAt(k)).equals("&"))
					{
						opPos = k;
					}
				}
			}
			//Finds Operations and Left and Right expressions
			String opnd1 = expr.substring (1, opPos);
			String opnd2 = expr.substring (opPos+opPosLen+1, expr.length()-1);
			String op = expr.substring (opPos, opPos+opPosLen+1);
			
			//Enqueues the Operation and left and right expressions.
			Queue.add(op);
			
			exprTreeHelper(opnd1);
			exprTreeHelper(opnd2);
		}
	}
}

