package class07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadLine {

	// 按行读数据，也可以读空格
	// StreamTokenizer 读一整行，有空格的情况比较麻烦
	public static void main(String[] args) throws IOException {
		System.out.println("请输入文本");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}

}
