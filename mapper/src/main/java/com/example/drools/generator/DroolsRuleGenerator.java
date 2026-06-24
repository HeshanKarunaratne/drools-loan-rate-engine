package com.example.drools.generator;

import com.example.drools.dto.RuleCondition;
import com.example.drools.dto.RuleRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Heshan Karunaratne
 */
@Service
@Slf4j
public class DroolsRuleGenerator {

    private final Configuration freemarkerConfig;

    public DroolsRuleGenerator() throws IOException {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_32);
        freemarkerConfig.setClassLoaderForTemplateLoading(
                getClass().getClassLoader(), "templates");
    }

    public String generate(RuleRequest request) throws Exception {

        log.info("Started Generate");
        Template template = freemarkerConfig.getTemplate("rule.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("packageName", "com.example.drools");
        model.put("groupClass", "com.example.drools.dto.Group");
        model.put("ruleResultClass", "com.example.drools.dto.RuleResult");

        List<Map<String, String>> rules = new ArrayList<>();


        for (RuleCondition condition : request.getRules()) {
            try {
                Map<String, String> rule = new HashMap<>();
                String expr = ConditionBuilder.build(condition);

                rule.put("name", condition.getField());
                rule.put("field", condition.getField());
                rule.put("expression", expr);
                rule.put("getter", capitalize(condition.getField()));

                rules.add(rule);
            } catch (Exception e) {
                log.error("Failed to process condition: field={}, condition={}",
                        condition.getField(), condition, e);
            }
        }


        model.put("rules", rules);

        StringWriter out = new StringWriter();
        template.process(model, out);

        log.info("Finished template");
        return out.toString();
    }

    private String capitalize(String field) {
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }


}