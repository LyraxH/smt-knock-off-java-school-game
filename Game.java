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
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is hit by " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 5);
                        break;
                    case 1: // if ally is weak to move affinity
                        preBattleDamage = baseDamage * allyStats[receiver][1] * enemyStats[currentCharacter][0] * damageTakenMultiplier * 1.45;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " is weak to " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 0);
                        break;
                    case 2: // if ally resists move affinity
                        preBattleDamage = baseDamage * allyStats[receiver][1] * enemyStats[currentCharacter][0] * damageTakenMultiplier * 0.69;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
                        textHistory.add(receiverName + " resists " + toAffinity + ", and takes " + battleDamage + " damage.");
                        setStatus(receiver + 4, 1);
                        break;
                    case 3: // if ally is null to move affinity
                        preBattleDamage = 0;
                        battleDamage = (int)Math.round(preBattleDamage);
                        hpEnemy[receiver] -= battleDamage;
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
                switch (decision){ // which "enemy" (ally) to target
                    case 0:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally one with a magic attack of affinity " + affinity);
                                calculateDamage(0, "Ame No Uzume", affinity, 75, affinityRNG);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally one with a magic attack of affinity " + affinity);
                                calculateDamage(0, "Ame No Uzume", affinity, 75, affinityRNG);
                                break;
                            case 2:
                                textHistory.add("legion targets ally one with a magic attack of affinity " + affinity);
                                calculateDamage(0, "Ame No Uzume", affinity, 75, affinityRNG);
                                break;
                            case 3:
                                textHistory.add("principality targets ally one with a magic attack of affinity " + affinity);
                                calculateDamage(0, "Ame No Uzume", affinity, 75, affinityRNG);
                                break;
                        }
                        break;
                    case 1:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally two with a magic attack of affinity " + affinity);
                                calculateDamage(1, "Cendrillon", affinity, 75, affinityRNG);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally two with a magic attack of affinity " + affinity);
                                calculateDamage(1, "Cendrillon", affinity, 75, affinityRNG);
                                break;
                            case 2:
                                textHistory.add("legion targets ally two with a magic attack of affinity " + affinity);
                                calculateDamage(1, "Cendrillon", affinity, 75, affinityRNG);
                                break;
                            case 3:
                                textHistory.add("principality targets ally two with a magic attack of affinity " + affinity);
                                calculateDamage(1, "Cendrillon", affinity, 75, affinityRNG);
                                break;
                        }
                        break;
                    case 2:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally three with a magic attack of affinity " + affinity);
                                calculateDamage(2, "Orpheus", affinity, 75, affinityRNG);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally three with a magic attack of affinity " + affinity);
                                calculateDamage(2, "Orpheus", affinity, 75, affinityRNG);
                                break;
                            case 2:
                                textHistory.add("legion targets ally three with a magic attack of affinity " + affinity);
                                calculateDamage(2, "Orpheus", affinity, 75, affinityRNG);
                                break;
                            case 3:
                                textHistory.add("principality targets ally three with a magic attack of affinity " + affinity);
                                calculateDamage(2, "Orpheus", affinity, 75, affinityRNG);
                                break;
                        }
                        break;
                    case 3:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally four with a magic attack of affinity " + affinity);
                                calculateDamage(3, "Robin Hood", affinity, 75, affinityRNG);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally four with a magic attack of affinity " + affinity);
                                calculateDamage(3, "Robin Hood", affinity, 75, affinityRNG);
                                break;
                            case 2:
                                textHistory.add("legion targets ally four with a magic attack of affinity " + affinity);
                                calculateDamage(3, "Robin Hood", affinity, 75, affinityRNG);
                                break;
                            case 3:
                                textHistory.add("principality targets ally four with a magic attack of affinity " + affinity);
                                calculateDamage(3, "Robin Hood", affinity, 75, affinityRNG);
                                break;
                        }
                        break;
                }
                break;
            case 2: // physical attack
                decision = rng.nextInt(4);
                switch (decision){ // which "enemy" (ally) to target
                    case 0:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally one with a basic attack");
                                calculateDamage(0, "Ame No Uzume", "Physical", 55, 6);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally one with a basic attack");
                                calculateDamage(0, "Ame No Uzume", "Physical", 55, 6);
                                break;
                            case 2:
                                textHistory.add("legion targets ally one with a basic attack");
                                calculateDamage(0, "Ame No Uzume", "Physical", 55, 6);
                                break;
                            case 3:
                                textHistory.add("principality targets ally one with a basic attack");
                                calculateDamage(0, "Ame No Uzume", "Physical", 55, 6);
                                break;
                        }
                        break;
                    case 1:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally two with a basic attack");
                                calculateDamage(1, "Cendrillon", "Physical", 55, 6);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally two with a basic attack");
                                calculateDamage(1, "Cendrillon", "Physical", 55, 6);
                                break;
                            case 2:
                                textHistory.add("legion targets ally two with a basic attack");
                                calculateDamage(1, "Cendrillon", "Physical", 55, 6);
                                break;
                            case 3:
                                textHistory.add("principality targets ally two with a basic attack");
                                calculateDamage(1, "Cendrillon", "Physical", 55, 6);
                                break;
                        }
                        break;
                    case 2:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally three with a basic attack");
                                calculateDamage(2, "Orpheus", "Physical", 55, 6);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally three with a basic attack");
                                calculateDamage(2, "Orpheus", "Physical", 55, 6);
                                break;
                            case 2:
                                textHistory.add("legion targets ally three with a basic attack");
                                calculateDamage(2, "Orpheus", "Physical", 55, 6);
                                break;
                            case 3:
                                textHistory.add("principality targets ally three with a basic attack");
                                calculateDamage(2, "Orpheus", "Physical", 55, 6);
                                break;
                        }
                        break;
                    case 3:
                        switch (enemy){
                            case 0:
                                textHistory.add("archangel targets ally four with a basic attack");
                                calculateDamage(3, "Robin Hood", "Physical", 55, 6);
                                break;
                            case 1:
                                textHistory.add("jack frost targets ally four with a basic attack");
                                calculateDamage(3, "Robin Hood", "Physical", 55, 6);
                                break;
                            case 2:
                                textHistory.add("legion targets ally four with a basic attack");
                                calculateDamage(3, "Robin Hood", "Physical", 55, 6);
                                break;
                            case 3:
                                textHistory.add("principality targets ally four with a basic attack");
                                calculateDamage(3, "Robin Hood", "Physical", 55, 6);
                                break;
                        }
                        break;
                }
                break;
            case 3: // buff / debuff
                decision = rng.nextInt(2);
                if (decision == 1){ // debuff "enemies" (allies)
                    decision = rng.nextInt(3);
                    switch (decision){ // which debuff
                        case 0: // attack
                            decision = rng.nextInt(4);
                            switch (decision){ // which ally to target
                                case 0: // ally one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally one with an attack debuff");
                                            allyStats[0][0] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally one with an attack debuff");
                                            allyStats[0][0] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally one with an attack debuff");
                                            allyStats[0][0] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally one with an attack debuff");
                                            allyStats[0][0] = 0.6;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally two with an attack debuff");
                                            allyStats[1][0] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally two with an attack debuff");
                                            allyStats[1][0] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally two with an attack debuff");
                                            allyStats[1][0] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally two with an attack debuff");
                                            allyStats[1][0] = 0.6;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally three with an attack debuff");
                                            allyStats[2][0] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally three with an attack debuff");
                                            allyStats[2][0] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally three with an attack debuff");
                                            allyStats[2][0] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally three with an attack debuff");
                                            allyStats[2][0] = 0.6;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally four with an attack debuff");
                                            allyStats[3][0] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally four with an attack debuff");
                                            allyStats[3][0] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally four with an attack debuff");
                                            allyStats[3][0] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally four with an attack debuff");
                                            allyStats[3][0] = 0.6;
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case 1: // defense
                            decision = rng.nextInt(4);
                            switch (decision){ // which ally to target
                                case 0: // ally one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally one with an defense debuff");
                                            allyStats[0][1] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally one with an defense debuff");
                                            allyStats[0][1] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally one with an defense debuff");
                                            allyStats[0][1] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally one with an defense debuff");
                                            allyStats[0][1] = 1.6;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally two with an defense debuff");
                                            allyStats[1][1] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally two with an defense debuff");
                                            allyStats[1][1] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally two with an defense debuff");
                                            allyStats[1][1] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally two with an defense debuff");
                                            allyStats[1][1] = 1.6;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally three with an defense debuff");
                                            allyStats[2][1] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally three with an defense debuff");
                                            allyStats[2][1] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally three with an defense debuff");
                                            allyStats[2][1] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally three with an defense debuff");
                                            allyStats[2][1] = 1.6;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally four with an defense debuff");
                                            allyStats[3][1] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally four with an defense debuff");
                                            allyStats[3][1] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets ally four with an defense debuff");
                                            allyStats[3][1] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets ally four with an defense debuff");
                                            allyStats[3][1] = 1.6;
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case 2: // agility
                            decision = rng.nextInt(4);
                            switch (decision){ // which ally to target
                                case 0: // ally one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally one with an agility debuff");
                                            allyStats[0][2] = 0.5;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally one with an agility debuff");
                                            allyStats[0][2] = 0.5;
                                            break;
                                        case 2:textHistory.add("Legion targets ally one with an agility debuff");
                                            allyStats[0][2] = 0.5;
                                            break;
                                        case 3:textHistory.add("Principality targets ally one with an agility debuff");
                                            allyStats[0][2] = 0.5;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally two with an agility debuff");
                                            allyStats[1][2] = 0.5;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally two with an agility debuff");
                                            allyStats[1][2] = 0.5;
                                            break;
                                        case 2:textHistory.add("Legion targets ally two with an agility debuff");
                                            allyStats[1][2] = 0.5;
                                            break;
                                        case 3:textHistory.add("Principality targets ally two with an agility debuff");
                                            allyStats[1][2] = 0.5;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally three with an agility debuff");
                                            allyStats[2][2] = 0.5;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally three with an agility debuff");
                                            allyStats[2][2] = 0.5;
                                            break;
                                        case 2:textHistory.add("Legion targets ally three with an agility debuff");
                                            allyStats[2][2] = 0.5;
                                            break;
                                        case 3:textHistory.add("Principality targets ally three with an agility debuff");
                                            allyStats[2][2] = 0.5;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets ally four with an agility debuff");
                                            allyStats[3][2] = 0.5;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets ally four with an agility debuff");
                                            allyStats[3][2] = 0.5;
                                            break;
                                        case 2:textHistory.add("Legion targets ally four with an agility debuff");
                                            allyStats[3][2] = 0.5;
                                            break;
                                        case 3:textHistory.add("Principality targets ally four with an agility debuff");
                                            allyStats[3][0] = 0.5;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else { // buff "allies" (enemies)
                    decision = rng.nextInt(3);
                    switch (decision){ // which buff
                        case 0: // attack
                            decision = rng.nextInt(4);
                            switch (decision){ // which enemy to target
                                case 0: // enemy one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy one with an attack buff");
                                            enemyStats[0][0] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy one with an attack buff");
                                            enemyStats[0][0] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy one with an attack buff");
                                            enemyStats[0][0] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy one with an attack buff");
                                            enemyStats[0][0] = 1.6;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy two with an attack buff");
                                            enemyStats[1][0] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy two with an attack buff");
                                            enemyStats[1][0] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy two with an attack buff");
                                            enemyStats[1][0] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy two with an attack buff");
                                            enemyStats[1][0] = 1.6;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy three with an attack buff");
                                            enemyStats[2][0] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy three with an attack buff");
                                            enemyStats[2][0] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy three with an attack buff");
                                            enemyStats[2][0] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy three with an attack buff");
                                            enemyStats[2][0] = 1.6;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy four with an attack buff");
                                            enemyStats[3][0] = 1.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy four with an attack buff");
                                            enemyStats[3][0] = 1.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy four with an attack buff");
                                            enemyStats[3][0] = 1.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy four with an attack buff");
                                            enemyStats[3][0] = 1.6;
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case 1: // defense
                            decision = rng.nextInt(4);
                            switch (decision){ // which enemy to target
                                case 0: // enemy one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy one with an defense buff");
                                            enemyStats[0][1] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy one with an defense buff");
                                            enemyStats[0][1] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy one with an defense buff");
                                            enemyStats[0][1] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy one with an defense buff");
                                            enemyStats[0][1] = 0.6;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy two with an defense buff");
                                            enemyStats[1][1] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy two with an defense buff");
                                            enemyStats[1][1] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy two with an defense buff");
                                            enemyStats[1][1] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy two with an defense buff");
                                            enemyStats[1][1] = 0.6;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy three with an defense buff");
                                            enemyStats[2][1] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy three with an defense buff");
                                            enemyStats[2][1] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy three with an defense buff");
                                            enemyStats[2][1] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy three with an defense buff");
                                            enemyStats[2][1] = 0.6;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy four with an defense buff");
                                            enemyStats[3][1] = 0.6;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy four with an defense buff");
                                            enemyStats[3][1] = 0.6;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy four with an defense buff");
                                            enemyStats[3][1] = 0.6;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy four with an defense buff");
                                            enemyStats[3][1] = 0.6;
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case 2: // agility
                            decision = rng.nextInt(4);
                            switch (decision){ // which enemy to target
                                case 0: // enemy one
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy one with an agility buff");
                                            enemyStats[0][2] = 2;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy one with an agility buff");
                                            enemyStats[0][2] = 2;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy one with an agility buff");
                                            enemyStats[0][2] = 2;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy one with an agility buff");
                                            enemyStats[0][2] = 2;
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy two with an agility buff");
                                            enemyStats[1][2] = 2;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy two with an agility buff");
                                            enemyStats[1][2] = 2;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy two with an agility buff");
                                            enemyStats[1][2] = 2;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy two with an agility buff");
                                            enemyStats[1][2] = 2;
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy three with an agility buff");
                                            enemyStats[2][2] = 2;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy three with an agility buff");
                                            enemyStats[2][2] = 2;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy three with an agility buff");
                                            enemyStats[2][2] = 2;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy three with an agility buff");
                                            enemyStats[2][2] = 2;
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (enemy){
                                        case 0:
                                            textHistory.add("Archangel targets enemy four with an agility buff");
                                            enemyStats[3][2] = 2;
                                            break;
                                        case 1:textHistory.add("Jack Frost targets enemy four with an agility buff");
                                            enemyStats[3][2] = 2;
                                            break;
                                        case 2:textHistory.add("Legion targets enemy four with an agility buff");
                                            enemyStats[3][2] = 2;
                                            break;
                                        case 3:textHistory.add("Principality targets enemy four with an agility buff");
                                            enemyStats[3][0] = 2;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
                break;
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
        switch (currentCharacter){
            case 0: // ame
                switch (enemy){
                    case 0:
                        textHistory.add("Ame No Uzume uses a basic attack on Archangel");
                        calculateDamage(0, "Archangel", "Physical", 45, 6);
                        break;
                    case 1:
                        textHistory.add("Ame No Uzume uses a basic attack on Jack Frost");
                        calculateDamage(1, "Jack Frost", "Physical", 45, 6);
                        break;
                    case 2:
                        textHistory.add("Ame No Uzume uses a basic attack on Legion");
                        calculateDamage(2, "Legion", "Physical", 45, 6);
                        break;
                    case 3:
                        textHistory.add("Ame No Uzume uses a basic attack on Principality");
                        calculateDamage(3, "Principality", "Physical", 45, 6);
                        break;
                }
                break;
            case 1: // cendrillon
                switch (move){
                    case 2:
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses Snip Snip on Archangel");
                                calculateDamage(0, "Archangel", "Physical", 85, 6);
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Snip Snip on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Physical", 85, 6);
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Snip Snip on Legion");
                                calculateDamage(2, "Legion", "Physical", 85, 6);
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses Snip Snip on Principality");
                                calculateDamage(3, "Principality", "Physical", 85, 6);
                                break;
                        }
                        hpAlly[1] -= 40;
                        break;
                    default:
                        switch (enemy){
                            case 0:
                                textHistory.add("Cendrillon uses a basic attack on Archangel");
                                calculateDamage(0, "Archangel", "Physical", 45, 6);
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses a basic attack on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Physical", 45, 6);
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses a basic attack on Legion");
                                calculateDamage(2, "Legion", "Physical", 45, 6);
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses a basic attack on Principality");
                                calculateDamage(3, "Principality", "Physical", 45, 6);
                                break;
                        }
                        break;
                }
                break;
            case 2: // orpheus
                switch (enemy){
                    case 0:
                        textHistory.add("Orpheus uses a basic attack on Archangel");
                        calculateDamage(0, "Archangel", "Physical", 45, 6);
                        break;
                    case 1:
                        textHistory.add("Orpheus uses a basic attack on Jack Frost"); 
                        calculateDamage(1, "Jack Frost", "Physical", 45, 6);                       
                        break;
                    case 2:
                        textHistory.add("Orpheus uses a basic attack on Legion");
                        calculateDamage(2, "Legion", "Physical", 45, 6);
                        break;
                    case 3:
                        textHistory.add("Orpheus uses a basic attack on Principality");
                        calculateDamage(3, "Principality", "Physical", 45, 6);
                        break;
                }
                break;
            case 3: // robin hood
                switch (enemy){
                    case 0:
                        textHistory.add("Robin Hood uses a basic attack on Archangel");
                        calculateDamage(0, "Archangel", "Physical", 45, 6);
                        break;
                    case 1:
                        textHistory.add("Robin Hood uses a basic attack on Jack Frost");
                        calculateDamage(1, "Jack Frost", "Physical", 45, 6);
                        break;
                    case 2:
                        textHistory.add("Robin Hood uses a basic attack on Legion");
                        calculateDamage(2, "Legion", "Physical", 45, 6);
                        break;
                    case 3:
                        textHistory.add("Robin Hood uses a basic attack on Principality");
                        calculateDamage(3, "Principality", "Physical", 45, 6);
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
        switch (currentCharacter){
            case 0: // ame
                switch (move){
                    case 0: // zephyr
                        switch (enemy){
                            case 0:
                                textHistory.add("Ame No Uzume uses Zephyr on Archangel");
                                calculateDamage(0, "Archangel", "Wind", 85, 2);
                                break;
                            case 1:
                                textHistory.add("Ame No Uzume uses Zephyr on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Wind", 85, 2);
                                break;
                            case 2:
                                textHistory.add("Ame No Uzume uses Zephyr on Legion");
                                calculateDamage(2, "Legion", "Wind", 85, 2);
                                break;
                            case 3:
                                textHistory.add("Ame No Uzume uses Zephyr on Principality");
                                calculateDamage(3, "Principality", "Wind", 85, 2);
                                break;
                        }
                        spAlly[0] -= 9;
                        break;
                    case 1: // monsoon
                        textHistory.add("Ame No Uzume uses Monsoon on every enemy");
                        calculateDamage(0, "Archangel", "Wind", 85, 2);
                        calculateDamage(1, "Jack Frost", "Wind", 85, 2);
                        calculateDamage(2, "Legion", "Wind", 85, 2);
                        calculateDamage(3, "Principality", "Wind", 85, 2);
                        spAlly[0] -= 21;
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
                                calculateDamage(0, "Archangel", "Water", 85, 1);
                                break;
                            case 1:
                                textHistory.add("Cendrillon uses Aqua Prison on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Water", 85, 1);
                                break;
                            case 2:
                                textHistory.add("Cendrillon uses Aqua Prison on Legion");
                                calculateDamage(2, "Legion", "Water", 85, 1);
                                break;
                            case 3:
                                textHistory.add("Cendrillon uses Aqua Prison on Principality");
                                calculateDamage(3, "Principality", "Water", 85, 1);
                                break;
                        }
                        spAlly[1] -= 9;
                        break;
                    case 1: // surging tide
                        textHistory.add("Cendrillon uses Surging Tide on every enemy");
                        calculateDamage(0, "Archangel", "Water", 65, 1);
                        calculateDamage(1, "Jack Frost", "Water", 65, 1);
                        calculateDamage(2, "Legion", "Water", 65, 1);
                        calculateDamage(3, "Principality", "Water", 65, 1);
                        spAlly[1] -= 21;
                        break;
                    case 2: // ability 3
                        // this is a physical ability managed by another script
                        System.out.println("if youre here youve done something wrong");
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
                                calculateDamage(0, "Archangel", "Moon", 85, 5);
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Lunar Rush on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Moon", 85, 5);
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Lunar Rush on Legion");
                                calculateDamage(2, "Legion", "Moon", 85, 5);
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Lunar Rush on Principality");
                                calculateDamage(3, "Principality", "Moon", 85, 5);
                                break;
                        }
                        spAlly[2] -= 9;
                        break;
                    case 1: // moon fall
                        textHistory.add("Orpheus uses Moonfall on every enemy");
                        calculateDamage(0, "Archangel", "Moon", 65, 5);
                        calculateDamage(1, "Jack Frost", "Moon", 65, 5);
                        calculateDamage(2, "Legion", "Moon", 65, 5);
                        calculateDamage(3, "Principality", "Moon", 65, 5);
                        spAlly[2] -= 21;
                        break;
                    case 2: // shattering strike
                        switch (enemy){
                            case 0:
                                textHistory.add("Orpheus uses Shattering Strike on Archangel");
                                calculateDamage(0, "Archangel", "Earth", 85, 3);
                                break;
                            case 1:
                                textHistory.add("Orpheus uses Shattering Strike on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Earth", 85, 3);
                                break;
                            case 2:
                                textHistory.add("Orpheus uses Shattering Strike on Legion");
                                calculateDamage(2, "Legion", "Earth", 85, 3);
                                break;
                            case 3:
                                textHistory.add("Orpheus uses Shattering Strike on Principality");
                                calculateDamage(3, "Principality", "Earth", 85, 3);
                                break;
                        }
                        spAlly[2] -= 9;
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
                                calculateDamage(0, "Archangel", "Sun", 85, 4);
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Zenith Blade on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Sun", 85, 4);
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Zenith Blade on Legion");
                                calculateDamage(2, "Legion", "Sun", 85, 4);
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Zenith Blade on Principality");
                                calculateDamage(3, "Principality", "Sun", 85, 4);
                                break;
                        }
                        spAlly[3] -= 9;
                        break;
                    case 1:  // solar flare
                        textHistory.add("Robin Hood uses Solar Flare on every enemy");
                        calculateDamage(0, "Archangel", "Sun", 65, 4);
                        calculateDamage(1, "Jack Frost", "Sun", 55, 4);
                        calculateDamage(2, "Legion", "Sun", 65, 4);
                        calculateDamage(3, "Principality", "Sun", 65, 4);
                        spAlly[3] -= 21;
                        break;
                    case 2: // 
                        switch (enemy){
                            case 0:
                                textHistory.add("Robin Hood uses Sear on Archangel");
                                calculateDamage(0, "Archangel", "Fire", 85, 0);
                                break;
                            case 1:
                                textHistory.add("Robin Hood uses Sear on Jack Frost");
                                calculateDamage(1, "Jack Frost", "Fire", 85, 0);
                                break;
                            case 2:
                                textHistory.add("Robin Hood uses Sear on Legion");
                                calculateDamage(2, "Legion", "Fire", 85, 0);
                                break;
                            case 3:
                                textHistory.add("Robin Hood uses Sear on Principality");
                                calculateDamage(3, "Principality", "Fire", 85, 0);
                                break;
                        }
                        spAlly[3] -= 9;
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
                                allyStats[0][0] = 2; // multiplying total damage by 2 means more damage, which means higher attack
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Robin Hood boosts Attack of Cendrillon");
                                allyStats[1][0] = 2;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Robin Hood boosts Attack of Orpheus");
                                allyStats[2][0] = 2;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Robin Hood boosts Attack of Robin Hood");
                                allyStats[3][0] = 2;
                                break;
                        }
                        spAlly[3] -= 12;
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
                                allyStats[0][1] = 0.5; // multiplying total damage by 0.5 means less damage, which means higher defense
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Orpheus boosts Defense of Cendrillon");
                                allyStats[1][1] = 0.5;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Orpheus boosts Defense of Orpheus");
                                allyStats[2][1] = 0.5;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Orpheus boosts Defense of Robin Hood");
                                allyStats[3][1] = 0.5;
                                break;
                        }
                        spAlly[2] -= 12;
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
                                allyStats[0][2] = 2;
                                break;
                            case 1: // boost cendrillon
                                textHistory.add("Cendrillon boosts Agility of Cendrillon");
                                allyStats[1][2] = 2;
                                break;
                            case 2: // boost orpheus
                                textHistory.add("Cendrillon boosts Agility of Orpheus");
                                allyStats[2][2] = 2;
                                break;
                            case 3: // boost robin hood
                                textHistory.add("Cendrillon boosts Agility of Robin Hood");
                                allyStats[3][2] = 2;
                                break;
                        }
                        spAlly[1] -= 12;
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
