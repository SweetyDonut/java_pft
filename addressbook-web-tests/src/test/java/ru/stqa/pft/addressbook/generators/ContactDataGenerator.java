package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даниил on 11.06.2017.
 */
public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Group count")
  public int count;

  @Parameter (names = "-f", description ="Target file" )
  public String file;

  @Parameter ( names ="-d", description = "Target Date format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException{
    List<ContactData> groups = GenerateContact(count);
    if (format.equals("csv")){
      saveAsCsv(groups, new File(file));
    }else if(format.equals("xml")){
      saveAsXml(groups,new File(file));
    }else if(format.equals("json")){
      saveAsJson(groups,new File(file));
    }else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException{
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException{
    Writer writer = new FileWriter(file);

    for (ContactData contact: contacts){
      writer.write(String.format("%s;%s;%s;%s;%s\n",contact.getFirstname(),
              contact.getLastname(),
              contact.getAddress(),
              contact.getMobilephone(),
              contact.getMail()));
    }
    writer.close();
  }

  private static List<ContactData> GenerateContact(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Name %s", i))
              .withLastname(String.format("Lastname %s", i))
              .withAddress(String.format("Adress %s", i))
              .withMobilephone(String.format("MobilePhone %s", i))
              .withMail(String.format("mail %s", i)));
    }
    return contacts;
  }
}

