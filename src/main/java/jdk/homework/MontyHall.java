/* В качестве задачи предлагаю вам реализовать код для демонстрации
парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно
убедиться в верности парадокса (запустить игру в цикле на 1000 и
вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям
 */

package jdk.homework;

import java.util.*;

public class MontyHall {
    static Random random = new Random();

    public static void main(String[] args) {

        int NUMBER_DOORS = 3; // количество дверей
        int TRY_TIMES = 1000; // количество попыток

        HashMap<Integer, String> test1 = doorChangeStatistics(NUMBER_DOORS, TRY_TIMES);
        System.out.println(test1);
        HashMap<Integer, String> test2 = doorNotChangeStatistics(NUMBER_DOORS, TRY_TIMES);
        System.out.println(test2);

    }

    // Метод для выбора ведущим последней двери
    public static int showmanChoice (int numberDoors, int carNumber, int playerChoice){

        List<Integer> notSelectedDoors = new ArrayList<>();
        int lastDoor = 0;
        for (int i = 1; i <= numberDoors; i++) {
            if(playerChoice != i){
                notSelectedDoors.add(i); // номера оставшихся дверей заносим в список
            }
        }
        Collections.shuffle(notSelectedDoors); // перемешиваем номера оставшихся дверей
        for (int j = 0; j < notSelectedDoors.size() -1; j++){ // открываем все двери с козой, кроме последней или с авто
            if (notSelectedDoors.get(j) == carNumber) {
                lastDoor = notSelectedDoors.get(j);
                return lastDoor;
            } else {
                lastDoor = notSelectedDoors.get(notSelectedDoors.size() -1);
            }
        }
        return lastDoor; // номер оставшейся двери
    }

    // Метод для подсчета статистики при изменении выбора игрока
    public static HashMap<Integer, String> doorChangeStatistics (int numberDoors, int tryTimes){

        HashMap<Integer, String> result = new HashMap<>();
        int win = 0;
        int fail = 0;

        for (int i = 0; i <tryTimes; i++) {
            int carNumber = random.nextInt(numberDoors) + 1; // выбор случайной двери, за которой будет авто
            int playerChoice = random.nextInt(numberDoors) + 1; // игрок выбирает свою дверь
            playerChoice = showmanChoice(numberDoors, carNumber, playerChoice); // игрок меняет выбор двери на последнюю
            if (carNumber == playerChoice) {
                result.put(i, "win");
                win++;
            } else {
                result.put(i, "fail");
                fail++;
            }
        }
        System.out.printf("Всего было %d испытаний Парадокса Монти Холла при изменении выбора игрока, " +
                "из них количество побед %d, количество поражений %d", tryTimes, win, fail);
        System.out.println();
        return result;
    }

    // Метод для подсчета статистики при неизменном выбора игрока
    public static HashMap<Integer, String> doorNotChangeStatistics (int numberDoors, int tryTimes){

        HashMap<Integer, String> result = new HashMap<>();
        int win = 0;
        int fail = 0;

        for (int i = 0; i <tryTimes; i++) {
            int carNumber = random.nextInt(numberDoors) + 1; // выбор случайной двери, за которой будет авто
            int playerChoice = random.nextInt(numberDoors) + 1; // игрок выбирает свою дверь

            if (carNumber == playerChoice) {
                result.put(i, "win");
                win++;
            } else {
                result.put(i, "fail");
                fail++;
            }
        }
        System.out.printf("Всего было %d испытаний Парадокса Монти Холла при неизменном выборе игрока, " +
                "из них количество побед %d, количество поражений %d", tryTimes, win, fail);
        System.out.println();
        return result;
    }

}