package HW;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;

	private void linkFirst(char element) {
		head = new Node(element, head);
	}

	private void linkLast(char element, Node x) {					//1번
		if(x.next==null) {					//노드의 다음값이 없으면
			x.next=new Node(element,null);	//노드의 다음값을 새로 생성해주고
			return;							//반환한다.
		}
		linkLast(element,x.next);			//노드의 마지막까지 가기위해 next로 계속 들어간다
	}

	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}

	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}

	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}

	private Node node(int index, Node x) {								//2번
		if(index==1) {
			return x;					//index가 1이되면 처음 입력한 index번만큼 들어갔으니 node를 반환한다.
		}
		return node(index-1,x.next);	//계속 next로 들어가다가 마지막에 반환한 x를 들고 나온다.
	}

	private int length(Node x) {										//3번
		if(x==null){				//맨마지막에 null이 오면 0을 반환시킨다.
			return 0;
		}
		return 1+length(x.next);	//이전의 값에 1을 더해서 값을 계속 반환해 준다.
	}

	private String toString(Node x) {							//4번
		if(x==null)
			return "";//더이상 확인할 node가 없으면 ""를 출력한다.
		return Character.toString(x.item)+ " " +toString(x.next); //반환할때 만다 출력값을 누적시켜 반환한다.
	}

	private void reverse(Node x, Node pred) {						//5번
		if(x.next==null) {		//리스트의 마지막에서 다음이 없다면
			head=x;
			head.next=pred;
			return;				//현재 노드를 head로 바꾸고 다음을 pred로 설정후 이동한다.
		}
		reverse(x.next, x);		//재귀적으로 리스트의 끝까지 들어간다.
		x.next=pred;			//현재 노드의 next를 이전 노드로 설정한다.
	}

	public void reverse() {
		reverse(head, null);
	}

	private void addAll(Node x, Node y) {						//6번
		if(x.next==null) {				//노드의 다음이 null일경우
			x.next=y;					//그 null에 y를 넣어준후 반환한다.	
			return;					
		}
		addAll(x.next,y);				//x노드에 대해서 마지막까지 재귀적으로 실행한다.
	}

	public void addAll(RecursionLinkedList list) {
		addAll(this.head, list.head);
	}

	public boolean add(char element) {
		if (head == null) {
			linkFirst(element);
		} else {
			linkLast(element, head);
		}
		return true;
	}

	public void add(int index, char element) {
		if (!(index >= 0 && index <= size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0)
			linkFirst(element);
		else
			linkNext(element, node(index - 1, head));
	}

	public char get(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		return node(index, head).item;
	}

	public char remove(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0) {
			return unlinkFirst();
		}
		return unlinkNext(node(index - 1, head));
	}

	public int size() {
		return length(head);
	}

	@Override
	public String toString() {
		if (head == null)
			return "[]";
		return "[ " + toString(head) + "]";
	}

	private static class Node {
		char item;
		Node next;

		Node(char element, Node next) {
			this.item = element;
			this.next = next;
		}
	}
}
