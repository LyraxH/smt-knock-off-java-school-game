/**
 * Who:
 *      ts (Taison Shea, Sheata, whatever else you want to call me)
 * What:
 *      The actual window. This takes assets from the images class and chooses where to put them, and what to do with them
 * When:
 *      08/04/24 -> 02/08/24
 * Where:
 *      Wellington High School Com labs or at my house in Karori
 * Why:
 *      Because school wanted a project, and I wanted credits
 * How:
 *      With difficulty. Extensive trialling, and lots of testing and rewriting to optimize the best code I can
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Window extends JFrame implements ActionListener, KeyListener, MouseListener
{
    Scanner input = new Scanner(System.in);
    Images img = new Images(); // imports images from other class
    Game game = new Game(); // imports data from game region
    int tutorial = 0;
    JLabel tutorialContent = new JLabel();

    // chat window variables
    JLabel textUpdateOne = new JLabel("FILLER TEXT NUMBER ONE NO ONE WILL EVER SEE THIS XDD");
    JLabel textUpdateTwo = new JLabel("https://youtube.com/@lyraxh");
    JLabel textUpdateThree = new JLabel("ASDASDASD");

    // enemy window variables
    JButton enemyOneButton = new JButton();
    JButton enemyTwoButton = new JButton();
    JButton enemyThreeButton = new JButton();
    JButton enemyFourButton = new JButton();
    JLabel enemyOneHPText = new JLabel("300 / 300");
    JLabel enemyTwoHPText = new JLabel("300 / 300");
    JLabel enemyThreeHPText = new JLabel("300 / 300");
    JLabel enemyFourHPText = new JLabel("300 / 300");

    // ally window variables
    JButton allyOneButton = new JButton();
    JButton allyTwoButton = new JButton();
    JButton allyThreeButton = new JButton();
    JButton allyFourButton =  new JButton();
    JLabel allyOneHPText = new JLabel("HP: 200 / 200");
    JLabel allyTwoHPText = new JLabel("HP: 200 / 200");
    JLabel allyThreeHPText = new JLabel("HP: 200 / 200");
    JLabel allyFourHPText = new JLabel("HP: 200 / 200");
    JLabel allyOneSPText = new JLabel("SP: 150 / 150");
    JLabel allyTwoSPText = new JLabel("SP: 150 / 150");
    JLabel allyThreeSPText = new JLabel("SP: 150 / 150");
    JLabel allyFourSPText = new JLabel("SP: 150 / 150");

    //move window variables
    JButton backButton = new JButton(img.unBack);
    JButton moveButtonOne = new JButton(img.attackIcon);
    JButton moveButtonTwo = new JButton(img.unGuardIcon);
    JButton moveButtonThree = new JButton(img.unMagicIcon);
    JButton moveButtonFour = new JButton(img.unItemIcon);

    // game window variables
    JLabel allyOneSprite = new JLabel();
    JLabel allyTwoSprite = new JLabel();
    JLabel allyThreeSprite = new JLabel();
    JLabel allyFourSprite = new JLabel();
    JLabel enemyOneSprite = new JLabel();
    JLabel enemyTwoSprite = new JLabel();
    JLabel enemyThreeSprite = new JLabel();
    JLabel enemyFourSprite = new JLabel();

    // j menu variables
    JMenuBar menuBar = new JMenuBar();
    JMenu system = new JMenu("System");
    JMenu difficulty = new JMenu("Difficulty");
    JMenu battleLog = new JMenu("Battle Log");
    JMenu tutorialMenu = new JMenu("Instructions");
    JMenuItem openTutorial = new JMenuItem("Open Tutorial");
    JMenuItem goBackItem = new JMenuItem("Go Back");
    JMenuItem quitGame = new JMenuItem("Quit Game");
    JMenuItem easyDif = new JMenuItem("Easy");
    JMenuItem mediumDif = new JMenuItem("Medium");
    JMenuItem hardDif = new JMenuItem("Hard");
    JMenuItem displayBattleLog = new JMenuItem("Display Battle Log");
    
    // panel for different regions of the game
    JPanel moveWindow = new JPanel(); // where actions can be taken
    JPanel chatWindow = new JPanel(); // where text of the actions taken is displayed
    
    JLayeredPane enemyWindow = new JLayeredPane();
    JPanel enemyWindowBase = new JPanel();
    JPanel enemyWindowBackgroundPanel = new JPanel();
    JLabel enemyWindowBackground = new JLabel();
    
    JLayeredPane allyWindow = new JLayeredPane();
    JPanel allyWindowBase = new JPanel(); // where ally health is displayed
    JPanel allyWindowBackgroundPanel = new JPanel();
    JLabel allyWindowBackground = new JLabel();
    
    JLayeredPane gameWindow = new JLayeredPane();
    JPanel gameWindowBase = new JPanel(); // where the characters are displayed
    JPanel gameWindowStats = new JPanel(); // stat overlay
    JPanel gameWindowAttack = new JPanel(); // attack
    JPanel gameWindowDefense = new JPanel(); // defense
    JPanel gameWindowDead = new JPanel(); // skull emoji
    JPanel gameWindowAffinities = new JPanel();  // where weakness indicator is displayed
    JPanel gameWindowBackgroundPanel = new JPanel();
    JLabel gameWindowBackground = new JLabel();

    public Window(){
        game.Start();
        setImages();
        setMenu();
        // adding panel regions to the jframe
        this.setIconImage(img.LOGO.getImage());
        this.add(gameWindow, BorderLayout.CENTER);
        this.add(enemyWindow, BorderLayout.LINE_END);
        this.add(allyWindow, BorderLayout.LINE_START);
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
        moveWindow.setBackground(Color.magenta);
        allyWindow.setBackground(Color.green);

        // setting grid layouts to the panels required
        moveWindow.setLayout(new GridLayout(1,0));
        gameWindowStats.setLayout(new GridLayout(3, 4));
        gameWindowAttack.setLayout(new GridLayout(3, 4));
        gameWindowDefense.setLayout(new GridLayout(3, 4));
        gameWindowAffinities.setLayout(new GridLayout(3, 4));
        gameWindowDead.setLayout(new GridLayout(3,4));
        gameWindowBase.setLayout(new GridLayout(3, 4));
        allyWindowBase.setLayout(new GridLayout(4,3));
        enemyWindowBase.setLayout(new GridLayout(4,2));
        
        chatWindow.setPreferredSize(new Dimension(0, 50));
        moveWindow.setPreferredSize(new Dimension(0, 200));
        gameWindow.setPreferredSize(new Dimension(730, 470));
        allyWindow.setPreferredSize(new Dimension(300, 470));
        enemyWindow.setPreferredSize(new Dimension(250, 470));
        
        gameWindowDead.setBounds(0,-5,730,480);
        gameWindowStats.setBounds(0,-5,730,480);
        gameWindowAttack.setBounds(0,-5,730,480);
        gameWindowDefense.setBounds(0,-5,730,480);
        gameWindowAffinities.setBounds(0,-5,730,480);
        gameWindow.setBounds(0,-5,730,480);
        gameWindowBase.setBounds(0,-5,730,480);
        gameWindowBackgroundPanel.setBounds(0,-5,730,480);
        allyWindow.setBounds(0,-5,300,480);
        allyWindowBase.setBounds(0,-5,300,480);
        allyWindowBackgroundPanel.setBounds(0,-5,300,480);
        enemyWindow.setBounds(0,-5,250,480);
        enemyWindowBase.setBounds(0,-5,250,480);
        enemyWindowBackgroundPanel.setBounds(0,-5,250,480);
        
        cleanUp();

        //adding panels to enemy window
        enemyWindow.add(enemyWindowBackgroundPanel, new Integer(0), 0);
        enemyWindow.add(enemyWindowBase, new Integer(1), 0);
        // adding panels to ally window
        allyWindow.add(allyWindowBackgroundPanel, new Integer(0), 0);
        allyWindow.add(allyWindowBase, new Integer(1), 0);
        // adding panels to game window
        gameWindow.add(gameWindowBackgroundPanel, new Integer(0), 0);
        gameWindow.add(gameWindowBase, new Integer(1), 0);
        gameWindow.add(gameWindowAffinities, new Integer(2), 0);
        gameWindow.add(gameWindowStats, new Integer(3), 0);
        gameWindow.add(gameWindowAttack, new Integer(4), 0);
        gameWindow.add(gameWindowDefense, new Integer(5), 0);
        gameWindow.add(gameWindowDead, new Integer(6), 0);

        updateUI();
        initialize();
    }
    
    ActionListener advanceTurn = new ActionListener() { // only code taken from the internet. 
    public void actionPerformed(ActionEvent evt) { // imma be honest, i was never figuring this out on my own
        game.goNext();
        updateUI();
        }
    };
    
    public void clearAffinity(){
        img.enemyOneAffinity.setIcon(null);
        img.enemyTwoAffinity.setIcon(null);
        img.enemyThreeAffinity.setIcon(null);
        img.enemyFourAffinity.setIcon(null);
        img.allyOneAffinity.setIcon(null);
        img.allyTwoAffinity.setIcon(null);
        img.allyThreeAffinity.setIcon(null);
        img.allyFourAffinity.setIcon(null);
    }
    
    public void addAndRemove(){
        enemyOneSprite.setIcon(img.virtueIMG);
        enemyTwoSprite.setIcon(img.eerieIMG);
        enemyThreeSprite.setIcon(img.soulIMG);
        enemyFourSprite.setIcon(img.reignIMG);
        allyOneSprite.setIcon(img.aegisIMG);
        allyTwoSprite.setIcon(img.dawnIMG);
        allyThreeSprite.setIcon(img.sentinelIMG);
        allyFourSprite.setIcon(img.blazeIMG);
    }
    
    public void updateUI(){
        displayStatus(game.target, game.status);
        game.checkInjured();
        Timer timer = new Timer(3000, advanceTurn); // this is the only code i've taken from the internet, imma be honest here.
        timer.setRepeats(false);
        
        if (game.turn == 1){
            timer.start();
        }
        
        // UPDATE CURRENT MOVE TEXT
        switch (game.turn){
            case 0:
                switch (game.currentCharacter){
                    case 0:
                        backButton.setIcon(img.currentAegis);
                        addAndRemove();
                        allyOneSprite.setIcon(img.aegisSelected);
                        break;
                    case 1:
                        backButton.setIcon(img.currentDawn);
                        addAndRemove();
                        allyTwoSprite.setIcon(img.dawnSelected);
                        break;
                    case 2:
                        backButton.setIcon(img.currentSentinel);
                        addAndRemove();
                        allyThreeSprite.setIcon(img.sentinelSelected);
                        break;
                    case 3:
                        backButton.setIcon(img.currentBlaze);
                        addAndRemove();
                        allyFourSprite.setIcon(img.blazeSelected);
                        break;
                }
                img.turnIndicatorOne.setIcon(img.playerTurnOne);
                img.turnIndicatorTwo.setIcon(img.playerTurnTwo);
                img.turnIndicatorThree.setIcon(img.playerTurnThree);
                img.turnIndicatorFour.setIcon(img.playerTurnFour);
                break;
            case 1:
                switch (game.currentCharacter){
                    case 0:
                        backButton.setIcon(img.currentVirtue);
                        addAndRemove();
                        enemyOneSprite.setIcon(img.virtueSelected);
                        break;
                    case 1:
                        backButton.setIcon(img.currentEerie);
                        addAndRemove();
                        enemyTwoSprite.setIcon(img.eerieSelected);
                        break;
                    case 2:
                        backButton.setIcon(img.currentSoul);
                        addAndRemove();
                        enemyThreeSprite.setIcon(img.soulSelected);
                        break;
                    case 3:
                        backButton.setIcon(img.currentReign);
                        addAndRemove();
                        enemyFourSprite.setIcon(img.reignSelected);
                        break;
                }
                img.turnIndicatorOne.setIcon(img.enemyTurnOne);
                img.turnIndicatorTwo.setIcon(img.enemyTurnTwo);
                img.turnIndicatorThree.setIcon(img.enemyTurnThree);
                img.turnIndicatorFour.setIcon(img.enemyTurnFour);
                break;
        }
        displayWarnings(game.warning);
        
        //UPDATE ACTION COMMANDS FOR INTERACTABLE ICONS
        allyOneButton.setActionCommand("aegis");
        allyTwoButton.setActionCommand("dawn");
        allyThreeButton.setActionCommand("sentinel");
        allyFourButton.setActionCommand("blaze");
        enemyOneButton.setActionCommand("virtue");
        enemyTwoButton.setActionCommand("eerie");
        enemyThreeButton.setActionCommand("soul");
        enemyFourButton.setActionCommand("reign");
        switch (game.page){
            case 0: // if main page
                moveButtonOne.setActionCommand("attack");
                moveButtonTwo.setActionCommand("guard");
                moveButtonThree.setActionCommand("magic");
                moveButtonFour.setActionCommand("item");
                break;
            case 1: // if enemy select
                backButton.setActionCommand("back");
                moveButtonOne.setActionCommand("enemyOne");
                moveButtonTwo.setActionCommand("enemyTwo");
                moveButtonThree.setActionCommand("enemyThree");
                moveButtonFour.setActionCommand("enemyFour");
                break;
            case 2: // if magic
                backButton.setActionCommand("back");
                moveButtonOne.setActionCommand("magicOne");
                moveButtonTwo.setActionCommand("magicTwo");
                moveButtonThree.setActionCommand("magicThree");
                moveButtonFour.setActionCommand("magicFour");
                break;
            case 3: // if item
                backButton.setActionCommand("back");
                moveButtonOne.setActionCommand("itemOne");
                moveButtonTwo.setActionCommand("itemTwo");
                moveButtonThree.setActionCommand("itemThree");
                moveButtonFour.setActionCommand("itemFour");
                break;
            case 4: // for ally select (healing spells)
                backButton.setActionCommand("back");
                moveButtonOne.setActionCommand("allyOne");
                moveButtonTwo.setActionCommand("allyTwo");
                moveButtonThree.setActionCommand("allyThree");
                moveButtonFour.setActionCommand("allyFour");
                break;
            case 5: // if all enemy select
                backButton.setActionCommand("back");
                moveButtonOne.setActionCommand("allEnemies");
                moveButtonTwo.setActionCommand("allEnemies");
                moveButtonThree.setActionCommand("allEnemies");
                moveButtonFour.setActionCommand("allEnemies");
                break;
        }
        
        // UPDATE HP AND SP
        allyOneHPText.setText("       " + game.hpAlly[0] + " / " + game.hpMaxAlly[0]);
        allyOneSPText.setText("       " + game.spAlly[0] + " / " + game.spMaxAlly[0]);
        allyTwoHPText.setText("       " + game.hpAlly[1] + " / " + game.hpMaxAlly[1]);
        allyTwoSPText.setText("       " + game.spAlly[1] + " / " + game.spMaxAlly[1]);
        allyThreeHPText.setText("       " + game.hpAlly[2] + " / " + game.hpMaxAlly[2]);
        allyThreeSPText.setText("       " + game.spAlly[2] + " / " + game.spMaxAlly[2]);
        allyFourHPText.setText("       " + game.hpAlly[3] + " / " + game.hpMaxAlly[3]);
        allyFourSPText.setText("       " + game.spAlly[3] + " / " + game.spMaxAlly[3]);
        enemyOneHPText.setText("       " + game.hpEnemy[0] + " / " + game.hpMaxEnemy[0]);
        enemyTwoHPText.setText("       " + game.hpEnemy[1] + " / " + game.hpMaxEnemy[1]);
        enemyThreeHPText.setText("       " + game.hpEnemy[2] + " / " + game.hpMaxEnemy[2]);
        enemyFourHPText.setText("       " + game.hpEnemy[3] + " / " + game.hpMaxEnemy[3]);
        
        //UPDATE TEXT HISTORY
        int size = game.textHistory.size();
        textUpdateOne.setText(game.textHistory.get(size - 3));
        textUpdateTwo.setText(game.textHistory.get(size - 2));
        textUpdateThree.setText(game.textHistory.get(size - 1));
        //System.out.println(game.textHistory.get(size - 1));
        
        //UPDATE SELECTABLE ICONS
        if (game.turn == 0){
            chatWindow.setBackground(Color.green);
            //backButton.setEnabled(true);
            backButton.setActionCommand("back");
            moveWindow.add(backButton);
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
                            backButton.setIcon(img.back);
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 1:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.enemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 2:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.enemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 3:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.enemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 4:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.enemyFour);
                            break;
                    }
                    break;
                case 5: // if selecting which enemies to multi target (yes i know case 1: into case 5: is gross but id rather have the attacks together
                    switch (game.selected){
                        case 0: // back button
                            backButton.setIcon(img.back);
                            moveButtonOne.setIcon(img.unEnemyOne);
                            moveButtonTwo.setIcon(img.unEnemyTwo);
                            moveButtonThree.setIcon(img.unEnemyThree);
                            moveButtonFour.setIcon(img.unEnemyFour);
                            break;
                        case 1,2,3,4: // i could use default, but to be safe ill just use 1,2,3,4
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.enemyOne);
                            moveButtonTwo.setIcon(img.enemyTwo);
                            moveButtonThree.setIcon(img.enemyThree);
                            moveButtonFour.setIcon(img.enemyFour);
                            break;
                    }
                    break;
                case 2: // if in magic attack
                    if (game.turn == 0 ){ //if the turn is an ally that you can see abilities for
                        switch (game.currentCharacter){
                            case 0: // Aegis
                                switch (game.selected){
                                    case 0: // if move one selected
                                        backButton.setIcon(img.back);
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unRedemption);
                                        moveButtonFour.setIcon(img.unGuardianAngel);
                                        break;
                                    case 1: // if move one selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.zephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unRedemption);
                                        moveButtonFour.setIcon(img.unGuardianAngel);
                                        break;
                                    case 2: // if move two selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.monsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unRedemption);
                                        moveButtonFour.setIcon(img.unGuardianAngel);
                                        break;
                                    case 3: // if move three selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.redemption);
                                        moveButtonFour.setIcon(img.unGuardianAngel);
                                        break;
                                    case 4: // if move four selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZephyr); // single target wind
                                        moveButtonTwo.setIcon(img.unMonsoon); // multi target wind
                                        moveButtonThree.setIcon(img.unRedemption);
                                        moveButtonFour.setIcon(img.guardianAngel);
                                        break;
                                }
                                break;
                            case 1: // Dawn
                                switch (game.selected){
                                    case 0: // if move one selected
                                        backButton.setIcon(img.back);
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unSnipSnip);
                                        moveButtonFour.setIcon(img.unAtkDown);
                                        break;
                                    case 1: // if move one selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.aquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unSnipSnip);
                                        moveButtonFour.setIcon(img.unAtkDown);
                                        break;
                                    case 2: // if move two selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.surgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unSnipSnip);
                                        moveButtonFour.setIcon(img.unAtkDown);
                                        break;
                                    case 3: // if move three selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.snipSnip);
                                        moveButtonFour.setIcon(img.unAtkDown);
                                        break;
                                    case 4: // if move four selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unAquaPrison); // single target water
                                        moveButtonTwo.setIcon(img.unSurgingTide); // multi target water
                                        moveButtonThree.setIcon(img.unSnipSnip);
                                        moveButtonFour.setIcon(img.atkDownTwo);
                                        break;
                                }
                                break;
                            case 2: // Sentinel
                                switch (game.selected){
                                    case 0: // if move one selected
                                        backButton.setIcon(img.back);
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unShatteringStrike);
                                        moveButtonFour.setIcon(img.unDefBoost);
                                        break;
                                    case 1: // if move one selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.lunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unShatteringStrike);
                                        moveButtonFour.setIcon(img.unDefBoost);
                                        break;
                                    case 2: // if move two selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.moonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unShatteringStrike);
                                        moveButtonFour.setIcon(img.unDefBoost);
                                        break;
                                    case 3: // if move three selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.shatteringStrike);
                                        moveButtonFour.setIcon(img.unDefBoost);
                                        break;
                                    case 4: // if move four selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unLunarRush); // single target moon
                                        moveButtonTwo.setIcon(img.unMoonfall); // multi target moon
                                        moveButtonThree.setIcon(img.unShatteringStrike);
                                        moveButtonFour.setIcon(img.defBoost);
                                        break;
                                }
                                break;
                            case 3: // Blaze
                                switch (game.selected){
                                    case 0: // if move one selected
                                        backButton.setIcon(img.back);
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unSear);
                                        moveButtonFour.setIcon(img.unAtkBoost);
                                        break;
                                    case 1: // if move one selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.zenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unSear);
                                        moveButtonFour.setIcon(img.unAtkBoost);
                                        break;
                                    case 2: // if move two selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.solarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unSear);
                                        moveButtonFour.setIcon(img.unAtkBoost);
                                        break;
                                    case 3: // if move three selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.sear);
                                        moveButtonFour.setIcon(img.unAtkBoost);
                                        break;
                                    case 4: // if move four selected
                                        backButton.setIcon(img.unBack);
                                        moveButtonOne.setIcon(img.unZenithBlade); // single target sun
                                        moveButtonTwo.setIcon(img.unSolarFlare); // multi target sun
                                        moveButtonThree.setIcon(img.unSear);
                                        moveButtonFour.setIcon(img.atkBoost);
                                        break;
                                }
                                break;
                        }
                    }
                    break;
                case 3:
                    switch (game.selected){
                        case 0:
                            backButton.setIcon(img.back);
                            moveButtonOne.setIcon(img.unOracleLens);
                            moveButtonTwo.setIcon(img.unEverfrost);
                            moveButtonThree.setIcon(img.unLightningCrash);
                            moveButtonFour.setIcon(img.unCleanse);
                            break;
                        case 1:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.oracleLens);
                            moveButtonTwo.setIcon(img.unEverfrost);
                            moveButtonThree.setIcon(img.unLightningCrash);
                            moveButtonFour.setIcon(img.unCleanse);
                            break;
                        case 2:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unOracleLens);
                            moveButtonTwo.setIcon(img.everfrost);
                            moveButtonThree.setIcon(img.unLightningCrash);
                            moveButtonFour.setIcon(img.unCleanse);
                            break;
                        case 3:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unOracleLens);
                            moveButtonTwo.setIcon(img.unEverfrost);
                            moveButtonThree.setIcon(img.lightningCrash);
                            moveButtonFour.setIcon(img.unCleanse);
                            break;
                        case 4:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unOracleLens);
                            moveButtonTwo.setIcon(img.unEverfrost);
                            moveButtonThree.setIcon(img.unLightningCrash);
                            moveButtonFour.setIcon(img.cleanse);
                            break;
                    }
                    break;
                case 4: // single target ally healing
                    switch (game.selected){
                        case 0:
                            backButton.setIcon(img.back);
                            moveButtonOne.setIcon(img.unAllyOne);
                            moveButtonTwo.setIcon(img.unAllyTwo);
                            moveButtonThree.setIcon(img.unAllyThree);
                            moveButtonFour.setIcon(img.unAllyFour);
                            break;
                        case 1:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.allyOne);
                            moveButtonTwo.setIcon(img.unAllyTwo);
                            moveButtonThree.setIcon(img.unAllyThree);
                            moveButtonFour.setIcon(img.unAllyFour);
                            break;
                        case 2:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unAllyOne);
                            moveButtonTwo.setIcon(img.allyTwo);
                            moveButtonThree.setIcon(img.unAllyThree);
                            moveButtonFour.setIcon(img.unAllyFour);
                            break;
                        case 3:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unAllyOne);
                            moveButtonTwo.setIcon(img.unAllyTwo);
                            moveButtonThree.setIcon(img.allyThree);
                            moveButtonFour.setIcon(img.unAllyFour);
                            break;
                        case 4:
                            backButton.setIcon(img.unBack);
                            moveButtonOne.setIcon(img.unAllyOne);
                            moveButtonTwo.setIcon(img.unAllyTwo);
                            moveButtonThree.setIcon(img.unAllyThree);
                            moveButtonFour.setIcon(img.allyFour);
                            break;
                    }
                    break;
            }
        } else if (game.turn == 1){
            chatWindow.setBackground(Color.red);
            moveWindow.removeAll();
            //backButton.setEnabled(false);
            backButton.setActionCommand("DONOTHINGLMAO");
            moveWindow.add(backButton);
            moveWindow.repaint();
            moveWindow.revalidate();
        }
        
        // ALLY ONE ------ ALLY ONE
        if (game.allyStats[0][0] == 1){ // ally one attack is normal
            img.attackOverlay5.setIcon(img.atkNorm);
        } else if (game.allyStats[0][0] == 1.6){ // is increased
            img.attackOverlay5.setIcon(img.atkUp);
        } else if (game.allyStats[0][0] == 0.6){ // is decreased
            img.attackOverlay5.setIcon(img.atkDown);
        }
        if (game.allyStats[0][1] == 1){ // ally one defense is normal
            img.defenseOverlay5.setIcon(img.defNorm);
        } else if (game.allyStats[0][1] == 0.6){ // is increased
            img.defenseOverlay5.setIcon(img.defUp);
        } else if (game.allyStats[0][1] == 1.6){ // is decreased
            img.defenseOverlay5.setIcon(img.defDown);
        }
        // ALLY TWO ------ ALLY TWO
        if (game.allyStats[1][0] == 1){ // ally two attack is normal
            img.attackOverlay6.setIcon(img.atkNorm);
        } else if (game.allyStats[1][0] == 1.6){ // is increased
            img.attackOverlay6.setIcon(img.atkUp);
        } else if (game.allyStats[1][0] == 0.6){ // is decreased
            img.attackOverlay6.setIcon(img.atkDown);
        }
        if (game.allyStats[1][1] == 1){ // ally two defense is normal
            img.defenseOverlay6.setIcon(img.defNorm);
        } else if (game.allyStats[1][1] == 0.6){ // is increased
            img.defenseOverlay6.setIcon(img.defUp);
        } else if (game.allyStats[1][1] == 1.6){ // is decreased
            img.defenseOverlay6.setIcon(img.defDown);
        }
        // ALLY THREE ------ ALLY THREE
        if (game.allyStats[2][0] == 1){ // ally three attack is normal
            img.attackOverlay7.setIcon(img.atkNorm);
        } else if (game.allyStats[2][0] == 1.6){ // is increased
            img.attackOverlay7.setIcon(img.atkUp);
        } else if (game.allyStats[2][0] == 0.6){ // is decreased
            img.attackOverlay7.setIcon(img.atkDown);
        }
        if (game.allyStats[2][1] == 1){ // ally three defense is normal
            img.defenseOverlay7.setIcon(img.defNorm);
        } else if (game.allyStats[2][1] == 0.6){ // is increased
            img.defenseOverlay7.setIcon(img.defUp);
        } else if (game.allyStats[2][1] == 1.6){ // is decreased
            img.defenseOverlay7.setIcon(img.defDown);
        }
        // ALLY FOUR ------ ALLY FOUR
        if (game.allyStats[3][0] == 1){ // ally three attack is normal
            img.attackOverlay8.setIcon(img.atkNorm);
        } else if (game.allyStats[3][0] == 1.6){ // is increased
            img.attackOverlay8.setIcon(img.atkUp);
        } else if (game.allyStats[3][0] == 0.6){ // is decreased
            img.attackOverlay8.setIcon(img.atkDown);
        }
        if (game.allyStats[3][1] == 1){ // ally three defense is normal
            img.defenseOverlay8.setIcon(img.defNorm);
        } else if (game.allyStats[3][1] == 0.6){ // is increased
            img.defenseOverlay8.setIcon(img.defUp);
        } else if (game.allyStats[3][1] == 1.6){ // is decreased
            img.defenseOverlay8.setIcon(img.defDown);
        }
        // enemy ONE ------ enemy ONE
        if (game.enemyStats[0][0] == 1){ // enemy one attack is normal
            img.attackOverlay1.setIcon(img.atkNorm);
        } else if (game.enemyStats[0][0] == 1.6){ // is increased
            img.attackOverlay1.setIcon(img.atkUp);
        } else if (game.enemyStats[0][0] == 0.6){ // is decreased
            img.attackOverlay1.setIcon(img.atkDown);
        }
        if (game.enemyStats[0][1] == 1){ // enemy one defense is normal
            img.defenseOverlay1.setIcon(img.defNorm);
        } else if (game.enemyStats[0][1] == 0.6){ // is increased
            img.defenseOverlay1.setIcon(img.defUp);
        } else if (game.enemyStats[0][1] == 1.6){ // is decreased
            img.defenseOverlay1.setIcon(img.defDown);
        }
        // enemy TWO ------ enemy TWO
        if (game.enemyStats[1][0] == 1){ // enemy two attack is normal
            img.attackOverlay2.setIcon(img.atkNorm);
        } else if (game.enemyStats[1][0] == 1.6){ // is increased
            img.attackOverlay2.setIcon(img.atkUp);
        } else if (game.enemyStats[1][0] == 0.6){ // is decreased
            img.attackOverlay2.setIcon(img.atkDown);
        }
        if (game.enemyStats[1][1] == 1){ // enemy two defense is normal
            img.defenseOverlay2.setIcon(img.defNorm);
        } else if (game.enemyStats[1][1] == 0.6){ // is increased
            img.defenseOverlay2.setIcon(img.defUp);
        } else if (game.enemyStats[1][1] == 1.6){ // is decreased
            img.defenseOverlay2.setIcon(img.defDown);
        }
        // enemy THREE ------ enemy THREE
        if (game.enemyStats[2][0] == 1){ // enemy three attack is normal
            img.attackOverlay3.setIcon(img.atkNorm);
        } else if (game.enemyStats[2][0] == 1.6){ // is increased
            img.attackOverlay3.setIcon(img.atkUp);
        } else if (game.enemyStats[2][0] == 0.6){ // is decreased
            img.attackOverlay3.setIcon(img.atkDown);
        }
        if (game.enemyStats[2][1] == 1){ // enemy three defense is normal
            img.defenseOverlay3.setIcon(img.defNorm);
        } else if (game.enemyStats[2][1] == 0.6){ // is increased
            img.defenseOverlay3.setIcon(img.defUp);
        } else if (game.enemyStats[2][1] == 1.6){ // is decreased
            img.defenseOverlay3.setIcon(img.defDown);
        }
        // enemy FOUR ------ enemy FOUR
        if (game.enemyStats[3][0] == 1){ // enemy three attack is normal
            img.attackOverlay4.setIcon(img.atkNorm);
        } else if (game.enemyStats[3][0] == 1.6){ // is increased
            img.attackOverlay4.setIcon(img.atkUp);
        } else if (game.enemyStats[3][0] == 0.6){ // is decreased
            img.attackOverlay4.setIcon(img.atkDown);
        }
        if (game.enemyStats[3][1] == 1){ // enemy three defense is normal
            img.defenseOverlay4.setIcon(img.defNorm);
        } else if (game.enemyStats[3][1] == 0.6){ // is increased
            img.defenseOverlay4.setIcon(img.defUp);
        } else if (game.enemyStats[3][1] == 1.6){ // is decreased
            img.defenseOverlay4.setIcon(img.defDown);
        }
        
        if (game.enemyDead[0] == 1){
            img.deadOverlay1.setIcon(img.dead);
        } else {
            img.deadOverlay1.setIcon(null);
        }
        if (game.enemyDead[1] == 1){
            img.deadOverlay2.setIcon(img.dead);
        } else {
            img.deadOverlay2.setIcon(null);
        }
        if (game.enemyDead[2] == 1){
            img.deadOverlay3.setIcon(img.dead);
        } else {
            img.deadOverlay3.setIcon(null);
        }
        if (game.enemyDead[3] == 1){
            img.deadOverlay4.setIcon(img.dead);
        } else {
            img.deadOverlay4.setIcon(null);
        }
        if (game.allyDead[0] == 1){
            img.deadOverlay5.setIcon(img.dead);
        } else {
            img.deadOverlay5.setIcon(null);
        }
        if (game.allyDead[1] == 1){
            img.deadOverlay6.setIcon(img.dead);
        } else {
            img.deadOverlay6.setIcon(null);
        }
        if (game.allyDead[2] == 1){
            img.deadOverlay7.setIcon(img.dead);
        } else {
            img.deadOverlay7.setIcon(null);
        }
        if (game.allyDead[3] == 1){
            img.deadOverlay8.setIcon(img.dead);
        } else {
            img.deadOverlay8.setIcon(null);
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

        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        goBackItem.addActionListener(this);
        quitGame.addActionListener(this);
        displayBattleLog.addActionListener(this);
        easyDif.addActionListener(this);
        mediumDif.addActionListener(this);
        hardDif.addActionListener(this);
        openTutorial.addActionListener(this);

        gameWindow.setVisible(true);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(e);
        if (game.turn != 2 || game.turn != 3){
            switch (cmd){
                // move button clicks
                case "attack":
                    game.move(0); // set page to attack
                    updateUI();
                    break;
                case "guard":
                    game.move(1); // do guard
                    updateUI();
                    break;
                case "magic":
                    game.move(2); // set page to magic
                    updateUI();
                    break;
                case "item":
                    game.move(3); // set page to item
                    updateUI();
                    break;
                case "back":
                    if (game.turn == 0 && game.page != 0){
                        goBack();
                        updateUI();
                    }
                    break;
                    
                // magic button clicks
                case "magicOne":
                    game.magicMoveSelect(0); // magic move one (usually single target magic damage)
                    updateUI();
                    break;
                case "magicTwo":
                    game.magicMoveSelect(1); // magic move two (usually multi target magic damage)
                    updateUI();
                    break;
                case "magicThree":
                    game.magicMoveSelect(2); // magic move three (probably going to be single target physical damage or single target heal)
                    updateUI();
                    break;
                case "magicFour":
                    game.magicMoveSelect(3); // magic move three (probably going to be multi target heal, revive, or something else special)
                    updateUI();
                    break;   
                    
                // items button clicks
                case "itemOne": // oracle lens
                    game.typeOfMove = 6; // set type of move to item (6 on enemy single select will mean sweeper)
                    game.prevPage = 3;
                    game.page = 1; // set page to select enemy
                    updateUI();
                    break;
                case "itemTwo": // everfrost
                    game.typeOfMove = 6; // set type of move to item (item on ally single select will mean everfrost)
                    game.prevPage = 3;
                    game.page = 4; // set page to select ally
                    updateUI();
                    break;
                case "itemThree": // lightning crash
                    game.typeOfMove = 7; // set type of move to item (7 on enemy single select will mean shock)
                    game.prevPage = 3;
                    game.page = 1; // set page to select ally
                    updateUI();
                    break;
                case "itemFour":
                    game.cleanse();
                    updateUI();
                    break;
                    
                // single target enemy clicks
                case "enemyOne":
                    switch (game.typeOfMove){
                        case 0: // physical
                            game.physical(0);
                            updateUI();
                            break;
                        case 1: // magic
                            game.magic(0);
                            updateUI();
                            break;
                        case 5: // atk down
                            game.debuff(0);
                            updateUI();
                            break;
                        case 6: // sweeper lens
                            game.sweeper(0);
                            updateUI();
                            break;
                        case 7: // lightning crash
                            game.lightningCrash(0);
                            updateUI();
                            break;
                    }
                    break;
                case "enemyTwo":
                    switch (game.typeOfMove){
                        case 0: // physical
                            game.physical(1);
                            updateUI();
                            break;
                        case 1: // magic
                            game.magic(1);
                            updateUI();
                            break;
                        case 5: // atk down
                            game.debuff(1);
                            updateUI();
                            break;
                        case 6: // sweeper lens
                            game.sweeper(1);
                            updateUI();
                            break;
                        case 7: // lightning crash
                            game.lightningCrash(1);
                            updateUI();
                            break;
                    }
                    break;
                case "enemyThree":
                    switch (game.typeOfMove){
                        case 0: // physical
                            game.physical(2);
                            updateUI();
                            break;
                        case 1: // magic
                            game.magic(2);
                            updateUI();
                            break;
                        case 5: // atk down
                            game.debuff(2);
                            updateUI();
                            break;
                        case 6: // sweeper lens
                            game.sweeper(2);
                            updateUI();
                            break;
                        case 7: // lightning crash
                            game.lightningCrash(2);
                            updateUI();
                            break;
                    }
                    break;
                case "enemyFour":
                    switch (game.typeOfMove){
                        case 0: // physical
                            game.physical(3);
                            updateUI();
                            break;
                        case 1: // magic
                            game.magic(3);
                            updateUI();
                            break;
                        case 5: // atk down
                            game.debuff(3);
                            updateUI();
                            break;
                        case 6: // sweeper lens
                            game.sweeper(3);
                            updateUI();
                            break;
                        case 7: // lightning crash
                            game.lightningCrash(3);
                            updateUI();
                            break;
                    }
                    break;
                    
                // single target ally clicks
                case "allyOne":
                    switch (game.typeOfMove){
                        case 2: // helaing
                            if (game.hpAlly[0] == game.hpMaxAlly[0]){
                                game.warning = 1;
                                updateUI();
                            } else if (game.allyDead[0] == 1) {
                                game.warning = 1;
                                updateUI();
                            } else {
                                game.healing(0);
                                updateUI();
                            }
                            break;
                        case 3,4,5: // buffs
                            if (game.allyDead[0] == 1){
                                game.warning = 9;
                                updateUI();
                            } else {
                                game.boost(0);
                                updateUI();
                            }
                            break;
                        case 6: // everfrost
                            game.everfrost(0);
                            updateUI();
                            break;
                        case 7:
                            if (game.allyDead[0] == 1){
                                game.revive(0);
                                updateUI();
                            } else {
                                game.warning = 7;
                                updateUI();
                            }
                            break;
                    }
                    break;
                case "allyTwo":
                    switch (game.typeOfMove){
                        case 2: // healing
                            if (game.hpAlly[1] == game.hpMaxAlly[1]){
                                game.warning = 1;
                                updateUI();
                            } else if (game.allyDead[1] == 1) {
                                game.warning = 1;
                                updateUI();
                            } else  {
                                game.healing(1);
                                updateUI();
                            }
                            break;
                        case 3,4,5: // buffs
                            if (game.allyDead[1] == 1){
                                game.warning = 9;
                                updateUI();
                            } else {
                                game.boost(1);
                                updateUI();
                            }
                            break; 
                        case 6: // everfrost
                            game.everfrost(1);
                            updateUI();
                            break; 
                        case 7:
                            if (game.allyDead[1] == 1){
                                game.revive(1);
                                updateUI();
                            } else {
                                game.warning = 2;
                                updateUI();
                            }
                            break;
                    }
                    break;
                case "allyThree":
                    switch (game.typeOfMove){
                        case 2: // healing
                            if (game.hpAlly[2] == game.hpMaxAlly[2]){
                                game.warning = 1;
                                updateUI();
                            } else if (game.allyDead[2] == 1) {
                                game.warning = 1;
                                updateUI();
                            } else  {
                                game.healing(2);
                                updateUI();
                            }
                            break;
                        case 3,4,5: // buffs
                            if (game.allyDead[2] == 1){
                                game.warning = 9;
                                updateUI();
                            } else {
                                game.boost(2);
                                updateUI();
                            }
                            break;  
                        case 6: // everfrost
                            game.everfrost(2);
                            updateUI();
                            break;
                        case 7:
                            if (game.allyDead[2] == 1){
                                game.revive(2);
                                updateUI();
                            } else {
                                game.warning = 2;
                                updateUI();
                            }
                            break;
                    }
                    break;
                case "allyFour":
                    switch (game.typeOfMove){
                        case 2: // healing
                            if (game.hpAlly[3] == game.hpMaxAlly[3]){
                                game.warning = 1;
                                updateUI();
                            } else if (game.allyDead[3] == 1) {
                                game.warning = 1;
                            } else  {
                                game.healing(3);
                                updateUI();
                            }
                            break;
                        case 3,4,5: // buffs
                            if (game.allyDead[3] == 1){
                                game.warning = 9;
                                updateUI();
                            } else {
                                game.boost(3);
                                updateUI();
                            }
                            break;   
                        case 6: // everfrost
                            game.everfrost(3);
                            updateUI();
                            break;    
                        case 7:
                            if (game.allyDead[3] == 1){
                                game.revive(3);
                                updateUI();
                            } else {
                                game.warning = 2;
                                updateUI();
                            }
                            break; 
                    }
                    break;
                    
                // multi target spells
                case "allEnemies":
                    switch (game.typeOfMove){
                        case 0: // physical
                            System.out.println("physical hit all  move, which if you get here atm means its broken");
                            break;
                        case 1: // magic
                            game.magic(69);
                            updateUI();
                            break;
                    }
                    break;
    
                // menu bar clicks
                case "Go Back":
                    goBack();
                    break;
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
                case "Open Tutorial":
                    createTutorial();
                    break;
                    
                case "tutorialLeft":
                    if (tutorial == 0){
                        tutorial = 0;
                    } else {
                        tutorial--;
                    }
                    updateTutorial();
                    break;
                case "tutorialRight":
                    if (tutorial == 19){
                        tutorial = 19;
                    } else {
                        tutorial++;
                    }
                    updateTutorial();
                    break;
                    
                // createStatsMenu("charName", fire, water, air, earth, sun, moon, phys);
                //                    0 = normal, 1 = weak, 2 = resist, 3 = null, 4 = unknown
                case "aegis":
                    createStatsMenu("Aegis", 0, game.allyAffinities[0][0], game.allyAffinities[0][1], game.allyAffinities[0][2], game.allyAffinities[0][3], game.allyAffinities[0][4], game.allyAffinities[0][5], game.allyAffinities[0][6]);
                    updateUI();
                    break;            
                case "dawn":
                    createStatsMenu("Dawn", 1, game.allyAffinities[1][0], game.allyAffinities[1][1], game.allyAffinities[1][2], game.allyAffinities[1][3], game.allyAffinities[1][4], game.allyAffinities[1][5], game.allyAffinities[1][6]);
                    updateUI();
                    break;
                case "sentinel":
                    createStatsMenu("Sentinel", 2, game.allyAffinities[2][0], game.allyAffinities[2][1], game.allyAffinities[2][2], game.allyAffinities[2][3], game.allyAffinities[2][4], game.allyAffinities[2][5], game.allyAffinities[2][6]);
                    updateUI();
                    break;
                case "blaze":
                    createStatsMenu("Blaze", 3, game.allyAffinities[3][0], game.allyAffinities[3][1], game.allyAffinities[3][2], game.allyAffinities[3][3], game.allyAffinities[3][4], game.allyAffinities[3][5], game.allyAffinities[3][6]);
                    updateUI();
                    break;
                    
                case "virtue":
                    if (game.affinitiesKnown[0] == 1) {
                        createStatsMenu("Virtue", 4, game.enemyAffinities[0][0], game.enemyAffinities[0][1], game.enemyAffinities[0][2], game.enemyAffinities[0][3], game.enemyAffinities[0][4], game.enemyAffinities[0][5], game.enemyAffinities[0][6]);
                        updateUI();
                    } else {
                        createStatsMenu("Virtue", 4, 4, 4, 4, 4, 4, 4, 4);
                        updateUI();
                    }
                    break;
                case "eerie":
                    if (game.affinitiesKnown[1] == 1) {
                        createStatsMenu("Jack Frost", 5, game.enemyAffinities[1][0], game.enemyAffinities[1][1], game.enemyAffinities[1][2], game.enemyAffinities[1][3], game.enemyAffinities[1][4], game.enemyAffinities[1][5], game.enemyAffinities[1][6]);
                        updateUI();
                    } else {
                        createStatsMenu("Jack Frost", 5, 4, 4, 4, 4, 4, 4, 4);
                        updateUI();
                    }
                    break;
                case "soul":
                    if (game.affinitiesKnown[2] == 1) {
                        createStatsMenu("Soul", 6, game.enemyAffinities[2][0], game.enemyAffinities[2][1], game.enemyAffinities[2][2], game.enemyAffinities[2][3], game.enemyAffinities[2][4], game.enemyAffinities[2][5], game.enemyAffinities[2][6]);
                        updateUI();
                    } else {
                        createStatsMenu("Soul", 6, 4, 4, 4, 4, 4, 4, 4);
                        updateUI();
                    }
                    break;
                case "reign":
                    if (game.affinitiesKnown[3] == 1) {
                        createStatsMenu("Reign", 7, game.enemyAffinities[3][0], game.enemyAffinities[3][1], game.enemyAffinities[3][2], game.enemyAffinities[3][3], game.enemyAffinities[3][4], game.enemyAffinities[3][5], game.enemyAffinities[3][6]);
                        updateUI();
                    } else {
                        createStatsMenu("Reign", 7, 4, 4, 4, 4, 4, 4, 4);
                        updateUI();
                    }
                    break;
            }
        }
        jRequestFocus();
    }

    public void keyPressed(KeyEvent e){
        jRequestFocus();
        int keyCode = e.getKeyCode();
        //System.out.println(keyCode); // 49 = 1, 50 = 2, 51 = 3, 52 = 4, 53 = 5
        if (game.turn == 0){
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
                        case 49:
                            if (game.turn == 0){
                                moveButtonOne.doClick();
                            }
                            break;
                        case 50:
                            if (game.turn == 0){
                                moveButtonTwo.doClick();
                            }
                            break;
                        case 51:
                            if (game.turn == 0){
                                moveButtonThree.doClick();
                            }
                            break;
                        case 52:
                            if (game.turn == 0){
                                moveButtonFour.doClick();
                            }
                            break;
                        case 90: // if z key pressed
                            if (game.turn == 0){
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
                            }
                            break;
                    }
                    break;
                case 1,2,3,4,6: // if not main page
                    switch (keyCode){
                        case 37: // left arrow
                            if (game.selected == 0){
                                game.selected = 0;
                            } else {
                                game.selected--;
                            }
                            updateUI();
                            break;
                        case 39: // right arrow in main page
                            if (game.selected == 4){
                                game.selected = 4;
                            } else {
                                game.selected++;
                            }
                            updateUI();
                            break;
                        case 49:
                            goBack();
                            break;
                        case 50:
                            if (game.turn == 0){
                                moveButtonOne.doClick();
                            }
                            break;
                        case 51:
                            if (game.turn == 0){
                                moveButtonTwo.doClick();
                            }
                            break;
                        case 52:
                            if (game.turn == 0){
                                moveButtonThree.doClick();
                            }
                            break;
                        case 53:
                            if (game.turn == 0){
                                moveButtonFour.doClick();
                            }
                            break;
                        case 90: // if z key pressed
                            if (game.turn == 0){
                                switch (game.selected){
                                    case 0:
                                        goBack();
                                        break;
                                    case 1:
                                        moveButtonOne.doClick();
                                        break;
                                    case 2:
                                        moveButtonTwo.doClick();
                                        break;
                                    case 3:
                                        moveButtonThree.doClick();
                                        break;
                                    case 4:
                                        moveButtonFour.doClick();
                                        break;
                                }
                            }
                            break;
                    }
                    break;
                    case 5: // if target all
                    switch (keyCode){
                        case 37: // left arrow
                            if (game.selected == 0){
                                game.selected = 0;
                            } else {
                                game.selected--;
                            }
                            updateUI();
                            break;
                        case 39: // right arrow in main page
                            if (game.selected == 1){
                                game.selected = 1;
                            } else {
                                game.selected++;
                            }
                            updateUI();
                            break;
                        case 49:
                            goBack();
                            break;
                        case 50,51,52,53:
                            if (game.turn == 0){
                                moveButtonOne.doClick();
                            }
                            break;
                        case 90: // if z key pressed
                            if (game.turn == 0){
                                switch (game.selected){
                                    case 0:
                                        goBack();
                                        break;
                                    case 1:
                                        moveButtonOne.doClick();
                                        break;
                                }
                            }
                            break;
                    }
                    break;
            }
            
            if (keyCode == 27){
                goBack();
            }
        }
    }    
    
    void goBack(){
        if (game.turn == 0){
            if (game.page == 2 || game.page == 3){
                game.page = 0;
            } else {
                game.page = game.prevPage;
            }
            updateUI();
        }
    }
    
    void createStatsMenu(String name, int character, int one, int two, int three, int four, int five, int six, int seven){
        JDialog box = new JDialog(this);
        box.setTitle(name + " stats");
        box.setBounds(200,400, 800, 200);
        box.setLayout(new GridLayout(2, 8, 20, 20));
        box.setResizable(false);

        JLabel nameLabel = new JLabel(name);
        switch (character){
            case 0:
                box.add(img.aegis);
                break;
            case 1:
                box.add(img.dawn);
                break;
            case 2:
                box.add(img.sentinel);
                break;
            case 3:
                box.add(img.blaze);
                break;
            case 4:
                box.add(img.virtue);
                break;
            case 5:
                box.add(img.eerie);
                break;
            case 6:
                box.add(img.soul);
                break;
            case 7:
                box.add(img.reign);
                break;
        }
        box.add(img.elementOne); // 1
        box.add(img.elementTwo); // 2
        box.add(img.elementThree); // 3
        box.add(img.elementFour); // 4
        box.add(img.elementFive); // 5
        box.add(img.elementSix); // 6
        box.add(img.elementSeven); // 7

        box.add(nameLabel); // adding name to the bottom row
        switch (one){
            case 0: // normal
                img.affinityOne.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinityOne.setIcon(img.weak);
                break;
            case 2: // null
                img.affinityOne.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinityOne.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinityOne.setIcon(img.unknown);
                break;
        }
        switch (two){
            case 0: // normal
                img.affinityTwo.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinityTwo.setIcon(img.weak);
                break;
            case 2: // null
                img.affinityTwo.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinityTwo.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinityTwo.setIcon(img.unknown);
                break;
        }
        switch (three){
            case 0: // normal
                img.affinityThree.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinityThree.setIcon(img.weak);
                break;
            case 2: // null
                img.affinityThree.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinityThree.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinityThree.setIcon(img.unknown);
                break;
        }
        switch (four){
            case 0: // normal
                img.affinityFour.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinityFour.setIcon(img.weak);
                break;
            case 2: // null
                img.affinityFour.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinityFour.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinityFour.setIcon(img.unknown);
                break;
        }
        switch (five){
            case 0: // normal
                img.affinityFive.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinityFive.setIcon(img.weak);
                break;
            case 2: // null
                img.affinityFive.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinityFive.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinityFive.setIcon(img.unknown);
                break;
        }
        switch (six){
            case 0: // normal
                img.affinitySix.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinitySix.setIcon(img.weak);
                break;
            case 2: // null
                img.affinitySix.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinitySix.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinitySix.setIcon(img.unknown);
                break;
        }
        switch (seven){
            case 0: // normal
                img.affinitySeven.setIcon(img.normal);
                break;
            case 1: // weak
                img.affinitySeven.setIcon(img.weak);
                break;
            case 2: // null
                img.affinitySeven.setIcon(img.resist);
                break;
            case 3: // resist
                img.affinitySeven.setIcon(img.nullify);
                break;
            case 4: // unknown
                img.affinitySeven.setIcon(img.unknown);
                break;
        }
        
        box.add(img.affinityOne);
        box.add(img.affinityTwo);
        box.add(img.affinityThree);
        box.add(img.affinityFour);
        box.add(img.affinityFive);
        box.add(img.affinitySix);
        box.add(img.affinitySeven);
        
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
    
    public void createTutorial(){
        JDialog box = new JDialog(this);
        box.setTitle("Instructions");
        tutorial = 0;
        
        JButton tutorialPrev = new JButton(img.leftArrow);
        JButton tutorialNext = new JButton(img.rightArrow);
        tutorialPrev.addActionListener(this);
        tutorialNext.addActionListener(this);
        tutorialPrev.setActionCommand("tutorialLeft");
        tutorialNext.setActionCommand("tutorialRight");
        updateTutorial();
        
        tutorialPrev.setOpaque(false);
        tutorialPrev.setContentAreaFilled(false);
        tutorialPrev.setBorderPainted(false);
        tutorialNext.setOpaque(false);
        tutorialNext.setContentAreaFilled(false);
        tutorialNext.setBorderPainted(false);

        box.add(tutorialPrev, BorderLayout.LINE_START);
        box.add(tutorialContent, BorderLayout.CENTER);
        box.add(tutorialNext, BorderLayout.LINE_END);
        
        box.setBounds(400,200, 850, 400);
        box.setResizable(false);
        box.toFront();
        box.setVisible(true);
    }
    
    void updateTutorial(){
        switch (tutorial){
            case 0:
                tutorialContent.setIcon(img.tutorial1);
                break;
            case 1:
                tutorialContent.setIcon(img.tutorial2);
                break;
            case 2:
                tutorialContent.setIcon(img.tutorial3);
                break;
            case 3:
                tutorialContent.setIcon(img.tutorial4);
                break;
            case 4:
                tutorialContent.setIcon(img.tutorial5);
                break;
            case 5:
                tutorialContent.setIcon(img.tutorial6);
                break;
            case 6:
                tutorialContent.setIcon(img.tutorial7);
                break;
            case 7:
                tutorialContent.setIcon(img.tutorial8);
                break;
            case 8:
                tutorialContent.setIcon(img.tutorial9);
                break;
            case 9:
                tutorialContent.setIcon(img.tutorial10);
                break;
            case 10:
                tutorialContent.setIcon(img.tutorial11);
                break;
            case 11:
                tutorialContent.setIcon(img.tutorial12);
                break;
            case 12:
                tutorialContent.setIcon(img.tutorial13);
                break;
            case 13:
                tutorialContent.setIcon(img.tutorial14);
                break;
            case 14:
                tutorialContent.setIcon(img.tutorial15);
                break;
            case 15:
                tutorialContent.setIcon(img.tutorial16);
                break;
            case 16:
                tutorialContent.setIcon(img.tutorial17);
                break;
            case 17:
                tutorialContent.setIcon(img.tutorial18);
                break;
            case 18:
                tutorialContent.setIcon(img.tutorial19);
                break;
            case 19:
                tutorialContent.setIcon(img.tutorial20);
                break;
        }
    }

    public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}

    public void mouseEntered(MouseEvent e){
        int hover = e.getXOnScreen();
        if (game.page == 0){
            if (hover > 263 && hover < 518){ //button one
                game.selected = 0;
            } else if (hover > 519 && hover < 774) {// button two
                game.selected = 1;
            } else if (hover > 775 && hover < 1030){ // button 3
                game.selected = 2;
            } else if (hover > 1031 && hover < 1285){ // button 4
                game.selected = 3;
            }
        } else {
            if (hover > 267 && hover < 518){ //button one
                game.selected = 1;
            } else if (hover > 519 && hover < 774) {// button two
                game.selected = 2;
            } else if (hover > 775 && hover < 1030){ // button 3
                game.selected = 3;
            } else if (hover > 1031 && hover < 1285){ // button 4
                game.selected = 4;
            } else if (hover < 257) { // back button on pages that can go back
                game.selected = 0;
            }
        }
        if (game.turn == 0){
            updateUI();
        }
    }

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}
    
    public void displayWarnings(int warning){
        img.turnIndicatorOne.setIcon(img.warningLeft);
        img.turnIndicatorFour.setIcon(img.warningRight);
        switch (warning){
            case 420:
                switch (game.turn){
                    case 0: // allies
                        img.turnIndicatorOne.setIcon(img.playerTurnOne);
                        img.turnIndicatorTwo.setIcon(img.playerTurnTwo);
                        img.turnIndicatorThree.setIcon(img.playerTurnThree);
                        img.turnIndicatorFour.setIcon(img.playerTurnFour);
                        break;
                    case 1: // enemies
                        img.turnIndicatorOne.setIcon(img.enemyTurnOne);
                        img.turnIndicatorTwo.setIcon(img.enemyTurnTwo);
                        img.turnIndicatorThree.setIcon(img.enemyTurnThree);
                        img.turnIndicatorFour.setIcon(img.enemyTurnFour);
                        break;
                    case 2:
                        img.turnIndicatorOne.setIcon(img.youLoseOne);
                        img.turnIndicatorTwo.setIcon(img.youLoseTwo);
                        img.turnIndicatorThree.setIcon(img.youLoseThree);
                        img.turnIndicatorFour.setIcon(img.youLoseFour);
                        moveWindow.removeAll();
                        moveWindow.repaint();
                        moveWindow.revalidate();
                        break;
                    case 3:
                        img.turnIndicatorOne.setIcon(img.youWinOne);
                        img.turnIndicatorTwo.setIcon(img.youWinTwo);
                        img.turnIndicatorThree.setIcon(img.youWinThree);
                        img.turnIndicatorFour.setIcon(img.youWinFour);
                        moveWindow.removeAll();
                        moveWindow.repaint();
                        moveWindow.revalidate();
                        break;
                }
                break;
            case 0: // affinities already revealed
                img.turnIndicatorTwo.setIcon(img.affinitiesRevealed1);
                img.turnIndicatorThree.setIcon(img.affinitiesRevealed2);
                break;
            case 1: // cannot heal ally is full hp
                img.turnIndicatorTwo.setIcon(img.fullHP1);
                img.turnIndicatorThree.setIcon(img.fullHP2);
                break;
            case 2: // cannot revive, ally is alive
                img.turnIndicatorTwo.setIcon(img.isAlive1);
                img.turnIndicatorThree.setIcon(img.isAlive2);
                break;
            case 3: // cannot target, enemy is dead
                img.turnIndicatorTwo.setIcon(img.isDead1);
                img.turnIndicatorThree.setIcon(img.isDead2);
                break;
            case 4: // ally already has max sp
                img.turnIndicatorTwo.setIcon(img.maxSP1);
                img.turnIndicatorThree.setIcon(img.maxSP2);
                break;
            case 5: // not enough hp
                img.turnIndicatorTwo.setIcon(img.noHP1);
                img.turnIndicatorThree.setIcon(img.noHP2);
                break;
            case 6: // no one is injured cannot heal
                img.turnIndicatorTwo.setIcon(img.noInjured1);
                img.turnIndicatorThree.setIcon(img.noInjured2);
                break;
            case 7: // no one is dead cannot revive
                img.turnIndicatorTwo.setIcon(img.noOneDead1);
                img.turnIndicatorThree.setIcon(img.noOneDead2);
                break;
            case 8: // not enough sp
                img.turnIndicatorTwo.setIcon(img.noSP1);
                img.turnIndicatorThree.setIcon(img.noSP2);
                break;
            case 9: // not enough alive to boost
                img.turnIndicatorTwo.setIcon(img.noBoost1);
                img.turnIndicatorThree.setIcon(img.noBoost2);
                break;
        }
    }
    
    public void displayStatus(int target, int status){ // 0 = weak, 1 = resist, 2 = null
        if (!game.targetAll){ // if not targetting all, clear before 
            clearAffinity();
        } // displaying stats
        switch (target){
            case 0: // enemy one
                switch (status){
                    case 0:
                        img.enemyOneAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.enemyOneAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.enemyOneAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.enemyOneAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.enemyOneAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.enemyOneAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.enemyOneAffinity.setIcon(img.debuffed);
                        break;
                }
                break;
            case 1: // enemy two
                switch (status){
                    case 0:
                        img.enemyTwoAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.enemyTwoAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.enemyTwoAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.enemyTwoAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.enemyTwoAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.enemyTwoAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.enemyTwoAffinity.setIcon(img.debuffed);
                        break;
                }
                break;
            case 2: // enemy three
                switch (status){
                    case 0:
                        img.enemyThreeAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.enemyThreeAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.enemyThreeAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.enemyThreeAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.enemyThreeAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.enemyThreeAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.enemyThreeAffinity.setIcon(img.debuffed);
                        break;
                }
                break;
            case 3: // enemy four
                switch (status){
                    case 0:
                        img.enemyFourAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.enemyFourAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.enemyFourAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.enemyFourAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.enemyFourAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.enemyFourAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.enemyFourAffinity.setIcon(img.debuffed);
                        break;
                }
                break;
            case 4: // allyOne
                switch (status){
                    case 0:
                        img.allyOneAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.allyOneAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.allyOneAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.allyOneAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.allyOneAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.allyOneAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.allyOneAffinity.setIcon(img.debuffed);
                        break;
                    case 7:
                        img.allyOneAffinity.setIcon(img.guard);
                        break;
                }
                break;
            case 5: // ally two
                switch (status){
                    case 0:
                        img.allyTwoAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.allyTwoAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.allyTwoAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.allyTwoAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.allyTwoAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.allyTwoAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.allyTwoAffinity.setIcon(img.debuffed);
                        break;
                    case 7:
                        img.allyTwoAffinity.setIcon(img.guard);
                        break;
                }
                break;
            case 6: // ally three
                switch (status){
                    case 0:
                        img.allyThreeAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.allyThreeAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.allyThreeAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.allyThreeAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.allyThreeAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.allyThreeAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.allyThreeAffinity.setIcon(img.debuffed);
                        break;
                    case 7:
                        img.allyThreeAffinity.setIcon(img.guard);
                        break;
                }
                break;
            case 7: // ally four
                switch (status){
                    case 0:
                        img.allyFourAffinity.setIcon(img.weakOverlay);
                        break;
                    case 1:
                        img.allyFourAffinity.setIcon(img.resistOverlay);
                        break;
                    case 2:
                        img.allyFourAffinity.setIcon(img.nullOverlay);
                        break;
                    case 3:
                        img.allyFourAffinity.setIcon(img.normalOverlay);
                        break;
                    case 4:
                        img.allyFourAffinity.setIcon(img.healed);
                        break;
                    case 5:
                        img.allyFourAffinity.setIcon(img.buffed);
                        break;
                    case 6:
                        img.allyFourAffinity.setIcon(img.debuffed);
                        break;
                    case 7:
                        img.allyFourAffinity.setIcon(img.guard);
                        break;
                }
                break;
        }
    }    
    
    void setMenu(){
        this.setJMenuBar(menuBar);
        menuBar.add(system);
        menuBar.add(difficulty);
        menuBar.add(battleLog);
        menuBar.add(tutorialMenu);

        system.add(goBackItem);
        system.add(quitGame);

        difficulty.add(easyDif);
        difficulty.add(mediumDif);
        difficulty.add(hardDif);
        
        tutorialMenu.add(openTutorial);

        battleLog.add(displayBattleLog);
    }

    void setImages(){
        allyOneButton.setIcon(img.aegisIMG);
        allyTwoButton.setIcon(img.dawnIMG);
        allyThreeButton.setIcon(img.sentinelIMG);
        allyFourButton.setIcon(img.blazeIMG);
        enemyOneButton.setIcon(img.virtueIMG);
        enemyTwoButton.setIcon(img.eerieIMG);
        enemyThreeButton.setIcon(img.soulIMG);
        enemyFourButton.setIcon(img.reignIMG);
    }
    
    void cleanUp(){
        // enemy window adding variables
        enemyOneHPText.setForeground(Color.white);
        enemyTwoHPText.setForeground(Color.white);
        enemyThreeHPText.setForeground(Color.white);
        enemyFourHPText.setForeground(Color.white);
        allyOneHPText.setForeground(Color.white);
        allyTwoHPText.setForeground(Color.white);
        allyThreeHPText.setForeground(Color.white);
        allyFourHPText.setForeground(Color.white);
        allyOneSPText.setForeground(Color.white);
        allyTwoSPText.setForeground(Color.white);
        allyThreeSPText.setForeground(Color.white);
        allyFourSPText.setForeground(Color.white);
        enemyWindowBase.add(enemyOneButton);
        enemyWindowBase.add(enemyOneHPText);
        enemyWindowBase.add(enemyTwoButton);
        enemyWindowBase.add(enemyTwoHPText);
        enemyWindowBase.add(enemyThreeButton);
        enemyWindowBase.add(enemyThreeHPText);
        enemyWindowBase.add(enemyFourButton);
        enemyWindowBase.add(enemyFourHPText);
        
        // ally window adding variables
        allyWindowBase.add(allyOneButton);
        allyWindowBase.add(allyOneHPText);
        allyWindowBase.add(allyOneSPText);
        allyWindowBase.add(allyTwoButton);
        allyWindowBase.add(allyTwoHPText);
        allyWindowBase.add(allyTwoSPText);
        allyWindowBase.add(allyThreeButton);
        allyWindowBase.add(allyThreeHPText);
        allyWindowBase.add(allyThreeSPText);
        allyWindowBase.add(allyFourButton);
        allyWindowBase.add(allyFourHPText);
        allyWindowBase.add(allyFourSPText);
        
        // game window adding variables
        gameWindowDead.add(img.deadOverlay1);
        gameWindowDead.add(img.deadOverlay2);
        gameWindowDead.add(img.deadOverlay3);
        gameWindowDead.add(img.deadOverlay4);
        gameWindowDead.add(img.filler17);
        gameWindowDead.add(img.filler18);
        gameWindowDead.add(img.filler19);
        gameWindowDead.add(img.filler20);
        gameWindowDead.add(img.deadOverlay5);
        gameWindowDead.add(img.deadOverlay6);
        gameWindowDead.add(img.deadOverlay7);
        gameWindowDead.add(img.deadOverlay8);
        
        gameWindowStats.add(img.statOverlay1);
        gameWindowStats.add(img.statOverlay2);
        gameWindowStats.add(img.statOverlay3);
        gameWindowStats.add(img.statOverlay4);
        gameWindowStats.add(img.filler1);
        gameWindowStats.add(img.filler2);
        gameWindowStats.add(img.filler3);
        gameWindowStats.add(img.filler4);
        gameWindowStats.add(img.statOverlay5);
        gameWindowStats.add(img.statOverlay6);
        gameWindowStats.add(img.statOverlay7);
        gameWindowStats.add(img.statOverlay8);
        
        gameWindowAttack.add(img.attackOverlay1);
        gameWindowAttack.add(img.attackOverlay2);
        gameWindowAttack.add(img.attackOverlay3);
        gameWindowAttack.add(img.attackOverlay4);
        gameWindowAttack.add(img.filler5);
        gameWindowAttack.add(img.filler6);
        gameWindowAttack.add(img.filler7);
        gameWindowAttack.add(img.filler8);
        gameWindowAttack.add(img.attackOverlay5);
        gameWindowAttack.add(img.attackOverlay6);
        gameWindowAttack.add(img.attackOverlay7);
        gameWindowAttack.add(img.attackOverlay8);
        
        gameWindowDefense.add(img.defenseOverlay1);
        gameWindowDefense.add(img.defenseOverlay2);
        gameWindowDefense.add(img.defenseOverlay3);
        gameWindowDefense.add(img.defenseOverlay4);
        gameWindowDefense.add(img.filler9);
        gameWindowDefense.add(img.filler10);
        gameWindowDefense.add(img.filler11);
        gameWindowDefense.add(img.filler12);
        gameWindowDefense.add(img.defenseOverlay5);
        gameWindowDefense.add(img.defenseOverlay6);
        gameWindowDefense.add(img.defenseOverlay7);
        gameWindowDefense.add(img.defenseOverlay8);
        
        gameWindowBase.add(enemyOneSprite);
        gameWindowBase.add(enemyTwoSprite);
        gameWindowBase.add(enemyThreeSprite);
        gameWindowBase.add(enemyFourSprite);
        gameWindowBase.add(img.turnIndicatorOne);
        gameWindowBase.add(img.turnIndicatorTwo);
        gameWindowBase.add(img.turnIndicatorThree);
        gameWindowBase.add(img.turnIndicatorFour);
        gameWindowBase.add(allyOneSprite);
        gameWindowBase.add(allyTwoSprite);
        gameWindowBase.add(allyThreeSprite);
        gameWindowBase.add(allyFourSprite);
        
        gameWindowAffinities.add(img.enemyOneAffinity);
        gameWindowAffinities.add(img.enemyTwoAffinity);
        gameWindowAffinities.add(img.enemyThreeAffinity);
        gameWindowAffinities.add(img.enemyFourAffinity);
        gameWindowAffinities.add(img.blankOne);
        gameWindowAffinities.add(img.blankTwo);
        gameWindowAffinities.add(img.blankThree);
        gameWindowAffinities.add(img.blankFour);
        gameWindowAffinities.add(img.allyOneAffinity);
        gameWindowAffinities.add(img.allyTwoAffinity);
        gameWindowAffinities.add(img.allyThreeAffinity);
        gameWindowAffinities.add(img.allyFourAffinity);
        
        // setting backgrounds
        allyWindowBackgroundPanel.add(allyWindowBackground);
        allyWindowBackground.setIcon(img.allyPanel);
        
        enemyWindowBackgroundPanel.add(enemyWindowBackground);
        enemyWindowBackground.setIcon(img.enemyPanel);
        
        gameWindowBackgroundPanel.add(gameWindowBackground);
        gameWindowBackground.setIcon(img.gamePanel);
        
        // setting opaque
        allyOneButton.setOpaque(false);
        allyOneButton.setContentAreaFilled(false);
        allyOneButton.setBorderPainted(false);
        allyTwoButton.setOpaque(false);
        allyTwoButton.setContentAreaFilled(false);
        allyTwoButton.setBorderPainted(false);
        allyThreeButton.setOpaque(false);
        allyThreeButton.setContentAreaFilled(false);
        allyThreeButton.setBorderPainted(false);
        allyFourButton.setOpaque(false);
        allyFourButton.setContentAreaFilled(false);
        allyFourButton.setBorderPainted(false);
        allyOneButton.setOpaque(false);
        enemyOneButton.setContentAreaFilled(false);
        enemyOneButton.setBorderPainted(false);
        enemyTwoButton.setOpaque(false);
        enemyTwoButton.setContentAreaFilled(false);
        enemyTwoButton.setBorderPainted(false);
        enemyThreeButton.setOpaque(false);
        enemyThreeButton.setContentAreaFilled(false);
        enemyThreeButton.setBorderPainted(false);
        enemyFourButton.setOpaque(false);
        enemyFourButton.setContentAreaFilled(false);
        enemyFourButton.setBorderPainted(false);
        enemyWindowBase.setOpaque(false);
        allyWindowBase.setOpaque(false);
        allyWindowBackground.setOpaque(false);
        gameWindowBase.setOpaque(false);
        gameWindowDead.setOpaque(false);
        gameWindowStats.setOpaque(false);
        gameWindowAttack.setOpaque(false);
        gameWindowDefense.setOpaque(false);
        gameWindowAffinities.setOpaque(false);
        gameWindowBackgroundPanel.setOpaque(false);
    }
}