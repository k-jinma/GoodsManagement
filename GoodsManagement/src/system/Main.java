package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import goods.License;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<License> list = new ArrayList<>();
		
		// 書き込む3つのクラス　File, FileWriter, BufferedWriter
		// 読み込む3つのクラス　File, FileReader, BufferedReader
		File file = new File("data.csv");
		FileWriter fw;
		BufferedWriter bw;
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				
				License a = new License(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10] );
				list.add(a);

			}
			
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		

		Scanner input = new Scanner(System.in); //System.inは標準入力(キーボード)を表す


		License zoom1;

		int no; // メニュー番号

		while (true) {
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
			try {
				no = input.nextInt();
				input.nextLine();
				
			} catch (Exception e) {
				input.nextLine();
				System.out.println("入力が間違っています");
				continue;
			}

			if (no == 1) {
				//貸出物品表示

				System.out.println("物品一覧");
				System.out.printf("%-4s %-4s %-4s %-14s %-8s %-10s %-8s %-10s %-8s %-4s%n",
						"管理番号", "サービス名", "ID", "メールアドレス", "開始日", "開始時間", "終了日", "終了時間", "使用者", "目的");
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%-8s %-8s %-4s %-14s %-8s %-10s %-8s %-10s %-8s %-4s%n",
							list.get(i).getNo(),
							list.get(i).getServiceName(),
							list.get(i).getId(),
							list.get(i).getMailAddress(),
							list.get(i).getStartDate(),
							list.get(i).getStartTime(),
							list.get(i).getEndDate(),
							list.get(i).getEndTime(),
							list.get(i).getUserName(),
							list.get(i).getPurpose());
				}

			} else if (no == 2) {
				boolean flag = false;
				//貸出
				for (License item : list) {
					if (item.getStartDate().equals(" ")) {
						System.out.println(item.getNo() + "：サービス名：" + item.getServiceName());
						flag = true;
					}
				}
				if (!flag) {
					System.out.println("貸出可能な物品はありません");
					continue;
				}
				System.out.print("貸出する物品番号を入力してください：");
				no = input.nextInt();
				input.nextLine();

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getNo() == no && list.get(i).getStartDate().equals(" ")) {
						// 貸出処理
						
						// 12/08 何月何日に返すか入力させる
						System.out.print("返却日を入力してください(yyyy/mm/dd)：");
						String date = input.nextLine();
						list.get(i).setEndDate(date);
						
						Pattern pattern = Pattern.compile("^[0-9]{4}/[0-9]{2}/[0-9]{2}$");
						Matcher matcher = pattern.matcher(date);
						if (!matcher.find()) {
							System.out.println("日付の形式が正しくありません");
							continue;
						}
						
						// 12/08 何時に返すか入力させる
						System.out.print("返却時間を入力してください(hh:mm)：");
						String time = input.nextLine();
						list.get(i).setEndTime(time);
						
						Pattern pattern2 = Pattern.compile("^[0-9]{2}:[0-9]{2}$");
						Matcher matcher2 = pattern2.matcher(time);
						if (!matcher2.find()) {
							System.out.println("時間の形式が正しくありません");
							continue;
						}

						// 12/08 貸出者の名前を入力させる
						System.out.print("利用者名を入力してください：");
						String userName = input.nextLine();
						list.get(i).setUserName(userName);
						
						// 12/08 利用目的を入力させる
						System.out.print("利用目的を入力してください：");
						String purpose = input.nextLine();
						list.get(i).setPurpose(purpose);
						
						// 現在の日時を取得する
						LocalDateTime now = LocalDateTime.now();

						list.get(i).setStartDate(now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth());
						list.get(i).setStartTime(now.getHour() + ":" + now.getMinute());

						// 12/08 パスワードを表示する
						System.out.println("パスワードは" + list.get(i).getPassword() + "です");
						
						break;
					}
				}

			} else if (no == 3) {
				//返却
				for (License item : list) {
					if (!item.getStartDate().equals("")) {
						System.out.println(item.getNo() + "：サービス名：" + item.getServiceName());
					}
				}

				System.out.print("返却する物品番号を入力してください：");
				no = input.nextInt();
				input.nextLine();
				
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getNo() == no && !list.get(i).getStartDate().equals("")) {
						// 返却処理
						list.get(i).setStartDate(" ");
						list.get(i).setStartTime(" ");
						list.get(i).setEndDate(" ");
						list.get(i).setEndTime(" ");
						list.get(i).setUserName(" ");
						list.get(i).setPurpose(" ");
						
						break;
					}
				}
				
			} else if (no == 4) {
				//物品登録

				System.out.print("サービス名を入力してください：");
				String str = input.nextLine();

				System.out.print("IDを入力してください：");
				String id = input.nextLine();

				System.out.print("パスワードを入力してください：");
				String password = input.nextLine();

				System.out.print("メールアドレスを入力してください：");
				String mailaddress = input.nextLine();


				zoom1 = new License(str, id, mailaddress, password);

				list.add(zoom1);

			} else if (no == 5) {
				//物品修正
				
				// 物品一覧を表示
				for (int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i).getNo());
					System.out.print("　");
					System.out.print(list.get(i).getServiceName());
					System.out.print("　");
					System.out.print(list.get(i).getId());
					System.out.print("　");
					System.out.print(list.get(i).getMailAddress());
					System.out.print("　");
					System.out.print(list.get(i).getStartDate());
					System.out.print("　");
					System.out.print(list.get(i).getStartTime());
					System.out.print("　");
					System.out.print(list.get(i).getEndDate());
					System.out.print("　");
					System.out.print(list.get(i).getEndTime());
					System.out.print("　");
					System.out.print(list.get(i).getUserName());
					System.out.print("　");
					System.out.print(list.get(i).getPurpose());
					
					System.out.println(); // 改行
				}
				
				// 修正する物品番号を入力
				System.out.print("修正する物品番号を入力してください：");
				no = input.nextInt();
				input.nextLine();
				
				// 修正項目を表示
				System.out.println("1: サービス名：" + list.get(no-1).getServiceName());
				System.out.println("2: ID：" + list.get(no-1).getId());
				System.out.println("3: パスワード：" + list.get(no-1).getPassword());
				System.out.println("4: メールアドレス：" + list.get(no-1).getMailAddress());
				System.out.println("5: 開始日：" + list.get(no-1).getStartDate());
				System.out.println("6: 開始時間：" + list.get(no-1).getStartTime());
				System.out.println("7: 終了日：" + list.get(no-1).getEndDate());
				System.out.println("8: 終了時間：" + list.get(no-1).getEndTime());
				System.out.println("9: 利用者名：" + list.get(no-1).getUserName());
				System.out.println("10: 利用目的：" + list.get(no-1).getPurpose());
				
				// 修正する項目番号を選択
				System.out.print("修正する番号を入力してください：");
				int no2 = input.nextInt();
				input.nextLine();
				
				
				// 項目番号の入力によって処理する
				switch(no2) {
				
					case 1:
						System.out.print("サービス名を入力してください：");
						String str = input.nextLine();
						list.get(no2 - 1).setServiceName(str);
						break;
					case 2:
						System.out.print("IDを入力してください：");
						String id = input.nextLine();
						list.get(no2 - 1).setId(id);
						break;
					case 3:
						System.out.print("パスワードを入力してください：");
						String password = input.nextLine();
						list.get(no2 - 1).setPassword(password);
						break;
					case 4:
						System.out.print("メールアドレスを入力してください：");
						String mailaddress = input.nextLine();
						list.get(no2 - 1).setMailAddress(mailaddress);
						break;
					case 5:
						System.out.print("開始日を入力してください：");
						String startDate = input.nextLine();
						list.get(no2 - 1).setStartDate(startDate);
						break;
					case 6:
						System.out.print("開始時間を入力してください：");
						String startTime = input.nextLine();
						list.get(no2 - 1).setStartTime(startTime);
						break;
					case 7:
						System.out.print("終了日を入力してください：");
						String endDate = input.nextLine();
						list.get(no2 - 1).setEndDate(endDate);
						break;
					case 8:
						System.out.print("終了時間を入力してください：");
						String endTime = input.nextLine();
						list.get(no2 - 1).setEndTime(endTime);
						break;
					case 9:
						System.out.print("利用者名を入力してください：");
						String userName = input.nextLine();
						list.get(no2 - 1).setUserName(userName);
						break;
					case 10:
						System.out.print("利用目的を入力してください：");
						String purpose = input.nextLine();
						list.get(no2 - 1).setPurpose(purpose);
						break;
						
					default:
						System.out.println("入力が間違っています");
						break;
				
				}
				
				// 修正が完了したことを表示する
				System.out.println("修正が完了しました");
				
				// 修正後の物品一覧を表示
				System.out.print(list.get(no2 - 1).getNo());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getServiceName());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getId());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getPassword());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getMailAddress());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getStartDate());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getStartTime());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getEndDate());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getEndTime());
				System.out.print("　");
				System.out.print(list.get(no2 - 1).getUserName());
				System.out.print("　");
				System.out.println(list.get(no2 - 1).getPurpose());
				
