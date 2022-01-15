package com.example.inheritance.function;

import java.util.function.Function;

import com.example.inheritance.exception.BusinessException;
import com.example.inheritance.model.CommonOutput;
import com.example.inheritance.model.ErrorOutput;
import com.example.inheritance.model.Inheritance;
import com.example.inheritance.model.InheritanceInput;
import com.example.inheritance.service.InheritanceValidationService;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class InheritanceFunction implements Function<Message<InheritanceInput>, Message<CommonOutput<Inheritance>>> {

    private final InheritanceValidationService validationService;

    @Override
    public Message<CommonOutput<Inheritance>> apply(Message<InheritanceInput> helloWorldInputMessage) {

        log.info(this.getClass().getName() + " has started!");
        InheritanceInput input = helloWorldInputMessage.getPayload();
        log.info("[Input]" + input.getMessage());

        final CommonOutput<Inheritance> output = new CommonOutput<>();

        // 入力検証
        try {
            validationService.execute(input);
        } catch (BusinessException e) {
            output.setError(new ErrorOutput(e.getCode(), e.getMessage()));
            return MessageBuilder.withPayload(output).build();
        }

        Inheritance helloWorldOutput = new Inheritance();
        helloWorldOutput.setMessage(input.getMessage() + "ですよ～");
        output.setResult(helloWorldOutput);
        return MessageBuilder.withPayload(output).build();
    }
}
