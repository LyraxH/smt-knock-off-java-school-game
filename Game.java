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
    int hpMaxAlly[] = new int[]{500,500,500,500};
    int hpAlly[] = new int[]{hpMaxAlly[0],hpMaxAlly[1],hpMaxAlly[2],hpMaxAlly[3]};
    boolean allyInjured = false;
    boolean allyDead = false;
    
    int spMaxAlly[] = new int[]{150,150,150,150};
    int spAlly[] = new int[]{spMaxAlly[0],spMaxAlly[1],spMaxAlly[2],spMaxAlly[3]};
    
    // first number = attack status, second = defense status, third = accuracy/evasion status
    double allyStats[][] = new double[4][3];
    int concentrate[] = new int[]{0,0,0,0}; // 1 means that characters attack next turn will do more than double damage
    int guard[] = new int[]{0,0,0,0}; // 1 means that you take half damage next round
    // first number = attack status, second = defense status, third = accuracy/evasion status. fourth = shock
    double enemyStats[][] = new double[4][4];
    // 0 = normal, 1 = weak, 2 = null, 3 = resist, 4 = unknown
    int allyAffinities[][] = new int[4][7];
    int enemyAffinities[][] = new int[4][7];
    String affinity = "o";
    int affinityRNG = 4;
    int affinitiesKnown[] = new int[]{0,0,0,0}; // 1 unknown affinities, 1 = known affinities.
    
    int hpMaxEnemy[] = new int[]{800,800,800,800};
    int hpEnemy[] = new int[]{hpMaxEnemy[0],hpMaxEnemy[1],hpMaxEnemy[2],hpMaxEnemy[3]};
    boolean enemyInjured;
    int enemyInjuredWho[] = new int[]{0,0,0,0};
    boolean enemyDead;
    
    int typeOfMove; // 0 = phys, 1 = magic, 2 = healing, 3 = buff atk, 4 = buff def, 5 = buff agl, 6 = sweeper
    int difficulty;
    double damageMultiplier;
    double damageTakenMultiplier;
    public int battleDamage = 0;
    double preBattleDamage = 420.69;
    int status = 420;
    int target = 420;
    boolean targetAll = false;

    public void Start(){
        initializeAffinitiesStats();
        
        textHistory.add("Welcome to my shitty SMT knockoff for school");
        textHistory.add("made by taison");
        textHistory.add("xdd");
        setDifficulty(1);
    }
    
    void setStatus(int target1, int status1){
        target = target1;
        status = status1;
    }
    
    void calculateDamage(int receiver, String receiverName, String toAffinity, int baseDamage, int moveAffinity){
        preBattleDamage = 0;
        battleDamage = 0;
        switch (turn){
            case 0: //ally turn
                switch (enemyAffinities[receiver][moveAffinity]){
                    case 0: // if enemy is normal to move affinity
                        preBattleDamage = baseDamage * enemyStats[receiver][1] * allyStats[currentCharacter][0] * damageMultiplier;
                        if (concentrate[currentCharacter] == 1){ // if concentrated
                            preBattleDamage *= 2.25; // deal over double damage to make wasting turn worth it
                            concentrate[currentCharacter] = 0; // reset the concentrate so you cant use it twice in a row
                        }
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is hit by " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver, 5);
                        break;
                    case 1: // if enemy is weak to move affinity
                        preBattleDamage = baseDamage * enemyStats[receiver][1] * allyStats[currentCharacter][0] * damageMultiplier * 1.45;
                        if (concentrate[currentCharacter] == 1){ // if concentrated
                            preBattleDamage *= 2.25; // deal over double damage to make wasting turn worth it
                            concentrate[currentCharacter] = 0; // reset the concentrate so you cant use it twice in a row
                        }
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is weak to " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver, 0);
                        break;
                    case 2: // if enemy resists move affinity
                        preBattleDamage = baseDamage * enemyStats[receiver][1] * allyStats[currentCharacter][0] * damageMultiplier * 0.69;
                        if (concentrate[currentCharacter] == 1){ // if concentrated
                            preBattleDamage *= 2.25; // deal over double damage to make wasting turn worth it
                            concentrate[currentCharacter] = 0; // reset the concentrate so you cant use it twice in a row
                        }
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " resists " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver, 1);
                        break;
                    case 3: // if enemy is null to move affinity
                        preBattleDamage = 0;
                        if (concentrate[currentCharacter] == 1){ // if concentrated
                            preBattleDamage *= 2.25; // deal over double damage to make wasting turn worth it
                            concentrate[currentCharacter] = 0; // reset the concentrate so you cant use it twice in a row
                        }
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is null to " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver, 2);
                        break;
                }
                break;
            case 1: // enemies turn
                switch (allyAffinities[receiver][moveAffinity]){
                    case 0: // if ally is normal to move affinity
                        preBattleDamage = baseDamage * allyStats[receiver][1] * enemyStats[currentCharacter][0] * damageTakenMultiplier;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpAlly[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is hit by " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 5);
                        break;
                    case 1: // if ally is weak to move affinity
                        preBattleDamage = baseDamage * allyStats[receiver][1] * enemyStats[currentCharacter][0] * damageTakenMultiplier * 1.45;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpAlly[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is weak to " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 0);
                        break;
                    case 2: // if ally resists move affinity
                        preBattleDamage = baseDamage * allyStats[receiver][1] * enemyStats[currentCharacter][0] * damageTakenMultiplier * 0.69;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpAlly[receiver] -= battleDamage;
                        textHistory.add(receiverName + " resists " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 1);
                        break;
                    case 3: // if ally is null to move affinity
                        preBattleDamage = 0;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpAlly[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is null to " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 2);
                        break;
                }
                break;
        }
    }
    
    void calculateHealing(int reciever, String recieverName, int amount){
        switch (turn){
            case 0: // allies turn
                if ((hpMaxAlly[reciever] - hpAlly[reciever]) < amount){ // if difference between hp and max hp is less than the amount healing for
                    amount = hpMaxAlly[reciever] - hpAlly[reciever];
                }
                hpAlly[reciever] += amount;
                textHistory.add(recieverName + " was healed for " + amount);
                break;
            case 1: // enemies turn
                if ((hpMaxEnemy[reciever] - hpEnemy[reciever]) < amount){ // if difference between hp and max hp is less than the amount healing for
                    amount = hpMaxEnemy[reciever] - hpEnemy[reciever];
                }
                hpEnemy[reciever] += amount;
                textHistory.add(recieverName + " was healed for " + amount);
                break;
        }
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
                        if (enemyStats[0][3] == 1){ // if afflicted with shock
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
                                enemyStats[0][3] = 0;
                                skip = false;
                            }
                        }
                        if (skip){
                            textHistory.add("Archangel is shocked and cannot move");
                        } else {
                            if (enemyInjured){ // if "ally" (enemy) injured
                                int decision = rng.nextInt(5);
                                if (decision == 1){
                                    enemyHeal("Archangel");
                                } else { // 66% you just attack anyways
                                    enemyAttack(0);
                                }
                            } else { // other wise if no one is injured just attack
                                enemyAttack(0);
                            }
                        }
                        break;
                    case 1:
                        skip = false;
                        if (enemyStats[1][3] == 1){ // if afflicted with shock
                            int shock = rng.nextInt(10);
                            if (shock < 7){ // 80% chance you remain shocked
                                textHistory.add("Jack Frost is still shocked");
                                shock = rng.nextInt(10);
                                if (shock < 5){ // 40% chance you can move anyways
                                    skip = false;
                                } else { // 60% chance you cannot move
                                    skip = true;
                                }
                            } else { // 20% chance you remove shock
                                textHistory.add("Jack Frost has recovered from shock");
                                enemyStats[1][3] = 0;
                                skip = false;
                            }
                        }
                        if (skip){
                            textHistory.add("Jack Frost is shocked and cannot move");
                        } else {
                            if (enemyInjured){ // if "ally" (enemy) injured
                                int decision = rng.nextInt(5);
                                if (decision == 1){
                                    enemyHeal("Jack Frost");
                                } else { // 66% you just attack anyways
                                    enemyAttack(1);
                                }
                            } else { // other wise if no one is injured just attack
                                enemyAttack(1);
                            }
                        }
                        break;
                    case 2:
                        skip = false;
                        if (enemyStats[2][3] == 1){ // if afflicted with shock
                            int shock = rng.nextInt(10);
                            if (shock < 7){ // 80% chance you remain shocked
                                textHistory.add("Legion is still shocked");
                                shock = rng.nextInt(10);
                                if (shock < 5){ // 40% chance you can move anyways
                                    skip = false;
                                } else { // 60% chance you cannot move
                                    skip = true;
                                }
                            } else { // 20% chance you remove shock
                                textHistory.add("Legion has recovered from shock");
                                enemyStats[2][3] = 0;
                                skip = false;
                            }
                        }
                        if (skip){
                            textHistory.add("Legion is shocked and cannot move");
                        } else {
                            if (enemyInjured){ // if "ally" (enemy) injured
                                int decision = rng.nextInt(5);
                                if (decision == 1){ // 33% rng.nextInt(5)
                                    enemyHeal("Legion");
                                } else { // 66% you just attack anyways
                                    enemyAttack(2);
                                }
                            } else { // other wise if no one is injured just attack
                                enemyAttack(2);
                            }
                        }
                        break;
                    case 3:
                        skip = false;
                        if (enemyStats[3][3] == 1){ // if afflicted with shock
                            int shock = rng.nextInt(10);
                            if (shock < 7){ // 80% chance you remain shocked
                                textHistory.add("Principality is still shocked");
                                shock = rng.nextInt(10);
                                if (shock < 5){ // 40% chance you can move anyways
                                    skip = false;
                                } else { // 60% chance you cannot move
                                    skip = true;
                                }
                            } else { // 20% chance you remove shock
                                textHistory.add("Principality has recovered from shock");
                                enemyStats[3][3] = 0;
                                skip = false;
                            }
                        }
                        if (skip){
                            textHistory.add("Principality is shocked and cannot move");
                        } else {
                            if (enemyInjured){ // if "ally" (enemy) injured
                                int decision = rng.nextInt(5);
                                if (decision == 1){
                                    enemyHeal("Principality");
                                } else { // 33% you just attack anyways
                                    enemyAttack(3);
                                }
                            } else { // other wise if no one is injured just attack
                                enemyAttack(3);
                            }
                        }
                        break;
                }
                break;
        }
    }
    
    void setAffinity(int toAffinity){
        switch (affinityRNG){
            case 0:
                affinity = "Fire";
                break;
            case 1:
                affinity = "Water";
                break;
            case 2:
                affinity = "Air";
                break;
            case 3:
                affinity = "Earth";
                break;
            case 4:
                affinity = "Sun";
                break;
            case 5:
                affinity = "Moon";
                break;
        }
    }
    
    void enemyAttack(int enemy){
        int decision = rng.nextInt(4);
        affinityRNG = rng.nextInt(6);
        switch (decision){
            case 0,1: // magic attack
                String target = "o";
                String who = "o";
                switch (affinityRNG){
                    case 0:
                        affinity = "Fire";
                        break;
                    case 1:
                        affinity = "Water";
                        break;
                    case 2:
                        affinity = "Air";
                        break;
                    case 3:
                        affinity = "Earth";
                        break;
                    case 4:
                        affinity = "Sun";
                        break;
                    case 5:
                        affinity = "Moon";
                        break;
                }
                decision = rng.nextInt(4);
                switch (decision){
                    case 0:
                        target = "Ame No Uzume";
                        break;
                    case 1:
                        target = "Cendrillon";
                        break;
                    case 2:
                        target = "Orpheus";
                        break;
                    case 3:
                        target = "Robin Hood";
                        break;
                }
                switch (currentCharacter){
                    case 0:
                        who = "Archangel";
                        break;
                    case 1:
                        who = "Jack Frost";
                        break;
                    case 2:
                        who = "Legion";
                        break;
                    case 3:
                        who = "Principality";
                        break;
                }
                textHistory.add(who + " targets " + target + " with a magic attack of affinity " + affinity);
                calculateDamage(decision, target, affinity, 75, affinityRNG);
                break;
            case 2: // physical attack
                decision = rng.nextInt(4);
                who = "o";
                target = "o";
                switch (currentCharacter){
                    case 0:
                        who = "Archangel";
                        break;
                    case 1:
                        who = "Jack Frost";
                        break;
                    case 2:
                        who = "Legion";
                        break;
                    case 3:
                        who = "Principality";
                        break;
                }
                switch (decision){
                    case 0:
                        target = "Ame No Uzume";
                        break;
                    case 1:
                        target = "Cendrillon";
                        break;
                    case 2:
                        target = "Orpheus";
                        break;
                    case 3:
                        target = "Robin Hood";
                        break;
                }
                textHistory.add(who + " targets " + target + " with a basic attack");
                calculateDamage(decision, target, "Physical", 55, 6);
            case 3: // buff / debuff
                decision = rng.nextInt(2);
                if (decision == 0){
                    who = "o";
                    target = "o";
                    String what = "o";
                    int targetInt = rng.nextInt(4);
                    int whatInt = rng.nextInt(3);
                    switch (enemy){
                        case 0:
                            who = "Archangel";
                            break;
                        case 1:
                            who = "Jack Frost";
                            break;
                        case 2:
                            who = "Legion";
                            break;
                        case 3:
                            who = "Principality";
                            break;
                    }
                    switch (whatInt){
                        case 0:
                            allyStats[targetInt][whatInt] = 0.6;
                            what = "an attack debuff";
                            break;
                        case 1:
                            what = "a defense debuff";
                            allyStats[targetInt][whatInt] = 1.6;
                            break;
                        case 2:
                            what = "an agility debuff";
                            allyStats[targetInt][whatInt] = 0.5;
                            break;
                    }
                    switch (targetInt){
                        case 0:
                            target = "Ame No Uzume";
                            break;
                        case 1:
                            target = "Cendrillon";
                            break;
                        case 2:
                            target = "Orpheus";
                            break;
                        case 3:
                            target = "Robin Hood";
                            break;
                    }
                    textHistory.add(who + " targets " + target + " with " + what);
                } else {
                    who = "o";
                    target = "o";
                    String what = "o";
                    int targetInt = rng.nextInt(4);
                    int whatInt = rng.nextInt(3);
                    switch (enemy){
                        case 0:
                            who = "Archangel";
                            break;
                        case 1:
                            who = "Jack Frost";
                            break;
                        case 2:
                            who = "Legion";
                            break;
                        case 3:
                            who = "Principality";
                            break;
                    }
                    switch (whatInt){
                        case 0:
                            enemyStats[targetInt][whatInt] = 1.6;
                            what = "an attack buff";
                            break;
                        case 1:
                            what = "a defense buff";
                            enemyStats[targetInt][whatInt] = 0.6;
                            break;
                        case 2:
                            what = "an agility buff";
                            enemyStats[targetInt][whatInt] = 2;
                            break;
                    }
                    switch (targetInt){
                        case 0:
                            target = "Archangel";
                            break;
                        case 1:
                            target = "Jack Frost";
                            break;
                        case 2:
                            target = "Legion";
                            break;
                        case 3:
                            target = "Principality";
                            break;
                    }
                    textHistory.add(who + " targets " + target + " with " + what);
                }
        }
    }
    
    void enemyHeal(String enemy){
        // i know this is linear, i also know it is possibly exploitable as it will always search for injuries left to right, but if you
        // know you can pull that off you deserve to be able to abuse this mechanic.
        if (enemyInjuredWho[0] == 1){
            textHistory.add(enemy + " has healed enemy one");
            calculateHealing(0, "Archangel", 100);
        } else if (enemyInjuredWho[1] == 1){
            textHistory.add(enemy + " has healed enemy two");
            calculateHealing(1, "Jack Frost", 100);
        } else if (enemyInjuredWho[2] == 1){
            textHistory.add(enemy + " has healed enemy three");
            calculateHealing(2, "Legion", 100);
        } else if (enemyInjuredWho[3] == 1){
            textHistory.add(enemy + " has healed enemy four");
            calculateHealing(3, "Principality", 100);
        }
    }
    
    void physical(int enemy){ // if from the main menu above you select attack, it will do a normal attack based on who you select
        String who = "o";
        String what = "o";
        String target = "o";
        int damage = 0;
        switch (currentCharacter){
            case 0:
                who = "Ame No Uzume";
                break;
            case 1:
                who = "Cendrillon";
                break;
            case 2:
                who = "Orpheus";
                break;
            case 3:
                who = "Robin Hood";
                break;
        }
        switch (move){
            case 2:
                what = "Snip Snip";
                damage = 85;
                hpAlly[1] -= 40;
                break;
            default:
                what = "a basic attack";
                damage = 45;
                break;
        }
        switch (enemy){
            case 0:
                target = "Archangel";
                break;
            case 1:
                target = "Jack Frost";
                break;
            case 2:
                target = "Legion";
                break;
            case 3:
                target = "Principality";
                break;
        }
        textHistory.add(who + " uses " + what + " on " + target);
        calculateDamage(enemy, target, "Physical", damage, 6);
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
                        if (spAlly[0] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // monsoon
                        if (spAlly[0] > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2: // redemption
                        if (allyInjured){
                            if (spAlly[0] > 22){
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
                            if (spAlly[0] > 8){
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
                        if (spAlly[1] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // surging tide
                        if (spAlly[1] > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (hpAlly[1] > 40){
                            typeOfMove = 0;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough HP");
                        }
                        break;
                    case 3: // agl boost
                        if (spAlly[1] > 12){
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
                        if (spAlly[2] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // moonfall
                        if (spAlly[2] > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (spAlly[2] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 3: // def boost
                        if (spAlly[2] > 12){
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
                        if (spAlly[3] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 1: // solar flare
                        if (spAlly[3] > 21){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 5; // multi target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 2:
                        if (spAlly[3] > 9){
                            typeOfMove = 1;
                            prevPage = 2;
                            page = 1; // single target enemy select
                        } else {
                            System.out.println("Not Enough SP");
                        }
                        break;
                    case 3: // atk boost
                        if (spAlly[3] > 12){
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
        String who = "o";
        String what = "o";
        String target = "o";
        int affinityInt = 0;
        String affinityString = "o";
        int damage = 0;
        targetAll = false;
        
        switch (currentCharacter){
            case 0:
                who = "Ame no uzume";
                switch (move){
                    case 0:
                        damage = 85;
                        what = "Zephyr";
                        affinityInt = 2;
                        affinityString = "Wind";
                        targetAll = false;
                        break;
                    case 1:
                        damage = 65;
                        what = "Monsoon";
                        affinityInt = 2;
                        affinityString = "Wind";
                        targetAll = true;
                        break;
                }
                break;
            case 1:
                who = "Cendrillon";
                switch (move){
                    case 0: // 
                        damage = 85;
                        what = "Aqua Prison";
                        affinityInt = 1;
                        affinityString = "Water";
                        targetAll = false;
                        break;
                    case 1:
                        damage = 65;
                        what = "Surging Tide";
                        affinityInt = 1;
                        affinityString = "Water";
                        targetAll = true;
                        break;
                }
                break;
            case 2:
                who = "Orpheus";
                switch (move){
                    case 0: // 
                        damage = 85;
                        what = "Lunar Rush";
                        affinityInt = 5;
                        affinityString = "Moon";
                        targetAll = false;
                        break;
                    case 1:
                        damage = 65;
                        what = "Moonfall";
                        affinityInt = 5;
                        affinityString = "Moon";
                        targetAll = true;
                        break;
                    case 2:
                        damage = 85;
                        what = "Shattering Strike";
                        affinityInt = 3;
                        affinityString = "Earth";
                        targetAll = false;
                        break;
                }
                break;
            case 3:
                who = "Robin Hood";
                switch (move){
                    case 0: // 
                        damage = 85;
                        what = "Zenith Blade";
                        affinityInt = 4;
                        affinityString = "Sun";
                        targetAll = false;
                        break;
                    case 1:
                        damage = 65;
                        what = "Solar Flare";
                        affinityInt = 4;
                        affinityString = "Sun";
                        targetAll = true;
                        break;
                    case 2:
                        damage = 85;
                        what = "Sear";
                        affinityInt = 0;
                        affinityString = "Fire";
                        targetAll = false;
                        break;
                }
                break;
        }
        switch (enemy){
            case 0:
                target = "Archangel";
                break;
            case 1:
                target = "Jack Frost";
                break;
            case 2:
                target = "Legion";
                break;
            case 3:
                target = "Principality";
                break;
        }
        textHistory.add(who + " uses " + what + " on " + target);
        if (targetAll){
            textHistory.add("Ame No Uzume uses Monsoon on every enemy");
            calculateDamage(0, "Archangel", affinityString, damage, affinityInt);
            calculateDamage(1, "Jack Frost", affinityString, damage, affinityInt);
            calculateDamage(2, "Legion", affinityString, damage, affinityInt);
            calculateDamage(3, "Principality", affinityString, damage, affinityInt);
            spAlly[currentCharacter] -= 21;
        } else {
            calculateDamage(enemy, target, affinityString, damage, affinityInt);
            spAlly[currentCharacter] -= 9;
        }
        goNext();
    }
    
    void healing(int ally){
        switch (currentCharacter){
            case 0: // ame
                switch (ally){
                    case 0:
                        textHistory.add("Ame No Uzume uses redemption on Ame No Uzume");
                        calculateHealing(0, "Ame No Uzume", 80);
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses redemption on Cendrillon");
                        calculateHealing(1, "Cendrillon", 80);
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses redemption on Orpheus");
                        calculateHealing(2, "Orpheus", 0);
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses redemption on Robin Hood");
                        calculateHealing(3, "Robin Hood", 80);
                        break;
                }
                spAlly[0] -= 8;
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
                spAlly[0] -= 22;
                break;
        }
        goNext();
    }
    
    void boost(int target){
        String what = "o";
        String from = "o";
        String who = "o";
        switch (currentCharacter){
            case 0:
                from = "Ame No Uzume";
                break;
            case 1:
                from = "Cendrillon";
                break;
            case 2:
                from = "Orpheus";
                break;
            case 3:
                from = "Robin Hood";
                break;
        }
        switch (typeOfMove){
            case 3:
                what = "Attack";
                allyStats[target][0] = 1.6;
                break;
            case 4:
                what = "Defense";
                allyStats[target][1] = 0.6;
                break;
            case 5:
                what = "Agility";
                allyStats[target][2] = 2;
                break;
        }
        switch (target){
            case 0:
                who = "Ame No Uzume";
                break;
            case 1:
                who = "Cendrillon";
                break;
            case 2:
                who = "Orpheus";
                break;
            case 3:
                who = "Robin Hood";
                break;
        }
        textHistory.add(from + " Boosts the " + what + " of " + who);
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
                if (spAlly[0] == spMaxAlly[0]){
                    System.out.println("This ally already has max SP");
                } else {
                    spAlly[0] += 30;
                    if (spAlly[0] > spMaxAlly[0]){
                        spAlly[0] = spMaxAlly[0];
                    }
                    textHistory.add("Restored 30 SP to Ally One");
                    goNext();
                }
                break;
            case 1:
                if (spAlly[1] == spMaxAlly[1]){
                    System.out.println("This Ally already has max SP");
                } else {
                    spAlly[1] += 30;
                    if (spAlly[1] > spMaxAlly[1]){
                        spAlly[1] = spMaxAlly[1];
                    }
                    textHistory.add("Restored 30 SP to Ally Two");
                    goNext();
                }
                break;
            case 2:
                if (spAlly[2] == spMaxAlly[2]){
                    System.out.println("This Ally already has max SP");
                } else {
                    spAlly[2] += 30;
                    if (spAlly[2] > spMaxAlly[2]){
                        spAlly[2] = spMaxAlly[2];
                    }
                    textHistory.add("Restored 30 SP to Ally Three");
                    goNext();
                }
                break;
            case 3:
                if (spAlly[3] == spMaxAlly[3]){
                    System.out.println("This ally already has max SP");
                } else {
                    spAlly[3] += 30;
                    if (spAlly[3] > spMaxAlly[3]){
                        spAlly[3] = spMaxAlly[3];
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
                enemyStats[0][3] = 1;
                textHistory.add("Applied shock to enemy One");
                break;
            case 1:
                enemyStats[1][3] = 1;
                textHistory.add("Applied shock to enemy Two");
                break;
            case 2:
                enemyStats[2][3] = 1;
                textHistory.add("Applied shock to enemy Three");
                break;
            case 3:
                enemyStats[3][3] = 1;
                textHistory.add("Applied shock to enemy Four");
                break;
        }
        goNext();
    }
    
    public void goNext(){ // the script that decides whos turn is next, and basically advances the game
        if (currentCharacter < 3 && turn == 0){ // if not end of rotation and player turn
            currentCharacter++;
            page = 0;
        } else if (currentCharacter < 3 && turn == 1){ // else if not end of rotation and enemy turn 
            currentCharacter++;
            move(69);
        } else if (currentCharacter == 3 && turn == 1){ // else if end of rotation and enemy turn
            page = 0;
            currentCharacter = 0;
            turn = 0;
            textHistory.add("----- Switching to player turn-----");
            //System.out.println("turn " + turn);
        } else if (currentCharacter == 3 && turn  == 0){ // else if end of rotation and player turn
            page = 420;
            currentCharacter = 0;
            turn = 1;
            textHistory.add("----- Switching to enemy turn-----");
            //System.out.println("turn " + turn);
            move(69);
        }
    }
    
    public void checkInjured(){
        if (hpEnemy[0] < hpMaxEnemy[0] || hpEnemy[1] < hpMaxEnemy[1] || hpEnemy[2] < hpMaxEnemy[2] || hpEnemy[3] < hpMaxEnemy[3]){
            enemyInjured = true;
        } else {
            enemyInjured = false;
        }
        if (hpAlly[0] < hpMaxAlly[0] || hpAlly[1] < hpMaxAlly[1] || hpAlly[2] < hpMaxAlly[2] || hpAlly[3] < hpMaxAlly[3]){
            allyInjured = true;
        } else {
            allyInjured = false;
        }
        if (hpEnemy[0] <= 0 || hpEnemy[1] <= 0 || hpEnemy[2] <= 0 || hpEnemy[3] <= 0){
            enemyDead = true;
        } else {
            enemyDead = false;
        }
        if (hpAlly[0] <= 0 || hpAlly[1] <= 0 || hpAlly[2] <= 0 || hpAlly[3] <= 0){
            allyDead = true;
        } else {
            allyDead = false;
        }
        for (int i = 0; i < 4; i++){
            if (hpEnemy[i] < hpMaxEnemy[i]){
                enemyInjuredWho[i] = 1;
            } else {
                enemyInjuredWho[i] = 0;
            }
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
    
    public void initializeAffinitiesStats(){
        for (int i = 0; i < 4; i++){
            for (int l = 0; l < 7; l++){
                allyAffinities[i][l] = 0;
            }
        }
        allyAffinities[0][0] = 1;
        allyAffinities[0][2] = 2;
        allyAffinities[0][3] = 3;
        allyAffinities[1][3] = 1;
        allyAffinities[1][1] = 2;
        allyAffinities[1][2] = 3;
        allyAffinities[2][4] = 1;
        allyAffinities[2][5] = 2;
        allyAffinities[2][1] = 3;
        allyAffinities[3][5] = 1;
        allyAffinities[3][4] = 2;
        allyAffinities[3][0] = 3;
        
        for (int i = 0; i < 4; i++){ // set ally attack and defense to one
            for (int l = 0; l < 2; l++){
                allyStats[i][l] = 1;
            }
        }
        for (int i = 0; i < 4; i++){ //  same thing to enemeis
            for (int l = 0; l < 2; l++){
                enemyStats[i][l] = 1;
            }
        }
        
        boolean can = false;
        int weak = 0;
        int resist = 0;
        int nullify = 0;
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                enemyAffinities[0][weak] = 1;
                enemyAffinities[0][nullify] = 3;
                enemyAffinities[0][resist] = 2;
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
                enemyAffinities[1][weak] = 1;
                enemyAffinities[1][nullify] = 3;
                enemyAffinities[1][resist] = 2;
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
                enemyAffinities[2][weak] = 1;
                enemyAffinities[2][nullify] = 3;
                enemyAffinities[2][resist] = 2;
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
                enemyAffinities[3][weak] = 1;
                enemyAffinities[3][nullify] = 3;
                enemyAffinities[3][resist] = 2;
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
     * //calculateDamage(target, target name, move affinity, base damage, move affinity number);
     */
}
