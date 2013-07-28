import static org.junit.Assert.*;

import org.junit.Test;


public class ExpressionTest {

  @Test
	public void test() {
		
		/*
		 * This cluster of tests check to make sure that the expressions 
		 * are being parsed in the expected way, from the top level down.
		 *
		 * */
		
		Expression n = new Expression("(a=>b)");
		assertEquals(n.toString(),"=>ab");
		
		Expression n2 = new Expression("((a=>b)&(a=>b))");
		//assertEquals(n2,"&=>ab=>ab");
		
		System.out.print("n2 is equal to: " + n2.Queue);
		
		Expression n3 = new Expression("((a=>b)&(a=>b))");
		//assertEquals(n3.toString(),"=>ab");
		
		System.out.print("n3 is equal to: " + n3.Queue);
		
		Expression n4 = new Expression("(a=>b)");
		assertEquals(n4.toString(),"=>ab");
		
		Expression n5 = new Expression("(a=>b)");
		assertEquals(n5.toString(),"=>ab");
		
		Expression n6 = new Expression("(a=>b)");
		assertEquals(n6.toString(),"=>ab");
		
	}

}
