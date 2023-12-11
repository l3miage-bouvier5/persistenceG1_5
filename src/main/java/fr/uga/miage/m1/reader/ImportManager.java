package fr.uga.miage.m1.reader;

import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.ShapeGroup;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportManager {
    private final JFileChooser jFileChooser;


    public ImportManager(){
        this.jFileChooser = new JFileChooser(".");

    }

    public List<SimpleShape> importXML() throws ParserConfigurationException, IOException, SAXException {
        List<SimpleShape> shapesVisible = new ArrayList<>();
        int result = jFileChooser.showOpenDialog(null);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = jFileChooser.getSelectedFile();
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

                DocumentBuilder dbuilder = factory.newDocumentBuilder();

                Document document = dbuilder.parse(selectedFile);

                Element root = document.getDocumentElement();

                NodeList groupList = root.getElementsByTagName("group");

                NodeList shapeList = root.getElementsByTagName("shape");

                for (int i = 0; i < groupList.getLength(); i++) {
                    Element groupElement = (Element) groupList.item(i);
                    ShapeGroup group = new ShapeGroup();

                    NodeList shapeListInGroup = groupElement.getElementsByTagName("shape");

                    for (int j = 0; j < shapeListInGroup.getLength(); j++) {
                        Element shapeElementInGroup = (Element) shapeList.item(j);
                        SimpleShape shape = getShape(shapeElementInGroup);
                        shape.setGroup(group);
                        group.addShape(shape);
                    }
                    shapesVisible.add(group);
                }
                for (int i = 0; i < shapeList.getLength(); i++) {
                    Element shapeElement = (Element) shapeList.item(i);

                    Element parentElement = (Element) shapeElement.getParentNode();
                    boolean isInGroup = parentElement != null && parentElement.getTagName().equals("group");
                    if (!isInGroup) {
                        shapesVisible.add(getShape(shapeElement));
                    }
                }
            } catch (ParserConfigurationException ex) {
                throw new ParserConfigurationException(ex.getMessage());
            } catch (IOException ex) {
                throw new IOException(ex.getMessage());
            } catch (SAXException ex) {
                throw new SAXException(ex.getMessage());
            }
        }
        return shapesVisible;
    }

    private SimpleShape getShape(Element shapeElementInGroup) {
        String type = shapeElementInGroup.getElementsByTagName("type").item(0).getTextContent();
        int x = Integer.parseInt(shapeElementInGroup.getElementsByTagName("x").item(0).getTextContent());
        int y = Integer.parseInt(shapeElementInGroup.getElementsByTagName("y").item(0).getTextContent());
        if (type.equals("cube"))
            return ShapeFactory.getInstance().createSimpleShape(type, x, y);
        return ShapeFactory.getInstance().createSimpleShape(type, x+25, y+25);
    }

}
