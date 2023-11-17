package fr.uga.miage.m1;

import fr.uga.miage.m1.commands.AddShapeCommand;
import fr.uga.miage.m1.commands.Command;
import fr.uga.miage.m1.commands.Invoker;
import fr.uga.miage.m1.commands.MoveShapeCommand;
import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.SimpleShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements MouseListener, MouseMotionListener {

    private Invoker invoker = new Invoker();

    private JDrawingFrame frame;

    public Client(String frameName){
        this.frame = new JDrawingFrame(frameName, this);
        this.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        this.frame.getRootPane().getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoker.undo();
            }
        });
    }

    public JDrawingFrame getFrame() {
        return frame;
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        SimpleShape shape;
        if (this.frame.getmPanel().contains(evt.getX(), evt.getY())) {
            shape = ShapeFactory.getInstance().createSimpleShape(frame.getmSelected(), evt.getX(), evt.getY());
            this.invoker.addCommand(new AddShapeCommand(frame, shape));
            this.invoker.execute();
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
     */
    @Override
    public void mousePressed(MouseEvent evt) {
        for(SimpleShape shape: frame.getShapesVisible()){
            if(shape.contains(evt.getX(),evt.getY())){
                frame.setMouseLastPosition(evt.getPoint());
                frame.setMovingShape(shape);
                Command c = new MoveShapeCommand(shape.getX(),
                                                 shape.getY(),
                                                 frame.getMovingShape(),
                                                 frame);
                this.invoker.addCommand(c);
                break;
            }
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent evt) {
        if(frame.getMovingShape() != null) {
            this.invoker.getCommand().setFinished(true);
            this.invoker.execute();
            frame.setMovingShape(null);
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent evt) {
        // des trucs à faire la (rien)
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseExited(MouseEvent evt) {
        frame.getmLabel().setText(" ");
        frame.getmLabel().repaint();
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent evt) {
        Point startPosition = frame.getMouseLastPosition();
        if(startPosition!=null) {
            int diffX = evt.getX() - startPosition.x;
            int diffY = evt.getY() - startPosition.y;
            MoveShapeCommand c = (MoveShapeCommand) this.invoker.getCommand();
            c.setDiff(diffX, diffY);
            this.invoker.execute();
            frame.paintComponents(frame.getGraphics());
            frame.setMouseLastPosition(evt.getPoint());
        }
    }


    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
     */
    @Override
    public void mouseMoved(MouseEvent evt) {
        frame.getmLabel().setText("(" + evt.getX() + "," + evt.getY() + ")");
    }
}
