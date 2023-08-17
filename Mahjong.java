import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mahjong {
   public Mahjong() {
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      Tile gameTile = new Tile();
      System.out.println("牌の入力規則");

      for(int i = 0; i < gameTile.getHai().length; ++i) {
         if (i == 0) {
             System.out.print("萬子：");
           } else if (i == 1) {
               System.out.print("筒子：");
           } else if (i == 2) {
               System.out.print("索子：");
           }
          for(int j= 0; j < gameTile.getHai()[i].length; ++j) {
                
                System.out.print(gameTile.getHai()[i][j]);
            }
            
            System.out.print("\n");
        }

        System.out.println("\n");
        Player[] mahjong = new Player[]{new Player(), new Player(), new Player(), new Player()};
        gameTile.setWampai();
        System.out.println("王牌");
        
      int i;
      for(i = 0; i < gameTile.getWampai().length; ++i) {
         System.out.println(gameTile.guessTile(gameTile.getWampai()[i]));
      }

      for(i = 0; i < mahjong.length; ++i) {
         mahjong[i].setTiles(gameTile.matchingCards());
         mahjong[i].printTiles();
      }

      System.out.println("配牌完了");
      System.out.println("\n");

      for(i = 0; i < mahjong.length; ++i) {
         int cardedTile = gameTile.card();
         mahjong[i].setTile(cardedTile, 0);
         mahjong[i].printTiles();
         System.out.println("どの牌を切りますか。入力規則に則って入力してください");
         String discardedTile = br.readLine();
         int discardedTileInt = -1;

         int j;
         for(j = 0; j < mahjong[i].getTiles().length; ++j) {
            if (gameTile.guessTile(mahjong[i].getTile(j)) == discardedTile) {
               discardedTileInt = mahjong[i].getTile(j);
               break;
            }
         }

         mahjong[i].discardedTiles(gameTile.guessKindNumber(discardedTileInt));
         mahjong[i].printTiles();

         for(j = 0; j < mahjong.length; ++j) {
            if (j != i) {
               mahjong[j].setCalledChiUd(-1 != mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[0]);
               mahjong[j].setCalledChiPn(-1 != mahjong[j].chiCheckPen(gameTile.guessKindNumber(discardedTileInt))[0]);
               mahjong[j].setCalledChiUp(-1 != mahjong[j].chiCheckUpper(gameTile.guessKindNumber(discardedTileInt))[0]);
               mahjong[j].setCalledPon(-1 != mahjong[j].ponCheck(gameTile.guessKindNumber(discardedTileInt)));
               mahjong[j].setCalledKan(-1 != mahjong[j].kanCheck(gameTile.guessKindNumber(discardedTileInt)));
            }

            String calledChiUd = "";
            String calledChiPn = "";
            String calledChiUp = "";
            String calledPon = "";
            String calledKan = "";
            if (mahjong[j].isCalledChiUd()) {
               System.out.println(mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
               calledChiUd = br.readLine();
            }

            if (mahjong[j].isCalledChiPn()) {
               System.out.println(mahjong[j].chiCheckPen(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
               calledChiPn = br.readLine();
            }

            if (mahjong[j].isCalledChiUp()) {
               System.out.println(mahjong[j].chiCheckUpper(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
               calledChiUp = br.readLine();
            }

            if (mahjong[j].isCalledPon()) {
               System.out.println(mahjong[j].ponCheck(gameTile.guessKindNumber(discardedTileInt)) + " ポンするんかぇ？");
               calledPon = br.readLine();
            }

            if (mahjong[j].isCalledKan()) {
               System.out.println(mahjong[j].kanCheck(gameTile.guessKindNumber(discardedTileInt)) + " カンするんかぇ？");
               calledKan = br.readLine();
            }

            if (calledChiUd.equals("y")) {
               System.out.println("チーなんだ㌔");
               mahjong[j].chiUnder(discardedTileInt);
            }

            if (calledChiPn.equals("y")) {
               System.out.println("チーなんだ㌔");
               mahjong[j].chiPen(discardedTileInt);
            }

            if (calledChiUp.equals("y")) {
               System.out.println("チーなんだ㌔");
               mahjong[j].chiUpper(discardedTileInt);
            }

            if (calledPon.equals("y")) {
               System.out.println("ポンなんだ㌔");
               mahjong[j].pon(discardedTileInt);
            }

            if (calledKan.equals("y")) {
               System.out.println("カンなんだ㌔!");
               mahjong[j].kan(discardedTileInt);
            }

            mahjong[j].setCalledChiUd(false);
            mahjong[j].setCalledChiPn(false);
            mahjong[j].setCalledChiUp(false);
            mahjong[j].setCalledPon(false);
            mahjong[j].setCalledKan(false);
         }
      }

   }
}

