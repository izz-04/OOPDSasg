public class Match {
    private String matchName;
    private int matchId; // usused so ignore for now
    private Stadium stadium;

    public Match () {};
    public Match (String matchName, int matchId, Stadium stadium) {

        this.matchName = matchName;
        this.matchId = matchId;
        this.stadium = stadium;
    }

    public String getMatchName() {
        return matchName;
    }
    public Stadium getStadium() {
        return stadium;
    }

}
