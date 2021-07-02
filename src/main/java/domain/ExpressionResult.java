package domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/1 3:03 下午
 * @modified By
 */
@Data
@Builder
public class ExpressionResult {
    private String expression;
    private BigDecimal result;
}
