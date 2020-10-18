package com.ark.resumecreater.validators;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.exceptions.ResumeCreationException;

public class ResumeCreatorInputValidatorImpl implements IResumeCreatorInputValidator {
    @Override
    public boolean validateInput(ResumeCreatorInput input) {
        validateNotEmptyTemplate(input);
        validateEmptyReplacementMap(input);
        return true;
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
