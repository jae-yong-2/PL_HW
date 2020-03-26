package HW;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;

	private void linkFirst(char element) {
		head = new Node(element, head);
	}

	private void linkLast(char element, Node x) {					//1��
		if(x.next==null) {					//����� �������� ������
			x.next=new Node(element,null);	//����� �������� ���� �������ְ�
			return;							//��ȯ�Ѵ�.
		}
		linkLast(element,x.next);			//����� ���������� �������� next�� ��� ����
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

	private Node node(int index, Node x) {								//2��
		if(index==1) {
			return x;					//index�� 1�̵Ǹ� ó�� �Է��� index����ŭ ������ node�� ��ȯ�Ѵ�.
		}
		return node(index-1,x.next);	//��� next�� ���ٰ� �������� ��ȯ�� x�� ��� ���´�.
	}

	private int length(Node x) {										//3��
		if(x==null){				//�Ǹ������� null�� ���� 0�� ��ȯ��Ų��.
			return 0;
		}
		return 1+length(x.next);	//������ ���� 1�� ���ؼ� ���� ��� ��ȯ�� �ش�.
	}

	private String toString(Node x) {							//4��
		if(x==null)
			return "";//���̻� Ȯ���� node�� ������ ""�� ����Ѵ�.
		return Character.toString(x.item)+ " " +toString(x.next); //��ȯ�Ҷ� ���� ��°��� �������� ��ȯ�Ѵ�.
	}

	private void reverse(Node x, Node pred) {						//5��
		if(x.next==null) {		//����Ʈ�� ���������� ������ ���ٸ�
			head=x;
			head.next=pred;
			return;				//���� ��带 head�� �ٲٰ� ������ pred�� ������ �̵��Ѵ�.
		}
		reverse(x.next, x);		//��������� ����Ʈ�� ������ ����.
		x.next=pred;			//���� ����� next�� ���� ���� �����Ѵ�.
	}

	public void reverse() {
		reverse(head, null);
	}

	private void addAll(Node x, Node y) {						//6��
		if(x.next==null) {				//����� ������ null�ϰ��
			x.next=y;					//�� null�� y�� �־����� ��ȯ�Ѵ�.	
			return;					
		}
		addAll(x.next,y);				//x��忡 ���ؼ� ���������� ��������� �����Ѵ�.
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
