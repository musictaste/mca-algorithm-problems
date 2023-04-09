package class06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code01_Test1 {

    // 题目描述，这个题是关于数组，求XXX东西
    // 数组长度N，N <= 10^5

    // 100
    // a b c ...
    // 200
    // a b c ...
    // 100000
    // a b c ..

    // 根据题目给定的数据量，直接设定这个测试用例最大的数据量
    public static int MAXN = 100010;

    // 提前准备空间，防止卡的很严的测试用例计算你申请的这部分的空间
    // 有的测试用例，会计算你申请的所有空间，不管你程序运算后释放的空间，不要让输入的数据影响你的空间复杂度
    public static int[] arr = new int[MAXN];

    public static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            // 当前测试用例，使用的有效的数据空间是n
            for (int i = 0; i < n; i++) {
                // 需要手动调
                in.nextToken();
                // in.nval 抓到下一个数字
                arr[i] = (int) in.nval;
            }
            // n = 1000
            // arr 0..999
            int ans = 1;
            // 输出答案，系统会抓到这个答案
            out.println(ans);
            out.flush();

        }
    }

}
