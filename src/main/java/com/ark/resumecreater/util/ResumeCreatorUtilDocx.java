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

    public static final String EMPTY_STRING = "";

    public XWPFDocument updateTemplateUsingMap(ResumeCreatorInput input) {
        //input should already be validating before reaching here
        Map<String, String> tokenReplacementMap = input.getTokenReplacementMap();
        InputStream docxResumeTemplate = input.getResumeTemplate();
        XWPFDocument modifiedDoc = null;
        try {
            XWPFDocument xwpfDocument = new XWPFDocument(docxResumeTemplate);
            List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
            processParagraphs(xwpfDocument.getParagraphs(), tokenReplacementMap);
            List<XWPFTable> tables = xwpfDocument.getTables();
            processTables(tables, tokenReplacementMap);
            processHeaders(xwpfDocument.getHeaderList(),tokenReplacementMap);
            modifiedDoc = xwpfDocument;

        } catch (Exception ex) {
            ex.printStackTrace();//TODO - Implement Logger using Log4j2.x
            throw new ResumeCreationException("Error Reading template " + ex.getMessage());
        }
        return modifiedDoc;
    }

    private void processHeaders(List<XWPFHeader> headerList, Map<String, String> tokenReplacementMap) {
        if(headerList != null && !headerList.isEmpty()) {
            for(XWPFHeader header: headerList) {
                processParagraphs(header.getParagraphs(),tokenReplacementMap);
                processTables(header.getTables(),tokenReplacementMap);
            }
        }
    }

    private void processTables(List<XWPFTable> tables, Map<String, String> tokenReplacementMap) {
        if (tables != null && !tables.isEmpty()) {
            for (XWPFTable table : tables) {
                List<XWPFTableRow> xwpfTableRows = table.getRows();
                if (xwpfTableRows != null && !xwpfTableRows.isEmpty()) {
                    processRows(tokenReplacementMap, xwpfTableRows);
                }
            }
        }
    }

    private void processRows(Map<String, String> tokenReplacementMap, List<XWPFTableRow> xwpfTableRows) {
        for (XWPFTableRow row : xwpfTableRows) {
            List<XWPFTableCell> xwpfTableCells = row.getTableCells();
            if (xwpfTableCells != null && !xwpfTableCells.isEmpty()) {
                for (XWPFTableCell cell : xwpfTableCells) {
                    processParagraphs(cell.getParagraphs(), tokenReplacementMap);
                }
            }
        }
    }


    private void processParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> tokenReplacementMap) {
        if (paragraphs != null && !paragraphs.isEmpty()) {
            for (XWPFParagraph xwpfParagraph : paragraphs) {
                List<XWPFRun> runsInParagraph = xwpfParagraph.getRuns();
                performReplacementInRun(tokenReplacementMap, xwpfParagraph, runsInParagraph);
            }
        }
    }

    private void performReplacementInRun(Map<String, String> tokenReplacementMap, XWPFParagraph xwpfParagraph, List<XWPFRun> runsInParagraph) {
        Set<String> keys = tokenReplacementMap.keySet();
        for (String key : keys) {
            //Search if the given string present under paragraph.
            TextSegment textSegment = xwpfParagraph.searchText(key, new PositionInParagraph());
            if (textSegment != null) {
                //If text if found then perform replacement.
                performReplacement(tokenReplacementMap, runsInParagraph, key, textSegment);
            }
        }
    }

    private void performReplacement(Map<String, String> tokenReplacementMap, List<XWPFRun> runsInParagraph, String key, TextSegment textSegment) {
        String allRunsText = EMPTY_STRING;
        int currentRunIndex = textSegment.getBeginRun(), finalRunIndex = textSegment.getEndRun();
        //Text might present in multiple runs.
        while (currentRunIndex <= finalRunIndex) {
            XWPFRun currentRun = runsInParagraph.get(currentRunIndex);
            allRunsText += currentRun.text();
            currentRun.setText(EMPTY_STRING, 0); //Set the Empty String
            currentRunIndex++;
        }
        allRunsText = allRunsText.replace(key, tokenReplacementMap.get(key));
        //Add the multiple runs text in first run.
        XWPFRun firstRun = runsInParagraph.get(0);
        firstRun.setText(allRunsText);
    }
}
