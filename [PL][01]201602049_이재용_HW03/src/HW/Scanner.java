package HW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;

public class Scanner {
	public static class Token {
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

	public Scanner(String source) {
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		this.st = new StringTokenizer(this.source, " ");
		initTM();
	}

	private void initTM() {
		// transM[4][128] = { {...}, {...}, {...}, {...} };
		// values of entries: -1, 0, 1, 2, 3 : next state
		// TransM[0]['0'] = 2, ..., TransM[0]['9'] = 2,
		// TransM[0]['-'] = 1,
		// TransM[0]['a'] = 3, ..., TransM[0]['z'] = 3,
		// TransM[1]['0'] = 2, ..., TransM[1]['9'] = 2,
		// TransM[2]['0'] = 2, ..., TransM[1]['9'] = 2,
		// TransM[3]['A'] = 3, ..., TransM[3]['Z'] = 3,
		// TransM[3]['a'] = 3, ..., TransM[3]['z'] = 3,
		// TransM[3]['0'] = 3, ..., TransM[3]['9'] = 3,
		// ...
		// The values of the other entries are all -1.
		int transM[][] = new int[4][128];
		for (int i = 0; i < 4; i++) { // -1�� ��� �ʱ�ȭ
			for (int k = 0; k < 128; k++) {
				transM[i][k] = -1;
			}
		}

		for (int i = 0; i < 4; i++) {

			int j = 0;
			for (j = '0'; j <= '9'; j++) { // 0~9
				transM[i][j] = 2;
				if (i == 3) {
					transM[i][j] = 3;
				}
			}

			j = '-'; // -
			if (i == 0) {
				transM[i][j] = 1;
			} else if (i == 3) {
				transM[i][j] = 3;
			}

			if (i == 0 || i == 3) {
				for (j = 'a'; j <= 'z'; j++) { // a~z

					transM[i][j] = 3;
				}

				for (j = 'A'; j <= 'Z'; j++) { //A~Z
					transM[i][j] = 3;
				}
			}
		}

	}

	private Token nextToken() {
		int stateOld = 0, stateNew;
		// ��ū�� �� �ִ��� �˻�
		if (!st.hasMoreTokens())
			return null;
		// �� ���� ��ū�� ����
		String temp = st.nextToken();
		Token result = null;
		for (int i = 0; i < temp.length(); i++) {
			// ���ڿ��� ���ڸ� �ϳ��� ������ ������¿� TransM�� �̿��Ͽ� ���� ���¸� �Ǻ�
			// ���� �Էµ� ������ ���°� reject �̸� �����޼��� ��� �� return��
			// ���� ���� ���¸� ���� ���·� ����
			
		}
		for (TokenType t : TokenType.values()) {
			if (t.finalState == stateOld) {
				result = new Token(t, temp);
				break;
			}
		}
		return result;
	}

	public List<Token> tokenize() {
		// �Է����� ���� ��� token�� ����
		// nextToken()�̿��� �ĺ��� �� list�� �߰��� ��ȯ
		return null;
	}

	public enum TokenType {
		ID(3), INT(2);

		private final int finalState;

		TokenType(int finalState) {
			this.finalState = finalState;
		}
	}

	public static void main(String[] args) throws IOException {
	      System.out.println("�������̱���");
	      FileReader fr = new FileReader("c:/as03.txt");
	      System.out.println("�������̱���");
	      BufferedReader br = new BufferedReader(fr);
	      System.out.println("�������̱���");
	      String source = br.readLine();
	      System.out.println("�������̱���");
	      Scanner s = new Scanner(source);
	      System.out.println("�������̱���");
	      List<Token> tokens = s.tokenize();
	      System.out.println("�������̱���");
	      System.out.println(tokens);
	      System.out.println("�������̱���");
	      }
}
