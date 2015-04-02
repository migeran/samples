package com.migeran.demo.sample.web.rssreader;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSFeed {

	ArrayList<RSSFeedItem> items = new ArrayList<RSSFeedItem>();

	public RSSFeed(String string) {
		try {
			URL url = new URL(string);
			BufferedInputStream in = new BufferedInputStream(url.openStream());
			DocumentBuilderFactory builderf = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderf.newDocumentBuilder();
			Document doc = builder.parse(in);

			NodeList list = doc.getChildNodes();
			for (int i = 0; i < list.getLength(); ++i) {
				if (list.item(i).getNodeName().equals("rss")) {
					handleRSSNode(list.item(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleRSSNode(Node rss) {
		NodeList list = rss.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			if (list.item(i).getNodeName().equals("channel")) {
				handleChannelNode(list.item(i));
			}
		}
	}

	private void handleChannelNode(Node channel) {
		NodeList list = channel.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			if (list.item(i).getNodeName().equals("item")) {
				handleItemNode(list.item(i));
			}
		}
	}

	private void handleItemNode(Node item) {
		RSSFeedItem fi = new RSSFeedItem();
		NodeList list = item.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			Node node = list.item(i);
			if (node.getNodeName().equals("title")) {
				fi.setTitle(node.getTextContent());
			} else if (node.getNodeName().equals("link")) {
				fi.setUrl(node.getTextContent());
			} else if (node.getNodeName().equals("description")) {
				fi.setDescription(node.getTextContent());
			} else if (node.getNodeName().equals("pubDate")) {
				fi.setPubDate(node.getTextContent());
			}
		}
		items.add(fi);
	}

	public int getCount() {
		return items.size();
	}

	public RSSFeedItem get(int row) {
		return items.get(row);
	}

}
