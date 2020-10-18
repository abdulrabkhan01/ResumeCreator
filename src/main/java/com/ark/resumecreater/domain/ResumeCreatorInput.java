//Date: 18/10/2020
package com.ark.resumecreater.domain;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * <p> Domain Immutable object representing the input required for Resume Creation</p>
 * @author AbdulR
 *
 */
public class ResumeCreatorInput {
    //MS Word template which contains variable tokens to be replaced
    private final InputStream resumeTemplate;
    //Replacement Map { Key = TemplateVariable, Value = Actual value to be replaced in template}
    private final Map<String,String> tokenReplacementMap;

    public ResumeCreatorInput(InputStream resumeTemplate, Map<String, String> tokenReplacementMap) {
        this.resumeTemplate = resumeTemplate;
        this.tokenReplacementMap = tokenReplacementMap;
    }

    public InputStream getResumeTemplate() {
        return resumeTemplate;
    }

    //Returning Un Modifiable Map from this Immutable object
    public Map<String, String> getTokenReplacementMap() {
        return Collections.unmodifiableMap(tokenReplacementMap);
    }
}
