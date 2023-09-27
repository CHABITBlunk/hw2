package cs250.hw2;

public class Memory {

  public static void main(String[] args) {
    int size = Integer.valueOf(args[0]);
    int experiments = Integer.valueOf(args[1]);
    int seed = Integer.valueOf(args[2]);
    ramVsCacheSpeeds(size, experiments);
  }

  public static void ramVsCacheSpeeds(int size, int experiments) {
    long runningTotal = 0;
    long totalTime = 0;
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
  }

}
