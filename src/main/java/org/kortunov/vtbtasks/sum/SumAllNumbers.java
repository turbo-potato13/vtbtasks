package org.kortunov.vtbtasks.sum;

public class SumAllNumbers {

    public static int calculateSum(String string){
        int sum = 0;
        int tmp;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))){
                int value = 0;
                tmp = i;
                while (i < string.length() && Character.isDigit(string.charAt(i))){
                    value = value * 10 + (string.charAt(i) - 48);
                    i++;
                }
                if (tmp > 0 && string.charAt(tmp - 1) == '-'){
                    value *= -1;
                }
                sum += value;
            }
        }
        return sum;
    }
}
