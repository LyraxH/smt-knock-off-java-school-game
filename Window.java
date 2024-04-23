
/**
 * Holds the window settings and stuff
 *
 * @ ts
 * @ 08/04/24 ->
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Window extends JFrame implements ActionListener, KeyListener, MouseListener
{
    Scanner input = new Scanner(System.in);
    Images img = new Images(); // imports images from other class
    Game game = new Game(); // imports data from game region
    // panel for different regions of the game
    JPanel gameWindow = new JPanel(); // where the characters are displayed
    JPanel enemyWindow = new JPanel(); // where enemy health is displayed
    JPanel allyWindow = new JPanel(); // where ally health is displayed
    JPanel moveWindow = new JPanel(); // where actions can be taken
    JPanel chatWindow = new JPanel(); // where text of the actions taken is displayed

    // chat window variables
    JLabel textUpdateOne = new JLabel("FILLER TEXT NUMBER ONE NO ONE WILL EVER SEE THIS XDD");
    JLabel textUpdateTwo = new JLabel("https://youtube.com/@lyraxh");
    JLabel textUpdateThree = new JLabel("ASDASDASD");

    // enemy window variables
    JButton enemyOneButton = new JButton("EnemyOne");
    JButton enemyTwoButton = new JButton("EnemyTwo");
    JButton enemyThreeButton = new JButton("EnemyThree");
    JButton enemyFourButton = new JButton("EnemyFour");
    JLabel enemyOneHPText = new JLabel("HP: 300 / 300");
    JLabel enemyTwoHPText = new JLabel("HP: 300 / 300");
    JLabel enemyThreeHPText = new JLabel("HP: 300 / 300");
    JLabel enemyFourHPText = new JLabel("HP: 300 / 300");

    // ally window variables
    JButton allyOneButton = new JButton("AllyOne");
    JButton allyTwoButton = new JButton("AllyTwo");
    JButton allyThreeButton = new JButton("AllyThree");
    JButton allyFourButton =  new JButton("AllyFour");
    JLabel allyOneHPText = new JLabel("HP: 200 / 200");
    JLabel allyTwoHPText = new JLabel("HP: 200 / 200");
    JLabel allyThreeHPText = new JLabel("HP: 200 / 200");
    JLabel allyFourHPText = new JLabel("HP: 200 / 200");
    JLabel allyOneSPText = new JLabel("SP: 150 / 150");
    JLabel allyTwoSPText = new JLabel("SP: 150 / 150");
    JLabel allyThreeSPText = new JLabel("SP: 150 / 150");
    JLabel allyFourSPText = new JLabel("SP: 150 / 150");

    //move window variables
    JLabel currentMoveText = new JLabel("Ally One");
    JButton moveButtonOne = new JButton(img.attackIcon);
    JButton moveButtonTwo = new JButton(img.unGuardIcon);
    JButton moveButtonThree = new JButton(img.unMagicIcon);
    JButton moveButtonFour = new JButton(img.unItemIcon);

    // game window variables
    JLabel allyOneSprite = new JLabel("ally one sprite");
    JLabel allyTwoSprite = new JLabel("ally two sprite");
    JLabel allyThreeSprite = new JLabel("ally three sprite");
    JLabel allyFourSprite = new JLabel("ally four sprite");
    JLabel enemyOneSprite = new JLabel("enemy one sprite");
    JLabel enemyTwoSprite = new JLabel("enemy two sprite");
    JLabel enemyThreeSprite = new JLabel("enemy three sprite");
    JLabel enemyFourSprite = new JLabel("enemy four sprite");

    // j menu variables
    JMenuBar menuBar = new JMenuBar();
    JMenu system = new JMenu("System");
    JMenu music = new JMenu("Music");
    JMenu difficulty = new JMenu("Difficulty");
    JMenu battleLog = new JMenu("Battle Log");
    JMenuItem quitGame = new JMenuItem("Quit Game");
    JMenuItem nextSong = new JMenuItem("Next Track");
    JMenuItem previousSong = new JMenuItem("Previous Track");
    JMenuItem pauseMusic = new JMenuItem("Pause Music");
    JMenuItem easyDif = new JMenuItem("Easy");
    JMenuItem mediumDif = new JMenuItem("Medium");
    JMenuItem hardDif = new JMenuItem("Hard");
    JMenuItem displayBattleLog = new JMenuItem("Display Battle Log");

    public Window(){
        game.Start();
        setImages();
        setMenu();
        // adding panel regions to the jframe
        this.add(gameWindow, BorderLayout.CENTER);
        this.add(enemyWindow, BorderLayout.LINE_START);
        this.add(allyWindow, BorderLayout.LINE_END);
        this.add(chatWindow, BorderLayout.PAGE_START);
        this.add(moveWindow, BorderLayout.PAGE_END);

        // adding variables to the chat window region
        chatWindow.setLayout(new BoxLayout(chatWindow, BoxLayout.PAGE_AXIS));
        textUpdateOne.setAlignmentX(CENTER_ALIGNMENT);
        textUpdateTwo.setAlignmentX(CENTER_ALIGNMENT);
        textUpdateThree.setAlignmentX(CENTER_ALIGNMENT);
        chatWindow.add(textUpdateOne);
        chatWindow.add(textUpdateTwo);
        chatWindow.add(textUpdateThree);

        // adding variables to the move window region
        moveWindow.setLayout(new GridLayout(1,0));

        // adding variables to the ally window region
        allyWindow.setLayout(new GridLayout(4,3, 0, 25));
        allyWindow.add(allyOneButton);
        allyWindow.add(allyOneHPText);
        allyWindow.add(allyOneSPText);

        allyWindow.add(allyTwoButton);
        allyWindow.add(allyTwoHPText);
        allyWindow.add(allyTwoSPText);

        allyWindow.add(allyThreeButton);
        allyWindow.add(allyThreeHPText);
        allyWindow.add(allyThreeSPText);

        allyWindow.add(allyFourButton);
        allyWindow.add(allyFourHPText);
        allyWindow.add(allyFourSPText);

        // adding variables to the enemy window region
        enemyWindow.setLayout(new GridLayout(4,2, 50, 25));
        enemyWindow.add(enemyOneButton);
        enemyWindow.add(enemyOneHPText);
        enemyWindow.add(enemyTwoButton);
        enemyWindow.add(enemyTwoHPText);
        enemyWindow.add(enemyThreeButton);
        enemyWindow.add(enemyThreeHPText);
        enemyWindow.add(enemyFourButton);
        enemyWindow.add(enemyFourHPText);
    
        gameWindow.setBackground(Color.blue);
        allyWindow.setBackground(Color.green);
        allyWindow.setPreferredSize(new Dimension(300, 0));
        enemyWindow.setBackground(Color.red);
        enemyWindow.setPreferredSize(new Dimension(250, 0));
        chatWindow.setBackground(Color.gray);
        chatWindow.setPreferredSize(new Dimension(0, 50));
        moveWindow.setBackground(Color.pink);
        moveWindow.setPreferredSize(new Dimension(0, 200));

        // adding variables to game window
        gameWindow.setLayout(new GridLayout(2, 4, 50, 100));
        gameWindow.add(enemyOneSprite);
        enemyOneSprite.setIcon(img.archangelIMG);
        gameWindow.add(enemyTwoSprite);
        enemyTwoSprite.setIcon(img.jackFrostIMG);
        gameWindow.add(enemyThreeSprite);
        enemyThreeSprite.setIcon(img.legionIMG);
        gameWindow.add(enemyFourSprite);
        enemyFourSprite.setIcon(img.principalityIMG);
        gameWindow.add(allyOneSprite);
        allyOneSprite.setIcon(img.ameNoUzumeIMG);
        gameWindow.add(allyTwoSprite);
        allyTwoSprite.setIcon(img.cendrillonIMG);
        gameWindow.add(allyThreeSprite);
        allyThreeSprite.setIcon(img.orpheusIMG);
        gameWindow.add(allyFourSprite);
        allyFourSprite.setIcon(img.robinHoodIMG);

        updateUI();
        initialize();
    }
    
    public void updateUI(){
        // UPDATE CURRENT MOVE TEXT
        switch (game.turn){
            case 0:
                switch (game.currentCharacter){
                    case 0:
                        currentMoveText.setText("Ame No Uzume");
                        break;
                    case 1:
                        currentMoveText.setText("Cendrillon");
                        break;
                    case 2:
                        currentMoveText.setText("Orpheus");
                        break;
                    case 3:
                        currentMoveText.setText("Robin Hood");
                        break;
                }
                break;
            case 1:
                switch (game.currentCharacter){
                    case 0:
                        currentMoveText.setText("Archangel");
                        break;
                    case 1:
                        currentMoveText.setText("Jack Frost");
                        break;
                    case 2:
                        currentMoveText.setText("Legion");
                        break;
                    case 3:
                        currentMoveText.setText("Principality");
                        break;
                }
                break;
        }
        
        //UPDATE TEXT HISTORY
        int size = game.textHistory.size();
        textUpdateOne.setText(game.textHistory.get(size - 3));
        textUpdateTwo.setText(game.textHistory.get(size - 2));
        textUpdateThree.setText(game.textHistory.get(size - 1));
        
        //UPDATE SELECTABLE ICONS
        if (game.turn == 0){
            moveWindow.add(currentMoveText);
            moveWindow.add(moveButtonOne);
            moveWindow.add(moveButtonTwo);
            moveWindow.add(moveButtonThree);
            moveWindow.add(moveButtonFour);
            switch (game.page){ // what menu
                case 0: // if main menu
                    switch (game.selected){
                        case 0: // if attack selected
                            moveButtonOne.setIcon(img.attackIcon);
                            moveButtonTwo.setIcon(img.unGuardIcon);
                            moveButtonThree.setIcon(img.unMagicIcon);
                            moveButtonFour.setIcon(img.unItemIcon);
                            break;
                        case 1: // if guard selected
                            moveButtonOne.setIcon(img.unAttackIcon);
                            moveButtonTwo.setIcon(img.guardIcon);
                            moveButtonThree.setIcon(img.unMagicIcon);
                            moveButtonFour.setIcon(img.unItemIcon);
                            break;
                        case 2: // if magic selected
                            moveButtonOne.setIcon(img.unAttackIcon);
                            moveButtonTwo.setIcon(img.unGuardIcon);
                            moveButtonThree.setIcon(img.magicIcon);
                            moveButtonFour.setIcon(img.unItemIcon);
                            break;
                        case 3: // if item selected
                            moveButtonOne.setIcon(img.unAttackIcon);
                            moveButtonTwo.setIcon(img.unGuardIcon);
                            moveButtonThree.setIcon(img.unMagicIcon);
                            moveButtonFour.setIcon(img.itemIcon);
                            break;
                    }
                    break;
                case 1: // if selecting which enemy to attack single target
                    switch (game.selected){
                        case 0:
                            moveButtonOne.setIcon(img.enemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 1:
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.enemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 2:
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.enemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 3:
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.enemyFour);
                            break;
                    }
                    break;
                case 2: // if in magic attack
                    if (game.turn == 0 ){ //if the turn is an ally that you can see abilities for 
                        switch (game.currentCharacter){
                            case 0: // ame no uzume
                                switch (game.selected){
                                    case 0: // if move one selected
                                        moveButtonOne.setIcon(img.zephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 1: // if move two selected
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.monsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 2: // if move three selected
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.magicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 3: // if move four selected
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.itemIcon);
                                        break;
                                }
                                break;
                            case 1: // cendrillon
                                switch (game.selected){
                                    case 0: // if move one selected
                                        moveButtonOne.setIcon(img.aquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 1: // if move two selected
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.surgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 2: // if move three selected
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.magicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 3: // if move four selected
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.itemIcon);
                                        break;
                                }
                                break;
                            case 2: // orpheus
                                switch (game.selected){
                                    case 0: // if move one selected
                                        moveButtonOne.setIcon(img.lunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 1: // if move two selected
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.moonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 2: // if move three selected
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.magicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 3: // if move four selected
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.itemIcon);
                                        break;
                                }
                                break;
                            case 3: // robin hood
                                switch (game.selected){
                                    case 0: // if move one selected
                                        moveButtonOne.setIcon(img.zenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 1: // if move two selected
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.solarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 2: // if move three selected
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.magicIcon);
                                        moveButtonFour.setIcon(img.unItemIcon);
                                        break;
                                    case 3: // if move four selected
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unMagicIcon);
                                        moveButtonFour.setIcon(img.itemIcon);
                                        break;
                                }
                                break;
                        }
                    } else { // if its on the enemies turn... then:
                        System.out.println("you have done something horribly wrong");
                    }
                    break;
            }
        } else if (game.turn == 1){
            moveWindow.removeAll();
            moveWindow.revalidate();
            moveWindow.repaint();
        } 
        
        //UPDATE ACTION COMMANDS FOR INTERACTABLE ICONS
        allyOneButton.setActionCommand("ameNoUzume");
        allyTwoButton.setActionCommand("cendrillon");
        allyThreeButton.setActionCommand("orpheus");
        allyFourButton.setActionCommand("robinHood");
        enemyOneButton.setActionCommand("archangel");
        enemyTwoButton.setActionCommand("jackFrost");
        enemyThreeButton.setActionCommand("legion");
        enemyFourButton.setActionCommand("principality");
        switch (game.page){
            case 0: // if main page
                moveButtonOne.setActionCommand("attack");
                moveButtonTwo.setActionCommand("guard");
                moveButtonThree.setActionCommand("magic");
                moveButtonFour.setActionCommand("item");
                break;
            case 1: // if enemy select
                moveButtonOne.setActionCommand("enemyOne");
                moveButtonTwo.setActionCommand("enemyTwo");
                moveButtonThree.setActionCommand("enemyThree");
                moveButtonFour.setActionCommand("enemyFour");
                break;
            case 2: // if magic
                moveButtonOne.setActionCommand("magicOne");
                moveButtonTwo.setActionCommand("magicTwo");
                moveButtonThree.setActionCommand("magicThree");
                moveButtonFour.setActionCommand("magicFour");
                break;
            case 3: // if item
                moveButtonOne.setActionCommand("itemOne");
                moveButtonTwo.setActionCommand("itemTwo");
                moveButtonThree.setActionCommand("itemThree");
                moveButtonFour.setActionCommand("itemFour");
                break;
            case 4: // for ally select (healing spells)
            moveButtonOne.setActionCommand("allyOne");
                moveButtonTwo.setActionCommand("allyTwo");
                moveButtonThree.setActionCommand("allyThree");
                moveButtonFour.setActionCommand("allyFour");
                break;
        }
    }
    
    void jRequestFocus(){
        this.requestFocus();
    }

    void initialize(){
        this.setTitle("SMT knock off in java swing for school");
        this.getContentPane().setPreferredSize(new Dimension (1280, 720));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        jRequestFocus();
        this.toFront();

        this.addKeyListener(this);
        allyOneButton.addActionListener(this);
        allyTwoButton.addActionListener(this);
        allyThreeButton.addActionListener(this);
        allyFourButton.addActionListener(this);
        enemyOneButton.addActionListener(this);
        enemyTwoButton.addActionListener(this);
        enemyThreeButton.addActionListener(this);
        enemyFourButton.addActionListener(this);
        moveButtonOne.addActionListener(this);
        moveButtonOne.addMouseListener(this);
        moveButtonTwo.addActionListener(this);
        moveButtonTwo.addMouseListener(this);
        moveButtonThree.addActionListener(this);
        moveButtonThree.addMouseListener(this);
        moveButtonFour.addActionListener(this);
        moveButtonFour.addMouseListener(this);

        quitGame.addActionListener(this);
        nextSong.addActionListener(this);
        previousSong.addActionListener(this);
        pauseMusic.addActionListener(this);
        displayBattleLog.addActionListener(this);
        easyDif.addActionListener(this);
        mediumDif.addActionListener(this);
        hardDif.addActionListener(this);

        gameWindow.setVisible(true);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(e);
        switch (cmd){
                // stat view clicks // 0 = fire, 1 = water, 2 = air, 3 = earth, 4 = sun, 5 = moon, 6 = phys
            case "ameNoUzume": // 0 = normal, 1 = weak, 2 = null, 3 = resist
                createStatsMenu("Ame-No-Uzume", 0, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(0);
                updateUI();
                break;            
            case "cendrillon":
                createStatsMenu("Cendrillon", 1, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(1);
                updateUI();
                break;
            case "orpheus":
                createStatsMenu("Orpheus", 2, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(2);
                updateUI();
                break;
            case "robinHood":
                createStatsMenu("Robin Hood", 3, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(3);
                updateUI();
                break;
            case "archangel":
                createStatsMenu("Archangel", 4, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(4);
                updateUI();
                break;
            case "jackFrost":
                createStatsMenu("Jack Frost", 5, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(5);
                updateUI();
                break;
            case "legion":
                createStatsMenu("Legion", 6, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(6);
                updateUI();
                break;
            case "principality":
                createStatsMenu("Principality", 7, 1, 0, 3, 0, 0, 2, 0);
                game.openStats(7);
                updateUI();
                break;

                // move button clicks
            case "attack":
                game.page = 1; // set page to attack
                break;
            case "guard":
                game.move(1);
                updateUI();
                break;
            case "magic":
                game.page = 2; // set page to magic
                updateUI();
                break;
            case "item":
                game.page = 3; // set page to item
                updateUI();
                break;
                
            case "magicOne":
                game.magicMoveSelect(0);
                break;
            case "magicTwo":
                game.magicMoveSelect(1);
                break;
            case "magicThree":
                game.magicMoveSelect(2);
                break;
            case "magicFour":
                game.magicMoveSelect(3);
                break;
                
                
            case "enemyOne":
                game.magic(0);
                break;
            case "enemyTwo":
                game.magic(1);
                break;
            case "enemyThree":
                game.magic(2);
                break;
            case "enemyFour":
                game.magic(3);
                break;
                
                

                // menu bar clicks
            case "Quit Game":
                System.exit(0);
                break;
            case "Next Track":
                break;
            case "Previous Track":
                break;
            case "Pause Music":
                break;
            case "Easy":
                game.setDifficulty(0);
                break;
            case "Medium":
                game.setDifficulty(1);
                break;
            case "Hard":
                game.setDifficulty(2);
                break;
            case "Display Battle Log":
                createBattleLog();
                break;
        }
        jRequestFocus();
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch (game.page){ // what menu are we in
            case 0: // if main page
                switch (keyCode){
                    case 37: // left arrow in main page
                        if (game.selected == 0){
                            game.selected = 0;
                        } else {
                            game.selected--;
                        }
                        updateUI();
                        break;
                    case 39: // right arrow in main page
                        if (game.selected == 3){
                            game.selected = 3;
                        } else {
                            game.selected++;
                        }
                        updateUI();
                        break;
                    case 90: // if z key pressed
                        switch (game.selected){
                            case 0:
                                moveButtonOne.doClick();
                                break;
                            case 1:
                                moveButtonTwo.doClick();
                                break;
                            case 2:
                                moveButtonThree.doClick();
                                break;
                            case 3:
                                moveButtonFour.doClick();
                                break;
                        }
                        break;
                }
                break;
            case 2:
                break;
        }
    }

    void createStatsMenu(String name, int character, int one, int two, int three, int four, int five, int six, int seven){
        JDialog box = new JDialog(this);
        box.setTitle(name + " stats");
        box.setBounds(200,400, 800, 200);
        box.setLayout(new GridLayout(2, 8, 20, 20));

        JLabel nameLabel = new JLabel(name);
        switch (character){
            case 0:
                box.add(img.ameNoUzume);
                break;
            case 1:
                box.add(img.cendrillon);
                break;
            case 2:
                box.add(img.orpheus);
                break;
            case 3:
                box.add(img.robinHood);
                break;
            case 4:
                box.add(img.archangel);
                break;
            case 5:
                box.add(img.jackFrost);
                break;
            case 6:
                box.add(img.legion);
                break;
            case 7:
                box.add(img.principality);
                break;
        }
        box.add(img.fire); // 1
        box.add(img.water); // 2
        box.add(img.earth); // 3
        box.add(img.air); // 4
        box.add(img.sun); // 5
        box.add(img.moon); // 6
        box.add(img.phys); // 7

        box.add(nameLabel); // adding name to the bottom row
        switch (one){
            case 0: // normal
                JLabel normal = new JLabel(img.normalIMG);
                box.add(normal);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (two){
            case 0: // normal
                JLabel normalTwo = new JLabel(img.normalIMG);
                box.add(normalTwo);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (three){
            case 0: // normal
                JLabel normalThree = new JLabel(img.normalIMG);
                box.add(normalThree);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (four){
            case 0: // normal
                JLabel normalFour = new JLabel(img.normalIMG);
                box.add(normalFour);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (five){
            case 0: // normal
                JLabel normalFive = new JLabel(img.normalIMG);
                box.add(normalFive);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (six){
            case 0: // normal
                JLabel normalSix = new JLabel(img.normalIMG);
                box.add(normalSix);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }
        switch (seven){
            case 0: // normal
                JLabel normalSeven = new JLabel(img.normalIMG);
                box.add(normalSeven);
                break;
            case 1: // weak
                box.add(img.weak);
                break;
            case 2: // null
                box.add(img.nullify);
                break;
            case 3: // resist
                box.add(img.resist);
                break;
        }

        box.toFront();
        box.setVisible(true);
    }

    public void createBattleLog(){
        JDialog box = new JDialog(this);
        box.setTitle("Battle Log");
        String a;
        String b;
        a = "0: " + game.textHistory.get(0) + " \n";
        for (int i = 1; i < game.textHistory.size(); i++){
            b = game.textHistory.get(i);
            b = i + ": " + b + " \n";
            a = a + b;
        }
        TextArea area = new TextArea(a);
        box.add(area);
        box.setBounds(400,200, 400,400);
        box.toFront();
        box.setVisible(true);
    }

    public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}

    public void mouseEntered(MouseEvent e){
        int hover = e.getXOnScreen();
        if (hover > 263 && hover < 518){ //button one
            game.selected = 0;
        } else if (hover > 519 && hover < 774) {// button two
            game.selected = 1;
        } else if (hover > 775 && hover < 1030){ // button 3
            game.selected = 2;
        } else if (hover > 1031 && hover < 1285){ // button 4
            game.selected = 3;
        }
        updateUI();
    }

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    void setMenu(){
        this.setJMenuBar(menuBar);
        menuBar.add(system);
        menuBar.add(music);
        menuBar.add(difficulty);
        menuBar.add(battleLog);

        system.add(quitGame);

        music.add(nextSong);
        music.add(previousSong);
        music.add(pauseMusic);

        difficulty.add(easyDif);
        difficulty.add(mediumDif);
        difficulty.add(hardDif);

        battleLog.add(displayBattleLog);
    }

    void setImages(){
        allyOneButton.setIcon(img.ameNoUzumeIMG);
        allyTwoButton.setIcon(img.cendrillonIMG);
        allyThreeButton.setIcon(img.orpheusIMG);
        allyFourButton.setIcon(img.robinHoodIMG);
        enemyOneButton.setIcon(img.archangelIMG);
        enemyTwoButton.setIcon(img.jackFrostIMG);
        enemyThreeButton.setIcon(img.legionIMG);
        enemyFourButton.setIcon(img.principalityIMG);
    }
}