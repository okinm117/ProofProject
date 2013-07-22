import java.util.*;

public class LineNumber {
	
	// NOTATION NOTES //
	/*
	* Layer: 3 --> 3 is the third layer in general within a proof/theorem
	* Sublayer: 3.5 --> 5 is the fifth layer in layer 3
	* Sub-sublayer: 3.5.1 --> 1 is the first sublayer within sublayer 5 of layer 3
	*/


	// CLASS VARIABLES//
	

	private String index;

	/*
	 * Integer used to create an arbitary number of array elements
	 * Used for arrays "currentPosition" and "holder" (toString())
	 */
	private int n;

	/*
	 * Integer used to create an arbitary number of array elements
	 * Used for arrays "currentPosition" and "holder" (toString())
	 */
	private ArrayList<Integer> currentPosition = new ArrayList<Integer>();

	
	// METHODS //

	/*
	* Constructor for creating a LineNumber object
	* Starts main layer at 1 (for first proof to be implemented)
	*/
	public LineNumber() {
		currentPosition.add(0,1);
	}


	/*
	* Method that "goes along" a given layer; increments given layer
	* Does not remove subcurrentPosition behind it
	* I.e. 3.4.2 --> 3.4.3
	*/
	public void step() {
		int last = currentPosition.size()-1;
		currentPosition.set(last, currentPosition.get(last)+1);
	}  


	/*
	* Shuffles up to the next sublayer, despite what the sub-sublayer is
	* I.e. 3.4.2 --> 3.5
	*/
	public void layerPlus() {
		// Removes integers after layer that should be decremented
		/*
		 * for (int n; n + 1 < currentPosition.size(); n++) {
			fakeNum = currentPosition.get(n);
			}
			currentPosition[n] = currentPosition[n] + 1;
		 * 
		 * 
		 * 
		 */
		
		
		
	}



	/*
	* Shuffles down to the next sublayer, despite what the sub-sublayer is
	* I.e. 3.4.5 --> 3.4
	*/
	public void layerMinus() {
		// Makes sure Layer does not go under 1 (no proofs have 0 as a label)
		/*
		if (currentPosition.get(n) == currentPosition.get(0)) {
			int doNothing = currentPosition.get(n) + 0; // Essentially do nothing
		} else {
			// Removes integers after layer that should be decremented
			for (int k = n + 1; k < currentPosition.size(); k++) {
				currentPosition.remove(k);
			}
			int getNum = currentPosition.get(n);
			getNum = getNum - 1;
			
			currentPosition[n] = currentPosition[n] - 1;
		}
		*/
	}


	/*
	* Adds another element to line number
	* Starts at 1
	* I.e. 3.4.2 --> 3.4.2.1
	*/
	public void layerAdder() {
		currentPosition.add(new Integer(1));
	}
	
	/*
	* Returns the current line number as a String object
	*/
	public String current() {
		return currentPosition.toString();
	}

	
	/*
	* Prints out a human-readable statement
	* Returns all "currentPosition" combined to form the appropriate index
	*/
	public String toString() {
		// Checking to see if first element is the layer that should be incremented
		/*
		if (currentPosition.get(n) == currentPosition.get(0)) {
			index = Integer.toString(currentPosition.get(n));
			return index;
		}
		//if (currentPosition.get(n) == fakeNum) { 
			//currentPosition.remove(n);
		//}
		for (int k = n + 1; k < currentPosition.size(); k++) {
			String dotAdded = Integer.toString(currentPosition.get(n)) + ".";
			dotAdded = Integer.toString(currentPosition.get(n));
			index = index + dotAdded;
		}
		return index;
		*/
		String rtn = "";
		for (int i = 0;i < currentPosition.size();i++)
		{
			if (i != 0)
			{
				rtn += ".";
			}
			rtn += currentPosition.get(i);
		}
		
		return rtn;
		
	}


// Ends LineNumber class
 }
