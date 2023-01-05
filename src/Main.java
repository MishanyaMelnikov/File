import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        String path = "C:/Users/misha/IdeaProjects/File/Games";
        String savePath = "C:/Users/misha/IdeaProjects/File/Games/savegames/";
        String zipPath = "C:/Users/misha/IdeaProjects/File/Games/savegames/";

//        newDir(path, "src");
//        newDir(path, "res");
//        newDir(path, "savegames");
//        newDir(path, "temp");
//        newDir(path + "/src", "main");
//        newDir(path + "/src", "test");
//        newFile(path + "/src/main", "Main.java");
//        newFile(path + "/src/main", "Utils.java");
//        newDir(path + "/res", "drawables");
//        newDir(path + "/res", "vectors");
//        newDir(path + "/res", "icons");
//        newFile(path + "/temp", "temp.txt");
//
//        GameProgress game1 = new GameProgress(5,5,5,5);
//        GameProgress game2 = new GameProgress(4,4,4,4);
//        GameProgress game3 = new GameProgress(3,3,3,3);
//
//        saveGame(savePath + "save1.dat", game1);
//        saveGame(savePath + "save2.dat", game2);
//        saveGame(savePath + "save3.dat", game3);

//        zipFiles(zipPath + "save.zip",savePath);

//        delete(savePath + "save1.dat");
//        delete(savePath + "save2.dat");
//        delete(savePath + "save3.dat");

        openZip(zipPath + "save.zip", savePath);

    }


    public static void newDir(String dirPath, String fileName) {
        File dir = new File(dirPath, fileName);
        String text = "Папка " + fileName + " создана";
        if (dir.mkdir()) {

            System.out.println(text);
            try (FileWriter writer = new FileWriter("C:/Users/misha/IdeaProjects/File/Games/temp/temp.txt", true)) {
                writer.write(text);
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
        }
    }

    public static void newFile(String filePath, String fileName) {
        String text = "Файл " + fileName + " создан";
        File file = new File(filePath, fileName);
        try {
            if (file.createNewFile())
                System.out.println(text);
            try (FileWriter writer = new FileWriter("C:/Users/misha/IdeaProjects/File/Games/temp/temp.txt", true)) {
                writer.write(text);
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void saveGame(String savePath, GameProgress game){

        try (FileOutputStream fos = new FileOutputStream(savePath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, String savePath){

        try (FileOutputStream fos = new FileOutputStream(zipPath); ZipOutputStream zos = new ZipOutputStream(fos)){

            File dir = new File(savePath);
             for (File item : dir.listFiles()){
                 FileInputStream fis = new FileInputStream(savePath + item.getName());
                 ZipEntry entry = new ZipEntry(item.getName());
                 zos.putNextEntry(entry);
                 byte[] buffer = new byte[fis.available()];
                 fis.read(buffer);
                 zos.write(buffer);
                 zos.closeEntry();
                 }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void openZip(String zipPath, String unZipPath){
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))){
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null){
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(unZipPath + name);
                for (int c = zis.read(); c != -1; c = zis.read()){
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }





    public static void delete(String savePath){
        File file = new File(savePath);
        if (file.delete()) {
            System.out.println("Незаархивированные файлы удалены");
        }else {
            System.out.println("Файлы не удалены");
        }
    }
}



