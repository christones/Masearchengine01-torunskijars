/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskisearchenginetoo;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.index.IndexWriter;
 
import com.torunski.crawler.Crawler;

import com.torunski.crawler.filter.ServerFilter;

import com.torunski.crawler.model.MaxDepthModel;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.util.Version;



/**
 *
 * @author christones
 */
public class CrawlerAndIndexing {

    
    public static void main(String[] args) {
        
        try {


// setting default parameters

Directory index  ;

int depth = 2;

String server = "http://www.cnn.com";

String start = "/";

IndexWriter writer;

// create Lucene index 

index = FSDirectory.open(new File("index")); 

// create Lucene index writer
		
 writer = new IndexWriter(index, new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.LIMITED);

// common crawler settings

Crawler crawler = new Crawler();

crawler.setLinkFilter(new ServerFilter(server));

crawler.setModel(new MaxDepthModel(depth));

 
// create Lucene parsing listener and add it

crawler.addParserListener(new ExampleLuceneHTMLDocumentParserEventListener(writer));

 
// start crawler

crawler.start(server, start);


// Optimizing Lucene index

writer.optimize();

writer.close();

} catch (Exception e) {

System.err.println("Caught a " + e.getClass() + " with message: " + e.getMessage());

e.printStackTrace();

}

}
        
        
        
}
    

