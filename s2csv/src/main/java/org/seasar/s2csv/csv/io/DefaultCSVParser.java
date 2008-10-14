package org.seasar.s2csv.csv.io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.csv.exception.runtime.CSVCloseException;
import org.seasar.s2csv.csv.exception.runtime.CSVReadException;

/**
 * S2CSVを読み込みます。
 * @author newta
 */
public class DefaultCSVParser implements CSVParser {

	private static char QUOTE = '"';
	
	private LineNumberReader reader;
	
	private String[] currentLine;
	private String[] nextLine;
	private boolean nextRead;
	private long lineNo;
	
	private CSVEntityDesc csvConf;

	//parseメソッド内でしか使用しないが、
	//newを減らすためにキープしてしまう。
	//csvなら大体１行サイズが同じくらいと思うのでバッファサイズもいい感じになるはず？
	private StringBuilder sb = null;
	
	/**
	 * DefaultCSVParserを生成します。
	 * @param reader
	 * @param csvConf
	 */
	public DefaultCSVParser(Reader reader, CSVEntityDesc csvConf){
		
		this.reader =  new LineNumberReader(reader);
		this.csvConf = csvConf;
		
		this.sb = new StringBuilder();

		if(csvConf.isHeader()){
			//headerありの場合一行ここで読んでしまう。
			nextLine();
		}
	}
	
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new CSVCloseException(e);
		}
	}

	public String[] getCurrentLine() {
		
		return this.currentLine;
	}

	public long getCurrentLineNo() {
		
		if(nextRead){
			return lineNo - 1;
		}
		
		return lineNo;
	}

	public String[] getHeader() {
		return csvConf.getHeaderNames();
	}

	public boolean isNext() {
		
		if(!nextRead){
			this.nextLine = nextCSVLine();
		}

		this.nextRead = true;
		return this.nextLine != null;
	}

	public String[] nextLine() {
		if(!nextRead){
			this.currentLine = nextCSVLine();
		}
		
		this.nextRead = false;
		this.currentLine = this.nextLine;
		this.nextLine = null;
		
		return this.currentLine;
	}
	
	/**
	 * 次の行をreaderから読み出す。
	 * @return 次の行をパースしたもの 行が無かった場合null
	 */
	public String[] nextCSVLine() {
		
		String line;
		try {
			do{
				line = reader.readLine();
				lineNo = reader.getLineNumber();
				
				if(line == null){
					return null;
				}
			}while(line.length() == 0);

			String[] cols = parse(line, csvConf.getColmunSize(), lineNo);
			return cols;
			
		} catch (IOException e) {
			throw new CSVReadException(e);
		}
	}

	private String[] parse(String line, int size,long lineNo) throws IOException {

		sb.delete(0, sb.length());
		
		String[] cols = new String[size];
		
		boolean quoteState = false;//false:nomal true:quote内
		int p = 0;
		
		int colIndex = 0;
		
		int lineSize = line.length();
		
		while(true){
			
			//行の最後処理
			if(lineSize == p){
				if(!quoteState){
					cols[colIndex] = sb.toString();
					break;
				}else{
					sb.append("\n");
					line = reader.readLine();
					if( line == null ) {
						throw new CSVFormatException(lineNo);
					}
					
					lineNo = reader.getLineNumber();
					p=0;
					lineSize = line.length();
					
					if(lineSize == 0){
						continue;
					}
				}
			}
			
			final char c = line.charAt(p);
			p++;
			
			if(!quoteState){
				if( c == ',' ) {
					cols[colIndex] = sb.toString();
					sb.delete(0, sb.length());
					
					if(colIndex == cols.length -1){
						break;
					}
					colIndex++;
					continue;
				}else{
					if(c == QUOTE) {
						if(sb.length() == 0) {
							quoteState = true;
							continue;
						}else{
							//次の文字もquoteならエスケープ
							if(line.length()>p && line.charAt(p) == QUOTE) {
								sb.append(c);
								p++;
							}else{
								quoteState = true;
							}
							continue;
						}
					}
					
					sb.append(c);
				}
			}else{
				if(c == QUOTE){
					//次の文字もquoteならエスケープ
					if(line.length()>p && line.charAt(p) == QUOTE) {
						sb.append(c);
						p++;
						continue;
					}else{
						quoteState = false;
						continue;
					}
				}
				
				sb.append(c);
			}	
		}
		
		return cols;
	}

}
