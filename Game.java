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
    
    int spAllyOne;
    int spAllyTwo;
    int spAllyThree;
    int spAllyFour;
    
    int hpEnemyOne;
    int hpEnemyTwo;
    int hpEnemyThree;
    int hpEnemyFour;
    
    // variables for game
    int turn = 0; // 0 = ally, 1 = enemy.
    int currentCharacter = 0; // what out of four characters turn it is
    // goes turn 0, character 1-2-3-4, then turn 1, character 1-2-3-4. then repeat
    int move;
    int difficulty;

    public void Start(){
        hpAllyOne = hpMaxAllyOne;
        hpAllyTwo = hpMaxAllyTwo;
        hpAllyThree = hpMaxAllyThree;
        hpAllyFour = hpMaxAllyFour;
        textHistory.add("Welcome to my shitty SMT knockoff for school");
        textHistory.add("made by taison");
        textHistory.add("xdd");
    }
    
    public void attack(int who){
        switch (who){ //depending on who is choosing to magic attack, display different moves
            case 0: // ame items
                break;
            case 1: // cendrillon items
                break;
            case 2: // orpheus items
                break;
            case 3: // robin hood items
                break;
        }
        
        // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3){
            currentCharacter++;
            page = 0;
        } else if (currentCharacter == 3 && turn == 0){
            page = 0;
            currentCharacter = 0;
            System.out.println("chaning to enemy turn");
            turn = 1;
        }
    }
    
    public void guard(String who){
        textHistory.add(who + " Guards");
        System.out.println("guard" + who);
        
        // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3){
            currentCharacter++;
            page = 0;
        } else if (currentCharacter == 3 && turn == 0){
            page = 0;
            currentCharacter = 0;
            System.out.println("chaning to enemy turn");
            turn = 1;
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
                                break;
                            case 1:
                                textHistory.add("Ame No Uzume uses Zephyr on Jack Frost");
                                System.out.println("Ame No Uzume uses Zephyr on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Ame No Uzume uses Zephyr on Legion");
                                System.out.println("Ame No Uzume uses Zephyr on Legion");
                                break;
                            case 3:
                                textHistory.add("Ame No Uzume uses Zephyr on Principality");
                                System.out.println("Ame No Uzume uses Zephyr on Principality");
                                break;
                        }
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
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Aqua Prison on Jack Frost");
                                System.out.println("Cendrillon uses Aqua Prison on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Aqua Prison on Legion");
                                System.out.println("Cendrillon uses Aqua Prison on Legion");
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses Aqua Prison on Principality");
                                System.out.println("Cendrillon uses Aqua Prison on Principality");
                                break;
                        }
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
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Lunar Rush on Jack Frost");
                                System.out.println("Orpheus uses Lunar Rush on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Lunar Rush on Legion");
                                System.out.println("Orpheus uses Lunar Rush on Legion");
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Lunar Rush on Principality");
                                System.out.println("Orpheus uses Lunar Rush on Principality");
                                break;
                        }
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
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Zenith Blade on Jack Frost");
                                System.out.println("Robin Hood uses Zenith Blade on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Zenith Blade on Legion");
                                System.out.println("Robin Hood uses Zenith Blade on Legion");
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Zenith Blade on Principality");
                                System.out.println("Robin Hood uses Zenith Blade on Principality");
                                break;
                        }
                        break;
                }
                break;
        }
        
        // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3){
            currentCharacter++;
            page = 0;
        } else if (currentCharacter == 3 && turn == 0){
            page = 0;
            currentCharacter = 0;
            System.out.println("chaning to enemy turn");
            turn = 1;
        }
    }
    
    public void magicMoveSelect(int move){
        move = move;
        switch (currentCharacter){ //depending on who is choosing to magic attack, display different moves
            case 0: // ame moves
                switch (move){
                    case 0:
                        page = 1; // single target enemy select
                        break;
                    case 1:
                        page = 5; // multi target enemy select
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 1: // cendrillon moves
                switch (move){
                    case 0:
                        page = 1; // single target enemy select
                        break;
                    case 1:
                        page = 5; // multi target enemy select
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 2: // orpheus moves
                switch (move){
                    case 0:
                        page = 1; // single target enemy select
                        break;
                    case 1:
                        page = 5; // multi target enemy select
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 3: // robin hood moves 
                switch (move){
                    case 0:
                        page = 1; // single target enemy select
                        break;
                    case 1:
                        page = 5; // multi target enemy select
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
        }
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
        
        // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3){
            currentCharacter++;
            page = 0;
        } else if (currentCharacter == 3 && turn == 0){
            page = 0;
            currentCharacter = 0;
            System.out.println("chaning to enemy turn");
            turn = 1;
        }
    }
    
    public void move(int move){
        switch (turn){
            case 0: // if its an allies turn
                switch (currentCharacter){ // which ally is it
                    case 0: // ame no uzume, wind
                        switch (move){ // what move are they making
                            case 0: // attack
                                break;
                            case 1: // guard
                                guard("Ame no uzume");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                items(currentCharacter);
                                break;
                        }
                        break;
                    case 1: // cendrillon, water damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                break;
                            case 1: // guard
                                guard("Cendrillon");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                break;
                        }
                        break;
                    case 2: // orpheus, moon damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                break;
                            case 1: // guard
                                guard("Orpheus");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                break;
                        }
                        break;
                    case 3: // robin hood, sun damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                break;
                            case 1: // guard
                                guard("Robin Hood");
                                break;
                            case 2: // magic
                                page = 2;
                                break;
                            case 3: // item
                                break;
                        }                        
                        break;
                }
                break;
            case 1: // if its an enemies turn
                switch (currentCharacter){
                    case 0:
                        // do the rng shit.
                        break;
                }
                break;
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
                System.out.println("difficulty : easy");
                difficulty = set;
                break;
            case 1:
                System.out.println("difficulty : medium");
                difficulty = set;
                break;
            case 2:
                System.out.println("difficulty : hard");
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
}
