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
			fr = new FileReader("C:\\Users\\재용\\Desktop\\hw01.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++) {
				list1.add(inputString.charAt(i));
				list2.add(inputString.charAt(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("첫번째 리스트");
		System.out.println(list1);
		System.out.println("두번째 리스트");
		System.out.println(list1);
		System.out.println("첫번째 리스트 3번째에 b를 추가");
		list1.add(3, 'b');
		System.out.println(list1);
		list1.remove(3);
		System.out.println("첫번째 리스트 3번째 값을 삭제");
		System.out.println(list1);
		list1.reverse();
		System.out.println("첫번째 리스트 역방향");
		System.out.println(list1);
		System.out.println("첫번째 리스트에 두번째 리스트를 합");
		list1.addAll(list2);
		System.out.println(list1);

		System.out.println("첫번째 리스트 4번째 값");
		System.out.println(list1.get(4));
		System.out.println("첫번째 리스트 4번째 값을 제거");
		list1.remove(4);
		
		System.out.println(list1);
	}

}
