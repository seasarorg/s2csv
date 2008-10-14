package org.seasar.s2csv.csv.io;

/**
 * CSVのパース処理を行う
 * @author newta
 */
public interface CSVParser {
	
	/**
	 * ヘッダー行
	 * @return ヘッダー行データ 
	 */
	public String[] getHeader();
	
	/**
	 * 次の行があるかチェックする
	 * @return true 有り
	 */
	public boolean isNext();
	
	/**
	 * CSVデータから１行のデータを取り出す。
	 * CSVのシーケンスを1進める
	 * CSVデータがなくなった場合 null
	 * @return １行データ
	 */
	public String[] nextLine();

	/**
	 * 現在の行データを再度取得します。
	 * (nextCSVLineで読み出した行を取得)
	 * @return １行データ
	 */
	public String[] getCurrentLine();
	
	/**
	 * 現在の行番号を取得します
	 * (カウントは０から開始（例：1行目は0））
	 * @return 行番号
	 */
	public long getCurrentLineNo();
	
	/**
	 * クローズ処理を行います。
	 */
	public void close();
}
