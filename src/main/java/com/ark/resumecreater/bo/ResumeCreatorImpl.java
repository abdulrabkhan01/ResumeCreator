package com.ark.resumecreater.bo;

import com.ark.resumecreater.domain.*;
import com.ark.resumecreater.exceptions.ResumeCreationException;
import com.ark.resumecreater.util.ResumeCreatorUtilDocx;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @author AbdulR
 */
public class ResumeCreatorImpl implements IResumeCreator {
    @Override
    public ResumeCreatorOutput createResume(ResumeCreatorInput resumeCreatorInput) {
        ResumeCreatorUtilDocx resumeCreatorUtilDocx = ResumeCreatorUtilDocx.INSTANCE;
        XWPFDocument outputDocument = resumeCreatorUtilDocx.updateTemplateUsingMap(resumeCreatorInput);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputDocument.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResumeCreationException("Error has occurred while creating the resume using template " + e.getMessage());
        }
        return new ResumeCreatorOutput(outputStream);
    }
}
