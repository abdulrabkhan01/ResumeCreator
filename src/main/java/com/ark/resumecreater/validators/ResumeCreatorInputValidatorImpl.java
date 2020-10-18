package com.ark.resumecreater.validators;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.exceptions.ResumeCreationException;

public class ResumeCreatorInputValidatorImpl implements IResumeCreatorInputValidator {
    @Override
    public boolean validateInput(ResumeCreatorInput input) {
        validateEmptyInputObject(input);
        validateNotEmptyTemplate(input);
        validateEmptyReplacementMap(input);
        return true;
    }
    private void validateEmptyInputObject(ResumeCreatorInput input) {
        if(input == null) {
            throw new ResumeCreationException("ResumeCreatorInput is null or empty");
        }
    }

    private void validateEmptyReplacementMap(ResumeCreatorInput input) {
        if(input.getTokenReplacementMap() == null) {
            throw new ResumeCreationException("Replacement Map is null or empty");
        }
    }

    private void validateNotEmptyTemplate(ResumeCreatorInput input) {
        if (input.getResumeTemplate()==null) {
            throw new ResumeCreationException("Template is empty ");
        }
    }
}
