package com.ark.resumecreater.validators;

import com.ark.resumecreater.domain.ResumeCreatorInput;

public interface IResumeCreatorInputValidator {
    boolean validateInput(ResumeCreatorInput input);
}
