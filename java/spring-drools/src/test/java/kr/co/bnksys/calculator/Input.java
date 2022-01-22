package kr.co.bnksys.calculator;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Input {

    private String op;
    private int num1;
    private int num2;
    private int result;

}
