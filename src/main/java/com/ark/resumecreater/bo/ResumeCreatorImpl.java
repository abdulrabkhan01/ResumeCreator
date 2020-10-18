package com.ark.resumecreater.bo;

import com.ark.resumecreater.domain.ResumeCreatorInput;
import com.ark.resumecreater.domain.ResumeCreatorOutput;

import java.io.IOException;
import java.io.OutputStream;

public class ResumeCreatorImpl implements IResumeCreator {
    @Override
    public ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput) {
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        return new ResumeCreatorOutput(outputStream);
    }
}
