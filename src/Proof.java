import java.util.*;

public class Proof {

	// need variable to hold a theoremSet
	// holds line number object showing current position
	// setAssumption

	//PROBLEM: ~q not stored correctly
	
	
	/*
		General To-Do
			-Write a inference checker for:
				//We need a hashtable for this expression in the global scope
				-User defined expression (takes in two clones: predefined expression, input expression)
					-Recursive Function that deconstructs both the pre-defined queue and the
					input-queue and when an operand is found, the input-function operand is assigned
					to the pre-defined fuction in a hashtable which is used to check for consistancy
					with every subsequent comparison.
					
					pop a value off both and store them
					
					if both stored values are operators:
						if the operators are equivilent
							call recursively the function on both sides and return only if both'
							are true
						else return the false
						
					if the stored values are operands:
						if the values are not in the hashtable
							add to the hashtable using predefined expression value as the key
							and the input expression as the vlaue
						if the operand comparison is inconsistant with the hashtable 
							return false	
					return true
		
				//Clear the table

				-Contradiction
				-
			-
			-
			-
			-
			-
			-
			-
	*/

	private TheoremSet myTheoremSet;
	private LineNumber myLineNumber;
	private Hashtable<String,Expression> showTable;

	public Proof (TheoremSet theorems) {
		myTheoremSet=theorems;
		myLineNumber=new LineNumber();
		showTable=null;
		//takes in expression stuff from Theorems
	}

	public LineNumber nextLineNumber () {
		//makes a new call on LineNumber based on arg x
		return myLineNumber;
	}

