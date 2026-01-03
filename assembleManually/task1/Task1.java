/*
Задание 1
Круговой массив - массив из элементов, в котором по достижению конца массива
следующим элементом будет снова первый. Массив задается числом n, то есть
представляет собой числа от 1 до n.
Пример кругового массива для n=3:
1231231
Напишите программу, которая выводит путь, по которому, двигаясь интервалом длины
m по заданному массиву, концом будет являться первый элемент.
Началом одного интервала является конец предыдущего.
Путь - массив из начальных элементов полученных интервалов.
Программа должна обрабатывать 2 массива одновременно.
Результаты необходимо объединить в один общий путь.
Параметры передаются в качестве аргументов командной строки!

Пример 1
Массив 1: n = 6, m = 3
Круговой массив: 123456.
При длине обхода 3 получаем интервалы: 123, 345, 561. Полученный путь: 135.
Массив 2: n = 5, m = 4
Круговой массив: 12345.
При длине обхода 4 получаем интервалы: 1234, 4512, 2345, 5123, 3451. Полученный путь: 14253.
В этом примере на вход подаются аргументы: 6 3 и 5 4, ожидаемый
вывод в консоль: 13514253

Пример 2
Массив 1: n = 4, m = 2
Круговой массив: 1234.
При длине обхода 2 получаем интервалы: 12, 23, 34, 41. Полученный путь: 1234.
Массив 2: n = 6, m = 4
Круговой массив: 123456.
При длине обхода 4 получаем интервалы: 1234, 4561. Полученный путь: 14.
Ожидаемый вывод в консоль: 123414
 */

import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {// 6 3 5 4   4 2 6 4
        int n = Integer.valueOf(args[0]);
        int m = Integer.valueOf(args[1]);
        System.out.print(circleWay(n, m));
        n = Integer.valueOf(args[2]);
        m = Integer.valueOf(args[3]);
        System.out.print(circleWay(n, m));
    }

    public static String circleWay(int n, int m) {
        ArrayList<String> baseArr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            baseArr.add(String.valueOf(i));
        }
        ArrayList<String> intervals = new ArrayList<>();
        String newWay = "";
        int baseArrInd = 0;
        int counterToM = 1;
        boolean oneFullCycleDone = false;
        String result = "";
        while (true) {
            if (baseArrInd == baseArr.size() - 1) {
                oneFullCycleDone = true;
            }
            String addedElement = baseArr.get(baseArrInd);
            newWay += addedElement;
            if (counterToM == 1) {
                result += addedElement;
            }
            if (counterToM == m) {
                intervals.add(newWay);
                if (baseArrInd == 0 && oneFullCycleDone) {
                    break;
                }
                newWay = "";
                counterToM = 0;
                baseArrInd--;
            }
            if (baseArrInd == (n - 1)) baseArrInd = -1;
            baseArrInd++;
            counterToM++;
        }
        return result;
    }
}