package udsancestry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class UDSAncestry {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashMap<String, Relative> relatives = new HashMap<String, Relative>();
        File path = new File("fam.ged");
        BufferedReader in = new BufferedReader(new FileReader(path));
        String nextLine;
        while((nextLine = in.readLine()) != null) {
            String[] splitLine = nextLine.split(" ", 3);
            if(splitLine[1].matches("@P[0-9]*@")) {
                if(splitLine[2].equals("INDI ")) {
                    String id = splitLine[1];
                    relatives.put(id, new Relative(in));
                    System.out.println(relatives.get(id).toString());
                }
            }
        }
        in.close();
    }
}

class Relative {
    String givenName;
    String familyName;
    String gender;
    String birthDate;
    String birthPlace;
    String deathDate;
    String deathPlace;
    String fams;
    String famc;

    public Relative(BufferedReader in) throws IOException {
        String[] splitLine;
        while(Integer.parseInt((splitLine = in.readLine().split(" ", 3))[0]) != 0) {
            if(splitLine[1].equals("NAME")) {
                if(splitLine[2].contains("/")) {
                    givenName = splitLine[2].split("/")[0].trim();
                    familyName = splitLine[2].split("/")[1];
                }
            } else if(splitLine[1].equals("SEX")) {
                gender = splitLine[2];
            } else if(splitLine[1].equals("BIRT")) {
                while(Integer.parseInt((splitLine = in.readLine().split(" ", 3))[0]) != 1) {
                    if(splitLine[1].equals("DATE")) {
                        birthDate = splitLine[2];
                    } else if(splitLine[1].equals("PLAC")) {
                        birthPlace = splitLine[2];
                    }
                    in.mark(10);
                }
                in.reset();
            } else if(splitLine[1].equals("DEAT")) {
                while(Integer.parseInt((splitLine = in.readLine().split(" ", 3))[0]) != 1) {
                    if(splitLine[1].equals("DATE")) {
                        deathDate = splitLine[2];
                    } else if(splitLine[1].equals("PLAC")) {
                        deathPlace = splitLine[2];
                    }
                    in.mark(10);
                }
                in.reset();
            } else if(splitLine[1].equals("FAMS")) {
                fams = splitLine[2];
            } else if(splitLine[1].equals("FAMC")) {
                famc = splitLine[2];
            }
            in.mark(10);
        }
        in.reset();
    }

    @Override
    public String toString() {
        return givenName + " " + familyName + ", " + gender + " was born " + birthDate + " at " + birthPlace + " and died " + deathDate + " at " + deathPlace + ".";
    }
}

class Event {
    public Event() {

    }
}