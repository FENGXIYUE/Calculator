package util;

import domain.ExpressionResult;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Stack;

public class CalculatorUtil {
    /**
     * 解析并计算四则运算表达式(含括号)，返回计算结果
     *
     * @param expression 算术表达式(含括号)
     */
    public static ExpressionResult execute(String expression) {
        //格式化表达式
        expression = ExpressionPrepareUtil.expressionFormat(expression);
        ExpressionResult result = ExpressionResult.builder().expression(expression).build();
        // 检查表达式是否合法
        if (!ValidateUtil.isValidated(expression)) {
            System.err.println("错误：输入算术表达式有误！");
            return result;
        }
        /** 数字栈：用于存储表达式中的各个数字 */
        Stack<BigDecimal> numberStack = new Stack<BigDecimal>();
        /** 符号栈：用于存储运算符和括号 */
        Stack<Character> symbolStack = new Stack<Character>();

        // 用于缓存数字，因为数字可能是多位的
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i); // 获取一个字符
            char lastCh = expression.charAt(i > 1 ? i - 1 : 0);
            if (i == 0 && ('-' == ch || '+' == ch)) {
                temp.append(ch);
            } else if ('(' == lastCh && ('-' == ch || '+' == ch)) {
                temp.append(ch);
            } else if (StringUtils.isNumeric(String.valueOf(ch))) { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            } else if ('.' == ch) {
                temp.append(ch);
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {
                    BigDecimal num = new BigDecimal(tempStr);
                    numberStack.push(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                }

                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!PriorityCompareUtil.priorityCompare(ch, symbolStack) && !symbolStack.empty()) {
                    BigDecimal b = numberStack.pop(); // 出栈，取出数字，后进先出
                    BigDecimal a = numberStack.pop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch (symbolStack.pop()) {
                        case '+':
                            numberStack.push(a.add(b));
                            break;
                        case '-':
                            numberStack.push(a.subtract(b));
                            break;
                        case '*':
                            numberStack.push(a.multiply(b));
                            break;
                        case '/':
                            numberStack.push(a.divide(b));
                            break;
                        default:
                            break;
                    }
                } // while循环结束
                if (ch != '=') {
                    symbolStack.push(new Character(ch)); // 符号入栈
                    if (ch == ')') { // 去括号
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束

        result.setResult(numberStack.pop());
        return result; // 返回计算结果
    }


}