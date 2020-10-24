package com.ark.resumecreater.util;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.exceptions.ResumeCreationException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <Singleton with utility methods for resume editing using apache poi.
 * It support editing of only Docx formats using apache POI XWPF library/>
 *
 * @author AbdulR
 */

public enum ResumeCreatorUtilDocx {
    INSTANCE;

    public XWPFDocument updateTemplateUsingMap(ResumeCreatorInput input) {
        //input should already be validating before reaching here
        Map<String, String> tokenReplacementMap = input.getTokenReplacementMap();
        InputStream docxResumeTemplate = input.getResumeTemplate();
        XWPFDocument modifiedDoc = null;
        try  {
            XWPFDocument xwpfDocument = new XWPFDocument(docxResumeTemplate);
            List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
            processParagraphs(xwpfDocument.getParagraphs(), tokenReplacementMap);
            List<XWPFTable> tables = xwpfDocument.getTables();
            processTables(tables, tokenReplacementMap);
            modifiedDoc = xwpfDocument;

        } catch (Exception ex) {
            ex.printStackTrace();//TODO - Implement Logger using Log4j2.x
            throw new ResumeCreationException("Error Reading template " + ex.getMessage());
        }
        return modifiedDoc;
    }
    //TODO lets this work first then will do refactoring to reduce cyclomatic complexity
    private void processTables(List<XWPFTable> tables, Map<String, String> tokenReplacementMap) {
        if (tables != null && !tables.isEmpty()) {
            for (XWPFTable table : tables) {
                List<XWPFTableRow> xwpfTableRows = table.getRows();
                if (xwpfTableRows != null && !xwpfTableRows.isEmpty()) {
                    for (XWPFTableRow row : xwpfTableRows) {
                        List<XWPFTableCell> xwpfTableCells = row.getTableCells();
                        if (xwpfTableCells != null && !xwpfTableCells.isEmpty()) {
                            for (XWPFTableCell cell : xwpfTableCells) {
                                processParagraphs(cell.getParagraphs(), tokenReplacementMap);
                            }
                        }
                    }
                }
            }
        }
    }


    //TODO lets this work first then will do refactoring to reduce cyclomatic complexity
    private void processParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> tokenReplacementMap) {
        if (paragraphs != null && !paragraphs.isEmpty()) {
            for (XWPFParagraph xwpfParagraph : paragraphs) {
                List<XWPFRun> runsInParagraph = xwpfParagraph.getRuns();
                if (runsInParagraph != null && !runsInParagraph.isEmpty()) {
                    for (XWPFRun xwpfRun : runsInParagraph) {
                        String text = xwpfRun.getText(0);
                        if (text != null) {
                            Set<String> keys = tokenReplacementMap.keySet();
                            for (String key : keys) {
                                if (text.contains(key)) {
                                    text = text.replace(key, tokenReplacementMap.get(key));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
