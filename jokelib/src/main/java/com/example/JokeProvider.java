package com.example;

import java.util.Random;

public class JokeProvider {
    public String getJoke() {
        Random rand = new Random();
        int randomValue = rand.nextInt(2) + 1;

        switch (randomValue) {
            case 1:
                return Jokes.joke1;

            case 2:
                return Jokes.joke2;

            default:
                return Jokes.jokeDefault;
        }
    }
}
