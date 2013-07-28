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
		
		Expression n = new Expression("a");
		assertEquals(n.toString(),"a");
		
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
		
		//Check basic operations parsing correctly with ~
		
		Expression n7 = new Expression("(~~a=>b)");
		assertEquals(n7.toString(),"=>~~ab");
		Expression n71 = new Expression("~(a=>b)");
		assertEquals(n71.toString(),"~=>ab");
		Expression n72 = new Expression("(a=>~b)");
		assertEquals(n72.toString(),"=>a~b");
		
		Expression n8 = new Expression("(~(a=>b)&(a=>b))");
		assertEquals(n8.toString(),"&~=>ab=>ab");
		Expression n81 = new Expression("((a=>b)&~(a=>b))");
		assertEquals(n81.toString(),"&=>ab~=>ab");
		Expression n82 = new Expression("~((a=>b)&(a=>b))");
		assertEquals(n82.toString(),"~&=>ab=>ab");
		Expression n83 = new Expression("((a=>b)&(a=>~b))");
		assertEquals(n83.toString(),"&=>ab=>a~b");
		Expression n84 = new Expression("((~a=>b)&(a=>b))");
		assertEquals(n84.toString(),"&=>~ab=>ab");
	
		Expression n9 = new Expression("(~(a=>b)|(a=>b))");
		assertEquals(n9.toString(),"|~=>ab=>ab");
		Expression n91 = new Expression("((a=>b)|~(a=>b))");
		assertEquals(n91.toString(),"|=>ab~=>ab");
		Expression n92 = new Expression("~((a=>b)|(a=>b))");
		assertEquals(n92.toString(),"~|=>ab=>ab");
		Expression n93 = new Expression("((a=>b)|(a=>~b))");
		assertEquals(n93.toString(),"|=>ab=>a~b");
		Expression n94 = new Expression("((~a=>b)|(a=>b))");
		assertEquals(n94.toString(),"|=>~ab=>ab");
		
		//Testing nested Expression with ~ in most positions
		Expression d1 = new Expression("~(~(~(~a=>~b)=>~(~b=>~c))=>(~b=>~a))");
		assertEquals(d1.toString(),"~=>~=>~=>~a~b~=>~b~c=>~b~a");
		
		//Testing nested Expression with ~~ in most positions
		Expression d2 = new Expression("~~(~~(~~(~~a=>~~b)=>~~(~~b=>~~c))=>(~~b=>~~a))");
		assertEquals(d2.toString(),"~~=>~~=>~~=>~~a~~b~~=>~~b~~c=>~~b~~a");
		
		//Testing nested Expression with ~~~ in most positions
		Expression d3 = new Expression("~~~(~~~(~~~(~~~a=>~~~b)=>~~~(~~~b=>~~~c))=>(~~~b=>~~~a))");
		assertEquals(d3.toString(),"~~~=>~~~=>~~~=>~~~a~~~b~~~=>~~~b~~~c=>~~~b~~~a");
		//Testing nested Expression with ~~~ in most positions, &
		Expression d4 = new Expression("~~~(~~~(~~~(~~~a=>~~~b)=>~~~(~~~b=>~~~c))&(~~~b=>~~~a))");
		assertEquals(d4.toString(),"~~~&~~~=>~~~=>~~~a~~~b~~~=>~~~b~~~c=>~~~b~~~a");
		//Testing nested Expression with ~~~ in most positions, |
		Expression d5 = new Expression("~~~(~~~(~~~(~~~a=>~~~b)=>~~~(~~~b=>~~~c))&(~~~b=>~~~a))");
		assertEquals(d5.toString(),"~~~&~~~=>~~~=>~~~a~~~b~~~=>~~~b~~~c=>~~~b~~~a");
		
	}

}
