package histwindow;

import org.jdatepicker.impl.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class DayInHistoryWindow {
        private static int day;
        private static int month;
        private static String text;
        public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("G:\\Java\\HttpStudy\\src\\histwindow\\Text_ru.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFrame window = new JFrame("Выбор даты");
            window.setSize(816,839);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(0,0,window.getWidth(),window.getHeight());
            panel.setFocusable(true);
            UtilDateModel model = new UtilDateModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
                private String datePattern = "dd MMM yyyy";
                private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
                @Override
                public Object stringToValue(String text) throws ParseException {
                    return dateFormatter.parseObject(text);
                }

                @Override
                public String valueToString(Object value) throws ParseException {
                    if (value != null) {
                        Calendar cal = (Calendar) value;
                        return dateFormatter.format(cal.getTime());
                    }
                    return "";
                }
            });
            JLabel label = new JLabel(text != null ? text : "Факт",JLabel.CENTER);
            label.setBounds(100,50,500,200);
            JButton btn = new JButton("Получить факт");
            btn.setBounds(50,50,150,20);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    month = datePicker.getModel().getMonth() + 1;
                    day = datePicker.getModel().getDay();
                    HttpRequest request = null;
                    try {
                        request = HttpRequest.newBuilder().uri(new URI("http://numbersapi.com/" + month + "/" + day + "/date")).GET().build();
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                    HttpResponse<String> response = null;
                    try {
                        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    text = response.body();
                    System.out.println(text);
                    label.setText("<html>" + text + "</html>");
                }
            });

            datePicker.setBounds(0,0,200,20);
            panel.add(datePicker);
            panel.add(label);
            panel.add(btn);
            window.getContentPane().add(panel);
            window.setVisible(true);
    }
}
