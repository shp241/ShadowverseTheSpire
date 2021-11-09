 package charbosses;
 
 import charbosses.EasyInfoDisplayPanel;
 
 public class BossMechanicDisplayPanel
   extends EasyInfoDisplayPanel {
   public static String mechanicName = "";
   public static String mechanicDesc = "NORENDER";
   
   private static int X = 1550;
   private static int Y = 550;
   private static int WIDTH = 275;
   
   public BossMechanicDisplayPanel() {
     super(X, Y, WIDTH);
   }
 
   
   public String getTitle() {
     return mechanicName;
   }
 
   
   public String getDescription() {
     return mechanicDesc;
   }
   
   public static void resetBossPanel() {
     mechanicDesc = "NORENDER";
   }
 
   
   public EasyInfoDisplayPanel.RENDER_TIMING getTiming() {
     return EasyInfoDisplayPanel.RENDER_TIMING.TIMING_RENDERSUBSCRIBER;
   }
 }

