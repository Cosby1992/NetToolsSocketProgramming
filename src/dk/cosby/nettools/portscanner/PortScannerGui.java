package dk.cosby.nettools.portscanner;

import javafx.event.ActionEvent;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortScannerGui implements PortScanner.PortListener {

    public TextArea ta_port_scanner;
    public TextField tf_ip_address;

    public void startScanning(ActionEvent actionEvent) {

        if(validateIP(tf_ip_address.getText())){

            ta_port_scanner.appendText("Starting scanner-threads on " + tf_ip_address.getText() + "\n");

            ThreadHandler threadHandler = new ThreadHandler(65535, tf_ip_address.getText(), this);

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

    @Override
    public void sendOpenPortUpdate(String report) {
        ta_port_scanner.appendText(report);
    }
}
