package dk.cosby.nettools.portscanner;

import javafx.event.ActionEvent;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortScannerGui {

    public TextArea ta_port_scanner;
    public TextField tf_ip_address;

    public void startScanning(ActionEvent actionEvent) {

        if(validateIP(tf_ip_address.getText())){

            ThreadHandler threadHandler = new ThreadHandler(200, tf_ip_address.getText());

            Thread thread = new Thread(threadHandler);
            thread.start();

        } else {

            Dialog dialog = new Dialog();

            dialog.setHeaderText("Wrong IP");
            dialog.setTitle("IP address error");
            dialog.setContentText("Invalid IP address, input valip IP");

            dialog.show();
        }



    }


    public boolean validateIP(final String ip) {
        Pattern pattern;
        Matcher matcher;
        String IPADDRESS_PATTERN
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
