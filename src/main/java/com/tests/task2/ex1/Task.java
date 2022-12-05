package com.tests.task2.ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {

    private static final Pattern namePattern = Pattern.compile("\\s+name\\s*=\\s*\"([A-Za-zа-яА-ЯіІїЇєЄґҐ0-9]+)\"", Pattern.UNICODE_CASE);
    private static final Pattern surnamePattern = Pattern.compile("\\s+surname\\s*=\\s*\"([A-Za-zа-яА-ЯіїєґҐ0-9]+)\"", Pattern.UNICODE_CASE);

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("temp.xml"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.xml"))) {
            String person = reader.readLine();
            person = person.replace("<persons>", "");
            writer.write("<persons>");
            if (person.isBlank()) {
                writer.newLine();
                person = reader.readLine();
            }
            do {
                while (!person.endsWith(">")) {
                    person = person + "\n" + reader.readLine();
                }
                person = parsePerson(person);
                writer.write(person + "\n");
            } while ((person = reader.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Changes person xml tag to parsed look
    private static String parsePerson(String person) {
        Matcher nameMatcher = namePattern.matcher(person);
        Matcher surnameMatcher = surnamePattern.matcher(person);
        while (nameMatcher.find() && surnameMatcher.find()) {
            String name = nameMatcher.group(1);
            String surname = surnameMatcher.group(1);
            person = person.replaceAll("\\ssurname\\s*=\\s*\""+surname+"\"\\s*", " ")
                    .replaceAll(name, name + " " + surname);
        }
        return person;
    }
}
