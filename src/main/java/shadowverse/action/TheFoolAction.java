 package shadowverse.action;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.ExhaustPower;

import java.util.ArrayList;

 
 public class TheFoolAction
   extends AbstractGameAction {
   private AbstractPlayer abstractPlayer;
   public  int amount;
   
   public TheFoolAction(AbstractPlayer abstractPlayer,int amount) {
     this.abstractPlayer = abstractPlayer;
     this.amount = amount;
   }


   public void update() {
       ArrayList<AbstractCard> cardGroup = new ArrayList<AbstractCard>();
       AbstractCard c = null;
       if (AbstractDungeon.player.drawPile.group.size() >= amount){
           for (int j=0;j<amount;j++){
               c = AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - j-1);
               cardGroup.add(c);
               int rand = AbstractDungeon.aiRng.random(1, 7);
               addToBot((AbstractGameAction)new DrawCardAction(abstractPlayer,1));
               if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)){
                   for (int i = 0;i<rand;i++){
                       c.flash();
                       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                       addToBot((AbstractGameAction)new ReduceCostAction(c));
                   }
               }else if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)){
                   for (int i = 0;i<rand;i++){
                       c.flash();
                       c.magicNumber = ++c.baseMagicNumber;
                       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                   }
               }
           }
       }else {
           int rest = amount - AbstractDungeon.player.drawPile.group.size();
           for (int j=0;j<AbstractDungeon.player.drawPile.group.size();j++) {
               c = AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - j - 1);
               cardGroup.add(c);
               int rand = AbstractDungeon.aiRng.random(1, 7);
               addToBot((AbstractGameAction) new DrawCardAction(abstractPlayer, 1));
               if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                   for (int i = 0; i < rand; i++) {
                       c.flash();
                       addToBot((AbstractGameAction) new SFXAction("spell_boost"));
                       addToBot((AbstractGameAction) new ReduceCostAction(c));
                   }
               } else if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                   for (int i = 0; i < rand; i++) {
                       c.flash();
                       c.magicNumber = ++c.baseMagicNumber;
                       addToBot((AbstractGameAction) new SFXAction("spell_boost"));
                   }
               }
           }
           if (AbstractDungeon.player.discardPile.group.size() >= rest){
               for (int j=0;j<rest;j++){
                   c = AbstractDungeon.player.discardPile.group.get(AbstractDungeon.player.discardPile.size() - j-1);
                   cardGroup.add(c);
                   int rand = AbstractDungeon.aiRng.random(1, 7);
                   addToBot((AbstractGameAction)new DrawCardAction(abstractPlayer,1));
                   if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)){
                       for (int i = 0;i<rand;i++){
                           c.flash();
                           addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                           addToBot((AbstractGameAction)new ReduceCostAction(c));
                       }
                   }else if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)){
                       for (int i = 0;i<rand;i++){
                           c.flash();
                           c.magicNumber = ++c.baseMagicNumber;
                           addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                       }
                   }
               }
           }
       }

       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ExhaustPower((AbstractCreature)abstractPlayer, cardGroup)));
     this.isDone = true;
   }
 }

