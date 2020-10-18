package com.ark.resumecreater.service;

import com.ark.resumecreater.bo.IResumeCreator;
import com.ark.resumecreater.bo.ResumeCreatorImpl;
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
    IResumeCreator resumeCreator = new ResumeCreatorImpl();
    public ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput) {
        validator.validateInput(resumeCreatorInput);
        return resumeCreator.createResume(resumeCreatorInput);
    }
}
