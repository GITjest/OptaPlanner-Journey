package journey.guifx.planICS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class CSV {
    private File CSVFile;

    public CSV(String propFile, String stats, String name) {
        try {
            File f = new File(propFile + "\\PlanICS_CSV");
            if(!f.exists()) f.mkdir();
            CSVFile = new File(f.getPath() + "\\" + name + ".csv");
            if(!CSVFile.exists()){
                CSVFile.createNewFile();
                Files.write(CSVFile.toPath(), ("javaTime; quality\n").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(CSVFile.toPath(), (stats+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getCSVFile() {
        return CSVFile;
    }
}
