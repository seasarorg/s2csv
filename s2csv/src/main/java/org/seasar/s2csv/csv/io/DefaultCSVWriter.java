package org.seasar.s2csv.csv.io;

import java.io.IOException;
import java.io.Writer;

import org.seasar.s2csv.csv.desc.CSVColumnDesc;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVCloseException;
import org.seasar.s2csv.csv.exception.runtime.CSVWriteException;

/**
 * S2CSV標準の書き出しを行います
 * @author newta
 */
public class DefaultCSVWriter implements CSVWriter {

	private Writer writer;
	private CSVEntityDesc csvDesc;
	
	/**
	 * DefaultCSVWriterを生成します
	 * @param writer
	 * @param csvConf
	 */
	public DefaultCSVWriter(Writer writer, CSVEntityDesc csvConf){
		this.writer = writer;
		this.csvDesc = csvConf;
		
		if(csvConf.isHeader()){
			//ヘッダありの場合、ここで書き出す
			putHeader(csvConf.getHeaderNames());
		}
	}
	
	/**
	 * ヘッダ行を書き出します
	 * @param csvColumns
	 */
	protected void putHeader(String[] csvColumns){
		writeLine(csvColumns);
	}

	public void writeLine(String[] csvColumns) {
		
		if(csvColumns != null && csvColumns.length != 0){
			
			try {
				for(int i=0;i<csvColumns.length;i++){
					String col = csvColumns[i];
					CSVColumnDesc colDesc = csvDesc.getColumnConfigs().get(i);
					
					if(i != 0){
						writer.write(csvDesc.getDemiliter());
					}
					
					if(col == null || col.length() == 0){
						continue;
					}
					
					putCol(col, colDesc.quote());
				}

				writer.write("\r\n");
			} catch (IOException e) {
				throw new CSVWriteException(e);
			}
			
		}
	}
	
	/**
	 * カラムを書き出します。
	 * @param col
	 * @param quote
	 * @throws IOException
	 */
	protected void putCol(String col, boolean quote) throws IOException{
		
		if(col.indexOf("\"") >= 0){
			col = col.replace("\"","\"\"");
		}
		
		boolean need = isNeedQuote(col);

		if(quote || need){
			writer.write("\"");
		}
		writer.write(col);
		if(quote || need){
			writer.write("\"");
		}
	}
	
	protected boolean isNeedQuote(String col){
		
		if(col.indexOf("\"") >= 0){
			return true;
		}

		if(col.indexOf(csvDesc.getDemiliter()) >= 0){
			return true;
		}

		if(col.indexOf("\n") >= 0){
			return true;
		}
		
		if(col.indexOf("\r") >= 0){
			return true;
		}
		
		return false;
	}
	
	public void close() {
		try {
			this.writer.close();
		} catch (IOException e) {
			throw new CSVCloseException(e);
		}
	}

}
