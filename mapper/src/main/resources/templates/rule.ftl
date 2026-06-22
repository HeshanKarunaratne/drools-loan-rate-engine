package ${packageName};

import ${groupClass};
import ${ruleResultClass};

global java.util.List results;

dialect "mvel"

<#list rules as rule>
rule "${rule.name}"
when
    $u : Group(${rule.expression})
then
    results.add(new RuleResult(
        "${rule.field}",
        "APPROVED",
        $u.get${rule.getter}(),
        "${rule.expression}"
    ));
end

</#list>