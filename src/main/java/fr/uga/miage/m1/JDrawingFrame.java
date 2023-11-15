package fr.uga.miage.m1;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.*;

import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.*;
import fr.uga.miage.m1.persistence.JSonVisitor;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private String pathToImages = "src/main/java/fr/uga/miage/m1/images/";
    private static final Logger LOGGER = Logger.getLogger("JDrawingFrame");

    private transient SimpleShape movingShape;

    private Point startPosition;

    private transient List<List<SimpleShape>> history = new LinkedList<>();
    private static final String OUTPUT = "outputs/output";

    private static final long serialVersionUID = 1L;

    private JToolBar mToolbar;

    private ShapeFactory.Shapes mSelected;

    private JPanel mPanel;

    private JLabel mLabel;

    private transient List<SimpleShape> shapesVisible = new ArrayList<>();

    private transient ActionListener mReusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<ShapeFactory.Shapes, JButton> mButtons = new EnumMap<>(ShapeFactory.Shapes.class);

    /**
     * Default constructor that populates the main window.
     * @param frameName
     */
    public JDrawingFrame(String frameName) {
        super(frameName);
        // Instantiates components
        mToolbar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(this);
        mPanel.addMouseMotionListener(this);
        mPanel.setFocusable(true);
        mPanel.requestFocusInWindow();

        
        mLabel = new JLabel(" ", SwingConstants.LEFT);
        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(ShapeFactory.Shapes.SQUARE, new ImageIcon(this.pathToImages + "square.png"));
        addShape(ShapeFactory.Shapes.TRIANGLE, new ImageIcon(this.pathToImages + "triangle.png"));
        addShape(ShapeFactory.Shapes.CIRCLE, new ImageIcon(this.pathToImages + "circle.png"));

        addButton("Export JSON", "json");
        addButton("Export XML","xml");
        setPreferredSize(new Dimension(400, 400));
        
    }



    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(ShapeFactory.Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        mButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(mReusableActionListener);
        if (mSelected == null) {
            button.doClick();
        }
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }

    private void addButton(String label, String type){
        JButton button = new JButton(label);
        button.setBorderPainted(false);
        button.setActionCommand(label);
        ActionListener actionListener =
                e -> {
                    try {
                        if(type.equals("json"))
                            exportJSON();
                        else if (type.equals("xml"))
                            exportXML();
                    } catch (Exception ex) {
                        LOGGER.warning(ex.getMessage());
                    }
                };
        button.addActionListener(actionListener);
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }

    private void exportJSON() throws IOException{
        StringBuilder bld = new StringBuilder();
        JSonVisitor visitor = new JSonVisitor();
        bld.append("{\"shapes\":[");
        for (SimpleShape simpleShape : shapesVisible) {
            simpleShape.accept(visitor);
            bld.append(visitor.getRepresentation());
            bld.append(",");
                    }

        bld.deleteCharAt(bld.length()-1);
        bld.append("]}");
        try(PrintWriter writer  = new PrintWriter(OUTPUT+".json")) {
            writer.println(bld);
        }
    }



    private void exportXML() throws IOException{
        StringBuilder bld = new StringBuilder();
        XMLVisitor visitor = new XMLVisitor();
        bld.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        bld.append("<root>");
        bld.append("<shapes>");
        for (SimpleShape simpleShape : shapesVisible) {
            simpleShape.accept(visitor);
            bld.append(visitor.getRepresentation());
        }
        bld.append("</shapes>");
        bld.append("</root>");
        try(PrintWriter writer  = new PrintWriter(OUTPUT+".xml")) {
            writer.println(bld);
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (SimpleShape simpleShape : shapesVisible) {
            simpleShape.draw((Graphics2D) this.mPanel.getGraphics());
        }


        if(movingShape != null){
            movingShape.draw((Graphics2D) this.mPanel.getGraphics());
        }


    }

    /**
     * Undoes the last action
     */
    public void undo(){
        LOGGER.info("undo");
        if(!history.isEmpty()){
            int last = this.history.size() - 1;
            this.shapesVisible = this.history.get(last);
            this.history.remove(last);
            this.paintComponents(this.getGraphics());
        }
    }


    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        SimpleShape s;
        if (mPanel.contains(evt.getX(), evt.getY())) {
            addHistoryList(shapesVisible);
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            s = ShapeFactory.getInstance().createSimpleShape(mSelected, evt.getX(), evt.getY());
            shapesVisible.add(s);
            s.draw(g2);
        }
    }

    public void addHistoryList(List<SimpleShape> shapes) {
        List<SimpleShape> newShapes = new ArrayList<>(shapes);
        history.add(newShapes);
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
        // Nothing to do
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        mLabel.setText(" ");
        mLabel.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
        for(SimpleShape shape: shapesVisible){
            if(shape.contains(evt.getX(),evt.getY())){
                SimpleShape shapeCopy = ShapeFactory.getInstance().createSimpleShape(shape.getType(), shape.getX() + 25, shape.getY() + 25);
                List<SimpleShape> listCopy = new ArrayList<>(shapesVisible);
                listCopy.remove(shape);
                listCopy.add(shapeCopy);
                addHistoryList(listCopy);
                movingShape = shape;
                startPosition = evt.getPoint();
                break;
            }
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
        this.movingShape = null;
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        if(this.movingShape != null){
            int diffX = evt.getX() - this.startPosition.x;
            int diffY = evt.getY() - this.startPosition.y;
            movingShape.move(diffX,diffY);
            paintComponents(this.getGraphics());
            startPosition = evt.getPoint();
        }
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        mLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            for (Map.Entry<ShapeFactory.Shapes, JButton> entry: mButtons.entrySet()) {
                ShapeFactory.Shapes shape = entry.getKey();
                JButton btn = entry.getValue();
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}
