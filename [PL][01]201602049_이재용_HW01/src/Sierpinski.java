import java.util.Scanner;

public class Sierpinski {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input= new Scanner(System.in);
		int number = input.nextInt();
		System.out.print("S"+number+" = ( "+Sierpinski_up(number)+" / "+Sierpinski_down(number)+" ) L");//���� ������ش�.
	}
	//����Ż�� ������ (3/2)^n���� ���� ������ �и�� ���� �κ��� ���� ������ش�.
	public static int Sierpinski_up(int number) {//���� �κп� �ش��ϴ� ���� ���
		if(number==1) {
			return 3;//ó�� �ﰢ���� ���� 3L�̶� Ż�����ǿ� 3�� �ִ´�
		}
		return 3*Sierpinski_up(number-1);//3�� ���ϸ鼭 ó�� �Է��� ������ 1�� ���� ���� ��������� ����. number��ƴ ����
	}
	
	
	public static int Sierpinski_down(int number) {//�и� �κп� �ش��ϴ� ���� �����ش�.
		if(number==1) {
			return 1;
		}
		return 2*Sierpinski_down(number-1);			//2�� ���ϸ鼭 ó�� �Է��� ������ 1�� ���� ���� ��������� ����. number��ŭ ����
	}
}
