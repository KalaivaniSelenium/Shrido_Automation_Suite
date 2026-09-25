package com.thinktimetechno.utils;

import com.thinktimetechno.constants.FrameworkConstants;
import com.thinktimetechno.mail.EmailAttachmentsSender;
import com.thinktimetechno.utils.FailedApiTracker.FailedAPI;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.thinktimetechno.mail.EmailConfig.*;


public class EmailSendUtils {

    private EmailSendUtils() {
        super();
    }


    public static void sendEmail(int total, int passed, List<FailedAPI> failedList) {
        if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
            System.out.println("****************************************");
            System.out.println("Send Email - START");
            System.out.println("****************************************");

            System.out.println("File name: " + FrameworkConstants.getExtentReportFilePath());

            String messageBody = buildApiExecutionSummary(total, passed, failedList);
            String attachmentFile = FrameworkConstants.getExtentReportFilePath();

            try {
                EmailAttachmentsSender.sendEmailWithAttachments(
                        SERVER, PORT, FROM, PASSWORD, TO, SUBJECT, messageBody, attachmentFile);
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
            
            System.out.println("Send Email - END");
            FailedApiTracker.clearFailures(); // Clear list for next run
            System.out.println("****************************************");
            
        }
    }

    


    private static String buildApiExecutionSummary(int total, int passed, List<FailedAPI> failedList) {
        int failed = failedList.size();
        double failPercent = ((double) failed / total) * 100;

        String executionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a"));
        String companyName = "ThinkTime Automation Team";
        String environment = "Staging";

        // Build Failed APIs Table
        StringBuilder failedApiTable = new StringBuilder();
        if (!failedList.isEmpty()) {
            failedApiTable.append("<h3 style='color:#4b2e70; font-size:16px;'>Failed APIs</h3>")
                    .append("<table style='border-collapse: collapse; width: 60%; box-shadow: 0 0 10px #ccc; border-radius:8px; overflow:hidden;'>")
                    .append("<thead style='background-color:#00B2A9; color:white;'>")
                    .append("<tr>")
                    .append("<th style='padding:10px; border: 1px solid #ddd; text-align:center;'>API Name</th>")
                    .append("<th style='padding:10px; border: 1px solid #ddd; text-align:center;'>Status Code</th>")
                    .append("</tr></thead><tbody>");

            for (FailedAPI api : failedList) {
                failedApiTable.append("<tr>")
                        .append("<td style='padding:8px; border: 1px solid #ddd;'>").append(api.name).append("</td>")
                        .append("<td style='padding:8px; border: 1px solid #ddd; color:red;'>").append(api.statusCode).append("</td>")
                        .append("</tr>");
            }
            failedApiTable.append("</tbody></table>");
        }

        // Build Full Email Body
        return "<html><body style='font-family:Arial,sans-serif;'>"
        + "<p>Hi Team,</p>"
        + "<p>Please find below the execution summary of the recent <strong>API testing:</strong></p>"
        + "<p><strong>Application Name:</strong> Shrido</p>"
        + "<p><strong>Execution Date & Time:</strong> " + executionDate + "</p>"
        + "<p><strong>Environment:</strong> " + environment + "</p>"
        + "<h3 style='color:#4b2e70; font-size:16px;'>Execution Summary</h3>"
        + "<table style='border-collapse: collapse; width: 45%; box-shadow: 0 0 10px #ccc; border-radius:8px; overflow:hidden;'>"
        + "<thead style='background-color:#00B2A9; color:white;'>"
        + "<tr>"
        + "<th style='padding:10px; border: 1px solid #ddd; text-align:center;'>Total</th>"
        + "<th style='padding:10px; border: 1px solid #ddd; text-align:center;'>Passed</th>"
        + "<th style='padding:10px; border: 1px solid #ddd; text-align:center;'>Failed</th>"
        + "</tr>"
        + "</thead><tbody style='text-align:center;'>"
        + "<tr>"
        + "<td style='padding:8px; border: 1px solid #ddd;'>" + total + "</td>"
        + "<td style='padding:8px; border: 1px solid #ddd; color:green; font-weight:bold;'>" + passed + "</td>"
        + "<td style='padding:8px; border: 1px solid #ddd; color:red; font-weight:bold;'>" + failed + "</td>"
        + "</tr></tbody></table>"
        
        // Failure percentage (modified note)
        + "<p style='margin-top:10px;'><strong>" + String.format("%.0f", failPercent)
        + "% of the APIs failed during testing.</strong></p>"
        
        + (!failedList.isEmpty() ? failedApiTable.toString() : "")
        + "<br/><h3 style='color:#4b2e70; font-size:16px;'>Summary of Observations</h3>"
        + "<div style='line-height:1.6;'>"
        + "<p>During our recent validation, we observed the following key issues:</p>"
        + "<ul>"
        + "<li style='margin-left:15px;'><b>500 Internal Server Errors </b>were encountered on several endpoints, indicating potential server-side instability.</li>"
        + "<li style='margin-left:15px;'><b>400 Bad Requests</b> were received even for valid inputs, suggesting inconsistencies in input validation.</li>"
        + "<li style='margin-left:15px;'>These issues should be reviewed and addressed to <b>ensure stable and reliable API behavior.</b></li>"
        + "</ul>"
	     // Replaced footer message
	     + "<p>Please download the attached HTML report to access detailed test results.</p>"
	     + "<p>Best regards,<br/>" + companyName + ".<br/>"
		+ "<a href='https://thinktime.in/' style='color:#1E90FF;'>www.thinktime.in</a></p>" + "</body></html>";
}


}

