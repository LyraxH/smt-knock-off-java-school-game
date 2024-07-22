
/**
 * holds / imports all the images for the game
 *
 * @ ts
 * @ 08/04/24 -> 
 */
import javax.swing.*;
import java.awt.*;
public class Images
{
    ImageIcon LOGO = new ImageIcon("stimMegumiSensei.png");
    // hud moves
    ImageIcon unAttackIcon = new ImageIcon("buttons/unselectedAttack.png");
    ImageIcon unGuardIcon = new ImageIcon("buttons/unselectedGuard.png");
    ImageIcon unMagicIcon = new ImageIcon("buttons/unselectedMagic.png");
    ImageIcon unItemIcon = new ImageIcon("buttons/unselectedItem.png");
    
    ImageIcon attackIcon = new ImageIcon("buttons/selectedAttack.png");
    ImageIcon guardIcon = new ImageIcon("buttons/selectedGuard.png");
    ImageIcon magicIcon = new ImageIcon("buttons/selectedMagic.png");
    ImageIcon itemIcon = new ImageIcon("buttons/selectedItem.png");
    
    // allies
    ImageIcon aegisIMG = new ImageIcon("sprites/aegis.png");
    ImageIcon dawnIMG = new ImageIcon("sprites/dawn.png");
    ImageIcon sentinelIMG = new ImageIcon("sprites/sentinel.png");
    ImageIcon blazeIMG = new ImageIcon("sprites/blaze.png");
    //turning allies into jlabels
    JLabel aegis = new JLabel(aegisIMG);
    JLabel dawn = new JLabel(dawnIMG);
    JLabel sentinel = new JLabel(sentinelIMG);
    JLabel blaze = new JLabel(blazeIMG);
    
    // enemies
    ImageIcon virtueIMG = new ImageIcon("sprites/virtue1.png");
    ImageIcon eerieIMG = new ImageIcon("sprites/eerie.png");
    ImageIcon soulIMG = new ImageIcon("sprites/soul.png");
    ImageIcon reignIMG = new ImageIcon("sprites/reign.png");
    // turning enemies into jlabels
    JLabel virtue = new JLabel(virtueIMG);
    JLabel eerie = new JLabel(eerieIMG);
    JLabel soul = new JLabel(soulIMG);
    JLabel reign = new JLabel(reignIMG);
    
    // panels/backgronds
    ImageIcon allyPanel = new ImageIcon("panels/allyPanel14.png");
    ImageIcon gamePanel = new ImageIcon("panels/gamepanel.png");
    ImageIcon enemyPanel = new ImageIcon("panels/enemyPanel14.png");
    
