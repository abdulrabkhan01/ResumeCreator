package com.ark.resumecreator;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;
import com.ark.resumecreater.exceptions.ResumeCreationException;
import com.ark.resumecreater.service.IResumeCreatorService;
import com.ark.resumecreater.service.ResumeCreatorServiceImpl;
import com.ark.resumecreator.util.ResumeCreatorDataHelper;
import com.ark.resumecreator.util.ResumeVerificationUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p> Created class for TDD using Junit5+</p>
 * @author AbdulR
 */
public class ResumeCreatorSpec {

    public static final String TEST_1_DOCX = "Test1.docx";
    public static final String TEST_2_DOCX = "Test2.docx";
    IResumeCreatorService resumeCreatorService = new ResumeCreatorServiceImpl();

    @Test
    @DisplayName("Given Null Input object for  Resume Generation, Exception should be thrown at Runtime")
    void emptyInputSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeCreatorInput input = null;
        assertThrows(ResumeCreationException.class,()-> {resumeCreatorService.createResume(input);});
    }

    @Test
    @DisplayName("Given Empty Template for Resume Generation, Exception should be thrown at Runtime")
    void emptyTemplateSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeCreatorInput input = dataHelper.createInputWithEmptyDocument();
        assertThrows(ResumeCreationException.class,()-> {resumeCreatorService.createResume(input);});
    }

    @Test
    @DisplayName("Given Empty Replacement Map for Resume Generation, Exception should be thrown at Runtime")
    void emptyReplacementMapSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeCreatorInput input = dataHelper.createInputWithEmptyReplacementMap();
        assertThrows(ResumeCreationException.class,()-> {resumeCreatorService.createResume(input);});
    }

    @Test
    @DisplayName("Given Valid Template & Replacement Map, Resume Creator should create Non Empty Output")
    void nonEmptyOutputSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeCreatorInput input = dataHelper.createValidInput();
        assertTrue(resumeCreatorService.createResume(input) != null);
    }

    @Test
    @DisplayName("Given Valid Template & Replacement Map, Resume Creator should create Non Empty Output & tokens should be replaced with actual value.")
    void nonEmptyOutputValidReplacementSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeVerificationUtil resumeVerificationUtil = ResumeVerificationUtil.INSTANCE;
        ResumeCreatorInput input = dataHelper.createValidInput();
        ResumeCreatorOutput output = resumeCreatorService.createResume(input);
        resumeVerificationUtil.createSampleFile(output.getOutputStream(), TEST_1_DOCX);
        InputStream inputStream = resumeVerificationUtil.getSampleFileAsStream(TEST_1_DOCX);
        assertTrue(resumeVerificationUtil.verifyIfDocumentContainsValue(inputStream,"Abdul"));
    }
    @Test
    @DisplayName("Given Valid Template & Replacement Map, Resume Creator should create Non Empty Output & tokens should be replaced with actual value in the document header.")
    void nonEmptyOutputValidReplacementInHeaderSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeVerificationUtil resumeVerificationUtil = ResumeVerificationUtil.INSTANCE;
        ResumeCreatorInput input = dataHelper.createValidInput();
        ResumeCreatorOutput output = resumeCreatorService.createResume(input);
        resumeVerificationUtil.createSampleFile(output.getOutputStream(), TEST_2_DOCX);
        InputStream inputStream = resumeVerificationUtil.getSampleFileAsStream(TEST_2_DOCX);
        assertTrue(resumeVerificationUtil.verifyIfDocumentContainsValue(inputStream,"test@ark.com"));
    }
}