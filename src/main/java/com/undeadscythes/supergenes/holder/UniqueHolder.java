package com.undeadscythes.supergenes.holder;

import com.undeadscythes.gedform.*;
import static java.lang.Integer.*;

/**
 * @author UndeadScythes
 */
public class UniqueHolder extends Holder {
    public static int parseID(String ID) {
        if (!ID.matches("@[a-zA-Z][-0-9]+@")) return 0;
        return parseInt(ID.replaceAll("@", "").substring(1));
    }

    protected int ID;

    public int getID() {
        return ID;
    }

    @Override
    public void set(Record record) {
        ID = parseID(record.get(0).value);
        super.set(record);
    }
}
