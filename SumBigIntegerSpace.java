import java.math.BigInteger;

public class SumBigIntegerSpace {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }

        String line = sb.toString();
        BigInteger sum = BigInteger.valueOf(0);

        StringBuilder number = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!(Character.getType(ch) == Character.SPACE_SEPARATOR)) {
                if (ch == '-' && number.length() == 0) {
                    number.append('-');
                } else if (Character.isDigit(ch)) {
                    number.append(ch);
                }
            } else if (number.length() > 0) {
                sum = sum.add(new BigInteger(number.toString()));
                number.setLength(0);
            }
        }

        System.out.println(sum);
    }
}
