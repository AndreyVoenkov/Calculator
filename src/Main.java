import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Введите выражение: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        calc(input);
    }

    public static String calc(String input) throws Exception {
        String[] arrayRim = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        boolean testnum1 = false;
        boolean testnum2 = false;
        int numIndex1 = 0;
        int numIndex2 = 2;
        int num1 = 0;
        int num2 = 0;

        String[] arrayInput = input.split(" ");

        if (arrayInput.length < 3) {
            throw new Exception("т.к. строка не является математической операцией");
        }

        String operator = arrayInput[1];

        if (arrayInput.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

// Тест 1-го значения в выражении на наличие римской цифры
        int num1Rim = testNum_Rim(arrayInput, num1, numIndex1, arrayRim);
        if (num1Rim == 0) {
            testnum1 = false;
        } else {
            testnum1 = true;
            num1 = num1Rim;
        }

// Тест 2-го значения в выражении на наличие римской цифры
        int num2Rim = testNum_Rim(arrayInput, num2, numIndex2, arrayRim);
        if (num2Rim == 0) {
            testnum2 = false;
        } else {
            testnum2 = true;
            num2 = num2Rim;
        }

        if (((testnum1 == true) && (testnum2 == false)) || ((testnum1 == false) && (testnum2 == true))) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }


        if (((testnum1 == false) && (testnum2 == false))) {
            try {
                num1 = Integer.parseInt(arrayInput[0]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка преобразования строки в число: " + input); // проверка, введено ли число (1 значение в строке)
            }
            try {
                num2 = Integer.parseInt(arrayInput[2]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка преобразования строки в число: " + input); // проверка, введено ли число (2 значение в строке)
            }
            test1_10(num1, num2); // проверка введенных значений на соответствие условию задачи (должны вводится значений от 1 до 10)

            int result = 0;
            calcArab(num1, num2, result, operator); // Вычисление выражения состоящего из арабских цифр и вывод результата.

        }
        if (((testnum1 == true) && (testnum2 == true))) {

            int result = 0;
            int resultRim = calcRim(num1, num2, result, operator); // Вычисление выражения состоящего из римских цифр.
            arabToRim(resultRim, input, arrayRim); // Через метод преобразуем результат арабских числах в результат на римских числах и выводим.
        }
        return operator;
    }


    // Метод конвертации результата выражения на арбском в римские цифры.
    static void arabToRim(int resultRim, String resultToRim, String [] arrayRim) {

        if (resultRim > 0 && resultRim < 11) {
            resultToRim = arrayRim[resultRim];
        } else if (resultRim <= 20) {
            resultToRim = arrayRim[10] + arrayRim[resultRim - 10];
        } else if (resultRim <= 30) {
            resultToRim = arrayRim[11] + arrayRim[resultRim - 20];
        } else if (resultRim <= 40) {
            resultToRim = arrayRim[12] + arrayRim[resultRim - 30];
        } else if (resultRim <= 50) {
            resultToRim = arrayRim[13] + arrayRim[resultRim - 40];
        } else if (resultRim <= 60) {
            resultToRim = arrayRim[14] + arrayRim[resultRim - 50];
        } else if (resultRim <= 70) {
            resultToRim = arrayRim[15] + arrayRim[resultRim - 60];
        } else if (resultRim <= 80) {
            resultToRim = arrayRim[16] + arrayRim[resultRim - 70];
        } else if (resultRim <= 90) {
            resultToRim = arrayRim[17] + arrayRim[resultRim - 80];
        } else if (resultRim < 100) {
            resultToRim = arrayRim[18] + arrayRim[resultRim - 90];
        } else if (resultRim == 100) {
            resultToRim = arrayRim[19];
        }

        System.out.println("Результат: "+resultToRim);
    }

    // Метод вычисления выражения состоящего из римских цифр.
    static int calcRim(int num1, int num2, int result, String operator) throws Exception {
        switch (operator) {
            case "+":
                result = num1 + num2;
                return result;
            case "-":
                if (num1 < num2) {
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
                if ((num1 - num2) == 0) {
                    throw new Exception("т.к. в римской системе нет нуля");
                }
                result = num1 - num2;
                return result;
            case "*":
                result = num1 * num2;
                return result;
            case "/":
                result = num1 / num2;
                return result;
        }
        return 0;
    }

    // Метод вычисления выражения состоящего из арабских цифр.
    static void calcArab(int num1, int num2, int result, String operator) {
        switch (operator) {
            case "+":
                result = num1 + num2;
                String str1 = Integer.toString(result);
                System.out.println("Результат: " + str1);
                break;
            case "-":
                result = num1 - num2;
                String str2 = Integer.toString(result);
                System.out.println("Результат: " + str2);
                break;
            case "*":
                result = num1 * num2;
                String str3 = Integer.toString(result);
                System.out.println("Результат: " + str3);
                break;
            case "/":
                result = num1 / num2;
                String str4 = Integer.toString(result);
                System.out.println("Результат: " + str4);
                break;
        }
    }


    // Метод проверки значений не входящих в диапазон от 1 до 10.
    static void test1_10(int num1, int num2) throws Exception{
        if ((num1 == 0 || num1 < 1 || num1 > 10) && (num2 == 0 || num2 < 1 || num2 > 10)) {
            throw new Exception("т.к. оба значения меньше 1 или больше 10");
        }
        if (num1 == 0 || num1 < 1 || num1 > 10) {
            throw new Exception("т.к. первое значение меньше 1 или больше 10");
        }
        if (num2 == 0 || num2 < 1 || num2 > 10) {
            throw new Exception("т.к. второе значение меньше 1 или больше 10");
        }
    }


    // Метод для тестирования значений в выражении на наличие римских цифр
    static int testNum_Rim(String[] arrayInput, int num, int numIndex, String[] arrayRim) {

        for (int i = 0; i < arrayRim.length; i++) {
            if (arrayInput[numIndex].equals(arrayRim[i])) {
                num = i;
            }
        }
        return num;
    }
}