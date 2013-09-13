package com.undeadscythes.supergenes.gedcom;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.family.*;
import static com.undeadscythes.supergenes.gedcom.GEDCOMTag.*;
import com.undeadscythes.supergenes.header.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import com.undeadscythes.tipscript.*;
import java.io.*;
import static java.lang.Integer.*;
import java.util.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author UndeadScythes
 */
public class GEDCOM {
    private Transmission transmission;
    private Header header;
    private final Map<GEDCOMTag, Map<Integer, UniqueHolder>> data;
    private String name;

    /**
     *
     */
    public GEDCOM() {
        data = new EnumMap<GEDCOMTag, Map<Integer, UniqueHolder>>(GEDCOMTag.class);
        for (GEDCOMTag tag : values()) {
            if (tag.equals(GEDCOMTag.HEADER)) continue;
            data.put(tag, new HashMap<Integer, UniqueHolder>(0));
        }
    }

    /**
     *
     * @param file
     * @param out
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load(final File file, final TipScript out) throws FileNotFoundException, IOException {
        name = file.getName().replace(".ged", "");
        BufferedReader in = new BufferedReader(new FileReader(file));
        transmission = new Transmission();
        Record record = new Record();
        Set<String> unused = new HashSet<String>(0);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] lineSplit = line.trim().split(" ");
            int level = parseInt(lineSplit[0]);
            String tag = lineSplit[1];
            String value = "";
            if (lineSplit.length > 2) {
                value = line.replace(level + " " + tag + " ", "").trim();
            }
            if (tag.startsWith("@")) {
                value = tag;
                tag = lineSplit[2];
            }
            try {
                if (level == 0 && !record.isEmpty()) {
                    transmission.add(record);
                    record = new Record();
                }
                record.add(new LineStruct(level, MasterTag.getByTag(tag), value));
            } catch (NoValidTagException ex) {
                unused.add(ex.tag);
            }
        }
        if (!unused.isEmpty()) {
            out.println("Unloaded tags: " + join(unused.toArray(), " "));
        }
    }

    /**
     *
     * @param out
     */
    public void parse(final TipScript out) {
        Set<String> unused = new HashSet<String>(0);
        for (Record record : transmission.copy()) {
            try {
                GEDCOMTag tag = (GEDCOMTag)record.getTag();
                switch (tag) {
                    case HEADER:
                        header = new Header(record);
                        break;
                    case FAMILY:
                        Family family = new Family(record);
                        data.get(tag).put(family.getID(), family);
                        break;
                    case INDIVIDUAL:
                        Individual individual = new Individual(record);
                        data.get(tag).put(individual.getID(), individual);
                        break;
                    case TRAILER:
                        break;
                    default:
                        unused.add(tag.getTag());
                }
            } catch (ClassCastException ex) {
                unused.add(record.getTag().getTag());
            }
        }
        if (!unused.isEmpty()) {
            out.println("Unparsed tags: " + join(unused.toArray(), " "));
        }
        for (UniqueHolder holder : data.get(GEDCOMTag.FAMILY).values()) {
            Family family = (Family)holder;
            family.linkMembers(data.get(GEDCOMTag.INDIVIDUAL));
        }
    }

    /**
     *
     * @return
     */
    public Header getHeader() {
        return header;
    }

    /**
     *
     * @param tag
     * @param ID
     * @return
     */
    public UniqueHolder get(final GEDCOMTag tag, final int ID) {
        return data.get(tag).get(ID);
    }

    /**
     *
     * @param tag
     * @return
     */
    public Collection<UniqueHolder> get(final GEDCOMTag tag) {
        return data.get(tag).values();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Transmission getMaster() {
        return transmission.copy();
    }

    /**
     *
     * @param out
     */
    public void dump(final TipScript out) {
        header.dump(out, "");
        for (Map<Integer, UniqueHolder> map : data.values()) {
            for (UniqueHolder holder : map.values()) {
                holder.dump(out, "");
            }
        }
    }
}
