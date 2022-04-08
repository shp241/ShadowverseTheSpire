 package shadowverseCharbosses.bosses.KMR;
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import shadowverseCharbosses.cards.bishop.*;
 import shadowverseCharbosses.cards.bishop.EnBlackenedScripture;
 import shadowverseCharbosses.cards.nemesis.EnMoonAlmiraj;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 import java.util.ArrayList;
 
 public class ArchetypeKMR extends ArchetypeBaseKMR {
   public ArchetypeKMR() {
     super("KMR_BISHOP", "KMR_BISHOP");
     if (AbstractDungeon.ascensionLevel >= 19){
       this.maxHPModifier += 300;
     }else if (AbstractDungeon.ascensionLevel >= 4){
       this.maxHPModifier += 200;
     }
     this.actNum = 4;
   }
   
   public void addedPreBattle() {
     super.addedPreBattle();
     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
   }

   public void initialize() {
     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 19);
   }
 
   
   public ArrayList<AbstractCard> getThisTurnCards() {
     ArrayList<AbstractCard> cardsList = new ArrayList<>();
     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 19);
     if (!this.looped) {
       switch (this.turn) {
         case 0:
           addToList(cardsList, (AbstractCard)new EnAcolyteLight(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnHereticalInquiry(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnArdentSister(),extraUpgrades);
           this.turn++;
           break;
         case 1:
           addToList(cardsList, (AbstractCard)new SacredLion(), extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnDarkPriest(), extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnPriestOfTheCudgel(),extraUpgrades);
           this.turn++;
           break;
         case 2:
           addToList(cardsList, (AbstractCard)new EnRadiantAngel(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnBlackenedScripture(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnAncientLionSpirit(),extraUpgrades);
           this.turn++;
           break;
         case 3:
           addToList(cardsList, (AbstractCard)new EnThemisDecree(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnPriestOfTheCudgel(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnDarkPriest(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnSacredPlea(),extraUpgrades);
           this.turn++;
           break;
         case 4:
           addToList(cardsList, (AbstractCard)new EnHereticalInquiry(), extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnLucifer(), extraUpgrades);
           addToList(cardsList,(AbstractCard) new EnDarkPriest(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnHeavenlyHound(), extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnArdentSister(), extraUpgrades);
           this.turn++;
           break;
         case 5:
           addToList(cardsList, (AbstractCard)new SacredLion(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnAncientLionSpirit(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnHeavenlyHound(),extraUpgrades);
           this.turn = 0;
           this.looped = true;
           break;
       } 
     } else {
       switch (this.turn) {
         case 0:
           addToList(cardsList, (AbstractCard)new SacredLion(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnDarkPriest(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnArdentSister(),extraUpgrades);
           this.turn++;
           break;
         case 1:
           addToList(cardsList, (AbstractCard)new EnPriestOfTheCudgel(), extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnRadiantAngel(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnHeavenlyHound(),extraUpgrades);
           this.turn++;
           break;
         case 2:
           addToList(cardsList, (AbstractCard)new EnAcolyteLight(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnLucifer(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnDarkPriest(),extraUpgrades);
           this.turn++;
           this.turn = 0;
           break;
         case 3:
           addToList(cardsList, (AbstractCard)new EnMoonAlmiraj(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnBlackenedScripture(),extraUpgrades);
           addToList(cardsList, (AbstractCard)new EnHeavenlyHound(),extraUpgrades);
           break;
       } 
     } 
     return cardsList;
   }
 
   
   public void initializeBonusRelic() {
   }
 }

