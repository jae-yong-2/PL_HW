import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	public static class Token {											//��ū Ÿ���� ����
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

	public Scanner(String source) {							//���� ���� ����ϱ� ���� ����
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		this.st = new StringTokenizer(this.source, " ");
		initTM();
	}

	private void initTM() {									//TMǥ�� ����� �Լ�
		for (int i = 0; i < 4; i++) { // -1�� ��� �ʱ�ȭ
			for (int k = 0; k < 128; k++) {					//�� ó�� ��� ���� -1�� �ǰ� transM�� �ʱ�ȭ ���ش�.
				this.transM[i][k] = -1;
			}
		}

		for (int i = 0; i < 4; i++) {						//

			int j = 0;
			for (j = '0'; j <= '9'; j++) { 					// digit���� ���� ��츦 �ʱ�ȭ
				this.transM[i][j] = 2;
				if (i == 3) {
					this.transM[i][j] = 3;
				}
			}

			j = '-'; 										// '-'�� ������츦 �ʱ�ȭ
			if (i == 0) {
				this.transM[i][j] = 1;
			} else if (i == 3) {
				this.transM[i][j] = 3;
			}

			if (i == 0 || i == 3) {
				for (j = 'a'; j <= 'z'; j++) {				 // a~z�� alpha�� ���� ��츦 �ʱ�ȭ

					this.transM[i][j] = 3;
				}

				for (j = 'A'; j <= 'Z'; j++) { 				// A~Z�� alpha�� ���� ��츦 �ʱ�ȭ
					this.transM[i][j] = 3;
				}
			}
		}
	}

	private Token nextToken() {										//���� ��ū�� �޾Ƽ� ��ū�� Ÿ���� �����ְ�, ������ȯ�ϴ� �޼ҵ�
		int stateOld = 0, stateNew;	
		// ��ū�� �� �ִ��� �˻�
		if (!st.hasMoreTokens())
			return null;
		// �� ���� ��ū�� ����
		String temp = st.nextToken();
		Token result = null;
		for (int i = 0; i < temp.length(); i++) {					//��ū�� �޾� ��ū�� �����ϳ��ϳ��� �˻����ش�.

			char charactor = temp.charAt(i);						
			stateNew = this.transM[stateOld][charactor];			//������ TM�� Ȱ���Ͽ� ��ū�� ���¸� ���������ش�.

			if (stateNew == -1) {									//���� ��찡 ���ýÿ� ���������� ����ϰ� ��ȯ
				System.out.printf("%s, ����\n",temp);
				return null;
			}

			stateOld = stateNew;									//���ο� ���¸� �������ش�.
			// ���ڿ��� ���ڸ� �ϳ��� ������ ������¿� TransM�� �̿��Ͽ� ���� ���¸� �Ǻ�
			// ���� �Էµ� ������ ���°� reject �̸� �����޼��� ��� �� return��
			// ���� ���� ���¸� ���� ���·� ����

		}
		for (TokenType t : TokenType.values()) {					//tŸ���� �� ����.
			if (t.finalState == stateOld) {							//������ ������ ���¿� ���� ���°�������
				result = new Token(t, temp);						//�� ���¸� ����
				break;
			}
		}
		return result;

	}

	public List<Token> tokenize() {			 			//�Է� ���� ������ ��ū���� ����Ʈ�� �������ִ� �޼ҵ�
		// �Է����� ���� ��� token�� ����
		// nextToken()�̿��� �ĺ��� �� list�� �߰��� ��ȯ
		List<Token> list = new ArrayList<Token>();		//��ȯ�� ����Ʈ�� �������ش�.
		Token token = nextToken();						//nextToken�Լ��� ��ū �ϳ��� �޾� �����Ѵ�.
		for (; token != null; token = nextToken()) {	//��ū�� ���� ���� ���� ����Ʈ�� �������ش�.
			list.add(token);
		}

		return list;									//����Ʈ ��ȯ
	}

	public enum TokenType {				//��ū�� Ÿ���� Ÿ������ Ŭ����
		ID(3), INT(2);					//2�� ������ int 3�� ������ id�� �ȴ�.

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