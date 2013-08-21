package com.undeadscythes.udsancestry.entities.gedcom;

import com.undeadscythes.udsancestry.entities.citeables.repositories.*;
import com.undeadscythes.udsancestry.entities.citeables.properties.*;
import com.undeadscythes.udsancestry.entities.citeables.families.*;
import java.io.*;
import java.util.*;
import com.undeadscythes.udsancestry.entities.*;
import com.undeadscythes.udsancestry.entities.citeables.individuals.*;
import com.undeadscythes.udsancestry.entities.sources.*;
import com.undeadscythes.udsancestry.record.*;

/**
 * @author UndeadScythes
 */
public class GEDCOM extends Entity {
    private File file;
    private HashMap<String, Individual> individuals = new HashMap<String, Individual>(0);
    private HashMap<String, Family> families = new HashMap<String, Family>(0);
    private HashMap<String, Repository> repositories = new HashMap<String, Repository>(0);
    private HashMap<String, Source> sources = new HashMap<String, Source>(0);
    private String line;
    private BufferedReader in;

    public GEDCOM(File file) {
        this.file = file;
    }

    public Collection<Individual> getIndividuals() throws FileNotFoundException, IOException {
        if(individuals.isEmpty()) {
            load(false);
        }
        return individuals.values();
    }

    public Individual getIndividual(String id) {
        return individuals.get(id);
    }

    public Collection<Family> getFamilies() throws FileNotFoundException, IOException {
        if(families.isEmpty()) {
            load(false);
        }
        return families.values();
    }

    public Collection<Repository> getRepositories() throws FileNotFoundException, IOException {
        if(repositories.isEmpty()) {
            load(false);
        }
        return repositories.values();
    }

    public Collection<Source> getSources() throws FileNotFoundException, IOException {
        if(sources.isEmpty()) {
            load(false);
        }
        return sources.values();
    }

    public void load(boolean debug) throws FileNotFoundException, IOException {
        try {
            in = new BufferedReader(new FileReader(file));
            line = in.readLine();
            while(line != null) {
                if(line.contains("INDI")) {
                    Individual individual = new Individual(new RecordSchedule("1 ", getRecord()));
                    individuals.put(individual.getID(), individual);
                    if(debug) individual.debug("");
                } else if(line.contains("FAM")) {
                    Family family = new Family(new RecordSchedule("1 ", getRecord()));
                    families.put(family.getID(), family);
                    if(debug) family.debug("");
                } else if(line.contains("REPO")) {
                    Repository repository = new Repository(new RecordSchedule("1 ", getRecord()));
                    repositories.put(repository.getID(), repository);
                    if(debug) repository.debug("");
                } else if(line.contains("SOUR")) {
                    Source source = new Source(new RecordSchedule("1 ", getRecord()));
                    sources.put(source.getID(), source);
                    if(debug) source.debug("");
                } else if(line.contains("HEAD")) {
                    addProperties(new RecordSchedule("1 ", getRecord()));
                } else if(line.contains("TRLR")) {
                    line = in.readLine();
                } else {
                    System.out.println("GEDCOM - " + line); //TODO: Remove debug message
                    line = in.readLine();
                }
            }
        } finally {
            in.close();
        }
    }

    private Record getRecord() throws IOException {
        Record record = new Record();
        record.add(line.replaceFirst("0 ", ""));
        line = in.readLine();
        while(!line.startsWith("0")) {
            record.add(line);
            line = in.readLine();
        }
        return record;
    }

    private void addProperties(RecordSchedule rs) {
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            if(record.headBegins("CHAR")) {
                addProperty(GEDCOMProperty.ENCODING, record.getHead().replace("CHAR", "").trim());
            } else if(record.headBegins("SOUR")) {
                addProperty(new Creator(new RecordSchedule(rs.nextLevel(), record)));
            } else if(record.headBegins("GEDC")) {
                addProperty(new Version(new RecordSchedule(rs.nextLevel(), record)));
            } else {
                System.out.println("RELATIVE - " + record.getHead() + " (" + record.size() + ")"); //TODO: Remove debug message
            }
        }
    }
}
