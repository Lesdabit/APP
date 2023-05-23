/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shoppingparsing;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Thread;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
//import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

/**
 *
 * @author lesdabit
 */
class Product implements Comparable<Product>, Serializable{
        String search;
        String name;
        String img;
        String link;
        int price;
        
        public Product(String search, String name, String img, String link, int price) {
            this.search = search;
            this.name = name;
            this.img = img;
            this.link = link;
            this.price = price;
        }
        
        @Override
        public int compareTo(Product other) {
            return Integer.compare(this.price, other.price);
        }
    }

public class shoppingparsing extends javax.swing.JFrame {
    public static LinkedList<Product> pchome = new LinkedList<>();
    public static LinkedList<Product> momo = new LinkedList<>();
    public static LinkedList<Product> prod_total = new LinkedList<Product>(); 
    public static Queue<LinkedList<Product>> total = new LinkedList<>();
    /**
     * Creates new form main
     */
    public shoppingparsing() {
        initComponents();
    }
    
    class SerializableLinkedList<E> extends LinkedList<E> implements Serializable {
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        item_et = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        search_bt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        page_num = new javax.swing.JLabel();
        next_bt = new javax.swing.JButton();
        back_bt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        item_et.setToolTipText("");
        item_et.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_etActionPerformed(evt);
            }
        });

        jLabel1.setText("Keyword:");

        search_bt.setText("Search");
        search_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(item_et, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(item_et, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(search_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        page_num.setText("1/3");

        next_bt.setText("Next");
        next_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_btActionPerformed(evt);
            }
        });

        back_bt.setText("Back");
        back_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(back_bt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(page_num)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(next_bt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(page_num)
                .addComponent(next_bt)
                .addComponent(back_bt))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item_etActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_etActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_item_etActionPerformed

    private void search_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btActionPerformed
        // TODO add your handling code here:
        String search = item_et.getText();
        String pchome_url = "https://ecshweb.pchome.com.tw/search/v3.3/?q=" + search;
        String momo_url = "https://www.momoshop.com.tw/search/searchShop.jsp?keyword=" + search;
        
//        try {
//            total = read("result.data");
//        } catch(IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
//        }

        thread_execute(search, pchome_url, momo_url);
        try {
            showInfo(prod_total, 1);
            
//        if(total == null) {
//            
//        } else {
//            for(LinkedList<Product> data : total) {
//                if(search == data.get(0).search) {
//                    prod_total = total.peek();
//                    for(int i = 0; i < prod_total.size(); i++) {
//                        System.out.println(prod_total.get(i).price);
//                    }
//                    break;
//                }
//                else {
//                    thread_execute(search, pchome_url, momo_url);
//                }
//            }
//        }
        } catch (IOException ex) {
            Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_btActionPerformed

    private void thread_execute(String search, String pchome_url, String momo_url) {
        Thread thr_pchome = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = loadAndExecuteJs(pchome_url);
                    if(content != null) {
                        parsing(content, search);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void parsing(String content, String search) throws IOException {
                FileWriter fw1 = new FileWriter("out_pchome.txt");
                FileWriter fw2 = new FileWriter("pchome_prod.txt");
                Document document = (Document) Jsoup.parse(content);
                fw1.write(document.toString());
                fw1.close();
                int i = 0;
                Elements items = (Elements) document.select("#ItemContainer dl");
                try {
                    for(i = 1; i < items.size(); i++) {
                        Element item = items.get(i);
                        String name = item.select(".prod_name").text();
                        String img = item.select(".prod_img > img").attr("src");
                        String link = item.select(".prod_name > a").attr("href");
                        String price = item.select(".price").text();
                        fw2.write("Product " + (i) + ": \n" + "Name: " + name + "\nimage: " + img + "\nlink: " + link + "\nprice: "  + price + "\n");
                        String[] price_s = price.split(" ");
                        Product pchome_store = new Product(search, name, ("https:"+img), link, Integer.parseInt(price_s[1])); 
                        prod_total.add(pchome_store);
                    }
                } catch (NullPointerException e) {
                    System.out.println();
                }
                fw2.close();
            }
        });
        Thread thr_momo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = loadAndExecuteJs(momo_url);
                    if(content != null) {
                        parsing(content, search);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void parsing(String content, String search) throws IOException {
                FileWriter fw1 = new FileWriter("out_momo.txt");
                FileWriter fw2 = new FileWriter("momo_prod.txt");
                Document document = (Document) Jsoup.parse(content);
                fw1.write(document.toString());
                fw1.close();
                int i = 0;
                Elements items = (Elements) document.select(".listArea li");
                try {
                    for(i = 0; i < items.size(); i++) {
                        Element item = items.get(i);
                        String name = item.select(".prdNameTitle").text();
                        String img = item.select(".swiper-slide > img").attr("src");
                        String link_select = item.select(".goodsUrl").attr("href");
                        String link = "https://www.momoshop.com.tw/" + link_select;
                        String price = item.select(".price").text();
                        fw2.write("Product " + (i+1) + ": \n" + "Name: " + name + "\nimage: " + img + "\nlink: " + link + "\nprice: "  + price + "\n");
                        String[] price_s = price.split("[\\s,]");
                        Product momo_store = new Product(search, name, img, link, Integer.parseInt(price_s[1]+price_s[2]));
                        prod_total.add(momo_store);
                    }
                } catch (NullPointerException e) {
                    System.out.println();
                }
                fw2.close();
            }
        });
        thr_pchome.setName("pchome");
        thr_pchome.start();
        thr_momo.setName("momo");
        thr_momo.start();
        
        try {
            thr_pchome.join();
            thr_momo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
//        prod_total.addAll(pchome);
//        prod_total.addAll(momo);
        
        Collections.sort(prod_total);
        
        total.offer(prod_total);
        
        try (FileOutputStream fos = new FileOutputStream("result.data");
            ObjectOutputStream os = new ObjectOutputStream(fos)) {
            
            os.writeObject(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter fw = new FileWriter("total.txt");
            for(int i = 0; i < 30; i++) {
                fw.write("Product " + (i+1) + ": \n" + "Name: " + prod_total.get(i).name + "\nimage: " + prod_total.get(i).img + "\nlink: " + prod_total.get(i).link + "\nprice: "  + prod_total.get(i).price + "\n");
            } 
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Queue<LinkedList<Product>> read(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        Queue<LinkedList<Product>> queue = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            queue = (Queue<LinkedList<Product>>) ois.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return queue;
    }
    
    private void next_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_btActionPerformed
        // TODO add your handling code here:
//        String num_s = page_num.getText();
//        String[] num_ss = num_s.split("/");
//        int page = Integer.parseInt(num_ss[0]);
//        if(page < 3) {
//            page_num.setText(Integer.toString(page) + "/3");
//            try {
//                showInfo(prod_total);
//            } catch (IOException ex) {
//                Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_next_btActionPerformed

    private void back_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btActionPerformed
        // TODO add your handling code here:
//        String num_s = page_num.getText();
//        String[] num_ss = num_s.split("/");
//        int page = Integer.parseInt(num_ss[0]);
//        if(page > 0) {
//            page_num.setText(Integer.toString(page) + "/3");
//            try {
//                showInfo(prod_total);
//            } catch (IOException ex) {
//                Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_back_btActionPerformed
    
    private static String loadAndExecuteJs(String url) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(1000);
        webClient.getOptions().setUseInsecureSSL(true);
        HtmlPage page = webClient.getPage(url);
        webClient.getCurrentWindow().setInnerHeight(3000);
        webClient.waitForBackgroundJavaScript(5000);
        String html = page.asXml();
        webClient.close();
        return html;
    }
    
    private void showInfo(LinkedList<Product> total, int start) throws MalformedURLException, IOException {
        Component component = jPanel1;
        JFrame rootFrame = (JFrame) SwingUtilities.getRoot(component);
        rootFrame.setLayout(new BoxLayout(rootFrame.getContentPane(), BoxLayout.Y_AXIS));
//        JPanel parentPanel = new JPanel();
//        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
//        Container content = new Container();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        for(int i = (start-1)*10; i < (start-1)*10+10; i++) {
            
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(150,150));
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel img_label;
            URL url = new URL(total.get(i).img);
            BufferedImage img = ImageIO.read(url);
            Image image = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            img_label = new JLabel(new ImageIcon(image));
            
            JPanel name_panel = new JPanel();
            name_panel.setPreferredSize(new Dimension(400,150));
            name_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JTextArea name = new JTextArea();
            name.setEditable(false);
            name.setLineWrap(true);
            name.setWrapStyleWord(true);
            name.setPreferredSize(new Dimension(400,150));
            name.setText(total.get(i).name + "\n" + total.get(i).link + "\n" + "$" + Integer.toString(total.get(i).price) + "\n");
            Font currentFont = name.getFont();
            Font font = currentFont.deriveFont(16f);
            name.setFont(font);
            
            panel.add(img_label);
            name_panel.add(name);
            
            JPanel childrenPanel = new JPanel();
            childrenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            childrenPanel.add(panel);
            childrenPanel.add(name_panel);
            mainPanel.add(childrenPanel);
        }
        
//        content.add(parentPanel);
        jScrollPane3.getViewport().add(mainPanel);
        jScrollPane3.repaint();
        
        
        next_bt.addActionListener((ActionEvent e) -> {
            String num_s = page_num.getText();
            String[] num_ss = num_s.split("/");
            int page = Integer.parseInt(num_ss[0]);
            if(page < 3) {
                page_num.setText(Integer.toString(page+1) + "/3");
                try {
                    showInfo(total, page+1);
                } catch (IOException ex) {
                    Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        back_bt.addActionListener((ActionEvent e) -> {
            String num_s = page_num.getText();
            String[] num_ss = num_s.split("/");
            int page = Integer.parseInt(num_ss[0]);
            if(page > 1) {
                page_num.setText(Integer.toString(page-1) + "/3");
                try {
                    showInfo(total, page-1);
                } catch (IOException ex) {
                    Logger.getLogger(shoppingparsing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(shoppingparsing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(shoppingparsing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(shoppingparsing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(shoppingparsing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new shoppingparsing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_bt;
    private javax.swing.JTextField item_et;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton next_bt;
    private javax.swing.JLabel page_num;
    private javax.swing.JButton search_bt;
    // End of variables declaration//GEN-END:variables


}

