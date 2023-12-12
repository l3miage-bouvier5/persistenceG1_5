package fr.uga.miage.m1;

import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.ShapeGroup;
import fr.uga.miage.m1.shapes.SimpleShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame {


    private String pathToImages = "src/main/java/fr/uga/miage/m1/images/";
    private static final Logger LOGGER = Logger.getLogger("JDrawingFrame");

    private transient SimpleShape movingShape;

    private Point mouseLastPosition;

    private static final String OUTPUT = "outputs/";

    private final transient ExportManager exportManager = new ExportManager(OUTPUT);

    private final transient ImportManager importManager = new ImportManager();

    private static final long serialVersionUID = 1L;

    private JToolBar mToolbar;

    private ShapeFactory.Shapes mSelected;

    private JPanel mPanel;

    private JLabel mLabel;

    private transient List<SimpleShape> shapesVisible = new ArrayList<>();

    private transient List<ShapeGroup> shapeGroups = new ArrayList<>();

    private transient ActionListener mReusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<ShapeFactory.Shapes, JButton> mButtons = new EnumMap<>(ShapeFactory.Shapes.class);

    /**
     * Default constructor that populates the main window.
     *
     * @param frameName
     */
    public JDrawingFrame(String frameName, Client client) {
        super(frameName);
        // Instantiates components
        mToolbar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(client);
        mPanel.addMouseMotionListener(client);


        mPanel.setFocusable(true);
        mPanel.requestFocusInWindow();

        mLabel = new JLabel(" ", SwingConstants.LEFT);
        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShapeIcon(ShapeFactory.Shapes.SQUARE, new ImageIcon(this.pathToImages + "square.png"));
        addShapeIcon(ShapeFactory.Shapes.TRIANGLE, new ImageIcon(this.pathToImages + "triangle.png"));
        addShapeIcon(ShapeFactory.Shapes.CIRCLE, new ImageIcon(this.pathToImages + "circle.png"));
        addShapeIcon(ShapeFactory.Shapes.CUBE, new ImageIcon(this.pathToImages + "underc.png"));


        addButton("Export JSON");
        addButton("Export XML");
        addButton("Group");
        addButton("Import XML");
        setPreferredSize(new Dimension(800, 800));

    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     *
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShapeIcon(ShapeFactory.Shapes shape, ImageIcon icon) {
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

    private void addButton(String label) {
        JButton button = new JButton(label);
        button.setBorderPainted(false);
        button.setActionCommand(label);
        ActionListener actionListener =
                e -> {
                    try {
                        String successMessageTitle = "Success";
                        if (label.contains("Export JSON")) {
                            this.exportManager.exportJSON(shapesVisible);
                            JOptionPane.showMessageDialog(null, "JSON export successful!", successMessageTitle, JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (label.contains("Export XML")){
                            this.exportManager.exportXML(shapesVisible);
                            JOptionPane.showMessageDialog(null, "XML export successful!", successMessageTitle, JOptionPane.INFORMATION_MESSAGE);

                        }
                        else if (label.equals("Group"))
                            groupSelectedShapes();
                        else if (label.equals("Import XML")) {
                            this.shapesVisible = this.importManager.importXML();
                            paintComponents(this.getGraphics());
                        }
                    } catch (Exception ex) {
                        LOGGER.warning(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "A problem occurred", "Fail", JOptionPane.ERROR_MESSAGE);
                    }
                };
        button.addActionListener(actionListener);
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }
    private void groupSelectedShapes() {
        ShapeGroup group = new ShapeGroup();
        for (SimpleShape shape : shapesVisible) {
            if (shape.isSelected()) {
                group.addShape(shape);
                shape.setGroup(group);
            }
        }
        shapeGroups.add(group);
        shapesVisible.removeAll(group.getShapes());
        shapesVisible.add(group);
        paintComponents(getGraphics());
        JOptionPane.showMessageDialog(null, "Group created!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (SimpleShape simpleShape : shapesVisible) {
            simpleShape.draw((Graphics2D) this.mPanel.getGraphics());
        }
        if (movingShape != null) {
            movingShape.draw((Graphics2D) this.mPanel.getGraphics());
        }
    }

    /**
     * Undoes the last action
     */
    public void addShape(SimpleShape shape) {
        LOGGER.info("ajout d'une shape");
        shape.draw((Graphics2D) this.mPanel.getGraphics());
        this.shapesVisible.add(shape);
    }

    public void removeShape(SimpleShape shape) {
        if (shape.isSelected()) {
            ((ShapeGroup) shape.getGroup()).removeShape(shape);
        } else {

            this.shapesVisible.remove(shape);
        }

        paintComponents(getGraphics());
    }

    public void moveShape(SimpleShape shape, int diffX, int diffY) {
        shape.move(diffX, diffY);
    }

    public void moveBackShape(SimpleShape shape, Map<SimpleShape, Point> initialPositions, int startX, int startY) {
        if (shape.getType().equals("group")) {
            for (SimpleShape s : ((ShapeGroup) shape).getShapes()) {
                Point initialPosition = initialPositions.get(s);
                if (initialPosition != null) {
                    s.goTo(initialPosition.x, initialPosition.y);
                }
            }
        } else {
            shape.goTo(startX, startY);
        }
        paintComponents(getGraphics());

    }


    public JPanel getmPanel() {
        return mPanel;
    }

    public ShapeFactory.Shapes getmSelected() {
        return mSelected;
    }

    public JLabel getmLabel() {
        return mLabel;
    }


    public List<SimpleShape> getShapesVisible() {
        return shapesVisible;
    }

    public void setMouseLastPosition(Point mouseLastPosition) {
        this.mouseLastPosition = mouseLastPosition;
    }

    public Point getMouseLastPosition() {
        return mouseLastPosition;
    }

    public SimpleShape getMovingShape() {
        return movingShape;
    }

    public void setMovingShape(SimpleShape movingShape) {
        this.movingShape = movingShape;
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            for (Map.Entry<ShapeFactory.Shapes, JButton> entry : mButtons.entrySet()) {
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
  