	public void changeLineNumber(String x){

	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException{

		//checks x for Line Errors, then throws if error
		//calls expression on x, gets a tree object Expression y
		//checks tree object expression y for inference errors and throws again
		//steps line number forward one  by calling appropriate method in LineNumber  
		//based on what expression y was called
		//adds tree object to proofs theorem set with name lineNumber and expression y

		//check x for Line Errors
		String[] statement;
		try{
			statement = StringSplitter(x);
			LineChecker(statement);
		}catch (IllegalLineException e){
			throw e;
		}

		//split string into argument and expression

		//make Expression

		reasonDelagation(statement);

		//check expression for inference error
		//try {
		//	InferenceChecker(userExpression.Queue);
		//}catch (IllegalInferenceException e){
		//	throw e;
		//}

		//step LineNumber based on argument
		//changeLineNumber(statement[0]);
		//myTheoremSet.put(myLineNumber.toString(),userExpression);
	}

	public String toString ( ) {
		//not sure where this is used?
		return "";
	}

	public boolean isComplete ( ) {
		//stops looping in Proofchecker by returning true
		//returns true when expression in line = expression inside
		System.out.println(myTheoremSet.myTheorems.toString());
		return false;
		//return ((myTheoremSet.get("1").equals(myTheoremSet.get(myLineNumber.toString()))&&(myLineNumber.toString()!="1")));
	}

	//change name
	public boolean mtChecker(ArrayList<LinkedList<String>> Queues)
	{
		//Iterate Over the Tree Expressions from Molles Tollens
		for(LinkedList curQueue : Queues)
		{
			//System.out.println(curQueue.toString());

			//curExpr is a expression to be evaluated
			if(curQueue.size() > 2)
			{
				//Gets Next Symbol
				String curSymbol = (String) curQueue.pop();

				//Checks Implies
				if(curSymbol.equals("=>"))
				{
					boolean bol1 = mtHelperChecker(curQueue,Queues);
					boolean bol2 = mtHelperChecker(curQueue,Queues);
					return implies(bol1,bol2);
				}
				if(curSymbol.equals("~"))
				{
					
				}
				 
				
			}
		}
		return false;
	}

	public boolean mtHelperChecker(LinkedList<String> curQueue,ArrayList<LinkedList<String>> Queues)
	{
		String curSymbol = (String) curQueue.pop();

		if(curSymbol.equals("=>"))
		{
			boolean bol1 = mtHelperChecker(curQueue,Queues);
			boolean bol2 = mtHelperChecker(curQueue,Queues);
			return implies(bol1,bol2);
		}

		for(LinkedList<String> Q : Queues)
		{
			if(Q.size() == 1)
			{
				if(curSymbol.equals(Q.getFirst()))
				{
					return true;
				}
			}
		}

		return false;
	}

	public static String[] StringSplitter(String x) throws IllegalLineException{
		String[] rtn=x.split(" ");
		if ((rtn.length<1)||rtn.length>4){
			throw new IllegalLineException("***Bad Input Format");
		}
		return rtn;
	}

	public void reasonDelagation(String[] args) throws IllegalInferenceException
	{
		String command = args[0];
		//System.out.println(args[0]);
		//System.out.println(args[1]);

		/*
		Todo:
			-
		
			-delegation for user defined theorems.
				-Find what matches between whats in the proof and whats in the user defined theorem
				-
				-	
				-
		
		
		*/
		if (command.equals("theorem"))
		{
			//add the argument to the theoremset with the key of the theorem name
			//what if theorem is first, will that ruin show code?
			this.myTheoremSet.put(args[1], new Expression(args[2]));
		}
		if (command.equals("show"))
		{
			Expression temp = new Expression(args[1]);
			if (!temp.Queue.peek().equals("=>")){
				throw new IllegalInferenceException("Expression must include =>: "+ args[1]);
			}
			this.showTable.put(myLineNumber.toString(), temp);
			
			/*
			To do: 
				-convert show to queue (with expression, which takes string) (DONE)
				-Show must check that the value at the top of the queue is an implication
				-store the converted show queue in a hashtable of shows (line number, queue)
			*/

			myLineNumber.layerMinus();
		}
		if (command.equals("assume"))
		{
			/*
			To do: 
				-if current line number = 2 then continue
				-else check that the last element in the line number array = 1
				  Deprecated: (current line number, without last 2 chars = hashtable (key) points to the show expression currently being proved)
				-else throw hands up theyre playing my song
				-add to theoremset (done)
			*/
			myTheoremSet.put(myLineNumber.toString(), new Expression(args[1]));
			myLineNumber.step();
		}
		if (command.equals("mp"))
		{
			/*
			To do:
				-
			
			
			
			
			*/


			//mpChecker(myTheoremSet.get(args[1]),myTheoremSet.get(args[2]),args[3]);
			myLineNumber.step();
		}
		if (command.equals("mt"))
		{
			Expression newThm = new Expression(args[3]);
			ArrayList<LinkedList<String>> passArrayList = new ArrayList<LinkedList<String>>();
			passArrayList.add((LinkedList<String>) myTheoremSet.get(args[1]).clone());
			passArrayList.add((LinkedList<String>) myTheoremSet.get(args[2]).clone());
			passArrayList.add((LinkedList<String>) newThm.Queue.clone());
			if(mtChecker(passArrayList))
			{
				myTheoremSet.put(myLineNumber.toString(), newThm);
				myLineNumber.step();
			}
			else
			{
				System.out.println("Invalid Inference");
			}
		}
		if (command.equals("co"))
		{
			//coChecker(myTheoremSet.get(args[1]),myTheoremSet.get(args[2]),args[3]);
			myLineNumber.layerUp();
		}
		if (command.equals("ic"))
		{
			myLineNumber.layerUp();
		}
		if (command.equals("repeat"))
		{

		}


	}

	public static boolean LineChecker(String[] statement) throws IllegalLineException {
		//check for correct space placement
		//checks for Line errors, returns true if isOK, returns false if error
		int numArgs;
		String command = statement[0];
		
		try{
			
			if (command.equals("print"))
			{
				numArgs = 1;
			}
			else if(command.equals("show")||
					command.equals("assume"))
				
					{numArgs = 2;
							
			}
			else if(command.equals("repeat")
					||command.equals("ic")
				    ||command.equals("theorem"))
				
					{numArgs = 3;
			}
			else if(command.equals("mp")||
					command.equals("mt")||
					command.equals("co"))
				
					{numArgs = 4;
			}
			else{
				throw new IllegalLineException("***Invalid Reason:" + command);
			}
			
			if (statement.length!=numArgs){
				throw new IllegalLineException("Invalid Number of Args For:"+ statement [0]);
			}
			
			if (numArgs != 1)
			{
				ExpressionChecker(statement[numArgs-1]);
				
				if (numArgs==3)
				{
					LineNumberChecker(statement[1]);
				}
				if (numArgs==4)
				{
					LineNumberChecker(statement[1]);
					LineNumberChecker(statement[2]);
				}
			}
			
		}
		catch (IllegalLineException e)
		{
			throw e;
		}

		return true;
	}

	
	public static void ExpressionChecker(String x) throws IllegalLineException{
		//checks Expression for valid Parentheses, typos
		//checks for nesting, operators within nesting, and syntax
		int a=0;
		int b=0;
		char test=0;
		int canHold=0;
		int needRight=0;
		boolean[][] dictionary=new boolean[][]{{false,false,true,false,true,false,false},
							{true,true,false,true,false,false,true},
							{false,false,true,false,true,false},
							{true,true,false,true,false,false,true},
							{true,true,false,true,true,false,true},
							{false,false,true,false,true,false,false},
							{false,false,false,false,false,true,false}};

		for(int i=0;i<x.length();i++){
			test=x.charAt(i);
			a=indexer(test);
			if (a==100){
				throw new IllegalLineException("***Invalid Expression: "+x);
			}
			if (!dictionary[a][b]){
				throw new IllegalLineException("***Invalid Expression: "+x);
			}
			if (test==')'){
				needRight--;
			}else if(test=='('){
				needRight++;
				canHold++;
			}else if(test=='='){
				canHold--;
			}else if(test=='|'||test=='&'){
				canHold--;
			}else if(test=='~'){
			}else if(!Character.isLetter(test)&&test!='>'){
				throw new IllegalLineException("***Invalid Expression: "+x);
			}
			if (canHold<0||needRight<0){
				throw new IllegalLineException("***Invalid Expression: "+x);
			}
			b=a;
		}
		if (canHold!=0||needRight!=0){
			throw new IllegalLineException("***Invalid Expression: "+x);
		}
	}
	public static int indexer(char x)throws IllegalLineException{
		//takes in a char and returns int to be used for indexing with Expression Checker's dictionary
		//throws IllegalLineException when the char is not of expected type
		//the final return of 100 is never reached.
		switch(x){
			case '|':case'&': return 0;
			case '(': return 1;
			case ')': return 2;
			case '~': return 3;
			case '=': return 5;
			case '>': return 6;
			default: if(!Character.isLetter(x)){
						throw new IllegalLineException("***Invalid Expression: "+x);
					}else{
						return 4;
					}
		}
	}


	public static void LineNumberChecker(String x)throws IllegalLineException{
		//checks that x is just ints and .
		if ((Character.toString(x.charAt(0)).equals('.'))&&(Character.toString(x.charAt(x.length()-1)).equals('.')))
		{
			throw new IllegalLineException("***Invalid Line Number"+x);
		}
		String[] test = x.split(".");
		for (int i=0;i<test.length;i++){
			try{
				Integer.parseInt(test[i]);
			}catch (NumberFormatException e){
				throw new IllegalLineException("***Invalid Line Number:" + x);
			} 
		}
	}



	public boolean implies(boolean op1, boolean op2)
	{
		//truth tabel processing

		if (op1==true && op2==true)
		{
			return true;
		}
		if (op1==true && op2==false)
		{
			return false;
		}
		return true;

	}

}
