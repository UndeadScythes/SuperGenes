package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.structure.*;
import com.undeadscythes.metaturtle.*;

/**
 * @author UndeadScythes
 */
public class ResToCen extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        for (UniqueMeta u : geneBase.getUniqueMeta(GEDTag.INDI)) {
            for (Metadata h : u.getData(GEDTag.RESI.toString())) {
                final Event e = ((Event)h);
                switch (e.getDate().year) {
                    case 1841:
                        e.setDate(new Date(1841, 6, 6));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1851:
                        e.setDate(new Date(1851, 3, 30));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1861:
                        e.setDate(new Date(1861, 4, 7));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1871:
                        e.setDate(new Date(1871, 4, 2));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1881:
                        e.setDate(new Date(1881, 4, 3));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1891:
                        e.setDate(new Date(1891, 3, 5));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1901:
                        e.setDate(new Date(1901, 3, 31));
                        e.setTag(GEDTag.CENS);
                        break;
                    case 1911:
                        e.setDate(new Date(1911, 3, 2));
                        e.setTag(GEDTag.CENS);
                        break;
                }
            }
        }
        out.println("Converted all residence events to census events.");
        return true;
    }
}
