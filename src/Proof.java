import java.util.*;

public class Proof {

	// need variable to hold a theoremSet
	// holds line number object showing current position
	// setAssumption

	private TheoremSet myTheoremSet;
	private LineNumber myLineNumber;

	public Proof (TheoremSet theorems) {
		myTheoremSet=theorems;
		myLineNumber=new LineNumber();
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

		try{
			LineChecker(x);
		}catch (IllegalLineException e){
			throw e;
		}

		//split string into argument and expression
		String[] statement = StringSplitter(x);
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

	public boolean LineChecker(String x) throws IllegalLineException {

		//checks for Line errors, returns true if isOK, returns false if error
		return true;
	}

	public boolean InferenceChecker(LinkedList<String> inferenceQueue) throws  IllegalInferenceException
	{
		return true;
	}
	
	public static String[] StringSplitter(String x) throws IllegalLineException{
		String[] rtn=x.split(" ");
		if ((rtn.length<1)||rtn.length>4){
			throw new IllegalLineException("***Bad Input Format");
		}
		return rtn;
	}
	
	public void reasonDelagation(String[] args)
	{
		String command = args[0];
		
		if (command.equals("theorem"))
		{
			
		}
		if (command.equals("show"))
		{
			myLineNumber.layerDown();
		}
		if (command.equals("assume"))
		{
			myTheoremSet.put(myLineNumber.current(), args[1]);
			myLineNumber.step();
		}
		if (command.equals("mp"))
		{
			mpChecker(myTheoremSet.get(args[1]),myTheoremSet.get(args[2]),args[3]);
			myLineNumber.step();
		}
		if (command.equals("mt"))
		{
			mtChecker(myTheoremSet.get(args[1]),myTheoremSet.get(args[2]),args[3]);
			myLineNumber.step();
		}
		if (command.equals("co"))
		{
			coChecker(myTheoremSet.get(args[1]),myTheoremSet.get(args[2]),args[3]);
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
		int mySize;
		
		try{
			mySize=reasonReader(statement[0]);
			if (statement.length!=mySize){
				throw new IllegalLineException("Invalid Number of Args For:"+ statement [0]);
			}
			if (mySize != 1){
				ExpressionChecker(statement[mySize-1]);
		
				if (mySize==3){
					LineNumberChecker(statement[1]);
				}
				if (mySize==4){
					LineNumberChecker(statement[1]);
					LineNumberChecker(statement[2]);
				}
			}
		}catch (IllegalLineException e){
			throw e;
		}
		
		return true;
	}

	public static int reasonReader(String x) throws IllegalLineException{
		//returns int based on what String x is
		// type 1 : "print" command
		// type 2 : "show","assume","theorem" commands
		// type 3 : "ic","repeat", commands
		// type 4 : "mp","mt","co" commands
		if (x.equals("print")){
			return 1;
		}else if(x.equals("show")||x.equals("assume")||x.equals("theorem")){
			return 2;
		}else if(x.equals("repeat")||x.equals("ic")){
			return 3;
		}else if(x.equals("mp")||x.equals("mt")||x.equals("co")){
			return 4;
		}else{
			throw new IllegalLineException("***Invalid Reason:" + x);
		}
	}

	public static void ExpressionChecker(String x) /*throws IllegalLineException*/{
		//checks Expression for valid Parentheses, typos
		int canHold=0;
		int needRight=0;
		for(int i=0;i<x.length();i++){
			char test=x.charAt(i);
			if (test==')'){
				needRight--;
				canHold--;
				if ((!Character.isLetter(x.charAt(i-1)))||(Character.isLetter(x.charAt(i+1)))){
					System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
				}
			}else if(test=='('){
				needRight++;
				canHold++;
				if ((Character.isLetter(x.charAt(i-1)))||((!Character.isLetter(x.charAt(i+1))))||x.charAt(i+1)!='~'){
					System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
				}
			}else if(test=='='){
				canHold--;
				if (x.charAt(i+1)!='>'){
					System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
				} else{
					i++;
				}
			}else if(test=='|'||test=='&'){
				canHold--;
			}else if(test=='~'){
				if ((!Character.isLetter(x.charAt(i+1)))&&(x.charAt(i+1)!='(')){
					System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
				} else if(Character.isLetter(x.charAt(i+1))){
					System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
				}
			}else if(!Character.isLetter(test)||test=='>'){
				System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
			}
			if (canHold<0||needRight<0){
				System.out.println("FUCKING SHIT MAN"+x);//throw new IllegalLineException("***Invalid Expression:"+x);
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
