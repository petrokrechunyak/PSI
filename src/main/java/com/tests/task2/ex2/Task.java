package com.tests.task2.ex2;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Task {

    private Map<String, Double> sortedMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Task task = new Task();

        task.main();

    }

    private void main() throws Exception {
        File dir = new File("./fines");
        File[] files = dir.listFiles();

        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();

        if (files != null) {
            for (File file : files) {
                if(file.length() == 0L || !file.getName().endsWith(".xml"))
                    continue;
                handler.penalty = null;
                parser.parse(file, handler);
            }

            sortedMap = sortMap();
            writeJson();
        }
    }

    // sorts finished map and returns it
    private Map<String, Double> sortMap() {
        return sortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // writes sortedMap with fines to json
    private void writeJson() {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator generator = factory.createGenerator(new FileWriter("./done/result.json"))) {
            generator.useDefaultPrettyPrinter();
            generator.writeStartObject();
            for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
                generator.writeNumberField(entry.getKey(), entry.getValue());
            }
            generator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SAXHandler extends DefaultHandler {

        Penalty penalty = null;
        String content = null;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            switch (qName) {
                case "penalty":
                    penalty = new Penalty();
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName,
                               String qName) {
            switch (qName) {
                case "penalty":
                    sortedMap.put(penalty.getType(),
                            sortedMap.getOrDefault(penalty.getType(), 0D) + penalty.getFine_amount());
                    break;
                case "date_time":
                    penalty.setDate_time(content);
                    break;
                case "first_name":
                    penalty.setFirst_name(content);
                    break;
                case "last_name":
                    penalty.setLast_name(content);
                    break;
                case "type":
                    penalty.setType(content);
                    break;
                case "fine_amount":
                    penalty.setFine_amount(Double.parseDouble(content));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            content = String.copyValueOf(ch, start, length).trim();
        }

    }

}
