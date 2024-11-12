package system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import goods.License;

public class Main {

	public static void main(String[] args) {		
		
		Scanner input = new Scanner(System.in); //System.inは標準入力(キーボード)を表す
		
	    ArrayList<License> list = new ArrayList<>();
		License zoom1;
		
		int no; // メニュー番号
		
		while( true ) {
			System.out.println("------------------");
			System.out.println("１．貸出物品表示");
			System.out.println("２．貸出");
			System.out.println("３．返却");
			System.out.println("４．物品登録");
			System.out.println("５．物品修正");
			System.out.println("６．物品削除");
			System.out.println("９．終了");
			System.out.println("------------------");
			System.out.print("入力：");
			no = input.nextInt();
			input.nextLine(); //改行を読み飛ばす
			
			if(no == 1) {
	            //貸出物品表示
				
				
				
				
				
			}else if(no == 2) {
	            //貸出
				int index = 1;
				for(License item : list) {
					if( item.getStartDate().equals("") ){
						System.out.println( index + "：サービス名：" + item.getServiceName());
						index++;
					}
				}
				System.out.print("貸出する物品番号を入力してください：");
				no = input.nextInt();
				input.nextLine();
				
				// 現在の日時を取得する
				LocalDateTime now = LocalDateTime.now();
				System.out.println( now.getYear()+ "/" + now.getMonthValue() +"/" + now.getDayOfMonth() );
				System.out.println( now.getHour() + ":" + now.getMinute() );
				
				list.get(no-1).setStartDate( now.getYear()+ "/" + now.getMonthValue() +"/" + now.getDayOfMonth() );
				list.get(no-1).setStartTime( now.getHour() + ":" + now.getMinute() );
				
			}else if(no == 3) {
	            //返却
				
			}else if(no == 4) {
	            //物品登録
				
				//①時刻を追加する
				zoom1 = new License("Zoom1","1234","xyz","aaa@bbb.com");
				//zoom1.setStartDate("20210101");
				list.add(zoom1);
				
				zoom1 = new License("Zoom2","5678","xyz","aaa@bbb.com");
				list.add(zoom1);
				
			}else if(no == 5) {
	            //物品修正
				
			}else if(no == 6) {
	            //物品削除
				
			}else if(no == 9) {
	            //終了
				input.close();
				break;
				
			}else {
				System.out.println("入力が間違っています");

			}

		}
		

	}

}

/*
	
	③入力にはScannerクラスを使う。サイトを参考にして入力できるようにしてください。
	URLはあとで連絡します
	

*/
