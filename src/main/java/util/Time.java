package util;

public class Time {
  public static float timeStarted = System.nanoTime();

  // Time difference from the application started to till now
  public static float getTime() {
      return (float)((System.nanoTime() - timeStarted) * 1E-9);
  }
}
