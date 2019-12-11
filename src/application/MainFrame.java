package application;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * MainFrame Class.
 * This class is responsible for initialising and maintaining the main window in which the game is played.
 * This involves initialising a menu bar, game panel and information panel.
 * The menu bar holds various menus related to game play (starting new games, loading saved games).
 * The game panel is responsible for loading the current game state, and responding to user input.
 * The info panel is responsible for representing relevant information for the game - current level, inventory etc.
 *
 * This GUI was designed and generated within the NetBeansIDE.
 *
 */
public abstract class MainFrame extends javax.swing.JFrame {

    protected abstract void redraw(Graphics g); // redraws the game on the graphics pane
    protected abstract void redrawInventory(Graphics g); // redraws the inventory graphics pane
    protected abstract void chipsLeftGraphics(Graphics g); // prints how many chips are left on the maze
    protected abstract void timeLeft(Graphics g); // prints the amount of time you have left on a level
    protected abstract void saveGame(); // saves the current game state
    protected abstract void newGame(); // restarts the game
    protected abstract void pauseGame(); // pauses the game and pauses the time left
    protected abstract void loadGame(File file); // loads in a games state that you have saved

    private void redraw() {
        this.repaint();
    } // redraws the graphics


    /**
     * Creates new form MainFrame
     * (constructor)
     */
    public MainFrame() {
        initComponents();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     *
     * Creates the entire layout of the game window
     */
    private void initComponents() {
        
        // Set defaults
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chip's Challenge!");
        setMinimumSize(new java.awt.Dimension(650, 500));
        setPreferredSize(new java.awt.Dimension(650, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);

        // Set up the swing components

        MenuBar menuBar1 = new MenuBar(this);

        JPanel gamePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JLabel contentPane = new JLabel();

        JLabel levelLabel = new JLabel();
        JLabel levelLabel2 = new JLabel();
        JLabel timeLabel = new JLabel();
        JLabel timeLabel2 = new JLabel();
        JLabel chipsLabel = new JLabel();

        JLabel invLabel = new JLabel();


        //Background Image
        ImageIcon backGround =
                createImageIcon();
        contentPane.setIcon( backGround );
        contentPane.setLayout( new BorderLayout() );
        setContentPane( contentPane );

        // Game Panel initialisation
        gamePanel.setBackground(new java.awt.Color(0, 0, 0));
        gamePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 356, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        // graphics pane
        JComponent drawing = new JComponent() {
            protected void paintComponent(Graphics g) {
                redraw(g);
            }
        };
        drawing.setSize(new Dimension(500, 500));
        drawing.setVisible(true);

        gamePanel.add(drawing);
        redraw();

        // Info Panel initialisation
        infoPanel.setBackground(new java.awt.Color(0, 255, 65));

        setLabel(levelLabel, "LEVEL:", "Current level");
        setLabel(levelLabel2, "1", "Current Level");
        setLabel(timeLabel, "TIME:", "Time remaining");
        JComponent timeLeft = new JComponent() {
            protected void paintComponent(Graphics g) {
                timeLeft(g);
            }
        };
        timeLeft.setSize(new Dimension(20, 20));
        timeLeft.setVisible(true);
        redraw();
        setLabel(chipsLabel, "CHIPS LEFT:", "Number of remaining chips");
        //setLabel(chipsLabel2, "10", "Number of remaining chips");
        JComponent chipsLeftGraphic = new JComponent() {
            protected void paintComponent(Graphics g) {
                chipsLeftGraphics(g);
            }
        };
        chipsLeftGraphic.setSize(new Dimension(20, 20));
        chipsLeftGraphic.setVisible(true);
        redraw();
        setLabel(invLabel, "INVENTORY:", "What's in Chap's inventory");

        // inventory graphics pane
        JComponent inventoryGraphics = new JComponent() {
            protected void paintComponent(Graphics g) {
                redrawInventory(g);
            }
        };
        inventoryGraphics.setSize(new Dimension(20, 20));
        inventoryGraphics.setVisible(true);
        redraw();

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        horizontalLayoutHelper(levelLabel, levelLabel2, timeLabel, timeLabel2, chipsLabel,timeLeft, chipsLeftGraphic, invLabel, inventoryGraphics, infoPanelLayout);
        verticalLayoutHelper(levelLabel, levelLabel2, timeLabel, timeLabel2, chipsLabel,timeLeft, chipsLeftGraphic, invLabel, inventoryGraphics, infoPanelLayout);

        // Menubar initialisation
        setJMenuBar(menuBar1);

        // Final layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50, 50, 50))
        );

        gamePanel.getAccessibleContext().setAccessibleName("gamePanel");
        gamePanel.getAccessibleContext().setAccessibleDescription("The panel where the game is displayed");

        getAccessibleContext().setAccessibleDescription("The game!");

        pack();
    }

    /**
     * assigns the vertical layout
     *
     * @param levelLabel
     * @param levelLabel2
     * @param timeLabel
     * @param timeLabel2
     * @param chipsLabel
     * @param timeLeft
     * @param chipsLeftGraphic
     * @param invLabel
     * @param inventoryGraphics
     * @param infoPanelLayout
     */
    private void verticalLayoutHelper(JLabel levelLabel, JLabel levelLabel2, JLabel timeLabel, JLabel timeLabel2, JLabel chipsLabel,JComponent timeLeft, JComponent chipsLeftGraphic, JLabel invLabel, JComponent inventoryGraphics, GroupLayout infoPanelLayout) {
        infoPanelLayout.setVerticalGroup(
                infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(levelLabel2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeLeft, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chipsLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chipsLeftGraphic, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inventoryGraphics, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    /**
     * assign the horizontal layout
     *
     * @param levelLabel
     * @param levelLabel2
     * @param timeLabel
     * @param timeLabel2
     * @param chipsLabel
     * @param timeLeft
     * @param chipsLeftGraphic
     * @param invLabel
     * @param inventoryGraphics
     * @param infoPanelLayout
     */
    private void horizontalLayoutHelper(JLabel levelLabel, JLabel levelLabel2, JLabel timeLabel, JLabel timeLabel2, JLabel chipsLabel, JComponent timeLeft, JComponent chipsLeftGraphic, JLabel invLabel, JComponent inventoryGraphics, GroupLayout infoPanelLayout) {
        infoPanelLayout.setHorizontalGroup(
                infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(infoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chipsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(levelLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(levelLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(timeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(timeLeft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chipsLeftGraphic, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(invLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inventoryGraphics, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }

    /**
     * sets the contents of a label
     *
     * @param label
     * @param name
     * @param tooltip
     */
    private void setLabel(JLabel label, String name, String tooltip) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(name);
        label.setToolTipText(tooltip);
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    private ImageIcon createImageIcon() {
        java.net.URL imgURL = getClass().getResource("matrix.gif");
        if (imgURL != null) {
            return new ImageIcon(imgURL, "GIF of the matrix");
        } else {
            System.err.println("Couldn't find file: " + "matrix.gif");
            return null;
        }
    }
}
