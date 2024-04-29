/**
 * The actual game, window displays what goes on here
 *
 * @ ts
 * @ 11/04/24
 */
import java.util.*;
public class Game
{
    Images img = new Images();
    Random rng = new Random();
    
    // game variables
    int page = 0; // what page you are on, e.g: 0 = home, 1 = attack, 2 = magic, 3 = items (guard doesnt have a page)
    int selected = 0; // what option is currently being hovered, 0-3 left to right
    
    // variable for chat
    ArrayList<String> textHistory = new ArrayList<String>();
    
    // varialbes for characters
    int hpAllyOne;
    int hpMaxAllyOne;
    int hpAllyTwo;
    int hpMaxAllyTwo;
    int hpAllyThree;
    int hpMaxAllyThree;
    int hpAllyFour;
    int hpMaxAllyFour;
    boolean allyInjured = false;
    boolean allyDead = false;
    
    int spAllyOne;
    int spAllyTwo;
    int spAllyThree;
    int spAllyFour;
    int spMaxAllyOne = 150;
    int spMaxAllyTwo = 150;
    int spMaxAllyThree = 150;
    int spMaxAllyFour = 150;
    
    // first number = attack status, second = defense status, third = accuracy/evasion status. 
    //2 target stat up, 0.5 is target stat down
    int allyOneStats[] = new int[]{0,0,0};
    int allyTwoStats[] = new int[]{0,0,0};
    int allyThreeStats[] = new int[]{0,0,0};
    int allyFourStats[] = new int[]{0,0,0};
    int enemyOneStats[] = new int[]{0,0,0};
    int enemyTwoStats[] = new int[]{0,0,0};
    int enemyThreeStats[] = new int[]{0,0,0};
    int enemyFourStats [] = new int[]{0,0,0};
    
    int hpEnemyOne;
    int hpEnemyTwo;
    int hpEnemyThree;
    int hpEnemyFour;
    int hpMaxEnemyOne;
    int hpMaxEnemyTwo;
    int hpMaxEnemyThree;
    int hpMaxEnemyFour;
    boolean enemyInjured;
    boolean enemyDead;
    
    // variables for game
    int turn = 0; // 0 = ally, 1 = enemy.
    int currentCharacter = 0; // what out of four characters turn it is
    // goes turn 0, character 1-2-3-4, then turn 1, character 1-2-3-4. then repeat
    int move;
    int typeOfMove; // 0 = phys, 1 = magic
    int difficulty;
    double damageMultiplier;
    double damageTakenMultiplier;

    public void Start(){
        hpAllyOne = hpMaxAllyOne;
        hpAllyTwo = hpMaxAllyTwo;
        hpAllyThree = hpMaxAllyThree;
        hpAllyFour = hpMaxAllyFour;
        spAllyOne = spMaxAllyOne;
        spAllyTwo = spMaxAllyTwo;
        spAllyThree = spMaxAllyThree;
        spAllyFour = spMaxAllyFour;
        textHistory.add("Welcome to my shitty SMT knockoff for school");
        textHistory.add("made by taison");
        textHistory.add("xdd");
        setDifficulty(1);
    }
    
