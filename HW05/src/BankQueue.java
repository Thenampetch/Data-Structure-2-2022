
public class BankQueue { // must work for any implementation of DeQ
	DeQ[] counters;
	DeQ special;

	public BankQueue(DeQ[] counters, DeQ special) {
		super();
		this.counters = counters;
		this.special = special;
	}

	//Write this method
	public void distribute() throws Exception {
		//calculate needed queue length.
		float peopleNum = 0;
		for (DeQ i : counters) {
			peopleNum += i.size();
		}
		float queueNum = 	counters.length + 1 ;
		int neededLength = Math.round(peopleNum/queueNum);
		
		//distribute people to special queue.
		
		for (DeQ k : counters) {
		
			//create auxiliary LinkedList to stored and check which member we should add to special. 
			DeQ aux = new DeQLinkedList();
			
			//if people more than needed length move to aux.	
			while (k.size() > neededLength) {
				aux.insertLast(k.removeLast());
			}
		
			//if special queue still have free space move people from aux to special.
			while (special.size() < neededLength && !aux.isEmpty())  {
				special.insertLast(aux.removeLast());
			}

			//if aux still have people left move back to counters in order.
			while (!aux.isEmpty()) {
				k.insertLast(aux.removeLast());
			}
		}
		
		//Case where special queue is empty.
		if (special.isEmpty()) {
			DeQ lastQueue = counters[counters.length - 1];
			special.insertLast(lastQueue.removeLast());
		}
		
	}

}
