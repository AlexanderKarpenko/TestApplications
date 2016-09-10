Java Developer Programming Test

Consider a league table for football. Each team plays a number of matches and the results of each match build the table. Given the following code below as a starting point build the LeagueTable class that can take a list of completed matches and produce a sorted list of LeagueTableEntry objects.

LeagueTableEntry objects are sorted by points, goal difference, goals for and then team names. The normal rules for scoring points apply.

Your code will be run through a series of JUnit tests to validate the implementation so it is important that method signatures are not changed. You will also be assessed on code quality and clarity.

Please submit what you consider to be production quality code.

Match.java

public class Match
{
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    
    public Match( final String homeTeam, final String awayTeam, final int homeScore, final int awayScore )
    {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
    
    public String getHomeTeam()
    {
        return homeTeam;
    }
    
    public String getAwayTeam()
    {
        return awayTeam;
    }
    
    public int getHomeScore()
    {
        return homeScore;
    }
    
    public int getAwayScore()
    {
        return awayScore;
    }
}

LeagueTableEntry.java

public class LeagueTableEntry
{
    private String teamName;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int points;
    
    public LeagueTableEntry( String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst, int goalDifference, int points )
    {
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
    }
}

LeagueTable.java

import java.util.List;

public class LeagueTable
{
    /**
     * Create a league table from the supplied list of match results
     * 
     * @param matches
     */
    public LeagueTable( final List<Match> matches )
    {
        
    }
    
    /**
     * Get the ordered list of league table entries for this league table.
     * 
     * @return
     */
    public List<LeagueTableEntry> getTableEntries()
    {
        return null;
    }
}

