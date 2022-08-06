package com.legends.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExtendedJPanel extends JPanel {

    private BufferedImage background;

    /**
     * Creates a new JPanel with the specified layout manager and buffering
     * strategy.
     *
     * @param layout           the LayoutManager to use
     * @param isDoubleBuffered a boolean, true for double-buffering, which
     *                         uses additional memory space to achieve fast, flicker-free
     */
    public ExtendedJPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Create a new buffered JPanel with the specified layout manager
     *
     * @param layout the LayoutManager to use
     */
    public ExtendedJPanel(LayoutManager layout) {
        super(layout);
    }

    /**
     * Creates a new <code>JPanel</code> with <code>FlowLayout</code>
     * and the specified buffering strategy.
     * If <code>isDoubleBuffered</code> is true, the <code>JPanel</code>
     * will use a double buffer.
     *
     * @param isDoubleBuffered a boolean, true for double-buffering, which
     *                         uses additional memory space to achieve fast, flicker-free
     *                         updates
     */
    public ExtendedJPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public ExtendedJPanel() {}

    public BufferedImage getImageBackground() {
        return background;
    }

    public void setImageBackground(BufferedImage background) {
        this.background = background;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
