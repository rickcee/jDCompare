package net.rickcee.jdcompare.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import net.rickcee.jdcompare.core.QueryComparator;
import net.rickcee.jdcompare.view.ColumnDataView;
import net.rickcee.jdcompare.view.ResultView;
import net.rickcee.jdcompare.view.RowDataView;

public class ExcelGenerator {

	/**
	 * @param finalResultView
	 * @return
	 * @throws IOException
	 */
	public static byte[] generate(ResultView finalResultView) throws IOException {
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet("jDCompare Report");

		/*
		 * Create Fonts
		 */
		Font defaultFont = workBook.createFont();
		defaultFont.setFontHeightInPoints((short) 10);

		Font boldFont = workBook.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		/*
		 * Create Styles
		 */
		CellStyle styleGreen = workBook.createCellStyle();
		styleGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		styleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleGreen.setFont(defaultFont);
		styleGreen.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle styleRed = workBook.createCellStyle();
		styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		styleRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRed.setFont(boldFont);
		styleRed.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle styleNormal = workBook.createCellStyle();
		styleNormal.setFont(defaultFont);

		CellStyle styleNormalBold = workBook.createCellStyle();
		styleNormalBold.setFont(boldFont);

		CellStyle stylePK = workBook.createCellStyle();
		stylePK.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		stylePK.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		stylePK.setAlignment(CellStyle.ALIGN_CENTER);
		stylePK.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		stylePK.setBorderBottom(CellStyle.BORDER_THIN);
		stylePK.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		stylePK.setBorderLeft(CellStyle.BORDER_THIN);
		stylePK.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		stylePK.setBorderRight(CellStyle.BORDER_THIN);
		stylePK.setRightBorderColor(IndexedColors.BLACK.getIndex());
		stylePK.setBorderTop(CellStyle.BORDER_THIN);
		stylePK.setTopBorderColor(IndexedColors.BLACK.getIndex());
		stylePK.setFont(boldFont);

		/*
		 * Start sheet generation
		 */
		int colNumber = 0; // Total # of columns to be auto adjusted
		int fistDataColumn = 0; // The column # where data (not PKs) start.
		int _row = 0;
		int _col = 0;

		Row excelRow = sheet.createRow(_row++);
		Row newExcelRow = sheet.createRow(_row++); // Issue with borders on
		// merged cells / rows
		Cell cell;

		// Header (PKs, 1st row)
		for (String pk : finalResultView.getMetadataPK()) {
			cell = excelRow.createCell(_col++);
			cell.setCellValue(pk);
			cell.setCellStyle(stylePK);

			cell = newExcelRow.createCell(_col - 1);
			cell.setCellValue("");
			cell.setCellStyle(stylePK);

			fistDataColumn++;

			sheet.addMergedRegion(new CellRangeAddress(_row - 2, _row - 1, _col - 1, _col - 1));
			newExcelRow.getCell(_col - 1).setCellStyle(stylePK);
		}

		// Header (Data, 1st row)
		for (String metaCol : finalResultView.getMetadata()) {
			cell = excelRow.createCell(_col++);
			cell.setCellValue(metaCol);
			cell.setCellStyle(stylePK);

			cell = excelRow.createCell(_col++);
			cell.setCellStyle(stylePK);

			cell = excelRow.createCell(_col++);
			cell.setCellStyle(stylePK);
			sheet.addMergedRegion(new CellRangeAddress(_row - 2, _row - 2, _col - 3, _col - 1));
		}

		// Header (Label, 2nd row)
		// excelRow = sheet.createRow(_row++);
		_col = fistDataColumn; // 1st col to start populating data
		for (int i = 0; i < finalResultView.getMetadata().size(); i++) {
			cell = newExcelRow.createCell(_col++);
			cell.setCellValue(finalResultView.getDbAlias1());
			cell.setCellStyle(stylePK);

			cell = newExcelRow.createCell(_col++);
			cell.setCellValue(finalResultView.getDbAlias2());
			cell.setCellStyle(stylePK);

			cell = newExcelRow.createCell(_col++);
			cell.setCellValue("Difference");
			cell.setCellStyle(stylePK);
		}

		Boolean firstTime = Boolean.TRUE;
		for (RowDataView row : finalResultView.getRows()) {
			excelRow = sheet.createRow(_row++);
			// Reset _col for each new row
			_col = 0;

			// Iterate and populate all PK fields
			for (ColumnDataView col : row.getPks()) {
				cell = excelRow.createCell(_col++);
				String value = col.getFieldValue1().toString();
				if (value == null || value.equals(QueryComparator.NA)) {
					cell.setCellValue(col.getFieldValue2().toString());
				} else {
					cell.setCellValue(value);
				}
			}

			// Iterate and populate all Data fields
			for (ColumnDataView col : row.getResult()) {
				cell = excelRow.createCell(_col++);
				cell.setCellValue(col.getFieldValue1().toString());

				cell = excelRow.createCell(_col++);
				cell.setCellValue(col.getFieldValue2().toString());

				cell = excelRow.createCell(_col++);
				cell.setCellValue(col.getDiff().toString());
				if (col.getIsOK()) {
					cell.setCellStyle(styleGreen);
				} else {
					cell.setCellStyle(styleRed);
				}
			}

			// Assign total # of columns (first iteration only)
			if (firstTime) {
				colNumber = row.getPks().size() + row.getResult().size() * 3;
				firstTime = Boolean.FALSE;
			}
		}

		// System.out.println("colNumber: " + colNumber);
		for (int i = 0; i <= colNumber; i++) {
			sheet.autoSizeColumn(i, true);
			// System.out.println("Resizing col: " + i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		workBook.write(baos);
		baos.close();

		return baos.toByteArray();
	}

}
