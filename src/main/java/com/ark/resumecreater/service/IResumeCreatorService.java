package com.ark.resumecreater.service;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;

/***
 * <p> Service to process request for Resume Creation </p>
 * @author AbdulR
 */
public interface IResumeCreatorService {
    ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput);
}
