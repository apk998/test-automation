package com.solvd.testautomation.api;

import com.solvd.testautomation.api.domain.Word;
import com.solvd.testautomation.api.method.GetDefinitionByWord;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

public class GetDefinitionByWordTest {

    @Test(description = "verify getting dictionary definition by word")
    public void VerifyGetDefinitionByWordTest() {
        Word word = new Word();
        word.setWord("pernicious");

        GetDefinitionByWord definition = new GetDefinitionByWord(word.getWord());
        definition.addProperty("word", word);

        definition.expectResponseStatus(HttpResponseStatusType.OK_200);
        definition.callAPI();

        definition.validateResponse();
    }
}
