import java.math.BigDecimal;

public class BigNumber {

    public static void main(String[] args) {
        System.out.println(add("123456789", "3"));
        System.out.println(subtract("4", "3"));
        System.out.println(multiply("4", "3"));
        System.out.println(divide("9999", "9"));
    }


    public static String add(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry == 1) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }
        return result.reverse().toString();
    }

    public static String multiply(String num1, String num2) {
        String ans = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder current = new StringBuilder();
            for (int j = num2.length() - 1; j > i; j--) {
                current.append(0);
            }
            int carry = 0;
            int digit2 = num2.charAt(i) - '0';
            int j = num1.length() - 1;
            while (j >= 0 || carry != 0) {
                int digit1 = j >= 0 ? num1.charAt(j--) - '0' : 0;
                int mul = digit1 * digit2 + carry;
                current.append(mul % 10);
                carry = mul / 10;
            }
            ans = add(ans, current.reverse().toString());
        }
        return ans;
    }

    public static String subtract(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        boolean isNegative = false;
        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            isNegative = true;
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        int borrow = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0) {
            int digit1 = num1.charAt(i--) - '0';
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
        }
        if (isNegative) {
            result.append("-");
        }
        return result.reverse().toString();
    }


    public static String divide(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            current.append(num1.charAt(i));
            while (current.length() > 1 && current.charAt(0) == '0') {
                current.deleteCharAt(0);
            }
            int quotient = 0;
            String diff = "";
            while ((diff = subtract(current.toString(), num2)).charAt(0) != '-') {
                current = new StringBuilder(diff);
                quotient++;
            }
            sb.append(quotient);
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

}