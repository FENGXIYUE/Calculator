package util;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

/**
 * @author: LLT
 * @description:有效表达式校验验证工具类 在对表达式求值运算之前，必须先对表达式的正确性进行校验。
 * 例如：
 * 1.空字符串、
 * 2.运算符连续、
 * 3.空括号、
 * 4.括号不配对、
 * 5.右括号前是运算符、
 * 6.表达式内含非法字符等。
 * @date: 2021/6/30 3:23 下午
 * @modified By
 */
public class ValidateUtil {

    /**
     * 检查算术表达式的基本合法性，符合返回true，否则false
     */
    public static boolean isValidated(String expression) {
        if (StringUtils.isEmpty(expression)) {
            return false;// 表达式不能为空
        }
        Stack<Character> stack = new Stack<Character>(); // 用来保存括号，检查左右括号是否匹配
        boolean manyEqualSign = false; // 用来标记'='符号是否存在多个

        //表达式长度
        int length = expression.length();
        if (length < 2) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            char n = expression.charAt(i);
            // 判断字符是否合法
            if (!(
                    StringUtils.isNumeric(String.valueOf(n))
                            || '(' == n || ')' == n
                            || '+' == n || '-' == n
                            || '*' == n || '/' == n
                            || '=' == n || '.' == n
            )) {
                return false;
            }

            //规则判断 不能位于首位  只能是数字或者 - + （
            if (i == 0 && !checkIsNumOrAddOrSubOrLP(n)) {
                return false;
            }
            //规则判断 不能位于倒数第二位 只能数字或者）
            if (i == length - 2 && !checkIsNumOrRP(n)) {
                return false;
            }
            //规则判断 = 位于末位
            if (i == length - 1 && !('=' == n)) {
                return false;
            }

            // 将左括号压栈，只能是数字 - +用来给后面的右括号进行匹配  括号个数匹配
            if ('(' == n) {
                char next = expression.charAt(i + 1);
                if (!checkIsNumOrAddOrSubOrLP(next)) {
                    return false;
                }
                stack.push(n);
            }
            //右括号后只能不能是（ 和数字
            if (')' == n) { // 匹配括号
                char next = expression.charAt(i + 1);
                if (!checkIsNotNumOrLP(next)) {
                    return false;
                }


                if (stack.isEmpty() || !('(' == stack.pop())) // 括号是否匹配
                    return false;
            }
            //+ - / * 后不能跟 + - / * ） = .
            if ('+' == n || '-' == n || '*' == n || '/' == n) {
                char next = expression.charAt(i + 1);
                if (!checkIsNumOrLP(next)) {
                    return false;
                }
            }
            // . 只能跟数字
            if ('.' == n) {
                char next = expression.charAt(i + 1);
                if (!StringUtils.isNumeric(String.valueOf(next))) {
                    return false;
                }
            }
            //数字后不能跟（
            if ('.' == n) {
                char next = expression.charAt(i + 1);
                if ('(' == next) {
                    return false;
                }
            }
            // 检查是否有多个'='号
            if ('=' == n) {
                if (manyEqualSign)
                    return false;
                manyEqualSign = true;
            }
        }
        // 可能会有缺少右括号的情况,没有弹出所有右括号
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }


    public static boolean checkIsNumOrAddOrSubOrLP(char n) {
        if ((')' == n || '*' == n || '/' == n | '=' == n || '.' == n)) {
            return false;
        }
        return true;
    }

    public static boolean checkIsNumOrRP(char n) {
        if ('(' == n || '*' == n || '/' == n | '=' == n || '.' == n || '+' == n || '-' == n) {
            return false;
        }
        return true;
    }

    public static boolean checkIsNumOrLP(char n) {
        if (')' == n || '*' == n || '/' == n | '=' == n || '.' == n || '+' == n || '-' == n) {
            return false;
        }
        return true;
    }

    public static boolean checkIsNotNumOrLP(char n) {
        if ('.' == n || '(' == n || StringUtils.isNumeric(String.valueOf(n))) {
            return false;
        }
        return true;
    }


}
//1*(-2)+0.2+((3-2)*2+((2-3)+1.2)-2)