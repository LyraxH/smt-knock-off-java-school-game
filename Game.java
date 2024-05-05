/**
 * The actual game, window displays what goes on here
 *
 * @ ts
 * @ 11/04/24
 */
import java.util.*;
public class Game
{
    Random rng = new Random();
    
    // game variables
    int prevPage = 0;
    int page = 0; // what page you are on, e.g: 0 = home, 1 = attack, 2 = magic, 3 = items (guard doesnt have a page)
    int selected = 0; // what option is currently being hovered, 0-3 left to right
    int turn = 0; // 0 = ally, 1 = enemy.
    int currentCharacter = 0; // what out of four characters turn it is
    // goes turn 0, character 1-2-3-4, then turn 1, character 1-2-3-4. then repeat
    int move;
    
    // variable for chat
    ArrayList<String> textHistory = new ArrayList<String>();
    
    // varialbes for characters
    int hpAllyOne;
    int hpAllyTwo;
    int hpAllyThree;
    int hpAllyFour;
    int hpMaxAllyOne = 500;
    int hpMaxAllyTwo = 500;
    int hpMaxAllyThree = 500;
    int hpMaxAllyFour = 500;
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
    
    // first number = attack status, second = defense status, third = accuracy/evasion status. fourth = shock
    double allyOneStats[] = new double[]{0,0,0};
    double allyTwoStats[] = new double[]{0,0,0};
    double allyThreeStats[] = new double[]{0,0,0};
    double allyFourStats[] = new double[]{0,0,0};
    int concentrate[] = new int[]{0,0,0,0}; // 1 means that characters attack next turn will do more than double damage
    double enemyOneStats[] = new double[]{0,0,0,0};
    double enemyTwoStats[] = new double[]{0,0,0,0};
    double enemyThreeStats[] = new double[]{0,0,0,0};
    double enemyFourStats [] = new double[]{0,0,0,0};
    // 0 = normal, 1 = weak, 2 = null, 3 = resist, 4 = unknown
    int allyOneAffinity[] = new int[]{1,0,2,3,0,0,0};
    int allyTwoAffinity[] = new int[]{0,2,3,1,0,0,0};
    int allyThreeAffinity[] = new int[]{0,3,0,0,1,2,0};
    int allyFourAffinity[] = new int[]{3,0,0,0,2,1,0};
    int enemyOneAffinity[] = new int[]{0,0,0,0,0,0,0};
    int enemyTwoAffinity[] = new int[]{0,0,0,0,0,0,0};
    int enemyThreeAffinity[] = new int[]{0,0,0,0,0,0,0};
    int enemyFourAffinity[] = new int[]{0,0,0,0,0,0,0};
    int affinitiesKnown[] = new int[]{0,0,0,0}; // 1 unknown affinities, 1 = known affinities.
    
    int hpEnemyOne;
    int hpEnemyTwo;
    int hpEnemyThree;
    int hpEnemyFour;
    int hpMaxEnemyOne;
    int hpMaxEnemyTwo;
    int hpMaxEnemyThree;
    int hpMaxEnemyFour;
    boolean enemyInjured;
    int enemyInjuredWho[] = new int[]{0,0,0,0};
    boolean enemyDead;
    
    int typeOfMove; // 0 = phys, 1 = magic, 2 = healing, 3 = buff atk, 4 = buff def, 5 = buff agl, 6 = sweeper
    int difficulty;
    double damageMultiplier;
    double damageTakenMultiplier;

