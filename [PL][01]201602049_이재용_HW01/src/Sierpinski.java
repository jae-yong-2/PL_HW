import java.util.Scanner;

public class Sierpinski {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input= new Scanner(System.in);
		int number = input.nextInt();
		System.out.print("S"+number+" = ( "+Sierpinski_up(number)+" / "+Sierpinski_down(number)+" ) L");//값을 출력해준다.
	}
	//프랙탈은 수식이 (3/2)^n으로 가기 때문에 분모와 분자 부분을 따로 계산해준다.
	public static int Sierpinski_up(int number) {//분자 부분에 해당하는 값을 계산
		if(number==1) {
			return 3;//처음 삼각형의 길이 3L이라 탈출조건에 3을 넣는다
		}
		return 3*Sierpinski_up(number-1);//3을 곱하면서 처음 입력한 수에서 1씩 작은 수로 재귀적으로 들어간다. number만틈 실행
	}
	
	
	public static int Sierpinski_down(int number) {//분모 부분에 해당하는 값을 구해준다.
		if(number==1) {
			return 1;
		}
		return 2*Sierpinski_down(number-1);			//2를 곱하면서 처음 입력한 수에서 1씩 작을 수로 재귀적으로 들어간다. number만큼 실행
	}
}
