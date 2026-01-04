package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
Дан массив целых чисел nums.
Напишите программу, выводящую минимальное количество ходов, требуемых для приведения всех элементов массива к одному числу.
За один ход можно уменьшить или увеличить число массива на 1. 
Имеется ограничение по максимальному количеству ходов – 20.
Необходимо вывести минимальное количество ходов. В случае, если за 20 ходов это сделать невозможно, необходимо вывести соответствующее сообщение.
Элементы массива читаются из файла, переданного в качестве аргумента командной строки!
Логика:
nums = [4, 5, 6]
Решение: [4, 5, 6] => [5, 5, 6] => [5, 5, 5].
Минимальное количество ходов: 2.

Пример 1:
На вход подаётся файл с содержимым:
3
6
8
9
Вывод в консоль: 8

Пример 2:
На вход подаётся файл с содержимым:
1
16
3
20
Вывод в консоль: «20 ходов недостаточно для приведения всех элементов массива к одному числу».
*/

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        ArrayList<Integer> arr = new ArrayList<>();
        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        arr.sort(Integer::compareTo);
        int counter = 0;
        int median = arr.get(arr.size() / 2);
        for (int i = 0; i < arr.size(); i++) {
            counter += Math.abs(arr.get(i) - median);
        }
        if (counter > 20) {
            System.out.println("Сработало ограничение по максимальному количеству ходов: " + counter + " > 20");
        } else System.out.println("Минимальное число ходов = " + counter);
    }
}