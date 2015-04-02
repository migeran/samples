package com.migeran;

import ios.foundation.NSArray;
import ios.foundation.c.Foundation;
import ios.foundation.enums.NSSearchPathDirectory;
import ios.foundation.enums.NSSearchPathDomainMask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Data Source class for managing entries.
 */
public class DataSource {

	/**
	 * Entry class represents an entry in the {@link DataSource}.
	 */
	public static class Entry {

		/**
		 * Title.
		 */
		private final String title;

		/**
		 * Category.
		 */
		private final String category;

		/**
		 * Creates a new {@link Entry} from a tab separated string.
		 * 
		 * @param string
		 *            tab separated string
		 */
		private Entry(String string) {
			String[] split = string.split("\t");

			this.title = split[0];
			if (split.length == 1) {
				this.category = "";
			} else {
				this.category = split[1];
			}
		}

		/**
		 * Returns the title.
		 * 
		 * @return title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * Returns the category.
		 * 
		 * @return category
		 */
		public String getCategory() {
			return category;
		}
	}

	/**
	 * Shared instance holder class.
	 */
	private static class HOLDER {
		static DataSource INSTANCE = new DataSource();
	}

	/**
	 * Returns the entry at the specified index.
	 * 
	 * @param idx
	 *            index of entry
	 * @return entry at index
	 */
	public static Entry get(long idx) {
		if (idx < Integer.MIN_VALUE || idx > Integer.MAX_VALUE) {
			throw new IndexOutOfBoundsException();
		}
		return HOLDER.INSTANCE.entries.get((int) idx);
	}

	/**
	 * Number of entries in the {@link DataSource}.
	 * 
	 * @return number of entries
	 */
	public static int size() {
		return HOLDER.INSTANCE.entries.size();
	}

	/**
	 * Returns the hash of the shared {@link DataSource}.
	 * 
	 * @return hash
	 */
	public static int hash() {
		return HOLDER.INSTANCE.entries.hashCode();
	}

	/**
	 * Inserts a new {@link Entry} into the {@link DataSource} and saves it as a
	 * custom {@link Entry}.
	 * 
	 * @param title
	 *            title string
	 * @param category
	 *            category string
	 */
	public static void insert(String title, String category) {
		// Create new Entry
		Entry newEntry = new Entry(title + "\t" + category);

		// Save into the active database
		DataSource inst = HOLDER.INSTANCE;
		inst.entries.add(newEntry);
		inst.customEntries.add(newEntry);

		// Save custom entries into a file
		File file = inst.getCustomDatabaseFile();
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			for (Entry entry : inst.customEntries) {
				writer.println(entry.getTitle() + "\t" + entry.getCategory());
			}
		} catch (Exception e) {
			System.err.println("Failed to save custom entries file!");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * List of all entries.
	 */
	private final ArrayList<Entry> entries = new ArrayList<>();

	/**
	 * List of custom entries.
	 */
	private final ArrayList<Entry> customEntries = new ArrayList<>();

	/**
	 * Creates a new {@link DataSource} by reading from saved file(s).
	 */
	private DataSource() {
		// Load default DB
		InputStream defaultSource = null;
		try {
			defaultSource = DataSource.class
					.getResourceAsStream("/datasource.txt");
			load(defaultSource, false);
		} catch (Exception e) {
			System.err.println("Failed to load default entries file!");
			e.printStackTrace();
		} finally {
			try {
				if (defaultSource != null) {
					defaultSource.close();
				}
			} catch (IOException e) {
				// We tried
			}
		}

		// Load custom DB
		File file = getCustomDatabaseFile();
		if (file.exists()) {
			FileInputStream customSource = null;
			try {
				customSource = new FileInputStream(file);
				load(customSource, true);
			} catch (Exception e) {
				System.err.println("Failed to load custom entries file!");
				e.printStackTrace();
			} finally {
				try {
					if (customSource != null) {
						customSource.close();
					}
				} catch (IOException e) {
					// We tried
				}
			}
		}

		System.out.println("Read " + entries.size() + " entities!");
	}

	/**
	 * Returns the {@link File} pointing to the custom database file.
	 * 
	 * @return custom database file
	 */
	private File getCustomDatabaseFile() {
		NSArray dirs = Foundation.NSSearchPathForDirectoriesInDomains(
				NSSearchPathDirectory.NSDocumentDirectory,
				NSSearchPathDomainMask.NSUserDomainMask, true);
		String dir = (String) dirs.get(0);
		File file = new File(dir, "mydata.txt");
		return file;
	}

	/**
	 * Load database from the specified input stream.
	 * 
	 * @param inputStream
	 *            input stream
	 * @param isCustom
	 *            is custom database
	 */
	private void load(InputStream inputStream, boolean isCustom) {
		// Create reader
		InputStreamReader dictStream;
		try {
			dictStream = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.err.println("Failed to create input stream reader");
			e.printStackTrace();
			return;
		}

		// Read the buffer
		BufferedReader in = new BufferedReader(dictStream);
		try {
			String str;
			while ((str = in.readLine()) != null) {
				if (str.length() == 0 || str.trim().length() == 0) {
					continue;
				}

				// Support for commenting
				if (str.startsWith("#")) {
					continue;
				}

				// Create and add entry
				try {
					Entry entry = new Entry(str);
					entries.add(entry);
					if (isCustom) {
						customEntries.add(entry);
					}
				} catch (RuntimeException ex) {
					System.err.println("Failed to parse line: " + str);
				}
			}
		} catch (Exception e) {
			System.err.println("Failed to read input stream reader");
			e.printStackTrace();
		}
	}
}
