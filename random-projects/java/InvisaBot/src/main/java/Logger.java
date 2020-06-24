import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Logger {

    public void log(String errorText) {

        String logfPath = "./invisaBot" + getDateStr() + ".log";
        BufferedWriter writer;

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd @ HH:mm:ss");

            writer = new BufferedWriter(new FileWriter(logfPath, false));
            writer.write("Error encountered during runtime @ " + dtf.format(LocalDateTime.now()) + "\n");
            writer.write("Returned error code is logged below:\n\n\n");
            writer.write(errorText);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getDateStr() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        return dateFormat.format(date);
    }

}
