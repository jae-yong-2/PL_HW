package HW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Linked_List {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursionLinkedList list1 = new RecursionLinkedList();
		RecursionLinkedList list2 = new RecursionLinkedList();
		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\���\\Desktop\\hw01.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++) {
				list1.add(inputString.charAt(i));
				list2.add(inputString.charAt(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ù��° ����Ʈ");
		System.out.println(list1);
		System.out.println("�ι�° ����Ʈ");
		System.out.println(list1);
		System.out.println("ù��° ����Ʈ 3��°�� b�� �߰�");
		list1.add(3, 'b');
		System.out.println(list1);
		list1.remove(3);
		System.out.println("ù��° ����Ʈ 3��° ���� ����");
		System.out.println(list1);
		list1.reverse();
		System.out.println("ù��° ����Ʈ ������");
		System.out.println(list1);
		System.out.println("ù��° ����Ʈ�� �ι�° ����Ʈ�� ��");
		list1.addAll(list2);
		System.out.println(list1);

		System.out.println("ù��° ����Ʈ 4��° ��");
		System.out.println(list1.get(4));
		System.out.println("ù��° ����Ʈ 4��° ���� ����");
		list1.remove(4);
		
		System.out.println(list1);
	}

}
