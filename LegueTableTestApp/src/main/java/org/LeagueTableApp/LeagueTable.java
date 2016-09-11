package org.LeagueTableApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class LeagueTable
{
    private static final Logger logger = LogManager.getLogger(LeagueTable.class);
    private final List<Match> matches;
    private final Map<String,LeagueTableEntry> leagueTableEntries;

    /**
     * Create a league table from the supplied list of match results
     */
    public LeagueTable( final List<Match> matches )
    {
        logger.debug("LeagueTable constructor received following argument: " + matches);
        this.matches = matches;
        leagueTableEntries = new ConcurrentHashMap<>(matches.size());
        createAndCalculateLeagueTableEntries();
    }

    private void createAndCalculateLeagueTableEntries() {
        matches.parallelStream().forEach(this::createAndCalculateEntriesForMatch);
        logger.info("LeagueTableEntries were created and successfully calculated.");
    }

    private void createAndCalculateEntriesForMatch(Match match) {
        match.getTeams().forEach((teamName) -> createAndCalculateEntry(match, teamName));
    }

    private void createAndCalculateEntry(Match match, String teamName) {
        LeagueTableEntry entry = leagueTableEntries.computeIfAbsent(teamName, LeagueTableEntry::new);
        entry.processMatch(match);
    }

    /**
     * Get the ordered list of league table entries for this league table.
     * Ordering is done from highest points to lowest.
     *
     */
    public List<LeagueTableEntry> getTableEntries()
    {
        return leagueTableEntries.values().stream()
                .sorted(comparingInt(LeagueTableEntry::getPoints)
                        .thenComparingInt(LeagueTableEntry::getGoalDifference)
                        .thenComparingInt(LeagueTableEntry::getGoalsFor).reversed()
                        .thenComparing(LeagueTableEntry::getTeamName))
                .collect(toList());
    }
}

