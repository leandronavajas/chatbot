package unlp.info.chatbot.service;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.*;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.client.response.WitMessageResponse;

import javax.annotation.Resource;

@Component
public class WitServiceImpl implements WitService {

    @Resource
    private Client<AddCategoryWitRequest, AddEntityWitResponse> addCategoryWitClient;
    @Resource
    private Client<GetMessageWitRequest, WitMessageResponse> getMessageWitClient;
    @Resource
    private Client<AddItemWitRequest, AddEntityWitResponse> addItemWitClient;
    @Resource
    private Client<AddPhraseWitRequest, AddEntityWitResponse> addPhraseWitClient;
    @Resource
    private Client<PutEntityWitRequest, AddEntityWitResponse> putEntityWitClient;


    @Override
    public AddEntityWitResponse addCategory(AddCategoryWitRequest request) {
        return this.addCategoryWitClient.call(request);
    }

    @Override
    public AddEntityWitResponse addItem(AddItemWitRequest request) {
        return this.addItemWitClient.call(request);
    }

    @Override
    public AddEntityWitResponse addPhrase(AddPhraseWitRequest request) {
        return this.addPhraseWitClient.call(request);
    }

    @Override
    public AddEntityWitResponse updateEntity(PutEntityWitRequest request) {
        return this.putEntityWitClient.call(request);
    }

    @Override
    public WitMessageResponse obtainMessage(GetMessageWitRequest request) {
        return this.getMessageWitClient.call(request);
    }

}
