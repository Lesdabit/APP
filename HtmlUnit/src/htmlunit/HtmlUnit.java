/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author lesdabit
 */
public class HtmlUnit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String url = "https://www.nutn.edu.tw/index-ch.html";
        String amazon = "https://www.amazon.com/s?k=iphone";
        String pchome = "https://ecshweb.pchome.com.tw/search/v3.3/?q=acer";
        String content = loadAndExecuteJs(pchome);
        System.out.println(content);
        if(content != null) {
            parsing(content);
        }
    }
    
    private static String loadAndExecuteJs(String url) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(10000);
        webClient.getOptions().setUseInsecureSSL(true);
        HtmlPage page = webClient.getPage(url);
        webClient.getCurrentWindow().setInnerHeight(3000);
        webClient.waitForBackgroundJavaScript(5000);
        String html = page.asXml();
        webClient.close();
        return html;
    }
    
    private static void parsing(String content) throws IOException {
        int i;
        FileWriter fw1 = new FileWriter("out.txt");
        FileWriter fw2 = new FileWriter("select.txt");
        org.jsoup.nodes.Document document = Jsoup.parse(content);
        fw1.write(document.toString());
        fw1.close();
        try {
            Elements items = (Elements) document.select("#ItemContainer dl");
            for(Element item : items) {
                fw2.append("line" + i + ": " + item.toString() + "\n");
            }
        } catch (IOException e) {
            
        }
        fw2.close();
    }
}
