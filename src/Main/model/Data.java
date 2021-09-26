package Main.model;

import java.io.*;

public class Data {

    private static final File dataDir = new File("data");

    static {
        dataDir.mkdir();
    }

    public static void saveData() throws IOException {
        try (FileOutputStream fout = new FileOutputStream(String.format("%s/%s.txt", dataDir, Entry.getCountry().getName()))) {
            try (ObjectOutputStream out = new ObjectOutputStream(fout)) {
                out.writeObject(Entry.getCountry());
            }
        }
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        File[] data = Data.dataDir.listFiles();
        if (data == null) {
            System.out.println("err");
            return;
        }
        for (File file : data) {
            try (FileInputStream fin = new FileInputStream(file)) {
                try (ObjectInputStream in = new ObjectInputStream(fin)) {
                    Universe.addCountry((Country) in.readObject());
                }
            }
        }
    }
}

