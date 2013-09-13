package com.undeadscythes.supergenes.family;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Family extends UniqueHolder {
    public Individual father = Nobody.UNKNOWN;
    public Individual mother = Nobody.UNKNOWN;
    public Map<Integer, Individual> children = new HashMap<Integer, Individual>(0);

    /**
     *
     * @param record
     */
    public Family(Record record) {
        set(record);
    }

    /**
     *
     * @param ID
     * @return
     */
    public boolean contains(int ID) {
        if (father.getID() == ID || mother.getID() == ID) return true;
        for (Individual child : children.values()) {
            if (child.getID() == ID) return true;
        }
        return false;
    }

    /**
     *
     * @param ID
     * @return
     */
    public Relation getMaritalStatus(int ID) {
        List<Holder> events = getList(FamilyEventTag.MARRIAGE);
        sort(events, new SortByDate(false));
        if (events.isEmpty()) return Relation.PARTNER;
        switch ((FamilyEventTag)events.get(0).getTag()) {
            case MARRIAGE:
                if (mother.getID() == ID) return Relation.HUSBAND;
                if (father.getID() == ID) return Relation.WIFE;
                break;
        }
        return Relation.PARTNER;
    }

    /**
     *
     * @param ID
     * @return
     */
    public Relation getRelation(int ID) {
        if (father.getID() == ID) return Relation.FATHER;
        if (mother.getID() == ID) return Relation.MOTHER;
        for (Individual child : children.values()) {
            if (child.getID() == ID) return Relation.CHILD;
        }
        return Relation.NONE;
    }

    /**
     *
     * @param group
     */
    public void addTo(FamilyGroup group) {
        Relation relation = getRelation(group.target.getID());
        Individual spouse = Nobody.NOBODY;
        switch(relation) {
            case FATHER:
                spouse = mother;
                group.children.put(spouse, new ArrayList<Individual>(0));
                break;
            case MOTHER:
                spouse = father;
                group.children.put(spouse, new ArrayList<Individual>(0));
                break;
            case CHILD:
                group.father = father;
                group.mother = mother;
                break;
        }
        for (Individual child : children.values()) {
            switch(relation) {
                case FATHER:
                case MOTHER:
                    group.children.get(spouse).add(child);
                    break;
                case CHILD:
                    group.siblings.add(child);
                    break;
            }
        }
    }

    /**
     *
     * @param tag
     * @return
     */
    public int getID(FamilyTag tag) {
        return parseID(getValue(tag));
    }

    /**
     *
     * @param individuals
     */
    public void linkMembers(Map<Integer, UniqueHolder> individuals) {
        try {
            father = (Individual)individuals.get(getID(FamilyTag.FATHER));
        } catch (TagNotSetException ex) {}
        try {
            mother = (Individual)individuals.get(getID(FamilyTag.MOTHER));
        } catch (TagNotSetException ex) {}
        for (Holder child : getList(FamilyTag.CHILD)) {
            int key = parseID(child.getValue());
            children.put(key, (Individual)individuals.get(key));
        }
    }
}
