package org.LeagueTableApp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LeagueTableTest {

    @Test
    public void checkCupSystem(){
        Match match1 = new Match("Arsenal", "Chelsey", 0,0);
        Match match2 = new Match("Arsenal", "Liverpool", 0,0);
        Match match3 = new Match("Chelsey", "Liverpool", 0,0);
        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);

        LeagueTable leagueTable = new LeagueTable(matchList);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertEquals(tableEntries.size(),3);
    }

    @Test
    public void checkHighestPointsOrder(){
        Match match1 = new Match("Arsenal", "Chelsey", 2,1);
        Match match2 = new Match("Arsenal", "Liverpool", 1,0);
        Match match3 = new Match("Chelsey", "Liverpool", 1,0);
        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);

        LeagueTable leagueTable = new LeagueTable(matchList);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        String errorMessage = "\nHighest points ordering: \n" + tableEntries;
        assertEquals(errorMessage, tableEntries.get(0).getTeamName(),"Arsenal");
        assertEquals(errorMessage, tableEntries.get(1).getTeamName(),"Chelsey");
        assertEquals(errorMessage, tableEntries.get(2).getTeamName(),"Liverpool");
    }

    @Test
    public void checkGoalsDifferenceOrder(){
        Match match1 = new Match("Arsenal", "Chelsey", 0,0);
        Match match2 = new Match("Arsenal", "Liverpool", 3,1);
        Match match3 = new Match("Chelsey", "Liverpool", 2,1);
        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);

        LeagueTable leagueTable = new LeagueTable(matchList);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        String errorMessage = "\nGoals Difference ordering: \n" + tableEntries;
        assertEquals(errorMessage, tableEntries.get(0).getTeamName(),"Arsenal");
        assertEquals(errorMessage, tableEntries.get(1).getTeamName(),"Chelsey");
        assertEquals(errorMessage, tableEntries.get(2).getTeamName(),"Liverpool");
    }

    @Test
    public void checkGoalsForOrdering(){
        Match match1 = new Match("Arsenal", "Chelsey", 0,0);
        Match match2 = new Match("Arsenal", "Liverpool", 3,2);
        Match match3 = new Match("Chelsey", "Liverpool", 2,1);
        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);

        LeagueTable leagueTable = new LeagueTable(matchList);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        String errorMessage = "\nGoals for ordering: \n" + tableEntries;
        assertEquals(errorMessage, tableEntries.get(0).getTeamName(),"Arsenal");
        assertEquals(errorMessage, tableEntries.get(1).getTeamName(),"Chelsey");
        assertEquals(errorMessage, tableEntries.get(2).getTeamName(),"Liverpool");
    }

    @Test
    public void checkTeamNameOrdering(){
        Match match1 = new Match("Arsenal", "Chelsey", 0,0);
        Match match2 = new Match("Arsenal", "Liverpool", 0,0);
        Match match3 = new Match("Chelsey", "Liverpool", 0,0);
        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);

        LeagueTable leagueTable = new LeagueTable(matchList);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        String errorMessage = "\nTeam name ordering: \n" + tableEntries;
        assertEquals(errorMessage, tableEntries.get(0).getTeamName(),"Arsenal");
        assertEquals(errorMessage, tableEntries.get(1).getTeamName(),"Chelsey");
        assertEquals(errorMessage, tableEntries.get(2).getTeamName(),"Liverpool");
    }
}