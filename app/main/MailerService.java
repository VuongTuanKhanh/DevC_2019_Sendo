package main;

import com.sendgrid.*;
import org.apache.commons.mail.EmailAttachment;
import play.Logger;
//import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class MailerService {
    @Inject
    MailerClient mailerClient;

//    public void sendEmail(String name, String email_address) {
//        Email email = new Email()
//                .setSubject("Email xác nhận")
//                .setFrom("Dreampass<service@dreampass.vn>")
//                .addTo(email_address)
//                // adds attachment
//                //.addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"))
//                // adds inline attachment from byte array
//                //.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE)
//                // adds cid attachment
//                //.addAttachment("image.jpg", new File("/some/path/image.jpg"), cid)
//                // sends text, HTML or both...
//                //.setBodyText("A text message")
//                .setBodyHtml("<html><body>Chúc mừng " + name + ". Bạn đã đăng kí thành công </p></body></html>");
//
//        Logger.info("------------------Mail is sendding-----------------");
//        mailerClient.send(email);
//    }

    public void sendEmail(String name, String email_address) {
        Email from = new Email("service@dreampass.vn" ,"Dreampass");
        String subject = "Xác nhận vé";
        Email to = new Email(email_address);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}
