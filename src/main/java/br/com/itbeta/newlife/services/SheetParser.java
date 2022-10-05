package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.utils.SheetRow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import java.util.stream.Stream;

public class SheetParser<T>{
    private class SheetToCSV implements SheetContentsHandler {
        private boolean firstCellOfRow;
        private int currentRow = -1;
        private int currentCol = -1;
        private String[] row = new String[SheetParser.this.minColumns];

        @Override
        public void startRow(int rowNum) {
            firstCellOfRow = true;
            currentRow = rowNum;
            currentCol = -1;
        }

        @Override
        public void endRow(int rowNum) {
            if(rowNum == 0){
                return;
            }
            SheetRow row = new SheetRow(this.row, this.currentRow);
            SheetParser.this.streamBuilder.accept(SheetParser.this.mapper.apply(row));
            this.row = new String[SheetParser.this.minColumns];
        }

        @Override
        public void cell(String cellReference, String formattedValue,
                         XSSFComment comment) {
            if(currentRow == 0){
                return;
            }
            if(cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }
            int thisCol = (new CellReference(cellReference)).getCol();
            int missedCols = thisCol - currentCol - 1;
            if (formattedValue == null) {
                return;
            }
            currentCol = thisCol;
            //Saída do dado
            this.row[currentCol] = formattedValue;
        }
    }

    private final OPCPackage xlsxPackage;
    private final int minColumns;
    private final Function<SheetRow,T> mapper;
    private final Stream.Builder<T> streamBuilder = Stream.builder();
    public SheetParser(OPCPackage pkg, int minColumns, Function<SheetRow,T> mapper) {
        this.xlsxPackage = pkg;
        this.minColumns = minColumns;

        this.mapper = mapper;
    }

    public void processSheet(
            Styles styles,
            SharedStrings strings,
            SheetContentsHandler sheetHandler,
            InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = new DataFormatter(true);
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = XMLHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch(ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }
    public Stream<T> process() throws IOException, OpenXML4JException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            try (InputStream stream = iter.next()) {
                String sheetName = iter.getSheetName();
                processSheet(styles, strings, new SheetToCSV(), stream);
            }
            ++index;
        }
        return this.streamBuilder.build();
    }
}