import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author stepa
 * @since 2026-01-02
 * Задание 2
 * Напишите программу, которая рассчитывает положение точек относительно эллипса.
 * Координаты центра эллипса и его радиусы считываются из файла 1.
 * Пример:
 * 0 0 – координаты центра
 * 5 3 – координаты радиуса
 * <p>
 * Координаты точек считываются из файла 2.
 * Пример:
 * 0 3
 * 0 0
 * 6 0
 * <p>
 * Вывод для данных примеров файлов:
 * 0
 * 1
 * 2
 * <p>
 * Пути к файлам передаются программе в качестве аргументов!
 * ● файл с координатами и радиусом эллипса - 1 аргумент;
 * ● файл с координатами точек - 2 аргумент;
 * ● координаты - рациональные числа в диапазоне от 10-38 до 1038;
 * ● количество точек от 1 до 100;
 * ● вывод каждого положения точки заканчивается символом новой строки;
 * ● соответствия ответов:
 * ○ 0 - точка лежит на окружности
 * ○ 1 - точка внутри
 * ○ 2 - точка снаружи.
 * Вывод программы в консоль!
 */
public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {//ellipsisCoords.txt dotsCoords.txt
        Scanner e = new Scanner(new File(args[0]));
        Scanner e2 = new Scanner(new File(args[1]));
        double elx = e.nextDouble(), ely = e.nextDouble();//центр эллипса
        double a = e.nextDouble(), b = e.nextDouble();//радиусы неповёрнутого эллипса

        while (e2.hasNext()) {
            System.out.println(dotPositionrelativeToEllipsis(elx, ely, a, b, e2.nextDouble(), e2.nextDouble()));
        }

        for (int i = 0; i < 10; i++) {
        }
    }

    public static int dotPositionrelativeToEllipsis(double ellCenterX, double ellCenterY, double radiusA,
                                                       double radiusB, double dotX, double dotY) {
        if (Math.pow((dotX - ellCenterX) / radiusA, 2) + Math.pow((dotY - ellCenterY) / radiusB, 2) == 1) {
            return 0;
        } else if (Math.pow((dotX - ellCenterX) / radiusA, 2) + Math.pow((dotY - ellCenterY) / radiusB, 2) < 1) {
            return 1;
        } else {
            return 2;

    }
}
}

