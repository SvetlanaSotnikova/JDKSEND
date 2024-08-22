package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Main {
    private static final int COUNT_SIMULATIONS = 100;
    public static void main(String[] args) {
        MontyHallsGame game = new MontyHallsGame();
        Map<Integer, GameResult> resultMap = new HashMap<>();

        for (int i = 0; i < COUNT_SIMULATIONS; i++) {
            boolean switchDoor = new Random().nextBoolean();
            GameResult gameResult = game.play(switchDoor);
            resultMap.put(i, gameResult);
        }

        MontyHallsSimulation.printStatistics(resultMap);
    }
}