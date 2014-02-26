package com.innovatons.asish;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Search {

	static void searchThis(File indexDirectory, String findme)
			throws IOException, ParseException {

		Directory directory = FSDirectory.open(indexDirectory);
		@SuppressWarnings("deprecation")
		IndexReader indexreader = IndexReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(indexreader);

		QueryParser parser = new QueryParser(Version.LUCENE_46, "contents",
				new StandardAnalyzer(Version.LUCENE_46));
		Query query = parser.parse(findme);
		TopDocs topDocs = searcher.search(query, 10);

		ScoreDoc[] hits = topDocs.scoreDocs;
		for (int i = 0; i < hits.length; i++) {

			int docId = hits[i].doc;

			Document d = searcher.doc(docId);

			System.out.println(d.get("path"));

		}

		System.out.println("Found: " + topDocs.totalHits);
	}

}
