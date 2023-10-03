package cs250.hw2;

public class Memory {

  private static volatile long ramRunningTotal;

  public static void main(String[] args) {
    int size = Integer.valueOf(args[0]);
    int experiments = Integer.valueOf(args[1]);
    int seed = Integer.valueOf(args[2]);

    // Task 1
    System.out.println("Task 1");
    testSpeeds(size, experiments);
  }

  public static void testSpeeds(int size, int experiments) {
    // cache
    long runningTotal = 0;
    double totalTime = System.nanoTime();
    for (int j = 0; j < experiments; j++) {
      if (size % 2 == 0) {
        for (int i = 0; i < size; i++) {
          runningTotal -= size;
        }
      } else {
        for (int i = 0; i < size; i++) {
          runningTotal += size;
        }
      }
    }
    totalTime = (System.nanoTime() - totalTime) / experiments;
    System.out.printf("Regular: %d seconds\n", totalTime);

    ramRunningTotal = 0;
    double ramTotalTime = System.nanoTime();
    for (int j = 0; j < experiments; j++) {
      if (size % 2 == 0) {
        for (int i = 0; i < size; i++) {
          ramRunningTotal -= size;
        }
      } else {
        for (int i = 0; i < size; i++) {
          ramRunningTotal += size;
        }
      }
    }
    ramTotalTime = (System.nanoTime() - ramTotalTime) / experiments;
    System.out.printf("Volatile: %d seconds\n", ramTotalTime);
    System.out.printf("Avg regular sum: %d\nAvg volatile sum: %d", runningTotal, ramRunningTotal);
  }

}
