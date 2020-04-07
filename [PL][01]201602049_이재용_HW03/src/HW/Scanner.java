import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	public static class Token {											//토큰 타입을 정의
		public final TokenType type;
		public final String lexme;

		Token(TokenType type, String lexme) {
			this.type = type;
			this.lexme = lexme;
		}

		@Override
		public String toString() {
			return String.format("[%s: %s]", type.toString(), lexme);
		}

	}

	private int transM[][];
	private String source;
	private StringTokenizer st;

	public Scanner(String source) {							//받은 값을 사용하기 좋게 변경
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		this.st = new StringTokenizer(this.source, " ");
		initTM();
	}

	private void initTM() {									//TM표를 만드는 함수
		for (int i = 0; i < 4; i++) { // -1로 모두 초기화
			for (int k = 0; k < 128; k++) {					//맨 처음 모든 값이 -1이 되게 transM을 초기화 해준다.
				this.transM[i][k] = -1;
			}
		}

		for (int i = 0; i < 4; i++) {						//

			int j = 0;
			for (j = '0'; j <= '9'; j++) { 					// digit값을 받을 경우를 초기화
				this.transM[i][j] = 2;
				if (i == 3) {
					this.transM[i][j] = 3;
				}
			}

			j = '-'; 										// '-'를 받을경우를 초기화
			if (i == 0) {
				this.transM[i][j] = 1;
			} else if (i == 3) {
				this.transM[i][j] = 3;
			}

			if (i == 0 || i == 3) {
				for (j = 'a'; j <= 'z'; j++) {				 // a~z의 alpha를 받을 경우를 초기화

					this.transM[i][j] = 3;
				}

				for (j = 'A'; j <= 'Z'; j++) { 				// A~Z의 alpha를 받을 경우를 초기화
					this.transM[i][j] = 3;
				}
			}
		}
	}

	private Token nextToken() {										//다음 토큰을 받아서 토큰의 타입을 정해주고, 값을반환하는 메소드
		int stateOld = 0, stateNew;	
		// 토큰이 더 있는지 검사
		if (!st.hasMoreTokens())
			return null;
		// 그 다음 토큰을 받음
		String temp = st.nextToken();
		Token result = null;
		for (int i = 0; i < temp.length(); i++) {					//토큰을 받아 토큰의 글자하나하나를 검사해준다.

			char charactor = temp.charAt(i);						
			stateNew = this.transM[stateOld][charactor];			//만들어둔 TM을 활용하여 토큰의 상태를 재정의해준다.

			if (stateNew == -1) {									//없는 경우가 나올시에 오류문구를 출력하고 반환
				System.out.printf("%s, 오류\n",temp);
				return null;
			}

			stateOld = stateNew;									//새로운 상태를 저장해준다.
			// 문자열의 문자를 하나씩 가져와 현재상태와 TransM를 이용하여 다음 상태를 판별
			// 만약 입력된 문자의 상태가 reject 이면 에러메세지 출력 후 return함
			// 새로 얻은 상태를 현재 상태로 저장

		}
		for (TokenType t : TokenType.values()) {					//t타입을 다 돈다.
			if (t.finalState == stateOld) {							//마지막 정해진 상태와 같은 상태가있으면
				result = new Token(t, temp);						//그 상태를 저장
				break;
			}
		}
		return result;

	}

	public List<Token> tokenize() {			 			//입력 받은 정리된 토큰들을 리스트에 저장해주는 메소드
		// 입력으로 들어온 모든 token에 대해
		// nextToken()이용해 식별한 후 list에 추가해 반환
		List<Token> list = new ArrayList<Token>();		//반환할 리스트를 생성해준다.
		Token token = nextToken();						//nextToken함수로 토큰 하나를 받아 저장한다.
		for (; token != null; token = nextToken()) {	//토큰이 없어 질때 까지 리스트에 저장해준다.
			list.add(token);
		}

		return list;									//리스트 반환
	}

	public enum TokenType {				//토큰의 타입을 타나내는 클래스
		ID(3), INT(2);					//2를 받으면 int 3을 받으면 id가 된다.

		private final int finalState;

		
		TokenType(int finalState) {
			this.finalState = finalState;
		}
	}

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:/as03.txt");
		BufferedReader br = new BufferedReader(fr);
		String source = br.readLine();
		Scanner s = new Scanner(source);
		List<Token> tokens = s.tokenize();
		System.out.println(tokens);
	}
}