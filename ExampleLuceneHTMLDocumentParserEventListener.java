/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torunskisearchenginetoo;

import java.io.File;

import java.io.IOException;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.apache.lucene.demo.HTMLDocument;


import org.apache.lucene.document.Document;

import org.apache.lucene.document.Field;

import org.apache.lucene.index.IndexWriter;

import com.torunski.crawler.link.Link;

import com.torunski.crawler.lucene.LuceneParserEventListener;



public class ExampleLuceneHTMLDocumentParserEventListener extends LuceneParserEventListener{
    
private static final transient Log LOG = LogFactory.getLog(ExampleLuceneHTMLDocumentParserEventListener.class);

/** document field name for storing the URL */

public static final String URL = "url";

/** document field name for storing the timestamp */

public static final String FIELD_TIMESTAMP = "timestamp";

 

/**
 *
 * @author christones
     * @param writer
 */

public ExampleLuceneHTMLDocumentParserEventListener(IndexWriter writer) {

super(writer);

}
   public Document createDocument(File file, Link link) throws IOException, InterruptedException {

       Document doc = HTMLDocument.Document(file);
       doc.add(new Field(URL, link.getURI(), Field.Store.YES, Field.Index.ANALYZED));
       return doc;



}

 
}