    public void Start(){
        initializeAffinities();
        
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
                                prevPage = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Ame no uzume");
                                break;
                            case 2: // magic
                                prevPage = 0;
                                page = 2;
                                break;
                            case 3: // item
                                prevPage = 0;
                                page = 3;
                                break;
                        }
                        break;
                    case 1: // cendrillon, water damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                prevPage = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Cendrillon");
                                break;
                            case 2: // magic
                                prevPage = 0;
                                page = 2;
                                break;
                            case 3: // item
                                prevPage = 0;
                                page = 3;
                                break;
                        }
                        break;
                    case 2: // orpheus, moon damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                prevPage = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Orpheus");
                                break;
                            case 2: // magic
                                prevPage = 0;
                                page = 2;
                                break;
                            case 3: // item
                                prevPage = 0;
                                page = 3;
                                break;
                        }
                        break;
                    case 3: // robin hood, sun damage
                        switch (move){ // what move are they making
                            case 0: // attack
                                typeOfMove = 0;
                                prevPage = 0;
                                page = 1;
                                break;
                            case 1: // guard
                                guard("Robin Hood");
                                break;
                            case 2: // magic
                                prevPage = 0;
                                page = 2;
                                break;
                            case 3: // item
                                prevPage = 0;
                                page = 3;
                                break;
                        }                        
                        break;
                }
                break;
            case 1: // if its an enemies turn
                switch (currentCharacter){
                    case 0:
                        boolean skip = false;
                        if (enemyOneStats[3] == 1){ // if afflicted with shock
                            int shock = rng.nextInt(10);
                            if (shock < 7){ // 80% chance you remain shocked
                                textHistory.add("Archangel is still shocked");
                                shock = rng.nextInt(10);
                                if (shock < 5){ // 40% chance you can move anyways
                                    skip = false;
                                } else { // 60% chance you cannot move
                                    skip = true;
                                }
                            } else { // 20% chance you remove shock
                                textHistory.add("Archangel has recovered from shock");
                                enemyOneStats[3] = 0;
                                skip = false;
                            }
                        }
                        if (skip){
                            textHistory.add("Archangel is shocked and cannot move");
                        } else {
                            enemyTurn(0);
                        }
                        break;
                }
                break;
        }
    }
    
    void enemyTurn(int enemy){
        int decision = rng.nextInt(4);
        if (enemyInjured){ // if an "ally"(enemy) is injured
            if (decision > 1){ // 50% chance to heal
                
            } else { // 50% chance to not
                decision = rng.nextInt(4);
                switch (decision){
                    case 0: // 25% chance to buff/debuff
                        break;
                    case 1,2: // 50% chance to magic attack
                        break;
                    case 3: // 25% chance to phys attack
                        break;
                }
            }
        }
    }
    
    void physical(int enemy){ // if from the main menu above you select attack, it will do a normal attack based on who you select
        switch (currentCharacter){
            case 0: // ame
                switch (enemy){
                    case 0:
                        textHistory.add("Ame No Uzume uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses a basic attack on Principality");
                        break;
                }
                break;
            case 1: // cendrillon
                switch (move){
                    case 2:
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses Snip Snip on Archangel");
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Snip Snip on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Snip Snip on Legion");
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses Snip Snip on Principality");
                                break;
                        }
                        hpAllyTwo -= 40;
                        break;
                    default:
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses a basic attack on Archangel");
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses a basic attack on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses a basic attack on Legion");
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses a basic attack on Principality");
                                break;
                        }
                        break;
                }
                break;
            case 2: // orpheus
                switch (enemy){
                    case 0:
                        textHistory.add("Orpheus uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Orpheus uses a basic attack on Jack Frost");                        
                        break;
                    case 2:
                        textHistory.add("Orpheus uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Orpheus uses a basic attack on Principality");
                        break;
                }
                break;
            case 3: // robin hood
                switch (enemy){
                    case 0:
                        textHistory.add("Robin Hood uses a basic attack on Archangel");
                        break;
                    case 1:
                        textHistory.add("Robin Hood uses a basic attack on Jack Frost");
                        break;
                    case 2:
                        textHistory.add("Robin Hood uses a basic attack on Legion");
                        break;
                    case 3:
                        textHistory.add("Robin Hood uses a basic attack on Principality");
                        break;
                }
                break;
        }
        goNext();
    }
    
    public void guard(String who){ // if from the main menu above you select guard. it will do this
        textHistory.add(who + " Guards");
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
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // monsoon
                        if (spAllyOne > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2: // redemption
                        if (allyInjured){
                            if (spAllyOne > 22){
                                typeOfMove = 2;
                                prevPage = 2;
                                page = 4; // single target ally select
                            } else {
                                System.out.println("Not Enough SP");
                            }
                        } else {
                            System.out.println("No one is injured");
                        }
                        break;
                    case 3: // guardian angel
                        if (allyDead){
                            if (spAllyOne > 8){
                                typeOfMove = 2;
                                prevPage = 2;
                                page = 4; // single target ally select
                            } else {
                                System.out.println("Not Enough SP");
                            }
                        } else {
                            System.out.println("No one is dead");
                        }
                        break;
                }
                break;
            case 1: // cendrillon moves
                switch (move){
                    case 0: // aqua prison
                        if (spAllyTwo > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // surging tide
                        if (spAllyTwo > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (hpAllyTwo > 40){
                            typeOfMove = 0;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough HP");
                        }
                        break;
                    case 3: // agl boost
                        if (spAllyTwo > 12){
                            typeOfMove = 5;
                            prevPage = 2;
                            page = 4; // single target
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                }
                break;
            case 2: // orpheus moves
                switch (move){
                    case 0: // lunar rush
                        if (spAllyThree > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // moonfall
                        if (spAllyThree > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (spAllyThree > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 3: // def boost
                        if (spAllyThree > 12){
                            typeOfMove = 4;
                            prevPage = 2;
                            page = 4; // single target
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                }
                break;
            case 3: // robin hood moves 
                switch (move){
                    case 0: // zenith blade
                        if (spAllyFour > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // solar flare
                        if (spAllyFour > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (spAllyFour > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 3: // atk boost
                        if (spAllyFour > 12){
                            typeOfMove = 3;
                            prevPage = 2;
                            page = 4; // single target
                        } else {
                            System.out.println("Not Enough SP");
                        }
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
                                break;
                            case 1:
                                textHistory.add("Ame No Uzume uses Zephyr on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Ame No Uzume uses Zephyr on Legion");
                                break;
                            case 3:
                                textHistory.add("Ame No Uzume uses Zephyr on Principality");
                                break;
                        }
                        spAllyOne -= 9;
                        break;
                    case 1: // monsoon
                        textHistory.add("Ame No Uzume uses Monsoon on every enemy");
                        spAllyOne -= 21;
                        break;
                    case 2: // redemption
                        // this is a heal ability managed by another method, if you got here you did something wrong
                        System.out.println("if youre here youve done something wrong");
                        break;
                    case 3: // guardian angel
                        // this is a heal ability managed by another method, if you got here you did something wrong
                        System.out.println("if youre here youve done something wrong");
                        break;
                }
                break;
            case 1: // cendrillon
                switch (move){
                    case 0: // aqua prison
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses Aqua Prison on Archangel");
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Aqua Prison on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Aqua Prison on Legion");
                                    break;
                            case 3:
                                textHistory.add("Cendrillon uses Aqua Prison on Principality");
                                break;
                        }
                        spAllyTwo -= 9;
                        break;
                    case 1: // surging tide
                        textHistory.add("Cendrillon uses Surging Tide on every enemy");
                        spAllyTwo -= 21;
                        break;
                    case 2: // ability 3
                        break;
                    case 3: // ability 4
                        // this is a buff ability managed by another method, if you got here you did something wrong
                        System.out.println("if youre here youve done something wrong");
                        break;
                }
                break;
            case 2: // orpheus
                switch (move){
                    case 0: // lunar rush
                        switch (enemy){
                            case 0:
                                textHistory.add("Orpheus uses Lunar Rush on Archangel");
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Lunar Rush on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Lunar Rush on Legion");
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Lunar Rush on Principality");
                                break;
                        }
                        spAllyThree -= 9;
                        break;
                    case 1: // moon fall
                        textHistory.add("Orpheus uses Moonfall on every enemy");
                        spAllyThree -= 21;
                        break;
                    case 2: // shattering strike
                        switch (enemy){
                            case 0:
                                textHistory.add("Orpheus uses Shattering Strike on Archangel");
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Shattering Strike on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Shattering Strike on Legion");
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Shattering Strike on Principality");
                                break;
                        }
                        spAllyThree -= 9;
                        break;
                    case 3: // ability 4
                        // this is a buff ability managed by another method, if you got here you did something wrong
                        System.out.println("if youre here youve done something wrong");
                        break;
                }
                break;
            case 3: // robin hood
                switch (move){
                    case 0: // zenith blade
                        switch (enemy){
                            case 0:
                                textHistory.add("Robin Hood uses Zenith Blade on Archangel");
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Zenith Blade on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Zenith Blade on Legion");
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Zenith Blade on Principality");
                                break;
                        }
                        spAllyFour -= 9;
                        break;
                    case 1:  // solar flare
                        textHistory.add("Robin Hood uses Solar Flare on every enemy");
                        spAllyFour -= 21;
                        break;
                    case 2: // 
                        switch (enemy){
                            case 0:
                                textHistory.add("Robin Hood uses Sear on Archangel");
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Sear on Jack Frost");
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Sear on Legion");
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Sear on Principality");
                                break;
                        }
                        spAllyFour -= 9;
                        break;
                    case 3: // ability 4
                        // this is a buff ability managed by another method, if you got here you did something wrong
                        System.out.println("if youre here youve done something wrong");
                        break;
                }
                break;
        }
        goNext();
    }
    
    void healing(int ally){
        switch (currentCharacter){
            case 0: // ame
                switch (ally){
                    case 0:
                        textHistory.add("Ame No Uzume uses redemption on Ame No Uzume");
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses redemption on Cendrillon");
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses redemption on Orpheus");
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses redemption on Robin Hood");
                        break;
                }
                spAllyOne -= 8;
                break;
        }
        goNext();
    }
    
    void revive(int ally){
        switch (currentCharacter){
            case 0: // ame
                switch (ally){
                    case 0:
                        textHistory.add("Ame No Uzume uses guardian angel on Ame No Uzume");
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses guardian angel on Cendrillon");
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses guardian angel on Orpheus");
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses guardian angel on Robin Hood");
                        break;
                }
                spAllyOne -= 22;
                break;
        }
        goNext();
    }
    
    void boost(int ally){
        switch (typeOfMove){
            case 3: // attack boosts
                switch (currentCharacter){ // who is giving the boost
                    case 0: // ame
                        System.out.println("if you got here you fucked something up");
                        // ame has no boost moves, so if you got here something went wrong
                        break;
                    case 1:
                        System.out.println("if you got here you fucked something up");
                        // cendrillon cant boost attack
                        break;
                    case 2:
                        System.out.println("if you got here you fucked something up");
                        // orpheus cannot boost attack
                        break;
                    case 3: // robin hood can boost attack
                        switch (ally){
                            case 0: // boost ame
                                textHistory.add("Robin Hood boosts Attack of Ame No Uzume");
                                allyOneStats[0] = 2; // multiplying total damage by 2 means more damage, which means higher attack
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Robin Hood boosts Attack of Cendrillon");
                                allyTwoStats[0] = 2;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Robin Hood boosts Attack of Orpheus");
                                allyThreeStats[0] = 2;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Robin Hood boosts Attack of Robin Hood");
                                allyFourStats[0] = 2;
                                break;
                        }
                        spAllyFour -= 12;
                        break;
                }
                break;
            case 4: // defense boosts 
                switch (currentCharacter){ // who is giving the boost
                    case 0: // ame
                        System.out.println("if you got here you fucked something up");
                        // ame has no boost moves, so if you got here something went wrong
                        break;
                    case 1:
                        System.out.println("if you got here you fucked something up");
                        // cendrillon cant boost defesne
                        break;
                    case 2:
                        switch (ally){
                            case 0: // boost ame
                                textHistory.add("Orpheus boosts Defense of Ame No Uzume");
                                allyOneStats[1] = 0.5; // multiplying total damage by 0.5 means less damage, which means higher defense
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Orpheus boosts Defense of Cendrillon");
                                allyTwoStats[1] = 0.5;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Orpheus boosts Defense of Orpheus");
                                allyThreeStats[1] = 0.5;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Orpheus boosts Defense of Robin Hood");
                                allyFourStats[1] = 0.5;
                                break;
                        }
                        spAllyThree -= 12;
                        break;
                    case 3:
                        System.out.println("if you got here you fucked something up");
                        // robin hood cant boost defense
                        break;
                }
                break;
            case 5: // agility boosts
                switch (currentCharacter){ // who is giving the boost
                    case 0: // ame
                        System.out.println("if you got here you fucked something up");
                        // ame has no boost moves, so if you got here something went wrong
                        break;
                    case 1:
                        switch (ally){
                            case 0: // boost ame
                                textHistory.add("Cendrillon boosts Agility of Ame No Uzume");
                                allyOneStats[2] = 2;
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Cendrillon boosts Agility of Cendrillon");
                                allyTwoStats[2] = 2;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Cendrillon boosts Agility of Orpheus");
                                allyThreeStats[2] = 2;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Cendrillon boosts Agility of Robin Hood");
                                allyFourStats[2] = 2;
                                break;
                        }
                        spAllyTwo -= 12;
                        break;
                    case 2:
                        System.out.println("if you got here you fucked something up");
                        // orpheus cant boost agility
                        break;
                    case 3:
                        System.out.println("if you got here you fucked something up");
                        // robin hood cant boost agility
                        break;
                }
                break;
        }
        goNext();
    }
    
    void sweeper(int enemy){ // reveal affinities for an enemy'
        switch (enemy){
            case 0:
                if (affinitiesKnown[0] == 0){ // if we dont know the affinities
                    textHistory.add("Oracle Lens Used. Affinities for Enemy One revealed.");
                    affinitiesKnown[0] = 1;
                    goNext();
                } else {
                    System.out.println("Affinities for enemy one already revealed");
                }
                break;
            case 1:
                if (affinitiesKnown[1] == 0){ // if we dont know the affinities
                    textHistory.add("Oracle Lens Used. Affinities for Enemy Two revealed.");
                    affinitiesKnown[1] = 1;
                    goNext();
                } else {
                    System.out.println("Affinities for Enemy Two already revealed");
                }
                break;
            case 2:
                if (affinitiesKnown[2] == 0){ // if we dont know the affinities
                    textHistory.add("Oracle Lens Used. Affinities for Enemy Three revealed.");
                    affinitiesKnown[2] = 1;
                    goNext();
                } else {
                    System.out.println("Affinities for Enemy Three already revealed");
                }
                break;
            case 3:
                if (affinitiesKnown[3] == 0){ // if we dont know the affinities
                    textHistory.add("Oracle Lens Used. Affinities for Enemy Four revealed.");
                    affinitiesKnown[3] = 1;
                    goNext();
                } else {
                    System.out.println("Affinities for Enemy Four already revealed");
                }
                break;
        }
    }
    
    void everfrost(int ally){
        switch (ally){
            case 0:
                if (spAllyOne == spMaxAllyOne){
                    System.out.println("This ally already has max SP");
                } else {
                    spAllyOne += 30;
                    if (spAllyOne > spMaxAllyOne){
                        spAllyOne = spMaxAllyOne;
                    }
                    textHistory.add("Restored 30 SP to Ally One");
                    goNext();
                }
                break;
            case 1:
                if (spAllyTwo == spMaxAllyTwo){
                    System.out.println("This Ally already has max SP");
                } else {
                    spAllyTwo += 30;
                    if (spAllyTwo > spMaxAllyTwo){
                        spAllyTwo = spMaxAllyTwo;
                    }
                    textHistory.add("Restored 30 SP to Ally Two");
                    goNext();
                }
                break;
            case 2:
                if (spAllyThree == spMaxAllyThree){
                    System.out.println("This Ally already has max SP");
                } else {
                    spAllyThree += 30;
                    if (spAllyThree > spMaxAllyThree){
                        spAllyThree = spMaxAllyThree;
                    }
                    textHistory.add("Restored 30 SP to Ally Three");
                    goNext();
                }
                break;
            case 3:
                if (spAllyFour == spMaxAllyFour){
                    System.out.println("This ally already has max SP");
                } else {
                    spAllyFour += 30;
                    if (spAllyFour > spMaxAllyFour){
                        spAllyFour = spMaxAllyFour;
                    }
                    textHistory.add("Restored 30 SP to Ally Four");
                    goNext();
                }
                break;
        }
    }
    
    void lightningCrash(int enemy){
        switch (enemy){
            case 0:
                enemyOneStats[3] = 1;
                textHistory.add("Applied shock to enemy One");
                break;
            case 1:
                enemyTwoStats[3] = 1;
                textHistory.add("Applied shock to enemy Two");
                break;
            case 2:
                enemyThreeStats[3] = 1;
                textHistory.add("Applied shock to enemy Three");
                break;
            case 3:
                enemyFourStats[3] = 1;
                textHistory.add("Applied shock to enemy Four");
                break;
        }
        goNext();
    }
    
    public void goNext(){ // the script that decides whos turn is next, and basically advances the game
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
        if (hpEnemyOne <= 0 || hpEnemyTwo <= 0 || hpEnemyThree <= 0 || hpEnemyFour <= 0){
            enemyDead = true;
        } else {
            enemyDead = false;
        }
        if (hpAllyOne <= 0 || hpAllyTwo <= 0 || hpAllyThree <= 0 || hpAllyFour <= 0){
            allyDead = true;
        } else {
            allyDead = false;
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
    
    public void initializeAffinities(){
        // this isnt technically a part of affinities, but i moved it here to get it out of the early method, cuz this only happens once
        hpAllyOne = hpMaxAllyOne;
        hpAllyTwo = hpMaxAllyTwo;
        hpAllyThree = hpMaxAllyThree;
        hpAllyFour = hpMaxAllyFour;
        spAllyOne = spMaxAllyOne;
        spAllyTwo = spMaxAllyTwo;
        spAllyThree = spMaxAllyThree;
        spAllyFour = spMaxAllyFour;
        
        boolean can = false;
        int weak = 0;
        int resist = 0;
        int nullify = 0;
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                enemyOneAffinity[weak] = 1;
                enemyOneAffinity[nullify] = 2;
                enemyOneAffinity[resist] = 3;
            } else {
                can = false;
                weak = rng.nextInt(7);
                resist = rng.nextInt(7);
                nullify = rng.nextInt(7);
            }
        }
        can = false;
        weak = 0;
        resist = 0;
        nullify = 0;
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                enemyTwoAffinity[weak] = 1;
                enemyTwoAffinity[nullify] = 2;
                enemyTwoAffinity[resist] = 3;
            } else {
                can = false;
                weak = rng.nextInt(7);
                resist = rng.nextInt(7);
                nullify = rng.nextInt(7);
            }
        }
        can = false;
        weak = 0;
        resist = 0;
        nullify = 0;
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                enemyThreeAffinity[weak] = 1;
                enemyThreeAffinity[nullify] = 2;
                enemyThreeAffinity[resist] = 3;
            } else {
                can = false;
                weak = rng.nextInt(7);
                resist = rng.nextInt(7);
                nullify = rng.nextInt(7);
            }
        }
        can = false;
        weak = 0;
        resist = 0;
        nullify = 0;
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                enemyFourAffinity[weak] = 1;
                enemyFourAffinity[nullify] = 2;
                enemyFourAffinity[resist] = 3;
            } else {
                can = false;
                weak = rng.nextInt(7);
                resist = rng.nextInt(7);
                nullify = rng.nextInt(7);
            }
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
