package com.sstacorp.colectivo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtil {
    /**
     * Checks that validation errors are of specified error. Also asserts expected number of errors.
     *
     * @param expectedErrors    expected errors
     * @param errorMsgException actual error exception with errors
     */
    public static void assertValidationErrors(List<String> expectedErrors,ErrorMessageException errorMsgException) {
        assertNotNull(errorMsgException.getErrors());
        assertEquals(expectedErrors.size(), errorMsgException.getErrors().size());

        List<String> actualErrorCodes = new ArrayList<String>();
        for (ErrorMessage errorMessage : errorMsgException.getErrors()) {
            actualErrorCodes.add(errorMessage.getCode());
        }

        // sort and verify equality of error codes
        Collections.sort(expectedErrors);
        Collections.sort(actualErrorCodes);

        assertEquals(expectedErrors, actualErrorCodes);
    }

    /**
     * Checks that validation errors are of specified error. Also asserts expected number of errors.
     *
     * @param expectedNumberOfErrors expected number of errors
     * @param expectedError          expected error
     * @param errorMsgException      actual error exception with errors
     */
    public static void assertValidationErrors(int expectedNumberOfErrors, String expectedError, ErrorMessageException errorMsgException) {
        assertEquals(expectedNumberOfErrors, errorMsgException.getErrors().size());
        for (ErrorMessage errorMessage : errorMsgException.getErrors()) {
            assertEquals(expectedError, errorMessage.getCode());
        }
    }
}
