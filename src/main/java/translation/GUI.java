package translation;

import javax.swing.*;

public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JPanel countryPanel = new JPanel();
            countryPanel.add(new JLabel("Country:"));

            JPanel languagePanel = new JPanel();
            languagePanel.add(new JLabel("Language:"));

            Translator translator = new JSONTranslator();
            LanguageCodeConverter langCodeConverter = new LanguageCodeConverter();
            CountryCodeConverter countryCodeConverter = new CountryCodeConverter();

            JComboBox<String> languageComboBox = new JComboBox<>();
            for(String langCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(langCodeConverter.fromLanguageCode(langCode));
            }
            languagePanel.add(languageComboBox);

            JComboBox<String> countryComboBox = new JComboBox<>();
            for(String countryCode : translator.getCountryCodes()) {
                countryComboBox.addItem(countryCodeConverter.fromCountryCode(countryCode));
            }
            countryPanel.add(countryComboBox);

            JPanel buttonPanel = new JPanel();

            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            buttonPanel.add(resultLabel);

            countryComboBox.addItemListener(e -> {
                String language = languageComboBox.getSelectedItem().toString();
                String country = countryComboBox.getSelectedItem().toString();

                String langCode = langCodeConverter.fromLanguage(language);
                String countryCode = countryCodeConverter.fromCountry(country);

                String result = translator.translate(countryCode, langCode);
                if (result == null) {
                    result = "No translation found!";
                }
                resultLabel.setText(result);
            });

            languageComboBox.addItemListener(e -> {
                    String language = languageComboBox.getSelectedItem().toString();
                    String country = countryComboBox.getSelectedItem().toString();

                    String langCode = langCodeConverter.fromLanguage(language);
                    String countryCode = countryCodeConverter.fromCountry(country);

                    String result = translator.translate(countryCode, langCode);
                    if (result == null) {
                        result = "No translation found!";
                    }
                    resultLabel.setText(result);
            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        });
    }
}
