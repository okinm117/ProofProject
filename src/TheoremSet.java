import java.util.Hashtable;

public class TheoremSet{
	
	public Hashtable<String,String> myTheorems;
	
	public TheoremSet ( ) {
		myTheorems = new Hashtable<String,String>();
	}

	public void put (String s, Expression e) {
		myTheorems.put(s,e.toString());
	}
	
	public void put (String s, String e) {
		myTheorems.put(s,e);
	}
	
	public String get (String s)
	{
		return myTheorems.get(s);
	}
	
	public boolean contains (String s)
	{
		return myTheorems.containsValue(s);
	}
}