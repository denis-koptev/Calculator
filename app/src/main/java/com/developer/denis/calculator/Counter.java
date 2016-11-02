package com.developer.denis.calculator;

/**
 * Created by Denis on 18.03.2016.
 */
public class Counter {

    String Expression;

    Counter(String expr_) {
        Expression = expr_;
    }

    String get_value() {
        while (Expression.contains("*") || Expression.contains("/")) {
            int sign_pos;
            double lhs = 0, rhs = 0;
            int r_index, l_index;
            boolean floating = false;
            double number = 0;
            int float_number = 0;
            if (Expression.contains("*")) {
                sign_pos = Expression.lastIndexOf("*");
            } else {
                sign_pos = Expression.lastIndexOf("/");
            }
            r_index = sign_pos + 1;
            while (r_index <= Expression.length() - 1 && Character.isDigit(Expression.charAt(r_index))
                    || Expression.charAt(r_index) == '.') {
                if (Expression.charAt(r_index) == '.') {
                    floating = true;
                    r_index++;
                    continue;
                }
                if (!floating) {
                    rhs *= 10;
                    rhs += ((int) Expression.charAt(r_index));
                } else {
                    float_number++;
                    rhs *= 10;
                    rhs += ((int) Expression.charAt(r_index));
                }
                rhs /= (double)Math.pow(10, float_number);
                ++r_index;
            }

            l_index = sign_pos - 1;
            float_number = 0;
            floating = false;

            while (l_index >= 0 && Character.isDigit(Expression.charAt(l_index))
                    || Expression.charAt(l_index) == '.') {
                if (Expression.charAt(l_index) == '.') {
                    floating = true;
                    l_index--;
                    continue;
                }
                if (!floating) {
                    lhs *= 10;
                    lhs += ((int) Expression.charAt(l_index));
                } else {
                    float_number++;
                    lhs *= 10;
                    lhs += ((int) Expression.charAt(l_index));
                }
                lhs /= (double)Math.pow(10, float_number);
                --l_index;
            }
            double counted = (Expression.charAt(sign_pos) == '*') ? lhs * rhs : lhs / rhs;
            String result = Expression.substring(0, l_index) +  counted + Expression.substring(r_index, Expression.length() - 1);
            Expression = result;
            return Expression;

        }
        return Expression;
    }

}
