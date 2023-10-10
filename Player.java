package mahjong;

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
      for(int i = 0; i < this.tiles.length; ++i) {
         this.tiles[i] = -1;
      }

      for(int i = 0; i < this.discardedTiles.length; ++i) {
         this.discardedTiles[i] = -1;
      }

      for(int i = 0; i < this.furo.length; ++i) {
    	  for(int j=0;j<this.furo[i].length;j++) {
    		  this.furo[i][j] = -1;
    	  }
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
	   Tile tile = new Tile();
      this.tiles = tile.orderTiles(tiles);
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

   

   public void printTiles() {
      Tile printTile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
    	  //副露配列に値が入っている時、表示を変える
    	  //手配配列の中の副露配列と一致する牌は一致しない牌を並べた後に牌を並べる
		 if(!furo[0].equals(null)) {
			 
		 }
         System.out.print(printTile.guessTile(this.tiles[i]));
      }
      System.out.print("\n");

   }

   public int discardedTileParseInt(String tile) {
	   int discardedTileInt = -1;
	   Tile tiletile = new Tile();
	   for(int j = 0; j < this.getTiles().length; j++) {
			if (tiletile.guessTile(this.getTile(j)).equals(tile)) {
				discardedTileInt = this.getTile(j);
				break;
			}
		}
	   return discardedTileInt;
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
            discarded = tile.guessKindNumber(this.getTile(i))[1];
         }
      }

      return discarded;
   }

   //他家が4捨てた時に手配の56で鳴く
   public String[] chiCheckUnder(int[] kindNumber) {
	  String[] ansChiUnder = new String[] {"",""};
	  //
      int[] chiUnder = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] + 2) {
                  ansChiUnder[0] = tile.guessTile(chiUnder[0] = this.tiles[i]);
                  ansChiUnder[1] = tile.guessTile(chiUnder[1] = this.tiles[j]);
               }
            }
         }
      }

      return ansChiUnder;
   }

   public String[] chiCheckPen(int[] kindNumber) {
	   String[] ansChiPen = new String[] {"",""};
      int[] chiPen = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] - 1) {
            	   ansChiPen[0] = tile.guessTile(chiPen[0] = this.tiles[i]);
            	   ansChiPen[1] = tile.guessTile(chiPen[1] = this.tiles[j]);
               }
            }
         }
      }

      return ansChiPen;
   }

   public String[] chiCheckUpper(int[] kindNumber) {
	  String[] ansChiUpper = new String[] {"",""};
      int[] chiUpper = new int[]{-1, -1};
      Tile tile = new Tile();

      for(int i = 0; i < this.tiles.length; ++i) {
         if (kindNumber[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[i])[1] == kindNumber[1] - 2) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == kindNumber[0] && tile.guessKindNumber(this.tiles[j])[1] == kindNumber[1] - 1) {
            	   ansChiUpper[0] = tile.guessTile(chiUpper[0] = this.tiles[i]);
            	   ansChiUpper[1] = tile.guessTile(chiUpper[1] = this.tiles[j]);
               }
            }
         }
      }

      return ansChiUpper;
   }

   public String ponCheck(int[] kindNumber) {
	   String ans = "";
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
         return ans;
      } else {
    	  ans = tile.guessTile(this.tiles[pon]);
         return ans;
      }
   }

   public String kanCheck(int[] kindNumber) {
	   String ans = "";
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
         return ans;
      } else {
    	  ans = tile.guessTile(this.tiles[kan]);
         return ans;
      }
   }

   public void chiUnder(int borrowedTile, String discardedTile) {
      Tile tile = new Tile();
      int x = this.discardedTileParseInt(discardedTile);
      int subscriptX = -1;

      for(int i = 0; i < this.tiles.length; ++i) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] + 1) {
            for(int j = 0; j < this.tiles.length; ++j) {
               if (tile.guessKindNumber(this.tiles[j])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[j])[1] == tile.guessKindNumber(borrowedTile)[1] + 2) {
            	   for(int k = 0; j < this.getTiles().length; k++) {
            	         if (tile.guessKindNumber(this.getTile(k))[0] == tile.guessKindNumber(x)[0] && tile.guessKindNumber(this.getTile(k))[1] == tile.guessKindNumber(x)[1]) {
            	            this.setDiscardedTile(this.getTile(k));
            	            this.setTile(-1, k);
            	            subscriptX = k;
            	            break;
            	         }
            	      }
            	   for(int k = 0; k < this.furo.length; k++) {
                     if (this.furo[k][0]==-1) {
                        this.furo[k][0] = this.tiles[i];
                        this.furo[k][1] = this.tiles[j];
                        this.furo[k][2] = borrowedTile;
                        break;
                     }
                     this.setTile(borrowedTile, subscriptX);
                  }
               }
            }
         }
      }

   }

   public void chiPen(int borrowedTile, String discardedTile) {
      Tile tile = new Tile();
      int x = this.discardedTileParseInt(discardedTile);
      int subscriptX = -1;

      //手配の中からフウロする牌を探してフウロ配列に格納、
      for(int i = 0; i < this.tiles.length; i++) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] + 1) {
            for(int j = 0; j < this.tiles.length; j++) {
               if (tile.guessKindNumber(this.tiles[j])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[j])[1] == tile.guessKindNumber(borrowedTile)[1] - 1) {
            	   for(int k = 0; j < this.getTiles().length; k++) {
          	         if (tile.guessKindNumber(this.getTile(k))[0] == tile.guessKindNumber(x)[0] && tile.guessKindNumber(this.getTile(k))[1] == tile.guessKindNumber(x)[1]) {
          	            this.setDiscardedTile(this.getTile(k));
          	            this.setTile(-1, k);
          	            subscriptX = k;
          	            break;
          	         }
          	      }
            	   for(int k = 0; k < this.furo.length; ++k) {
                     if (this.furo[k][0]==-1) {
                        this.furo[k][0] = this.tiles[j];
                        this.furo[k][1] = borrowedTile;
                        this.furo[k][2] = this.tiles[i];
                        this.setTile(borrowedTile, subscriptX);
                        break;
                     }
                  }
               }
            }
         }
      }

   }

   public void chiUpper(int borrowedTile, String discardedTile) {
      Tile tile = new Tile();
      int x = this.discardedTileParseInt(discardedTile);
      int subscriptX = -1;

      for(int i = 0; i < this.tiles.length; i++) {
         if (tile.guessKindNumber(borrowedTile)[0] != 3 && tile.guessKindNumber(this.tiles[i])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[i])[1] == tile.guessKindNumber(borrowedTile)[1] - 1) {
            for(int j = 0; j < this.tiles.length; j++) {
               if (tile.guessKindNumber(this.tiles[j])[0] == tile.guessKindNumber(borrowedTile)[0] && tile.guessKindNumber(this.tiles[j])[1] == tile.guessKindNumber(borrowedTile)[1] - 2) {
            	   for(int k = 0; j < this.getTiles().length; k++) {
          	         if (tile.guessKindNumber(this.getTile(k))[0] == tile.guessKindNumber(x)[0] && tile.guessKindNumber(this.getTile(k))[1] == tile.guessKindNumber(x)[1]) {
          	            this.setDiscardedTile(this.getTile(k));
          	            this.setTile(-1, k);
          	            subscriptX = k;
          	            break;
          	         }
          	      }
            	   for(int k = 0; k < this.furo.length; ++k) {
                     if (this.furo[k][0]==-1) {
                        this.furo[k][0] = borrowedTile;
                        this.furo[k][1] = this.tiles[i];
                        this.furo[k][2] = this.tiles[j];
                        this.setTile(borrowedTile, subscriptX);
                        break;
                     }
                  }
               }
            }
         }
      }

   }

   public void pon(int borrowedTile, String discardedTile) {
	   int x = this.discardedTileParseInt(discardedTile);
	   
   }

   public void kan(int borrowedTile, String discardedTile) {
	   int x = this.discardedTileParseInt(discardedTile);
   }

   public boolean furitenn() {
      boolean furi = false;
      return furi;
   }
}
