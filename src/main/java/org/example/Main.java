package org.example;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        List<Fish> fishList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the our aquarium! \nEnter initial value of fish:");

        int quantityOfFish = scanner.nextInt();
        // This block for initialization
        for (int i = 0; i < quantityOfFish; i++) {
            Fish fish = new Fish();
            fish.start();
            fishList.add(fish);
        }

        while (!fishList.isEmpty()) {
            Thread.sleep(500);
            Fish parentOne = fishList.get(random.nextInt(fishList.size()));
            Fish parentTwo = fishList.get(random.nextInt(fishList.size()));
            if (!parentOne.equals(parentTwo)) {
                Fish newFish = parentOne.meetingFishes(parentTwo);
                if (Objects.nonNull(newFish)) {
                    fishList.add(newFish);
                    System.out.println("Added new Fish: " + newFish);
                } else {
                    continue;
                }
                fishList = fishList.stream().filter(Fish::getIsAlive).collect(Collectors.toList());
                System.out.println("Quantity of fishes: " + fishList.size());
                System.out.println("Quantity of MALE fishes: " + fishList.stream().filter(fish -> fish.getGender().equals(Gender.MALE)).count());
                System.out.println("Quantity of FEMALE fishes: " + fishList.stream().filter(fish -> fish.getGender().equals(Gender.FEMALE)).count());

            }

        }
    }
}