package com.undeadscythes.supergenes.gedcom;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.evidence.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.family.*;
import static com.undeadscythes.supergenes.gedcom.GEDCOMTag.*;
import com.undeadscythes.supergenes.header.*;
import com.undeadscythes.supergenes.individual.*;
import static java.util.Arrays.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class MasterTag {
    /**
     *
     */
    public static final List<Tag> tags = new ArrayList<Tag>(0);

    static {
        tags.addAll(asList(values()));
        tags.addAll(asList(IndividualTag.values()));
        tags.addAll(asList(FamilyTag.values()));
        tags.addAll(asList(EventTag.values()));
        tags.addAll(asList(HeaderTag.values()));
        tags.addAll(asList(SourceTag.values()));
        tags.addAll(asList(GlobalTag.values()));
    }

    /**
     *
     * @param tag
     * @return
     */
    public static Tag getByTag(String tag) {
        for (Tag test : tags) {
            if (test.getTag().equals(tag)) return test;
        }
        throw new NoValidTagException(tag);
    }

    /**
     *
     * @param tag
     */
    public static void addTag(String tag) {
        tags.add(new NamedTag(tag));
    }

    private MasterTag() {}
}
