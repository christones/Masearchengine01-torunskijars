/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskiexpleserver;

import java.util.Collection;

import java.util.Iterator;

import com.torunski.crawler.Crawler;

import com.torunski.crawler.filter.ServerFilter;

/**
15.
* Example for a simple crawling process.
16.
*
17.
* Description: Command line example to crawl a web site starting from root. It uses a "ServerFilter" and the default "Max Iterations" model with a maximum of 32 links.
18.
* Result: Using www.spiegel.de as the parameter 32 pages are visted and more than 400 pages are left.
19.
*
20.
* @author Lars Torunski
21.
* @version $Id: ExampleServer.java,v 1.2 2006-08-26 10:33:11 ltorunski Exp $
22.
*/

public class TorunskiExpleServer {

 

public static void main(String[] args) {

 

if (args.length != 1) {

System.out.println("ExampleServer for Crawler");

System.out.println("Usage: java com.torunski.crawler.examples.ExampleServer [http server]");

return;

}

Crawler crawler = new Crawler();

crawler.setLinkFilter(new ServerFilter(args[0]));

crawler.start(args[0], "/");


// show visited links

Collection visitedLinks = crawler.getModel().getVisitedURIs();

System.out.println("Links visited=" + visitedLinks.size());


Iterator list = visitedLinks.iterator();

while (list.hasNext()) {

System.out.println(list.next());

}


// show visited links

Collection notVisitedLinks = crawler.getModel().getToVisitURIs();


System.out.println("Links NOT visited=" + notVisitedLinks.size());

Iterator listNot = notVisitedLinks.iterator();

while (listNot.hasNext()) {
System.out.println(listNot.next());

}

}
}


