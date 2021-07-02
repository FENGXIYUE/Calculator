package util;

import java.util.Stack;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/1 10:04 上午
 * @modified By
 * <p>
 * 符号优先级说明（从高到低）:
 * 第1级: (
 * 第2级: * /
 * 第3级: + -
 * 第4级: )
 */
public class PriorityCompareUtil {
    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     * true继续压栈
     * false将栈顶元素弹出
     */
    public static boolean priorityCompare(char operator, Stack<Character> operatorStack) {
        //空栈直接放入栈
        if (operatorStack.empty()) { // 空栈返回ture
            return true;
        }

        // 查看堆栈顶部的对象，注意不是出栈
        char top = operatorStack.peek();
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (operator) {
            case '(': // 优先级最高
                return true;
            case '*':
            case '/': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '+':
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }

}
