package graphics;

import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoNabber {

    public InfoNabber() {



    }

    private void writeSiteToFile(String url, String filePath) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.write(getSite(url));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


    public String[] getItemNumberAndClassThenFormatData(String str) {

        String itemNumber = null;
        String classType = null;
        String itemResult = null;

        String itemNumberReg = "\\<p\\>\\<strong\\>Item #:\\<\\/strong\\> SCP-\\d+\\<\\/p\\>";
        String classReg = "\\<p\\>\\<strong\\>Object Class:\\<\\/strong\\> .+\\<\\/p\\>";
        Pattern r = Pattern.compile(itemNumberReg);

        Matcher m = r.matcher(str);
        if (m.find()) {
            itemNumber = m.group(0).substring("<p><strong>Item #:</strong> ".length()-1, m.group(0).length() - 4).strip();
            itemResult = m.group(0);
        }

        r = Pattern.compile(classReg);
        m = r.matcher(str);
        if (m.find()) {
            classType = m.group(0).substring("<p><strong>Object Class:</strong> ".length()-1, m.group(0).length() - 4).strip();
        }


        String rfStr = str.substring(str.indexOf(itemResult), str.indexOf("<div class=\"footer-wikiwalk-nav\">"));


        if (itemNumber != null && classType != null) return new String[]{itemNumber,classType, rfStr};

        return new String[]{"Failed to get item number.", "Failed to get class.", rfStr};

    }


    public String getSite(String url) {
        String html;

        try
        {
            html = Jsoup.connect(url).get().html();
        }
        catch (IOException ioe)
        {
            System.out.println("Failed to get website.");
            ioe.printStackTrace();
            html = "";
            System.exit(-1);
        }

        return html;
    }


    private void findRelevantInfo(String htmlContent) {
        System.out.println(htmlContent);
    }
}
