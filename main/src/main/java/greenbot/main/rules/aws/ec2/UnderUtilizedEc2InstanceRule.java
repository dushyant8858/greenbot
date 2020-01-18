package greenbot.main.rules.aws.ec2;

import java.util.Arrays;

import greenbot.main.rules.AbstractGreenbotRule;
import greenbot.rule.model.AnalysisResponse;
import greenbot.rule.model.RuleInfo;
import greenbot.rule.model.RuleRequest;

public class UnderUtilizedEc2InstanceRule extends AbstractGreenbotRule {

    @Override
    public AnalysisResponse doWork(RuleRequest ruleRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RuleInfo ruleInfo() {
        return RuleInfo.builder()
                .id(buildId())
                .description("TODO")
                .permissions(Arrays.asList("ReadEc2State", "ReadCloudWatch"))
                .build();
    }
}
