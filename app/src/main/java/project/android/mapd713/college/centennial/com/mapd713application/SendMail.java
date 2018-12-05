package project.android.mapd713.college.centennial.com.mapd713application;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail extends AsyncTask<Void,Void,Void> {
    private Context ctx;

    public SendMail(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String to = "*****@gmail.com";
            sendviaSSL("smtp.gmail.com", "587", "******", to, to, "MY TEST", "deneme zaaaaaa");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.print("Sending Email Exception: " + e);
        }
        return null;
    }



    //TODO: IMPLEMENT THIS CLASS WITH ASYNC TASK
    public void sendviaSSL(String host, String port, String password, String from, String mail_to,String subject,String mail) throws Exception
    {

        String[] to = { mail_to };

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/html; charset=UTF-8");
        message.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject(subject, "UTF-8");

        message.setText(mail, "UTF-8");

        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
