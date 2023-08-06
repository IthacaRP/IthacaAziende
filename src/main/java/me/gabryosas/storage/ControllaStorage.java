package me.gabryosas.storage;

import com.google.gson.*;
import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Date;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllaStorage {
    public File file;

    public ControllaStorage() {
        file = new File(IthacaAziende.plugin.getDataFolder(), "controllo.json");
        createJSON();
    }
    public void createJSON() {
        try {
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("[]");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addControllo(String azienda, double importo, String autore, String azione) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonArray jsonArray;
            try (FileReader fileReader = new FileReader(file)) {
                jsonArray = gson.fromJson(fileReader, JsonArray.class);
            }
            JsonObject nuovoControllo = new JsonObject();
            nuovoControllo.addProperty("Id", CasseStorage.generateID());
            nuovoControllo.addProperty("Azienda", azienda);
            nuovoControllo.addProperty("Importo", importo);
            nuovoControllo.addProperty("Autore", autore);
            nuovoControllo.addProperty("Data", Date.getDate());
            nuovoControllo.addProperty("Azione", azione);
            jsonArray.add(nuovoControllo);
            try (FileWriter fileWriter = new FileWriter(file)) {
                gson.toJson(jsonArray, fileWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<JsonObject> getItemsByAzienda(String azienda) {
        List<JsonObject> aziendaItems = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonArray jsonArray;
            try (FileReader fileReader = new FileReader(file)) {
                jsonArray = gson.fromJson(fileReader, JsonArray.class);
            }

            for (JsonElement element : jsonArray) {
                JsonObject controllo = element.getAsJsonObject();
                if (controllo.has("Azienda") && controllo.get("Azienda").getAsString().equalsIgnoreCase(azienda)) {
                    aziendaItems.add(controllo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aziendaItems;
    }
    public int getNumItemsByAzienda(String azienda) {
        int count = 0;
        try {
            Gson gson = new Gson();
            JsonArray jsonArray;
            try (FileReader fileReader = new FileReader(file)) {
                jsonArray = gson.fromJson(fileReader, JsonArray.class);
            }

            for (JsonElement element : jsonArray) {
                JsonObject controllo = element.getAsJsonObject();
                if (controllo.has("Azienda") && controllo.get("Azienda").getAsString().equalsIgnoreCase(azienda)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
