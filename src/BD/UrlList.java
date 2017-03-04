/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author koop
 */
public class UrlList {

    private List<UrlInfo> urls;
    private int id;
    //private List<String> urls_anchor = new ArrayList();
    //private List<Integer> urls_status = new ArrayList();
    
    public UrlList() {
        this.urls = new ArrayList<UrlInfo>();
        this.id = 1;
    }
    
    public boolean addUrl(String url, String anchor, String urlparent){
        UrlInfo a = this.getUrl(url);
            
        if(null == a){
            a = new UrlInfo();
            a.url = url;
            a.anchor = anchor;
            a.status = 0;
            a.qtyrlparents = 0;
            a.urlparents = "";
            a.id = this.id++;
        }

        a.urlparents += urlparent+"\n";
        a.qtyrlparents++;
            
        if(!this.existUrl(url)){
            this.urls.add(a);
            return true;                    
        }
        return false;
    }
    
    private UrlInfo getUrl(String url){
        for (UrlInfo urlinfo : this.urls) {
           if( urlinfo.url.equals(url) ){
               return urlinfo;
           }
        }
        return null;
    }
    
    public boolean existUrl(String url){
        for (UrlInfo urlinfo : this.urls) {
           if( urlinfo.url.equals(url) ){
               return true;
           }
        }
        return false;
    }
    
    public boolean setStatusUrl(String url, int status){
        for (UrlInfo urlinfo : this.urls) {
           if( urlinfo.url.equals(url) ){
               urlinfo.status = status;
               return true;
           }
        }
        return false;
    }
    
    public String getNextUrl(){
        for (UrlInfo urlinfo : this.urls) {
           if( urlinfo.status == 0 ){
               return urlinfo.url;
           }
        }
        return "";
    }
    
    public List<UrlInfo> getList(){
        return this.urls;
    }
}