    public void move(int moveNew){ // you start here, from main menu page = 0. decisions will move you to next methods
        move = moveNew;
        switch (turn){
            case 0: // if its an allies turn
                switch (currentCharacter){ // which ally is it
                    case 0: // ame no uzume, wind
                        switch (moveNew){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Ame no uzume");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item 
                                page = 3;
                                break;
                        }
                        break;
                    case 1: // cendrillon, water damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Cendrillon");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                page = 3;
                                break;
                        }
                        break;
                    case 2: // orpheus, moon damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Orpheus");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                page = 3;
                                break;
                        }
                        break;
                    case 3: // robin hood, sun damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Robin Hood");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                page = 3;
                                break;
                        }                        
                        break;
                }
                break;
            case 1: // if its an enemies turn
                switch (currentCharacter){
                    case 0:
                        int decision = rng.nextInt(4);
                        if (enemyInjured){ // if enemy is injured and can be healed
                            if (decision > 2){ // 50 percent chance to use healing skill
                                textHistory.add("Archangel used healing skill");
                                System.out.println("Archangel healed everyone");
                            } else { // if heal was not chosen
                                decision = rng.nextInt(4);
                                switch (decision){
                                    default:
                                    System.out.println("Archangel magic attack");
                                    textHistory.add("Archangel magic attack");
                                    break;
                                case 1:
                                    System.out.println("Archangel buff/debuff");
                                    textHistory.add("Archangel buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Archangel physical attack");
                                    textHistory.add("Archangel physical attack");
                                    break;
                                }
                            }
                        } else { // if enemy is not injured, and no healing spell can be used
                            decision = rng.nextInt(4);
                            switch (decision){
                                default:
                                    System.out.println("Archangel magic attack");
                                    textHistory.add("Archangel magic attack");
                                    break;
                                case 1:
                                    System.out.println("Archangel buff/debuff");
                                    textHistory.add("Archangel buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Archangel physical attack");
                                    textHistory.add("Archangel physical attack");
                                    break;
                            }
                        }
                        break;
                    case 1:
                        decision = rng.nextInt(4);
                        if (enemyInjured){ // if enemy is injured and can be healed
                            if (decision > 2){ // 50 percent chance to use healing skill
                                textHistory.add("Jack Frost used healing skill");
                                System.out.println("Jack Frost healed everyone");
                            } else { // if heal was not chosen
                                decision = rng.nextInt(4);
                                switch (decision){
                                    default:
                                    System.out.println("Jack Frost magic attack");
                                    textHistory.add("Jack Frost magic attack");
                                    break;
                                case 1:
                                    System.out.println("Jack Frost buff/debuff");
                                    textHistory.add("Jack Frost buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Jack Frost physical attack");
                                    textHistory.add("Jack Frost physical attack");
                                    break;
                                }
                            }
                        } else { // if enemy is not injured, and no healing spell can be used
                            decision = rng.nextInt(4);
                            switch (decision){
                                default:
                                    System.out.println("Jack Frost magic attack");
                                    textHistory.add("Jack Frost magic attack");
                                    break;
                                case 1:
                                    System.out.println("Jack Frost buff/debuff");
                                    textHistory.add("Jack Frost buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Jack Frost physical attack");
                                    textHistory.add("Jack Frost physical attack");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        decision = rng.nextInt(4);
                        if (enemyInjured){ // if enemy is injured and can be healed
                            if (decision > 2){ // 50 percent chance to use healing skill
                                textHistory.add("Legion used healing skill");
                                System.out.println("Legion healed everyone");
                            } else { // if heal was not chosen
                                decision = rng.nextInt(4);
                                switch (decision){
                                    default:
                                    System.out.println("Legion magic attack");
                                    textHistory.add("Legion magic attack");
                                    break;
                                case 1:
                                    System.out.println("Legion buff/debuff");
                                    textHistory.add("Legion buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Legion physical attack");
                                    textHistory.add("Legion physical attack");
                                    break;
                                }
                            }
                        } else { // if enemy is not injured, and no healing spell can be used
                            decision = rng.nextInt(4);
                            switch (decision){
                                default:
                                    System.out.println("Legion magic attack");
                                    textHistory.add("Legion magic attack");
                                    break;
                                case 1:
                                    System.out.println("Legion buff/debuff");
                                    textHistory.add("Legion buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Legion physical attack");
                                    textHistory.add("Legion physical attack");
                                    break;
                            }
                        }
                        break;
                    case 3:
                        decision = rng.nextInt(4);
                        if (enemyInjured){ // if enemy is injured and can be healed
                            if (decision > 2){ // 50 percent chance to use healing skill
                                textHistory.add("Principality used healing skill");
                                System.out.println("Principality healed everyone");
                            } else { // if heal was not chosen
                                decision = rng.nextInt(4);
                                switch (decision){
                                    default:
                                    System.out.println("Principality magic attack");
                                    textHistory.add("Principality magic attack");
                                    break;
                                case 1:
                                    System.out.println("Principality buff/debuff");
                                    textHistory.add("Principality buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Principality physical attack");
                                    textHistory.add("Principality physical attack");
                                    break;
                                }
                            }
                        } else { // if enemy is not injured, and no healing spell can be used
                            decision = rng.nextInt(4);
                            switch (decision){
                                default:
                                    System.out.println("Principality magic attack");
                                    textHistory.add("Principality magic attack");
                                    break;
                                case 1:
                                    System.out.println("Principality buff/debuff");
                                    textHistory.add("Principality buff/debuff");
                                    break;
                                case 2:
                                    System.out.println("Principality physical attack");
                                    textHistory.add("Principality physical attack");
                                    break;
                            }
                        }
                        break;
                }
                break;
        }
    }
    
    void physical(int enemy){ // if from the main menu above you select attack, it will do a normal attack based on who you select
        switch (currentCharacter){
            case 0: // ame
                switch (enemy){
                    case 0:
                        textHistory.add("Ame No Uzume uses a basic attack on Archangel");
                        System.out.println("Ame No Uzume uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses a basic attack on Jack Frost");
                        System.out.println("Ame No Uzume uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses a basic attack on Legion");
                        System.out.println("Ame No Uzume uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses a basic attack on Principality");
                        System.out.println("Ame No Uzume uses a basic attack on Principality");
                        break;
                }
                break;
            case 1: // cendrillon
                switch (enemy){
                    case 0:
                        textHistory.add("Cendrillon uses a basic attack on Archangel");
                        System.out.println("Cendrillon uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Cendrillon uses a basic attack on Jack Frost");
                        System.out.println("Cendrillon uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Cendrillon uses a basic attack on Legion");
                        System.out.println("Cendrillon uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Cendrillon uses a basic attack on Principality");
                        System.out.println("Cendrillon uses a basic attack on Principality");
                        break;
                }
                break;
            case 2: // orpheus
                switch (enemy){
                    case 0:
                        textHistory.add("Orpheus uses a basic attack on Archangel");
                        System.out.println("Orpheus uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Orpheus uses a basic attack on Jack Frost");
                        System.out.println("Orpheus uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Orpheus uses a basic attack on Legion");
                        System.out.println("Orpheus uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Orpheus uses a basic attack on Principality");
                        System.out.println("Orpheus uses a basic attack on Principality");
                        break;
                }
                break;
            case 3: // robin hood
                switch (enemy){
                    case 0:
                        textHistory.add("Robin Hood uses a basic attack on Archangel");
                        System.out.println("Robin Hood uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Robin Hood uses a basic attack on Jack Frost");
                        System.out.println("Robin Hood uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Robin Hood uses a basic attack on Legion");
                        System.out.println("Robin Hood uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Robin Hood uses a basic attack on Principality");
                        System.out.println("Robin Hood uses a basic attack on Principality");
                        break;
                }
                break;
        }
        goNext();
    }
    
    public void guard(String who){ // if from the main menu above you select guard. it will do this
        textHistory.add(who + " Guards");
        System.out.println("guard" + who);
        goNext();
    }
    
    public void magicMoveSelect(int moveNew){ // if from above main menu, you choose magic, you get your selection of magic moves
        move = moveNew;
        switch (currentCharacter){ //depending on who is choosing to magic attack, display different moves
            case 0: // ame moves
                switch (move){
                    case 0: // zephyr
                        if (spAllyOne > 9){
                            typeOfMove = 1;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // monsoon
                        if (spAllyOne > 21){
                            typeOfMove = 1;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        // for example, single target physical move
                        // typeOfMove = 0;
                        // page = 1;
                        break;
                    case 3:
                        // for example, multi target physical move
                        // typeOfMove = 0;
                        // page = 5;
                        break;
                }
                break;
            case 1: // cendrillon moves
                switch (move){
                    case 0: // aqua prison
                        if (spAllyOne > 9){
                            typeOfMove = 1;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // surging tide
                        if (spAllyOne > 21){
                            typeOfMove = 1;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 2: // orpheus moves
                switch (move){
                    case 0: // lunar rush
                        if (spAllyOne > 9){
                            typeOfMove = 1;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // moonfall
                        if (spAllyOne > 21){
                            typeOfMove = 1;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 3: // robin hood moves 
                switch (move){
                    case 0: // zenith blade
                        if (spAllyOne > 9){
                            typeOfMove = 1;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // solar flare
                        if (spAllyOne > 21){
                            typeOfMove = 1;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
        }
    }  
    
    void magic(int enemy){
        switch (currentCharacter){
            case 0: // ame
                switch (move){
                    case 0: // zephyr
                        switch (enemy){
                            case 0:
                                textHistory.add("Ame No Uzume uses Zephyr on Archangel");
                                System.out.println("Ame No Uzume uses Zephyr on Archangel");
                                spAllyOne -= 9;
                                break;
                            case 1:
                                textHistory.add("Ame No Uzume uses Zephyr on Jack Frost");
                                System.out.println("Ame No Uzume uses Zephyr on Jack Frost");
                                spAllyOne -= 9;
                                break;
                            case 2:
                                textHistory.add("Ame No Uzume uses Zephyr on Legion");
                                System.out.println("Ame No Uzume uses Zephyr on Legion");
                                spAllyOne -= 9;
                                break;
                            case 3:
                                textHistory.add("Ame No Uzume uses Zephyr on Principality");
                                System.out.println("Ame No Uzume uses Zephyr on Principality");
                                spAllyOne -= 9;
                                break;
                        }
                        break;
                    case 1: // monsoon
                        textHistory.add("Ame No Uzume uses Monsoon on every enemy");
                        System.out.println("Ame No Uzume uses Monsoon on every enemy");
                        spAllyOne -= 21;
                        break;
                    case 2: // redemption
                        break;
                    case 3: // guardian angel
                        break;
                }
                break;
            case 1: // cendrillon
                switch (move){
                    case 0: // aqua prison
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses Aqua Prison on Archangel");
                                System.out.println("Cendrillon uses Aqua Prison on Archangel");
                                spAllyTwo -= 9;
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Aqua Prison on Jack Frost");
                                System.out.println("Cendrillon uses Aqua Prison on Jack Frost");
                                spAllyTwo -= 9;
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Aqua Prison on Legion");
                                System.out.println("Cendrillon uses Aqua Prison on Legion");
                                spAllyTwo -= 9;
                                    break;
                            case 3:
                                textHistory.add("Cendrillon uses Aqua Prison on Principality");
                                System.out.println("Cendrillon uses Aqua Prison on Principality");
                                spAllyTwo -= 9;
                                break;
                        }
                        break;
                    case 1: // surging tide
                        textHistory.add("Cendrillon uses Surging Tide on every enemy");
                        System.out.println("Cendrillon uses Surging Tide on every enemy");
                        spAllyTwo -= 21;
                        break;
                    case 2: // ability 3
                        break;
                    case 3: // ability 4
                        break;
                }
                break;
            case 2: // orpheus
                switch (move){
                    case 0: // lunar rush
                        switch (enemy){
                            case 0:
                                textHistory.add("Orpheus uses Lunar Rush on Archangel");
                                System.out.println("Orpheus uses Lunar Rush on Archangel");
                                spAllyThree -= 9;
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Lunar Rush on Jack Frost");
                                System.out.println("Orpheus uses Lunar Rush on Jack Frost");
                                spAllyThree -= 9;
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Lunar Rush on Legion");
                                System.out.println("Orpheus uses Lunar Rush on Legion");
                                spAllyThree -= 9;
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Lunar Rush on Principality");
                                System.out.println("Orpheus uses Lunar Rush on Principality");
                                spAllyThree -= 9;
                                break;
                        }
                        break;
                    case 1: // moon fall
                        textHistory.add("Orpheus uses Moonfall on every enemy");
                        System.out.println("Orpheus uses Moonfall on every enemy");
                        spAllyThree -= 21;
                        break;
                    case 2: // ability 3
                        break;
                    case 3: // ability 4
                        break;
                }
                break;
            case 3: // robin hood
                switch (move){
                    case 0: // zenith blade
                        switch (enemy){
                            case 0:
                                textHistory.add("Robin Hood uses Zenith Blade on Archangel");
                                System.out.println("Robin Hood uses Zenith Blade on Archangel");
                                spAllyFour -= 9;
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Zenith Blade on Jack Frost");
                                System.out.println("Robin Hood uses Zenith Blade on Jack Frost");
                                spAllyFour -= 9;
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Zenith Blade on Legion");
                                System.out.println("Robin Hood uses Zenith Blade on Legion");
                                spAllyFour -= 9;
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Zenith Blade on Principality");
                                System.out.println("Robin Hood uses Zenith Blade on Principality");
                                spAllyFour -= 9;
                                break;
                        }
                        break;
                    case 1:  // solar flare
                        textHistory.add("Robin Hood uses Solar Flare on every enemy");
                        System.out.println("Robin Hood uses Solar Flare on every enemy");
                        spAllyFour -= 21;
                        break;
                    case 2: // ability 3
                        break;
                    case 3: // ability 4
                        break;
                }
                break;
        }
        goNext();
    }
    
    public void items(int who){
        switch (who){ //depending on who is choosing items, display different items
            case 0: // ame items
                break;
            case 1: // cendrillon items
                break;
            case 2: // orpheus items
                break;
            case 3: // robin hood items
                break;
        }
        goNext();
    }
    
    void wait(int ms) { // the only part of my project taken from the internet
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void goNext(){
        // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3 && turn == 0){
            currentCharacter++;
            page = 0;
        } else if (currentCharacter < 3 && turn == 1){
            currentCharacter++;
            move(69);
        } else if (currentCharacter == 3 && turn == 1){
            page = 0;
            currentCharacter = 0;
            turn = 0;
            //System.out.println("turn " + turn);
        } else if (currentCharacter == 3 && turn  == 0){
            page = 420;
            currentCharacter = 0;
            turn = 1;
            //System.out.println("turn " + turn);
            move(69);
        }
    }
    
    public void checkInjured(){
        if (hpEnemyOne < hpMaxEnemyOne || hpEnemyTwo < hpMaxEnemyTwo || hpEnemyThree < hpMaxEnemyThree || hpEnemyFour < hpMaxEnemyFour){
            enemyInjured = true;
        } else {
            enemyInjured = false;
        }
        
        if (hpAllyOne < hpMaxAllyOne || hpAllyTwo < hpMaxAllyTwo || hpAllyThree < hpMaxAllyThree || hpAllyFour < hpMaxAllyFour){
            allyInjured = true;
        } else {
            allyInjured = false;
        }
        
        if (hpEnemyOne == 0 || hpEnemyTwo == 0 || hpEnemyThree == 0 || hpEnemyFour == 0){
            enemyDead = true;
        } else {
            enemyDead = false;
        }
        
        if (hpAllyOne == 0 || hpAllyTwo == 0 || hpAllyThree == 0 || hpAllyFour == 0){
            allyDead = true;
        } else {
            allyDead = false;
        }
    }
    
    public void openStats(int who){
        switch (who){
            case 0:
                textHistory.add("opened Ame No Uzume stats");
                break;
            case 1:
                textHistory.add("opened Cendrillon stats");
                break;
            case 2:
                textHistory.add("opened Orpheus stats");
                break;
            case 3:
                textHistory.add("opened Robin Hood stats");
                break;
            case 4:
                textHistory.add("opened Archangel stats");
                break;
            case 5:
                textHistory.add("opened Jack Frost stats");
                break;
            case 6:
                textHistory.add("opened Legion stats");
                break;
            case 7:
                textHistory.add("opened Principality stats");
                break;
        }
    }
    
    public void setDifficulty(int set){
        switch (set){
            case 0:
                //System.out.println("difficulty : easy");
                difficulty = set;
                damageMultiplier = 1.6;
                damageTakenMultiplier = 0.6;
                break;
            case 1:
                //System.out.println("difficulty : medium");
                damageMultiplier = 1;
                damageTakenMultiplier = 1;
                difficulty = set;
                break;
            case 2:
                //System.out.println("difficulty : hard");
                damageMultiplier = 0.6;
                damageTakenMultiplier = 1.6;
                difficulty = set;
                break;
        }
    }
    /**
     * ELEMENTS AND INDEX
     * 0 - fire
     * 1 - water
     * 2 - wind
     * 3 - earth
     * 4 - sun
     * 5 - moon
     * 6 - physicalr
     */
    
    /**
     * CHARACTERS AND INDEX
     * 0 - ame no uzume
     * 1 - cendrillon
     * 2 - orpheus
     * 3 - robin hood
     * 4 - archangel
     * 5 - jack frost
     * 6 - legion
     * 7 - principality
     */
    
    /**
     * PAGE AND INDEX
     * 0 = home main menu
     * 1 = select enemy (single target)
     * 2 = select magic ability
     * 3 = select item
     * 4 = select ally (single target for healing n buffs n such)
     * 5 = select enemy (multi target)
     * 6 = select ally (multi target for healing)
     * 
     */
}
