package org.example;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Map;
@Getter
@Setter
public class MontyHallsSimulation {
    private static final int COUNT_SIMULATIONS = 100;

    //в инете нашла
    public static void printStatistics(Map<Integer, GameResult> results) {
        long switchWins = results.values().stream()
                .filter(r -> r.isSwitchDoor() && r.isWin())
                .count();
        long switchLosses = results.values().stream()
                .filter(r -> r.isSwitchDoor() && !r.isWin())
                .count();
        long stayWins = results.values().stream()
                .filter(r -> !r.isSwitchDoor() && r.isWin())
                .count();
        long stayLosses = results.values().stream()
                .filter(r -> !r.isSwitchDoor() && !r.isWin())
                .count();

        double switchWinPercentage = 100.0 * switchWins / (switchWins + switchLosses);
        double stayWinPercentage = 100.0 * stayWins / (stayWins + stayLosses);

        DescriptiveStatistics switchStats = new DescriptiveStatistics();
        DescriptiveStatistics stayStats = new DescriptiveStatistics();

        results.values().stream()
                .filter(GameResult::isSwitchDoor)
                .mapToDouble(r -> r.isWin() ? 1 : 0)
                .forEach(switchStats::addValue);

        results.values().stream()
                .filter(r -> !r.isSwitchDoor())
                .mapToDouble(r -> r.isWin() ? 1 : 0)
                .forEach(stayStats::addValue);

        System.out.println("Results after " + COUNT_SIMULATIONS + " simulations:");
        System.out.println("Switch Wins: " + switchWins);
        System.out.println("Switch Losses: " + switchLosses);
        System.out.println("Stay Wins: " + stayWins);
        System.out.println("Stay Losses: " + stayLosses);
        System.out.println("Switch Win Percentage: " + switchWinPercentage + "%");
        System.out.println("Stay Win Percentage: " + stayWinPercentage + "%");
        System.out.println("Switch Statistics: " + switchStats);
        System.out.println("Stay Statistics: " + stayStats);
    }

}
