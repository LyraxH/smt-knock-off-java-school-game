
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
    ImageIcon ameNoUzumeIMG = new ImageIcon("allies/100ameNoUzume.png");
    ImageIcon cendrillonIMG = new ImageIcon("allies/100cendrillon.png");
    ImageIcon orpheusIMG = new ImageIcon("allies/100orpheus.png");
    ImageIcon robinHoodIMG = new ImageIcon("allies/100robinHood.png");
    //turning allies into jlabels
    JLabel ameNoUzume = new JLabel(ameNoUzumeIMG);
    JLabel cendrillon = new JLabel(cendrillonIMG);
    JLabel orpheus = new JLabel(orpheusIMG);
    JLabel robinHood = new JLabel(robinHoodIMG);
    
    // enemies
    ImageIcon archangelIMG = new ImageIcon("enemies/100archangel.png");
    ImageIcon jackFrostIMG = new ImageIcon("enemies/100jackFrost.png");
    ImageIcon legionIMG = new ImageIcon("enemies/100legion.png");
    ImageIcon principalityIMG = new ImageIcon("enemies/100principality.png");
    // turning enemies into jlabels
    JLabel archangel = new JLabel(archangelIMG);
    JLabel jackFrost = new JLabel(jackFrostIMG);
    JLabel legion = new JLabel(legionIMG);
    JLabel principality = new JLabel(principalityIMG);
    
    // elements and affinities
    ImageIcon fireIMG = new ImageIcon("elements/fire3.png");
    ImageIcon waterIMG = new ImageIcon("elements/water3.png");
    ImageIcon earthIMG = new ImageIcon("elements/earth3.png");
    ImageIcon airIMG = new ImageIcon("elements/air3.png");
    ImageIcon sunIMG = new ImageIcon("elements/sun3.png");
    ImageIcon moonIMG = new ImageIcon("elements/moon3.png");
    ImageIcon physIMG = new ImageIcon("elements/phys3.png");
    ImageIcon weakIMG = new ImageIcon("elements/weak3.png");
    ImageIcon resistIMG = new ImageIcon("elements/resist3.png");
    ImageIcon nullifyIMG = new ImageIcon("elements/nullify3.png");
    ImageIcon normalIMG = new ImageIcon("elements/normal3.png");
    //turning elements and affinites into jlabels
    JLabel fire = new JLabel(fireIMG);
    JLabel water = new JLabel(waterIMG);
    JLabel earth = new JLabel(earthIMG);
    JLabel air = new JLabel(airIMG);
    JLabel sun = new JLabel(sunIMG);
    JLabel moon = new JLabel(moonIMG);
    JLabel phys = new JLabel(physIMG);
    JLabel weak = new JLabel(weakIMG);
    JLabel resist = new JLabel(resistIMG);
    JLabel nullify = new JLabel(nullifyIMG);
    
    
    // move icons
    // 0 fire
    ImageIcon pyroclasm = new ImageIcon("buttons/moves/0fire/pyroclasm.png"); // fire to all
    ImageIcon unPyroclasm = new ImageIcon("buttons/moves/0fire/pyroclasmUn.png"); // fire to all
    ImageIcon sear = new ImageIcon("buttons/moves/0fire/sear.png"); // fire to one
    ImageIcon unSear = new ImageIcon("buttons/moves/0fire/searUn.png"); // fire to one
    
    // 1 water
    ImageIcon aquaPrison = new ImageIcon("buttons/moves/1water/aquaPrison.png"); // water to one
    ImageIcon unAquaPrison = new ImageIcon("buttons/moves/1water/aquaPrisonUn.png"); // water to one
    ImageIcon surgingTide = new ImageIcon("buttons/moves/1water/surgingTide.png"); // water to all
    ImageIcon unSurgingTide = new ImageIcon("buttons/moves/1water/surgingTideUn.png"); // water to all
    
    // 2 wind
    ImageIcon monsoon = new ImageIcon("buttons/moves/2wind/monsoon.png"); // wind to all
    ImageIcon unMonsoon = new ImageIcon("buttons/moves/2wind/monsoonUn.png"); // wind to all
    ImageIcon zephyr = new ImageIcon("buttons/moves/2wind/zephyr.png"); // wind to one
    ImageIcon unZephyr = new ImageIcon("buttons/moves/2wind/zephyrUn.png"); // wind to one
    
    // 3 earth
    ImageIcon magnetStorm = new ImageIcon("buttons/moves/3earth/magnetStorm.png"); // earth to all
    ImageIcon unMagnetStorm = new ImageIcon("buttons/moves/3earth/magnetStormUn.png"); // earth to all
    ImageIcon shatteringStrike = new ImageIcon("buttons/moves/3earth/shatteringStrike.png"); // earth to one
    ImageIcon unShatteringStrike = new ImageIcon("buttons/moves/3earth/shatteringStrikeUn.png"); // earth to one
    
    // 4 sun
    ImageIcon solarFlare = new ImageIcon("buttons/moves/4sun/solarFlare.png"); // sun to all
    ImageIcon unSolarFlare = new ImageIcon("buttons/moves/4sun/solarFlareUn.png"); // sun to all
    ImageIcon zenithBlade = new ImageIcon("buttons/moves/4sun/zenithBlade.png"); // sun to one
    ImageIcon unZenithBlade = new ImageIcon("buttons/moves/4sun/zenithBladeUn.png"); // sun to one
    
    // 5 moon
    ImageIcon lunarRush = new ImageIcon("buttons/moves/5moon/lunarRush.png"); // moon to one
    ImageIcon unLunarRush = new ImageIcon("buttons/moves/5moon/lunarRush.png"); // moon to one
    ImageIcon moonfall = new ImageIcon("buttons/moves/5moon/moonfall.png"); // moon to all
    ImageIcon unMoonfall = new ImageIcon("buttons/moves/5moon/moonfallUn.png"); // moon to all
    
    // 6 phys
    ImageIcon needlework = new ImageIcon("buttons/moves/6phys/needlework.png"); // phys to all
    ImageIcon unNeedlework = new ImageIcon("buttons/moves/6phys/needleworkUn.png"); // phys to all
    ImageIcon snipSnip = new ImageIcon("buttons/moves/6phys/snipSnip.png"); // phys to one
    ImageIcon unSnipSnip = new ImageIcon("buttons/moves/6phys/snipSnipUn.png"); // phys to one
}
