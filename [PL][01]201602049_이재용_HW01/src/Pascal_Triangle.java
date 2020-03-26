import java.util.Scanner;

public class Pascal_Triangle {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		int a[][] = new int[2 * num - 1][num];	//입력 받는 값에 따라 이차원 배열의 크기를 설정해준다.
		Frame(a, num - 1, num - 1);				//생성된 배열에 맞게 1을 채워 큰틀을 만든다.
		make(a,0,0,0);							//틀과 함께 그 안에 들어간 수를 계산하여 넣어준다.
		int b;
		if(num==1){								//출력전에 모양을 이쁘게 만들기위한 예외처리
			b=0;
		}
		else if(num%2==0) {
			b=(2 * num)/2;
		}else {
			b=(2 * num - 1)/2;
		}
		print(a, 0, 0,0, Integer.toString(a[b][num-1]).length());							//이차원 배열을 과제의 조건에 맞게 출력해준다.
	}

	public static void Frame(int a[][], int x, int y) {		// 맨처음 이차원 배열을 다 돌면서 1에 해당하는 부분을 다 채워주는 메소드
		a[x][y - x] = 1;									//삼각형에서 왼쪽 변에 해당하는 부분에 1을 채워준다.
		a[2 * y - x][y - x] = 1;							//반대편에 채워준다. (y좌표는 x값이 변할때 마다 자동으로 변화한다.
		if ((x == 0)) {				
			return;											//탈출 조건으로
		}
		Frame(a, x - 1, y);									//x값을 기준으로 0이 될때까지 시행한다. y좌표는 x에 따라 변한다.

	}	

	public static void print(int a[][], int x, int y, int countPrint, int maxNum) {	//이차원 배열에 값들을 출력해주는 메소드
		if (countPrint==a.length * a[0].length) {		//전역 변수로 이차원 배열의 전체 크기에 도달하면 무조건 탈줄 하도록 조건을 걸었다.
			return;
		}
		countPrint++;
	
			if(x==a.length ||y==a[0].length)
		 	return;
		
		if (a[x][y] != 0) {								//과제에서 요구하는 것과 같이 숫자만 출력할 수 있게 한다.

			System.out.print(a[x][y]);
			int number=Integer.toString(a[x][y]).length();
			blank(maxNum-number);							//그냥 출력시 칸이 밀려 밀리지 않게 공백으로 기준을 잡아준다. 값이 없으면 공백 출력
		} else {
			blank(maxNum);
		}
		
		if (x == a.length - 1) {						//x좌표의 값을 다돌면 y좌표를 증가 시켜준다.
			nextLine(2);							//배열을 한줄 출력할때마다 줄바꿈을 해준다.
			print(a, 0, y + 1, countPrint, maxNum);
		}
		print(a, x + 1, y, countPrint, maxNum);								//x좌표를 증가 시키면서 메소드를 실행한다.
	}
	

	public static void make(int a[][], int x, int y,int make) {	//이차원 배열에 들가갈 값을 계산해 주는 메소드
		if (make == a.length * a[0].length) {		//전역 변수로 이차원 배열의 전체 크기에 도달하면 무조건 탈줄 하도록 조건을 걸었다.
			return;
		}
		make++;
		
		if(x>1 && y>1 && x<a.length-1) {				//이차원 배열을 처음부터 순서 대로 돌면서 조건에 맞게 값을 채워준다.
			a[x][y]=a[x-1][y-1]+a[x+1][y-1];
		}
		
		
		if (x == a.length - 1) {						//x좌표의 값을 다돌면 y좌표를 증가 시켜준다.
			make(a, 0, y + 1,make);
		}
		make(a, x + 1, y,make);								//x좌표를 증가 시키면서 메소드를 실행한다.
	}
	
	public static void blank(int num) {// 콜솔창에 잘 정렬될 수 있게 입력된 num값만큼 띄어쓰기를 축력하는 메소드
		if(num == 0 )return ;		   //탈출조건 num이 0이 될 경우
		System.out.print(" ");
		blank(num-1);				   //재귀적으로 들어가 1씩 작은 수를 대입하여 들어간다.
	}
	public static void nextLine(int num) {//입력한 값만큼 띄어쓰기를 해주는 메소드
		if(num==0) return ;				//blank()와 같은 원리
		System.out.println();
		nextLine(num-1);
	}
}
