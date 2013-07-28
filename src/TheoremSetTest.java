import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;


public class TheoremSetTest {

  @Test
	public void testTheoremSet() {
		//test Constructor
		TheoremSet test=new TheoremSet();
		//test put (Expression)
		test.put("Expression", new Expression("(a=>b)"));
		//test put (Queue)
		LinkedList<String> Queue=new LinkedList<String>();
		Queue.add("=");
		Queue.add(">");
		Queue.add("a");
		Queue.add("b");
		test.put("Queue",Queue);
		//test get(s)
		assertEquals(test.get("Expression").toString(),"[=>, a, b]");
		assertEquals(test.get("Queue").toString(),"[=, >, a, b]");
	}

}
