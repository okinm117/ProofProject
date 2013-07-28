import static org.junit.Assert.*;

import org.junit.Test;


public class ProofTest{
  @Test
	public void testReasonDelagation(){
		TheoremSet mythm= new TheoremSet();
		Proof tester = new Proof(mythm);
		try{
			tester.extendProof("show ((a=>b)=>((b=>c)=>(a=>c)))");
			tester.extendProof("assume (a=>b)");
			tester.extendProof("show ((b=>c)=>(a=>c))");
			tester.extendProof("assume (b=>c)");
			tester.extendProof("show (a=>c)");
			tester.extendProof("assume a");
			tester.extendProof("show c");
			tester.extendProof("assume ~c");
			tester.extendProof("mt 3.2.2.1 3.1 ~b");
			tester.extendProof("mt 2 3.2.2.2 ~a");
			tester.extendProof("co 3.2.2.3 3.2.1 c");
			tester.extendProof("ic 3.2.2 (a=>c)");
			tester.extendProof("ic 3.2 ((b=>c)=>(a=>c))");
			tester.extendProof("ic 3 ((a=>b)=>((b=>c)=>(a=>c)))");
			assertTrue(tester.isComplete());
		}catch(IllegalInferenceException e){
			System.out.println(e);
		} catch (IllegalLineException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		String [] myTxts = {"thmSetOne","proof06_Revised.txt"};
		
		ProofChecker myChecker = new ProofChecker(myTxts);
	}


}
