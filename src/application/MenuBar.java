package application;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

class MenuBar extends JMenuBar {

    private JFileChooser filechooser;

    /**
     * sets the menu bar of the MainFrame/game window
     *
     * @param m
     */
    MenuBar(MainFrame m) {

        ///////////////////////////////////////  Game menu ///////////////////////////////////////
        JMenu gameMenu;
        JMenuItem newGame, SaveOption, loadGame, exitOption, replayOption, pauseOption, recordOption;

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        gameMenu.getAccessibleContext().setAccessibleDescription(
                "The game menu"
        );
        add(gameMenu);

        // New game option
        newGame = new JMenuItem("New Game",
                KeyEvent.VK_N);
        newGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, InputEvent.ALT_MASK));
        newGame.getAccessibleContext().setAccessibleDescription(
                "Starts a new game");
        newGame.addActionListener((e)-> m.newGame());
        gameMenu.add(newGame);

        //Save game

        SaveOption = new JMenuItem("Save",
                KeyEvent.VK_S);
        SaveOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, InputEvent.ALT_MASK));
        SaveOption.getAccessibleContext().setAccessibleDescription(
                "Saves the current game");
        SaveOption.addActionListener((e)-> m.saveGame());

        gameMenu.add(SaveOption);

        // Load game option
        filechooser = new JFileChooser();
        loadGame = new JMenuItem("Load",
                KeyEvent.VK_L);
        loadGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, InputEvent.ALT_MASK));
        loadGame.getAccessibleContext().setAccessibleDescription(
                "Loads the game from a previous save");
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filechooser.setCurrentDirectory(new File("."));
                filechooser.setDialogTitle("Select Save File");
            //    filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if(filechooser.showOpenDialog(m)== JFileChooser.APPROVE_OPTION){
                    File directory = filechooser.getSelectedFile();
                 ;

                        if(directory.getName().equals("SavedGame.txt")){
                            m.loadGame(directory);
                        }

                }
            }
        });

        gameMenu.add(loadGame);


        //Pause game option
        pauseOption = new JMenuItem("Pause Game",
                KeyEvent.VK_P);
        pauseOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_5, InputEvent.ALT_MASK));
        pauseOption.getAccessibleContext().setAccessibleDescription(
                "Pauses the current game");
        pauseOption.addActionListener((e)-> m.pauseGame());

        gameMenu.add(pauseOption);


        // Exit option
        exitOption = new JMenuItem("Exit",
                KeyEvent.VK_E);
        exitOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_7, InputEvent.ALT_MASK));
        exitOption.getAccessibleContext().setAccessibleDescription(
                "Exits the game");
        exitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameMenu.add(exitOption);

        /////////////////////////////////////// Level menu ///////////////////////////////////////
        JMenu levelMenu;
        JMenuItem restartOption, nextOption, previousOption, goToOption;

        levelMenu = new JMenu("Level");
        levelMenu.setMnemonic(KeyEvent.VK_L);
        levelMenu.getAccessibleContext().setAccessibleDescription(
                "The level menu"
        );
        add(levelMenu);

        // Restart option
        restartOption = new JMenuItem("Restart",
                KeyEvent.VK_R);
        restartOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, InputEvent.CTRL_MASK));
        restartOption.getAccessibleContext().setAccessibleDescription(
                "Restarts the current level");
        levelMenu.add(restartOption);

        // Next option
        nextOption = new JMenuItem("Next",
                KeyEvent.VK_N);
        nextOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, InputEvent.CTRL_MASK));
        nextOption.getAccessibleContext().setAccessibleDescription(
                "Goes to the next level");
        levelMenu.add(nextOption);

        // Previous option
        previousOption = new JMenuItem("Previous Scheme",
                KeyEvent.VK_P);
        previousOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, InputEvent.CTRL_MASK));
        previousOption.getAccessibleContext().setAccessibleDescription(
                "Goes to the previous level");
        levelMenu.add(previousOption);

        // Go To option
        goToOption = new JMenuItem("Go To...",
                KeyEvent.VK_T);
        goToOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, InputEvent.CTRL_MASK));
        goToOption.getAccessibleContext().setAccessibleDescription(
                "Goes to a specific level");
        levelMenu.add(goToOption);


        /////////////////////////////////////// Help menu ///////////////////////////////////////

        JMenuItem contentsOption, howToOption, commandsOption,

                helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription(
                "The help menu"
        );
        add(helpMenu);

        // Contents option
        contentsOption = new JMenuItem("Contents",
                KeyEvent.VK_C);
        contentsOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, InputEvent.ALT_MASK));
        contentsOption.getAccessibleContext().setAccessibleDescription(
                "Specifies the contents of the game");
        helpMenu.add(contentsOption);

        // How to play option
        howToOption = new JMenuItem("How to play",
                KeyEvent.VK_H);
        howToOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, InputEvent.ALT_MASK));
        howToOption.getAccessibleContext().setAccessibleDescription(
                "Specifies how to play the game");
        howToOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Collect all the chips in the level to progress to the next level", "How to play", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(howToOption);

        // Commands option
        commandsOption = new JMenuItem("Commands",
                KeyEvent.VK_O);
        commandsOption.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, InputEvent.ALT_MASK));
        commandsOption.getAccessibleContext().setAccessibleDescription(
                "Specifies the various commands you can use in the game");

        helpMenu.add(commandsOption);

    }
}
