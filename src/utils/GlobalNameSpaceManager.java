package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class GlobalNameSpaceManager {

	public final String GLOBAL_NS_FILENAME = "resources/globalnamespace.xml";
	private FileNode root;
	private Map<String, Integer[]> allFilesMap;

	public GlobalNameSpaceManager() {

	}

	public void rebuildGlobalPath() {
		File globalNameSpaceFile = new File(this.GLOBAL_NS_FILENAME);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(globalNameSpaceFile);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlToFileNode(doc);
	};

	public FileNode getRoot() {
		return this.root;
	}

	private void xmlToFileNode(Document doc) {
		doc.getDocumentElement().normalize();
		root = new FileNode();
		root = walk(root, null, doc.getDocumentElement().getFirstChild(), "/");
	}

	private FileNode walk(FileNode fileNode, FileNode parentNode, Node node, String path) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			if (element.getAttribute("id").equalsIgnoreCase("/tmp")) {
				path = element.getAttribute("id");
			} else {
				path = "/tmp" + element.getAttribute("id");
			}

			String[] path_split = element.getAttribute("id").split("/");
			fileNode.filename = path_split[path_split.length - 1];
			fileNode.path = path;
			fileNode.parent = parentNode;

			if (parentNode != null)
				parentNode.children.put(path, fileNode);

			if (element.getTagName().equalsIgnoreCase("folder")) {
				fileNode.isDir = true;
				fileNode.children = new TreeMap<String, FileNode>();
				if (node.hasChildNodes()) {
					for (int i = 0; i < node.getChildNodes().getLength(); i++) {
						walk(new FileNode(), fileNode, node.getChildNodes().item(i), path);
					}
				}
			}
		}
		return fileNode;
	}

}
