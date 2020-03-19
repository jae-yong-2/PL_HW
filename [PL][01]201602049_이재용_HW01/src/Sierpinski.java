import java.util.Scanner;

public class Sierpinski {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input= new Scanner(System.in);
		int number = input.nextInt();
		System.out.print(" ( "+Sierpinski_up(number)+" / "+Sierpinski_down(number)+" ) L");
	}
	
	public static int Sierpinski_up(int number) {
		if(number==1) {
			return 3;
		}
		return 3*Sierpinski_up(number-1);
	}
	
	
	public static int Sierpinski_down(int number) {
		if(number==1) {
			return 1;
		}
		return 2*Sierpinski_down(number-1);
	}
}
