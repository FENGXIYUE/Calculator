package util;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author: LLT
 * @description:
 * @date: 2021/6/30 4:21 下午
 * @modified By
 */
public class ExpressionPrepareUtil {

    public static String EQUAL_SIGN = "=";

    /**
     * 去除字符串中的所有空格
     *
     * @param expression
     * @return
     */
    public static String removeStrSpace(String expression) {
        return Optional.ofNullable(expression).map(e -> e.replaceAll(StringUtils.SPACE, StringUtils.EMPTY)).orElse(StringUtils.EMPTY);
    }

    /**
     * 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
     *
     * @param str
     * @return
     */
    public static String expressionFormat(String str) {
        str = removeStrSpace(str);
        if (str.length() > 1 && !EQUAL_SIGN.equals(str.charAt(str.length() - 1) + StringUtils.EMPTY)) {
            str += EQUAL_SIGN;
        }
        return str;
    }
}
