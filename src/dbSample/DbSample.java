package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSample {
	public static void main(String[] args) {

		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// データベースへ接続
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.132:1521:xe", "stockuser", "moriara0029");

			// select文を準備
			String sql = "select id,name,age from employee";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// selectを実行し、結果票を取得
			ResultSet rs = pstmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			while (rs.next()) {

				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");

				// 取得したデータを出力
				System.out.println("ID" + id);
				System.out.println("名前" + name);
				System.out.println("年齢" + age);

			}

		} catch (SQLException e) {

			e.printStackTrace();// 接続やSQL処理の失敗時の処理

		} catch (ClassNotFoundException e) {

			e.printStackTrace();// JDBCドライバが見つからない時の処理
		} finally {

			// データベース切断
			if (conn != null) {

				try {

					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
	}
}
