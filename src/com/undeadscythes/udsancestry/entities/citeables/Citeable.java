package com.undeadscythes.udsancestry.entities.citeables;

import java.util.*;
import com.undeadscythes.udsancestry.entities.Entity;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Citeable extends Entity {
    private ArrayList<Citation> citations = new ArrayList<Citation>(0);

    public Citeable() {}

    public void addCitation(String level, Record record) {
        citations.add(new Citation(new RecordSchedule(level, record)));
    }

    @Override
    public void debug(String prefix) {
        super.debug(prefix);
        if(!citations.isEmpty()) {
            System.out.println(prefix + "- CITATIONS:");
            for(Citation source : citations) {
                source.debug(prefix + "   ");
            }
        }
    }
}
