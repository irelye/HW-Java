package com.example.Parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingTerminalTest {

    private ParkingTerminal parking;
    private final int MAX = 20;

    @BeforeEach
    void initialize() {
        parking = new ParkingTerminal(MAX);
    }

    @Test
    void driveIn_FreePLaceExists_True() {
        assertTrue(parking.driveIn());
    }

    @Test
    void driveIn_NoFreePlaces_False() {
        for (int i = 0; i < MAX; i++) {
            parking.driveIn();
        }

        assertFalse(parking.driveIn());
    }

    @Test
    void driveIn_MoreThanMaxCarsComing_NoFreePlaces() throws InterruptedException {
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < MAX * 2; i++) {
            threads.add(new Thread(() -> {
                parking.driveIn();
            }));
            threads.get(i).start();
        }

        for (var thread: threads) {
            thread.join();
        }
        assertFalse(parking.driveIn());
    }
}