package com.ark.resumecreater.service;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;
import com.ark.resumecreater.validators.IResumeCreatorInputValidator;
import com.ark.resumecreater.validators.ResumeCreatorInputValidatorImpl;

/**
 * <p> Implementation for IResumeCreatorService</p>
 * @author AbdulR
 */
public class ResumeCreatorServiceImpl implements IResumeCreatorService {
    IResumeCreatorInputValidator validator = new ResumeCreatorInputValidatorImpl();
    public ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput) {
        validator.validateInput(resumeCreatorInput);
        return null;
    }
}
