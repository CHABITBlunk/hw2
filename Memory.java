package cs250.hw2;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class Memory {

  private static volatile long ramRunningTotal;
  private static final int NS_IN_S = 1000000000;

  public static void main(String[] args) {
    int size = Integer.valueOf(args[0]);
    int experiments = Integer.valueOf(args[1]);
    int seed = Integer.valueOf(args[2]);

    // Task 1
    System.out.println("Task 1");
    taskOne(size, experiments);

    // Task 2
    System.out.println("\nTask 2");
    taskTwo(size, experiments, seed);

    // Task 3
    System.out.println("\nTask 3");
    taskThree(size, experiments);
  }

  public static void taskOne(int size, int experiments) {
    // cache
    long runningTotal = 0;
    double totalTime = (double) System.nanoTime();
    for (int j = 0; j < experiments; j++) {
      if (size % 2 == 0) {
        for (int i = 0; i < size; i++) {
          runningTotal += size;
        }
      } else {
        for (int i = 0; i < size; i++) {
          runningTotal -= size;
        }
      }
    }
    totalTime = ((System.nanoTime() - totalTime) / experiments) / NS_IN_S;
    System.out.printf("Regular: %.5f seconds\n", totalTime);

    // ram
    ramRunningTotal = 0;
    double ramTotalTime = (double) System.nanoTime();
    for (int j = 0; j < experiments; j++) {
      if (size % 2 == 0) {
        for (int i = 0; i < size; i++) {
          ramRunningTotal += size;
        }
      } else {
        for (int i = 0; i < size; i++) {
          ramRunningTotal -= size;
        }
      }
    }
    ramTotalTime = ((System.nanoTime() - ramTotalTime) / experiments) / NS_IN_S;
    System.out.printf("Volatile: %.5f seconds\n", ramTotalTime);
    System.out.printf("Avg regular sum: %d\nAvg volatile sum: %d\n", runningTotal, ramRunningTotal);
  }

  public static void taskTwo(int size, int experiments, int seed) {
    Integer[] randomIntegers = new Integer[size];
    Random random = new Random(seed);
    // populate array
    for (int i = 0; i < size; i++) {
      randomIntegers[i] = random.nextInt();
    }
    double sum = 0;
    double knownElementAccessTime = (double) System.nanoTime();
    for (int i = 0; i < experiments; i++) {
      // access known element in first 10% of array
      sum += randomIntegers[0];
    }
    knownElementAccessTime = (double) (System.nanoTime() - knownElementAccessTime) / experiments;
    double randomElementAccessTime = (double) System.nanoTime();
    for (int i = 0; i < experiments; i++) {
      // access random element in last 10% of array
      sum += randomIntegers[(int) Math.round(size * ((random.nextDouble() * 0.1) + 0.9))];
    }
    randomElementAccessTime = (double) (System.nanoTime() - randomElementAccessTime) / experiments;
    System.out.printf("Avg time to access known element: %.2f nanoseconds\n", knownElementAccessTime);
    System.out.printf("Avg time to access random element: %.2f nanoseconds\n", randomElementAccessTime);
    System.out.printf("Sum: %.2f\n", sum);
  }

  public static void taskThree(int size, int experiments) {
    // instantiate
    TreeSet<Integer> set = new TreeSet<>();
    LinkedList<Integer> list = new LinkedList<>();
    // populate
    for (int i = 0; i < size; i++) {
      set.add(i);
      list.add(i);
    }
    double elapsedTime;
    // experiment - set
    elapsedTime = (double) System.nanoTime();
    for (int i = 0; i < experiments; i++) {
      set.contains((int) (Math.random() * size));
    }
    elapsedTime = (System.nanoTime() - elapsedTime) / experiments;
    System.out.printf("Avg time to find in set: %.2f nanoseconds\n", elapsedTime);
    // experiment - list
    elapsedTime = (double) System.nanoTime();
    for (int i = 0; i < experiments; i++) {
      list.contains((int) (Math.random() * size));
    }
    elapsedTime = (System.nanoTime() - elapsedTime) / experiments;
    System.out.printf("Avg time to find in list: %.2f nanoseconds\n", elapsedTime);
  }

}
