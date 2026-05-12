import java.util.HashMap;

public class DynamicProgramming {

    // Every day for the rest of the year, you're going to be given a choice between
    // two jobs to do:
    // one that is LOW stress, and one that is HIGH stress. Each job pays out a
    // dollar amount;
    // *usually* the high stress jobs pay more. However, after doing a high stress
    // job, you need to
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs,
    // what is the most amount of money you can get?

    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
        return hiLoStressHelper(lowPayouts, highPayouts, new HashMap<Integer, Integer>(), 0);
    }

    public static int hiLoStressHelper(int[] lowPayouts, int[] highPayouts, HashMap<Integer, Integer> expectedPayout,
            int day) {
        if (day >= lowPayouts.length) {
            return 0;
        }

        if (expectedPayout.get(day) != null) {
            return expectedPayout.get(day);
        }

        int highPayout = highPayouts[day] + hiLoStressHelper(lowPayouts, highPayouts, expectedPayout, day + 2);
        int lowPayout = lowPayouts[day] + hiLoStressHelper(lowPayouts, highPayouts, expectedPayout, day + 1);

        int maxPayout = highPayout;
        if (lowPayout > highPayout) {
            maxPayout = lowPayout;
        }

        expectedPayout.put(day, maxPayout);
        return maxPayout;
    }

    // You are partaking in a scavenger hunt!
    // You've gotten a secret map to find many of the more difficult
    // items, but they are only available at VERY specific times at
    // specific places. You have an array, times[], that lists at which
    // MINUTE an item is available, in increasing order.
    // Items in the ScavHunt are worth varying numbers of points.
    // You also have an array, points[], same length as times[],
    // that lists how many points each of the corresponding items is worth.
    // Problem is: to get from one location to the other takes 5 minutes,
    // so if there is an item, for example, available at time 23 and another
    // at time 27, it's just not possible for you to make it to both: you'll
    // have to choose!
    // Write a method that returns the maximum POINTS you can get.
    public static int scavHunt(int[] times, int[] points) {
        return maxReward(times, points, 0);
    }

    // This finds the max reward starting at a given index and then it returns that,
    // so eventually it will return the max reward for the whole scav hunt
    public static int maxReward(int[] times, int[] points, int index) {
        if (times.length - index - 1 == 0) {
            return points[index];
        } else if (times.length - index - 1 < 0) {
            return 0;
        } else {
            int newIndex = -1;
            if (times.length - index - 1 > 5) {
                for (int i = 1; i < 6; i++) {
                    if (times[i + index] > (times[index] + 4)) {
                        newIndex = i + index;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < times.length - index; i++) {
                    if (times[i + index] > (times[index] + 4)) {
                        newIndex = i + index;
                        break;
                    }
                }
            }
            int bigNumberLeft = 0;
            if (newIndex == -1) {
                for (int i = index; i < points.length; i++) {
                    if (points[i] > bigNumberLeft) {
                        bigNumberLeft = points[i];
                    }
                }
                return bigNumberLeft;
            }
            int taken = points[index] + maxReward(times, points, newIndex);
            int notTaken = maxReward(times, points, index + 1);
            if (taken > notTaken) {
                return taken;
            } else {
                return notTaken;
            }
        }
    }

    /*
     * Uses memoization to calculate the route which grants the most cookies,
     * starting at [0][0], only going right or down at each point
     */
    public static int dynamicCookies(int[][] cookieGrid) {

    }

}
