//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.MapDifference;
//import com.google.common.collect.Maps;
//
//import java.util.Map;
//
//public class JsonDiffPrinter {
//    public static void printDifference(String json1, String json2) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> left = mapper.readValue(json1, Map.class);
//        Map<String, Object> right = mapper.readValue(json2, Map.class);
//        MapDifference<String, Object> diff = Maps.difference(left, right);
//
//        for (Map.Entry<String, Object> entry : diff.entriesOnlyOnLeft().entrySet()) {
//            System.out.println("\u001B[31mУдалено: " + entry.getKey() + " = " + entry.getValue() + "\u001B[0m");
//        }
//        for (Map.Entry<String, Object> entry : diff.entriesOnlyOnRight().entrySet()) {
//            System.out.println("\u001B[32mДобавлено: " + entry.getKey() + " = " + entry.getValue() + "\u001B[0m");
//        }
//        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : diff.entriesDiffering().entrySet()) {
//            System.out.println("\u001B[33mИзменено: " + entry.getKey() + " | " +
//                    entry.getValue().leftValue() + " -> " + entry.getValue().rightValue() + "\u001B[0m");
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        String repoPath = "C:/demo/translate";
//        String filePath = "src/main/resources/templates/en.json";
//        String previousJson = GitFileRetriever.getFileContentAtCommit(repoPath, filePath, "HEAD~1");
//        String updatedJson = GitFileRetriever.getFileContentAtCommit(repoPath, filePath, "HEAD");
//
//        printDifference(previousJson, updatedJson);
//    }
//}