    //sprites
    ImageIcon aegisSelected = new ImageIcon("sprites/aegisSelected.png");
    ImageIcon dawnSelected = new ImageIcon("sprites/dawnSelected.png");
    ImageIcon sentinelSelected = new ImageIcon("sprites/sentinelSelected.png");
    ImageIcon blazeSelected = new ImageIcon("sprites/blazeSelected.png");
    ImageIcon virtueSelected = new ImageIcon("sprites/virtueSelected1.png");
    ImageIcon eerieSelected = new ImageIcon("sprites/eerieSelected.png");
    ImageIcon soulSelected = new ImageIcon("sprites/soulSelected.png");
    ImageIcon reignSelected = new ImageIcon("sprites/reignSelected.png");
    ImageIcon playerTurnOne = new ImageIcon("sprites/playerTurnOne.png");
    ImageIcon playerTurnTwo = new ImageIcon("sprites/playerTurnTwo.png");
    ImageIcon playerTurnThree = new ImageIcon("sprites/playerTurnThree.png");
    ImageIcon playerTurnFour = new ImageIcon("sprites/playerTurnFour.png");
    ImageIcon enemyTurnOne = new ImageIcon("sprites/enemyTurnOne.png");
    ImageIcon enemyTurnTwo = new ImageIcon("sprites/enemyTurntwo.png");
    ImageIcon enemyTurnThree = new ImageIcon("sprites/enemyTurnThree.png");
    ImageIcon enemyTurnFour = new ImageIcon("sprites/enemyTurnFour.png");
    ImageIcon youLoseOne = new ImageIcon("sprites/youLoseOne2.png");
    ImageIcon youLoseTwo = new ImageIcon("sprites/youLoseTwo2.png");
    ImageIcon youLoseThree = new ImageIcon("sprites/youLoseThree2.png");
    ImageIcon youLoseFour = new ImageIcon("sprites/youLoseFour2.png");
    ImageIcon youWinOne = new ImageIcon("sprites/youWinOne.png");
    ImageIcon youWinTwo = new ImageIcon("sprites/youWinTwo.png");
    ImageIcon youWinThree = new ImageIcon("sprites/youWinThree.png");
    ImageIcon youWinFour = new ImageIcon("sprites/youWinFour.png");
    ///
    ImageIcon warningLeft = new ImageIcon("sprites/warnings/left.png");
    ImageIcon warningRight = new ImageIcon("sprites/warnings/right.png");
    ImageIcon affinitiesRevealed1 = new ImageIcon("sprites/warnings/affinitiesRevealed1.png");
    ImageIcon affinitiesRevealed2 = new ImageIcon("sprites/warnings/affinitiesRevealed2.png");
    ImageIcon fullHP1 = new ImageIcon("sprites/warnings/fullHP1.png");
    ImageIcon fullHP2 = new ImageIcon("sprites/warnings/fullHP2.png");
    ImageIcon isAlive1 = new ImageIcon("sprites/warnings/isAlive1.png");
    ImageIcon isAlive2 = new ImageIcon("sprites/warnings/isAlive2.png");
    ImageIcon isDead1 = new ImageIcon("sprites/warnings/isDead1.png");
    ImageIcon isDead2 = new ImageIcon("sprites/warnings/isDead2.png");
    ImageIcon maxSP1 = new ImageIcon("sprites/warnings/maxSP1.png");
    ImageIcon maxSP2 = new ImageIcon("sprites/warnings/maxSP2.png");
    ImageIcon noHP1 = new ImageIcon("sprites/warnings/noHP1.png");
    ImageIcon noHP2 = new ImageIcon("sprites/warnings/noHP2.png");
    ImageIcon noInjured1 = new ImageIcon("sprites/warnings/noInjured1.png");
    ImageIcon noInjured2 = new ImageIcon("sprites/warnings/noInjured2.png");
    ImageIcon noOneDead1 = new ImageIcon("sprites/warnings/noOneDead1.png");
    ImageIcon noOneDead2 = new ImageIcon("sprites/warnings/noOneDead2.png");
    ImageIcon noSP1 = new ImageIcon("sprites/warnings/noSP1.png");
    ImageIcon noSP2 = new ImageIcon("sprites/warnings/noSP2.png");
    JLabel turnIndicatorOne = new JLabel(playerTurnOne);
    JLabel turnIndicatorTwo = new JLabel(playerTurnTwo);
    JLabel turnIndicatorThree = new JLabel(playerTurnThree);
    JLabel turnIndicatorFour = new JLabel(playerTurnFour);
    
    // elements and affinities
    ImageIcon fire = new ImageIcon("elements/fire3.png");
    ImageIcon water = new ImageIcon("elements/water3.png");
    ImageIcon air = new ImageIcon("elements/air3.png");
    ImageIcon earth = new ImageIcon("elements/earth3.png");
    ImageIcon sun = new ImageIcon("elements/sun3.png");
    ImageIcon moon = new ImageIcon("elements/moon3.png");
    ImageIcon phys = new ImageIcon("elements/phys3.png");
    ImageIcon weak = new ImageIcon("elements/weak3.png");
    ImageIcon resist = new ImageIcon("elements/resist3.png");
    ImageIcon nullify = new ImageIcon("elements/nullify3.png");
    ImageIcon normal = new ImageIcon("elements/normal3.png");
    ImageIcon unknown = new ImageIcon("elements/unknown.png");
    JLabel elementOne = new JLabel(fire);
    JLabel elementTwo = new JLabel(water);
    JLabel elementThree = new JLabel(air);
    JLabel elementFour = new JLabel(earth);
    JLabel elementFive = new JLabel(sun);
    JLabel elementSix = new JLabel(moon);
    JLabel elementSeven = new JLabel(phys);
    JLabel affinityOne = new JLabel(unknown);
    JLabel affinityTwo = new JLabel(unknown);
    JLabel affinityThree = new JLabel(unknown);
    JLabel affinityFour = new JLabel(unknown);
    JLabel affinityFive = new JLabel(unknown);
    JLabel affinitySix = new JLabel(unknown);
    JLabel affinitySeven = new JLabel(unknown);
    
