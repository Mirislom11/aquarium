package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Fish extends Thread {

    private final Long lifeExpectancy;
    private final Long birthTime;
    private final Gender gender;
    /**
     * Coordinates for meeting
     */
    private Integer x = ThreadLocalRandom.current().nextInt(1, 2);
    private Integer y = ThreadLocalRandom.current().nextInt(1, 2);

    private boolean isAlive;

    private List<Fish> parents = new ArrayList<>();

    public Fish() {
        this.lifeExpectancy = ThreadLocalRandom.current().nextLong(10 * 1000, 60 * 1000);
        this.birthTime = System.currentTimeMillis();
        this.gender = ThreadLocalRandom.current().nextInt(1, 3) == 1 ? Gender.MALE : Gender.FEMALE;
        this.isAlive = true;
    }

    @Override
    public void run() {
        while (lifeExpectancy > (System.currentTimeMillis() - birthTime)) {
            x = ThreadLocalRandom.current().nextInt(1, 2);
            y = ThreadLocalRandom.current().nextInt(1, 2);
        }
        System.out.println("Fish by ID: " + super.getId() + " was died");
        this.isAlive = false;
    }

    public Fish meetingFishes(Fish fish) {
        if ((this.getX().equals(fish.getX()) && this.getY().equals(fish.getY())) &&
                (!this.getGender().equals(fish.getGender())) &&
                parents.stream().noneMatch(this::equals)) {
            Fish newFish = new Fish();
            newFish.addToParents(this);
            newFish.addToParents(fish);
            return fish;
        } else {
            return null;
        }
    }

    public Long getLifeExpectancy() {
        return lifeExpectancy;
    }

    public Long getBirthTime() {
        return birthTime;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void addToParents(Fish fish) {
        this.parents.add(fish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return super.getId()==fish.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lifeExpectancy, birthTime, gender, x, y, isAlive, parents);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + super.getId() + ", " +
                "sex='" + gender + '\'' +
                ", birthTime=" + birthTime +
                ", lifespan=" + lifeExpectancy +
                ", isLive=" + isAlive +
                '}';
    }
}


// (int) (Math.random() * 2 + 1) == 1 ? Gender.MALE : Gender.FEMALE
