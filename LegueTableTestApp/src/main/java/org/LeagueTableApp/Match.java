package org.LeagueTableApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.*;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;
    private final Map<String, TeamGoalsPair> teamGoalsPairMap = new HashMap<>(2);

    /**
     * Contains results of match.
     *
     * @throws NullPointerException is thrown if team name argument is null
     * @throws IllegalArgumentException is thrown if integer arguments are negative
     */
    public Match(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
        validateArguments(homeTeam, awayTeam, homeScore, awayScore);

        teamGoalsPairMap.put(homeTeam, new TeamGoalsPair(homeScore,awayScore));
        teamGoalsPairMap.put(awayTeam, new TeamGoalsPair(awayScore, homeScore));

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    private void validateArguments(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
        requireNonNull(homeTeam, "HomeTeam argument is null.");
        requireNonNull(awayTeam, "AwayTeam argument is null.");

        if (homeScore<0 || awayScore<0){
            throw new IllegalArgumentException("Score can't be negative.");
        }
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public Set<String> getTeams(){
        return teamGoalsPairMap.keySet();
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public Boolean isDraw() {
        return homeScore == awayScore;
    }

    public Boolean isTeamWinner(String teamName) {
        return !isDraw() && teamName.equals(getWinner());

    }

    private String getWinner() {
        assert homeScore != awayScore;
        return homeScore > awayScore ? homeTeam : awayTeam;
    }

    /**
     * @throws NullPointerException if argument is null
     */
    public int getTeamGoalsFor(String teamName) {
        TeamGoalsPair pair = getTeamGoalsTuple(teamName);
        return pair.getGoalsFor();
    }

    public int getTeamGoalsAgainst(String teamName){
        TeamGoalsPair pair = getTeamGoalsTuple(teamName);
        return pair.getGoalsAgainst();
    }

    private TeamGoalsPair getTeamGoalsTuple(String teamName) {
        requireNonNull(teamName, "Team name argument is null.");
        TeamGoalsPair teamGoalsPair = teamGoalsPairMap.get(teamName);
        requireNonNull(teamGoalsPair, "Invalid team name for match: " + teamName);
        return teamGoalsPair;
    }

    private static class TeamGoalsPair {
        private final int goalsFor;
        private final int goalsAgainst;

        public TeamGoalsPair(int goalsFor, int goalsAgainst) {
            this.goalsFor = goalsFor;
            this.goalsAgainst = goalsAgainst;
        }

        public int getGoalsFor() {
            return goalsFor;
        }

        public int getGoalsAgainst() {
            return goalsAgainst;
        }
    }
}

