package com.ark.resumecreator.util;

import com.ark.resumecreater.domain.ResumeCreatorInput;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> Singleton  for populating input for testing</p>
 * @author AbdulR
 */
public enum ResumeCreatorDataHelper {

    INSTANCE();
    private static Map<String,String> replacementMap = new HashMap<>();

    static {
        replacementMap.put("${name}","Abdul");
    }
    public ResumeCreatorInput createInputWithEmptyDocument() {
        return new ResumeCreatorInput(null,replacementMap);
    }





}
