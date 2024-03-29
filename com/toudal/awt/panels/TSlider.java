package com.toudal.awt.panels;

import java.awt.*;
import java.awt.event.*;

/*
 * ===========================================================================
 * com.toudal.awt.panels
 *
 * Project: www.toudal.com in Java
 *
 * ===========================================================================
 * History:
 *
 * Version  Date        User  Comment
 * -------  ----------  ----  -----------------------------------------------
 *   1.0.0  03-11-2021   MST  Initial version
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: TOUDAL.WRITEME
 * ===========================================================================
 */
public class TSlider extends TPanel implements MouseListener, MouseMotionListener {

  /**
   *
   */
  private static final long serialVersionUID = -5312997080156335732L;

  private Component ivComponentLeft;
  private Component ivComponentRight;
  private Component ivComponentUp;
  private Component ivComponentDown;
  private int ivSliderPos;
  private int ivSliderSize;
  private Image ivImage;
  private Graphics ivGraphics;
  private boolean ivMouseDragging;
  private boolean ivVertical;
  private boolean ivSpeedTab;

  public TSlider() {
    ivComponentLeft = new Button("UpComponent");
    ivComponentRight = new Button("RightComponent");
    ivComponentUp = new Button("UpComponent");
    ivComponentDown = new Button("DownComponent");
    ivSliderPos = 0;
    ivSliderSize = 2;
    ivImage = null;
    ivGraphics = null;
    ivMouseDragging = false;
    ivVertical = true;
    ivSpeedTab = false;
    addMouseMotionListener(this);
    addMouseListener(this);
  }

  public TSlider(boolean pVertical) {
    this();
    ivVertical = pVertical;
  }

  public void mouseClicked(MouseEvent pMouseEvent) {
  }

  public void mouseDragged(MouseEvent pMouseEvent) {
    if (ivMouseDragging)
      if (ivVertical) {
        setSliderPos(pMouseEvent.getPoint().x);
        placeComponents();
      } else {
        setSliderPos(pMouseEvent.getPoint().y);
        placeComponents();
      }
  }

  public void mouseEntered(MouseEvent pMouseEvent) {
  }

  public void mouseExited(MouseEvent pMouseEvent) {
  }

  public void mouseMoved(MouseEvent pMouseEvent) {
  }

  public void mousePressed(MouseEvent pMouseEvent) {
    int i = pMouseEvent.getX();
    int j = pMouseEvent.getY();
    if (!ivVertical) {
      if (j > ivSliderPos && j < (ivSliderPos + ivSliderSize) - 2) {
        if (i > 2 && i < ivSliderSize - 2)
          setSliderPos(0);
        if (i > ivSliderSize && i < ivSliderSize * 2)
          setSliderPos(super.ivHeight - (ivSliderSize + 5));
      }
    } else if (i > ivSliderPos && i < (ivSliderPos + ivSliderSize) - 2) {
      if (j > 2 && j < 12)
        setSliderPos(0);
      if (j > 14 && j < 26)
        setSliderPos(super.ivWidth - (ivSliderSize + 5));
    }
    ivMouseDragging = true;
  }

  public void mouseReleased(MouseEvent pMouseEvent) {
    ivMouseDragging = false;
  }

  public void paint(Graphics pGraphics) {
    if (ivImage == null)
      if (!ivVertical) {
        ivImage = createImage(super.ivWidth, ivSliderSize);
        ivGraphics = ivImage.getGraphics();
        ivGraphics.setColor(getBackground().darker());
        ivGraphics.fillRect(0, 0, super.ivWidth, ivSliderSize);
        ivGraphics.setColor(getBackground().brighter());
        for (int i = 0; i < super.ivWidth; i += 2)
          ivGraphics.drawLine(i, 0, i, ivSliderSize);

        if (ivSpeedTab) {
          ivGraphics.fillRect(2, 1, ivSliderSize - 2, ivSliderSize - 2);
          ivGraphics.fillRect(ivSliderSize, 1, ivSliderSize - 2, ivSliderSize - 2);
        }
      } else {
        ivImage = createImage(ivSliderSize, super.ivHeight);
        ivGraphics = ivImage.getGraphics();
        ivGraphics.setColor(getBackground().darker());
        ivGraphics.fillRect(0, 0, ivSliderSize, super.ivHeight);
        ivGraphics.setColor(getBackground().brighter());
        for (int j = 0; j < super.ivHeight; j += 2)
          ivGraphics.drawLine(0, j, ivSliderSize, j);

        if (ivSpeedTab) {
          ivGraphics.fillRect(1, 2, ivSliderSize - 2, ivSliderSize - 2);
          ivGraphics.fillRect(1, ivSliderSize, ivSliderSize - 2, ivSliderSize - 2);
        }
      }
    update(pGraphics);
  }

  private void placeComponents() {
    if (super.ivWidth > 0 && super.ivHeight > 0) {
      if (ivComponentLeft != null)
        ivComponentLeft.setBounds(0, 0, ivSliderPos, super.ivHeight);
      if (ivComponentRight != null)
        ivComponentRight.setBounds(ivSliderPos + ivSliderSize, 0, super.ivWidth - ivSliderPos - ivSliderSize,
            super.ivHeight);
      if (ivComponentUp != null)
        ivComponentUp.setBounds(0, 0, super.ivWidth, ivSliderPos);
      if (ivComponentDown != null)
        ivComponentDown.setBounds(0, ivSliderPos + ivSliderSize, super.ivWidth,
            super.ivHeight - ivSliderPos - ivSliderSize);
    }
  }

  public void setComponentDown(Component pComponent) {
    if (ivComponentDown != null) {
      ivComponentDown.setVisible(false);
      remove(ivComponentDown);
    }
    ivComponentDown = pComponent;
    add(pComponent);
    pComponent.setVisible(true);
    placeComponents();
  }

  public void setComponentLeft(Component pComponent) {
    if (ivComponentLeft != null) {
      ivComponentLeft.setVisible(false);
      remove(ivComponentLeft);
    }
    ivComponentLeft = pComponent;
    add(pComponent);
    pComponent.setVisible(true);
    placeComponents();
  }

  public void setComponentRight(Component pComponent) {
    if (ivComponentRight != null) {
      ivComponentRight.setVisible(false);
      remove(ivComponentRight);
    }
    ivComponentRight = pComponent;
    add(pComponent);
    pComponent.setVisible(true);
    placeComponents();
  }

  public void setComponentUp(Component pComponent) {
    if (ivComponentUp != null) {
      ivComponentUp.setVisible(false);
      remove(ivComponentUp);
    }
    ivComponentUp = pComponent;
    add(pComponent);
    pComponent.setVisible(true);
    placeComponents();
  }

  public void setSliderPos(int pPosition) {
    ivSliderPos = pPosition;
    if (pPosition < 0)
      setSliderPos(0);
    if (ivVertical) {
      if (pPosition > super.ivWidth - ivSliderSize)
        setSliderPos(super.ivWidth - ivSliderSize);
    } else if (pPosition > super.ivHeight - ivSliderSize)
      setSliderPos(super.ivHeight - ivSliderSize);
    placeComponents();
  }

  public void setSliderSize(int pSize) {
    ivSliderSize = pSize;
  }

  public void setSpeedTab(boolean pSpeedTab) {
    ivSpeedTab = pSpeedTab;
  }

  protected void sizeChanged() {
    placeComponents();
  }

  public void update(Graphics pGraphics) {
    if (ivVertical)
      pGraphics.drawImage(ivImage, ivSliderPos, 0, this);
    else
      pGraphics.drawImage(ivImage, 0, ivSliderPos, this);
  }
}
