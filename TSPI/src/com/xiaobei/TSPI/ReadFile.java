package com.xiaobei.TSPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadFile {
	public ReadFile() {
	}

	public double[] readSpecify(File file,int co) {
		ArrayList<String> columnList = new ArrayList<String>();
		Workbook readwb = null;
		InputStream io;
		try {
			io = new FileInputStream(file.getAbsoluteFile());
			readwb = Workbook.getWorkbook(io);
			Sheet readsheet = readwb.getSheet(0);
			int rsRows = readsheet.getRows();
			for (int i = 1; i < rsRows; i++) {
				Cell cell = readsheet.getCell(co, i);
				columnList.add(cell.getContents());
			}
			String[] ageString = new String[columnList.size()];
			double[] col = new double[ageString.length];
			for (int i = 0; i < columnList.size(); i++) {
				ageString[i] = columnList.get(i);
				col[i] = Double.parseDouble(ageString[i]);
			}
			return col;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
