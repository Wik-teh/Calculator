package Calculator;

public class Romes extends Number {
    private final int romes_value1_int;
    private final int romes_value2_int;
    private int result_int;
    private String sign = "";
    private String result_string;
    private final String[] roman_letters_9 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    Romes(String value1, String value2) {
        this.romes_value1_int = convert_to_int(value1);
        this.romes_value2_int = convert_to_int(value2);
    }
    private String convert_result_to_Romes(int n) {
        int ostatok = n % 10;
        if (ostatok != 0) {
            try {
                return convert_result_to_Romes(n - ostatok) + roman_letters_9[ostatok - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                sign = "";
                return convert_result_to_Romes(n - ostatok) + roman_letters_9[(ostatok + 1) * -1];
            }
        }
        //Возможность вывести отрицательное римское число
        if (n > 0) {
            n = n - 10;
            return convert_result_to_Romes(n) + "X";
        } else if (n < 0) {
            n = n + 10;
            return convert_result_to_Romes(n) + "X";
        }   else {
            return sign;
        }
    }

    @Override
    public void sum() {
        result_int = romes_value1_int + romes_value2_int;
        result_string = convert_result_to_Romes(result_int);
    }

    @Override
    public void sub() {
        result_int = romes_value1_int - romes_value2_int;
        result_string = convert_result_to_Romes(result_int);
    }

    @Override
    public void div() {
        try {
            result_int = romes_value1_int / romes_value2_int;
            result_string = convert_result_to_Romes(result_int);
        } catch (ArithmeticException e) {
            System.out.print("Проверьте правильность ввода римских цифр. Вероятно введены и арабские и римские одновременно. ");
        }

    }

    @Override
    public void mul() {
        result_int = romes_value1_int * romes_value2_int;
        result_string = convert_result_to_Romes(result_int);
    }

    @Override
    public int getResult() {
        return result_int;
    }
    public String getStringResult() {
        return result_string;
    }

    private int convert_to_int(String romes_value){
        char[] value_char = romes_value.toCharArray();
        int[] values_int = new int[value_char.length];
        for (int i = 0; i < value_char.length; i++) {
            switch (value_char[i]) {
                case 'I' -> values_int[i] = 1;
                case 'V' -> values_int[i] = 5;
                case 'X' -> values_int[i] = 10;
                default -> System.out.println("Содержится неверный символ. Проверьте правильность ввода римских цифр:" + "\n" +
                        "I = 1" + "\n" +
                        "V = 5" + "\n" +
                        "X = 10");
            }
        }
        int result = values_int[0];
        for (int i = 0; i < values_int.length && values_int.length > i + 1; i++) {
            if (values_int[i] >= values_int[i+1]) {
                result += values_int[i+1];
            } else if (values_int[i] < values_int[i+1]) {
                result = result + values_int[i+1] - 2;
            }
        }
        return result;
    }


}
