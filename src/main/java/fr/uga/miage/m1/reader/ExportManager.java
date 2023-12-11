package fr.uga.miage.m1.reader;

import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.ShapeGroup;
import fr.uga.miage.m1.shapes.SimpleShape;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportManager {
    private final String outputFolderPath;

    public ExportManager(String outputPath){
        this.outputFolderPath = outputPath;
    }

    public void exportXML(List<SimpleShape> shapesVisible) throws IOException {
        StringBuilder bld = new StringBuilder();
        XMLVisitor visitor = new XMLVisitor();
        bld.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        bld.append("<root>");
        bld.append("<shapes>");
        for (SimpleShape simpleShape : shapesVisible) {
            if(simpleShape.getType().equals("group")){
                bld.append("<group>");
                for (SimpleShape shape : ((ShapeGroup) simpleShape).getShapes()) {
                    shape.accept(visitor);
                    bld.append(visitor.getRepresentation());
                }
                bld.append("</group>");
            }
            else{
                simpleShape.accept(visitor);
                bld.append(visitor.getRepresentation());
            }
        }
        bld.append("</shapes>");
        bld.append("</root>");
        try(PrintWriter writer  = new PrintWriter(outputFolderPath+"output.xml")) {
            writer.println(bld);

        }catch (IOException e){
            throw new IOException(e);
        }
    }
    public void exportJSON(List<SimpleShape> shapesVisible) throws IOException{
        StringBuilder bld = new StringBuilder();
        JSonVisitor visitor = new JSonVisitor();
        bld.append("{\"shapes\":[");
        for (SimpleShape simpleShape : shapesVisible) {
            if(simpleShape.getType().equals("group")){
                bld.append("{\"group\":[");
                for (SimpleShape shape : ((ShapeGroup) simpleShape).getShapes()) {
                    shape.accept(visitor);
                    bld.append(visitor.getRepresentation());
                    bld.append(",");
                }
                bld.deleteCharAt(bld.length()-1);
                bld.append("]},");
            }else{
                simpleShape.accept(visitor);
                bld.append(visitor.getRepresentation());
                bld.append(",");
            }
        }
        bld.deleteCharAt(bld.length()-1);
        bld.append("]}");
        try(PrintWriter writer  = new PrintWriter(outputFolderPath+"output.json")) {
            writer.println(bld);
        }catch (IOException e){
            throw new IOException(e);
        }
    }

}
