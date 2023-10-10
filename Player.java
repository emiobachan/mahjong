
import java.util.Objects;

public class Player {
   private int[] tiles = new int[14];
   private int[] discardedTiles = new int[18];
   private String wind;
   private int[][] furo = new int[4][4];
   private boolean calledChiUp;
   private boolean calledChiPn;
   private boolean calledChiUd;
   private boolean calledPon;
   private boolean calledKan;
   private boolean thirteenUniqueWonders;
   private boolean sevenPairs;
   private boolean otherTempai;

   public Player() {
      int i;
      for(i = 0; i < this.tiles.length; ++i) {
         this.tiles[i] = -1;
      }

      for(i = 0; i < this.discardedTiles.length; ++i) {
         this.discardedTiles[i] = -1;
      }

   }

   public boolean isCalledChiUp() {
      return this.calledChiUp;
   }

   public void setCalledChiUp(boolean calledChiUp) {
      this.calledChiUp = calledChiUp;
   }

   public boolean isCalledChiPn() {
      return this.calledChiPn;
   }

   public void setCalledChiPn(boolean calledChiPn) {
      this.calledChiPn = calledChiPn;
   }

   public boolean isCalledChiUd() {
      return this.calledChiUd;
   }

   public void setCalledChiUd(boolean calledChiUd) {
      this.calledChiUd = calledChiUd;
   }

   public boolean isCalledPon() {
      return this.calledPon;
   }

   public void setCalledPon(boolean calledPon) {
      this.calledPon = calledPon;
   }

   public boolean isCalledKan() {
      return this.calledKan;
   }

   public void setCalledKan(boolean calledKan) {
      this.calledKan = calledKan;
   }

   public int getTile(int a) {
      return this.tiles[a];
   }

   public int[] getTiles() {
      return this.tiles;
   }

   public void setTile(int tile, int a) {
      this.tiles[a] = tile;
   }

   public void setTiles(int[] tiles) {
      this.tiles = this.orderTiles(tiles);
   }

   public int[] getDiscardedTiles() {
      return this.discardedTiles;
   }

   public void setDiscardedTile(int tile) {
      int i = 0;
      if (i < this.discardedTiles.length && this.discardedTiles[i] == -1) {
         this.discardedTiles[i] = tile;
      }

   }

   public String getWind() {
      return this.wind;
   }

   public void setWind(String wind) {
      this.wind = wind;
   }

   private int[] orderTiles(int[] xs) {
      for(int i = 0; i < xs.length; ++i) {
         for(int j = i + 1; j < xs.length; ++j) {
            if (xs[i] > xs[j]) {
               int temp = xs[i];
               xs[i] = xs[j];
               xs[j] = temp;
            }
         }
      }

      return xs;
   }

   public void printTiles() {
      Tile printTile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         System.out.println(printTile.guessTile(this.tiles[i]));
      }

   }

   public void discardedTiles(int[] kindNumber) {
      Tile tile = new Tile();

      for(int j = 0; j < this.getTiles().length; ++j) {
         if (tile.guessKindNumber(this.getTile(j))[0] == kindNumber[0] && tile.guessKindNumber(this.getTile(j))[1] == kindNumber[1]) {
            this.setDiscardedTile(this.getTile(j));
            this.setTile(-1, j);
            break;
         }
      }

   }

   public int thirteenUniqueWondersCheck() {
      int discarded = -1;
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (tile.guessKindNumber(this.getTile(i))[0] == 0) {
            int var10000 = tile.guessKindNumber(this.getTile(i))[1];
         }
      }

      return discarded;
   }

   public int[] chiCheckUnder(int[] kindNumber) {
      int[] chiUnder = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] + 2) {
                  chiUnder[0] = tile.guessKindNumber(this.tiles[i])[1];
                  chiUnder[1] = tile.guessKindNumber(this.tiles[j])[1];
               }
            }
         }
      }

      return chiUnder;
   }

   public int[] chiCheckPen(int[] kindNumber) {
      int[] chiPen = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] - 1) {
                  chiPen[0] = tile.guessKindNumber(this.tiles[i])[1];
                  chiPen[1] = tile.guessKindNumber(this.tiles[j])[1];
               }
            }
         }
      }

      return chiPen;
   }

   public int[] chiCheckUpper(int[] kindNumber) {
      int[] chiUpper = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] == 0 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] - 2) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] - 1) {
                  chiUpper[0] = tile.guessKindNumber(this.tiles[i])[1];
                  chiUpper[1] = tile.guessKindNumber(this.tiles[j])[1];
               }
            }
         }
      }

      return chiUpper;
   }

   public int ponCheck(int[] kindNumber) {
      int pon = -1;
      int ponCountMZ = 0;
      int ponCountPZ = 0;
      int ponCountSZ = 0;
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] == 0 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++ponCountMZ;
            pon = i;
         } else if (kindNumber[0] == 1 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++ponCountPZ;
            pon = i;
         } else if (kindNumber[0] == 2 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++ponCountSZ;
            pon = i;
         }
      }

      if (ponCountMZ < 2 && ponCountPZ < 2 && ponCountSZ < 2) {
         return pon;
      } else {
         return this.tiles[pon];
      }
   }

   public int kanCheck(int[] kindNumber) {
      int kan = -1;
      int kanCountMZ = 0;
      int kanCountPZ = 0;
      int kanCountSZ = 0;
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] == 0 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++kanCountMZ;
            kan = i;
         } else if (kindNumber[0] == 1 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++kanCountPZ;
            kan = i;
         } else if (kindNumber[0] == 2 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1]) {
            ++kanCountSZ;
            kan = i;
         }
      }

      if (kanCountMZ < 3 && kanCountPZ < 3 && kanCountSZ < 3) {
         return kan;
      } else {
         return this.tiles[kan];
      }
   }

   public void chiUnder(int borrowedTile) {
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] - 2) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] - 1) {
                  for(int k = 0; k < this.furo.length; ++k) {
                     if (Objects.isNull(this.furo[k])) {
                        this.furo[k][0] = this.tiles[i];
                        this.furo[k][1] = this.tiles[j];
                        this.furo[k][2] = borrowedTile;
                        break;
                     }
                  }
               }
            }
         }
      }

   }

   public void chiPen(int borrowedTile) {
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] - 1) {
                  for(int k = 0; k < this.furo.length; ++k) {
                     if (Objects.isNull(this.furo[k])) {
                        this.furo[k][0] = this.tiles[j];
                        this.furo[k][1] = borrowedTile;
                        this.furo[k][2] = this.tiles[i];
                        break;
                     }
                  }
               }
            }
         }
      }

   }

   public void chiUpper(int borrowedTile) {
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] + 2) {
                  for(int k = 0; k < this.furo.length; ++k) {
                     if (Objects.isNull(this.furo[k])) {
                        this.furo[k][0] = borrowedTile;
                        this.furo[k][1] = this.tiles[i];
                        this.furo[k][2] = this.tiles[j];
                        break;
                     }
                  }
               }
            }
         }
      }

   }

   public void pon(int borrowedTile) {
   }

   public void kan(int borrowedTile) {
   }

   public boolean furitenn() {
      boolean furi = false;
      return furi;
   }
}
