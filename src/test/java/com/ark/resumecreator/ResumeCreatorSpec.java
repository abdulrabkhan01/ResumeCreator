package com.ark.resumecreator;

import com.ark.resumecreater.domain.*;
import com.ark.resumecreater.exceptions.ResumeCreationException;
import com.ark.resumecreater.service.*;
import com.ark.resumecreator.util.*;
import org.junit.jupiter.api.*;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Created class for TDD using Junit5+</p>
 * @author AbdulR
 */
public class ResumeCreatorSpec {

    public static final String TEST_1_DOCX = "Test1.docx";
    public static final String TEST_2_DOCX = "Test2.docx";
    public static final String TEST_3_DOCX = "Test3.docx";
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

    @Test
    @DisplayName("Given Valid Template & Replacement Map, Resume Creator should create Non Empty Output & tokens should be replaced with actual value in the document header and mobile number should match.")
    void nonEmptyOutputValidReplacementInHeaderVerifyMobileSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeVerificationUtil resumeVerificationUtil = ResumeVerificationUtil.INSTANCE;
        ResumeCreatorInput input = dataHelper.createValidInput();
        ResumeCreatorOutput output = resumeCreatorService.createResume(input);
        resumeVerificationUtil.createSampleFile(output.getOutputStream(), TEST_3_DOCX);
        InputStream inputStream = resumeVerificationUtil.getSampleFileAsStream(TEST_3_DOCX);
        assertTrue(resumeVerificationUtil.verifyIfDocumentContainsValue(inputStream,"123456"));
    }

    @Test
    @DisplayName("Given Valid Template & Replacement Map, Resume Creator should create Non Empty Output & tokens should be replaced with actual value in the document header and mobile number should match.")
    void nonEmptyOutputValidReplacementInHeaderVerifyCopyrightSpec() {
        ResumeCreatorDataHelper dataHelper = ResumeCreatorDataHelper.INSTANCE;
        ResumeVerificationUtil resumeVerificationUtil = ResumeVerificationUtil.INSTANCE;
        ResumeCreatorInput input = dataHelper.createValidInput();
        ResumeCreatorOutput output = resumeCreatorService.createResume(input);
        resumeVerificationUtil.createSampleFile(output.getOutputStream(), "Test4.docx");
        InputStream inputStream = resumeVerificationUtil.getSampleFileAsStream("Test4.docx");
        assertTrue(resumeVerificationUtil.verifyIfDocumentContainsValue(inputStream,"2020 all rights reserved."));
    }
}