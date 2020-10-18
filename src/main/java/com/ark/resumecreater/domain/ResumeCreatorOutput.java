package com.ark.resumecreater.domain;

import java.io.OutputStream;

/**
 * <p>
 * Immutable domain object containing the output of processing, it contains
 * the output stream that will contain the MS Word file that can be saved to a File
 * </p>
 * @author AbdulR
 */
public class ResumeCreatorOutput {
    private final OutputStream outputStream;

    public ResumeCreatorOutput(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
