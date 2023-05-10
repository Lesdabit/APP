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
        FileWriter fw3 = new FileWriter("name.txt");
        FileWriter fw4 = new FileWriter("price.txt");
        FileWriter fw5 = new FileWriter("link.txt");
        org.jsoup.nodes.Document document = Jsoup.parse(content);
        fw1.write(document.toString());
        fw1.close();    
        Elements items = (Elements) document.select("#ItemContainer dl");
        System.out.println("line " + 0 + ": " + items.get(0).toString() + "\n");
        try {
            for(i = 0; i < items.size(); i++) {
                fw2.write("line " + i + ": " + items.get(i).toString() + "\n");
                Element item = items.get(i);
                String name = item.select(".prod_name").first().text();
                fw3.write(name + "\n");
                String price = item.select(".price").text();
                fw4.write(price + "\n");
                String link = item.select(".prod_name > a").attr("href");
                fw5.write(link + "\n");
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
        fw2.close();
        fw3.close();
        fw4.close();
        fw5.close();
    }
}
