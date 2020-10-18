package com.ark.resumecreator;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;
import com.ark.resumecreater.exceptions.ResumeCreationException;
import com.ark.resumecreater.service.IResumeCreatorService;
import com.ark.resumecreater.service.ResumeCreatorServiceImpl;
import com.ark.resumecreator.util.ResumeCreatorDataHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Created class for TDD using Junit5+</p>
 */
public class ResumeCreatorSpec {

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


}