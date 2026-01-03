package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
/**
 * На вход в качестве аргументов программы поступают три пути к файлу (в приложении к заданию находятся примеры этих файлов):
 * • values.json содержит результаты прохождения тестов с уникальными id
 * • tests.json содержит структуру для построения отчета на основе прошедших тестов (вложенность может быть большей, чем в примере)
 * • report.json - сюда записывается результат.
 * Напишите программу, которая формирует файл report.json с заполненными полями value для структуры tests.json на основании values.json.
 * Структура report.json такая же, как у tests.json, только заполнены поля “value”.
 * <p>
 * На вход программы передается три пути к файлу!
 
 Запуск через мавен: 
	mvn compile
	mvn exec:java -Dexec.mainClass="org.example.Task3" -Dexec.args="values.json tests.json report.json"
 */
public class Task3 {
//tests.json report.json
    public static void main(String[] args) throws Exception {
//ObjectMapper позволяет читать и записывать JSON (туда и обратно) (из джейсона в мапу и обратно)
// (сериализация [джава Object в джейсон строку] и
// десериализация [джейсон строка в джава объект] )
        ObjectMapper mapper = new ObjectMapper();

        // 1. values.json -> Map
        JsonNode valuesRoot = mapper.readTree(new File(args[0]));
        Map<Integer, String> valueMap = new HashMap<>();

        for (JsonNode node : valuesRoot.get("values")) {
            int id = node.get("id").asInt();
            String value = node.get("value").asText();
            valueMap.put(id, value);
        }

        // 2. tests.json -> JsonNode
        JsonNode testsRoot = mapper.readTree(new File(args[1]));

        // 3. Рекурсивно заполняем value
        fillValues(testsRoot.get("tests"), valueMap);

        // 4. Записываем report.json
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[2]), testsRoot);
    }

    private static void fillValues(JsonNode testsNode, Map<Integer, String> valueMap) {

        for (JsonNode test : testsNode) {//идём по всем уздлам в tests через обращение к главному узлу tests testsNode

            int id = test.get("id").asInt();//получили текущий айдишник узла в tests

            // ObjectNode нужен, чтобы можно было менять JSON. JsonNode — только для чтения
            ObjectNode objectNode = (ObjectNode) test;

            if (valueMap.containsKey(id)) {//если есть такой же (как текущий из tests) айдишник в valueMap
                objectNode.put("value", valueMap.get(id));//то в тесте с таким же айдишником меняем значение
                // value на то, что лежит в valueMap
            }
            // Если есть вложенные тесты — рекурсия
            if (test.has("values")) {
                fillValues(test.get("values"), valueMap);
            }
        }
    }
}
