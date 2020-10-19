package com.ark.resumecreater.bo;
import com.ark.resumecreater.domain.*;
import java.io.*;

/**
 * @author AbdulR
 */
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
