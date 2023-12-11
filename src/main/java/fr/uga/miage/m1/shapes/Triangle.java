package fr.uga.miage.m1.shapes;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * .
 */
import java.awt.*;

import fr.uga.miage.m1.persistence.Visitor;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
class Triangle implements SimpleShape {
    private SimpleShape group = null;

    int mX;

    int mY;

    private boolean isSelected = false;

    protected Triangle(int x, int y) {
        mX = x - 25;
        mY = y - 25;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(mX, mY, Color.GREEN, mX + 50f, mY, Color.WHITE);
        g2.setPaint(gradient);
        int[] xcoords = { mX + 25, mX, mX + 50 };
        int[] ycoords = {mY, mY + 50, mY + 50 };
        Polygon polygon = new Polygon(xcoords, ycoords, xcoords.length);
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.BLACK);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
        if (isSelected || getGroup() != null) {
            int borderSize = 1;
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(borderSize));
            g2.drawPolygon(polygon);
        }

    }

    /**
     * Implements the <tt>Visitable.accept()</tt> method for the
     * visitor design pattern.
     * @param visitor The visitor.
     * */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getX() {
        return mX;
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public int getY() {
        return mY;
    }

    @Override
    public String getType(){
        return "triangle";
    }
    
    @Override
    public void move(int diffX, int diffY){
        this.mX += diffX;
        this.mY += diffY;
    }
    @Override
    public boolean contains(int x, int y) {
        int[] xcoords = { mX + 25, mX, mX + 50 };
        int[] ycoords = { mY, mY + 50, mY + 50 };
        Polygon triangle = new Polygon(xcoords, ycoords, xcoords.length);

        return triangle.contains(x, y);
    }

    @Override
    public void goTo(int x, int y) {
        this.mX = x;
        this.mY = y;
    }


    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setGroup(SimpleShape group) {
        this.group = group;
    }

    @Override
    public SimpleShape getGroup() {
        return group;
    }
}
