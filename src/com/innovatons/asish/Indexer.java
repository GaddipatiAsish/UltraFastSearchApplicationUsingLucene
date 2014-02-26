package com.innovatons.asish;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;


public class Indexer {
/*
 * This class will build an index on text files by taking Directory of files as the input.
 */
	static IndexWriter createIndex(File indexDirectory) throws IOException {
		StandardAnalyzer analyser = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig indexconfig = new IndexWriterConfig(
				Version.LUCENE_46, analyser);
		IndexWriter index = new IndexWriter(FSDirectory.open(indexDirectory),
				indexconfig);
		return index;
	}

	static void closeIndex(IndexWriter index) throws IOException {
		index.close();
	}

	@SuppressWarnings("deprecation")
	static void buildIndex(IndexWriter index, File dataDirectory,
			File IndexDirectory) throws IOException {
		File[] files = dataDirectory.listFiles();
		for (int i = 0; i < files.length; i++) {
			Document document = new Document();

			Reader reader = new FileReader(files[i]);
			document.add(new Field("contents", reader));

			String path = files[i].getCanonicalPath();
			document.add(new Field("path", path, Field.Store.YES,
					Field.Index.NOT_ANALYZED));

			index.addDocument(document);

		}
	}

}
