package mahjong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mahjong {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tile gameTile = new Tile();
		System.out.println("牌の入力規則");

		for(int i = 0; i < gameTile.getHai().length; i++) {
			if (i == 0) {
				System.out.print("萬子：");
			} else if (i == 1) {
				System.out.print("筒子：");
			} else if (i == 2) {
				System.out.print("索子：");
			}
			for(int j= 0; j < gameTile.getHai()[i].length; j++) {

				System.out.print(gameTile.getHai()[i][j]);
			}

			System.out.print("\n");
		}
		
		
		//プレイヤークラスのオブジェクト化
		System.out.println("\n");
		Player[] mahjong = new Player[]{new Player(), new Player(), new Player(), new Player()};

		//王牌のセットと表示
		gameTile.setWampai();
		System.out.println("王牌");
		for(int i = 0; i < gameTile.getWampai().length; i++) {
			System.out.print(gameTile.guessTile(gameTile.getWampai()[i]));
		}
		
		
		//配牌
		System.out.println("\n");
		System.out.println("配牌開始");
		for(int i = 0; i < mahjong.length; i++) {
			mahjong[i].setTiles(gameTile.matchingCards());
			mahjong[i].printTiles();
		}
		System.out.println("配牌完了");
		System.out.println("\n");


		for(int i = 0; i < mahjong.length; i++) {
			//ツモ
			int cardedTile = gameTile.card();
			mahjong[i].setTile(cardedTile, 0);
			mahjong[i].printTiles();

			//捨て牌入力
			System.out.println("どの牌を切りますか。入力規則に則って入力してください");
			String discardedTile = br.readLine();

			//捨て牌を文字列⇒数字に変換
			int discardedTileInt = mahjong[i].discardedTileParseInt(discardedTile);
			
			//手配から捨て牌を除き、捨て牌完了後の牌を表示する
			mahjong[i].discardedTiles(gameTile.guessKindNumber(discardedTileInt));
			mahjong[i].printTiles();

			int j = (i+1)%4; 
			do{
				//mahjong[i]の捨て牌について鳴く
				if (j != i) {
					//鳴けるか判定
					mahjong[j].setCalledChiUd(!mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[0].equals(""));
					mahjong[j].setCalledChiPn(!mahjong[j].chiCheckPen(gameTile.guessKindNumber(discardedTileInt))[0].equals(""));
					mahjong[j].setCalledChiUp(!mahjong[j].chiCheckUpper(gameTile.guessKindNumber(discardedTileInt))[0].equals(""));
					mahjong[j].setCalledPon(!mahjong[j].ponCheck(gameTile.guessKindNumber(discardedTileInt)).equals(""));
					mahjong[j].setCalledKan(!mahjong[j].kanCheck(gameTile.guessKindNumber(discardedTileInt)).equals(""));
					
					//鳴くか否か・鳴くなら鳴きの処理
					String calledChiUd = "";
					String calledChiPn = "";
					String calledChiUp = "";
					String calledPon = "";
					String calledKan = "";
					if (mahjong[j].isCalledChiUd()) {
						mahjong[j].printTiles();
						System.out.println(mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckUnder(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
						System.out.println("するなら捨てる牌を入力しろ/鳴かない場合：Enterキー押下");
						calledChiUd = br.readLine();
						if(!calledChiUd.equals("")) {
							System.out.println("チーなんだ㌔");
							mahjong[j].chiUnder(discardedTileInt, calledChiUd);
							mahjong[j].setCalledChiUd(false);
							mahjong[j].printTiles();
						}else {
							System.out.println("チーしない！");
						}
						i=j%4;
						break;
					}
					
					if (mahjong[j].isCalledChiPn()) {
						mahjong[j].printTiles();
						System.out.println(mahjong[j].chiCheckPen(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckPen(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
						System.out.println("するなら捨てる牌を入力しろ/鳴かない場合：Enterキー押下");
						calledChiPn = br.readLine();
						if(!calledChiPn.equals("")) {
							System.out.println("チーなんだ㌔");
							mahjong[j].chiPen(discardedTileInt, calledChiPn);
							mahjong[j].setCalledChiPn(false);
							mahjong[j].printTiles();
						}else {
							System.out.println("チーしない！");
						}
						i=j%4;
						break;
					}
					
					if (mahjong[j].isCalledChiUp()) {
						mahjong[j].printTiles();
						System.out.println(mahjong[j].chiCheckUpper(gameTile.guessKindNumber(discardedTileInt))[0] + " " + mahjong[j].chiCheckUpper(gameTile.guessKindNumber(discardedTileInt))[1] + "チーするんかぇ？");
						System.out.println("するなら捨てる牌を入力しろ/鳴かない場合：Enterキー押下");
						calledChiUp = br.readLine();
						if(!calledChiPn.equals("")) {
							System.out.println("チーなんだ㌔");
							mahjong[j].chiUpper(discardedTileInt, calledChiUp);
							mahjong[j].setCalledChiUp(false);
							mahjong[j].printTiles();
						}else {
							System.out.println("チーしない！");
						}
						i=j%4;
						break;
					}
					
					if (mahjong[j].isCalledPon()) {
						mahjong[j].printTiles();
						System.out.println(mahjong[j].ponCheck(gameTile.guessKindNumber(discardedTileInt)) + " ポンするんかぇ？");
						System.out.println("するなら捨てる牌を入力しろ/鳴かない場合：Enterキー押下");
						calledPon = br.readLine();
						if(!calledPon.equals("")) {
							System.out.println("ポンなんだ㌔");
							mahjong[j].pon(discardedTileInt, calledPon);
							mahjong[j].setCalledPon(false);
							mahjong[j].printTiles();
						}else {
							System.out.println("チーしない！");
						}
						i=j%4;
						break;
					}
					
					if (mahjong[j].isCalledKan()) {
						mahjong[j].printTiles();
						System.out.println(mahjong[j].kanCheck(gameTile.guessKindNumber(discardedTileInt)) + " カンするんかぇ？");
						System.out.println("するなら捨てる牌を入力しろ/鳴かない場合：Enterキー押下");
						calledKan = br.readLine();
						if(!calledKan.equals("")) {
							System.out.println("カンなんだ㌔!");
							mahjong[j].kan(discardedTileInt, calledKan);
							mahjong[j].setCalledKan(false);
							mahjong[j].printTiles();
						}else {
							System.out.println("チーしない！");
						}
						i=j%4;
						break;
					}
					
					
				}
				mahjong[j].setCalledChiUd(false);
				mahjong[j].setCalledChiPn(false);
				mahjong[j].setCalledChiUp(false);
				mahjong[j].setCalledPon(false);
				mahjong[j].setCalledKan(false);
				j = (j+1)%4;
			}while(j != (i+1)%4);
		}

	}
}

