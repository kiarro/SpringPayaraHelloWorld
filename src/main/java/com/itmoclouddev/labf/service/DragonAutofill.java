package com.itmoclouddev.labf.service;

import java.util.concurrent.ThreadLocalRandom;

import com.itmoclouddev.labf.dao.Dao;
import com.itmoclouddev.labf.dao.DaoImpl;
import com.itmoclouddev.labf.model.Coordinates;
import com.itmoclouddev.labf.model.Dragon;
import com.itmoclouddev.labf.model.DragonCave;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;

public class DragonAutofill {
    private static ThreadLocalRandom r = ThreadLocalRandom.current();

    public static void addRandomDragon() {
        try {
            Dao dao = new DaoImpl();
            dao.addDragonAndCave(new Dragon(
                    generateName(),
                    new Coordinates((float)r.nextDouble(-100, 100), (float)r.nextDouble(-100, 100)),
                    r.nextLong(1, 100000), r.nextLong(1, 100000),
                    types[r.nextInt(types.length)],
                    characters[r.nextInt(characters.length)],
                    new DragonCave((float)r.nextDouble(1, 10000), r.nextDouble(1, 1000000)), 1l)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String generateName() {
        StringBuilder name = new StringBuilder();
        int i = r.nextInt(7);

        switch (i) {
            case 0: {
                // Bob, the Bright Moon
                // <name>, the <adj> <obj>
                name.append(getNameName())
                        .append(", the ")
                        .append(getAdjName())
                        .append(" ")
                        .append(getObjName());

                break;
            }
            case 1: {
                // Bob, Champion of Light
                // <name>, <title> of <obj>
                name.append(getNameName())
                        .append(", ")
                        .append(getTitleName())
                        .append(" of ")
                        .append(getObjName());

                break;
            }
            case 2: {
                // Bob, Champion of the Ancient Lord
                // <name>, <title> of the <adj> <title>
                name.append(getNameName())
                        .append(", ")
                        .append(getTitleName())
                        .append(" of the ")
                        .append(getAdjName())
                        .append(" ")
                        .append(getTitleName());

                break;
            }
            case 3: {
                // Bob, Champion of the Ancient Sun
                // <name>, <title> of the <adj> <obj>
                name.append(getNameName())
                        .append(", ")
                        .append(getTitleName())
                        .append(" of the ")
                        .append(getAdjName())
                        .append(" ")
                        .append(getObjName());

                break;
            }
            case 4: {
                // Bob, the Sleepy Lord
                // <name>, the <adj> <title>
                name.append(getNameName())
                        .append(", the ")
                        .append(getAdjName())
                        .append(" ")
                        .append(getTitleName());

                break;
            }
            case 5: {
                // Bob, the Sun Slayer
                // <name>, the <obj> <title>
                name.append(getNameName())
                        .append(", the ")
                        .append(getObjName())
                        .append(" ")
                        .append(getTitleName());

                break;
            }
            case 6: {
                // Bob, Holy Lord of Moon
                // <name>, <adj> <title> of <obj>
                name.append(getNameName())
                        .append(", ")
                        .append(getAdjName())
                        .append(" ")
                        .append(getTitleName())
                        .append(" of ")
                        .append(getObjName());

                break;
            }
        }

        return name.toString();
    }

    private static DragonType[] types = DragonType.values();
    private static DragonCharacter[] characters = DragonCharacter.values();

    private static String getAdjName() {
        return namesAdj[r.nextInt(namesAdj.length)];
    }

    private static String[] namesAdj = {
            "Good",
            "Doomed",
            "Golden",
            "Silver",
            "Magical",
            "Furious",
            "Sealed",
            "Natural",
            "Ancient",
            "Great",
            "Sleepy",
            "Dreadful",
            "Horrible",
            "Shiny",
            "Bright",
            "Dark",
            "Dead",
            "Holy",
            "Righteous",
            "Wise",
            "Ancient",

    };

    private static String getTitleName() {
        return namesTitle[r.nextInt(namesTitle.length)];
    }

    private static String[] namesTitle = {
            "Master",
            "Slayer",
            "Overlord",
            "Mother",
            "Father",
            "Ancestor",
            "Elder",
            "Prime",
            "Sultan",
            "Doom",
            "Lord",
            "Patriarch",
            "Champion",
            "Sage",
            "Protector",
            "Magician",
            "Disaster",
            "Sage",
            "One",
            "Eater",
            "General",
    };

    private static String getObjName() {
        return namesObj[r.nextInt(namesObj.length)];
    }

    private static String[] namesObj = {
            "Doom",
            "Gold",
            "Death",
            "Life",
            "Void",
            "Ice",
            "Fire",
            "Wind",
            "Water",
            "Sun",
            "Moon",
            "Dream",
            "Earth",
            "Birth",
            "World",
            "Worlds",
            "Shadows",
            "Day",
            "Night",
            "Order",
    };

    private static String getNameName() {
        return nameBeginning[r.nextInt(nameBeginning.length)]
                + nameMiddle[r.nextInt(nameMiddle.length)]
                + nameEnd[r.nextInt(nameEnd.length)];
    }

    private static String[] nameBeginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk" };
    private static String[] nameMiddle = { "air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak" };
    private static String[] nameEnd = { "d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur" };

}
