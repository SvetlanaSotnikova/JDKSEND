package org.example;

import com.google.common.primitives.Ints;

import java.util.*;

public class MontyHallsGame {
    private static final int COUNT_DOORS = 3;
    private final Random RANDOM = new Random();

    public GameResult play(boolean switchDoor) {
        int[] doors = {0, 0, 1}; // 0 - goat, 1 - car
        int[] shuffleDoors = shuffleArray(doors);

        int contestantChoice = RANDOM.nextInt(COUNT_DOORS);
        int hostChoice = finedHostChoice(shuffleDoors, contestantChoice);
        int switchDoorChoice = COUNT_DOORS - contestantChoice - hostChoice;
        int finalChoice = switchDoor ? switchDoorChoice : contestantChoice;
        boolean win = shuffleDoors[finalChoice] == 1;
        GameResult result = new GameResult();
        result.setSwitchDoor(switchDoor);
        result.setWin(win);

        return result;
    }

    private int finedHostChoice(int[] doors, int contestantChoice) {
        for (int i = 0; i < doors.length; i++) {
            if (i != contestantChoice && doors[i] == 0) { // Ведущий должен выбрать дверь с козлом, которую не выбрал участник
                return i;
            }
        }
        throw new IllegalStateException("Host choice not found");
    }

    private int[] shuffleArray(int[] array) {
        List<Integer> list = Ints.asList(array);
        Collections.shuffle(list);
        return Ints.toArray(list);
    }
}
