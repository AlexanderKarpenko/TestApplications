package org.LeagueTableApp;

import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;

public class LeagueTableEntry
{
    private static final int WIN_POINTS = 3 ;
    private static final int DRAW_POINTS = 1 ;
    private static final int LOSE_POINTS = 0 ;
    private final String teamName;
    private final AtomicInteger played;
    private final AtomicInteger won;
    private final AtomicInteger drawn;
    private final AtomicInteger lost;
    private final AtomicInteger goalsFor;
    private final AtomicInteger goalsAgainst;
    private final AtomicInteger points;

    public LeagueTableEntry( String teamName){
        this(teamName,0,0,0,0,0,0,0,0);
    }

    /**
     * Represents a record with statistics in LeagueTable for a team.
     *
     * @param goalDifference is ignored as it can be calculated from goalsFor and goalsAgainst
     * @throws IllegalArgumentException if int arguments are negative
     * @throws NullPointerException if team name argument is null.
     */
    public LeagueTableEntry( String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst, int goalDifference, int points )
    {
        this.teamName = teamName;
        this.played = new AtomicInteger(played);
        this.won = new AtomicInteger(won);
        this.drawn = new AtomicInteger(drawn);
        this.lost = new AtomicInteger(lost);
        this.goalsFor = new AtomicInteger(goalsFor);
        this.goalsAgainst = new AtomicInteger(goalsAgainst);
        this.points = new AtomicInteger(points);

        validateArguments(teamName, played, won, drawn, lost, goalsFor, goalsAgainst, goalDifference, points );
    }

    private void validateArguments(String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst, int goalDifference, int points ) {
        requireNonNull(teamName, "Team name value is null.");

        validateIntArgument(played, "played games");
        validateIntArgument(won, "wins");
        validateIntArgument(drawn, "draws");
        validateIntArgument(lost, "looses");
        validateIntArgument(goalsFor, "goals for");
        validateIntArgument(goalsAgainst, "goals against");
        validateIntArgument(goalDifference, "goal difference");
        validateIntArgument(points, "points");

    }

    private void validateIntArgument(int argument, String argumentName) {
        if (argument < 0) {
            throw new IllegalArgumentException(String.format("Number of %s is negative.", argumentName));
        }
    }

    /**
     * @throws ArithmeticException is thrown if there is an integer overflow.
     */
    public void processMatch(Match match) {
        increasePlayed();
        increaseGoalsFor(match.getTeamGoalsFor(teamName));
        increaseGoalsAgainst(match.getTeamGoalsAgainst(teamName));
        if (match.isDraw()) {
            increaseDraws();
            addPoints(DRAW_POINTS);
        } else if (match.isTeamWinner(teamName)){
            increaseWins();
            addPoints(WIN_POINTS);
        } else {
            increaseLoses();
            addPoints(LOSE_POINTS);
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalsFor() {
        return goalsFor.get();
    }

    public int getGoalDifference() {
        return goalsFor.get()-goalsAgainst.get();
    }

    public int getPoints() {
        return points.get();
    }

    public int getPlayed() {
        return played.get();
    }

    private void increasePlayed(){
        checkOverflow(played.get(), 1);
        played.incrementAndGet();
    }

    private void increaseWins(){
        checkOverflow(won.get(),1);
        won.incrementAndGet();
    }

    private void addPoints(int points) {
        checkOverflow(this.points.get(), points);
        this.points.addAndGet(points);
    }

    private void increaseDraws() {
        checkOverflow(drawn.get(), 1);
        drawn.incrementAndGet();
    }

    private void increaseLoses() {
        checkOverflow(lost.get(),1);
        lost.incrementAndGet();
    }

    private void increaseGoalsFor(int goals){
        checkOverflow(goalsFor.get(), goals);
        goalsFor.addAndGet(goals);
    }

    private void increaseGoalsAgainst(int goals){
        checkOverflow(goalsAgainst.get(), goals);
        goalsAgainst.addAndGet(goals);
    }

    private void checkOverflow(int x, int y) {
        Math.addExact(x, y);
    }

    @Override
    public String toString() {
        return "org.LeagueTableApp.LeagueTableEntry{" +
                "teamName='" + teamName + '\'' +
                ", played=" + played +
                ", won=" + won +
                ", drawn=" + drawn +
                ", lost=" + lost +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDifference=" + (goalsFor.get()-goalsAgainst.get()) +
                ", points=" + points +
                '}';
    }

}

