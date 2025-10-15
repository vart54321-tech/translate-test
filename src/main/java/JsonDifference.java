import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.File;
import java.io.IOException;

public class JsonDifference {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File enFile = new File("src/main/resources/templates/en.json");
        File bgFile = new File("src/main/resources/templates/bg-BG.json");
        File outputFile = new File("src/main/resources/templates/en_updated.json");

        JsonNode source = mapper.readTree(enFile);
        JsonNode target = mapper.readTree(bgFile);

        JsonNode patchNode = JsonDiff.asJson(source, target);
        JsonPatch patch = JsonPatch.fromJson(patchNode);

        try {
            JsonNode updatedJson = patch.apply(source);
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, updatedJson);

            System.out.println(patchNode.toPrettyString());
            System.out.println("Updated JSON saved to " + outputFile.getAbsolutePath());
        } catch (JsonPatchException e) {
            System.err.println("Ошибка при применении patch: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
