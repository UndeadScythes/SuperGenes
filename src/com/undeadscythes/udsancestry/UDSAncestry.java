package com.undeadscythes.udsancestry;

import com.undeadscythes.udsancestry.exceptions.PropertyNotSetException;
import com.undeadscythes.udsancestry.entities.citeables.families.FamilyProperty;
import com.undeadscythes.udsancestry.entities.citeables.families.Family;
import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.entities.gedcom.GEDCOM;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UDSAncestry {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GEDCOM gedcom = new GEDCOM(new File("fam.ged"));
        gedcom.load(false);
        for(Family family : gedcom.getFamilies()) {
            System.out.print(family.getID() + ":");
            try {
                System.out.print(gedcom.getIndividual(family.getPropertyValue(FamilyProperty.HUSBAND)).getID());
            } catch (PropertyNotSetException ex) {
                try {
                    System.out.print(gedcom.getIndividual(family.getPropertyValue(FamilyProperty.WIFE)).getID());
                } catch (PropertyNotSetException ex2) {}
            }
            try {
                System.out.print("-" + gedcom.getIndividual(family.getPropertyValue(FamilyProperty.WIFE)).getID());
            } catch (PropertyNotSetException ex2) {}
            String children = "";
            for(Property child : family.getChildren()) {
                children = children + "," + child.getValue();
            }
            if(!children.isEmpty()) {
                System.out.print("{" + children.replaceFirst(",", "") + "}");
            }
            System.out.println();
        }
    }
}
