package com.undeadscythes.supergenes.holder;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.tipscript.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Holder {
    /**
     *
     */
    protected Tag tag;
    /**
     *
     */
    protected String value;
    /**
     *
     */
    protected final List<Holder> list = new ArrayList<Holder>(0);

    /**
     *
     */
    protected Holder() {}

    /**
     *
     * @return
     */
    public Tag getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     */
    public void setTag(final Tag tag) {
        this.tag = tag;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     *
     * @param line
     */
    public void set(final LineStruct line) {
        tag = line.tag;
        value = line.value;
    }

    /**
     *
     * @param record
     */
    public void set(final Record record) {
        set(record.popHead());
        while (record.hasNext()) {
            final Record next = record.pullCluster();
            if(next.getType().equals(TagType.EVENT)) {
                list.add(new Event(next));
            } else {
                list.add(new Data(next));
            }
        }
    }

    /**
     *
     * @return
     */
    public int parseID() {
        return UniqueHolder.parseID(getValue());
    }

    /**
     *
     * @param tag
     * @return
     */
    public Holder getTag(final Tag tag) {
        if (getList(tag).isEmpty()) throw new TagNotSetException(tag);
        return getList(tag).get(0);
    }

    /**
     *
     * @param tag
     */
    public void removeTag(final Tag tag) {
        for (final Iterator<Holder> i = list.iterator(); i.hasNext();) {
            if (i.next().tag.equals(tag)) i.remove();
        }
    }

    /**
     *
     * @param tag
     * @return
     */
    public String getValue(final Tag tag) {
        return getTag(tag).value;
    }

    /**
     *
     * @param tag
     * @return
     */
    public List<Holder> getList(final Tag tag) {
        final List<Holder> matches = new ArrayList<Holder>(0);
        for (Holder holder : list) {
            if (holder.tag.equals(tag)) {
                matches.add(holder);
            }
        }
        return matches;
    }

    /**
     *
     * @return
     */
    public List<Holder> getList() {
        return unmodifiableList(list);
    }

    /**
     *
     * @param type
     * @return
     */
    public List<Holder> getList(final TagType type) {
        final List<Holder> matches = new ArrayList<Holder>(0);
        for (Holder holder : list) {
            if (holder.getTag().getType().equals(type)) {
                matches.add(holder);
            }
        }
        return matches;
    }

    /**
     *
     * @param type
     * @param comp
     * @return
     */
    public List<Holder> getList(final TagType type, final Comparator<Holder> comp) {
        final List<Holder> matches = getList(type);
        sort(matches, comp);
        return matches;
    }

    /**
     *
     * @param out
     * @param indent
     */
    public void dump(final TipScript out, final String indent) {
        out.printf(indent + (tag == null ? "----" : "[" + tag.getType() + "] " + tag.getFriendly()) + ": " + value);
        for (Holder holder : list) {
            holder.dump(out, "  " + indent);
        }
    }
}
