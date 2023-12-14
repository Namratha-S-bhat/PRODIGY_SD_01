import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField temperatureField;
    private JComboBox<String> originalUnitComboBox;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel temperatureLabel = new JLabel("Enter Temperature:");
        add(temperatureLabel);

        temperatureField = new JTextField();
        add(temperatureField);

        JLabel originalUnitLabel = new JLabel("Select Original Unit:");
        add(originalUnitLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        originalUnitComboBox = new JComboBox<>(units);
        add(originalUnitComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        add(convertButton);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel);

        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String originalUnit = (String) originalUnitComboBox.getSelectedItem();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

            switch (originalUnit) {
                case "Celsius":
                    fahrenheit = (temperature * 9 / 5) + 32;
                    kelvin = temperature + 273.15;
                    break;
                case "Fahrenheit":
                    celsius = (temperature - 32) * 5 / 9;
                    kelvin = (temperature + 459.67) * 5 / 9;
                    break;
                case "Kelvin":
                    celsius = temperature - 273.15;
                    fahrenheit = (temperature * 9 / 5) - 459.67;
                    break;
                default:
                    resultLabel.setText("Invalid unit");
                    return;
            }

            resultLabel.setText(String.format("%.2f °C\n%.2f °F\n%.2f K", celsius, fahrenheit, kelvin));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterGUI();
            }
        });
    }
}