    // game window overlay affinity weakness hitting notice thing
    ImageIcon weakOverlay = new ImageIcon("elements/popUpWeak.png");
    ImageIcon resistOverlay = new ImageIcon("elements/popUpResist.png");
    ImageIcon nullOverlay = new ImageIcon("elements/popUpNull.png");
    ImageIcon normalOverlay = new ImageIcon("elements/popUphit.png");
    ImageIcon dead = new ImageIcon("elements/dead3.png");
    ImageIcon test = new ImageIcon("elements/test.png");
    JLabel enemyOneAffinity = new JLabel();
    JLabel enemyTwoAffinity = new JLabel();
    JLabel enemyThreeAffinity = new JLabel();
    JLabel enemyFourAffinity = new JLabel();    
    JLabel blankOne = new JLabel();
    JLabel blankTwo = new JLabel();
    JLabel blankThree = new JLabel();
    JLabel blankFour = new JLabel();
    JLabel allyOneAffinity = new JLabel();
    JLabel allyTwoAffinity = new JLabel();
    JLabel allyThreeAffinity = new JLabel();
    JLabel allyFourAffinity = new JLabel();
    
    // stat changes overlays
    ImageIcon statOverlay = new ImageIcon("overlayBuffs/statsOverlay2.png");
    ImageIcon atkUp = new ImageIcon("overlayBuffs/attackUp.png");
    ImageIcon atkNorm = new ImageIcon("overlayBuffs/attackNormal2.png");
    ImageIcon atkDown = new ImageIcon("overlayBuffs/attackDown.png");
    ImageIcon defUp = new ImageIcon("overlayBuffs/defenseUp.png");
    ImageIcon defNorm = new ImageIcon("overlayBuffs/defenseNormal2.png");
    ImageIcon defDown = new ImageIcon("overlayBuffs/defenseDown.png");
    ImageIcon guard = new ImageIcon("overlayBuffs/guard.png");
    ImageIcon healed = new ImageIcon("overlayBuffs/healed.png");
    ImageIcon buffed = new ImageIcon("overlayBuffs/buffed.png");
    ImageIcon debuffed = new ImageIcon("overlayBuffs/debuffed.png");
    JLabel filler1 = new JLabel();
    JLabel filler2 = new JLabel();
    JLabel filler3 = new JLabel();
    JLabel filler4 = new JLabel();
    JLabel filler5 = new JLabel();
    JLabel filler6 = new JLabel();
    JLabel filler7 = new JLabel();
    JLabel filler8 = new JLabel();
    JLabel filler9 = new JLabel();
    JLabel filler10 = new JLabel();
    JLabel filler11 = new JLabel();
    JLabel filler12 = new JLabel();
    JLabel filler17 = new JLabel();
    JLabel filler18 = new JLabel();
    JLabel filler19 = new JLabel();
    JLabel filler20 = new JLabel();
    JLabel deadOverlay1 = new JLabel();
    JLabel deadOverlay2 = new JLabel();
    JLabel deadOverlay3 = new JLabel();
    JLabel deadOverlay4 = new JLabel();
    JLabel deadOverlay5 = new JLabel();
    JLabel deadOverlay6 = new JLabel();
    JLabel deadOverlay7 = new JLabel();
    JLabel deadOverlay8 = new JLabel();
    JLabel statOverlay1 = new JLabel(statOverlay);
    JLabel statOverlay2 = new JLabel(statOverlay);
    JLabel statOverlay3 = new JLabel(statOverlay);
    JLabel statOverlay4 = new JLabel(statOverlay);
    JLabel statOverlay5 = new JLabel(statOverlay);
    JLabel statOverlay6 = new JLabel(statOverlay);
    JLabel statOverlay7 = new JLabel(statOverlay);
    JLabel statOverlay8 = new JLabel(statOverlay);
    JLabel attackOverlay1 = new JLabel(atkNorm);
    JLabel attackOverlay2 = new JLabel(atkNorm);
    JLabel attackOverlay3 = new JLabel(atkNorm);
    JLabel attackOverlay4 = new JLabel(atkNorm);
    JLabel attackOverlay5 = new JLabel(atkNorm);
    JLabel attackOverlay6 = new JLabel(atkNorm);
    JLabel attackOverlay7 = new JLabel(atkNorm);
    JLabel attackOverlay8 = new JLabel(atkNorm);
    JLabel defenseOverlay1 = new JLabel(defNorm);
    JLabel defenseOverlay2 = new JLabel(defNorm);
    JLabel defenseOverlay3 = new JLabel(defNorm);
    JLabel defenseOverlay4 = new JLabel(defNorm);
    JLabel defenseOverlay5 = new JLabel(defNorm);
    JLabel defenseOverlay6 = new JLabel(defNorm);
    JLabel defenseOverlay7 = new JLabel(defNorm);
    JLabel defenseOverlay8 = new JLabel(defNorm);
    
