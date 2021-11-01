package JavaCore_3_2;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress play1 = new GameProgress(100, 2, 3, 2.14);
        GameProgress play2 = new GameProgress(80, 3, 2, 4.68);
        GameProgress play3 = new GameProgress(90, 1, 1, 5.44);
        saveGame("C://Games//savegames//play1.dat", play1);
        saveGame("C://Games//savegames//play2.dat", play2);
        saveGame("C://Games//savegames//play3.dat", play3);
        List<String> arrayList = new ArrayList<>();
        arrayList.add("C://Games//savegames//play1.dat");
        arrayList.add("C://Games//savegames//play2.dat");
        arrayList.add("C://Games//savegames//play3.dat");
        zipFiles("C://Games//savegames//plays.zip", arrayList);

        File play1Delete = new File("C://Games//savegames//play1.dat");
        File play2Delete = new File("C://Games//savegames//play2.dat");
        File play3Delete = new File("C://Games//savegames//play3.dat");
        if (play1Delete.delete()) {
            System.out.println("Фаил play1.dat удален");
        }
        if (play2Delete.delete()) {
            System.out.println("Фаил play2.dat удален");
        }
        if (play3Delete.delete()) {
            System.out.println("Фаил play3.dat удален");
        }
    }

    private static void saveGame(String s, GameProgress play) {
        try (FileOutputStream fin = new FileOutputStream(s);
             ObjectOutputStream obj = new ObjectOutputStream(fin)) {
            obj.writeObject(play);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void zipFiles(String save, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(save))) {
            for (String array : arrayList) {
                FileInputStream fis = new FileInputStream(array);
                ZipEntry entry = new ZipEntry(array);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}








