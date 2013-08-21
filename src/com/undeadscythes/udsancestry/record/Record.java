package com.undeadscythes.udsancestry.record;

import java.util.*;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class Record extends ArrayList<String> {
    public Record() {
        super(1);
    }

    public boolean headBegins(String value) {
        return super.get(0).startsWith(value);
    }

    public String getHead() {
        return super.get(0);
    }
}