    // tutotiral assets
    ImageIcon rightArrow = new ImageIcon("tutorial/rightArrowSmall.png");
    ImageIcon leftArrow = new ImageIcon("tutorial/leftArrowSmall.png");
    ImageIcon tutorial1 = new ImageIcon("tutorial/1overview.png");
    ImageIcon tutorial2 = new ImageIcon("tutorial/2moves.png");
    ImageIcon tutorial3 = new ImageIcon("tutorial/3basicAttack.png");
    ImageIcon tutorial4 = new ImageIcon("tutorial/4guard.png");
    ImageIcon tutorial5 = new ImageIcon("tutorial/5magic.png");
    ImageIcon tutorial6 = new ImageIcon("tutorial/6elementalAffinities.png");
    ImageIcon tutorial7 = new ImageIcon("tutorial/7singleTargetMagic.png");
    ImageIcon tutorial8 = new ImageIcon("tutorial/8targetAllMagic.png");
    ImageIcon tutorial9 = new ImageIcon("tutorial/9healing.png");
    ImageIcon tutorial10 = new ImageIcon("tutorial/10revives.png");
    ImageIcon tutorial11 = new ImageIcon("tutorial/11stats.png");
    ImageIcon tutorial12 = new ImageIcon("tutorial/12items.png");
    ImageIcon tutorial13 = new ImageIcon("tutorial/13oracleLens.png");
    ImageIcon tutorial14 = new ImageIcon("tutorial/14checkingAffinities.png");
    ImageIcon tutorial15 = new ImageIcon("tutorial/15everfrost.png");
    ImageIcon tutorial16 = new ImageIcon("tutorial/16lightningCrash.png");
    ImageIcon tutorial17 = new ImageIcon("tutorial/17shock.png");
    ImageIcon tutorial18 = new ImageIcon("tutorial/18cleanse.png");
    ImageIcon tutorial19 = new ImageIcon("tutorial/19difficulty.png");
    ImageIcon tutorial20 = new ImageIcon("tutorial/20battleLog.png");
    
    // move icons
    // 0 fire // alchemy symbol: fire
    ImageIcon pyroclasm = new ImageIcon("moves/0fire/pyroclasm.png"); // fire to all
    ImageIcon unPyroclasm = new ImageIcon("moves/0fire/pyroclasmUn.png"); // fire to all
    ImageIcon sear = new ImageIcon("moves/0fire/sear.png"); // fire to one
    ImageIcon unSear = new ImageIcon("moves/0fire/searUn.png"); // fire to one
    