//				int result = switch(no) {
//					case 1 -> {
//						System.out.print("サービス名を入力してください：");
//						String str = input.nextLine();
//						list.get(no - 1).setServiceName(str);
//						yield 1;
//					}
//					case 2 -> {
//						System.out.print("IDを入力してください：");
//						String id = input.nextLine();
//						list.get(no - 1).setId(id);
//						yield 1;
//					}
//					default -> {
//						System.out.println("入力が間違っています");
//                        yield -1;
//					}
//				
//				};
				
				

			} else if (no == 6) {
				//物品削除

			} else if (no == 9) {
				//終了
				try {
					fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					
					String csv = "";
					for (int i = 0; i < list.size(); i++) {
						csv += list.get(i).getNo() + ","
								+ list.get(i).getServiceName() + ","
								+ list.get(i).getId() + ","
								+ list.get(i).getPassword() + ","
								+ list.get(i).getMailAddress() + ","
								+ list.get(i).getStartDate() + ","
								+ list.get(i).getStartTime() + ","
								+ list.get(i).getEndDate() + ","
								+ list.get(i).getEndTime() + ","
								+ list.get(i).getUserName() + ","
								+ list.get(i).getPurpose() + "\n"; // \n 改行
					}
					
					System.out.println(csv);
					bw.write(csv);
					
					bw.flush();
					
					
					
					
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				

				
				input.close();
				break;

			} else {
				System.out.println("入力が間違っています");

			}

		}

	}

}

/*
	
	③入力にはScannerクラスを使う。サイトを参考にして入力できるようにしてください。
	URLはあとで連絡します
	

*/
