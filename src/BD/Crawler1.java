/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author koop
 */
public class Crawler1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UrlList urllist = new UrlList();
            urllist.addUrl("http://www.stadnie.pl/", "", "");
            // TODO code application logic here
            
            String url_scan = "http://www.stadnie.pl/";
            int petla =0;
            while(true){
                //if(petla++>40) break;
            
                System.out.print("Scan url = "+url_scan+" Baza urls = "+urllist.getList().size()+"\n");
                
                String wynik = UrlContent.getText(url_scan);

                urllist.setStatusUrl(url_scan,1);

                //System.out.print(wynik);

                String string = wynik;
                Pattern pattern = Pattern.compile("(<a[^>]*>)([^<]*)(</a>)");
                Pattern pattern2 = Pattern.compile("(href=\")(http[^\"]*)");
                Matcher matcher = pattern.matcher(string);
                Matcher matcher2;

                List<String> listMatches = new ArrayList<String>();

                while(matcher.find())
                {
                    matcher2 = pattern2.matcher( matcher.group(1) );
                    matcher2.find();

                    //System.out.println(matcher.group(1));
                    try {
                        if(!matcher2.group(2).equals(""))
                            urllist.addUrl(matcher2.group(2), matcher.group(2), url_scan);
                    } catch (Exception e) {
                    }
                }

            
                url_scan = urllist.getNextUrl();
                if(url_scan.equals("")) break;
            
            }//end while
            
            System.out.println("Adresy nie zeskanowane\n");
            for (UrlInfo urlinfo : urllist.getList()) {
                if( urlinfo.status == 0 ){
                    System.out.println(urlinfo.url);
                }
            }
            
            System.out.println("Adresy zeskanowane\n");
            for (UrlInfo urlinfo : urllist.getList()) {
                if( urlinfo.status != 0 ){
                    System.out.println("id:"+urlinfo.id+" url:"+urlinfo.url+" q:"+urlinfo.qtyrlparents);
                    System.out.println("id:"+urlinfo.id+" urlparent:"+urlinfo.urlparents);
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Crawler1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