    // 1 water // alchemy symbol: water
    ImageIcon aquaPrison = new ImageIcon("moves/1water/aquaPrison.png"); // water to one
    ImageIcon unAquaPrison = new ImageIcon("moves/1water/aquaPrisonUn.png"); // water to one
    ImageIcon surgingTide = new ImageIcon("moves/1water/surgingTide.png"); // water to all
    ImageIcon unSurgingTide = new ImageIcon("moves/1water/surgingTideUn.png"); // water to all
    
    // 2 air // alchemy symbol: air
    ImageIcon monsoon = new ImageIcon("moves/2wind/monsoon.png"); // wind to all
    ImageIcon unMonsoon = new ImageIcon("moves/2wind/monsoonUn.png"); // wind to all
    ImageIcon zephyr = new ImageIcon("moves/2wind/zephyr.png"); // wind to one
    ImageIcon unZephyr = new ImageIcon("moves/2wind/zephyrUn.png"); // wind to one
    
    // 3 earth // alchemy symbol: earth
    ImageIcon magnetStorm = new ImageIcon("moves/3earth/magnetStorm.png"); // earth to all
    ImageIcon unMagnetStorm = new ImageIcon("moves/3earth/magnetStormUn.png"); // earth to all
    ImageIcon shatteringStrike = new ImageIcon("moves/3earth/shatteringStrike.png"); // earth to one
    ImageIcon unShatteringStrike = new ImageIcon("moves/3earth/shatteringStrikeUn.png"); // earth to one
    
    // 4 sun // alchemy symbol: sun
    ImageIcon solarFlare = new ImageIcon("moves/4sun/solarFlare.png"); // sun to all
    ImageIcon unSolarFlare = new ImageIcon("moves/4sun/solarFlareUn.png"); // sun to all
    ImageIcon zenithBlade = new ImageIcon("moves/4sun/zenithBlade.png"); // sun to one
    ImageIcon unZenithBlade = new ImageIcon("moves/4sun/zenithBladeUn.png"); // sun to one
    
    // 5 moon // alchemy symbol: moon
    ImageIcon lunarRush = new ImageIcon("moves/5moon/lunarRush.png"); // moon to one
    ImageIcon unLunarRush = new ImageIcon("moves/5moon/lunarRushUn.png"); // moon to one
    ImageIcon moonfall = new ImageIcon("moves/5moon/moonfall.png"); // moon to all
    ImageIcon unMoonfall = new ImageIcon("moves/5moon/moonfallUn.png"); // moon to all
    
    // 6 phys // alchemy symbol: lead
    ImageIcon needlework = new ImageIcon("moves/6phys/needlework.png"); // phys to all
    ImageIcon unNeedlework = new ImageIcon("moves/6phys/needleworkUn.png"); // phys to all
    ImageIcon snipSnip = new ImageIcon("moves/6phys/snipSnip.png"); // phys to one
    ImageIcon unSnipSnip = new ImageIcon("moves/6phys/snipSnipUn.png"); // phys to one
    
    // 7 healing // alchemy symbol: vinegar
    ImageIcon redemption = new ImageIcon("moves/7heal/redemption.png"); // healing to one
    ImageIcon unRedemption = new ImageIcon("moves/7heal/redemptionUn.png"); // healing to one
    ImageIcon guardianAngel = new ImageIcon("moves/7heal/guardianAngel.png"); // revive to one
    ImageIcon unGuardianAngel = new ImageIcon("moves/7heal/guardianAngelUn.png"); // revive to one
     
