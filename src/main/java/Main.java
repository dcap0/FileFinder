import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Main {

    public static Queue<String> folderQueue = new LinkedList<>();
    public static ArrayList<String> returnSet = new ArrayList<>();

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.home");
        System.out.println("Searching from: " + currentDir);
        System.out.println("Initial Search");
        fileSearch(currentDir,args[0]);
        folderSetFilter(folderQueue,args[0]);
        if(returnSet.size() == 0){
            System.out.println("No results were found for " + args[0]);
        } else {
            returnSet.forEach(System.out::println);
        }
    }

    public static void folderSetFilter(Queue<String> folder, String param){
        while(!folder.isEmpty()){
            String current = folder.remove();
            fileSearch(new File(current).getPath(),param);
        }
    }

    public static void fileSearch(String path, String param){
        String[] currentDirContents = new File(path).list();

        if(currentDirContents!=null) {
            String[] modifiedDirContents = new String[currentDirContents.length];
            for(int i = 0; i < currentDirContents.length;i++){
                modifiedDirContents[i] = path + "/" + currentDirContents[i];
            }

            for (int i = 0; i < (modifiedDirContents).length; i++) {
                File tempFile = new File(modifiedDirContents[i]);
                if (tempFile.isFile() && tempFile.getPath().contains(param)) {
                    returnSet.add(tempFile.getPath());
                } else {
                    folderQueue.add(tempFile.getPath());
                }
            }
        }
    }

}
