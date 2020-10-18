package com.ark.resumecreater.bo;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;

public interface IResumeCreator {
    ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput);
}
