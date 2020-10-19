package com.ark.resumecreator.util;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import java.io.*;
import java.util.*;
/**
 * <p> Singleton  for populating input for testing</p>
 *
 * @author AbdulR
 */
public enum ResumeCreatorDataHelper {
    INSTANCE();
    private static Map<String, String> replacementMap = new HashMap<>();

    static {
        replacementMap.put("${name}", "Abdul");
    }

    public ResumeCreatorInput createInputWithEmptyDocument() {
        return new ResumeCreatorInput(null, replacementMap);
    }

    public ResumeCreatorInput createInputWithEmptyReplacementMap() {
        return new ResumeCreatorInput(getDefaultFile(), null);
    }
    public ResumeCreatorInput createValidInput() {
        return new ResumeCreatorInput(getDefaultFile(), replacementMap);
    }

    private InputStream getDefaultFile() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./inputTemplates/SampleInput.docx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting file");
        }
        return inputStream;
    }


}
