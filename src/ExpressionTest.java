import static org.junit.Assert.*;

import org.junit.Test;


public class ExpressionTest {

	@Test
	public void test() {
		
		/*
		 * This cluster of tests check to make sure that the expressions 
		 * are being parsed in the expected way, from the top level down,
		 * taking into account the various operations possible.
		 *
		 * */
		
		//Check basic operations parsing correctly for each operation
		
		Expression n1 = new Expression("(a=>b)");
		assertEquals(n1.toString(),"=>ab");
		
		Expression n2 = new Expression("((a=>b)&(a=>b))");
		assertEquals(n2.toString(),"&=>ab=>ab");
	
		Expression n3 = new Expression("((a=>b)|(a=>b))");
		assertEquals(n3.toString(),"|=>ab=>ab");
		
		//System.out.print("n is equal to: " + n1);
		//System.out.print("n2 is equal to: " + n2);
		//System.out.print("n3 is equal to: " + n3);

		//Test nested parsing happening correctly for each operation
		
		Expression n4 = new Expression("(((a=>b)=>(b=>c))=>(b=>a))");
		assertEquals(n4.toString(),"=>=>=>ab=>bc=>ba");
		
		Expression n5 = new Expression("(((~a=>b)=>(b=>c))&(~b=>~a))");
		assertEquals(n5.toString(),"&=>=>~ab=>bc=>~b~a");
		
		Expression n6 = new Expression("(((~a=>b)=>(b=>c))|(~b=>~a))");
		assertEquals(n6.toString(),"|=>=>~ab=>bc=>~b~a");
		
	}

}
