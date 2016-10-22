/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskiexplefilesystem;

import com.torunski.crawler.Crawler;
import com.torunski.crawler.events.LinkGraphParserEventListener;
import com.torunski.crawler.filter.ServerFilter;
import com.torunski.crawler.link.Link;
import com.torunski.crawler.link.LinkGraph;
import com.torunski.crawler.model.MaxDepthModel;
import com.torunski.crawler.parser.filesystem.FileSystemParser;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author christones
 */
public class TorunskiExpleFileSystem {

   private static final String SERVER = "http://www.njorku.com";

   private static final String START = "/index.html";
   
   private static final String FILE_PATH = "/home/christones/NetBeansProjects/TorunskiExpleFileSystem/test";


public static void main(String[] args) {
        
Crawler crawler = new Crawler();

crawler.setModel(new MaxDepthModel(2));

crawler.setLinkFilter(new ServerFilter(SERVER));

FileSystemParser fileSystemParser = new FileSystemParser();

fileSystemParser.addMapping(SERVER, new File(FILE_PATH));

crawler.setParser(fileSystemParser);

 

LinkGraphParserEventListener graph = new LinkGraphParserEventListener();

crawler.addParserListener(graph);

 

crawler.start(SERVER, START);

 

// show link graph of the visited links

Iterator list = crawler.getModel().getVisitedURIs().iterator();

while (list.hasNext()) {

Link link = (Link) list.next();

System.out.println(link.getURI());

LinkGraph linkGraph = graph.getLink(link.getURI());

Iterator inLinks = linkGraph.iteratorOfInLinks();

 

while (inLinks.hasNext()) {

System.out.println("-> in:  " + inLinks.next());

}


Iterator outLinks = linkGraph.iteratorOfOutLinks();

while (outLinks.hasNext()) {

System.out.println("-> out: " + outLinks.next());

}
        
}

/*

// show visited links

Collection visitedLinks = crawler.getModel().getVisitedURIs();

System.out.println("Links visited=" + visitedLinks.size());


Iterator list = visitedLinks.iterator();

while (list.hasNext()) {

System.out.println(list.next());

}

*/
// show not visited links

Collection notVisitedLinks = crawler.getModel().getToVisitURIs();

System.out.println("Links NOT visited=" + notVisitedLinks.size());

Iterator listNot = notVisitedLinks.iterator();

while (listNot.hasNext()) {

System.out.println(listNot.next());


}

}

    
}
