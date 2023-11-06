package fr.uga.miage.m1;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import fr.uga.miage.m1.shapes.Circle;
import fr.uga.miage.m1.shapes.SimpleShape;
import fr.uga.miage.m1.shapes.Square;
import fr.uga.miage.m1.shapes.Triangle;
import fr.uga.miage.m1.persistence.JSonVisitor;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {
    private static final Logger LOGGER = Logger.getLogger("JDrawingFrame");




    private static final long serialVersionUID = 1L;

    private JToolBar mToolbar;

    private Shapes mSelected;

    private JPanel mPanel;

    private JLabel mLabel;

    private transient ArrayList<SimpleShape> shapesVisible = new ArrayList<>();

    private transient ActionListener mReusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> mButtons = new EnumMap<>(Shapes.class);

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
        addShape(Shapes.SQUARE, new ImageIcon("images/square.png"));
        addShape(Shapes.TRIANGLE, new ImageIcon("images/triangle.png"));
        addShape(Shapes.CIRCLE, new ImageIcon("images/circle.png"));

        addButton("Export JSON", "json");
        setPreferredSize(new Dimension(400, 400));
        
    }



    public Shapes getMSelected(){
        return mSelected;
    }

    public void setmSelected(Shapes shape){
        mSelected = shape;
    }


    public List<SimpleShape> getShapesVisible(){
        return shapesVisible;
    }



    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(Shapes shape, ImageIcon icon) {
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
                    // Code � ex�cuter lorsque le bouton est cliqu�
                    try {
                        if(type.equals("json"))
                            exportJSON();
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
            if(simpleShape instanceof Square){
                Square square = (Square) simpleShape;
                LOGGER.info("square");
                square.accept(visitor);
            }
            else if(simpleShape instanceof Triangle){
                Triangle triangle = (Triangle) simpleShape;
                triangle.accept(visitor);
            }
            else if(simpleShape instanceof Circle){
                Circle circle = (Circle) simpleShape;
                circle.accept(visitor);
            }
            bld.append(visitor.getRepresentation());
            bld.append(",");
                    }

        bld.deleteCharAt(bld.length()-1);
        bld.append("]}");
        try(PrintWriter writer  = new PrintWriter(new File("output.json"))) {
            writer.println(bld);
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (SimpleShape simpleShape : shapesVisible) {
            simpleShape.draw((Graphics2D) g);
        }
    }

    /**
     * Undoes the last action
     */
    public void undo(){
        LOGGER.info("undo");
        if(!shapesVisible.isEmpty()){
            shapesVisible.remove(shapesVisible.size()-1);
            this.paintComponents(this.getGraphics());
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        if (mPanel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            switch(mSelected) {
                case CIRCLE:
                    Circle c = new Circle(evt.getX(), evt.getY());
                    shapesVisible.add(c);
                    shapesVisible.get(shapesVisible.size()-1).draw(g2);
                    break;
                case TRIANGLE:
                    Triangle t = new Triangle(evt.getX(), evt.getY());
                    shapesVisible.add(t);
                    shapesVisible.get(shapesVisible.size()-1).draw(g2);
                    break;
                case SQUARE:
                    Square s = new Square(evt.getX(), evt.getY());
                    shapesVisible.add(s);
                    shapesVisible.get(shapesVisible.size()-1).draw(g2);
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Unknown shape: {0}", mSelected);
            }
        }
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
        // nothing to do

    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
        // nothing to do
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        //nothing to do
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
            Iterator<Shapes> keys = mButtons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = mButtons.get(shape);
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
