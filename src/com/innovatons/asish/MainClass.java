package com.innovatons.asish;
import com.innovatons.asish.Indexer;
import com.innovatons.asish.Search;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;

public class MainClass {
	public static void main(String args[]) throws ParseException {
		/*
		 *  dataDirectoy is the path where the input files
		 * are present. indexDirectory id the path where the index will be
		 * created.
		 */
		File dataDirectory = new File(
				"F:\\Projects Space\\UltraFastSearchApplicationForBoots\\Input");
		File indexDirectory = new File("C:\\Lucene Index\\");

		try {
			/*
			 * Creating an INDEX by sending the path to createIndex() on where
			 * it should be created.
			 */
			IndexWriter index = Indexer.createIndex(indexDirectory);
			Indexer.buildIndex(index, dataDirectory, indexDirectory);
			Indexer.closeIndex(index);
			System.out.println(" Index BUILT");
			Search.searchThis(indexDirectory,"Assistant"); // Give Search term here.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
