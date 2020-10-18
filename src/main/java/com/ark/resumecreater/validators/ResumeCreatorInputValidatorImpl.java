package com.ark.resumecreater.validators;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.exceptions.ResumeCreationException;

public class ResumeCreatorInputValidatorImpl implements IResumeCreatorInputValidator {
    @Override
    public boolean validateInput(ResumeCreatorInput input) {
        validateNotEmptyTemplate(input);
        return true;
    }


    private void validateNotEmptyTemplate(ResumeCreatorInput input) {
        if (input.getResumeTemplate()==null) {
            throw new ResumeCreationException("Template should not be empty ");
        }
    }
}