    // 8 stat boosts // alchemy symbol sulfur
    ImageIcon atkBoost = new ImageIcon("moves/8buff/atkBoost.png"); // take a wild guess at what these do
    ImageIcon unAtkBoost = new ImageIcon("moves/8buff/atkBoostUn.png");
    ImageIcon defBoost = new ImageIcon("moves/8buff/defBoost.png");
    ImageIcon unDefBoost = new ImageIcon("moves/8buff/defBoostUn.png");
    ImageIcon aglBoost = new ImageIcon("moves/8buff/aglBoost.png");
    ImageIcon unAglBoost = new ImageIcon("moves/8buff/aglBoostUn.png");
    ImageIcon atkDownTwo = new ImageIcon("moves/8buff/atkDown.png");
    ImageIcon unAtkDown = new ImageIcon("moves/8buff/atkDownUn.png");
    
    // 9 items // alchemy symbol potash
    ImageIcon oracleLens = new ImageIcon("moves/9item/oracleLens.png"); // reveals affinity of one enemy
    ImageIcon unOracleLens = new ImageIcon("moves/9item/oracleLensUn.png"); // reveals affinity of one enemy
    ImageIcon everfrost = new ImageIcon("moves/9item/everfrost.png"); // restores mana to ally
    ImageIcon unEverfrost = new ImageIcon("moves/9item/everfrostUn.png"); // restores mana to ally
    ImageIcon lightningCrash = new ImageIcon("moves/9item/lightningCrash.png"); // shock one enemy
    ImageIcon unLightningCrash = new ImageIcon("moves/9item/lightningCrashUn.png"); // shock one enemy
    ImageIcon focus = new ImageIcon("moves/9item/focus.png"); // shock one enemy
    ImageIcon unFocus = new ImageIcon("moves/9item/focusUn.png"); // shock one enemy
    ImageIcon cleanse = new ImageIcon("moves/9item/cleanse.png");
    ImageIcon unCleanse = new ImageIcon("moves/9item/cleanseUn.png");
    
    // enemy select buttons
    ImageIcon enemyOne = new ImageIcon("buttons/enemyOne.png");
    ImageIcon enemyTwo = new ImageIcon("buttons/enemyTwo.png");
    ImageIcon enemyThree = new ImageIcon("buttons/enemyThree.png");
    ImageIcon enemyFour = new ImageIcon("buttons/enemyFour.png");
    ImageIcon unEnemyOne = new ImageIcon("buttons/enemyOneUn.png");
    ImageIcon unEnemyTwo = new ImageIcon("buttons/enemyTwoUn.png");
    ImageIcon unEnemyThree = new ImageIcon("buttons/enemyThreeUn.png");
    ImageIcon unEnemyFour = new ImageIcon("buttons/enemyFourUn.png");
    
    // ally select buttons
    ImageIcon allyOne = new ImageIcon("buttons/allyOne.png");
    ImageIcon allyTwo = new ImageIcon("buttons/allyTwo.png");
    ImageIcon allyThree = new ImageIcon("buttons/allyThree.png");
    ImageIcon allyFour = new ImageIcon("buttons/allyFour.png");
    ImageIcon unAllyOne = new ImageIcon("buttons/allyOneUn.png");
    ImageIcon unAllyTwo = new ImageIcon("buttons/allyTwoUn.png");
    ImageIcon unAllyThree = new ImageIcon("buttons/allyThreeUn.png");
    ImageIcon unAllyFour = new ImageIcon("buttons/allyFourUn.png");
    
    // other, lmao
    ImageIcon back = new ImageIcon("buttons/backSelected.png");
    ImageIcon unBack = new ImageIcon("buttons/back.png");
    ImageIcon currentAegis = new ImageIcon("buttons/currentAegis.png");
    ImageIcon currentBlaze = new ImageIcon("buttons/currentBlaze.png");
    ImageIcon currentDawn = new ImageIcon("buttons/currentDawn.png");
    ImageIcon currentEerie = new ImageIcon("buttons/currentEerie.png");
    ImageIcon currentReign = new ImageIcon("buttons/currentReign.png");
    ImageIcon currentSentinel = new ImageIcon("buttons/currentSentinel.png");
    ImageIcon currentSoul = new ImageIcon("buttons/currentSoul.png");
    ImageIcon currentVirtue = new ImageIcon("buttons/currentVirtue.png");
}
