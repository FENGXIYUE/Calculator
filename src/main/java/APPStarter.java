import domain.ExpressionResult;
import org.apache.commons.lang3.StringUtils;
import util.CalculatorUtil;

import java.util.Scanner;

/**
 * @author: LLT
 * @description:
 * @date: 2021/6/29 11:26 上午
 * @modified By
 */
public class APPStarter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用lsqdzc计算器："
                + "\r\n" + "1.输入表达式开始计算"
                + "\r\n" + "2.输入\"exit\"退出");
        while (true) {
           String inputLine = scanner.nextLine();
            if("exit".equals(inputLine)){
                break;
            }
            //输入表达式不为空
            if (StringUtils.isNotBlank(inputLine)) {
                //输入表达式不能包含字母
                ExpressionResult result = CalculatorUtil.execute(inputLine);
                System.out.println("计算结果为:"+result.getExpression()+result.getResult());
            }
            System.out.println("1.输入表达式开始计算"
                    + "\r\n" + "2.输入\"exit\"退出");
        }
    }
}
