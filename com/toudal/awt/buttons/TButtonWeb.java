package com.toudal.awt.buttons;

import java.awt.*;
import java.awt.event.MouseEvent;

/*
 * ===========================================================================
 * com.toudal.awt.buttons
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
public class TButtonWeb extends TButton {

  public static final int IMAGE_ENTERED = 1;

  public static final int IMAGE_EXITED = 0;

  public static final int IMAGE_PRESSED = 2;

  public static final int IMAGE_SPECIEL = 3;

  /**
   *
   */
  private static final long serialVersionUID = -6490993378276417356L;

  private Color ivColor;

  private int ivCurrentImage;

  private boolean ivDown;

  private Font ivFont;

  private Image ivImage[];

  private Point ivImagePoint;

  private Point ivLabelPoint;

  private boolean ivSticky;

  private String ivText;

  /**
   *
   */
  public TButtonWeb() {
    this(null);
  }

  /**
   *
   */
  public TButtonWeb(Image pImage[]) {
    ivCurrentImage = 0;
    ivImage = null;
    ivLabelPoint = new Point(10, 12);
    ivImagePoint = new Point(0, 0);
    ivColor = Color.darkGray;
    ivFont = new Font("Lucinda", 1, 12);
    ivText = "";
    ivWidth = 0;
    ivHeight = 0;
    ivDown = false;
    ivSticky = false;
    ivImage = pImage;
    ivWidth = pImage[0].getWidth(this);
    ivHeight = pImage[0].getHeight(this);
    ivCurrentImage = 0;
  }

  /**
   *
   */
  public TButtonWeb(Image pImage[], Point pPoint) {
    this(pImage);
    ivLabelPoint = pPoint;
  }

  /**
   *
   */
  public TButtonWeb(Image pImage[], Point pPoint, String pText) {
    this(pImage, pPoint);
    ivText = pText;
  }

  /**
   *
   */
  public TButtonWeb(Image pImage[], Point pPoint, String pText, Color pColor) {
    this(pImage, pPoint, pText);
    ivColor = pColor;
  }

  /**
   *
   */
  public TButtonWeb(Image pImage[], Point pPoint, String pText, Color pColor, Font pFont) {
    this(pImage, pPoint, pText, pColor);
    ivFont = pFont;
  }

  /**
   *
   */
  public void doUpdate(int i, String pText, Color pColor) {
    ivCurrentImage = i;
    ivText = pText;
    ivColor = pColor;
    if (!ivText.equalsIgnoreCase(""))
      repaint();
  }

  /**
   *
   */
  public boolean getSticky() {
    return ivSticky;
  }

  /**
   *
   */
  public boolean isDown() {
    return ivDown;
  }

  /**
   *
   */
  @Override
  public void mouseEntered(MouseEvent pMouseEvent) {
    if (ivLabelPoint.x != 0 || ivLabelPoint.y != 0) {
      ivCurrentImage = 1;
      super.mouseEntered(pMouseEvent);
    }
  }

  /**
   *
   */
  @Override
  public void mouseExited(MouseEvent pMouseEvent) {
    ivCurrentImage = 0;
    super.mouseExited(pMouseEvent);
  }

  /**
   *
   */
  @Override
  public void mousePressed(MouseEvent pMouseEvent) {
    ivCurrentImage = 2;
    super.mousePressed(pMouseEvent);
  }

  /**
   *
   */
  @Override
  public void mouseReleased(MouseEvent pMouseEvent) {
    ivCurrentImage = 3;
    super.mouseReleased(pMouseEvent);
  }

  /**
   *
   */
  @Override
  protected void paint() {
    super.ivGraphics.setColor(ivColor);
    super.ivGraphics.setFont(ivFont);
    switch (ivCurrentImage) {
    case 0: // '\0'
      super.ivGraphics.drawImage(ivImage[0], 0, 0, this);
      super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
      return;
    case 1: // '\001'
      super.ivGraphics.drawImage(ivImage[1], ivImagePoint.x, ivImagePoint.y, this);
      super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
      return;
    case 2: // '\002'
      super.ivGraphics.drawImage(ivImage[1], 0, 0, this);
      super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
      return;
    case 3: // '\003'
      super.ivGraphics.drawImage(ivImage[2], 0, 0, this);
      super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
      return;
    }
  }

  /**
   *
   */
  public void setDown(boolean pFlag) {
    ivDown = pFlag;
    if (!pFlag)
      ivCurrentImage = 0;
    else
      ivCurrentImage = 2;
    repaint();
  }

  /**
   *
   */
  public void setSticky(boolean pFlag) {
    ivSticky = pFlag;
  }

  /**
   *
   */
  @Override
  protected void sizeChanged() {
    repaint();
  }
}
