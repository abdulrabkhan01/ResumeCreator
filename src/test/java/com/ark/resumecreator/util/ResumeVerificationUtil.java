package com.ark.resumecreator.util;

import com.ark.resumecreater.exceptions.ResumeCreationException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;


public enum ResumeVerificationUtil {
    INSTANCE;

    public static final String OUTPUT_TEST_DOCX = "./output/";

    public void createSampleFile(OutputStream outputStream, String fileName) {
        if (!(outputStream instanceof ByteArrayOutputStream)) {
            throw new IllegalArgumentException("Only valid type is ByteArrayOutputStream");
        }
        try (ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
             FileOutputStream fileOutputStream = new FileOutputStream(new File(OUTPUT_TEST_DOCX+"/"+fileName));) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResumeCreationException("Error has occurred while saving file " + ex.getMessage());
        }
    }

    public InputStream getSampleFileAsStream(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(OUTPUT_TEST_DOCX + "/" + fileName);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new ResumeCreationException("Error has occurred while getting file " + ex.getMessage());
        }
        return inputStream;

    }

    public boolean verifyIfDocumentContainsValue(InputStream inputStream, String value) {
        try (XWPFDocument document = new XWPFDocument(inputStream)) {
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            if (checkValueInParagraphs(value, paragraphs)
                    || checkValueInHeader(value, document.getHeaderList())
                    || checkValueInFooter(value, document.getFooterList())
                    ) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResumeCreationException("Error has occurred while verifying document " + ex.getMessage());
        }
        return false;
    }

    private boolean checkValueInFooter(String value, List<XWPFFooter> footerList) {
        if(footerList != null) {
            for(XWPFFooter footer : footerList) {
                if(checkValueInParagraphs(value,footer.getParagraphs())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkValueInHeader(String value, List<XWPFHeader> headerList) {
        if(headerList != null) {
            for(XWPFHeader header : headerList) {
                if(checkValueInParagraphs(value,header.getParagraphs())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkValueInParagraphs(String value, List<XWPFParagraph> paragraphs) {
        if (paragraphs != null && !paragraphs.isEmpty()) {
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    System.out.println(run.text());
                    if (run.text() != null && run.text().contains(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}