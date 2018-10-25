
public class LinkStrand implements IDnaStrand{
	
	private class Node{
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private StringBuilder mySequence;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myAppends = 0;
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = source.length();
		myIndex = 0; 
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
		
	}

	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myAppends ++;
		mySize += dna.length();
		myLast = myLast.next;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		Node firstCopy = null;
		Node listCopy = null;
		Node current = myFirst;
		while(current!=null) {		
			StringBuilder copy = new StringBuilder(current.info);
			firstCopy = new Node("thisisfake");
			firstCopy.info = copy.reverse().toString();
			firstCopy.next = listCopy;
			listCopy = firstCopy;
			current = current.next;
		}
		LinkStrand backwards = new LinkStrand();
		backwards.myFirst = firstCopy;
		backwards.myAppends = myAppends; //use this
		backwards.mySize = mySize;		 //here too
		Node rvsrvs = listCopy;
		while(listCopy != null) {
			backwards.myLast = rvsrvs.next;
			listCopy = listCopy.next;
		}
		return backwards;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		if(index >= mySize || index < 0) throw new IndexOutOfBoundsException();
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if(myCurrent==null) {
				myIndex = 0;
				myLocalIndex = 0;
				myCurrent = myFirst;
			}
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
        return myCurrent.info.charAt(myLocalIndex);
	}
	
	public String toString() {
		while(myFirst != null) {
			mySequence  = mySequence.append(myFirst.info);
			myFirst = myFirst.next;
		}
		return mySequence.toString();
	}
	
}