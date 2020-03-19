import java.util.Scanner;

public class Pascal_Triangle {
	static int countPrint = 0;
	static int find=0;
	static int make=0;
	static int maxNum=1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		int a[][] = new int[2 * num - 1][num];	//�Է� �޴� ���� ���� ������ �迭�� ũ�⸦ �������ش�.
		Frame(a, num - 1, num - 1);				//������ �迭�� �°� 1�� ä�� ūƲ�� �����.
		make(a,0,0);							//Ʋ�� �Բ� �� �ȿ� �� ���� ����Ͽ� �־��ش�.
		findMax(a,0,0);							//���߿� ��¿��� ������ �����ֱ� ���ؼ� ������ �迭���� ���� ū���� ���ڱ��̸� ����Ѵ�.
		print(a, 0, 0);							//������ �迭�� ������ ���ǿ� �°� ������ش�.
//		exception(a,0,0);//Ʋ�� �������� ���� �����ϰ� ����Ѵ�.
	}

	public static void Frame(int a[][], int x, int y) {		// ��ó�� ������ �迭�� �� ���鼭 1�� �ش��ϴ� �κ��� �� ä���ִ� �޼ҵ�
		a[x][y - x] = 1;									//�ﰢ������ ���� ���� �ش��ϴ� �κп� 1�� ä���ش�.
		a[2 * y - x][y - x] = 1;							//�ݴ��� ä���ش�. (y��ǥ�� x���� ���Ҷ� ���� �ڵ����� ��ȭ�Ѵ�.
		if ((x == 0)) {				
			return;											//Ż�� ��������
		}
		Frame(a, x - 1, y);									//x���� �������� 0�� �ɶ����� �����Ѵ�. y��ǥ�� x�� ���� ���Ѵ�.

	}	

	public static void print(int a[][], int x, int y) {	//������ �迭�� ������ ������ִ� �޼ҵ�
		if (countPrint == a.length * a[0].length) {		//���� ������ ������ �迭�� ��ü ũ�⿡ �����ϸ� ������ Ż�� �ϵ��� ������ �ɾ���.
			return;
		}
		countPrint++;
		
		if (a[x][y] != 0) {								//�������� �䱸�ϴ� �Ͱ� ���� ���ڸ� ����� �� �ְ� �Ѵ�.

			System.out.print(a[x][y]);
			int number=Integer.toString(a[x][y]).length();
			blank(maxNum-number);							//�׳� ��½� ĭ�� �з� �и��� �ʰ� �������� ������ ����ش�. ���� ������ ���� ���
		} else {
			blank(maxNum);
		}
		
		if (x == a.length - 1) {						//x��ǥ�� ���� �ٵ��� y��ǥ�� ���� �����ش�.
			System.out.println();
			print(a, 0, y + 1);
		}
		print(a, x + 1, y);								//x��ǥ�� ���� ��Ű�鼭 �޼ҵ带 �����Ѵ�.
	}
	

	public static void make(int a[][], int x, int y) {	//������ �迭�� �鰡�� ���� ����� �ִ� �޼ҵ�
		if (make == a.length * a[0].length) {		//���� ������ ������ �迭�� ��ü ũ�⿡ �����ϸ� ������ Ż�� �ϵ��� ������ �ɾ���.
			return;
		}
		make++;
		
		if(x>1 && y>1 && x<a.length-1) {				//������ �迭�� ó������ ���� ��� ���鼭 ���ǿ� �°� ���� ä���ش�.
			a[x][y]=a[x-1][y-1]+a[x+1][y-1];
		}
		
		
		if (x == a.length - 1) {						//x��ǥ�� ���� �ٵ��� y��ǥ�� ���� �����ش�.
			make(a, 0, y + 1);
		}
		make(a, x + 1, y);								//x��ǥ�� ���� ��Ű�鼭 �޼ҵ带 �����Ѵ�.
	}
	

	public static void findMax(int a[][], int x, int y) {	//������ �迭�� ����ִ� ���߿��� ���� ū���� ���ڼ��� ã���ִ� �޼ҵ�
		if (find == a.length * a[0].length) {		//���� ������ ������ �迭�� ��ü ũ�⿡ �����ϸ� ������ Ż�� �ϵ��� ������ �ɾ���.
			return;
		}
		find++;
		
		if(x>1 && y>1 && x<a.length-1) {				//������ �迭�� ó������ ���� ��� ���鼭 ���� ū���� ���������� ä���ش�.
			if(maxNum<Integer.toString(a[x][y]).length()) {
				maxNum=Integer.toString(a[x][y]).length();
			}
		}
		if (x == a.length - 1) {						//x��ǥ�� ���� �ٵ��� y��ǥ�� ���� �����ش�.
			findMax(a, 0, y + 1);
		}
		findMax(a, x + 1, y);								//x��ǥ�� ���� ��Ű�鼭 �޼ҵ带 �����Ѵ�.
	}
	
	public static void blank(int num) {// �ݼ�â�� �� ���ĵ� �� �ְ� �Էµ� num����ŭ ���⸦ ����ϴ� �޼ҵ�
		if(num == 0 )return ;		   //Ż������ num�� 0�� �� ���
		System.out.print(" ");
		blank(num-1);				   //��������� �� 1�� ���� ���� �����Ͽ� ����.
	}
}
