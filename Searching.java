/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskisearchenginetoo;

/**
 *
 * @author christones
 */

import java.io.File;

import java.io.IOException;

import static java.lang.Double.max;

import java.util.Scanner;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Searching {
    
    
@SuppressWarnings("SuspiciousIndentAfterControlStatement")
public static void main (String[] args) throws IOException, ParseException {
    
Directory index  ;

IndexWriter writer ;



// enter the word to search

System.out.println("Enter a keyword: ");

Scanner scanner = new Scanner(System.in);

String search = scanner.nextLine();

System.out.println("Searching for: " + search);


// open the index created by the crawler

index = FSDirectory.open(new File("index"));

// append the directory index

 if (IndexWriter.isLocked(index))
    
 IndexWriter.unlock(index);

 writer = new IndexWriter(index, new StandardAnalyzer(Version.LUCENE_30), false, IndexWriter.MaxFieldLength.LIMITED);

    try (//creates the searcher variable
            IndexSearcher is = new IndexSearcher(index)) {
        QueryParser parser = new QueryParser(Version.LUCENE_30, "url", new StandardAnalyzer(Version.LUCENE_30));
        
        Query query = parser.parse(search);
        
// We look for top-10 results
int hitsPerPage = 10;

TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage,
        true);

is.search(query, collector);

TopDocs topDocs = collector.topDocs();

ScoreDoc[] hits = topDocs.scoreDocs;

 
System.out.println("      results: " + hits.length + " of total " + topDocs.totalHits);

for (int i = 0; i < hits.length; i++) {
    
    float relevance = ((float) Math.round(hits[i].score * 1000)) / 10;
    
    String url = is.doc(hits[i].doc).getField(ExampleLuceneHTMLDocumentParserEventListener.URL).stringValue();
    
    String modified = is.doc(hits[i].doc).getField(ExampleLuceneHTMLDocumentParserEventListener.FIELD_TIMESTAMP).stringValue();
    
    System.out.println("No " + (i+1) + " with relevance " + relevance + "% : "+ url + " (" + modified + ')');
    
}  
    } catch (ParseException | IOException e) {
			System.out.println("Got an Exception: " + e.getMessage());

}
}
}



