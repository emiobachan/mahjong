import java.util.Random;

public class Tile {
   private String[] man = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九"};
   private String[] pin = new String[]{"①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨"};
   private String[] sou = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
   private String[] jihai = new String[]{"東", "南", "西", "北", "白", "發", "中"};
   private String[][] hai;
   private int[] pastTile;
   private int[] wampai;

   public Tile() {
      this.hai = new String[][]{this.man, this.pin, this.sou, this.jihai};
      this.pastTile = new int[136];
      this.wampai = new int[14];
   }

   public String[][] getHai() {
      return this.hai;
   }

   public void getPastTiles() {
      for(int i = 0; i < this.pastTile.length; ++i) {
         System.out.println(this.pastTile[i]);
      }

   }

   public int getPastTile(int a) {
      return this.pastTile[a];
   }

   public String guessTile(int x) {
      String kind = "";
      String number = "";
      if (x == -1) {
         return "";
      } else {
         if (x / 36 == 0) {
            number = this.man[(x + 1) % 9];
         } else if (x / 36 == 1) {
            number = this.pin[(x + 5) % 9];
         } else if (x / 36 == 2) {
            number = this.sou[(x + 9) % 9];
         } else {
            kind = this.jihai[(x + 4) % 7];
         }

         return number + kind;
      }
   }

   public int[] guessKindNumber(int tile) {
      int kind = tile / 36;
      int number;
      if (kind == 0) {
         number = (tile + 1) % 9;
      } else if (kind == 1) {
         number = (tile + 5) % 9;
      } else if (kind == 2) {
         number = (tile + 9) % 9;
      } else {
         number = (tile + 4) % 7;
      }

      int[] kindNumber = new int[]{kind, number};
      return kindNumber;
   }

   public int[] matchingCards() {
      Random matchingTile = new Random();
      int[] cards = new int[14];
      cards[13] = -1;

      for(int i = 0; i < 13; ++i) {
         boolean tileCheck = false;

         int temporary;
         do {
            temporary = matchingTile.nextInt(136);
            if (this.pastTile[temporary] == -1) {
               tileCheck = true;
            } else {
               tileCheck = false;
            }
         } while(tileCheck);

         cards[i] = temporary;
         this.pastTile[temporary] = -1;
      }

      return cards;
   }

   public void setWampai() {
      Random matchingTile = new Random();

      for(int i = 0; i < 14; ++i) {
         boolean tileCheck = false;

         int temporary;
         do {
            temporary = matchingTile.nextInt(136);
            if (this.pastTile[temporary] == -1) {
               tileCheck = true;
            } else {
               tileCheck = false;
            }
         } while(tileCheck);

         this.wampai[i] = temporary;
         this.pastTile[temporary] = -1;
      }

   }

   public int[] getWampai() {
      return this.wampai;
   }

   public int card() {
      Random tumoTile = new Random();
      boolean tileCheck = false;

      int card;
      do {
         card = tumoTile.nextInt(136);
         if (this.pastTile[card] == -1) {
            tileCheck = true;
         } else {
            tileCheck = false;
         }
      } while(tileCheck);

      this.pastTile[card] = -1;
      return card;
   }
}

