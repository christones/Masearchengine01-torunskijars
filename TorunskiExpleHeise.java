/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskiexpleheise;

import java.util.Collection;
import java.util.Iterator;
 
import com.torunski.crawler.Crawler;
import com.torunski.crawler.filter.BeginningPathFilter;
import com.torunski.crawler.filter.LinkFilterUtil;
import com.torunski.crawler.filter.ServerFilter;
 
/**
 * Example for the site www.heise.de.
 *
 * Description: Uses the "Max Iterations" model to crawl only the site www.heise.de. A "ServerFilter" is defined to accept only links to www.heise.de.
 * Result: Output of 32 links and more than 600 not visted www.heise.de pages.
 *
 * @author Lars Torunski
 * @version $Id: ExampleHeise.java,v 1.5 2009-04-14 19:36:23 ltorunski Exp $
 */
public class TorunskiExpleHeise {
     
    // private static final String SERVER = "http://www.blacktulipsystems.com";
    // private static final String SERVER = "http://www.onezeek.com";
    private static final String SERVER = "http://www.heise.de";
 
    private static final String START = "/newsticker/";
     
    public static void main(String[] args) {
         
        Crawler crawler = new Crawler();
        crawler.setLinkFilter(LinkFilterUtil.and(new ServerFilter(SERVER), new BeginningPathFilter(START)));
         
        crawler.start(SERVER, START);
         
        // show visited links
        Collection visitedLinks = crawler.getModel().getVisitedURIs();
        System.out.println("Links visited=" + visitedLinks.size());
 
        Iterator list = visitedLinks.iterator();
        while (list.hasNext()) {
            System.out.println(list.next());
        }
 
        // show not visited links
        Collection notVisitedLinks = crawler.getModel().getToVisitURIs();
 
        System.out.println("Links NOT visited=" + notVisitedLinks.size());
        Iterator listNot = notVisitedLinks.iterator();
        while (listNot.hasNext()) {
            System.out.println(listNot.next());
        }
    
}
    
}

