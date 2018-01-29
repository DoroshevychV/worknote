package net.worknote.mail;

import net.worknote.entity.User;
import net.worknote.request.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class Message {

    private String messageForConfirmationOfMail;

    public Message() {
    }

    public Message(String messageForConfirmationOfMail) {
        this.messageForConfirmationOfMail = messageForConfirmationOfMail;
    }

    public String getMessageForConfirmationOfMail() {
        return messageForConfirmationOfMail;
    }

    public void setMessageForConfirmationOfMail(String name) {
        messageForConfirmationOfMail = "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>worknote</title>\n" +
                "    <link href=\"bootstrap-3.3.7-dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<style>\n" +
                "    body {\n" +
                "        padding: 0;\n" +
                "        margin: 0;\n" +
                "    }\n" +
                "\n" +
                "    html {\n" +
                "        -webkit-text-size-adjust: none;\n" +
                "        -ms-text-size-adjust: none;\n" +
                "    }\n" +
                "\n" +
                "    @media only screen and (max-device-width: 680px),\n" +
                "    only screen and (max-width: 680px) {\n" +
                "        *[class=\"table_width_100\"] {\n" +
                "            width: 96% !important;\n" +
                "        }\n" +
                "        *[class=\"border-right_mob\"] {\n" +
                "            border-right: 1px solid #dddddd;\n" +
                "        }\n" +
                "        *[class=\"mob_100\"] {\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "        *[class=\"mob_center\"] {\n" +
                "            text-align: center !important;\n" +
                "        }\n" +
                "        *[class=\"mob_center_bl\"] {\n" +
                "            float: none !important;\n" +
                "            display: block !important;\n" +
                "            margin: 0px auto;\n" +
                "        }\n" +
                "        .iage_footer a {\n" +
                "            text-decoration: none;\n" +
                "            color: #929ca8;\n" +
                "        }\n" +
                "        img.mob_display_none {\n" +
                "            width: 0px !important;\n" +
                "            height: 0px !important;\n" +
                "            display: none !important;\n" +
                "        }\n" +
                "        img.mob_width_50 {\n" +
                "            width: 40% !important;\n" +
                "            height: auto !important;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    .table_width_100 {\n" +
                "        width: 680px;\n" +
                "    }\n" +
                "\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"mailsub\" class=\"notification\" align=\"center\">\n" +
                "        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\" bgcolor=\"#eff3f8\">\n" +
                "                    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px; min-width: 300px;\">\n" +
                "                        <tr>\n" +
                "                            <td>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" bgcolor=\"\">\n" +
                "                                <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\" style=\"background-color: #4d74a1\">\n" +
                "                                            <a href=\"http://www.worknote.net\" target=\"_blank\" style=\"color: #596167; font-family: Arial, Helvetica, sans-serif; float:left; width:100%; padding:20px;text-align:center; font-size: 13px;\">\n" +
                "                                                <font face=\"Arial, Helvetica, sans-seri; font-size: 13px;\" size=\"3\" color=\"#596167\">\n" +
                "                                                    <img src=\"https://cdn1.savepice.ru/uploads/2018/1/29/0e17ba1eb1d8aea5140c12339a8d969e-full.png\" width=\"250\" alt=\"Worknote\" border=\"0\" /></font>\n" +
                "                                            </a>\n" +
                "                                        </td>\n" +
                "                                        <td align=\"right\">\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\" bgcolor=\"#f1f2f6\">\n" +
                "                                            <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\n" +
                "                                                <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"text-align: center\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"padding-top: 20px;color: black\">\n" +
                "                                                            <span style=\"color:black\">Congratulations <span style=\"font-size: 15px;font-weight: bolder;color: #4d74a1\"> Vadym</span>!<br/> You received this message because you just registered on the site <a href=\"http://www.worknote.net\" style=\"color: #4d74a1\">http://www.worknote.net</a> To confirm this mail, follow the link below.</span>\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                    <tr>\n" +
                "                                                        <td align=\"center\" style=\"padding-top: 20px;\">\n" +
                "                                                            <a href=\"message.html\">\n" +
                "                                                                <div type=\"button\" style=\"background-color: #4d74a1;border-radius: 0px;font-family: ubuntu;font-weight: bolder;color: white;max-width: 130px;padding-top: 20px;padding-bottom: 20px;cursor: pointer;\">Confirm</div>\n" +
                "                                                            </a>\n" +
                "                                                            <!-- padding -->\n" +
                "                                                            <br>\n" +
                "                                                            <br>\n" +
                "                                                            <p style=\"text-align: center;color: darkslategray;font-size: 12px\"> <b style=\"text-align: center;font-size: 12px;\">Message sent automatically. No need to answer!</b>\n" +
                "                                                                <p style=\"color: brown;font-size: 12px\">\n" +
                "                                                                    If you did not register - ignore this message!</p>\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "\n" +
                "                                                </table>\n" +
                "                                            </font>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr style=\"margin-top: 200px\">\n" +
                "                                        <td class=\"iage_footer\" align=\"center\" bgcolor=\"\">\n" +
                "                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" style=\"padding:20px;flaot:left;width:100%; text-align:center;\">\n" +
                "                                                        <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\n" +
                "                                                            <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">\n" +
                "\t\t\t\t\t                                       2018 © Worknote - notebook for your job!\n" +
                "\t\t\t\t                                        </span></font>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
                "    <script src=\"bootstrap-3.3.7-dist/js/bootstrap.min.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageForConfirmationOfMail='" + messageForConfirmationOfMail + '\'' +
                '}';
    }



}
