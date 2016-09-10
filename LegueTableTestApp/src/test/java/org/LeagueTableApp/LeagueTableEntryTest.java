package org.LeagueTableApp;

import org.junit.Test;

public class LeagueTableEntryTest {

    @Test(expected = NullPointerException.class)
    public void checkTableEntryCreationWithTeamNameIsNull(){
        new LeagueTableEntry(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativePlayedArgument() {
        new LeagueTableEntry("TestTeam", -1, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeWinsArgument() {
        new LeagueTableEntry("TestTeam",  0,-1,0,0,0,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeDrawsArgument() {
        new LeagueTableEntry("TestTeam",  0,0,-1,0,0,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeLosesArgument() {
        new LeagueTableEntry("TestTeam",  0,0,0,-1,0,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeGoalsForArgument() {
        new LeagueTableEntry("TestTeam",  0,0,0,0,-1,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeGoalsAgainstArgument() {
        new LeagueTableEntry("TestTeam",  0,0,0,0,0,-1,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativeGoalsDifferenceArgument() {
        new LeagueTableEntry("TestTeam",  0,0,0,0,0,0,-1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNegativePointsArgument() {
        new LeagueTableEntry("TestTeam",  0,0,0,0,0,0,0,-1);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreasePlayed(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", Integer.MAX_VALUE,0,0,0,0,0,0,0);
        Match match = new Match("TestTeam", "awayTeam", 0, 0);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreaseWins(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,Integer.MAX_VALUE,0,0,0,0,0,0);
        Match match = new Match("TestTeam", "awayTeam", 1, 0);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreaseDraws(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,0,Integer.MAX_VALUE,0,0,0,0,0);
        Match match = new Match("TestTeam", "awayTeam", 0, 0);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreaseLoses(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,0,0,Integer.MAX_VALUE,0,0,0,0);
        Match match = new Match("TestTeam", "awayTeam", 0, 1);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreaseGoalsFor(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,0,0,0,Integer.MAX_VALUE,Integer.MAX_VALUE,0,0);
        Match match = new Match("TestTeam", "awayTeam", 1, 0);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForIncreaseGoalsAgainst(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,0,0,0,Integer.MAX_VALUE,Integer.MAX_VALUE,0,0);
        Match match = new Match("TestTeam", "awayTeam", 0, 1);
        leagueTableEntry.processMatch(match);
    }

    @Test(expected = ArithmeticException.class)
    public void checkOverflowForAddPoints(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("TestTeam", 0,0,0,0,0,0,0,Integer.MAX_VALUE);
        Match match = new Match("TestTeam", "awayTeam", 0, 0);
        leagueTableEntry.processMatch(match);
    }


}