import java.util.Arrays;

public class Problem5_FantasyLeagueAutoDraft {

    static class Player implements Comparable<Player> {

        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name, int matchesPlayed,
                      double battingAverage, boolean injured) {

            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        public String getName() {
            return name;
        }

        public int getMatchesPlayed() {
            return matchesPlayed;
        }

        public double getBattingAverage() {
            return battingAverage;
        }

        public boolean isInjured() {
            return injured;
        }

        // Experience-only rule
        static boolean isDraftable(int matchesPlayed) {
            return matchesPlayed >= 10;
        }

        // Combined matches + fitness rule
        static boolean isDraftable(int matchesPlayed, boolean injured) {
            return matchesPlayed >= 5 && !injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(
                other.battingAverage,
                this.battingAverage
            );
        }
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];
        int count = 0;

        for (Player player : players) {

            boolean eligible;

            if (Player.isDraftable(player.getMatchesPlayed())) {
                eligible = true;
            } else {
                eligible = Player.isDraftable(
                    player.getMatchesPlayed(),
                    player.isInjured()
                );
            }

            if (eligible) {
                draftable[count] = player;
                count++;
            }
        }

        Player[] finalList = Arrays.copyOf(draftable, count);

        Arrays.sort(finalList);

        String result = "";

        for (int i = 0; i < finalList.length; i++) {

            result += (i + 1) + ". " + finalList[i].getName();

            if (i < finalList.length - 1) {
                result += " | ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}