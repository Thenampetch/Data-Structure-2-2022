
public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	public void insert(int value, Iterator p) throws Exception {
		
		//from CDLinkedList
		super.insert(value,p);
		
		DListNode pos = ((DListIterator)p).currentNode.nextNode;
		DListIterator left = new DListIterator(pos.previousNode);
		DListIterator right = new DListIterator(pos.nextNode);
		 
		int thatValue = value;
		int count = 1;
		 
		while (true) {
			while (left.currentNode.data == thatValue) {
				 count ++;
				 left.previous();
			 }
			while (right.currentNode.data == thatValue) {
				 count ++;
				 right.next();
			 }
			 
			if (count >= 3) {
				 removeBetween(left,right,count);
				 score += count;
				 
				 //check consecutive again.
				if (right.currentNode.data == left.currentNode.data) {
					 //recount the consecutive nodes.
					 thatValue = right.currentNode.data;
					 count = 0;
				}else break;
			}else break;
		 }
		 
	}

	
	public void removeBetween(DListIterator left, DListIterator right, int inc) {
		//check if it valid to removeBetween
		if (left == null || right == null) {
			return;
		}
		
		if (right.currentNode.nextNode == left.currentNode || left.currentNode == right.currentNode) {
			return;
		}
		
		//removeBetween
		left.currentNode.nextNode = right.currentNode;
		right.currentNode.previousNode = left.currentNode;
		size -= inc;
	}

}
