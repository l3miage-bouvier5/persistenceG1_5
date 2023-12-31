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
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import fr.uga.miage.m1.persistence.Visitor;

class Circle implements SimpleShape {

    private SimpleShape group = null;

    int mX;

    int mY;

    private boolean isSelected = false;

    protected Circle(int x, int y) {
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
        GradientPaint gradient = new GradientPaint(mX, mY, Color.RED, mX + 50f, mY, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(mX, mY, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(mX, mY, 50, 50));
        if (isSelected || getGroup() != null) {
            int borderSize = 1;
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(borderSize));


            int minX = this.mX;
            int minY = this.mY;
            int width = 50 + borderSize;
            int height = 50 + borderSize;

            g2.draw(new Ellipse2D.Double(minX, minY, width, height));
        }
    }


    /**
     * Implements the <tt>Visitable.accept()</tt> method for the
     * visitor design pattern.
     * @param visitor The visitor.
     *                {@link Visitor#visit(Circle)}
     *                {@link Visitor#visit(Square)}
     *                {@link Visitor#visit(Triangle)}
     *
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    @Override
    public String getType() {
        return "circle";
    }

    @Override
    public boolean contains(int x, int y){
        Ellipse2D cercle = new Ellipse2D.Double(mX, mY, 50, 50);
        return cercle.contains(x, y);
    }


    public void move(int diffX, int diffY){
        this.mX += diffX;
        this.mY += diffY;
    }

    @Override
    public void goTo(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
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
